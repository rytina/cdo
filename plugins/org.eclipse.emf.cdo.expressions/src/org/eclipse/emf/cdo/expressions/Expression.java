/*
 * Copyright (c) 2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.expressions;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.cdo.expressions.ExpressionsPackage#getExpression()
 * @model interface="true" abstract="true"
 * @extends CDOObject
 * @generated
 */
public interface Expression extends CDOObject
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model contextDataType="org.eclipse.emf.cdo.expressions.EvaluationContext"
   * @generated
   */
  Object evaluate(EvaluationContext context);

} // Expression
