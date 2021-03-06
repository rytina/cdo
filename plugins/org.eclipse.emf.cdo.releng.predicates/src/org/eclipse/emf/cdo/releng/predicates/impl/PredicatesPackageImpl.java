/*
 * Copyright (c) 2013, 2014 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.releng.predicates.impl;

import org.eclipse.emf.cdo.releng.predicates.AndPredicate;
import org.eclipse.emf.cdo.releng.predicates.BuilderPredicate;
import org.eclipse.emf.cdo.releng.predicates.CommentPredicate;
import org.eclipse.emf.cdo.releng.predicates.FilePredicate;
import org.eclipse.emf.cdo.releng.predicates.LocationPredicate;
import org.eclipse.emf.cdo.releng.predicates.NamePredicate;
import org.eclipse.emf.cdo.releng.predicates.NaturePredicate;
import org.eclipse.emf.cdo.releng.predicates.NotPredicate;
import org.eclipse.emf.cdo.releng.predicates.OrPredicate;
import org.eclipse.emf.cdo.releng.predicates.Predicate;
import org.eclipse.emf.cdo.releng.predicates.PredicatesFactory;
import org.eclipse.emf.cdo.releng.predicates.PredicatesPackage;
import org.eclipse.emf.cdo.releng.predicates.RepositoryPredicate;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.core.resources.IProject;

import java.io.File;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PredicatesPackageImpl extends EPackageImpl implements PredicatesPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass predicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namePredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass commentPredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass locationPredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass repositoryPredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass andPredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orPredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass notPredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass naturePredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass builderPredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass filePredicateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType projectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType fileEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.cdo.releng.predicates.PredicatesPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private PredicatesPackageImpl()
  {
    super(eNS_URI, PredicatesFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   *
   * <p>This method is used to initialize {@link PredicatesPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static PredicatesPackage init()
  {
    if (isInited)
    {
      return (PredicatesPackage)EPackage.Registry.INSTANCE.getEPackage(PredicatesPackage.eNS_URI);
    }

    // Obtain or create and register package
    PredicatesPackageImpl thePredicatesPackage = (PredicatesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PredicatesPackageImpl ? EPackage.Registry.INSTANCE
        .get(eNS_URI) : new PredicatesPackageImpl());

    isInited = true;

    // Create package meta-data objects
    thePredicatesPackage.createPackageContents();

    // Initialize created meta-data
    thePredicatesPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    thePredicatesPackage.freeze();

    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(PredicatesPackage.eNS_URI, thePredicatesPackage);
    return thePredicatesPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPredicate()
  {
    return predicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getPredicate__Matches__IProject()
  {
    return predicateEClass.getEOperations().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EOperation getPredicate__Matches__File()
  {
    return predicateEClass.getEOperations().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamePredicate()
  {
    return namePredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamePredicate_Pattern()
  {
    return (EAttribute)namePredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCommentPredicate()
  {
    return commentPredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCommentPredicate_Pattern()
  {
    return (EAttribute)commentPredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLocationPredicate()
  {
    return locationPredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLocationPredicate_Pattern()
  {
    return (EAttribute)locationPredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRepositoryPredicate()
  {
    return repositoryPredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRepositoryPredicate_Project()
  {
    return (EAttribute)repositoryPredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAndPredicate()
  {
    return andPredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAndPredicate_Operands()
  {
    return (EReference)andPredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrPredicate()
  {
    return orPredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrPredicate_Operands()
  {
    return (EReference)orPredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNotPredicate()
  {
    return notPredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNotPredicate_Operand()
  {
    return (EReference)notPredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNaturePredicate()
  {
    return naturePredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNaturePredicate_Nature()
  {
    return (EAttribute)naturePredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBuilderPredicate()
  {
    return builderPredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBuilderPredicate_Builder()
  {
    return (EAttribute)builderPredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFilePredicate()
  {
    return filePredicateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFilePredicate_FilePattern()
  {
    return (EAttribute)filePredicateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFilePredicate_ContentPattern()
  {
    return (EAttribute)filePredicateEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getProject()
  {
    return projectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getFile()
  {
    return fileEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PredicatesFactory getPredicatesFactory()
  {
    return (PredicatesFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated)
    {
      return;
    }
    isCreated = true;

    // Create classes and their features
    predicateEClass = createEClass(PREDICATE);
    createEOperation(predicateEClass, PREDICATE___MATCHES__IPROJECT);
    createEOperation(predicateEClass, PREDICATE___MATCHES__FILE);

    namePredicateEClass = createEClass(NAME_PREDICATE);
    createEAttribute(namePredicateEClass, NAME_PREDICATE__PATTERN);

    commentPredicateEClass = createEClass(COMMENT_PREDICATE);
    createEAttribute(commentPredicateEClass, COMMENT_PREDICATE__PATTERN);

    locationPredicateEClass = createEClass(LOCATION_PREDICATE);
    createEAttribute(locationPredicateEClass, LOCATION_PREDICATE__PATTERN);

    repositoryPredicateEClass = createEClass(REPOSITORY_PREDICATE);
    createEAttribute(repositoryPredicateEClass, REPOSITORY_PREDICATE__PROJECT);

    andPredicateEClass = createEClass(AND_PREDICATE);
    createEReference(andPredicateEClass, AND_PREDICATE__OPERANDS);

    orPredicateEClass = createEClass(OR_PREDICATE);
    createEReference(orPredicateEClass, OR_PREDICATE__OPERANDS);

    notPredicateEClass = createEClass(NOT_PREDICATE);
    createEReference(notPredicateEClass, NOT_PREDICATE__OPERAND);

    naturePredicateEClass = createEClass(NATURE_PREDICATE);
    createEAttribute(naturePredicateEClass, NATURE_PREDICATE__NATURE);

    builderPredicateEClass = createEClass(BUILDER_PREDICATE);
    createEAttribute(builderPredicateEClass, BUILDER_PREDICATE__BUILDER);

    filePredicateEClass = createEClass(FILE_PREDICATE);
    createEAttribute(filePredicateEClass, FILE_PREDICATE__FILE_PATTERN);
    createEAttribute(filePredicateEClass, FILE_PREDICATE__CONTENT_PATTERN);

    // Create data types
    projectEDataType = createEDataType(PROJECT);
    fileEDataType = createEDataType(FILE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized)
    {
      return;
    }
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    namePredicateEClass.getESuperTypes().add(getPredicate());
    commentPredicateEClass.getESuperTypes().add(getPredicate());
    locationPredicateEClass.getESuperTypes().add(getPredicate());
    repositoryPredicateEClass.getESuperTypes().add(getPredicate());
    andPredicateEClass.getESuperTypes().add(getPredicate());
    orPredicateEClass.getESuperTypes().add(getPredicate());
    notPredicateEClass.getESuperTypes().add(getPredicate());
    naturePredicateEClass.getESuperTypes().add(getPredicate());
    builderPredicateEClass.getESuperTypes().add(getPredicate());
    filePredicateEClass.getESuperTypes().add(getPredicate());

    // Initialize classes, features, and operations; add parameters
    initEClass(predicateEClass, Predicate.class, "Predicate", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    EOperation op = initEOperation(getPredicate__Matches__IProject(), ecorePackage.getEBoolean(), "matches", 0, 1,
        IS_UNIQUE, IS_ORDERED);
    addEParameter(op, getProject(), "project", 0, 1, IS_UNIQUE, IS_ORDERED);

    op = initEOperation(getPredicate__Matches__File(), ecorePackage.getEBoolean(), "matches", 0, 1, IS_UNIQUE,
        IS_ORDERED);
    addEParameter(op, getFile(), "projectFolder", 0, 1, IS_UNIQUE, IS_ORDERED);

    initEClass(namePredicateEClass, NamePredicate.class, "NamePredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNamePredicate_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1, NamePredicate.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(commentPredicateEClass, CommentPredicate.class, "CommentPredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCommentPredicate_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1,
        CommentPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(locationPredicateEClass, LocationPredicate.class, "LocationPredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLocationPredicate_Pattern(), ecorePackage.getEString(), "pattern", null, 1, 1,
        LocationPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(repositoryPredicateEClass, RepositoryPredicate.class, "RepositoryPredicate", !IS_ABSTRACT,
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRepositoryPredicate_Project(), getProject(), "project", null, 0, 1, RepositoryPredicate.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(andPredicateEClass, AndPredicate.class, "AndPredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAndPredicate_Operands(), getPredicate(), null, "operands", null, 0, -1, AndPredicate.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(orPredicateEClass, OrPredicate.class, "OrPredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOrPredicate_Operands(), getPredicate(), null, "operands", null, 0, -1, OrPredicate.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(notPredicateEClass, NotPredicate.class, "NotPredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEReference(getNotPredicate_Operand(), getPredicate(), null, "operand", null, 0, 1, NotPredicate.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(naturePredicateEClass, NaturePredicate.class, "NaturePredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNaturePredicate_Nature(), ecorePackage.getEString(), "nature", null, 1, 1, NaturePredicate.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(builderPredicateEClass, BuilderPredicate.class, "BuilderPredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getBuilderPredicate_Builder(), ecorePackage.getEString(), "builder", null, 1, 1,
        BuilderPredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(filePredicateEClass, FilePredicate.class, "FilePredicate", !IS_ABSTRACT, !IS_INTERFACE,
        IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFilePredicate_FilePattern(), ecorePackage.getEString(), "filePattern", null, 1, 1,
        FilePredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFilePredicate_ContentPattern(), ecorePackage.getEString(), "contentPattern", null, 0, 1,
        FilePredicate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    // Initialize data types
    initEDataType(projectEDataType, IProject.class, "Project", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
    initEDataType(fileEDataType, File.class, "File", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} // PredicatesPackageImpl
