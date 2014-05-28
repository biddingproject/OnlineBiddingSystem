package com.bidding.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;

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

		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		paymentDetails.setPaymentAction(PaymentActionCodeType.SALE);
		PaymentDetailsItemType item = new PaymentDetailsItemType();
		BasicAmountType amt = new BasicAmountType();
		amt.setCurrencyID(CurrencyCodeType.USD);
		String itemAmount = "1.00";
		amt.setValue(itemAmount);
		int itemQuantity = 1;
		item.setQuantity(itemQuantity);
		item.setName("my item");
		item.setAmount(amt);

		List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
		lineItems.add(item);
		paymentDetails.setPaymentDetailsItem(lineItems);
		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setCurrencyID(CurrencyCodeType.fromValue("USD"));
		orderTotal.setValue("1.00");
		paymentDetails.setOrderTotal(orderTotal);
		List<PaymentDetailsType> paymentDetailsList = new ArrayList<PaymentDetailsType>();
		paymentDetailsList.add(paymentDetails);

		SetExpressCheckoutRequestDetailsType setExpressCheckoutRequestDetails = new SetExpressCheckoutRequestDetailsType();
		setExpressCheckoutRequestDetails
				.setReturnURL("https://devtools-paypal.com/guide/expresscheckout?success=true");
		setExpressCheckoutRequestDetails
				.setCancelURL("https://devtools-paypal.com/guide/expresscheckout?cancel=true");

		setExpressCheckoutRequestDetails.setPaymentDetails(paymentDetailsList);

		SetExpressCheckoutRequestType setExpressCheckoutRequest = new SetExpressCheckoutRequestType(
				setExpressCheckoutRequestDetails);
		setExpressCheckoutRequest.setVersion("104.0");

		SetExpressCheckoutReq setExpressCheckoutReq = new SetExpressCheckoutReq();
		setExpressCheckoutReq
				.setSetExpressCheckoutRequest(setExpressCheckoutRequest);

		Map<String, String> sdkConfig = new HashMap<String, String>();
		sdkConfig.put("mode", "sandbox");
		sdkConfig.put("acct1.UserName", "madhumal.lahiru.hd-facilitator_api1.gmail.com");
		sdkConfig.put("acct1.Password", "1401235779");
		sdkConfig.put("acct1.Signature","AmtjS8l1uwyXgrtanKVm21uyeD9FAvJKcL7sHanT-URCgvGH5Vv3jxYE");
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(
				sdkConfig);
		try {
			SetExpressCheckoutResponseType setExpressCheckoutResponse = service
					.setExpressCheckout(setExpressCheckoutReq);
			
			System.out.println("result >>>>>>>>>>>>>> : " + setExpressCheckoutResponse.getAck());
			System.out.println("result >>>>>>>>>>>>>> : " + setExpressCheckoutResponse.getToken());
		} catch (SSLConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
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
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "paypal/paypalpay";
	}

}
