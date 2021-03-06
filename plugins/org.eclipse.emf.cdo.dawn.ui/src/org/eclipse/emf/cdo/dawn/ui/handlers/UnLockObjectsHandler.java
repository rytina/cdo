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
package org.eclipse.emf.cdo.dawn.ui.handlers;

import org.eclipse.emf.cdo.dawn.editors.IDawnEditor;
import org.eclipse.emf.cdo.dawn.helper.DawnEditorHelper;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * @author Martin Fluegge
 * @since 2.0
 */
public class UnLockObjectsHandler extends SelectionHandler
{
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    ((IDawnEditor)DawnEditorHelper.getActiveEditor()).getDawnEditorSupport().unlockObjects(getSelectedObjects(event));

    return null;
  }
}
