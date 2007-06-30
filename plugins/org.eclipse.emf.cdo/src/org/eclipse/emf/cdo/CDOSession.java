/***************************************************************************
 * Copyright (c) 2004-2007 Eike Stepper, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 **************************************************************************/
package org.eclipse.emf.cdo;

import org.eclipse.net4j.IChannel;
import org.eclipse.net4j.util.container.IContainer;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * @author Eike Stepper
 */
public interface CDOSession extends IContainer<CDOView>
{
  public int getSessionID();

  public IChannel getChannel();

  public boolean isOpen();

  public void close();

  public String getRepositoryName();

  public String getRepositoryUUID();

  public CDORevisionManager getRevisionManager();

  public CDOView openView(ResourceSet resourceSet, long timeStamp);

  public CDOView openView(ResourceSet resourceSet, boolean readOnly);

  public CDOView openView(ResourceSet resourceSet);

  public CDOView[] getViews();
}
