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
package org.eclipse.emf.cdo.releng.internal.setup.targlets;

import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.p2.metadata.OSGiVersion;
import org.eclipse.equinox.internal.p2.metadata.RequiredCapability;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.MetadataFactory;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.eclipse.BundlesAction;
import org.eclipse.equinox.p2.publisher.eclipse.Feature;
import org.eclipse.equinox.p2.publisher.eclipse.FeaturesAction;
import org.eclipse.osgi.service.resolver.BundleDescription;

import java.io.File;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;

/**
 * @author Eike Stepper
 */
public interface IUGenerator
{
  public IInstallableUnit generateIU(File location) throws Exception;

  /**
   * @author Eike Stepper
   */
  public static final class BundleIUGenerator extends BundlesAction implements IUGenerator
  {
    public static final IUGenerator INSTANCE = new BundleIUGenerator();

    private BundleIUGenerator()
    {
      super((File[])null);
      setPublisherInfo(new PublisherInfo());
    }

    public IInstallableUnit generateIU(File location) throws Exception
    {
      Dictionary<String, String> manifest = loadManifest(location);
      if (manifest == null)
      {
        return null;
      }

      String version = manifest.get(org.osgi.framework.Constants.BUNDLE_VERSION);
      if (version.endsWith(".qualifier"))
      {
        version = version.substring(0, version.length() - ".qualifier".length());
        manifest.put(org.osgi.framework.Constants.BUNDLE_VERSION, version);
      }

      BundleDescription description = createBundleDescription(manifest, location);
      if (description == null)
      {
        return null;
      }

      IInstallableUnit iu = createBundleIU(description, null, info);
      if (iu instanceof InstallableUnit)
      {
        ((InstallableUnit)iu).setArtifacts(new IArtifactKey[0]);
      }

      return iu;
    }
  }

  /**
   * @author Eike Stepper
   */
  public static final class FeatureIUGenerator extends FeaturesAction implements IUGenerator
  {
    public static final IUGenerator INSTANCE = new FeatureIUGenerator();

    private FeatureIUGenerator()
    {
      super((File[])null);
      setPublisherInfo(new PublisherInfo());
    }

    public IInstallableUnit generateIU(File location) throws Exception
    {
      Feature[] features = getFeatures(new File[] { location });
      if (features == null || features.length == 0)
      {
        return null;
      }

      Feature feature = features[0];

      String version = feature.getVersion();
      if (version.endsWith(".qualifier"))
      {
        version = version.substring(0, version.length() - ".qualifier".length());
        feature.setVersion(version);
      }

      List<IInstallableUnit> childIUs = Collections.emptyList();
      InstallableUnit iu = (InstallableUnit)createGroupIU(feature, childIUs, info);
      List<IRequirement> requirements = iu.getRequirements();

      String licenseFeature = feature.getLicenseFeature();
      String licenseFeatureVersion = feature.getLicenseFeatureVersion();
      boolean hasLicenseFeature = licenseFeature != null && licenseFeatureVersion != null;

      int size = requirements.size();
      IRequirement[] newRequirements = new IRequirement[size + (hasLicenseFeature ? 1 : 0)];

      if (hasLicenseFeature)
      {
        VersionRange osgiRange;

        Version osgiVersion = OSGiVersion.create(licenseFeatureVersion);
        if (osgiVersion.equals(OSGiVersion.emptyVersion))
        {
          osgiRange = VersionRange.emptyRange;
        }
        else
        {
          osgiRange = new VersionRange(osgiVersion, true, osgiVersion, true);

          VersionRange adjustedRange = adjustQualifier(osgiRange);
          if (adjustedRange != null)
          {
            osgiRange = adjustedRange;
          }
        }

        IRequirement requirement = MetadataFactory.createRequirement(IInstallableUnit.NAMESPACE_IU_ID, licenseFeature
            + ".feature.group", osgiRange, null, true, false);
        newRequirements[size] = requirement;
      }

      // Adjust childIU requirements to support possible .qualifier specifications
      for (int i = 0; i < size; i++)
      {
        IRequirement requirement = requirements.get(i);
        if (requirement instanceof RequiredCapability)
        {
          RequiredCapability capability = (RequiredCapability)requirement;
          VersionRange range = adjustQualifier(capability.getRange());
          if (range != null)
          {
            requirement = MetadataFactory.createRequirement(capability.getNamespace(), capability.getName(), range,
                capability.getFilter(), capability.getMin() == 0, capability.getMax() > 1);
          }
        }

        newRequirements[i] = requirement;
      }

      iu.setRequiredCapabilities(newRequirements);
      return iu;
    }

    private static VersionRange adjustQualifier(VersionRange range)
    {
      Version minimum = range.getMinimum();
      if (minimum instanceof OSGiVersion)
      {
        OSGiVersion osgiVersion = (OSGiVersion)minimum;
        if (osgiVersion.equals(range.getMaximum()))
        {
          if ("qualifier".equals(osgiVersion.getQualifier()))
          {
            minimum = OSGiVersion.createOSGi(osgiVersion.getMajor(), osgiVersion.getMinor(), osgiVersion.getMicro());
            Version maximum = OSGiVersion.createOSGi(osgiVersion.getMajor(), osgiVersion.getMinor(),
                osgiVersion.getMicro() + 1);

            return new VersionRange(minimum, true, maximum, false);
          }
        }
      }

      return null;
    }
  }
}
