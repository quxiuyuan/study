package com.linecorp.lmn.web.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.lmn.auth.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.IOException;

@Slf4j
public class TestController {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static void main(String[] args) {
		User user = new User();
		user.setUserID("CL90007");
		user.setAuthUserName("quxiuyuan");
		String userStr = toJSON(user);
		System.out.println(userStr);
		User user2 = parseJSON(userStr, User.class);
		System.out.println(user2);
	}

	/**
	 * parseJSON
	 * @param contents
	 * @param type
	 * @return
	 */
	public static <T> T parseJSON(String contents, Class<T> type) {
		try {
			return OBJECT_MAPPER.readValue(contents, type);
		} catch (JsonParseException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (JsonMappingException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (IOException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return null;
	}

	/**
	 * toJSON
	 * @param value
	 * @return
	 */
	public static String toJSON(Object value) {
		try {
			return OBJECT_MAPPER.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return StringUtils.EMPTY;
	}
}
