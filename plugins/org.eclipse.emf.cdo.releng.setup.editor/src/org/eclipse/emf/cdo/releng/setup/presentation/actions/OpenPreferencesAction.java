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
package org.eclipse.emf.cdo.releng.setup.presentation.actions;

import org.eclipse.emf.cdo.releng.setup.presentation.SetupEditor;
import org.eclipse.emf.cdo.releng.setup.presentation.SetupEditorPlugin;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * @author Eike Stepper
 */
public class OpenPreferencesAction implements IWorkbenchWindowActionDelegate
{
  private IWorkbenchWindow window;

  public OpenPreferencesAction()
  {
  }

  public void init(IWorkbenchWindow window)
  {
    this.window = window;
  }

  public void selectionChanged(IAction action, ISelection selection)
  {
  }

  public void dispose()
  {
  }

  public void run(IAction action)
  {
    try
    {
      SetupEditor.openPreferences(window.getActivePage());
    }
    catch (Exception ex)
    {
      SetupEditorPlugin.INSTANCE.log(ex);
    }
  }
}
