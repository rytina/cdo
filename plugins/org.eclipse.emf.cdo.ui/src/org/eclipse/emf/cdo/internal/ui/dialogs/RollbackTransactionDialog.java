/*
 * Copyright (c) 2007-2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *    Victor Roldan Betancort - maintenance
 */
package org.eclipse.emf.cdo.internal.ui.dialogs;

import org.eclipse.emf.cdo.internal.ui.messages.Messages;
import org.eclipse.emf.cdo.transaction.CDOTransaction;

import org.eclipse.net4j.util.ui.UIUtil;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;

/**
 * @author Eike Stepper
 */
public class RollbackTransactionDialog extends TitleAreaDialog
{
  private IWorkbenchPage page;

  private String title;

  private String description;

  private CDOTransaction transaction;

  public RollbackTransactionDialog(IWorkbenchPage page, String title, String description, CDOTransaction transaction)
  {
    super(new Shell(page.getWorkbenchWindow().getShell()));
    this.page = page;
    this.title = title;
    this.description = description;
    this.transaction = transaction;
    setShellStyle(getShellStyle() | SWT.APPLICATION_MODAL | SWT.MAX | SWT.TITLE | SWT.RESIZE);
  }

  public IWorkbenchPage getPage()
  {
    return page;
  }

  @Override
  protected void configureShell(Shell newShell)
  {
    super.configureShell(newShell);
    newShell.setText(title);
  }

  @Override
  protected Control createDialogArea(Composite parent)
  {
    Composite composite = new Composite((Composite)super.createDialogArea(parent), SWT.NONE);
    composite.setLayoutData(UIUtil.createGridData());
    composite.setLayout(new GridLayout(1, false));

    setTitle(description);
    setTitleImage(getShell().getDisplay().getSystemImage(SWT.ICON_QUESTION));

    Label label = new Label(composite, SWT.NONE);
    label.setText(formatMessage());
    label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));

    return composite;
  }

  protected String formatMessage()
  {
    StringBuilder builder = new StringBuilder();
    builder.append(Messages.getString("RollbackTransactionDialog.0")); //$NON-NLS-1$

    append(builder, transaction.getNewObjects().size(),
        Messages.getString("RollbackTransactionDialog.4"), Messages.getString("RollbackTransactionDialog.5")); //$NON-NLS-1$ //$NON-NLS-2$
    append(builder, transaction.getDirtyObjects().size(),
        Messages.getString("RollbackTransactionDialog.6"), Messages.getString("RollbackTransactionDialog.7")); //$NON-NLS-1$ //$NON-NLS-2$
    append(builder, transaction.getDetachedObjects().size(),
        Messages.getString("RollbackTransactionDialog.8"), Messages.getString("RollbackTransactionDialog.9")); //$NON-NLS-1$ //$NON-NLS-2$

    builder.append("\n\n"); //$NON-NLS-1$
    builder.append(Messages.getString("RollbackTransactionDialog.11")); //$NON-NLS-1$
    return builder.toString();
  }

  private void append(StringBuilder builder, int count, String labelSingular, String labelPlural)
  {
    if (count > 0)
    {
      builder.append("\n- "); //$NON-NLS-1$
      builder.append(count);
      builder.append(" "); //$NON-NLS-1$
      if (count > 1)
      {
        builder.append(labelPlural);
      }
      else
      {
        builder.append(labelSingular);
      }
    }
  }
}
