package com.bidding.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class TestEmailService {

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;

	public void sendEmail() {

		try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress("madhumal@madhumal.com");
			Address[] to = new InternetAddress[] { new InternetAddress(
					"madhumal.lahiru.hd@gmail.com") };

			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject("JBoss AS 7 Mail");
			m.setSentDate(new java.util.Date());
			m.setContent("Mail sent from JBoss AS 7", "text/plain");
			Transport.send(m);
			System.out.println("Mail sent!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
