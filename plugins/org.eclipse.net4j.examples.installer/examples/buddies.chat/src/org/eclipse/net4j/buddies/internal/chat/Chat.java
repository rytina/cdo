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
package org.eclipse.net4j.buddies.internal.chat;

import org.eclipse.net4j.buddies.chat.IChat;
import org.eclipse.net4j.buddies.chat.IComment;
import org.eclipse.net4j.buddies.common.IMessage;
import org.eclipse.net4j.buddies.spi.common.Facility;
import org.eclipse.net4j.util.event.IListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eike Stepper
 */
public class Chat extends Facility implements IChat
{
  private List<IComment> comments = new ArrayList<IComment>();

  public Chat()
  {
    super(TYPE);
  }

  public IComment[] getComments()
  {
    synchronized (comments)
    {
      return comments.toArray(new IComment[comments.size()]);
    }
  }

  public void sendComment(String text)
  {
    TextMessage message = new TextMessage(text);
    sendMessage(message);
    addComment(message.getSenderID(), text);
  }

  @Override
  public void handleMessage(IMessage message)
  {
    if (message instanceof TextMessage)
    {
      addComment(message.getSenderID(), ((TextMessage)message).getText());
    }
  }

  protected void addComment(String senderID, String text)
  {
    Comment comment = new Comment(System.currentTimeMillis(), senderID, text);
    synchronized (comments)
    {
      comments.add(comment);
    }

    IListener[] listeners = getListeners();
    if (listeners != null)
    {
      fireEvent(new CommentEvent(this, comment), listeners);
    }
  }
}