package com.custom.app2025.shared.utils;

import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtils {

	/**
	 * <pre>
	 * 메서드명: getStatus
	 * 설명: status 반환
	 * </pre>
	 * @param request
	 * @return
	 */
	public static HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ex) {
            	return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
