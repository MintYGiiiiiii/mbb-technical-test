package com.main.service;

import org.springframework.data.domain.Pageable;

import com.main.dto.QuoteDTO;

public interface QuoteService {
	
	public String getRandomQuote(String limit);
	
	public void insertQuote(QuoteDTO insertQuoteRequetsDTO);

	public String findQuoteHistory(int page, Pageable pageRequest);

	public String updateQuote(String id, QuoteDTO requestDTO);
}
