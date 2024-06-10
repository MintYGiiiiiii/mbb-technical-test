package com.main.dto;


public class QuoteDTO {
	private String id;
	private String quoteId;
	
	private String author;
	private String quote;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getQuote() {
		return quote;
	}
	
	public void setQuote(String quote) {
		this.quote = quote;
	}
}
