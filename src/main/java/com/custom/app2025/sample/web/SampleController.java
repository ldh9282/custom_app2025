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

@Controller @Log4j2
public class SampleController extends CustomController {
	
	@Autowired
	private SampleService sampleService;

	@PostMapping("/APPTE01")
	@ResponseBody
	public Object appte01(@RequestBody CustomMap params) throws CustomException {
		
		params.put("sysCreator", "admin");
		
		CustomMap resultMap = sampleService.insertSampleUser(params);
		
		return getResponse(resultMap);
	}
	
	@PostMapping("/APPTE02")
	@ResponseBody
	public Object appte02(@RequestBody CustomMap params) throws CustomException {
		
		MapUtils.paramsValidation(params, new String[][] {
			{ "sampleUserSno", "required" }
			, { "sampleUserSno", "number" }
			, { "sampleUserDtlSno", "number" }
		});
		CustomMap resultMap = sampleService.getSampleUserInfo(params);
		
		return getResponse(resultMap);
	}
}
