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
package org.eclipse.emf.cdo.releng.setup.provider;

import org.eclipse.emf.cdo.releng.internal.setup.ui.PropertiesViewer;
import org.eclipse.emf.cdo.releng.setup.Preferences;
import org.eclipse.emf.cdo.releng.setup.SetupPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import java.util.Collection;
import java.util.List;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.cdo.releng.setup.Preferences} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PreferencesItemProvider extends SetupTaskContainerItemProvider implements IEditingDomainItemProvider,
    IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreferencesItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addInstallFolderPropertyDescriptor(object);
      addBundlePoolFolderPropertyDescriptor(object);
      addBundlePoolFolderTPPropertyDescriptor(object);
      addAcceptedLicensesPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Install Folder feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addInstallFolderPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors
        .add(createItemPropertyDescriptor(
            ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
            getResourceLocator(),
            getString("_UI_Preferences_installFolder_feature"),
            getString("_UI_PropertyDescriptor_description", "_UI_Preferences_installFolder_feature",
                "_UI_Preferences_type"), SetupPackage.Literals.PREFERENCES__INSTALL_FOLDER, true, false, false,
            ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Bundle Pool Folder feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addBundlePoolFolderPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Preferences_bundlePoolFolder_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Preferences_bundlePoolFolder_feature",
            "_UI_Preferences_type"), SetupPackage.Literals.PREFERENCES__BUNDLE_POOL_FOLDER, true, false, false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, PropertiesViewer.EXPERT_FILTER));
  }

  /**
   * This adds a property descriptor for the Bundle Pool Folder TP feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBundlePoolFolderTPPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Preferences_bundlePoolFolderTP_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Preferences_bundlePoolFolderTP_feature",
            "_UI_Preferences_type"), SetupPackage.Literals.PREFERENCES__BUNDLE_POOL_FOLDER_TP, true, false, false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
  }

  /**
     * This adds a property descriptor for the Accepted Licenses feature.
     * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
     * @generated NOT
     */
  protected void addAcceptedLicensesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_Preferences_acceptedLicenses_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_Preferences_acceptedLicenses_feature",
            "_UI_Preferences_type"), SetupPackage.Literals.PREFERENCES__ACCEPTED_LICENSES, true, false, false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, PropertiesViewer.EXPERT_FILTER));
  }

  /**
   * This returns Preferences.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/Preferences"));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean shouldComposeCreationImage()
  {
    return true;
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public String getText(Object object)
  {
    return getString("_UI_Preferences_type");
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(Preferences.class))
    {
    case SetupPackage.PREFERENCES__INSTALL_FOLDER:
    case SetupPackage.PREFERENCES__BUNDLE_POOL_FOLDER:
    case SetupPackage.PREFERENCES__BUNDLE_POOL_FOLDER_TP:
    case SetupPackage.PREFERENCES__ACCEPTED_LICENSES:
      fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
      return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

}
