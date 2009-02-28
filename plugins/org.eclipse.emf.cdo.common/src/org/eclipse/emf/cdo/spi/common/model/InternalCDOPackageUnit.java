package org.eclipse.emf.cdo.spi.common.model;

import org.eclipse.emf.cdo.common.io.CDODataInput;
import org.eclipse.emf.cdo.common.io.CDODataOutput;
import org.eclipse.emf.cdo.common.model.CDOPackageUnit;

import java.io.IOException;

/**
 * @author Eike Stepper
 */
public interface InternalCDOPackageUnit extends CDOPackageUnit
{
  public InternalCDOPackageUnitManager getManager();

  public void setManager(InternalCDOPackageUnitManager manager);

  public void setID(String id);

  public void setTimeStamp(long timeStamp);

  public InternalCDOPackageInfo[] getPackageInfos();

  public void setPackageInfos(InternalCDOPackageInfo[] packageInfos);

  public void load();

  public void write(CDODataOutput out) throws IOException;

  public void read(CDODataInput in) throws IOException;
}
