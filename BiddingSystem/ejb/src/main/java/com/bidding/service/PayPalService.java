package com.bidding.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bidding.data.ItemListRepository;
import com.bidding.model.ShoppingCartItem;
import com.bidding.model.Transaction;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.services.AdaptivePaymentsService;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;

/**
 * 
 * @author madhumal
 * 
 */
@Stateless
public class PayPalService implements IPaymentService {

	@Inject
	ItemListRepository itemListRepository;

	/**
	 * pay for the shopping cart
	 * 
	 * @return
	 */
	@Override
	public List<ShoppingCartItem> Pay(List<ShoppingCartItem> cartItems) {

		for (ShoppingCartItem shoppingCartItem : cartItems) {

			int quantity = shoppingCartItem.getQuantity();
			float itemPrice = shoppingCartItem.getItemList().getBuyItNowPrice();
			String sellerEmail = itemListRepository
					.getSellerEmailByItemList(shoppingCartItem.getItemList());// shoppingCartItem.getItemList().getSeller().getUser().getEmail();
			float total = quantity * itemPrice;
			float amountForSystem = (float) (total * 0.02);

			try {
				payTheSeller(total, sellerEmail, amountForSystem);
			} catch (Exception e) {
				shoppingCartItem.setPaymentWentWrong(true);
				e.printStackTrace();
			} 
		}

		return cartItems;
	}

	/**
	 * 
	 * @param totalForSeller
	 * @param sellerEmail
	 * @param totalForSystem
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws OAuthException
	 * @throws MissingCredentialException
	 * @throws ClientActionRequiredException
	 * @throws InvalidResponseDataException
	 * @throws HttpErrorException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidCredentialException
	 * @throws SSLConfigurationException
	 */
	public Map<String, String> payTheSeller(float totalForSeller,
			String sellerEmail, float totalForSystem)
			throws SSLConfigurationException, InvalidCredentialException,
			UnsupportedEncodingException, HttpErrorException,
			InvalidResponseDataException, ClientActionRequiredException,
			MissingCredentialException, OAuthException, IOException,
			InterruptedException {

		Map<String, String> reponse = new HashMap<String, String>();

		PayRequest payRequest = new PayRequest();

		List<Receiver> receivers = new ArrayList<Receiver>();
		Receiver secondaryReceiver = new Receiver();
		secondaryReceiver.setAmount((double) totalForSystem);
		secondaryReceiver.setEmail("mad@thinkcube.com");
		receivers.add(secondaryReceiver);

		Receiver primaryReceiver = new Receiver();
		primaryReceiver.setAmount((double) totalForSeller);
		primaryReceiver.setEmail(sellerEmail);
		primaryReceiver.setPrimary(true);
		receivers.add(primaryReceiver);
		ReceiverList receiverList = new ReceiverList(receivers);

		payRequest.setReceiverList(receiverList);

		RequestEnvelope requestEnvelope = new RequestEnvelope();
		requestEnvelope.setErrorLanguage("en_US");
		payRequest.setRequestEnvelope(requestEnvelope);
		payRequest.setActionType("PAY");
		payRequest
				.setCancelUrl("https://devtools-paypal.com/guide/ap_chained_payment?cancel=true");
		payRequest
				.setReturnUrl("https://devtools-paypal.com/guide/ap_chained_payment?success=true");
		payRequest.setCurrencyCode("USD");
		payRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName",
				"madhumal.lahiru.hd-facilitator_api1.gmail.com");
		sdkConfig.put("acct1.Password", "1401235779");
		sdkConfig.put("acct1.Signature",
				"AmtjS8l1uwyXgrtanKVm21uyeD9FAvJKcL7sHanT-URCgvGH5Vv3jxYE");
		sdkConfig.put("acct1.AppId", "APP-80W284485P519543T");

		AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(
				sdkConfig);

		PayResponse payResponse = adaptivePaymentsService.pay(payRequest);
		System.out.println(">>>>>>" + payResponse.getPaymentExecStatus());
		System.out.println(">>>>>>" + payResponse.getPayKey());

		reponse.put("payKey", payResponse.getPayKey());
		reponse.put("status", payResponse.getPaymentExecStatus());

		return reponse;

	}

	@Override
	public void Refund(Transaction transaction) {
		// TODO Auto-generated method stub

	}

}
