package com.bidding.controller;

import com.bidding.model.ItemList;
import com.bidding.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ejb.EJB;

/**
 * Created by kavi on 5/25/14.
 */

@Controller
public class SearchController {

    @EJB(mappedName = "java:app/BiddingSystem-ejb/SearchService")
    SearchService searchService;

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String getSearchPage(){
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchResults(@RequestParam(value = "searchKey", required = true) String searchKey) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>. search key : " + searchKey);
        return "searchResult";
    }
}
