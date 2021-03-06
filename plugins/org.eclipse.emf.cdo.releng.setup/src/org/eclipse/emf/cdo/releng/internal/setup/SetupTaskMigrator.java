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
package org.eclipse.emf.cdo.releng.internal.setup;

/**
 * @author Eike Stepper
 */
public interface SetupTaskMigrator
{
  // public void migrate();

  public static final SetupTaskMigrator[] MIGRATORS = {

      // v1
      null,

      // v2: Add P2Task.isMergeDisabled(), Project.getRestrictions(), Branch.getRestrictions()
      null,

      // v3: Add "setup.p2.pool.dir" and "setup.p2.pool.tp.dir" variables
      null,

      // v4: Move setup-eclipse.xmi to ${user.home}/.eclipse/org.eclipse.emf.cdo.releng.setup
      null,

      // v5: Add MylynQueriesTask and MylynBuildsTask
      null,

      // v6: Add TargletTask and TargletImportTask
      null,

      // v7: Add AutomaticSourceLocator.predicates
      null,

      // v8: Add BasicMaterializationTask.bundlePool. Remove Preferences.bundlePoolFolder and
      // Preferences.bundlePoolFolderTP
      null,

  };

  public static final int TOOL_VERSION = MIGRATORS.length;
}
