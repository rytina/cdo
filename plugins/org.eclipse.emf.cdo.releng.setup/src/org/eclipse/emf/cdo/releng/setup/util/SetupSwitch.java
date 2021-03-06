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
package org.eclipse.emf.cdo.releng.setup.util;

import org.eclipse.emf.cdo.releng.setup.ApiBaselineTask;
import org.eclipse.emf.cdo.releng.setup.AutomaticSourceLocator;
import org.eclipse.emf.cdo.releng.setup.BasicMaterializationTask;
import org.eclipse.emf.cdo.releng.setup.Branch;
import org.eclipse.emf.cdo.releng.setup.BuckminsterImportTask;
import org.eclipse.emf.cdo.releng.setup.BuildPlan;
import org.eclipse.emf.cdo.releng.setup.CommandParameter;
import org.eclipse.emf.cdo.releng.setup.Component;
import org.eclipse.emf.cdo.releng.setup.ComponentDefinition;
import org.eclipse.emf.cdo.releng.setup.ComponentExtension;
import org.eclipse.emf.cdo.releng.setup.CompoundSetupTask;
import org.eclipse.emf.cdo.releng.setup.ConfigurableItem;
import org.eclipse.emf.cdo.releng.setup.Configuration;
import org.eclipse.emf.cdo.releng.setup.ContextVariableTask;
import org.eclipse.emf.cdo.releng.setup.Eclipse;
import org.eclipse.emf.cdo.releng.setup.EclipseIniTask;
import org.eclipse.emf.cdo.releng.setup.EclipsePreferenceTask;
import org.eclipse.emf.cdo.releng.setup.FileAssociationTask;
import org.eclipse.emf.cdo.releng.setup.FileAssociationsTask;
import org.eclipse.emf.cdo.releng.setup.FileEditor;
import org.eclipse.emf.cdo.releng.setup.FileMapping;
import org.eclipse.emf.cdo.releng.setup.GitCloneTask;
import org.eclipse.emf.cdo.releng.setup.Index;
import org.eclipse.emf.cdo.releng.setup.InstallableUnit;
import org.eclipse.emf.cdo.releng.setup.JRETask;
import org.eclipse.emf.cdo.releng.setup.KeyBindingContext;
import org.eclipse.emf.cdo.releng.setup.KeyBindingTask;
import org.eclipse.emf.cdo.releng.setup.LinkLocationTask;
import org.eclipse.emf.cdo.releng.setup.ManualSourceLocator;
import org.eclipse.emf.cdo.releng.setup.MaterializationTask;
import org.eclipse.emf.cdo.releng.setup.MavenImportTask;
import org.eclipse.emf.cdo.releng.setup.MetaIndex;
import org.eclipse.emf.cdo.releng.setup.MylynBuildsTask;
import org.eclipse.emf.cdo.releng.setup.MylynQueriesTask;
import org.eclipse.emf.cdo.releng.setup.MylynQueryTask;
import org.eclipse.emf.cdo.releng.setup.P2Repository;
import org.eclipse.emf.cdo.releng.setup.P2Task;
import org.eclipse.emf.cdo.releng.setup.Preferences;
import org.eclipse.emf.cdo.releng.setup.Project;
import org.eclipse.emf.cdo.releng.setup.ProjectSetImportTask;
import org.eclipse.emf.cdo.releng.setup.ProjectsImportTask;
import org.eclipse.emf.cdo.releng.setup.Query;
import org.eclipse.emf.cdo.releng.setup.RedirectionTask;
import org.eclipse.emf.cdo.releng.setup.RepositoryList;
import org.eclipse.emf.cdo.releng.setup.ResourceCopyTask;
import org.eclipse.emf.cdo.releng.setup.ResourceCreationTask;
import org.eclipse.emf.cdo.releng.setup.ScopeRoot;
import org.eclipse.emf.cdo.releng.setup.Setup;
import org.eclipse.emf.cdo.releng.setup.SetupPackage;
import org.eclipse.emf.cdo.releng.setup.SetupTask;
import org.eclipse.emf.cdo.releng.setup.SetupTaskContainer;
import org.eclipse.emf.cdo.releng.setup.SourceLocator;
import org.eclipse.emf.cdo.releng.setup.TargetPlatformTask;
import org.eclipse.emf.cdo.releng.setup.Targlet;
import org.eclipse.emf.cdo.releng.setup.TargletData;
import org.eclipse.emf.cdo.releng.setup.TargletImportTask;
import org.eclipse.emf.cdo.releng.setup.TargletTask;
import org.eclipse.emf.cdo.releng.setup.TextModification;
import org.eclipse.emf.cdo.releng.setup.TextModifyTask;
import org.eclipse.emf.cdo.releng.setup.VariableChoice;
import org.eclipse.emf.cdo.releng.setup.WorkingSetTask;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.cdo.releng.setup.SetupPackage
 * @generated
 */
@SuppressWarnings("deprecation")
public class SetupSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SetupPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetupSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = SetupPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
    case SetupPackage.META_INDEX:
    {
      MetaIndex metaIndex = (MetaIndex)theEObject;
      T result = caseMetaIndex(metaIndex);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.INDEX:
    {
      Index index = (Index)theEObject;
      T result = caseIndex(index);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.ECLIPSE:
    {
      Eclipse eclipse = (Eclipse)theEObject;
      T result = caseEclipse(eclipse);
      if (result == null)
      {
        result = caseConfigurableItem(eclipse);
      }
      if (result == null)
      {
        result = caseScopeRoot(eclipse);
      }
      if (result == null)
      {
        result = caseSetupTaskContainer(eclipse);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.CONFIGURATION:
    {
      Configuration configuration = (Configuration)theEObject;
      T result = caseConfiguration(configuration);
      if (result == null)
      {
        result = caseScopeRoot(configuration);
      }
      if (result == null)
      {
        result = caseSetupTaskContainer(configuration);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.CONFIGURABLE_ITEM:
    {
      ConfigurableItem configurableItem = (ConfigurableItem)theEObject;
      T result = caseConfigurableItem(configurableItem);
      if (result == null)
      {
        result = caseScopeRoot(configurableItem);
      }
      if (result == null)
      {
        result = caseSetupTaskContainer(configurableItem);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.PROJECT:
    {
      Project project = (Project)theEObject;
      T result = caseProject(project);
      if (result == null)
      {
        result = caseConfigurableItem(project);
      }
      if (result == null)
      {
        result = caseScopeRoot(project);
      }
      if (result == null)
      {
        result = caseSetupTaskContainer(project);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.BRANCH:
    {
      Branch branch = (Branch)theEObject;
      T result = caseBranch(branch);
      if (result == null)
      {
        result = caseConfigurableItem(branch);
      }
      if (result == null)
      {
        result = caseScopeRoot(branch);
      }
      if (result == null)
      {
        result = caseSetupTaskContainer(branch);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.PREFERENCES:
    {
      Preferences preferences = (Preferences)theEObject;
      T result = casePreferences(preferences);
      if (result == null)
      {
        result = caseScopeRoot(preferences);
      }
      if (result == null)
      {
        result = caseSetupTaskContainer(preferences);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.SETUP:
    {
      Setup setup = (Setup)theEObject;
      T result = caseSetup(setup);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.SETUP_TASK:
    {
      SetupTask setupTask = (SetupTask)theEObject;
      T result = caseSetupTask(setupTask);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.SETUP_TASK_CONTAINER:
    {
      SetupTaskContainer setupTaskContainer = (SetupTaskContainer)theEObject;
      T result = caseSetupTaskContainer(setupTaskContainer);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.SCOPE_ROOT:
    {
      ScopeRoot scopeRoot = (ScopeRoot)theEObject;
      T result = caseScopeRoot(scopeRoot);
      if (result == null)
      {
        result = caseSetupTaskContainer(scopeRoot);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.COMPOUND_SETUP_TASK:
    {
      CompoundSetupTask compoundSetupTask = (CompoundSetupTask)theEObject;
      T result = caseCompoundSetupTask(compoundSetupTask);
      if (result == null)
      {
        result = caseSetupTask(compoundSetupTask);
      }
      if (result == null)
      {
        result = caseSetupTaskContainer(compoundSetupTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.CONTEXT_VARIABLE_TASK:
    {
      ContextVariableTask contextVariableTask = (ContextVariableTask)theEObject;
      T result = caseContextVariableTask(contextVariableTask);
      if (result == null)
      {
        result = caseSetupTask(contextVariableTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.VARIABLE_CHOICE:
    {
      VariableChoice variableChoice = (VariableChoice)theEObject;
      T result = caseVariableChoice(variableChoice);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.REDIRECTION_TASK:
    {
      RedirectionTask redirectionTask = (RedirectionTask)theEObject;
      T result = caseRedirectionTask(redirectionTask);
      if (result == null)
      {
        result = caseSetupTask(redirectionTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.ECLIPSE_INI_TASK:
    {
      EclipseIniTask eclipseIniTask = (EclipseIniTask)theEObject;
      T result = caseEclipseIniTask(eclipseIniTask);
      if (result == null)
      {
        result = caseSetupTask(eclipseIniTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.LINK_LOCATION_TASK:
    {
      LinkLocationTask linkLocationTask = (LinkLocationTask)theEObject;
      T result = caseLinkLocationTask(linkLocationTask);
      if (result == null)
      {
        result = caseSetupTask(linkLocationTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.P2_TASK:
    {
      P2Task p2Task = (P2Task)theEObject;
      T result = caseP2Task(p2Task);
      if (result == null)
      {
        result = caseSetupTask(p2Task);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.INSTALLABLE_UNIT:
    {
      InstallableUnit installableUnit = (InstallableUnit)theEObject;
      T result = caseInstallableUnit(installableUnit);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.P2_REPOSITORY:
    {
      P2Repository p2Repository = (P2Repository)theEObject;
      T result = caseP2Repository(p2Repository);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.GIT_CLONE_TASK:
    {
      GitCloneTask gitCloneTask = (GitCloneTask)theEObject;
      T result = caseGitCloneTask(gitCloneTask);
      if (result == null)
      {
        result = caseSetupTask(gitCloneTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.BASIC_MATERIALIZATION_TASK:
    {
      BasicMaterializationTask basicMaterializationTask = (BasicMaterializationTask)theEObject;
      T result = caseBasicMaterializationTask(basicMaterializationTask);
      if (result == null)
      {
        result = caseSetupTask(basicMaterializationTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.BUCKMINSTER_IMPORT_TASK:
    {
      BuckminsterImportTask buckminsterImportTask = (BuckminsterImportTask)theEObject;
      T result = caseBuckminsterImportTask(buckminsterImportTask);
      if (result == null)
      {
        result = caseBasicMaterializationTask(buckminsterImportTask);
      }
      if (result == null)
      {
        result = caseSetupTask(buckminsterImportTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.MATERIALIZATION_TASK:
    {
      MaterializationTask materializationTask = (MaterializationTask)theEObject;
      T result = caseMaterializationTask(materializationTask);
      if (result == null)
      {
        result = caseBasicMaterializationTask(materializationTask);
      }
      if (result == null)
      {
        result = caseSetupTask(materializationTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.COMPONENT:
    {
      Component component = (Component)theEObject;
      T result = caseComponent(component);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.SOURCE_LOCATOR:
    {
      SourceLocator sourceLocator = (SourceLocator)theEObject;
      T result = caseSourceLocator(sourceLocator);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.MANUAL_SOURCE_LOCATOR:
    {
      ManualSourceLocator manualSourceLocator = (ManualSourceLocator)theEObject;
      T result = caseManualSourceLocator(manualSourceLocator);
      if (result == null)
      {
        result = caseSourceLocator(manualSourceLocator);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.AUTOMATIC_SOURCE_LOCATOR:
    {
      AutomaticSourceLocator automaticSourceLocator = (AutomaticSourceLocator)theEObject;
      T result = caseAutomaticSourceLocator(automaticSourceLocator);
      if (result == null)
      {
        result = caseSourceLocator(automaticSourceLocator);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.COMPONENT_EXTENSION:
    {
      ComponentExtension componentExtension = (ComponentExtension)theEObject;
      T result = caseComponentExtension(componentExtension);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.COMPONENT_DEFINITION:
    {
      ComponentDefinition componentDefinition = (ComponentDefinition)theEObject;
      T result = caseComponentDefinition(componentDefinition);
      if (result == null)
      {
        result = caseComponentExtension(componentDefinition);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.MAVEN_IMPORT_TASK:
    {
      MavenImportTask mavenImportTask = (MavenImportTask)theEObject;
      T result = caseMavenImportTask(mavenImportTask);
      if (result == null)
      {
        result = caseSetupTask(mavenImportTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.TARGLET_IMPORT_TASK:
    {
      TargletImportTask targletImportTask = (TargletImportTask)theEObject;
      T result = caseTargletImportTask(targletImportTask);
      if (result == null)
      {
        result = caseSetupTask(targletImportTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.TARGLET_TASK:
    {
      TargletTask targletTask = (TargletTask)theEObject;
      T result = caseTargletTask(targletTask);
      if (result == null)
      {
        result = caseSetupTask(targletTask);
      }
      if (result == null)
      {
        result = caseTargletData(targletTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.TARGLET:
    {
      Targlet targlet = (Targlet)theEObject;
      T result = caseTarglet(targlet);
      if (result == null)
      {
        result = caseTargletData(targlet);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.TARGLET_DATA:
    {
      TargletData targletData = (TargletData)theEObject;
      T result = caseTargletData(targletData);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.REPOSITORY_LIST:
    {
      RepositoryList repositoryList = (RepositoryList)theEObject;
      T result = caseRepositoryList(repositoryList);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.PROJECTS_IMPORT_TASK:
    {
      ProjectsImportTask projectsImportTask = (ProjectsImportTask)theEObject;
      T result = caseProjectsImportTask(projectsImportTask);
      if (result == null)
      {
        result = caseSetupTask(projectsImportTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.PROJECT_SET_IMPORT_TASK:
    {
      ProjectSetImportTask projectSetImportTask = (ProjectSetImportTask)theEObject;
      T result = caseProjectSetImportTask(projectSetImportTask);
      if (result == null)
      {
        result = caseSetupTask(projectSetImportTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.TARGET_PLATFORM_TASK:
    {
      TargetPlatformTask targetPlatformTask = (TargetPlatformTask)theEObject;
      T result = caseTargetPlatformTask(targetPlatformTask);
      if (result == null)
      {
        result = caseSetupTask(targetPlatformTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.API_BASELINE_TASK:
    {
      ApiBaselineTask apiBaselineTask = (ApiBaselineTask)theEObject;
      T result = caseApiBaselineTask(apiBaselineTask);
      if (result == null)
      {
        result = caseSetupTask(apiBaselineTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.ECLIPSE_PREFERENCE_TASK:
    {
      EclipsePreferenceTask eclipsePreferenceTask = (EclipsePreferenceTask)theEObject;
      T result = caseEclipsePreferenceTask(eclipsePreferenceTask);
      if (result == null)
      {
        result = caseSetupTask(eclipsePreferenceTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.FILE_ASSOCIATION_TASK:
    {
      FileAssociationTask fileAssociationTask = (FileAssociationTask)theEObject;
      T result = caseFileAssociationTask(fileAssociationTask);
      if (result == null)
      {
        result = caseSetupTask(fileAssociationTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.FILE_ASSOCIATIONS_TASK:
    {
      FileAssociationsTask fileAssociationsTask = (FileAssociationsTask)theEObject;
      T result = caseFileAssociationsTask(fileAssociationsTask);
      if (result == null)
      {
        result = caseSetupTask(fileAssociationsTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.FILE_MAPPING:
    {
      FileMapping fileMapping = (FileMapping)theEObject;
      T result = caseFileMapping(fileMapping);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.FILE_EDITOR:
    {
      FileEditor fileEditor = (FileEditor)theEObject;
      T result = caseFileEditor(fileEditor);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.WORKING_SET_TASK:
    {
      WorkingSetTask workingSetTask = (WorkingSetTask)theEObject;
      T result = caseWorkingSetTask(workingSetTask);
      if (result == null)
      {
        result = caseSetupTask(workingSetTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.RESOURCE_COPY_TASK:
    {
      ResourceCopyTask resourceCopyTask = (ResourceCopyTask)theEObject;
      T result = caseResourceCopyTask(resourceCopyTask);
      if (result == null)
      {
        result = caseSetupTask(resourceCopyTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.RESOURCE_CREATION_TASK:
    {
      ResourceCreationTask resourceCreationTask = (ResourceCreationTask)theEObject;
      T result = caseResourceCreationTask(resourceCreationTask);
      if (result == null)
      {
        result = caseSetupTask(resourceCreationTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.TEXT_MODIFY_TASK:
    {
      TextModifyTask textModifyTask = (TextModifyTask)theEObject;
      T result = caseTextModifyTask(textModifyTask);
      if (result == null)
      {
        result = caseSetupTask(textModifyTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.TEXT_MODIFICATION:
    {
      TextModification textModification = (TextModification)theEObject;
      T result = caseTextModification(textModification);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.KEY_BINDING_TASK:
    {
      KeyBindingTask keyBindingTask = (KeyBindingTask)theEObject;
      T result = caseKeyBindingTask(keyBindingTask);
      if (result == null)
      {
        result = caseSetupTask(keyBindingTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.KEY_BINDING_CONTEXT:
    {
      KeyBindingContext keyBindingContext = (KeyBindingContext)theEObject;
      T result = caseKeyBindingContext(keyBindingContext);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.COMMAND_PARAMETER:
    {
      CommandParameter commandParameter = (CommandParameter)theEObject;
      T result = caseCommandParameter(commandParameter);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.MYLYN_QUERY_TASK:
    {
      MylynQueryTask mylynQueryTask = (MylynQueryTask)theEObject;
      T result = caseMylynQueryTask(mylynQueryTask);
      if (result == null)
      {
        result = caseSetupTask(mylynQueryTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.MYLYN_QUERIES_TASK:
    {
      MylynQueriesTask mylynQueriesTask = (MylynQueriesTask)theEObject;
      T result = caseMylynQueriesTask(mylynQueriesTask);
      if (result == null)
      {
        result = caseSetupTask(mylynQueriesTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.QUERY:
    {
      Query query = (Query)theEObject;
      T result = caseQuery(query);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.QUERY_ATTRIBUTE:
    {
      @SuppressWarnings("unchecked")
      Map.Entry<String, String> queryAttribute = (Map.Entry<String, String>)theEObject;
      T result = caseQueryAttribute(queryAttribute);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.MYLYN_BUILDS_TASK:
    {
      MylynBuildsTask mylynBuildsTask = (MylynBuildsTask)theEObject;
      T result = caseMylynBuildsTask(mylynBuildsTask);
      if (result == null)
      {
        result = caseSetupTask(mylynBuildsTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.BUILD_PLAN:
    {
      BuildPlan buildPlan = (BuildPlan)theEObject;
      T result = caseBuildPlan(buildPlan);
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    case SetupPackage.JRE_TASK:
    {
      JRETask jreTask = (JRETask)theEObject;
      T result = caseJRETask(jreTask);
      if (result == null)
      {
        result = caseSetupTask(jreTask);
      }
      if (result == null)
      {
        result = defaultCase(theEObject);
      }
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Meta Index</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Meta Index</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMetaIndex(MetaIndex object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Index</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Index</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIndex(Index object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Eclipse</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Eclipse</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEclipse(Eclipse object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Preferences</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Preferences</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePreferences(Preferences object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Link Location Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Link Location Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLinkLocationTask(LinkLocationTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Task Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Task Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetupTaskContainer(SetupTaskContainer object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Scope Root</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Scope Root</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScopeRoot(ScopeRoot object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Eclipse Preference Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Eclipse Preference Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEclipsePreferenceTask(EclipsePreferenceTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>P2 Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>P2 Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseP2Task(P2Task object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Installable Unit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Installable Unit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInstallableUnit(InstallableUnit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>P2 Repository</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>P2 Repository</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseP2Repository(P2Repository object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfiguration(Configuration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Project</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Project</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProject(Project object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Branch</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Branch</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBranch(Branch object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Api Baseline Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Api Baseline Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseApiBaselineTask(ApiBaselineTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Git Clone Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Git Clone Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGitCloneTask(GitCloneTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Project Set Import Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Project Set Import Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProjectSetImportTask(ProjectSetImportTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Setup</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Setup</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetup(Setup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetupTask(SetupTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Working Set Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Working Set Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWorkingSetTask(WorkingSetTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Resource Copy Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Resource Copy Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseResourceCopyTask(ResourceCopyTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Text Modify Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Text Modify Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTextModifyTask(TextModifyTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Text Modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Text Modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTextModification(TextModification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Key Binding Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Key Binding Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseKeyBindingTask(KeyBindingTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Key Binding Context</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Key Binding Context</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseKeyBindingContext(KeyBindingContext object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Command Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Command Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCommandParameter(CommandParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mylyn Query Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mylyn Query Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMylynQueryTask(MylynQueryTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mylyn Queries Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mylyn Queries Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMylynQueriesTask(MylynQueriesTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Query</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Query</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseQuery(Query object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Query Attribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Query Attribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseQueryAttribute(Map.Entry<String, String> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mylyn Builds Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mylyn Builds Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMylynBuildsTask(MylynBuildsTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Build Plan</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Build Plan</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBuildPlan(BuildPlan object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JRE Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JRE Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseJRETask(JRETask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Maven Import Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Maven Import Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMavenImportTask(MavenImportTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Component Extension</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Extension</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComponentExtension(ComponentExtension object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Component Definition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component Definition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComponentDefinition(ComponentDefinition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Targlet Import Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Targlet Import Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTargletImportTask(TargletImportTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>File Association Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>File Association Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFileAssociationTask(FileAssociationTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>File Associations Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>File Associations Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFileAssociationsTask(FileAssociationsTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>File Mapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>File Mapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFileMapping(FileMapping object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>File Editor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>File Editor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFileEditor(FileEditor object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Target Platform Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Target Platform Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTargetPlatformTask(TargetPlatformTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Automatic Source Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Automatic Source Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAutomaticSourceLocator(AutomaticSourceLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Targlet Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Targlet Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTargletTask(TargletTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Targlet</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Targlet</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTarglet(Targlet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Targlet Data</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Targlet Data</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTargletData(TargletData object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Repository List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Repository List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRepositoryList(RepositoryList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Projects Import Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Projects Import Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProjectsImportTask(ProjectsImportTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Redirection Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Redirection Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRedirectionTask(RedirectionTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Manual Source Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Manual Source Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseManualSourceLocator(ManualSourceLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Context Variable Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Context Variable Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseContextVariableTask(ContextVariableTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variable Choice</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Choice</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariableChoice(VariableChoice object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Resource Creation Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Resource Creation Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseResourceCreationTask(ResourceCreationTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Materialization Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Materialization Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMaterializationTask(MaterializationTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Source Locator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Source Locator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSourceLocator(SourceLocator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Basic Materialization Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Basic Materialization Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBasicMaterializationTask(BasicMaterializationTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Component</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComponent(Component object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Eclipse Ini Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Eclipse Ini Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEclipseIniTask(EclipseIniTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compound Setup Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compound Setup Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompoundSetupTask(CompoundSetupTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Configurable Item</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Configurable Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConfigurableItem(ConfigurableItem object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Buckminster Import Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Buckminster Import Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBuckminsterImportTask(BuckminsterImportTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} // SetupSwitch
