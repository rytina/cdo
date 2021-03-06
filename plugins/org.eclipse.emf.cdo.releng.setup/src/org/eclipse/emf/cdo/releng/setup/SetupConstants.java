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
package org.eclipse.emf.cdo.releng.setup;

import org.eclipse.emf.cdo.releng.internal.setup.Activator;
import org.eclipse.emf.cdo.releng.setup.util.SetupUtil;

import org.eclipse.emf.common.util.URI;

import java.io.File;

/**
 * @author Eike Stepper
 */
public interface SetupConstants
{
  public static final String KEY_STATE_DIR = "setup.state.dir";

  public static final String KEY_INSTALL_DIR = "setup.install.dir";

  public static final String KEY_P2_AGENT_DIR = "setup.p2.agent.dir";

  public static final String KEY_P2_POOL_DIR = "setup.p2.pool.dir";

  public static final String KEY_PROJECT_DIR = "setup.project.dir";

  public static final String KEY_BRANCH_DIR = "setup.branch.dir";

  public static final String KEY_ECLIPSE_DIR = "setup.eclipse.dir";

  public static final String KEY_WS_DIR = "setup.ws.dir";

  public static final String KEY_PROJECT_NAME = "setup.project.name";

  public static final String KEY_PROJECT_LABEL = "setup.project.label";

  public static final String KEY_BRANCH_NAME = "setup.branch.name";

  public static final String KEY_BRANCH_LABEL = "setup.branch.label";

  public static final String KEY_OS = "os";

  public static final String KEY_ARCH = "os.arch";

  public static final String KEY_WS = "ws";

  public static final String PROP_SETUP_IDE = "org.eclipse.emf.cdo.releng.setup.ide";

  public static final String PROP_SETUP_SKIP = "org.eclipse.emf.cdo.releng.setup.skip";

  public static final String PROP_P2_TASK_SKIP = "org.eclipse.emf.cdo.releng.setup.skip.p2";

  public static final String PROP_META_INDEX = "meta.index";

  public static final String PROP_SETUP_URI = "setup.uri";

  public static final String PROP_EXAMPLE_URI = "example.uri";

  public static final String PROP_RELENG_URL = "releng.url";

  public static final String PROP_SETUP_BRANCH_URI = "setup.branch.uri";

  public static final String RELENG_URL = SetupUtil.getProperty(PROP_RELENG_URL, Activator.CDO_URL).replace('\\', '/');

  public static final boolean META_INDEX = "true".equalsIgnoreCase(SetupUtil.getProperty(PROP_META_INDEX, "false"));

  public static final boolean SETUP_IDE = "true".equalsIgnoreCase(SetupUtil.getProperty(PROP_SETUP_IDE, "false"));

  public static final boolean SETUP_SKIP = "true".equalsIgnoreCase(SetupUtil.getProperty(PROP_SETUP_SKIP, "false"));

  public static final String PREF_SKIP_STARTUP_TASKS = "skip.startup.tasks";

  public static final String PREF_LOG_UNNEEDED_TASKS = "log.unneeded.tasks";

  public static final String USER_HOME = SetupUtil.getProperty("user.home", ".");

  public static final String PREFERENCES_NAME = "setup-eclipse.xmi";

  public static final File PREFERENCES_FOLDER = new File(USER_HOME, ".eclipse/org.eclipse.emf.cdo.releng.setup");

  public static final File PREFERENCES_FILE = new File(PREFERENCES_FOLDER, PREFERENCES_NAME);

  public static final URI PREFERENCES_URI = URI.createFileURI(PREFERENCES_FILE.getAbsolutePath());

  public static final String CACHE_NAME = "cache";

  public static final File CACHE_FOLDER = new File(PREFERENCES_FOLDER, CACHE_NAME);

  public static final File GIT_IGNORE_FILE = new File(PREFERENCES_FOLDER, ".gitignore");
}
