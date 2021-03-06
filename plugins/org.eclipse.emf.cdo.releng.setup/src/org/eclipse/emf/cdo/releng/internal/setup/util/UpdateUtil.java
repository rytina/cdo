/*
 * Copyright (c) 2013, 2014 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.releng.internal.setup.util;

import org.eclipse.emf.cdo.releng.internal.setup.Activator;
import org.eclipse.emf.cdo.releng.internal.setup.ui.AbstractSetupDialog;
import org.eclipse.emf.cdo.releng.internal.setup.ui.InstallerDialog;
import org.eclipse.emf.cdo.releng.setup.SetupConstants;
import org.eclipse.emf.cdo.releng.setup.util.ServiceUtil;
import org.eclipse.emf.cdo.releng.setup.util.UIUtil;

import org.eclipse.net4j.util.collection.Pair;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.operations.ProvisioningJob;
import org.eclipse.equinox.p2.operations.ProvisioningSession;
import org.eclipse.equinox.p2.operations.UpdateOperation;
import org.eclipse.equinox.p2.planner.IProfileChangeRequest;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Eike Stepper
 */
public final class UpdateUtil extends Plugin
{
  public static final String SELF_HOSTING_PROFILE = "SelfHostingProfile";

  public static final String PROP_ROOT_LIST = "setup.root.list";

  public static final String PROP_REPO_LIST = "setup.repo.list";

  public static final IStatus UPDATE_FOUND_STATUS = new Status(IStatus.OK, Activator.PLUGIN_ID, "Updates found");

  public static final String PRODUCT_ID = "org.eclipse.emf.cdo.releng.setup.installer.product";

  private static final String[] PRODUCT_PREFIXES = { "org.eclipse.emf.cdo.releng", "org.eclipse.net4j" };

  private UpdateUtil()
  {
  }

  public static void recordRepos(IProfile profile, IProfileChangeRequest request, List<java.net.URI> uris)
  {
    Set<URI> set = new LinkedHashSet<URI>(uris);
    String list = profile.getProperty(PROP_REPO_LIST);

    if (list != null)
    {
      StringTokenizer tokenizer = new StringTokenizer(list, ",");
      while (tokenizer.hasMoreTokens())
      {
        String uri = tokenizer.nextToken();

        try
        {
          set.add(new URI(uri));
        }
        catch (URISyntaxException ex)
        {
          Activator.log(ex);
        }
      }
    }

    String repos = buildRepoList(set);
    request.setProfileProperty(PROP_REPO_LIST, repos);
  }

  public static void recordRepos(Map<String, String> properties, List<java.net.URI> uris)
  {
    String repos = buildRepoList(uris);
    properties.put(PROP_REPO_LIST, repos);
  }

  private static String buildRepoList(Collection<URI> uris)
  {
    StringBuilder builder = new StringBuilder();
    for (URI uri : uris)
    {
      if (builder.length() != 0)
      {
        builder.append(',');
      }

      builder.append(uri);
    }

    return builder.toString();
  }

  public static boolean update(final Shell shell, final String[] iuPrefixes, final boolean needsEarlyConfirmation,
      final boolean async, final Runnable postInstall, final Runnable restartHandler)
  {
    final Display display = shell.getDisplay();

    if (needsEarlyConfirmation)
    {
      final AtomicBoolean result = new AtomicBoolean();
      display.syncExec(new Runnable()
      {
        public void run()
        {
          boolean confirmation = MessageDialog
              .openQuestion(
                  null,
                  AbstractSetupDialog.SHELL_TEXT,
                  "Updates are needed to process the setup configuration, and then a restart is required. "
                      + "It might be possible for this older version of the tool to process the configuration, but that's not recommended.\n\n"
                      + "Do you wish to update and restart?");
          result.set(confirmation);
        }
      });

      if (!result.get())
      {
        return false;
      }
    }

    try
    {
      final IRunnableWithProgress runnable = new IRunnableWithProgress()
      {
        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
        {
          IProvisioningAgent agent = ServiceUtil.getService(IProvisioningAgent.class);

          try
          {
            SubMonitor sub = SubMonitor.convert(monitor, needsEarlyConfirmation ? "Updating..."
                : "Checking for updates...", 1000);

            IStatus updateStatus = checkForUpdates(agent, iuPrefixes, false, postInstall, sub);

            if (updateStatus.getCode() == UpdateOperation.STATUS_NOTHING_TO_UPDATE)
            {
              UIUtil.exec(display, async, new Runnable()
              {
                public void run()
                {
                  MessageDialog.openInformation(null, AbstractSetupDialog.SHELL_TEXT, "No updates were found.");
                }
              });
            }
            else if (updateStatus.getSeverity() != IStatus.ERROR)
            {
              UIUtil.exec(display, async, new Runnable()
              {
                public void run()
                {
                  if (!needsEarlyConfirmation)
                  {
                    MessageDialog.openInformation(null, AbstractSetupDialog.SHELL_TEXT,
                        "Updates were installed. Press OK to restart.");
                  }

                  if (restartHandler != null)
                  {
                    restartHandler.run();
                  }
                }
              });
            }
            else
            {
              throw new InvocationTargetException(new CoreException(updateStatus));
            }
          }
          finally
          {
            ServiceUtil.ungetService(agent);
            monitor.done();
          }
        }
      };

      UIUtil.exec(display, async, new Runnable()
      {
        public void run()
        {
          try
          {
            UIUtil.runInProgressDialog(shell, runnable);
          }
          catch (InvocationTargetException ex)
          {
            UIUtil.handleException(ex.getCause());
          }
          catch (InterruptedException ex)
          {
            // Do nothing
          }
        }
      });
    }
    catch (Throwable ex)
    {
      UIUtil.handleException(ex);
    }

    return true;
  }

  public static IStatus checkForUpdates(IProvisioningAgent agent, String[] iuPrefixes, boolean resolveOnly,
      Runnable postInstall, SubMonitor sub)
  {
    try
    {
      try
      {
        addRepositories(agent, true, sub);
      }
      catch (CoreException ex)
      {
        return ex.getStatus();
      }

      if (iuPrefixes == null)
      {
        iuPrefixes = UpdateUtil.PRODUCT_PREFIXES;
      }

      ProvisioningSession session = new ProvisioningSession(agent);
      List<IInstallableUnit> ius = getInstalledUnits(session, iuPrefixes).getElement2();

      UpdateOperation operation = new UpdateOperation(session, ius);
      IStatus status = operation.resolveModal(sub.newChild(300));
      if (status.getCode() == UpdateOperation.STATUS_NOTHING_TO_UPDATE)
      {
        return status;
      }

      if (status.getSeverity() == IStatus.CANCEL)
      {
        throw new OperationCanceledException();
      }

      if (status.getSeverity() != IStatus.ERROR)
      {
        ProvisioningJob job = operation.getProvisioningJob(null);
        if (job == null)
        {
          String resolutionDetails = operation.getResolutionDetails();
          throw new IllegalStateException(resolutionDetails);
        }

        if (resolveOnly)
        {
          return UPDATE_FOUND_STATUS;
        }

        sub.setTaskName("Installing updates...");

        try
        {
          addRepositories(agent, false, sub);
        }
        catch (CoreException ex)
        {
          return ex.getStatus();
        }

        status = job.runModal(sub.newChild(300));
        if (status.getSeverity() == IStatus.CANCEL)
        {
          throw new OperationCanceledException();
        }
      }

      return status;
    }
    finally
    {
      if (!resolveOnly && postInstall != null)
      {
        postInstall.run();
      }
    }
  }

  public static Pair<String, List<IInstallableUnit>> getInstalledUnits(ProvisioningSession session,
      String... iuPrefixes)
  {
    IProvisioningAgent agent = session.getProvisioningAgent();
    IProfileRegistry profileRegistry = (IProfileRegistry)agent.getService(IProfileRegistry.class.getName());
    IProfile profile = profileRegistry.getProfile(IProfileRegistry.SELF);
    if (profile == null)
    {
      List<IInstallableUnit> none = Collections.emptyList();
      return Pair.create(SELF_HOSTING_PROFILE, none);
    }

    IQueryResult<IInstallableUnit> queryResult = profile.query(QueryUtil.createIUAnyQuery(), null);

    List<IInstallableUnit> ius = new ArrayList<IInstallableUnit>();
    for (IInstallableUnit installableUnit : queryResult)
    {
      String id = installableUnit.getId();

      if (iuPrefixes.length == 0)
      {
        ius.add(installableUnit);
      }
      else
      {
        if (hasPrefix(id, iuPrefixes))
        {
          ius.add(installableUnit);
        }
      }
    }

    return Pair.create(profile.getProfileId(), ius);
  }

  public static boolean hasPrefix(String id)
  {
    return hasPrefix(id, PRODUCT_PREFIXES);
  }

  private static boolean hasPrefix(String id, String[] iuPrefixes)
  {
    for (int i = 0; i < iuPrefixes.length; i++)
    {
      String iuPrefix = iuPrefixes[i];
      if (id.startsWith(iuPrefix))
      {
        return true;
      }
    }

    return false;
  }

  private static void addRepositories(IProvisioningAgent agent, boolean metadata, SubMonitor sub) throws CoreException
  {
    addRepository(agent, InstallerDialog.TRAIN_URL, metadata, sub.newChild(200));
    addRepository(agent, SetupConstants.RELENG_URL, metadata, sub.newChild(200));
  }

  private static void addRepository(IProvisioningAgent agent, String location, boolean metadata,
      IProgressMonitor monitor) throws CoreException
  {
    SubMonitor sub = SubMonitor.convert(monitor, "Loading " + location, 500);

    try
    {
      URI uri = new URI(location);

      if (metadata)
      {
        addMetadataRepository(agent, uri, sub);
      }
      else
      {
        addArtifactRepository(agent, uri, sub);
      }
    }
    catch (URISyntaxException ex)
    {
      throw new IllegalArgumentException(ex);
    }
  }

  private static void addMetadataRepository(IProvisioningAgent agent, URI location, IProgressMonitor monitor)
      throws CoreException
  {
    IMetadataRepositoryManager manager = (IMetadataRepositoryManager)agent
        .getService(IMetadataRepositoryManager.SERVICE_NAME);
    if (manager == null)
    {
      throw new IllegalStateException("No metadata repository manager found");
    }

    manager.loadRepository(location, monitor);
  }

  private static void addArtifactRepository(IProvisioningAgent agent, URI location, IProgressMonitor monitor)
      throws CoreException
  {
    IArtifactRepositoryManager manager = (IArtifactRepositoryManager)agent
        .getService(IArtifactRepositoryManager.SERVICE_NAME);
    if (manager == null)
    {
      throw new IllegalStateException("No artifact repository manager found");
    }

    manager.loadRepository(location, monitor);
  }

  /**
   * @author Eike Stepper
   */
  public static final class UpdatingException extends Exception
  {
    private static final long serialVersionUID = 1L;
  }
}
