package com.main.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.main.entity.Quotes;

@Repository
public interface QuoteDAO extends JpaRepository<Quotes, Long> { 
	
	@Query(nativeQuery = true, value = "select * from Quotes where quote_id = ?1")
	public Quotes updateQuoteByQuoteId(String id);
	
}
