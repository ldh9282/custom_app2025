package com.custom.app2025.shared.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.custom.app2025.shared.web.CustomController;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler extends CustomController {

	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public Object handleCustomException(HttpServletRequest request, CustomException e) {
		
		boolean isAjaxRequest = "application/json".equals(request.getHeader("Content-Type"));

//        if (isAjaxRequest) {
            // AJAX 요청일 경우, response.header.status != 0000
            return getErrorResponse(e);
//        } else {
//            // AJAX 요청이 아닐 경우, 에러 페이지로 리다이렉션
//        	ModelAndView modelAndView = new ModelAndView();
//        	modelAndView.addObject("response", getErrorResponse(e));
//        	modelAndView.setViewName("cmmn/error-500");
//            return modelAndView;
//        }
	}
	
	
}
