/**
 */
package org.eclipse.emf.cdo.security.impl;

import org.eclipse.emf.cdo.common.branch.CDOBranchPoint;
import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.common.revision.CDORevisionProvider;
import org.eclipse.emf.cdo.security.ClassFilter;
import org.eclipse.emf.cdo.security.SecurityPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class Filter</b></em>'.
 * @since 4.3
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.cdo.security.impl.ClassFilterImpl#getApplicableClass <em>Applicable Class</em>}</li>
 *   <li>{@link org.eclipse.emf.cdo.security.impl.ClassFilterImpl#isSubTypes <em>Sub Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassFilterImpl extends PermissionFilterImpl implements ClassFilter
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClassFilterImpl()
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
    return SecurityPackage.Literals.CLASS_FILTER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getApplicableClass()
  {
    return (EClass)eGet(SecurityPackage.Literals.CLASS_FILTER__APPLICABLE_CLASS, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setApplicableClass(EClass newApplicableClass)
  {
    eSet(SecurityPackage.Literals.CLASS_FILTER__APPLICABLE_CLASS, newApplicableClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSubTypes()
  {
    return (Boolean)eGet(SecurityPackage.Literals.CLASS_FILTER__SUB_TYPES, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubTypes(boolean newSubTypes)
  {
    eSet(SecurityPackage.Literals.CLASS_FILTER__SUB_TYPES, newSubTypes);
  }

  @Override
  protected boolean filter(CDORevision revision, CDORevisionProvider revisionProvider, CDOBranchPoint securityContext,
      int level) throws Exception
  {
    EClass actualClass = revision.getEClass();
    EClass applicableClass = getApplicableClass();

    if (isSubTypes())
    {
      return applicableClass.isSuperTypeOf(actualClass);
    }

    return applicableClass == actualClass;
  }

  public String format()
  {
    String label = "?";

    EClass applicableClass = getApplicableClass();
    if (applicableClass != null)
    {
      label = applicableClass.getEPackage().getName() + "." + applicableClass.getName();
    }

    String operator = formatOperator();
    return "class" + operator + label;
  }

  private String formatOperator()
  {
    return isSubTypes() ? " >= " : " == ";
  }

} // ClassFilterImpl