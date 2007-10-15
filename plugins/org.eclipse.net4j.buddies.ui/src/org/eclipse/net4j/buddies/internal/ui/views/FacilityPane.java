/***************************************************************************
 * Copyright (c) 2004 - 2007 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 **************************************************************************/
package org.eclipse.net4j.buddies.internal.ui.views;

import org.eclipse.net4j.util.ui.UIUtil;

import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Layout;

/**
 * @author Eike Stepper
 */
public abstract class FacilityPane extends Composite
{
  private CoolBarManager coolBarManager;

  private Control control;

  public FacilityPane(Composite parent, int style)
  {
    super(parent, style);
    setLayout(new FacilityPaneLayout());

    ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT | SWT.RIGHT | SWT.WRAP);
    fillCoolBar(toolBarManager);

    coolBarManager = new CoolBarManager(SWT.FLAT | SWT.RIGHT | SWT.WRAP);
    coolBarManager.add(toolBarManager);
    coolBarManager.setLockLayout(true);
    coolBarManager.createControl(this);
    coolBarManager.update(true);

    Composite composite = new Composite(this, SWT.NONE);
    composite.setLayout(UIUtil.createGridLayout(1));

    control = createUI(composite);
    control.setLayoutData(UIUtil.createGridData());
  }

  @Override
  public void dispose()
  {
    coolBarManager.dispose();
    super.dispose();
  }

  public ICoolBarManager getCoolBarManager()
  {
    return coolBarManager;
  }

  public Control getControl()
  {
    return control;
  }

  public void hidden(FacilityPane newPane)
  {
  }

  public void showed(FacilityPane oldPane)
  {
  }

  protected abstract Control createUI(Composite parent);

  protected CoolBar getCoolBarControl()
  {
    if (coolBarManager != null)
    {
      return coolBarManager.getControl();
    }

    return null;
  }

  protected void fillCoolBar(IContributionManager manager)
  {
  }

  /**
   * @author Eike Stepper
   * @see org.eclipse.jface.window.ApplicationWindow.ApplicationWindowLayout
   */
  public class FacilityPaneLayout extends Layout
  {
    static final int BAR_SIZE = 23;

    @Override
    protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache)
    {
      if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT)
      {
        return new Point(wHint, hHint);
      }

      Point result = new Point(0, 0);
      Control[] ws = composite.getChildren();
      for (int i = 0; i < ws.length; i++)
      {
        Control w = ws[i];
        boolean hide = false;
        if (w == getCoolBarControl())
        {
          if (!coolBarChildrenExist())
          {
            hide = true;
            result.y += BAR_SIZE;
          }
        }
        else if (i > 0)
        {
          hide = false;
        }

        if (!hide)
        {
          Point e = w.computeSize(wHint, hHint, flushCache);
          result.x = Math.max(result.x, e.x);
          result.y += e.y;
        }
      }

      if (wHint != SWT.DEFAULT)
      {
        result.x = wHint;
      }

      if (hHint != SWT.DEFAULT)
      {
        result.y = hHint;
      }

      return result;
    }

    @Override
    protected void layout(Composite composite, boolean flushCache)
    {
      Rectangle clientArea = composite.getClientArea();
      Control[] ws = composite.getChildren();
      for (int i = 0; i < ws.length; i++)
      {
        Control w = ws[i];
        if (w == getCoolBarControl())
        {
          if (coolBarChildrenExist())
          {
            Point e = w.computeSize(clientArea.width, SWT.DEFAULT, flushCache);
            w.setBounds(clientArea.x, clientArea.y, clientArea.width, e.y);
            clientArea.y += e.y;
            clientArea.height -= e.y;
          }
        }
        else
        {
          w.setBounds(clientArea.x, clientArea.y, clientArea.width, clientArea.height);
        }
      }
    }

    protected boolean coolBarChildrenExist()
    {
      CoolBar coolBarControl = getCoolBarControl();
      if (coolBarControl != null)
      {
        if (coolBarControl.getItemCount() == 0)
        {
          System.out.println("FALSE");
        }

        return coolBarControl.getItemCount() > 0;
      }

      return false;
    }
  }
}
