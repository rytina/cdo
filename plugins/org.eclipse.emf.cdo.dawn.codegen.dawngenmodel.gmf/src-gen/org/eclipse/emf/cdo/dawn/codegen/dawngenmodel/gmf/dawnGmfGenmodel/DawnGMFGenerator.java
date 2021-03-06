/*
 * Copyright (c) 2011, 2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Martin Fluegge - initial API and implementation
 */
package org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel;

import org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.DawnFragmentGenerator;

import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Dawn GMF Generator</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnDocumentProviderClassName
 * <em>Dawn Document Provider Class Name</em>}</li>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnEditorUtilClassName
 * <em>Dawn Editor Util Class Name</em>}</li>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnCreationWizardClassName
 * <em>Dawn Creation Wizard Class Name</em>}</li>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnCanonicalEditingPolicyClassName
 * <em>Dawn Canonical Editing Policy Class Name</em>}</li>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnDiagramEditPartClassName
 * <em>Dawn Diagram Edit Part Class Name</em>}</li>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnEditPartFactoryClassName
 * <em>Dawn Edit Part Factory Class Name</em>}</li>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnEditPartProviderClassName
 * <em>Dawn Edit Part Provider Class Name</em>}</li>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnEditPolicyProviderClassName
 * <em>Dawn Edit Policy Provider Class Name</em>}</li>
 * <li>
 * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getGMFGenEditorGenerator
 * <em>GMF Gen Editor Generator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator()
 * @model
 * @generated
 * @author Martin Fluegge
 */
public interface DawnGMFGenerator extends DawnFragmentGenerator
{
  /**
   * Returns the value of the '<em><b>Dawn Document Provider Class Name</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dawn Document Provider Class Name</em>' attribute isn't clear, there really should be
   * more of a description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Dawn Document Provider Class Name</em>' attribute.
   * @see #setDawnDocumentProviderClassName(String)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_DawnDocumentProviderClassName()
   * @model
   * @generated
   */
  String getDawnDocumentProviderClassName();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnDocumentProviderClassName
   * <em>Dawn Document Provider Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>Dawn Document Provider Class Name</em>' attribute.
   * @see #getDawnDocumentProviderClassName()
   * @generated
   */
  void setDawnDocumentProviderClassName(String value);

  /**
   * Returns the value of the '<em><b>Dawn Editor Util Class Name</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dawn Editor Util Class Name</em>' attribute isn't clear, there really should be more of
   * a description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Dawn Editor Util Class Name</em>' attribute.
   * @see #setDawnEditorUtilClassName(String)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_DawnEditorUtilClassName()
   * @model
   * @generated
   */
  String getDawnEditorUtilClassName();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnEditorUtilClassName
   * <em>Dawn Editor Util Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>Dawn Editor Util Class Name</em>' attribute.
   * @see #getDawnEditorUtilClassName()
   * @generated
   */
  void setDawnEditorUtilClassName(String value);

  /**
   * Returns the value of the '<em><b>Dawn Creation Wizard Class Name</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dawn Creation Wizard Class Name</em>' attribute isn't clear, there really should be more
   * of a description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Dawn Creation Wizard Class Name</em>' attribute.
   * @see #setDawnCreationWizardClassName(String)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_DawnCreationWizardClassName()
   * @model
   * @generated
   */
  String getDawnCreationWizardClassName();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnCreationWizardClassName
   * <em>Dawn Creation Wizard Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>Dawn Creation Wizard Class Name</em>' attribute.
   * @see #getDawnCreationWizardClassName()
   * @generated
   */
  void setDawnCreationWizardClassName(String value);

  /**
   * Returns the value of the '<em><b>Dawn Canonical Editing Policy Class Name</b></em>' attribute. <!-- begin-user-doc
   * -->
   * <p>
   * If the meaning of the '<em>Dawn Canonical Editing Policy Class Name</em>' attribute isn't clear, there really
   * should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Dawn Canonical Editing Policy Class Name</em>' attribute.
   * @see #setDawnCanonicalEditingPolicyClassName(String)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_DawnCanonicalEditingPolicyClassName()
   * @model
   * @generated
   */
  String getDawnCanonicalEditingPolicyClassName();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnCanonicalEditingPolicyClassName
   * <em>Dawn Canonical Editing Policy Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>Dawn Canonical Editing Policy Class Name</em>' attribute.
   * @see #getDawnCanonicalEditingPolicyClassName()
   * @generated
   */
  void setDawnCanonicalEditingPolicyClassName(String value);

  /**
   * Returns the value of the '<em><b>Dawn Diagram Edit Part Class Name</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dawn Diagram Edit Part Class Name</em>' attribute isn't clear, there really should be
   * more of a description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Dawn Diagram Edit Part Class Name</em>' attribute.
   * @see #setDawnDiagramEditPartClassName(String)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_DawnDiagramEditPartClassName()
   * @model
   * @generated
   */
  String getDawnDiagramEditPartClassName();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnDiagramEditPartClassName
   * <em>Dawn Diagram Edit Part Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>Dawn Diagram Edit Part Class Name</em>' attribute.
   * @see #getDawnDiagramEditPartClassName()
   * @generated
   */
  void setDawnDiagramEditPartClassName(String value);

  /**
   * Returns the value of the '<em><b>Dawn Edit Part Factory Class Name</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dawn Edit Part Factory Class Name</em>' attribute isn't clear, there really should be
   * more of a description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Dawn Edit Part Factory Class Name</em>' attribute.
   * @see #setDawnEditPartFactoryClassName(String)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_DawnEditPartFactoryClassName()
   * @model
   * @generated
   */
  String getDawnEditPartFactoryClassName();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnEditPartFactoryClassName
   * <em>Dawn Edit Part Factory Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>Dawn Edit Part Factory Class Name</em>' attribute.
   * @see #getDawnEditPartFactoryClassName()
   * @generated
   */
  void setDawnEditPartFactoryClassName(String value);

  /**
   * Returns the value of the '<em><b>Dawn Edit Part Provider Class Name</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dawn Edit Part Provider Class Name</em>' attribute isn't clear, there really should be
   * more of a description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Dawn Edit Part Provider Class Name</em>' attribute.
   * @see #setDawnEditPartProviderClassName(String)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_DawnEditPartProviderClassName()
   * @model
   * @generated
   */
  String getDawnEditPartProviderClassName();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnEditPartProviderClassName
   * <em>Dawn Edit Part Provider Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>Dawn Edit Part Provider Class Name</em>' attribute.
   * @see #getDawnEditPartProviderClassName()
   * @generated
   */
  void setDawnEditPartProviderClassName(String value);

  /**
   * Returns the value of the '<em><b>Dawn Edit Policy Provider Class Name</b></em>' attribute. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dawn Edit Policy Provider Class Name</em>' attribute isn't clear, there really should be
   * more of a description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Dawn Edit Policy Provider Class Name</em>' attribute.
   * @see #setDawnEditPolicyProviderClassName(String)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_DawnEditPolicyProviderClassName()
   * @model
   * @generated
   */
  String getDawnEditPolicyProviderClassName();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getDawnEditPolicyProviderClassName
   * <em>Dawn Edit Policy Provider Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>Dawn Edit Policy Provider Class Name</em>' attribute.
   * @see #getDawnEditPolicyProviderClassName()
   * @generated
   */
  void setDawnEditPolicyProviderClassName(String value);

  /**
   * Returns the value of the '<em><b>GMF Gen Editor Generator</b></em>' reference. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>GMF Gen Editor Generator</em>' reference isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>GMF Gen Editor Generator</em>' reference.
   * @see #setGMFGenEditorGenerator(GenEditorGenerator)
   * @see org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGmfGenmodelPackage#getDawnGMFGenerator_GMFGenEditorGenerator()
   * @model
   * @generated
   */
  GenEditorGenerator getGMFGenEditorGenerator();

  /**
   * Sets the value of the '
   * {@link org.eclipse.emf.cdo.dawn.codegen.dawngenmodel.gmf.dawnGmfGenmodel.DawnGMFGenerator#getGMFGenEditorGenerator
   * <em>GMF Gen Editor Generator</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   *
   * @param value
   *          the new value of the '<em>GMF Gen Editor Generator</em>' reference.
   * @see #getGMFGenEditorGenerator()
   * @generated
   */
  void setGMFGenEditorGenerator(GenEditorGenerator value);

} // DawnGMFGenerator
