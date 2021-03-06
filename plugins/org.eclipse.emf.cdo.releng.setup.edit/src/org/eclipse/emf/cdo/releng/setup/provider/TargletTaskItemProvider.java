/*
 * Copyright (c) 2014 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.releng.setup.provider;

import org.eclipse.emf.cdo.releng.setup.Component;
import org.eclipse.emf.cdo.releng.setup.InstallableUnit;
import org.eclipse.emf.cdo.releng.setup.MaterializationTask;
import org.eclipse.emf.cdo.releng.setup.P2Repository;
import org.eclipse.emf.cdo.releng.setup.RepositoryList;
import org.eclipse.emf.cdo.releng.setup.SetupFactory;
import org.eclipse.emf.cdo.releng.setup.SetupPackage;
import org.eclipse.emf.cdo.releng.setup.SourceLocator;
import org.eclipse.emf.cdo.releng.setup.TargletTask;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.cdo.releng.setup.TargletTask} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TargletTaskItemProvider extends SetupTaskItemProvider implements IEditingDomainItemProvider,
    IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TargletTaskItemProvider(AdapterFactory adapterFactory)
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

      addNamePropertyDescriptor(object);
      addActiveRepositoryListPropertyDescriptor(object);
      addIncludeSourcesPropertyDescriptor(object);
      addIncludeAllPlatformsPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Name feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addNamePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), getResourceLocator(),
        getString("_UI_TargletData_name_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_TargletData_name_feature", "_UI_TargletData_type"),
        SetupPackage.Literals.TARGLET_DATA__NAME, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null,
        null));
  }

  /**
   * This adds a property descriptor for the Active Repository List feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addActiveRepositoryListPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_TargletData_activeRepositoryList_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_TargletData_activeRepositoryList_feature",
            "_UI_TargletData_type"), SetupPackage.Literals.TARGLET_DATA__ACTIVE_REPOSITORY_LIST, true, false, false,
        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Include Sources feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addIncludeSourcesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_TargletData_includeSources_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_TargletData_includeSources_feature",
            "_UI_TargletData_type"), SetupPackage.Literals.TARGLET_DATA__INCLUDE_SOURCES, true, false, false,
        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
  }

  /**
   * This adds a property descriptor for the Include All Platforms feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addIncludeAllPlatformsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add(createItemPropertyDescriptor(
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
        getResourceLocator(),
        getString("_UI_TargletData_includeAllPlatforms_feature"),
        getString("_UI_PropertyDescriptor_description", "_UI_TargletData_includeAllPlatforms_feature",
            "_UI_TargletData_type"), SetupPackage.Literals.TARGLET_DATA__INCLUDE_ALL_PLATFORMS, true, false, false,
        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(SetupPackage.Literals.TARGLET_DATA__ROOTS);
      childrenFeatures.add(SetupPackage.Literals.TARGLET_DATA__SOURCE_LOCATORS);
      childrenFeatures.add(SetupPackage.Literals.TARGLET_DATA__REPOSITORY_LISTS);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns TargletTask.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/TargletTask"));
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
    String label = ((TargletTask)object).getName();
    return label == null || label.length() == 0 ? getString("_UI_TargletTask_type") : label;
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

    switch (notification.getFeatureID(TargletTask.class))
    {
    case SetupPackage.TARGLET_TASK__NAME:
    case SetupPackage.TARGLET_TASK__ACTIVE_REPOSITORY_LIST:
    case SetupPackage.TARGLET_TASK__INCLUDE_SOURCES:
    case SetupPackage.TARGLET_TASK__INCLUDE_ALL_PLATFORMS:
      fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
      return;
    case SetupPackage.TARGLET_TASK__ROOTS:
    case SetupPackage.TARGLET_TASK__SOURCE_LOCATORS:
    case SetupPackage.TARGLET_TASK__REPOSITORY_LISTS:
      fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

    newChildDescriptors.add(createChildParameter(SetupPackage.Literals.TARGLET_DATA__ROOTS,
        SetupFactory.eINSTANCE.createInstallableUnit()));

    newChildDescriptors.add(createChildParameter(SetupPackage.Literals.TARGLET_DATA__SOURCE_LOCATORS,
        SetupFactory.eINSTANCE.createAutomaticSourceLocator()));

    newChildDescriptors.add(createChildParameter(SetupPackage.Literals.TARGLET_DATA__REPOSITORY_LISTS,
        SetupFactory.eINSTANCE.createRepositoryList()));
  }

  @Override
  protected Command factorAddCommand(EditingDomain domain, CommandParameter commandParameter)
  {
    Collection<?> collection = commandParameter.getCollection();
    if (collection != null)
    {
      for (Object value : collection)
      {
        if (value instanceof MaterializationTask)
        {
          MaterializationTask materializationTask = (MaterializationTask)value;
          if (materializationTask.eContainer() == null)
          {
            TargletTask targletTask = (TargletTask)commandParameter.getOwner();

            CompoundCommand result = new CompoundCommand("Copy Materialization contents",
                "Copy the contents of the materialization task");

            List<InstallableUnit> installableUnits = new ArrayList<InstallableUnit>();
            for (Component component : materializationTask.getRootComponents())
            {
              switch (component.getType())
              {
              case OSGI_BUNDLE:
              {
                InstallableUnit installableUnit = SetupFactory.eINSTANCE.createInstallableUnit();
                String name = component.getName();
                installableUnit.setID(name);
                installableUnit.setVersionRange(component.getVersionRange());
                installableUnits.add(installableUnit);
                break;
              }
              case ECLIPSE_FEATURE:
              {
                InstallableUnit installableUnit = SetupFactory.eINSTANCE.createInstallableUnit();
                String name = component.getName();
                if (name != null)
                {
                  installableUnit.setID(name + ".feature.group");
                }
                installableUnit.setVersionRange(component.getVersionRange());
                installableUnits.add(installableUnit);
                break;
              }
              }
            }

            if (!installableUnits.isEmpty())
            {
              result.appendIfCanExecute(AddCommand.create(domain, targletTask,
                  SetupPackage.Literals.TARGLET_DATA__ROOTS, installableUnits));
            }

            List<SourceLocator> sourceLocators = new ArrayList<SourceLocator>(materializationTask.getSourceLocators());
            materializationTask.getSourceLocators().clear();
            result.appendIfCanExecute(AddCommand.create(domain, targletTask,
                SetupPackage.Literals.TARGLET_DATA__SOURCE_LOCATORS, sourceLocators));

            List<P2Repository> p2Repositories = materializationTask.getP2Repositories();
            if (!p2Repositories.isEmpty())
            {
              RepositoryList repositoryList = SetupFactory.eINSTANCE.createRepositoryList();
              repositoryList.getP2Repositories().addAll(p2Repositories);
              repositoryList.setName("Default");
              result.appendIfCanExecute(AddCommand.create(domain, targletTask,
                  SetupPackage.Literals.TARGLET_DATA__REPOSITORY_LISTS, repositoryList));
            }

            return result;
          }
        }
      }
    }

    return super.factorAddCommand(domain, commandParameter);
  }

}
