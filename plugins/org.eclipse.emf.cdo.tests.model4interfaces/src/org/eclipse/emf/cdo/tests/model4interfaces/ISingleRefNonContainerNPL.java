/**
 * <copyright>
 * </copyright>
 *
 * $Id: ISingleRefNonContainerNPL.java,v 1.3 2008-09-18 12:56:27 estepper Exp $
 */
package org.eclipse.emf.cdo.tests.model4interfaces;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>ISingle Ref Non Container NPL</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.cdo.tests.model4interfaces.ISingleRefNonContainerNPL#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.emf.cdo.tests.model4interfaces.model4interfacesPackage#getISingleRefNonContainerNPL()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ISingleRefNonContainerNPL extends EObject
{
  /**
   * Returns the value of the '<em><b>Element</b></em>' reference. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element</em>' reference isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Element</em>' reference.
   * @see #setElement(IContainedElementNoParentLink)
   * @see org.eclipse.emf.cdo.tests.model4interfaces.model4interfacesPackage#getISingleRefNonContainerNPL_Element()
   * @model
   * @generated
   */
  IContainedElementNoParentLink getElement();

  /**
   * Sets the value of the '{@link org.eclipse.emf.cdo.tests.model4interfaces.ISingleRefNonContainerNPL#getElement
   * <em>Element</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value
   *          the new value of the '<em>Element</em>' reference.
   * @see #getElement()
   * @generated
   */
  void setElement(IContainedElementNoParentLink value);

} // ISingleRefNonContainerNPL
