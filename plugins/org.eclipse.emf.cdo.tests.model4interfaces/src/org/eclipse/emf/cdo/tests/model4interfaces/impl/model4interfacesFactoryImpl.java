/**
 * <copyright>
 * </copyright>
 *
 * $Id: model4interfacesFactoryImpl.java,v 1.3 2008-09-18 12:56:27 estepper Exp $
 */
package org.eclipse.emf.cdo.tests.model4interfaces.impl;

import org.eclipse.emf.cdo.tests.model4interfaces.model4interfacesFactory;
import org.eclipse.emf.cdo.tests.model4interfaces.model4interfacesPackage;

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
public class model4interfacesFactoryImpl extends EFactoryImpl implements model4interfacesFactory
{
  /**
   * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public static model4interfacesFactory init()
  {
    try
    {
      model4interfacesFactory themodel4interfacesFactory = (model4interfacesFactory)EPackage.Registry.INSTANCE
          .getEFactory("http://www.eclipse.org/emf/CDO/tests/model4interfaces/1.0.0");
      if (themodel4interfacesFactory != null)
      {
        return themodel4interfacesFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new model4interfacesFactoryImpl();
  }

  /**
   * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public model4interfacesFactoryImpl()
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
    default:
      throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public model4interfacesPackage getmodel4interfacesPackage()
  {
    return (model4interfacesPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @deprecated
   * @generated
   */
  @Deprecated
  public static model4interfacesPackage getPackage()
  {
    return model4interfacesPackage.eINSTANCE;
  }

} // model4interfacesFactoryImpl
