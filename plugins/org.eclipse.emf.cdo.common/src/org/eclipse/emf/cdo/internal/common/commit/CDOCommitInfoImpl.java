/*
 * Copyright (c) 2010-2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.internal.common.commit;

import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.commit.CDOChangeKind;
import org.eclipse.emf.cdo.common.commit.CDOChangeSetData;
import org.eclipse.emf.cdo.common.commit.CDOCommitData;
import org.eclipse.emf.cdo.common.commit.CDOCommitInfo;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.model.CDOPackageUnit;
import org.eclipse.emf.cdo.common.revision.CDOIDAndVersion;
import org.eclipse.emf.cdo.common.revision.CDORevisionKey;
import org.eclipse.emf.cdo.common.util.CDOCommonUtil;
import org.eclipse.emf.cdo.internal.common.branch.CDOBranchPointImpl;
import org.eclipse.emf.cdo.spi.common.commit.InternalCDOCommitInfoManager;

import org.eclipse.net4j.util.CheckUtil;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @author Eike Stepper
 */
public class CDOCommitInfoImpl extends CDOBranchPointImpl implements CDOCommitInfo
{
  // private static final CDOCommitInfo[] NO_PARENTS = {};

  private InternalCDOCommitInfoManager commitInfoManager;

  private long previousTimeStamp;

  private String userID;

  private String comment;

  private CDOCommitData commitData;

  // private transient CDOCommitInfo[] parents;

  public CDOCommitInfoImpl(InternalCDOCommitInfoManager commitInfoManager, CDOBranch branch, long timeStamp,
      long previousTimeStamp, String userID, String comment, CDOCommitData commitData)
  {
    super(branch, timeStamp);
    CheckUtil.checkArg(commitInfoManager, "commitInfoManager"); //$NON-NLS-1$
    this.commitInfoManager = commitInfoManager;
    this.previousTimeStamp = previousTimeStamp;
    this.userID = userID;
    this.comment = comment;
    this.commitData = commitData;
  }

  public InternalCDOCommitInfoManager getCommitInfoManager()
  {
    return commitInfoManager;
  }

  // public synchronized CDOCommitInfo[] getParents()
  // {
  // if (parents == null)
  // {
  // CDOCommitInfo previousCommitInfo = commitInfoManager.getCommitInfo(previousTimeStamp);
  // if (previousCommitInfo != null)
  // {
  // parents = new CDOCommitInfo[] { previousCommitInfo };
  // }
  // else
  // {
  // parents = NO_PARENTS;
  // }
  // }
  //
  // return parents;
  // }

  public long getPreviousTimeStamp()
  {
    return previousTimeStamp;
  }

  public String getUserID()
  {
    return userID;
  }

  public String getComment()
  {
    return comment;
  }

  public boolean isInitialCommit()
  {
    return CDOCommonUtil.SYSTEM_USER_ID.equals(userID);
  }

  public boolean isEmpty()
  {
    loadCommitDataIfNeeded();
    return commitData.isEmpty();
  }

  public CDOChangeSetData copy()
  {
    return commitData == null ? null : commitData.copy();
  }

  public void merge(CDOChangeSetData changeSetData)
  {
    loadCommitDataIfNeeded();
    commitData.merge(changeSetData);
  }

  public synchronized List<CDOPackageUnit> getNewPackageUnits()
  {
    loadCommitDataIfNeeded();
    return commitData.getNewPackageUnits();
  }

  public synchronized List<CDOIDAndVersion> getNewObjects()
  {
    loadCommitDataIfNeeded();
    return commitData.getNewObjects();
  }

  public synchronized List<CDORevisionKey> getChangedObjects()
  {
    loadCommitDataIfNeeded();
    return commitData.getChangedObjects();
  }

  public synchronized List<CDOIDAndVersion> getDetachedObjects()
  {
    loadCommitDataIfNeeded();
    return commitData.getDetachedObjects();
  }

  public Map<CDOID, CDOChangeKind> getChangeKinds()
  {
    loadCommitDataIfNeeded();
    return commitData.getChangeKinds();
  }

  public CDOChangeKind getChangeKind(CDOID id)
  {
    loadCommitDataIfNeeded();
    return commitData.getChangeKind(id);
  }

  @Override
  public int hashCode()
  {
    long timeStamp = getTimeStamp();

    final int prime = 31;
    int result = 0;
    result = prime * result + (commitInfoManager == null ? 0 : commitInfoManager.hashCode());
    result = prime * result + (int)(timeStamp ^ timeStamp >>> 32);
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }

    if (!(obj instanceof CDOCommitInfoImpl))
    {
      return false;
    }

    CDOCommitInfoImpl other = (CDOCommitInfoImpl)obj;
    if (commitInfoManager != other.commitInfoManager)
    {
      return false;
    }

    if (getTimeStamp() != other.getTimeStamp())
    {
      return false;
    }

    return true;
  }

  @Override
  public String toString()
  {
    return toString(this);
  }

  public static String toString(CDOCommitInfo commitInfo)
  {
    String data = null;
    if (commitInfo.isCommitDataLoaded())
    {
      data = CDOCommitDataImpl.toString(commitInfo);
    }

    long t = commitInfo.getTimeStamp();
    String timeStamp = CDOCommonUtil.formatTimeStamp(t) + " (" + t + ")";

    return MessageFormat
        .format(
            "CommitInfo[{0}, {1}, {2}, {3}, {4}]", timeStamp, commitInfo.getBranch(), commitInfo.getUserID(), commitInfo.getComment(), data); //$NON-NLS-1$
  }

  public synchronized boolean isCommitDataLoaded()
  {
    return commitData != null;
  }

  private synchronized void loadCommitDataIfNeeded()
  {
    if (commitData == null)
    {
      commitData = commitInfoManager.getCommitInfoLoader().loadCommitData(getTimeStamp());
    }
  }
}
