/**
 * <copyright>
 * </copyright>
 *
 * $Id: IMultiRefNonContainer.java,v 1.3 2008-09-18 12:56:27 estepper Exp $
 */
package org.eclipse.emf.cdo.tests.model4interfaces;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>IMulti Ref Non Container</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.cdo.tests.model4interfaces.IMultiRefNonContainer#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.emf.cdo.tests.model4interfaces.model4interfacesPackage#getIMultiRefNonContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IMultiRefNonContainer extends EObject
{
  /**
   * Returns the value of the '<em><b>Elements</b></em>' reference list. The list contents are of type
   * {@link org.eclipse.emf.cdo.tests.model4interfaces.IMultiRefNonContainedElement}. It is bidirectional and its
   * opposite is '{@link org.eclipse.emf.cdo.tests.model4interfaces.IMultiRefNonContainedElement#getParent
   * <em>Parent</em>}'. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' reference isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Elements</em>' reference list.
   * @see org.eclipse.emf.cdo.tests.model4interfaces.model4interfacesPackage#getIMultiRefNonContainer_Elements()
   * @see org.eclipse.emf.cdo.tests.model4interfaces.IMultiRefNonContainedElement#getParent
   * @model opposite="parent"
   * @generated
   */
  EList<IMultiRefNonContainedElement> getElements();

} // IMultiRefNonContainer
