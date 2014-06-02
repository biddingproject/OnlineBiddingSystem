package com.bidding.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

@Controller
class IndexController {

	// @EJB(mappedName = "java:app/BiddingSystem-ejb/UserRepository")
	// UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayIndex(ModelMap model) {
		return "index";
	}

	@RequestMapping(value = "/payPal", method = RequestMethod.GET)
	public String payPalTest(ModelMap model) {
		
		PayRequest payRequest = new PayRequest();
		
		List<Receiver> receivers = new ArrayList<Receiver>();
		Receiver secondaryReceiver = new Receiver();
		secondaryReceiver.setAmount(1.00);
		secondaryReceiver.setEmail("mad@thinkcube.com");
		receivers.add(secondaryReceiver);

		Receiver primaryReceiver = new Receiver();
		primaryReceiver.setAmount(2.00);
		primaryReceiver.setEmail("madhumal.lahiru.hd-facilitator@gmail.com");
		primaryReceiver.setPrimary(true);
		receivers.add(primaryReceiver);
		ReceiverList receiverList = new ReceiverList(receivers);

		payRequest.setReceiverList(receiverList);

		RequestEnvelope requestEnvelope = new RequestEnvelope();
		requestEnvelope.setErrorLanguage("en_US");
		payRequest.setRequestEnvelope(requestEnvelope); 
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl("https://devtools-paypal.com/guide/ap_chained_payment?cancel=true");
		payRequest.setReturnUrl("https://devtools-paypal.com/guide/ap_chained_payment?success=true");
		payRequest.setCurrencyCode("USD");
		payRequest.setIpnNotificationUrl("http://replaceIpnUrl.com");

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName", "madhumal.lahiru.hd-facilitator_api1.gmail.com");
		sdkConfig.put("acct1.Password", "1401235779");
		sdkConfig.put("acct1.Signature","AmtjS8l1uwyXgrtanKVm21uyeD9FAvJKcL7sHanT-URCgvGH5Vv3jxYE");
		sdkConfig.put("acct1.AppId","APP-80W284485P519543T");

		AdaptivePaymentsService adaptivePaymentsService = new AdaptivePaymentsService(sdkConfig);
		try {
			PayResponse payResponse = adaptivePaymentsService.pay(payRequest);
			System.out.println(">>>>>>"+ adaptivePaymentsService.getAccessToken());
			System.out.println(">>>>>>"+ adaptivePaymentsService.getTokenSecret());
			System.out.println(">>>>>>"+ payResponse.getPaymentExecStatus());
			System.out.println(">>>>>>"+ payResponse.getPayKey());
		} catch (SSLConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidResponseDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientActionRequiredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		PaymentDetailsType paymentDetails = new PaymentDetailsType();
//		paymentDetails.setPaymentAction(PaymentActionCodeType.SALE);
//		PaymentDetailsItemType item = new PaymentDetailsItemType();
//		BasicAmountType amt = new BasicAmountType();
//		amt.setCurrencyID(CurrencyCodeType.USD);
//		String itemAmount = "1.00";
//		amt.setValue(itemAmount);
//		int itemQuantity = 1;
//		item.setQuantity(itemQuantity);
//		item.setName("my item");
//		item.setAmount(amt);
//
//		List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
//		lineItems.add(item);
//		paymentDetails.setPaymentDetailsItem(lineItems);
//		BasicAmountType orderTotal = new BasicAmountType();
//		orderTotal.setCurrencyID(CurrencyCodeType.fromValue("USD"));
//		orderTotal.setValue("1.00");
//		paymentDetails.setOrderTotal(orderTotal);
//		List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();
//		paymentDetailsList.add(paymentDetails);
//
//		SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();
//		setExpressCheckoutRequestDetails
//				.setReturnURL("https://devtools-paypal.com/guide/expresscheckout?success=true");
//		setExpressCheckoutRequestDetails
//				.setCancelURL("https://devtools-paypal.com/guide/expresscheckout?cancel=true");
//
//		setExpressCheckoutRequestDetails.setPaymentDetails(paymentDetailsList);
//
//		SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(
//				setExpressCheckoutRequestDetails);
//		setExpressCheckoutRequest.setVersion("104.0");
//
//		SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
//		setExpressCheckoutReq
//				.setSetExpressCheckoutRequest(setExpressCheckoutRequest);
//
//		Map<String, String> sdkConfig = new HashMap<String, String>();
//		sdkConfig.put("mode", "sandbox");
//		sdkConfig.put("acct1.UserName", "madhumal.lahiru.hd-facilitator_api1.gmail.com");
//		sdkConfig.put("acct1.Password", "1401235779");
//		sdkConfig.put("acct1.Signature","AmtjS8l1uwyXgrtanKVm21uyeD9FAvJKcL7sHanT-URCgvGH5Vv3jxYE");
//		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(
//				sdkConfig);
//		try {
//			SetExpressCheckoutResponseType setExpressCheckoutResponse = service
//					.setExpressCheckout(setExpressCheckoutReq);
//			
//			System.out.println("result >>>>>>>>>>>>>> : " + setExpressCheckoutResponse.getAck());
//			System.out.println("result >>>>>>>>>>>>>> : " + setExpressCheckoutResponse.getToken());
//		} catch (SSLConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidCredentialException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (HttpErrorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidResponseDataException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientActionRequiredException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MissingCredentialException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (OAuthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return "paypal/paypalpay";
	}

}
