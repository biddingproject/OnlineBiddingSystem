package com.bidding.async_processor;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bidding.data.CustomerRepository;
import com.bidding.data.ItemListRepository;
import com.bidding.model.Transaction;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "activemq/queue/TestQueue") })
public class EmailProcessor implements MessageListener {

	public EmailProcessor() {
	}

	@Inject
	ItemListRepository itemListRepository;

	@Inject
	CustomerRepository customerRepository;

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;

	@Override
	public void onMessage(Message message) {
		if (message instanceof Transaction) {
			try {
				System.out.println("Queue: I received a TextMessage at "
						+ new Date());
				ObjectMessage msg = (ObjectMessage) message;
				Transaction transaction = (Transaction) msg.getObject();
				String sellerEmail = itemListRepository
						.getSellerEmailByItemList(transaction.getItemList());
				String customerEmail = customerRepository
						.findById(transaction.getCustomer().getId()).getUser()
						.getEmail();
				String subject = "new payment to online bidding system";
				String sellerMsg = "You have recieved a new payment";
				String customerMsg = "You payed "
						+ transaction.getTransactionAmount()
						+ " to online bidding system";
				sendEmail(customerEmail, "madhumal@madhumal.com", subject,
						customerMsg);
				sendEmail(sellerEmail, "madhumal@madhumal.com", subject,
						sellerMsg);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 
	 * @param to1
	 * @param from1
	 * @param subject1
	 * @param content1
	 */
	private void sendEmail(String to1, String from1, String subject1,
			String content1) {
		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress(from1);
			Address[] to = new InternetAddress[] { new InternetAddress(to1) };

			m.setFrom(from);
			m.setRecipients(javax.mail.Message.RecipientType.TO, to);
			m.setSubject(subject1);
			m.setSentDate(new java.util.Date());
			m.setContent(content1, "text/plain");
			Transport.send(m);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
