/*
 * Copyright (c) 2011-2014 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *    Simon McDuff - maintenance
 */
package org.eclipse.emf.internal.cdo.view;

import org.eclipse.emf.cdo.CDOState;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDTemp;
import org.eclipse.emf.cdo.common.revision.CDORevisable;
import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.common.revision.CDORevisionFactory;
import org.eclipse.emf.cdo.common.revision.CDORevisionKey;
import org.eclipse.emf.cdo.common.revision.CDORevisionUtil;
import org.eclipse.emf.cdo.common.revision.delta.CDOFeatureDelta;
import org.eclipse.emf.cdo.common.revision.delta.CDOListFeatureDelta;
import org.eclipse.emf.cdo.common.revision.delta.CDORevisionDelta;
import org.eclipse.emf.cdo.common.revision.delta.CDOSetFeatureDelta;
import org.eclipse.emf.cdo.common.security.NoPermissionException;
import org.eclipse.emf.cdo.common.util.PartialCollectionLoadingNotSupportedException;
import org.eclipse.emf.cdo.spi.common.model.InternalCDOPackageInfo;
import org.eclipse.emf.cdo.spi.common.revision.InternalCDORevision;
import org.eclipse.emf.cdo.spi.common.revision.InternalCDORevisionCache;
import org.eclipse.emf.cdo.spi.common.revision.InternalCDORevisionDelta;
import org.eclipse.emf.cdo.spi.common.revision.InternalCDORevisionManager;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.transaction.CDOUndoDetector;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.cdo.view.CDOInvalidationPolicy;
import org.eclipse.emf.cdo.view.CDOView;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.eclipse.emf.internal.cdo.bundle.OM;
import org.eclipse.emf.internal.cdo.object.CDONotificationBuilder;

import org.eclipse.net4j.util.collection.Pair;
import org.eclipse.net4j.util.fsm.FiniteStateMachine;
import org.eclipse.net4j.util.fsm.ITransition;
import org.eclipse.net4j.util.om.trace.ContextTracer;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.spi.cdo.CDOSessionProtocol.CommitTransactionResult;
import org.eclipse.emf.spi.cdo.FSMUtil;
import org.eclipse.emf.spi.cdo.InternalCDOObject;
import org.eclipse.emf.spi.cdo.InternalCDOSavepoint;
import org.eclipse.emf.spi.cdo.InternalCDOSession;
import org.eclipse.emf.spi.cdo.InternalCDOTransaction;
import org.eclipse.emf.spi.cdo.InternalCDOView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Eike Stepper
 */
public final class CDOStateMachine extends FiniteStateMachine<CDOState, CDOEvent, InternalCDOObject>
{
  // @Singleton
  public static final CDOStateMachine INSTANCE = new CDOStateMachine();

  static final ThreadLocal<Boolean> SWITCHING_TARGET = new InheritableThreadLocal<Boolean>();

  private static final ContextTracer TRACER = new ContextTracer(OM.DEBUG_STATEMACHINE, CDOStateMachine.class);

  private InternalCDOObject lastTracedObject;

  private CDOState lastTracedState;

  private CDOEvent lastTracedEvent;

  @SuppressWarnings("unchecked")
  private CDOStateMachine()
  {
    super(CDOState.class, CDOEvent.class);

    init(CDOState.TRANSIENT, CDOEvent.PREPARE, new PrepareTransition());
    init(CDOState.TRANSIENT, CDOEvent.ATTACH, FAIL);
    init(CDOState.TRANSIENT, CDOEvent.DETACH, IGNORE);
    init(CDOState.TRANSIENT, CDOEvent.REATTACH, new ReattachTransition());
    init(CDOState.TRANSIENT, CDOEvent.READ, IGNORE);
    init(CDOState.TRANSIENT, CDOEvent.WRITE, IGNORE);
    init(CDOState.TRANSIENT, CDOEvent.INVALIDATE, IGNORE);
    init(CDOState.TRANSIENT, CDOEvent.DETACH_REMOTE, IGNORE);
    init(CDOState.TRANSIENT, CDOEvent.COMMIT, FAIL);
    init(CDOState.TRANSIENT, CDOEvent.ROLLBACK, FAIL);

    init(CDOState.PREPARED, CDOEvent.PREPARE, FAIL);
    init(CDOState.PREPARED, CDOEvent.ATTACH, new AttachTransition());
    init(CDOState.PREPARED, CDOEvent.DETACH, FAIL);
    init(CDOState.PREPARED, CDOEvent.REATTACH, FAIL);
    init(CDOState.PREPARED, CDOEvent.READ, IGNORE);
    init(CDOState.PREPARED, CDOEvent.WRITE, FAIL);
    init(CDOState.PREPARED, CDOEvent.INVALIDATE, FAIL);
    init(CDOState.PREPARED, CDOEvent.DETACH_REMOTE, FAIL);
    init(CDOState.PREPARED, CDOEvent.COMMIT, FAIL);
    init(CDOState.PREPARED, CDOEvent.ROLLBACK, FAIL);

    init(CDOState.NEW, CDOEvent.PREPARE, FAIL);
    init(CDOState.NEW, CDOEvent.ATTACH, FAIL);
    init(CDOState.NEW, CDOEvent.DETACH, new DetachTransition());
    init(CDOState.NEW, CDOEvent.REATTACH, FAIL);
    init(CDOState.NEW, CDOEvent.READ, IGNORE);
    init(CDOState.NEW, CDOEvent.WRITE, new WriteNewTransition());
    init(CDOState.NEW, CDOEvent.INVALIDATE, FAIL);
    init(CDOState.NEW, CDOEvent.DETACH_REMOTE, FAIL);
    init(CDOState.NEW, CDOEvent.COMMIT, new CommitTransition(false));
    init(CDOState.NEW, CDOEvent.ROLLBACK, FAIL);

    init(CDOState.CLEAN, CDOEvent.PREPARE, FAIL);
    init(CDOState.CLEAN, CDOEvent.ATTACH, FAIL);
    init(CDOState.CLEAN, CDOEvent.DETACH, new DetachTransition());
    init(CDOState.CLEAN, CDOEvent.REATTACH, FAIL);
    init(CDOState.CLEAN, CDOEvent.READ, IGNORE);
    init(CDOState.CLEAN, CDOEvent.WRITE, new WriteTransition(false));
    init(CDOState.CLEAN, CDOEvent.INVALIDATE, new InvalidateTransition());
    init(CDOState.CLEAN, CDOEvent.DETACH_REMOTE, DetachRemoteTransition.INSTANCE);
    init(CDOState.CLEAN, CDOEvent.COMMIT, FAIL);
    init(CDOState.CLEAN, CDOEvent.ROLLBACK, FAIL);

    init(CDOState.DIRTY, CDOEvent.PREPARE, FAIL);
    init(CDOState.DIRTY, CDOEvent.ATTACH, FAIL);
    init(CDOState.DIRTY, CDOEvent.DETACH, new DetachTransition());
    init(CDOState.DIRTY, CDOEvent.REATTACH, FAIL);
    init(CDOState.DIRTY, CDOEvent.READ, IGNORE);
    init(CDOState.DIRTY, CDOEvent.WRITE, new RewriteTransition());
    init(CDOState.DIRTY, CDOEvent.INVALIDATE, new ConflictTransition());
    init(CDOState.DIRTY, CDOEvent.DETACH_REMOTE, new InvalidConflictTransition());
    init(CDOState.DIRTY, CDOEvent.COMMIT, new CommitTransition(true));
    init(CDOState.DIRTY, CDOEvent.ROLLBACK, new RollbackTransition());

    init(CDOState.PROXY, CDOEvent.PREPARE, FAIL);
    init(CDOState.PROXY, CDOEvent.ATTACH, FAIL);
    init(CDOState.PROXY, CDOEvent.DETACH, new DetachTransition());
    init(CDOState.PROXY, CDOEvent.REATTACH, FAIL);
    init(CDOState.PROXY, CDOEvent.READ, new LoadTransition());
    init(CDOState.PROXY, CDOEvent.WRITE, new WriteTransition(true));
    init(CDOState.PROXY, CDOEvent.INVALIDATE, IGNORE);
    init(CDOState.PROXY, CDOEvent.DETACH_REMOTE, DetachRemoteTransition.INSTANCE);
    init(CDOState.PROXY, CDOEvent.COMMIT, FAIL);
    init(CDOState.PROXY, CDOEvent.ROLLBACK, FAIL);

    init(CDOState.CONFLICT, CDOEvent.PREPARE, FAIL);
    init(CDOState.CONFLICT, CDOEvent.ATTACH, IGNORE);
    init(CDOState.CONFLICT, CDOEvent.DETACH, new DetachTransition());
    init(CDOState.CONFLICT, CDOEvent.REATTACH, FAIL);
    init(CDOState.CONFLICT, CDOEvent.READ, IGNORE);
    init(CDOState.CONFLICT, CDOEvent.WRITE, new RewriteTransition());
    init(CDOState.CONFLICT, CDOEvent.INVALIDATE, IGNORE);
    init(CDOState.CONFLICT, CDOEvent.DETACH_REMOTE, IGNORE);
    init(CDOState.CONFLICT, CDOEvent.COMMIT, IGNORE);
    init(CDOState.CONFLICT, CDOEvent.ROLLBACK, new RollbackTransition());

    init(CDOState.INVALID, CDOEvent.PREPARE, InvalidTransition.INSTANCE);
    init(CDOState.INVALID, CDOEvent.ATTACH, InvalidTransition.INSTANCE);
    init(CDOState.INVALID, CDOEvent.DETACH, InvalidTransition.INSTANCE);
    init(CDOState.INVALID, CDOEvent.REATTACH, FAIL);
    init(CDOState.INVALID, CDOEvent.READ, InvalidTransition.INSTANCE);
    init(CDOState.INVALID, CDOEvent.WRITE, InvalidTransition.INSTANCE);
    init(CDOState.INVALID, CDOEvent.INVALIDATE, IGNORE); // TODO Handle changeViewTarget!!!
    init(CDOState.INVALID, CDOEvent.DETACH_REMOTE, IGNORE);
    init(CDOState.INVALID, CDOEvent.COMMIT, InvalidTransition.INSTANCE);
    init(CDOState.INVALID, CDOEvent.ROLLBACK, InvalidTransition.INSTANCE);

    init(CDOState.INVALID_CONFLICT, CDOEvent.PREPARE, InvalidTransition.INSTANCE);
    init(CDOState.INVALID_CONFLICT, CDOEvent.ATTACH, InvalidTransition.INSTANCE);
    init(CDOState.INVALID_CONFLICT, CDOEvent.DETACH, InvalidTransition.INSTANCE);
    init(CDOState.INVALID_CONFLICT, CDOEvent.REATTACH, FAIL);
    init(CDOState.INVALID_CONFLICT, CDOEvent.READ, IGNORE);
    init(CDOState.INVALID_CONFLICT, CDOEvent.WRITE, IGNORE);
    init(CDOState.INVALID_CONFLICT, CDOEvent.INVALIDATE, IGNORE);
    init(CDOState.INVALID_CONFLICT, CDOEvent.DETACH_REMOTE, IGNORE);
    init(CDOState.INVALID_CONFLICT, CDOEvent.COMMIT, InvalidTransition.INSTANCE);
    init(CDOState.INVALID_CONFLICT, CDOEvent.ROLLBACK, DetachRemoteTransition.INSTANCE);
  }

  /**
   * The object is already attached in EMF world. It contains all the information needed to know where it will be connected.
   */
  public void attach(InternalCDOObject object, InternalCDOTransaction transaction)
  {
    synchronized (transaction)
    {
      List<InternalCDOObject> contents = new ArrayList<InternalCDOObject>();
      prepare(object, Pair.create(transaction, contents));

      attachOrReattach(object, transaction);
      for (InternalCDOObject content : contents)
      {
        attachOrReattach(content, transaction);
      }
    }
  }

  private void attachOrReattach(InternalCDOObject object, InternalCDOTransaction transaction)
  {
    // Bug 283985 (Re-attachment):
    // If the object going through a prepareTransition is present in cleanRevisions,
    // then it was detached earlier, and so we can infer that it is being re-attached
    if (transaction.getCleanRevisions().containsKey(object))
    {
      reattachObject(object, transaction);
    }
    else
    {
      attachObject(object);
    }
  }

  /**
   * Phase 1: TRANSIENT --> PREPARED
   */
  private void prepare(InternalCDOObject object,
      Pair<InternalCDOTransaction, List<InternalCDOObject>> transactionAndContents)
  {
    if (TRACER.isEnabled())
    {
      TRACER.format("PREPARE: {0} --> {1}", object, transactionAndContents.getElement1()); //$NON-NLS-1$
    }

    process(object, CDOEvent.PREPARE, transactionAndContents);
  }

  /**
   * Phase 2: PREPARED --> NEW
   */
  private void attachObject(InternalCDOObject object)
  {
    if (TRACER.isEnabled())
    {
      TRACER.format("ATTACH: {0}", object); //$NON-NLS-1$
    }

    process(object, CDOEvent.ATTACH, null);
  }

  private void reattachObject(InternalCDOObject object, InternalCDOTransaction transaction)
  {
    if (TRACER.isEnabled())
    {
      TRACER.format("REATTACH: {0}", object);
    }

    process(object, CDOEvent.REATTACH, transaction);
  }

  public void detach(InternalCDOObject object)
  {
    synchronized (getMonitor(object))
    {
      if (TRACER.isEnabled())
      {
        trace(object, CDOEvent.DETACH);
      }

      List<InternalCDOObject> objectsToDetach = new ArrayList<InternalCDOObject>();
      InternalCDOTransaction transaction = (InternalCDOTransaction)object.cdoView();

      // Accumulate objects that needs to be detached
      // If we have an error, we will keep the graph exactly like it was before.
      process(object, CDOEvent.DETACH, objectsToDetach);

      // postDetach requires the object to be TRANSIENT
      for (InternalCDOObject content : objectsToDetach)
      {
        CDOState oldState = content.cdoInternalSetState(CDOState.TRANSIENT);
        content.cdoInternalPostDetach(false);
        content.cdoInternalSetState(oldState);
      }

      // detachObject needs to know the state before we change the object to TRANSIENT
      for (InternalCDOObject content : objectsToDetach)
      {
        transaction.detachObject(content);
        content.cdoInternalSetState(CDOState.TRANSIENT);

        content.cdoInternalSetView(null);
        content.cdoInternalSetID(null);
      }
    }
  }

  public InternalCDORevision read(InternalCDOObject object)
  {
    synchronized (getMonitor(object))
    {
      if (TRACER.isEnabled())
      {
        trace(object, CDOEvent.READ);
      }

      process(object, CDOEvent.READ, null);
      return object.cdoRevision();
    }
  }

  public InternalCDORevision readNoLoad(InternalCDOObject object)
  {
    synchronized (getMonitor(object))
    {
      switch (object.cdoState())
      {
      case TRANSIENT:
      case PREPARED:
      case NEW:
      case CONFLICT:
      case INVALID_CONFLICT:
      case INVALID:
      case PROXY:
        return null;
      }

      return object.cdoRevision();
    }
  }

  public Object write(InternalCDOObject object, CDOFeatureDelta featureDelta)
  {
    synchronized (getMonitor(object))
    {
      return writeWithoutViewLock(object, featureDelta);
    }
  }

  private Object writeWithoutViewLock(InternalCDOObject object, CDOFeatureDelta featureDelta)
  {
    if (TRACER.isEnabled())
    {
      trace(object, CDOEvent.WRITE);
    }

    // TODO: Make FeatureDeltaAndResult constant
    FeatureDeltaAndResult featureDeltaAndResult = new FeatureDeltaAndResult(featureDelta);

    process(object, CDOEvent.WRITE, featureDeltaAndResult);
    return featureDeltaAndResult.getResult();
  }

  public void reload(InternalCDOObject... objects)
  {
    if (objects == null || objects.length == 0)
    {
      return;
    }

    synchronized (getMonitor(objects[0]))
    {
      for (InternalCDOObject object : objects)
      {
        CDOState state = object.cdoState();
        if (state == CDOState.CLEAN || state == CDOState.PROXY)
        {
          changeState(object, CDOState.PROXY);
          object.cdoInternalSetRevision(null);
          read(object);
        }
      }
    }
  }

  public void invalidate(InternalCDOObject object, CDORevisionKey key)
  {
    synchronized (getMonitor(object))
    {
      if (TRACER.isEnabled())
      {
        trace(object, CDOEvent.INVALIDATE);
      }

      process(object, CDOEvent.INVALIDATE, key);
    }
  }

  public void detachRemote(InternalCDOObject object)
  {
    synchronized (getMonitor(object))
    {
      if (TRACER.isEnabled())
      {
        trace(object, CDOEvent.DETACH_REMOTE);
      }

      process(object, CDOEvent.DETACH_REMOTE, null);
    }
  }

  public void commit(InternalCDOObject object, CommitTransactionResult result)
  {
    synchronized (getMonitor(object))
    {
      if (TRACER.isEnabled())
      {
        trace(object, CDOEvent.COMMIT);
      }

      process(object, CDOEvent.COMMIT, result);
    }
  }

  public void rollback(InternalCDOObject object)
  {
    synchronized (getMonitor(object))
    {
      if (TRACER.isEnabled())
      {
        trace(object, CDOEvent.ROLLBACK);
      }

      process(object, CDOEvent.ROLLBACK, null);
    }
  }

  @Override
  protected CDOState getState(InternalCDOObject object)
  {
    return object.cdoState();
  }

  @Override
  protected void setState(InternalCDOObject object, CDOState state)
  {
    object.cdoInternalSetState(state);
  }

  private Object getMonitor(InternalCDOObject object)
  {
    InternalCDOView view = object.cdoView();
    if (view != null)
    {
      return view;
    }

    // In TRANSIENT and PREPARED the object is not yet attached to a view
    return object;
  }

  /**
   * Removes clutter from the trace log
   */
  private void trace(InternalCDOObject object, CDOEvent event)
  {
    CDOState state = object.cdoState();
    if (lastTracedObject != object || lastTracedState != state || lastTracedEvent != event)
    {
      TRACER.format("{0}: {1}", event, object.getClass().getName()); //$NON-NLS-1$
      lastTracedObject = object;
      lastTracedState = state;
      lastTracedEvent = event;
    }
  }

  @SuppressWarnings("unused")
  private void testAttach(InternalCDOObject object)
  {
    process(object, CDOEvent.ATTACH, null);
  }

  public void internalReattach(InternalCDOObject object, InternalCDOTransaction transaction)
  {
    InternalCDORevisionManager revisionManager = transaction.getSession().getRevisionManager();
    InternalCDORevision cleanRevision = transaction.getCleanRevisions().get(object).copy();
    CDOID id = cleanRevision.getID();

    // Bug 373096: Determine clean revision of the CURRENT/LAST savepoint
    InternalCDOSavepoint savepoint = transaction.getFirstSavepoint();
    while (savepoint.getNextSavepoint() != null)
    {
      CDORevisionDelta delta = savepoint.getRevisionDeltas2().get(id);
      if (delta != null)
      {
        delta.applyTo(cleanRevision);
      }

      savepoint = savepoint.getNextSavepoint();
    }

    object.cdoInternalSetView(transaction);

    // Construct a new revision
    CDORevisionFactory factory = revisionManager.getFactory();
    InternalCDORevision revision = (InternalCDORevision)factory.createRevision(object.eClass());
    revision.setID(id);
    revision.setBranchPoint(cleanRevision.getBranch().getHead());
    revision.setVersion(cleanRevision.getVersion());

    // Populate the revision based on the values in the CDOObject
    object.cdoInternalSetRevision(revision);
    object.cdoInternalPostAttach();

    // Compute a revision delta and register it with the tx
    CDORevisionDelta revisionDelta = revision.compare(cleanRevision);
    if (revisionDelta.isEmpty())
    {
      changeState(object, CDOState.CLEAN);
    }
    else
    {
      transaction.registerRevisionDelta(revisionDelta);
      transaction.registerDirty(object, (CDOFeatureDelta)null);
      changeState(object, CDOState.DIRTY);
    }

    // Add the object to the set of reattached objects
    InternalCDOSavepoint lastSavepoint = transaction.getLastSavepoint();
    lastSavepoint.getReattachedObjects().put(id, object);
  }

  public void dispatchLoadNotification(InternalCDOObject object)
  {
    InternalCDOView view = object.cdoView();
    if (view.options().isLoadNotificationEnabled())
    {
      try
      {
        InternalCDORevision revision = object.cdoRevision();
        CDONotificationBuilder builder = new CDONotificationBuilder(view);

        NotificationChain notification = builder.buildNotification(object, revision);
        if (notification != null)
        {
          notification.dispatch();
        }
      }
      catch (PartialCollectionLoadingNotSupportedException ex)
      {
        if (TRACER.isEnabled())
        {
          TRACER.trace(ex);
        }
      }
    }
  }

  private void internalLoad(InternalCDOObject object, boolean forWrite)
  {
    object.cdoInternalPreLoad();

    InternalCDOView view = object.cdoView();
    InternalCDORevision revision = view.getRevision(object.cdoID(), true);
    if (revision == null)
    {
      INSTANCE.detachRemote(object);
      CDOInvalidationPolicy policy = view.options().getInvalidationPolicy();
      policy.handleInvalidObject(object);
    }

    if (forWrite && !revision.isWritable())
    {
      throw new NoPermissionException(revision);
    }

    object.cdoInternalSetRevision(revision);
    changeState(object, CDOState.CLEAN);
    object.cdoInternalPostLoad();
    dispatchLoadNotification(object);
  }

  /**
   * @author Eike Stepper
   */
  public static final class FeatureDeltaAndResult
  {
    private final CDOFeatureDelta featureDelta;

    private Object result;

    public FeatureDeltaAndResult(CDOFeatureDelta featureDelta)
    {
      this.featureDelta = featureDelta;
    }

    public CDOFeatureDelta getFeatureDelta()
    {
      return featureDelta;
    }

    public Object getResult()
    {
      return result;
    }

    public void setResult(Object result)
    {
      this.result = result;
    }
  }

  /**
   * Prepares a tree of transient objects to be subsequently {@link AttachTransition attached} to a CDOView.
   * <p>
   * Execution is recursive and includes:
   * <ol>
   * <li>Assignment of a new {@link CDOIDTemp}
   * <li>Assignment of a new {@link CDORevision}
   * <li>Bidirectional association with the {@link CDOView}
   * <li>Registration with the {@link CDOTransaction}
   * <li>Changing state to {@link CDOState#PREPARED PREPARED}
   * </ol>
   *
   * @see AttachTransition
   * @author Eike Stepper
   */
  private final class PrepareTransition implements
      ITransition<CDOState, CDOEvent, InternalCDOObject, Pair<InternalCDOTransaction, List<InternalCDOObject>>>
  {
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event,
        Pair<InternalCDOTransaction, List<InternalCDOObject>> transactionAndContents)
    {
      InternalCDOTransaction transaction = transactionAndContents.getElement1();
      List<InternalCDOObject> contents = transactionAndContents.getElement2();

      // If the object going through a prepareTransition is present in cleanRevisions,
      // then it was detached earlier, and so we can infer that it is being re-attached
      boolean reattaching = transaction.getCleanRevisions().containsKey(object);

      if (!reattaching)
      {
        // Prepare object
        CDOID id = transaction.createIDForNewObject(object.cdoInternalInstance());
        object.cdoInternalSetView(transaction);
        changeState(object, CDOState.PREPARED);

        // Create new revision
        EClass eClass = object.eClass();
        InternalCDOSession session = transaction.getSession();
        checkPackageRegistrationProblems(session, eClass);

        CDORevisionFactory factory = session.getRevisionManager().getFactory();
        InternalCDORevision revision = (InternalCDORevision)factory.createRevision(eClass);
        revision.setID(id);
        revision.setBranchPoint(transaction.getBranch().getHead());

        object.cdoInternalSetRevision(revision);

        // Register object
        transaction.registerObject(object);
      }

      transaction.registerAttached(object, !reattaching);

      // Prepare content tree
      for (Iterator<InternalEObject> it = getPersistentContents(object); it.hasNext();)
      {
        InternalEObject content = it.next();
        Resource.Internal directResource = content.eDirectResource();

        boolean objectIsResource = directResource == object;
        if (objectIsResource || directResource == null)
        {
          InternalCDOObject adapted = FSMUtil.adapt(content, transaction);
          contents.add(adapted);
          INSTANCE.process(adapted, CDOEvent.PREPARE, transactionAndContents);
        }
      }
    }

    private Iterator<InternalEObject> getPersistentContents(InternalCDOObject object)
    {
      EStructuralFeature[] features = object.cdoClassInfo().getAllPersistentContainments();
      return new EContentsEList.ResolvingFeatureIteratorImpl<InternalEObject>(object, features);
    }

    private void checkPackageRegistrationProblems(InternalCDOSession session, EClass eClass)
    {
      if (session.options().isGeneratedPackageEmulationEnabled())
      {
        // Check that there are no multiple EPackages with the same URI in system. Bug 335004
        String packageURI = eClass.getEPackage().getNsURI();
        Object packageObject = session.getPackageRegistry().get(packageURI);
        if (packageObject instanceof InternalCDOPackageInfo)
        {
          packageObject = ((InternalCDOPackageInfo)packageObject).getEPackage(false);
        }

        if (packageObject instanceof EPackage && packageObject != eClass.getEPackage())
        {
          throw new IllegalStateException(MessageFormat.format(
              "Global EPackage {0} for EClass {1} is different from EPackage found in CDOPackageRegistry", packageURI,
              eClass));
        }
      }
    }
  }

  /**
   * Attaches a tree of {@link PrepareTransition prepared} objects to a CDOView.
   * <p>
   * Execution is recursive and includes:
   * <ol>
   * <li>Calling {@link InternalCDOObject#cdoInternalPostAttach()},<br>
   * which includes for {@link CDOObjectImpl}:
   * <ol>
   * <li>Population of the CDORevision with the current values in
   * {@link EStoreEObjectImpl#eSetting(org.eclipse.emf.ecore.EStructuralFeature) eSettings}
   * <li>Unsetting {@link EStoreEObjectImpl#eSetting(org.eclipse.emf.ecore.EStructuralFeature) eSettings}
   * </ol>
   * <li>Changing state to {@link CDOState#NEW NEW}
   * </ol>
   *
   * @see PrepareTransition
   * @author Eike Stepper
   */
  private final class AttachTransition implements ITransition<CDOState, CDOEvent, InternalCDOObject, Object>
  {
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, Object NULL)
    {
      object.cdoInternalPostAttach();
      changeState(object, CDOState.NEW);
    }
  }

  /**
   * Bug 283985 (Re-attachment)
   *
   * @author Caspar De Groot
   */
  private final class ReattachTransition implements
      ITransition<CDOState, CDOEvent, InternalCDOObject, InternalCDOTransaction>
  {
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, InternalCDOTransaction transaction)
    {
      internalReattach(object, transaction);

      // Bug 385268
      CDOID reattachedObject = object.cdoID();
      processRevisionDeltas(reattachedObject, transaction);
    }

    private void processRevisionDeltas(CDOID reattachedObject, InternalCDOTransaction transaction)
    {
      InternalCDOSavepoint lastSavepoint = transaction.getLastSavepoint();
      Map<CDOID, CDORevisionDelta> revisionDeltas = lastSavepoint.getRevisionDeltas2();
      for (Iterator<Entry<CDOID, CDORevisionDelta>> it = revisionDeltas.entrySet().iterator(); it.hasNext();)
      {
        Entry<CDOID, CDORevisionDelta> entry = it.next();
        CDORevisionDelta revisionDelta = entry.getValue();

        Map<EStructuralFeature, CDOFeatureDelta> map = ((InternalCDORevisionDelta)revisionDelta).getFeatureDeltaMap();
        processFeatureDeltas(reattachedObject, map);

        if (revisionDelta.isEmpty())
        {
          it.remove();

          CDOID id = revisionDelta.getID();
          InternalCDOObject cleanObject = (InternalCDOObject)lastSavepoint.getDirtyObjects().remove(id);
          if (cleanObject != null)
          {
            cleanObject.cdoInternalSetState(CDOState.CLEAN);
          }
        }
      }

      if (revisionDeltas.isEmpty())
      {
        transaction.setDirty(false);
      }
    }

    private void processFeatureDeltas(CDOID reattachedObject, Map<EStructuralFeature, CDOFeatureDelta> map)
    {
      for (Iterator<Entry<EStructuralFeature, CDOFeatureDelta>> it = map.entrySet().iterator(); it.hasNext();)
      {
        Entry<EStructuralFeature, CDOFeatureDelta> entry = it.next();
        CDOFeatureDelta featureDelta = entry.getValue();
        processFeatureDelta(reattachedObject, it, featureDelta);
      }
    }

    private void processFeatureDelta(CDOID reattachedObject, Iterator<?> it, CDOFeatureDelta featureDelta)
    {
      switch (featureDelta.getType())
      {
      case SET:
        CDOSetFeatureDelta setFeatureDelta = (CDOSetFeatureDelta)featureDelta;
        Object oldValue = setFeatureDelta.getOldValue();
        if (oldValue instanceof EObject)
        {
          oldValue = CDOUtil.getCDOObject((EObject)oldValue).cdoID();
        }

        Object newValue = setFeatureDelta.getValue();
        if (newValue instanceof EObject)
        {
          newValue = CDOUtil.getCDOObject((EObject)newValue).cdoID();
        }

        if (reattachedObject == oldValue && reattachedObject == newValue)
        {
          it.remove();
        }

        break;

      case LIST:
        CDOListFeatureDelta listFeatureDelta = (CDOListFeatureDelta)featureDelta;
        List<CDOFeatureDelta> listChanges = listFeatureDelta.getListChanges();
        for (Iterator<CDOFeatureDelta> listIt = listChanges.iterator(); listIt.hasNext();)
        {
          CDOFeatureDelta singleFeatureDelta = listIt.next();
          processFeatureDelta(reattachedObject, listIt, singleFeatureDelta);
        }

        if (listChanges.isEmpty())
        {
          it.remove();
        }

        break;
      }
    }
  }

  /**
   * @author Eike Stepper
   */
  private static final class DetachTransition implements
      ITransition<CDOState, CDOEvent, InternalCDOObject, List<InternalCDOObject>>
  {
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event,
        List<InternalCDOObject> objectsToDetach)
    {
      InternalCDOTransaction transaction = (InternalCDOTransaction)object.cdoView();
      objectsToDetach.add(object);
      boolean isResource = object instanceof Resource;

      // Prepare content tree
      for (Iterator<EObject> it = object.eContents().iterator(); it.hasNext();)
      {
        InternalEObject eObject = (InternalEObject)it.next();
        boolean isDirectlyConnected = isResource && eObject.eDirectResource() == object;
        if (isDirectlyConnected || eObject.eDirectResource() == null)
        {
          InternalCDOObject content = FSMUtil.adapt(eObject, transaction);
          if (content != null)
          {
            INSTANCE.process(content, CDOEvent.DETACH, objectsToDetach);
          }
        }
      }
    }
  }

  /**
   * @author Eike Stepper
   */
  final private class CommitTransition implements
      ITransition<CDOState, CDOEvent, InternalCDOObject, CommitTransactionResult>
  {
    public CommitTransition(boolean useDeltas)
    {
    }

    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, CommitTransactionResult data)
    {
      InternalCDOTransaction transaction = object.cdoView().toTransaction();
      InternalCDORevision revision = object.cdoRevision();
      Map<CDOID, CDOID> idMappings = data.getIDMappings();

      // Adjust object
      CDOID oldID = object.cdoID();
      CDOID newID = idMappings.get(oldID);
      if (newID != null)
      {
        revision.setID(newID);
        transaction.remapObject(oldID);
      }

      // Adjust revision
      revision.adjustForCommit(transaction.getBranch(), data.getTimeStamp());
      revision.adjustReferences(data.getReferenceAdjuster());
      revision.freeze();

      InternalCDORevisionManager revisionManager = transaction.getSession().getRevisionManager();
      revisionManager.addRevision(revision);

      changeState(object, CDOState.CLEAN);
    }
  }

  /**
   * @author Eike Stepper
   */
  private final class RollbackTransition implements ITransition<CDOState, CDOEvent, InternalCDOObject, Object>
  {
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, Object NULL)
    {
      InternalCDOTransaction transaction = object.cdoView().toTransaction();
      if (transaction.getLastSavepoint().isNewObject(object.cdoID()))
      {
        changeState(object, CDOState.TRANSIENT);
        object.cdoInternalPostDetach(false);
      }
      else
      {
        changeState(object, CDOState.PROXY);
        object.cdoInternalPostRollback();
      }

      object.cdoInternalSetRevision(null);
    }
  }

  /**
   * @author Eike Stepper
   */
  private static abstract class AbstractWriteTransition implements
      ITransition<CDOState, CDOEvent, InternalCDOObject, FeatureDeltaAndResult>
  {
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event,
        FeatureDeltaAndResult featureDeltaAndResult)
    {
      InternalCDORevision revision = object.cdoRevision();
      if (!revision.isWritable())
      {
        throw new NoPermissionException(revision);
      }

      InternalCDOTransaction transaction = object.cdoView().toTransaction();
      CDOFeatureDelta featureDelta = featureDeltaAndResult.getFeatureDelta();
      Object result = execute(object, transaction, featureDelta, revision);
      featureDeltaAndResult.setResult(result);
    }

    protected abstract Object execute(InternalCDOObject object, InternalCDOTransaction transaction,
        CDOFeatureDelta featureDelta, InternalCDORevision revision);
  }

  /**
   * @author Simon McDuff
   */
  private final class WriteNewTransition extends AbstractWriteTransition
  {
    @Override
    protected Object execute(InternalCDOObject object, InternalCDOTransaction transaction,
        CDOFeatureDelta featureDelta, InternalCDORevision revision)
    {
      Object result = null;
      if (featureDelta != null)
      {
        result = featureDelta.applyTo(revision);
      }

      transaction.registerFeatureDelta(object, featureDelta);
      return result;
    }
  }

  /**
   * @author Eike Stepper
   */
  private final class WriteTransition extends AbstractWriteTransition
  {
    private boolean load;

    public WriteTransition(boolean load)
    {
      this.load = load;
    }

    @Override
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event,
        FeatureDeltaAndResult featureDeltaAndResult)
    {
      if (load)
      {
        internalLoad(object, true);
      }

      super.execute(object, state, event, featureDeltaAndResult);
    }

    @Override
    protected Object execute(InternalCDOObject object, InternalCDOTransaction transaction,
        CDOFeatureDelta featureDelta, InternalCDORevision cleanRevision)
    {
      InternalCDORevision revision = cleanRevision.copy();

      Object result = null;
      if (featureDelta != null)
      {
        result = featureDelta.applyTo(revision);

        if (!transaction.hasMultipleSavepoints())
        {
          CDOUndoDetector undoDetector = transaction.options().getUndoDetector();
          if (undoDetector.detectUndo(transaction, cleanRevision, revision, featureDelta))
          {
            return null;
          }
        }
      }

      transaction.getCleanRevisions().put(object, cleanRevision);
      object.cdoInternalSetRevision(revision);

      transaction.registerDirty(object, featureDelta, cleanRevision);
      changeState(object, CDOState.DIRTY);
      return result;
    }
  }

  /**
   * @author Simon McDuff
   */
  private final class RewriteTransition extends AbstractWriteTransition
  {
    @Override
    protected Object execute(InternalCDOObject object, InternalCDOTransaction transaction,
        CDOFeatureDelta featureDelta, InternalCDORevision revision)
    {
      Map<InternalCDOObject, InternalCDORevision> cleanRevisions = transaction.getCleanRevisions();
      InternalCDORevision cleanRevision = cleanRevisions.get(object);

      Object result = null;
      if (featureDelta != null)
      {
        result = featureDelta.applyTo(revision);

        if (!transaction.hasMultipleSavepoints())
        {
          CDOUndoDetector undoDetector = transaction.options().getUndoDetector();
          if (undoDetector.detectUndo(transaction, cleanRevision, revision, featureDelta))
          {
            CDOID id = revision.getID();

            InternalCDOSavepoint lastSavepoint = transaction.getLastSavepoint();
            Map<CDOID, CDORevisionDelta> revisionDeltas = lastSavepoint.getRevisionDeltas2();
            InternalCDORevisionDelta revisionDelta = (InternalCDORevisionDelta)revisionDeltas.get(id);
            if (revisionDelta != null)
            {
              Map<EStructuralFeature, CDOFeatureDelta> featureDeltas = revisionDelta.getFeatureDeltaMap();
              featureDeltas.remove(featureDelta.getFeature());

              if (featureDeltas.isEmpty())
              {
                cleanRevisions.remove(object);
                revisionDeltas.remove(id);
                lastSavepoint.getDirtyObjects().remove(id);

                object.cdoInternalSetRevision(cleanRevision);
                changeState(object, CDOState.CLEAN);
              }
            }

            if (revisionDeltas.isEmpty())
            {
              transaction.setDirty(false);
            }

            return result;
          }
        }
      }

      transaction.registerFeatureDelta(object, featureDelta, cleanRevision);
      return result;
    }
  }

  /**
   * @author Simon McDuff
   */
  private static class DetachRemoteTransition implements ITransition<CDOState, CDOEvent, InternalCDOObject, Object>
  {
    static final DetachRemoteTransition INSTANCE = new DetachRemoteTransition();

    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, Object NULL)
    {
      CDOStateMachine.INSTANCE.changeState(object, CDOState.INVALID);

      InternalCDOView view = object.cdoView();
      view.deregisterObject(object);
      object.cdoInternalPostDetach(true);
    }
  }

  /**
   * @author Eike Stepper
   */
  private class InvalidateTransition implements ITransition<CDOState, CDOEvent, InternalCDOObject, CDORevisionKey>
  {
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, CDORevisionKey key)
    {
      InternalCDORevision oldRevision = object.cdoRevision();
      InternalCDORevision newRevision = null;

      InternalCDOView view = object.cdoView();
      InternalCDORevisionCache cache = view.getSession().getRevisionManager().getCache();

      if (SWITCHING_TARGET.get() == Boolean.TRUE)
      {
        CDORevisionDelta delta = (CDORevisionDelta)key;
        CDORevisable target = delta.getTarget();
        newRevision = (InternalCDORevision)cache.getRevisionByVersion(delta.getID(), target);
        if (newRevision == null)
        {
          newRevision = oldRevision.copy();
          view.getSession().resolveAllElementProxies(newRevision);
          delta.applyTo(newRevision);
          newRevision.setBranchPoint(target);
          newRevision.setVersion(target.getVersion());
          cache.addRevision(newRevision);
        }

        object.cdoInternalSetRevision(newRevision);
        changeState(object, CDOState.CLEAN);
        object.cdoInternalPostLoad();
        return;
      }

      if (key == null || key.getVersion() >= oldRevision.getVersion())
      {
        CDORevisionKey newKey = null;
        if (key != null)
        {
          int newVersion = getNewVersion(key);
          newKey = CDORevisionUtil.createRevisionKey(key.getID(), key.getBranch(), newVersion);
        }

        if (newKey != null)
        {
          newRevision = (InternalCDORevision)cache.getRevisionByVersion(newKey.getID(), newKey);
        }

        if (newRevision != null)
        {
          object.cdoInternalSetRevision(newRevision);
          changeState(object, CDOState.CLEAN);
          object.cdoInternalPostLoad();
        }
        else
        {
          changeState(object, CDOState.PROXY);

          CDOInvalidationPolicy policy = view.options().getInvalidationPolicy();
          policy.handleInvalidation(object, key);

          object.cdoInternalPostInvalidate();
        }
      }
    }

    private int getNewVersion(CDORevisionKey key)
    {
      if (key instanceof CDORevisionDelta)
      {
        CDORevisionDelta delta = (CDORevisionDelta)key;
        CDORevisable target = delta.getTarget();
        if (target != null && key.getBranch() == target.getBranch())
        {
          return target.getVersion();
        }
      }

      return key.getVersion() + 1;
    }
  }

  /**
   * @author Eike Stepper
   */
  private class ConflictTransition extends InvalidateTransition
  {
    @Override
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, CDORevisionKey key)
    {
      InternalCDORevision oldRevision = object.cdoRevision();
      if (key == null || key.getVersion() >= oldRevision.getVersion() - 1)
      {
        changeState(object, CDOState.CONFLICT);
        InternalCDOTransaction transaction = object.cdoView().toTransaction();
        transaction.setConflict(object);
      }
    }
  }

  /**
   * @author Simon McDuff
   */
  private final class InvalidConflictTransition extends ConflictTransition
  {
    @Override
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, CDORevisionKey UNUSED)
    {
      changeState(object, CDOState.INVALID_CONFLICT);

      InternalCDOTransaction transaction = object.cdoView().toTransaction();
      transaction.setConflict(object);
    }
  }

  /**
   * @author Eike Stepper
   */
  private final class LoadTransition implements ITransition<CDOState, CDOEvent, InternalCDOObject, Object>
  {
    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, Object UNUSED)
    {
      internalLoad(object, false);
    }
  }

  /**
   * @author Simon McDuff
   */
  private static final class InvalidTransition implements ITransition<CDOState, CDOEvent, InternalCDOObject, Object>
  {
    public static final InvalidTransition INSTANCE = new InvalidTransition();

    public void execute(InternalCDOObject object, CDOState state, CDOEvent event, Object NULL)
    {
      InternalCDOView view = object.cdoView();
      CDOInvalidationPolicy policy = view.options().getInvalidationPolicy();
      policy.handleInvalidObject(object);
    }
  }
}

/**
 * @author Eike Stepper
 */
enum CDOEvent
{
  PREPARE, ATTACH, DETACH, REATTACH, READ, WRITE, INVALIDATE, DETACH_REMOTE, COMMIT, ROLLBACK
}
