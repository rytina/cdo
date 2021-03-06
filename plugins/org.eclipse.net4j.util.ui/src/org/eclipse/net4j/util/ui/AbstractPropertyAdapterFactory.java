/*
 * Copyright (c) 2011-2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.net4j.util.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @author Eike Stepper
 * @since 3.2
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractPropertyAdapterFactory implements IAdapterFactory
{
  private static final Class[] CLASSES = { IPropertySourceProvider.class, IActionFilter.class };

  public AbstractPropertyAdapterFactory()
  {
  }

  public Class[] getAdapterList()
  {
    return CLASSES;
  }

  public Object getAdapter(Object adaptableObject, Class adapterType)
  {
    if (adapterType == CLASSES[0])
    {
      final IPropertySource propertySource = createPropertySource(adaptableObject);
      if (propertySource != null)
      {
        return new IPropertySourceProvider()
        {
          public IPropertySource getPropertySource(Object object)
          {
            return propertySource;
          }
        };
      }
    }

    if (adapterType == CLASSES[1])
    {
      return createActionFilter(adaptableObject);
    }

    return null;
  }

  protected abstract IPropertySource createPropertySource(Object object);

  /**
   * @since 3.4
   */
  protected IActionFilter createActionFilter(Object object)
  {
    return null;
  }
}
