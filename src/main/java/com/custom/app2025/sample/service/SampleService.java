package com.custom.app2025.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.custom.app2025.sample.entity.QSampleUser;
import com.custom.app2025.sample.entity.QSampleUserDtl;
import com.custom.app2025.sample.entity.SampleUser;
import com.custom.app2025.sample.entity.SampleUserDtl;
import com.custom.app2025.shared.exception.CustomException;
import com.custom.app2025.shared.exception.CustomExceptionCode;
import com.custom.app2025.shared.model.CustomMap;
import com.custom.app2025.shared.utils.StringUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service @Log4j2
public class SampleService {

	@Autowired
	private JPAQueryFactory queryFactory;
	
	@Autowired
	private SampleUserRepository sampleUserRepository;
	
	@Autowired
	private SampleUserDtlRepository sampleUserDtlRepository;
	
	
	@Transactional
	public CustomMap insertSampleUser(CustomMap params) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		
		try {
			
			SampleUser sampleUser = SampleUser.builder()
					.sampleUserName(params.getString("sampleUserName"))
					.sampleUserEmail(params.getString("sampleUserEmail"))
					.sampleUserAge(params.getString("sampleUserAge"))
					.build();
			
			sampleUser.setSysCreator(params.getString("sysCreator"));
			
			
			SampleUser theSampleUser = sampleUserRepository.save(sampleUser);
			
			SampleUserDtl sampleUserDtl = SampleUserDtl.builder()
					.sampleUserSno(theSampleUser.getSampleUserSno())
					.sampleUserBaseAddr(params.getString("sampleUserBaseAddr"))
					.sampleUserDtlAddr(params.getString("sampleUserDtlAddr"))
					.build();

			sampleUserDtl.setSysCreator(params.getString("sysCreator"));
			SampleUserDtl theSampleUserDtl = sampleUserDtlRepository.save(sampleUserDtl);
			
			theSampleUser.setSampleUserDtl(theSampleUserDtl);
			
			resultMap.put("sampleUser", sampleUser);
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR501, new String[] { e.getMessage() });
		}
		
		return resultMap;
	}
	
	public CustomMap getSampleUserInfo(CustomMap params) throws CustomException {
		BooleanBuilder booleanBuilder = null;
		CustomMap resultMap = new CustomMap();
		try {
			QSampleUser qSampleUser = QSampleUser.sampleUser;
			
			SampleUser sampleUser = queryFactory.select(qSampleUser)
				.from(qSampleUser)
				.where(qSampleUser.sampleUserSno.eq(params.getLong("sampleUserSno")))
				.fetchOne();
			
			QSampleUserDtl qSampleUserDtl = QSampleUserDtl.sampleUserDtl;
			
			booleanBuilder = new BooleanBuilder(qSampleUserDtl.sampleUserSno.eq(params.getLong("sampleUserSno")));
			if (!StringUtils.isNVL(params.getString("sampleUserDtlSno"))) {
				booleanBuilder.and(qSampleUserDtl.sampleUserDtlSno.eq(params.getLong("sampleUserDtlSno")));
			}
			
			SampleUserDtl sampleUserDtl = queryFactory.select(qSampleUserDtl)
					.from(qSampleUserDtl)
					.where(booleanBuilder)
					.fetchOne();
			sampleUser.setSampleUserDtl(sampleUserDtl);
			
			resultMap.put("sampleUser", sampleUser);

		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR501, new String[] { e.getMessage() });
		}
		return resultMap;
	}
	
}
