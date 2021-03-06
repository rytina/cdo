/*
 * Copyright (c) 2011, 2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.internal.explorer;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * @author Eike Stepper
 */
public class CDOExplorer implements IApplication
{
  public Object start(IApplicationContext context)
  {
    Display display = PlatformUI.createDisplay();

    try
    {
      int returnCode = PlatformUI.createAndRunWorkbench(display, new CDOExplorerWorkbenchAdvisor());
      if (returnCode == PlatformUI.RETURN_RESTART)
      {
        return IApplication.EXIT_RESTART;
      }

      return IApplication.EXIT_OK;
    }
    finally
    {
      display.dispose();
    }
  }

  public void stop()
  {
    if (!PlatformUI.isWorkbenchRunning())
    {
      return;
    }

    final IWorkbench workbench = PlatformUI.getWorkbench();
    final Display display = workbench.getDisplay();
    display.syncExec(new Runnable()
    {
      public void run()
      {
        if (!display.isDisposed())
        {
          workbench.close();
        }
      }
    });
  }
}
