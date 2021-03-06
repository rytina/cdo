/*
 * Copyright (c) 2011, 2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Martin Fluegge - initial API and implementation
 */
package org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.emf.util;

import org.eclipse.emf.cdo.dawn.codegen.util.DawnWorkflowUtil;

import java.net.URL;

/**
 * @author Martin Fluegge
 */
public class DawnEMFWorkflowUtil implements DawnWorkflowUtil
{
  public URL getWorkFlow()
  {
    return this.getClass().getClassLoader().getResource("/workflow/emfFragmentGenerator.mwe");
  }
}
