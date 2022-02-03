package com.leanlee.memory.back.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 请求工具类
 *
 * @author Lean
 * @since 2022-02-03 11:50
 **/
public class RequestUtils {

	private static final String UNKNOWN = "unknown";
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	/**
	 * 获取cookie
	 *
	 * @param request 请求
	 * @param key     键
	 * @return 值
	 */
	public static String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
		Cookie[] cookies = request.getCookies();
		if (Objects.isNull(cookies)) {
			// todo 应该返回null，而非""
			return "";
		}

		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (name.equals(key)) {
				String value = cookie.getValue();
				value = URLDecoder.decode(value, "UTF-8");
				return value;
			}
		}

		return "";
	}

	/**
	 * 获取Ip地址
	 *
	 * @param request 请求
	 * @return IP地址
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String xRealIp = request.getHeader("X-Real-IP");
		String xForwardedFor = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(xForwardedFor) && !UNKNOWN.equalsIgnoreCase(xForwardedFor)) {
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = xForwardedFor.indexOf(",");
			if (index != -1) {
				return xForwardedFor.substring(0, index);
			} else {
				return xForwardedFor;
			}
		}
		xForwardedFor = xRealIp;
		if (StringUtils.isNotEmpty(xForwardedFor) && !UNKNOWN.equalsIgnoreCase(xForwardedFor)) {
			return xForwardedFor;
		}
		if (StringUtils.isBlank(xForwardedFor) || UNKNOWN.equalsIgnoreCase(xForwardedFor)) {
			xForwardedFor = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(xForwardedFor) || UNKNOWN.equalsIgnoreCase(xForwardedFor)) {
			xForwardedFor = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(xForwardedFor) || UNKNOWN.equalsIgnoreCase(xForwardedFor)) {
			xForwardedFor = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(xForwardedFor) || UNKNOWN.equalsIgnoreCase(xForwardedFor)) {
			xForwardedFor = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(xForwardedFor) || UNKNOWN.equalsIgnoreCase(xForwardedFor)) {
			xForwardedFor = request.getRemoteAddr();
		}
		return xForwardedFor;
	}

	/**
	 * 获取当前时间的字符串
	 *
	 * @return 时间
	 */
	public static String getTimestamp() {
		return SIMPLE_DATE_FORMAT.format(new Date());
	}

}
