package com.bidding.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bidding.model.ItemList;
import com.bidding.service.ItemListService;

/**
 * 
 * @author madhumal
 * 
 */
@Controller
public class ItemListController {

	@EJB(mappedName = "java:app/BiddingSystem-ejb/ItemListService")
	ItemListService itemListService;
	
	@Autowired
	SimpleDateFormat dateFormatter;

	/**
	 * 
	 * @param itemList
	 * @param result
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@PreAuthorize("hasRole('ROLE_SELLER')")
	@RequestMapping(value = "/createItemList", method = RequestMethod.POST)
	public String loadCustomerProfile(
			@ModelAttribute("itemList") ItemList itemList,
			BindingResult result,
			ModelMap model,
			@RequestParam(value = "bidStartTime", required = true) String bidStartTime,
			@RequestParam(value = "expireTime", required = true) String expireTime,
			@RequestParam(value = "timeOffset", required = true) int timeOffset)
			throws ParseException {

		if (result.hasErrors()) {
			System.out.println("binding result has errors");
		} else {
			String email = SecurityContextHolder.getContext()
					.getAuthentication().getName();

			Date startTime = dateFormatter.parse(bidStartTime.replace("T", " "));
			Date endTime = dateFormatter.parse(expireTime.replace("T", " "));

			Calendar cal = Calendar.getInstance();
			cal.setTime(startTime);
			cal.add(Calendar.MINUTE, timeOffset);
			startTime = cal.getTime();

			cal.setTime(endTime);
			cal.add(Calendar.MINUTE, timeOffset);
			endTime = cal.getTime();

			itemList.setBidStartTimeStamp(startTime);
			itemList.setExpireTimeStamp(endTime);
			itemListService.createItemLIst(itemList, email);
			
			model.put("itemList", new ItemList());
			return "/profile/seller";
		}

		model.put("itemList", new ItemList());
		return "/profile/seller";
	}
}
