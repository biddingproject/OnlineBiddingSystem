package com.bidding.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.bidding.data.ItemListRepository;
import com.bidding.data.UserRepository;
import com.bidding.model.ItemList;
import com.bidding.model.Transaction;
import com.bidding.model.User;

@Stateless
public class TransactionService {

	@Inject
	private ItemListRepository itemListRepository;

	@Inject
	private UserRepository userRepository;

	/**
	 * transactions are pushed to the queue
	 * 
	 * @param itemListId
	 * @param quantity
	 * @param email
	 */
	public void buyItems(long itemListId, int quantity, String email) {

		final String QUEUE_LOOKUP = "java:jboss/activemq/queue/TestQueue";
		final String CONNECTION_FACTORY = "java:jboss/activemq/QueueConnectionFactory";

		Context context;

		try {

			context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context
					.lookup(CONNECTION_FACTORY);
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);

			Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);
			QueueSender sender = session.createSender(queue);

			// 2. Sending ObjectMessage to the Queue
			ObjectMessage objMsg = session.createObjectMessage();

			Transaction transaction = new Transaction();

			ItemList itemList = itemListRepository.findById(itemListId);

			transaction.setItemList(itemList);
			transaction.setPriceBought(itemList.getBuyItNowPrice());
			transaction.setQuantity(quantity);

			User user = userRepository.findByEmail(email);
			transaction.setCustomer(user.getCustomer());

			/**
			 * convert item bought time to utc time
			 */

			transaction.setItemBoughtTime(new Date());

			float amount = itemList.getBuyItNowPrice() * quantity;
			/**
			 * handle discounts here
			 */
			if (transaction.getDisCountRate() > 0) {
				float discount = itemList.getBuyItNowPrice()
						* transaction.getDisCountRate() * quantity;
				amount = amount - discount;
			}

			transaction.setTransactionAmount(amount);

			objMsg.setObject(transaction);
			sender.send(objMsg);

			System.out.println("Sent transaction to the Queue");
			session.close();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
