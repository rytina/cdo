/*
 * Copyright (c) 2007, 2011, 2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.net4j.util.container.delegate;

import org.eclipse.net4j.util.container.IContainer;

import java.util.Queue;

/**
 * A {@link IContainer container} that is a {@link Queue}.
 * 
 * @author Eike Stepper
 */
public interface IContainerQueue<E> extends IContainerCollection<E>, Queue<E>
{
  public Queue<E> getDelegate();
}
