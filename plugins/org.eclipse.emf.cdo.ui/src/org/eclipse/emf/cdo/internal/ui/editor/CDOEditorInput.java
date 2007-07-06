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
package org.eclipse.emf.cdo.internal.ui.editor;

import org.eclipse.emf.cdo.CDOSession;
import org.eclipse.emf.cdo.CDOView;
import org.eclipse.emf.cdo.internal.ui.bundle.SharedIcons;

import org.eclipse.net4j.IConnector;

import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import java.util.Date;

/**
 * @author Eike Stepper
 */
public class CDOEditorInput extends PlatformObject implements IEditorInput
{
  private CDOView view;

  private String resourcePath;

  public CDOEditorInput(CDOView view, String resourcePath)
  {
    this.view = view;
    this.resourcePath = resourcePath;
  }

  public CDOView getView()
  {
    return view;
  }

  public String getResourcePath()
  {
    return resourcePath;
  }

  public boolean exists()
  {
    return true;
  }

  public ImageDescriptor getImageDescriptor()
  {
    if (view.isHistorical())
    {
      return SharedIcons.getDescriptor(SharedIcons.OBJ_EDITOR_HISTORICAL);
    }

    if (view.isReadOnly())
    {
      return SharedIcons.getDescriptor(SharedIcons.OBJ_EDITOR_READONLY);
    }

    return SharedIcons.getDescriptor(SharedIcons.OBJ_EDITOR);
  }

  public String getName()
  {
    if (resourcePath != null)
    {
      return new Path(resourcePath).lastSegment();
    }

    return view.getSession().getRepositoryName();
  }

  public IPersistableElement getPersistable()
  {
    return null;
  }

  public String getToolTipText()
  {
    CDOSession session = view.getSession();
    IConnector connector = session.getChannel().getConnector();
    String repositoryName = session.getRepositoryName();

    StringBuilder builder = new StringBuilder();
    builder.append(connector.getURL());
    builder.append("/");
    builder.append(repositoryName);
    if (resourcePath != null)
    {
      builder.append(resourcePath);
    }

    builder.append(" [");
    builder.append(session.getSessionID());
    builder.append(":");
    builder.append(view.getID());
    builder.append("]");

    if (view.isHistorical())
    {
      builder.append(" ");
      builder.append(new Date(view.getTimeStamp()));
    }
    else if (view.isReadOnly())
    {
      builder.append(" readonly");
    }

    return builder.toString();
  }
}
