/***************************************************************************
 * Copyright (c) 2004 - 2007 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *    Simon McDuff - https://bugs.eclipse.org/bugs/show_bug.cgi?id=201266
 **************************************************************************/
package org.eclipse.emf.cdo.server;

import org.eclipse.emf.cdo.internal.protocol.model.CDOClassImpl;
import org.eclipse.emf.cdo.internal.protocol.model.CDOClassProxy;
import org.eclipse.emf.cdo.internal.protocol.model.CDOFeatureImpl;
import org.eclipse.emf.cdo.internal.protocol.model.CDOPackageImpl;
import org.eclipse.emf.cdo.internal.protocol.revision.CDORevisionImpl;
import org.eclipse.emf.cdo.internal.protocol.revision.delta.CDORevisionDeltaImpl;
import org.eclipse.emf.cdo.protocol.CDOID;
import org.eclipse.emf.cdo.protocol.model.CDOClass;
import org.eclipse.emf.cdo.protocol.model.CDOFeature;

import org.eclipse.net4j.util.transaction.ITransaction;

/**
 * @author Eike Stepper
 */
public interface IStoreWriter extends IStoreReader
{
  public IView getView();

  /**
   * Stores a complete description of a package so that it can be restored to an identical state at a later point in
   * time.
   * <p>
   * <b>Note:</b> The implementor of this method must not assume that references to classes in this package or in any
   * other package are already resolved or are resolveable at the point in time when this method is called by the
   * framework. References to classes frequently appear in {@link CDOClass#getSuperTypes()} and in
   * {@link CDOFeature#getReferenceType()}. Instead {@link CDOClassImpl#getSuperTypeProxies()} and
   * {@link CDOFeatureImpl#getReferenceTypeProxy()} should be used.
   * <p>
   * 
   * @see CDOClassProxy#getPackageURI()
   * @see CDOClassProxy#getClassifierID()
   */
  public void writePackages(CDOPackageImpl... cdoPackages);

  public void writeRevision(CDORevisionImpl revision);

  public void writeRevisionDelta(CDORevisionDeltaImpl delta);

  public CDOID primeNewObject(CDOClass cdoClass);

  public void rollback(IView view, ITransaction<IStoreWriter> storeTransaction);
}
