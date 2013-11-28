/*
 * Copyright (c) 2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.releng.setup.impl;

import org.eclipse.emf.cdo.releng.setup.BasicMaterializationTask;
import org.eclipse.emf.cdo.releng.setup.Preferences;
import org.eclipse.emf.cdo.releng.setup.SetupPackage;
import org.eclipse.emf.cdo.releng.setup.SetupTaskContext;
import org.eclipse.emf.cdo.releng.setup.Trigger;
import org.eclipse.emf.cdo.releng.setup.util.FileUtil;
import org.eclipse.emf.cdo.releng.setup.util.TargetPlatformUtil;
import org.eclipse.emf.cdo.releng.setup.util.log.ProgressLogMonitor;

import org.eclipse.net4j.util.io.FileLock;
import org.eclipse.net4j.util.io.IOUtil;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.materializer.InstallerJob;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.materializer.MaterializerJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.p2.publisher.AbstractPublisherApplication;
import org.eclipse.equinox.p2.publisher.eclipse.FeaturesAndBundlesPublisherApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Buckminster Import Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.cdo.releng.setup.impl.BasicMaterializationTaskImpl#getTargetPlatform <em>Target Platform</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class BasicMaterializationTaskImpl extends SetupTaskImpl implements BasicMaterializationTask
{
  private static final Pattern LINE_FEED = Pattern.compile("\n|\n\r|\r\n|\r");

  /**
   * The default value of the '{@link #getTargetPlatform() <em>Target Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetPlatform()
   * @generated
   * @ordered
   */
  protected static final String TARGET_PLATFORM_EDEFAULT = "${setup.branch.dir/tp}";

  /**
   * The cached value of the '{@link #getTargetPlatform() <em>Target Platform</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetPlatform()
   * @generated
   * @ordered
   */
  protected String targetPlatform = TARGET_PLATFORM_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BasicMaterializationTaskImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SetupPackage.Literals.BASIC_MATERIALIZATION_TASK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTargetPlatform()
  {
    return targetPlatform;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetPlatform(String newTargetPlatform)
  {
    String oldTargetPlatform = targetPlatform;
    targetPlatform = newTargetPlatform;
    if (eNotificationRequired())
    {
      eNotify(new ENotificationImpl(this, Notification.SET, SetupPackage.BASIC_MATERIALIZATION_TASK__TARGET_PLATFORM,
          oldTargetPlatform, targetPlatform));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
    case SetupPackage.BASIC_MATERIALIZATION_TASK__TARGET_PLATFORM:
      return getTargetPlatform();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
    case SetupPackage.BASIC_MATERIALIZATION_TASK__TARGET_PLATFORM:
      setTargetPlatform((String)newValue);
      return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
    case SetupPackage.BASIC_MATERIALIZATION_TASK__TARGET_PLATFORM:
      setTargetPlatform(TARGET_PLATFORM_EDEFAULT);
      return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
    case SetupPackage.BASIC_MATERIALIZATION_TASK__TARGET_PLATFORM:
      return TARGET_PLATFORM_EDEFAULT == null ? targetPlatform != null : !TARGET_PLATFORM_EDEFAULT
          .equals(targetPlatform);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy())
    {
      return super.toString();
    }

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (targetPlatform: ");
    result.append(targetPlatform);
    result.append(')');
    return result.toString();
  }

  @Override
  public Set<Trigger> getValidTriggers()
  {
    return Trigger.IDE_TRIGGERS;
  }

  public boolean isNeeded(SetupTaskContext context)
  {
    return context.getTrigger() == Trigger.MANUAL
        || !TargetPlatformUtil.hasTargetPlatform(getTargetPlatform(), context);
  }

  protected abstract String getMspec(SetupTaskContext context) throws Exception;

  public void perform(SetupTaskContext context) throws Exception
  {
    IProgressMonitor monitor = new ProgressLogMonitor(context);
    monitor.beginTask("Starting Buckminster import", 160);

    try
    {
      File tpOld = null;
      File tp = new File(getTargetPlatform());
      if (tp.exists())
      {
        tpOld = new File(tp.getParentFile(), tp.getName() + "." + System.currentTimeMillis());
        FileUtil.rename(tp, tpOld);
      }

      FileLock tpPoolLock = null;

      try
      {
        File tpPool = updateTargetPlatformPool(context);
        tpPoolLock = FileLock.forWrite(tpPool);

        tp.mkdirs();
        TargetPlatformUtil.setTargetPlatform(tp.toString(), context.getSetup().getBranch().getProject().getName()
            + " Target", true, context);

        BuckminsterHelper.materialize(context, getMspec(context), monitor);

        if (tpPoolLock != null)
        {
          updateBundlePool(context, tp.getAbsolutePath(), tpPool.getAbsolutePath());
        }

        if (context.isCancelled())
        {
          throw new InterruptedException();
        }
      }
      catch (Exception ex)
      {
        File tpBroken = new File(tp.getParentFile(), tp.getName() + "." + System.currentTimeMillis());
        FileUtil.rename(tp, tpBroken);
        if (tpOld != null)
        {
          FileUtil.rename(tpOld, tp);
        }

        FileUtil.deleteAsync(tpBroken);
        throw ex;
      }
      finally
      {
        if (tpPoolLock != null)
        {
          tpPoolLock.release();
        }
      }

      if (tpOld != null)
      {
        FileUtil.deleteAsync(tpOld);
      }
    }
    finally
    {
      monitor.done();
    }
  }

  private static File updateTargetPlatformPool(SetupTaskContext context) throws Exception
  {
    Preferences preferences = context.getSetup().getPreferences();

    File idePool = new File(preferences.getBundlePoolFolder());
    File tpPool = new File(preferences.getBundlePoolFolderTP());
    tpPool.mkdirs();

    FileLock idePoolLock = FileLock.forWrite(idePool);

    try
    {
      updateBundlePool(context, idePool.getAbsolutePath(), tpPool.getAbsolutePath());
    }
    finally
    {
      idePoolLock.release();
    }

    return tpPool;
  }

  private static void updateBundlePool(SetupTaskContext context, String source, String bundlePool) throws Exception
  {
    context.log("Updating bundle pool: " + bundlePool);

    String bundlePoolURL = "file:/" + bundlePool.replace('\\', '/');
    String[] args = { "-source", source, "-metadataRepository", bundlePoolURL, "-artifactRepository", bundlePoolURL,
        "-append", "-publishArtifacts" };

    AbstractPublisherApplication publisher = new FeaturesAndBundlesPublisherApplication();
    publisher.run(args);
  }

  private static class BuckminsterHelper
  {
    private static MaterializationSpec getMSpec(URL mspecURL, IProgressMonitor monitor) throws Exception
    {
      monitor.subTask("Downloading MSpec " + mspecURL);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DownloadManager.readInto(mspecURL, null, baos, MonitorUtils.subMonitor(monitor, 20));

      IParser<MaterializationSpec> parser = CorePlugin.getDefault().getParserFactory()
          .getMaterializationSpecParser(true);
      return parser.parse(mspecURL.toString(), new ByteArrayInputStream(baos.toByteArray()));
    }

    private static ComponentQuery getCQuery(URL cqueryURL, IProgressMonitor monitor) throws Exception
    {
      monitor.subTask("Downloading CQuery " + cqueryURL);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DownloadManager.readInto(cqueryURL, null, baos, MonitorUtils.subMonitor(monitor, 20));

      return ComponentQuery.fromStream(cqueryURL, null, new ByteArrayInputStream(baos.toByteArray()), true);
    }

    private static void materialize(final SetupTaskContext context, String mSpec, IProgressMonitor monitor)
        throws MalformedURLException, Exception
    {
      URL mSpecURL = new URL(mSpec);
      MaterializationSpec mspec = BuckminsterHelper.getMSpec(mSpecURL, monitor); // 20 ticks
      ComponentQuery cquery = BuckminsterHelper.getCQuery(mspec.getResolvedURL(), monitor); // 20 ticks

      IResolver resolver = new MainResolver(new ResolutionContext(mspec, cquery));
      resolver.getContext().setContinueOnError(true);

      monitor.subTask("Resolving components");
      BillOfMaterials bom = resolver.resolve(MonitorUtils.subMonitor(monitor, 40));

      MaterializationSpecBuilder mspecBuilder = new MaterializationSpecBuilder();
      mspecBuilder.initFrom(mspec);
      mspecBuilder.setName(bom.getViewName());

      bom.addMaterializationNodes(mspecBuilder);

      ResolutionContext resolutionContext = new ResolutionContext(bom.getQuery());
      MaterializationContext materializationContext = new MaterializationContext(bom, mspec, resolutionContext);

      monitor.subTask("Materializing components");
      MaterializationJob job = new MaterializationJob(materializationContext)
      {
        @Override
        public IStatus run(IProgressMonitor monitor)
        {
          PrintStream errStream = Logger.getErrStream();
          PrintStream outStream = Logger.getOutStream();
          PrintStream printStream = null;

          try
          {
            printStream = new PrintStream(new ByteArrayOutputStream()
            {
              String string = "";

              @Override
              public void flush() throws IOException
              {
                if (count != 0)
                {
                  String line = string + new String(toByteArray(), "UTF-8");
                  String[] split = LINE_FEED.split(line);
                  int limit = split.length - 1;
                  if (limit >= 0)
                  {
                    string = split[limit];
                    if (string.length() != line.length())
                    {
                      if (limit == 0)
                      {
                        context.log(string);
                        string = "";
                      }
                      else
                      {
                        for (int i = 0; i < limit; ++i)
                        {
                          context.log(split[i]);
                        }
                      }
                    }
                  }
                  reset();
                }
              }

              @Override
              public void close() throws IOException
              {
                flush();
              }
            }, true, "UTF-8");

            Logger.setErrStream(printStream);
            Logger.setOutStream(printStream);
            return super.run(monitor);
          }
          catch (UnsupportedEncodingException ex)
          {
            // UTF-8 is always supported so this can't happen.
            throw new RuntimeException(ex);
          }
          finally
          {
            Logger.setErrStream(errStream);
            Logger.setOutStream(outStream);
            IOUtil.close(printStream);
          }
        }

        @Override
        protected void internalRun(IProgressMonitor monitor, boolean waitForCompletion) throws CoreException
        {
          MaterializationContext context = getMaterializationContext();
          BillOfMaterials bom = context.getBillOfMaterials();

          Queue<MaterializerJob> allJobs = prepareJobs(monitor, bom);
          if (allJobs != null)
          {
            triggerJobs(monitor, allJobs);
            waitForJobs(monitor, allJobs, bom);
          }

          if (context.getStatus().getSeverity() == IStatus.ERROR)
          {
            throw BuckminsterException.wrap(context.getStatus());
          }

          InstallerJob installerJob = new InstallerJob(context, !waitForCompletion);
          installerJob.run(monitor);
        }
      };

      IStatus status = job.run(MonitorUtils.subMonitor(monitor, 80));
      int severity = status.getSeverity();
      if (severity != IStatus.OK && severity != IStatus.INFO)
      {
        throw new CoreException(status);
      }
    }
  }

} // BuckminsterImportTaskImpl
