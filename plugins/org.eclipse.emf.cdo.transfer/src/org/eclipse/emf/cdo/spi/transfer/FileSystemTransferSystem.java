/*
 * Copyright (c) 2004 - 2012 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.cdo.spi.transfer;

import org.eclipse.emf.cdo.transfer.CDOTransferElement;
import org.eclipse.emf.cdo.transfer.CDOTransferSystem;

import org.eclipse.net4j.util.io.IORuntimeException;
import org.eclipse.net4j.util.io.IOUtil;

import org.eclipse.emf.common.util.URI;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;

/**
 * @author Eike Stepper
 * @since 4.2
 */
public class FileSystemTransferSystem extends CDOTransferSystem
{
  public static final String TYPE = "fs";

  public FileSystemTransferSystem()
  {
    super(false);
  }

  @Override
  public String getType()
  {
    return TYPE;
  }

  @Override
  public URI getURI(IPath path)
  {
    return URI.createFileURI(path.toOSString());
  }

  @Override
  public CDOTransferElement getElement(IPath path)
  {
    File file = getFile(path);
    if (file.exists())
    {
      return new Element(this, file);
    }

    return null;
  }

  @Override
  public CDOTransferElement getElement(URI uri)
  {
    if (uri.isFile())
    {
      return getElement(uri.path());
    }

    return null;
  }

  @Override
  public void createFolder(IPath path)
  {
    File file = getFile(path);
    mkDir(file);
  }

  @Override
  public void createBinary(IPath path, InputStream source)
  {
    File file = getFile(path);
    mkParent(file);

    OutputStream target = null;

    try
    {
      target = new FileOutputStream(file);
      IOUtil.copy(source, target);
    }
    catch (IOException ex)
    {
      throw new IORuntimeException();
    }
    finally
    {
      IOUtil.close(target);
    }
  }

  @Override
  public void createText(IPath path, InputStream source, String encoding)
  {
    File file = getFile(path);
    mkParent(file);

    Writer target = null;

    try
    {
      target = new FileWriter(file);
      IOUtil.copyCharacter(new InputStreamReader(source, encoding), target);
    }
    catch (IOException ex)
    {
      throw new IORuntimeException();
    }
    finally
    {
      IOUtil.close(target);
    }
  }

  @Override
  public String toString()
  {
    return "File System";
  }

  protected File getFile(IPath path)
  {
    return new File(path.toOSString());
  }

  protected void mkParent(File file)
  {
    File parent = file.getParentFile();
    if (parent != null)
    {
      mkDir(parent);
    }
  }

  protected void mkDir(File file)
  {
    if (file.exists())
    {
      if (!file.isDirectory())
      {
        throw new IORuntimeException("Not a folder " + file);
      }
    }
    else
    {
      if (!file.mkdirs())
      {
        throw new IORuntimeException("Could not create folder " + file);
      }
    }
  }

  /**
   * @author Eike Stepper
   */
  private static class Element extends CDOTransferElement
  {
    private File file;

    public Element(CDOTransferSystem system, File file)
    {
      super(system);
      this.file = file.getAbsoluteFile();
    }

    @Override
    public Object getNativeObject()
    {
      return file;
    }

    @Override
    public IPath getPath()
    {
      return new Path(file.getPath());
    }

    @Override
    public boolean isDirectory()
    {
      return file.isDirectory();
    }

    @Override
    protected CDOTransferElement[] doGetChildren()
    {
      File[] children = file.listFiles();
      CDOTransferElement[] result = new Element[children.length];

      for (int i = 0; i < children.length; i++)
      {
        File child = children[i];
        result[i] = new Element(getSystem(), child);
      }

      return result;
    }

    @Override
    protected InputStream doOpenInputStream()
    {
      try
      {
        return new FileInputStream(file);
      }
      catch (IOException ex)
      {
        throw new IORuntimeException(ex);
      }
    }
  }
}