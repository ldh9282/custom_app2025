package com.custom.app2025.sample.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.custom.app2025.shared.model.CustomMap;

@Repository
public class SampleDao {

	@Autowired
	private SqlSessionTemplate sst;
	
	/**
	 * <pre>
	 * 메소드명: selectSampleUserInfo
	 * 설명: 샘플유저정보 조회
	 * </pre>
	 * @param params
	 * @return
	 */
	public CustomMap selectSampleUserInfo(CustomMap params) {
		return sst.selectOne("Sample.selectSampleUserInfo", params);
	}
	
}
