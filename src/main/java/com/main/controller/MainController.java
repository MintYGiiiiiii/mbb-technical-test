package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.QuoteDTO;
import com.main.service.QuoteService;
import com.main.util.JsonUtil;

@RestController
@RequestMapping(value = "/api/v1/quotes", produces = "application/json;charset=UTF-8")
public class MainController {
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private QuoteService quoteService;
	
	@PostMapping(value = "/random")
	public String getRandomQuotes(@RequestParam(value = "limit", required = false) String limit) {
		return quoteService.getRandomQuote(limit);
	}
	
	@GetMapping(value = "/getQuoteHistory")
	public @ResponseBody String getQuoteHistory(@RequestParam("page") int page) {
		return quoteService.findQuoteHistory(page, PageRequest.of(page,10));
	}
	
	@PatchMapping(value = "/updateQuote/{id}")
	public @ResponseBody String updateQuote(@PathVariable("id") String id, @RequestBody final String requestString) {
		QuoteDTO requestDTO = jsonUtil.generateRequestDTO(requestString, QuoteDTO.class);
		return quoteService.updateQuote(id, requestDTO);
	}

}
