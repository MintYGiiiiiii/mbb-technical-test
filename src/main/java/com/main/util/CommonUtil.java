package com.main.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	public String generateRandomString (int length) {
		String randomStr = RandomStringUtils.randomAlphanumeric(length);
		return randomStr;
	}
}
