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
package org.eclipse.emf.internal.cdo.object;

import org.eclipse.emf.cdo.CDOLock;
import org.eclipse.emf.cdo.common.lock.CDOLockState;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.net4j.util.concurrent.IRWLockManager.LockType;

import org.eclipse.emf.spi.cdo.InternalCDOObject;

/**
 * @author Eike Stepper
 * @since 2.0
 */
public abstract class CDOObjectWrapper extends CDOObjectWrapperBase implements InternalCDOObject
{
  public CDOObjectWrapper()
  {
  }

  /**
   * @since 2.0
   */
  public CDOLock cdoReadLock()
  {
    return CDOObjectImpl.createLock(this, LockType.READ);
  }

  /**
   * @since 2.0
   */
  public CDOLock cdoWriteLock()
  {
    return CDOObjectImpl.createLock(this, LockType.WRITE);
  }

  /**
   * @since 4.1
   */
  public CDOLock cdoWriteOption()
  {
    return CDOObjectImpl.createLock(this, LockType.OPTION);
  }

  public synchronized CDOLockState cdoLockState()
  {
    return CDOObjectImpl.getLockState(this);
  }
}
