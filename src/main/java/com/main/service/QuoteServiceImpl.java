package com.main.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.QuoteDAO;
import com.main.dto.QuoteDTO;
import com.main.dto.QuoteResponseDTO;
import com.main.entity.Quotes;
import com.main.util.CommonUtil;
import com.main.util.JsonUtil;
import com.main.util.RestfulRequestSender;

@Service
@Transactional
public class QuoteServiceImpl implements QuoteService {
	
	protected final transient Logger log = LogManager.getLogger(QuoteServiceImpl.class);
	
	@Autowired
	RestfulRequestSender restfulRequestSender;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Autowired
	private QuoteDAO quoteDAO;

	public String getRandomQuote(String limit) {
		String quoteUrl = "https://api.quotable.io/quotes/random";
		if (null != limit) {
			quoteUrl = quoteUrl + "?limit=" + limit;
		}
		
		String response = restfulRequestSender.send(null, quoteUrl, HttpMethod.GET);
		
		QuoteResponseDTO[] quoteResponseDTO = (QuoteResponseDTO[]) jsonUtil.fromJson(response,
				QuoteResponseDTO[].class);
		
		List<QuoteDTO> responseDTO = new ArrayList<>();
		for(QuoteResponseDTO quote : quoteResponseDTO) {
			QuoteDTO quoteDTO = new QuoteDTO();
			quoteDTO.setQuote(quote.getContent());
			quoteDTO.setAuthor(quote.getAuthor());
			
			insertQuote(quoteDTO);
			responseDTO.add(quoteDTO);
		}
		String responseStr = jsonUtil.toJson(responseDTO);
		log.info("Outgoing response: {}", responseStr);
		return responseStr;
	}

	@Override
	public void insertQuote(QuoteDTO insertQuoteRequetsDTO) {
		
		Quotes quote = new Quotes();
		quote.setDateAdded(new Date());
		quote.setQuoteId(commonUtil.generateRandomString(9));
		quote.setContent(insertQuoteRequetsDTO.getQuote());
		quote.setAuthor(insertQuoteRequetsDTO.getAuthor());
		quoteDAO.saveAndFlush(quote);
	}

	@Override
	@Transactional(readOnly = true)
	public String findQuoteHistory(int page, Pageable pageRequest) {
		Page<Quotes> quotes = quoteDAO.findAll(pageRequest);
		
		List<QuoteDTO> responseDTO = new ArrayList<>();
		for(Quotes quote : quotes.getContent()) {
			QuoteDTO quoteDTO = new QuoteDTO();
			quoteDTO.setQuoteId(quote.getQuoteId());
			quoteDTO.setQuote(quote.getContent());
			responseDTO.add(quoteDTO);
		}
		String responseStr = jsonUtil.toJson(responseDTO);
		log.info("Outgoing response: {}", responseStr);
		return responseStr;
	}

	@Override
	public String updateQuote(String id, QuoteDTO insertQuoteRequestDTO ) {
		Quotes quote = quoteDAO.updateQuoteByQuoteId(id);
		quote.setDateModified(new Date());
		quote.setContent(insertQuoteRequestDTO.getQuote());
		quoteDAO.save(quote);
		
		return null;
	}
}
