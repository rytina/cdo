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
package org.eclipse.emf.cdo.releng.internal.setup.util;

import org.eclipse.emf.cdo.releng.setup.SetupConstants;
import org.eclipse.emf.cdo.releng.setup.util.FileUtil;

import org.eclipse.net4j.util.io.IOUtil;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;

import org.eclipse.ecf.core.ContainerCreateException;
import org.eclipse.ecf.core.ContainerFactory;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.core.security.ConnectContextFactory;
import org.eclipse.ecf.core.util.Proxy;
import org.eclipse.ecf.filetransfer.IFileTransferListener;
import org.eclipse.ecf.filetransfer.IRetrieveFileTransferContainerAdapter;
import org.eclipse.ecf.filetransfer.IRetrieveFileTransferOptions;
import org.eclipse.ecf.filetransfer.IncomingFileTransferException;
import org.eclipse.ecf.filetransfer.events.IFileTransferEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveDoneEvent;
import org.eclipse.ecf.filetransfer.events.IIncomingFileTransferReceiveStartEvent;
import org.eclipse.ecf.provider.filetransfer.identity.FileTransferID;
import org.eclipse.ecf.provider.filetransfer.identity.FileTransferNamespace;
import org.eclipse.ecf.provider.filetransfer.util.ProxySetupHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @author Eike Stepper
 */
public class ECFURIHandlerImpl extends URIHandlerImpl
{
  @Override
  public Map<String, ?> getAttributes(URI uri, Map<?, ?> options)
  {
    if (uri.scheme().startsWith("http"))
    {
      Set<String> requestedAttributes = getRequestedAttributes(options);
      if (requestedAttributes != null && requestedAttributes.contains(URIConverter.ATTRIBUTE_READ_ONLY)
          && requestedAttributes.size() == 1)
      {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put(URIConverter.ATTRIBUTE_READ_ONLY, true);
        return result;
      }
    }

    return super.getAttributes(uri, options);
  }

  @Override
  public boolean exists(URI uri, Map<?, ?> options)
  {
    return super.exists(uri, options);
  }

  @Override
  public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
  {
    File cacheFile = new File(SetupConstants.CACHE_FOLDER, FileUtil.encodeFileName(uri.toString()));

    String username;
    String password;

    String uriString = uri.toString();
    Proxy proxy = ProxySetupHelper.getProxy(uriString);
    if (proxy != null)
    {
      username = proxy.getUsername();
      password = proxy.getPassword();
    }
    else
    {
      username = null;
      password = null;
    }

    IContainer container = createContainer();

    for (int i = 0;; ++i)
    {
      IRetrieveFileTransferContainerAdapter fileTransfer = (IRetrieveFileTransferContainerAdapter)container
          .getAdapter(IRetrieveFileTransferContainerAdapter.class);

      if (proxy != null)
      {
        fileTransfer.setProxy(proxy);

        if (username != null)
        {
          fileTransfer.setConnectContextForAuthentication(ConnectContextFactory.createUsernamePasswordConnectContext(
              username, password));
        }
        else if (password != null)
        {
          fileTransfer.setConnectContextForAuthentication(ConnectContextFactory.createPasswordConnectContext(password));
        }
      }

      FileTransferListener transferListener = new FileTransferListener();

      try
      {
        FileTransferID fileTransferID = new FileTransferID(new FileTransferNamespace(), new java.net.URI(uriString));
        Map<Object, Object> requestOptions = new HashMap<Object, Object>();
        requestOptions.put(IRetrieveFileTransferOptions.CONNECT_TIMEOUT, 10000);
        requestOptions.put(IRetrieveFileTransferOptions.READ_TIMEOUT, 10000);
        fileTransfer.sendRetrieveRequest(fileTransferID, transferListener, Collections.emptyMap());
      }
      catch (URISyntaxException ex)
      {
        throw new IOException(ex);
      }
      catch (IncomingFileTransferException ex)
      {
        throw new IOException(ex);
      }

      try
      {
        transferListener.receiveLatch.await();
      }
      catch (InterruptedException ex)
      {
        throw new IOException(ex);
      }

      if (transferListener.exception != null)
      {
        if (!(transferListener.exception.getCause() instanceof SocketTimeoutException) || i > 2)
        {
          if (cacheFile.isFile())
          {
            return new FileInputStream(cacheFile);
          }

          throw new IOException(transferListener.exception);
        }

        continue;
      }

      byte[] bytes = transferListener.out.toByteArray();

      cacheFile.getParentFile().mkdirs();
      IOUtil.writeFile(cacheFile, bytes);

      return new ByteArrayInputStream(bytes);
    }
  }

  private IContainer createContainer() throws IOException
  {
    try
    {
      return ContainerFactory.getDefault().createContainer();
    }
    catch (ContainerCreateException ex)
    {
      throw new IOException(ex);
    }
  }

  /**
   * @author Eike Stepper
   */
  private static final class FileTransferListener implements IFileTransferListener
  {
    public final CountDownLatch receiveLatch = new CountDownLatch(1);

    public Exception exception;

    public ByteArrayOutputStream out;

    public void handleTransferEvent(IFileTransferEvent event)
    {
      if (event instanceof IIncomingFileTransferReceiveStartEvent)
      {
        out = new ByteArrayOutputStream();

        try
        {
          ((IIncomingFileTransferReceiveStartEvent)event).receive(out);
        }
        catch (IOException ex)
        {
          exception = ex;
        }
      }
      else if (event instanceof IIncomingFileTransferReceiveDoneEvent)
      {
        IIncomingFileTransferReceiveDoneEvent done = (IIncomingFileTransferReceiveDoneEvent)event;
        Exception ex = done.getException();
        if (ex != null)
        {
          exception = ex;
        }

        receiveLatch.countDown();
      }
    }
  }
}
