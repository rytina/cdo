/*
 * Copyright (c) 2004-2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.releng.internal.setup;

import org.eclipse.emf.cdo.releng.internal.setup.ui.ProgressLogDialog;
import org.eclipse.emf.cdo.releng.setup.Branch;
import org.eclipse.emf.cdo.releng.setup.Project;
import org.eclipse.emf.cdo.releng.setup.Setup;
import org.eclipse.emf.cdo.releng.setup.SetupTask;
import org.eclipse.emf.cdo.releng.setup.SetupTaskContext;
import org.eclipse.emf.cdo.releng.setup.Trigger;
import org.eclipse.emf.cdo.releng.setup.util.OS;
import org.eclipse.emf.cdo.releng.setup.util.log.ProgressLog;
import org.eclipse.emf.cdo.releng.setup.util.log.ProgressLogRunnable;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Eike Stepper
 */
public class SetupTaskPerformer extends HashMap<Object, Object> implements SetupTaskContext
{
  private static final String RELENG_URL = System.getProperty("releng.url",
      "http://download.eclipse.org/modeling/emf/cdo/updates/integration").replace('\\', '/');

  private static final long serialVersionUID = 1L;

  private static ProgressLog progress;

  private Trigger trigger;

  private File branchDir;

  private Setup setup;

  private transient boolean restartNeeded;

  private List<String> logMessageBuffer;

  public SetupTaskPerformer(File branchDir)
  {
    trigger = Trigger.BOOTSTRAP;

    this.branchDir = branchDir;

    initialize();
  }

  public SetupTaskPerformer(boolean manual) throws Exception
  {
    trigger = manual ? Trigger.MANUAL : Trigger.STARTUP;

    IPath branchDirPath = ResourcesPlugin.getWorkspace().getRoot().getLocation().removeLastSegments(1);
    branchDir = new File(branchDirPath.toOSString()).getCanonicalFile();

    initialize();
  }

  private void initialize()
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());

    URI uri = URI.createFileURI(branchDir.toString() + "/setup.xmi");
    Resource resource = resourceSet.getResource(uri, true);

    setup = (Setup)resource.getContents().get(0);

    Branch branch = setup.getBranch();
    String branchName = branch.getName();

    Project project = branch.getProject();
    String projectName = project.getName();

    put("setup.git.prefix", setup.getPreferences().getGitPrefix());
    put("setup.install.dir", getInstallDir());
    put("setup.project.dir", getProjectDir());
    put("setup.branch.dir", getBranchDir());
    put("setup.eclipse.dir", getEclipseDir());
    put("setup.tp.dir", getTargetPlatformDir());
    put("setup.ws.dir", getWorkspaceDir());
    put("setup.project.name", projectName);
    put("setup.branch.name", branchName);
    put("releng.url", RELENG_URL);
  }

  public void dispose()
  {
    setup = null;
    branchDir = null;
  }

  public void log(String line)
  {
    if (progress != null)
    {
      if (logMessageBuffer != null)
      {
        for (String value : logMessageBuffer)
        {
          progress.log(value);
        }

        logMessageBuffer = null;
      }

      progress.log(line);
    }
    else
    {
      if (logMessageBuffer == null)
      {
        logMessageBuffer = new ArrayList<String>();
      }

      logMessageBuffer.add(line);
    }
  }

  public boolean isCancelled()
  {
    if (progress != null)
    {
      return progress.isCancelled();
    }

    return false;
  }

  public Trigger getTrigger()
  {
    return trigger;
  }

  public boolean isRestartNeeded()
  {
    return restartNeeded;
  }

  public void setRestartNeeded()
  {
    restartNeeded = true;
  }

  private static final Pattern PATTERN = Pattern.compile("\\$\\{([^${}|]+)(\\|([^}]+))?}");

  protected String lookup(String key)
  {
    Object object = get(key);
    if (object != null)
    {
      return object.toString();
    }

    return System.getProperty(key, key);
  }

  interface StringFilter
  {
    public String filter(String value);
  }

  private static final Map<String, StringFilter> FILTERS = new HashMap<String, StringFilter>();

  static
  {
    FILTERS.put("uri", new StringFilter()
    {
      public String filter(String value)
      {
        return URI.createFileURI(value).toString();
      }
    });
    FILTERS.put("upper", new StringFilter()
    {
      public String filter(String value)
      {
        return value.toUpperCase();
      }
    });
    FILTERS.put("lower", new StringFilter()
    {
      public String filter(String value)
      {
        return value.toLowerCase();
      }
    });
  }

  protected String filter(String value, String filterName)
  {
    StringFilter filter = FILTERS.get(filterName);
    if (filter != null)
    {
      return filter.filter(value);
    }

    return value;
  }

  public String expandString(String string)
  {
    StringBuilder result = new StringBuilder();
    int previous = 0;
    for (Matcher matcher = PATTERN.matcher(string); matcher.find();)
    {
      result.append(string.substring(previous, matcher.start()));
      String key = matcher.group(1);
      String value = lookup(key);
      String filters = matcher.group(3);
      if (filters != null)
      {
        for (String filterName : filters.split("\\|"))
        {
          value = filter(value, filterName);
        }
      }
      result.append(value);
      previous = matcher.end();
    }
    result.append(string.substring(previous));
    return result.toString();
  }

  public OS getOS()
  {
    return OS.INSTANCE;
  }

  public String getP2ProfileName()
  {
    Branch branch = setup.getBranch();
    Project project = branch.getProject();

    String profileName = project.getName() + "_" + branch.getName();
    profileName = profileName.replace('.', '_');
    profileName = profileName.replace('-', '_');
    profileName = profileName.replace('/', '_');
    profileName = profileName.replace('\\', '_');
    return profileName;
  }

  public File getP2ProfileDir()
  {
    return new File(getP2AgentDir(), "org.eclipse.equinox.p2.engine/profileRegistry/" + getP2ProfileName() + ".profile");
  }

  public File getP2AgentDir()
  {
    return new File(getP2PoolDir(), "p2");
  }

  public File getP2PoolDir()
  {
    return new File(getInstallDir(), ".p2pool-ide");
  }

  public File getInstallDir()
  {
    return getProjectDir().getParentFile();
  }

  public File getProjectDir()
  {
    return branchDir.getParentFile();
  }

  public File getBranchDir()
  {
    return branchDir;
  }

  public File getEclipseDir()
  {
    return new File(branchDir, "eclipse");
  }

  // TODO Is this Bucky-specific?
  public File getTargetPlatformDir()
  {
    return new File(branchDir, "tp");
  }

  public File getWorkspaceDir()
  {
    return new File(branchDir, "ws");
  }

  public Setup getSetup()
  {
    return setup;
  }

  public void perform() throws Exception
  {
    EList<SetupTask> setupTasks = setup.getSetupTasks(true, trigger);
    if (setupTasks.isEmpty())
    {
      return;
    }

    Map<SetupTask, SetupTask> substitutions = getSubstitutions(setupTasks);
    setup = copySetup(setupTasks, substitutions);

    perform(setupTasks);
  }

  private void perform(EList<SetupTask> setupTasks) throws Exception
  {
    final EList<SetupTask> neededTasks = getNeededTasks(setupTasks);
    if (neededTasks.isEmpty())
    {
      return;
    }

    if (Activator.SETUP_IDE && trigger != Trigger.MANUAL)
    {
      File logFile = new File(getInstallDir(), "setup.log");
      IWorkbenchWindow window = PlatformUI.getWorkbench().getWorkbenchWindows()[0];
      final Shell shell = window.getShell();
      ProgressLogDialog.run(shell, logFile, "Setting up IDE", new ProgressLogRunnable()
      {
        public boolean run(ProgressLog log) throws Exception
        {
          doPerform(neededTasks);
          return isRestartNeeded();
        }
      });
    }
    else
    {
      doPerform(neededTasks);
    }
  }

  private void doPerform(EList<SetupTask> neededTasks) throws Exception
  {
    Branch branch = setup.getBranch();
    log("Setting up " + branch.getProject().getName() + " " + branch.getName());

    for (SetupTask neededTask : neededTasks)
    {
      neededTask.perform(this);
      neededTask.dispose();
    }
  }

  private Map<SetupTask, SetupTask> getSubstitutions(EList<SetupTask> setupTasks)
  {
    SetupTaskComparator.sort(setupTasks);

    Map<Object, SetupTask> overrides = new HashMap<Object, SetupTask>();
    Map<SetupTask, SetupTask> substitutions = new HashMap<SetupTask, SetupTask>();

    for (SetupTask setupTask : setupTasks)
    {
      Object overrideToken = setupTask.getOverrideToken();
      SetupTask overriddenTask = overrides.put(overrideToken, setupTask);
      if (overriddenTask != null)
      {
        substitutions.put(overriddenTask, setupTask);
      }
    }

    // Shorten the paths through the substitutions map
    for (Map.Entry<SetupTask, SetupTask> entry : substitutions.entrySet())
    {
      SetupTask task = entry.getValue();

      for (;;)
      {
        SetupTask overridingTask = substitutions.get(task);
        if (overridingTask == null)
        {
          break;
        }

        entry.setValue(overridingTask);
      }
    }

    return substitutions;
  }

  private Setup copySetup(EList<SetupTask> setupTasks, Map<SetupTask, SetupTask> substitutions)
  {
    Set<EObject> roots = new LinkedHashSet<EObject>();
    roots.add(setup);

    for (EObject eObject : setup.eCrossReferences())
    {
      EObject rootContainer = EcoreUtil.getRootContainer(eObject);
      roots.add(rootContainer);
    }

    EcoreUtil.Copier copier = new EcoreUtil.Copier();
    Setup setup = (Setup)copier.copyAll(roots).iterator().next();

    for (Map.Entry<SetupTask, SetupTask> entry : substitutions.entrySet())
    {
      SetupTask overriddenTask = entry.getKey();
      SetupTask overridingTask = entry.getValue();

      EObject copy = copier.get(overridingTask);
      copier.put(overriddenTask, copy);
    }

    copier.copyReferences();

    for (ListIterator<SetupTask> it = setupTasks.listIterator(); it.hasNext();)
    {
      SetupTask setupTask = it.next();
      if (substitutions.containsKey(setupTask))
      {
        it.remove();
      }
      else
      {
        SetupTask copy = (SetupTask)copier.get(setupTask);
        it.set(copy);
      }
    }

    SetupTaskComparator.sort(setupTasks);
    return setup;
  }

  private EList<SetupTask> getNeededTasks(EList<SetupTask> setupTasks) throws Exception
  {
    EList<SetupTask> result = new BasicEList<SetupTask>();

    for (Iterator<SetupTask> it = setupTasks.iterator(); it.hasNext();)
    {
      SetupTask setupTask = it.next();
      if (setupTask.isNeeded(this))
      {
        result.add(setupTask);
      }
      else
      {
        setupTask.dispose();
      }
    }

    return result;
  }

  public static ProgressLog getProgress()
  {
    return progress;
  }

  public static void setProgress(ProgressLog progress)
  {
    SetupTaskPerformer.progress = progress;
  }

  /**
   * @author Eike Stepper
   */
  public static class SetupTaskComparator implements Comparator<SetupTask>
  {
    public static void sort(EList<SetupTask> setupTasks)
    {
      Collections.sort(setupTasks, new SetupTaskComparator());
    }

    public int compare(SetupTask t1, SetupTask t2)
    {
      boolean t1RequiresT2 = t1.requires(t2);
      boolean t2RequiresT1 = t2.requires(t1);
      if (t1RequiresT2 && t2RequiresT1)
      {
        throw new IllegalStateException("Requirements cycle detected between " + t1 + " and " + t2);
      }

      if (t1RequiresT2 && !t2RequiresT1)
      {
        return 1;
      }

      if (!t1RequiresT2 && t2RequiresT1)
      {
        return -1;
      }

      int scope1 = t1.getScope().getValue();
      int scope2 = t2.getScope().getValue();
      if (scope1 < scope2)
      {
        return -1;
      }

      if (scope1 > scope2)
      {
        return 1;
      }

      String uri1 = EcoreUtil.getURI(t1).toString();
      String uri2 = EcoreUtil.getURI(t2).toString();
      return uri1.compareTo(uri2); // Arbitrary but symmetric within one ResourceSet
    }
  }
}