/**
 * JmsUtils.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.utils;

import com.mg.framework.api.jms.MessageCreator;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

/**
 * Утилиты JMS
 *
 * @author Oleg V. Safonov
 * @version $Id: JmsUtils.java,v 1.3 2008/04/25 07:23:42 safonov Exp $
 */
public class JmsUtils {
  private static String factoryName = "ConnectionFactory";

  /**
   * отправка сообщения в очередь
   *
   * @param queueName      имя очереди
   * @param messageCreator создатель сообщения
   * @throws Exception при любых проблемах отправки
   */
  public static void sendObjectMessageToQueue(String queueName, MessageCreator messageCreator) throws Exception {
    if (messageCreator == null)
      throw new IllegalArgumentException("Message creator is null");

    QueueConnection cnn = null;
    QueueSession session = null;
    InitialContext ctx = null;
    try {
      ctx = new InitialContext();
      Queue queue = (Queue) ctx.lookup(queueName);
      QueueConnectionFactory factory = (QueueConnectionFactory) ctx.lookup(factoryName);
      cnn = factory.createQueueConnection();
      session = cnn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
      QueueSender sender = session.createSender(queue);
      sender.send(messageCreator.create(session));
    } finally {
      if (session != null)
        session.close();
      if (cnn != null)
        cnn.close();
      if (ctx != null)
        ctx.close();
    }
  }

  /**
   * отправка сообщения в тему
   *
   * @param topicName      имя темы
   * @param messageCreator создатель сообщения
   * @throws Exception при любых проблемах отправки
   */
  public static void sendObjectMessageToTopic(String topicName, MessageCreator messageCreator) throws Exception {
    if (messageCreator == null)
      throw new IllegalArgumentException("Message creator is null");

    TopicConnection cnn = null;
    TopicSession session = null;
    InitialContext ctx = null;
    try {
      ctx = new InitialContext();
      Topic topic = (Topic) ctx.lookup(topicName);
      TopicConnectionFactory factory = (TopicConnectionFactory) ctx.lookup(factoryName);
      cnn = factory.createTopicConnection();
      session = cnn.createTopicSession(false, QueueSession.AUTO_ACKNOWLEDGE);
      TopicPublisher publisher = session.createPublisher(topic);
      publisher.publish(messageCreator.create(session));
    } finally {
      if (session != null)
        session.close();
      if (cnn != null)
        cnn.close();
      if (ctx != null)
        ctx.close();
    }
  }

  /**
   * отправка объектного сообщения в очередь
   *
   * @param queueName   имя очереди
   * @param messageBody сообщение
   * @throws Exception при любых проблемах отправки
   */
  public static void sendObjectMessageToQueue(String queueName, Serializable messageBody) throws Exception {
    sendObjectMessageToQueue(queueName, new ObjectMessageCreator(messageBody));
  }

  /**
   * отправка объектного сообщения в тему
   *
   * @param topicName   имя темы
   * @param messageBody сообщение
   * @throws Exception при любых проблемах отправки
   */
  public static void sendObjectMessageToTopic(String topicName, Serializable messageBody) throws Exception {
    sendObjectMessageToTopic(topicName, new ObjectMessageCreator(messageBody));
  }

  /**
   * создатель объектного сообщения
   */
  static class ObjectMessageCreator implements MessageCreator {
    private Serializable messageBody;

    private ObjectMessageCreator(Serializable messageBody) {
      super();
      this.messageBody = messageBody;
    }

    public Message create(Session session) throws JMSException {
      return session.createObjectMessage(messageBody);
    }

  }

}
