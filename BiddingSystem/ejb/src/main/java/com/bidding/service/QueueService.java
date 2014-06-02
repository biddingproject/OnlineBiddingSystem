package com.bidding.service;

import javax.ejb.Stateless;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.bidding.model.Employee;

@Stateless
public class QueueService {

	public void publishMesssage(){
		try {
			final String QUEUE_LOOKUP = "java:jboss/activemq/queue/TestQueue";
			final String CONNECTION_FACTORY = "java:jboss/activemq/QueueConnectionFactory";

			Context context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context
					.lookup(CONNECTION_FACTORY);
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+connection.getClientID());

			Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);
			QueueSender sender = session.createSender(queue);

			// 1. Sending TextMessage to the Queue
			TextMessage message = session.createTextMessage();
			message.setText("Hello EJB3 MDB Queue!!!");
			sender.send(message);
			System.out.println("1. Sent TextMessage to the Queue");

			// 2. Sending ObjectMessage to the Queue
			ObjectMessage objMsg = session.createObjectMessage();

			Employee employee = new Employee();
			employee.setId(2163);
			employee.setName("Kumar");
			employee.setDesignation("CTO");
			employee.setSalary(100000);
			objMsg.setObject(employee);
			sender.send(objMsg);
			System.out.println("2. Sent ObjectMessage to the Queue");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
