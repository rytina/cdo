/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.cdo.internal.ui.editor;

import org.eclipse.emf.cdo.internal.ui.bundle.OM;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * This is the central singleton for the Editor editor plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public final class PluginDelegator extends EMFPlugin
{
  /**
   * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public static final PluginDelegator INSTANCE = new PluginDelegator();

  /**
   * Keep track of the singleton. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private static Implementation plugin;

  /**
   * Create the instance. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public PluginDelegator()
  {
    super(new ResourceLocator[] {});
  }

  /**
   * @ADDED
   */
  @Override
  public void log(Object logEntry)
  {
    if (logEntry instanceof Throwable)
    {
      OM.LOG.error((Throwable)logEntry);
    }
    else
    {
      OM.LOG.info(logEntry.toString());
    }
  }

  /**
   * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the singleton instance.
   * @generated
   */
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }

  /**
   * Returns the singleton instance of the Eclipse plugin. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the singleton instance.
   * @generated
   */
  public static Implementation getPlugin()
  {
    return plugin;
  }

  /**
   * The actual implementation of the Eclipse <b>Plugin</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public static class Implementation extends EclipseUIPlugin
  {
    /**
     * Creates an instance. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Implementation()
    {
      super();

      // Remember the static instance.
      //
      plugin = this;
    }
  }

}
