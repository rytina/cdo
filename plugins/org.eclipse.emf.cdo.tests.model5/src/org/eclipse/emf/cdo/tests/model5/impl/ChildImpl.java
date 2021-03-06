/*
 * Copyright (c) 2012, 2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Christian W. Damus (CEA) - initial API and implementation
 */
package org.eclipse.emf.cdo.tests.model5.impl;

import org.eclipse.emf.cdo.tests.model5.Child;
import org.eclipse.emf.cdo.tests.model5.Model5Package;
import org.eclipse.emf.cdo.tests.model5.Parent;
import org.eclipse.emf.cdo.tests.model5.util.IsLoadingTestFixture;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Child</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.cdo.tests.model5.impl.ChildImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.tests.model5.impl.ChildImpl#getPreferredBy <em>Preferred By</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.tests.model5.impl.ChildImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChildImpl extends CDOObjectImpl implements Child
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ChildImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return Model5Package.eINSTANCE.getChild();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected int eStaticFeatureCount()
  {
    return 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Parent getParent()
  {
    return (Parent)eGet(Model5Package.eINSTANCE.getChild_Parent(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParent(Parent newParent)
  {
    eSet(Model5Package.eINSTANCE.getChild_Parent(), newParent);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Parent getPreferredBy()
  {
    return (Parent)eGet(Model5Package.eINSTANCE.getChild_PreferredBy(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPreferredBy(Parent newPreferredBy)
  {
    eSet(Model5Package.eINSTANCE.getChild_PreferredBy(), newPreferredBy);
  }

  /**
   * <!-- begin-user-doc -->
  	 * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return (String)eGet(Model5Package.eINSTANCE.getChild_Name(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNameGen(String newName)
  {
    eSet(Model5Package.eINSTANCE.getChild_Name(), newName);
  }

  public void setName(String newName)
  {
    IsLoadingTestFixture.reportLoading(eResource(), this);
    setNameGen(newName);
  }

} // ChildImpl
