package com.main.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.main.dto.QuoteDTO;

@Component
public class JsonUtil {
	
	private final static Logger log = LogManager.getLogger(JsonUtil.class);
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	//Parse JSON string as DTO object.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object fromJson(final String dto, final Class clazz) {
		return gson.fromJson(dto, clazz);
	}
	
	//Parse DTO object as JSON string.
	public String toJson (final Object dto) {
		return gson.toJson(dto);
	}
	
	public <T extends Object> T generateRequestDTO (final String requestString, final Class<T> clazz) {
		log.info("Incoming Request: {}", toJson(fromJson(requestString, clazz)));
		return clazz.cast(fromJson(requestString, clazz));
	}

}
