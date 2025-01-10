package com.custom.app2025.sample.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.custom.app2025.sample.service.SampleService;
import com.custom.app2025.shared.exception.CustomException;
import com.custom.app2025.shared.model.CustomMap;
import com.custom.app2025.shared.utils.MapUtils;
import com.custom.app2025.shared.web.CustomController;

import lombok.extern.log4j.Log4j2;

/**
 * <pre>
 * 클래스명: SampleController
 * 설명: 샘플 컨트롤러
 * </pre>
 */
@Controller @Log4j2
public class SampleController extends CustomController {
	
	@Autowired
	private SampleService sampleService;

	/**
	 * <pre>
	 * 메서드명: appte01
	 * 설명: 샘플유저정보 등록
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/APPTE01")
	@ResponseBody
	public Object appte01(@RequestBody CustomMap params) {
		
		CustomMap resultMap = sampleService.insertSampleUser(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: appte02
	 * 설명: 샘플유저정보 조회
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("/APPTE02")
	@ResponseBody
	public Object appte02(@RequestBody CustomMap params) {
		
		MapUtils.paramsValidation(params, new String[][] {
			{ "sampleUserSno", "required" }
			, { "sampleUserSno", "number" }
			, { "sampleUserDtlSno", "number" }
		});
		CustomMap resultMap = sampleService.getSampleUserInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: appte03
	 * 설명: 샘플유저정보 수정
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("APPTE03")
	@ResponseBody
	public Object appte03(@RequestBody CustomMap params) {
		
		CustomMap resultMap = sampleService.updateSampleUserInfo(params);
		
		return getResponse(resultMap);
	}
	
	/**
	 * <pre>
	 * 메서드명: appte02
	 * 설명: 샘플유저정보 삭제
	 * </pre>
	 * @param params
	 * @return
	 */
	@PostMapping("APPTE04")
	@ResponseBody
	public Object appte04(@RequestBody CustomMap params) {
		MapUtils.paramsValidation(params, new String[][] {
			{ "sampleUserSno", "required" }
			, { "sampleUserSno", "number" }
			, { "sampleUserDtlSno", "number" }
		});
		CustomMap resultMap = sampleService.deleteSampleUserInfo(params);
		
		return getResponse(resultMap);
	}
}
