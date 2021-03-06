/*
 * Copyright (c) 2010-2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Martin Fluegge - initial API and implementation
 */
package org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.impl;

import org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.DawnFragmentGenerator;
import org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.DawnGenerator;
import org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.DawngenmodelFactory;
import org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.DawngenmodelPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class DawngenmodelFactoryImpl extends EFactoryImpl implements DawngenmodelFactory
{
  /**
   * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public static DawngenmodelFactory init()
  {
    try
    {
      DawngenmodelFactory theDawngenmodelFactory = (DawngenmodelFactory)EPackage.Registry.INSTANCE
          .getEFactory("http://www.eclipse.org/emf/cdo/dawn/2010/GenModel");
      if (theDawngenmodelFactory != null)
      {
        return theDawngenmodelFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DawngenmodelFactoryImpl();
  }

  /**
   * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public DawngenmodelFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
    case DawngenmodelPackage.DAWN_GENERATOR:
      return createDawnGenerator();
    case DawngenmodelPackage.DAWN_FRAGMENT_GENERATOR:
      return createDawnFragmentGenerator();
    default:
      throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @since 1.0
   */
  public DawnGenerator createDawnGenerator()
  {
    DawnGeneratorImpl dawnGenerator = new DawnGeneratorImpl();
    return dawnGenerator;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   * @since 1.0
   */
  public DawnFragmentGenerator createDawnFragmentGenerator()
  {
    DawnFragmentGeneratorImpl dawnFragmentGenerator = new DawnFragmentGeneratorImpl();
    return dawnFragmentGenerator;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @generated
   */
  public DawngenmodelPackage getDawngenmodelPackage()
  {
    return (DawngenmodelPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @deprecated
   * @generated
   */
  @Deprecated
  public static DawngenmodelPackage getPackage()
  {
    return DawngenmodelPackage.eINSTANCE;
  }

} // DawngenmodelFactoryImpl
