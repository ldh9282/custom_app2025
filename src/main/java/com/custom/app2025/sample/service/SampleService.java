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
	
	@Autowired
	private SampleDao sampleDao;
	
	
	@Transactional
	public CustomMap insertSampleUser(CustomMap params) throws CustomException {
		
		CustomMap resultMap = new CustomMap();
		
		try {
			
			SampleUser sampleUser = SampleUser.builder()
					.sampleUserName(params.getString("sampleUserName"))
					.sampleUserEmail(params.getString("sampleUserEmail"))
					.sampleUserAge(params.getString("sampleUserAge"))
					.build();
			
			sampleUser.setSysActor(params.getString("sysActor"));
			
			
			SampleUser theSampleUser = sampleUserRepository.save(sampleUser);
			
			SampleUserDtl sampleUserDtl = SampleUserDtl.builder()
					.sampleUserSno(theSampleUser.getSampleUserSno())
					.sampleUserBaseAddr(params.getString("sampleUserBaseAddr"))
					.sampleUserDtlAddr(params.getString("sampleUserDtlAddr"))
					.build();

			sampleUserDtl.setSysActor(params.getString("sysActor"));
			SampleUserDtl theSampleUserDtl = sampleUserDtlRepository.save(sampleUserDtl);
			
			theSampleUser.setSampleUserDtl(theSampleUserDtl);
			
			resultMap.put("sampleUser", sampleUser);
			
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR521, new String[] { "샘플유저" }, e);
		}
		
		return resultMap;
	}
	
	public CustomMap getSampleUserInfo(CustomMap params) {
		CustomMap resultMap = new CustomMap();
		try {
			var sampleUser = sampleUserRepository.findById(params.getLong("sampleUserSno"))
					.orElseThrow(() -> new CustomException(CustomExceptionCode.ERR999, new String[] { "조회결과 없음 [sampleUserSno: " + params.getLong("sampleUserSno") +"]" }));
			
			var sampleUserDtl = sampleUserDtlRepository.findBySampleUserSnoAndSampleUserDtlSno(params.getLong("sampleUserSno"), params.getLong("sampleUserDtlSno"))
					.orElseThrow(() -> new CustomException(CustomExceptionCode.ERR999, new String[] { "조회결과 없음 [sampleUserDtlSno: " + params.getLong("sampleUserDtlSno") +"]" }));
			
			sampleUser.setSampleUserDtl(sampleUserDtl);
			
			var sampleUser2 = sampleDao.selectSampleUserInfo(params);
			
			resultMap.put("sampleUser", sampleUser);
			resultMap.put("sampleUser2", sampleUser2);
			
			
			
		} catch (CustomException e) {
			throw new CustomException(CustomExceptionCode.ERR999, new String[] { e.getMessage() }, e);
		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "샘플유저" }, e);
		}
		return resultMap;
	}
	public CustomMap getSampleUserInfo2(CustomMap params) {
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
			throw new CustomException(CustomExceptionCode.ERR511, new String[] { "샘플유저" }, e);
		}
		return resultMap;
	}
	
	@Transactional
	public CustomMap updateSampleUserInfo(CustomMap params) {
		BooleanBuilder booleanBuilder = null;
		CustomMap resultMap = new CustomMap();
		try {
			QSampleUser qSampleUser = QSampleUser.sampleUser;
			
			SampleUser sampleUser = queryFactory.select(qSampleUser)
				.from(qSampleUser)
				.where(qSampleUser.sampleUserSno.eq(params.getLong("sampleUserSno")))
				.fetchOne();
			
			sampleUser.setSampleUserName(params.getString("sampleUserName"));
			sampleUser.setSampleUserEmail(params.getString("sampleUserEmail"));
			sampleUser.setSampleUserAge(params.getString("sampleUserAge"));
			sampleUser.setSysActor(params.getString("sysActor"));
			
			sampleUserRepository.save(sampleUser);
			
			QSampleUserDtl qSampleUserDtl = QSampleUserDtl.sampleUserDtl;
			
			booleanBuilder = new BooleanBuilder(qSampleUserDtl.sampleUserSno.eq(params.getLong("sampleUserSno")));
			
			if (!StringUtils.isNVL(params.getString("sampleUserDtlSno"))) {
				booleanBuilder.and(qSampleUserDtl.sampleUserDtlSno.eq(params.getLong("sampleUserDtlSno")));
			}
			
			SampleUserDtl sampleUserDtl = queryFactory.select(qSampleUserDtl)
					.from(qSampleUserDtl)
					.where(booleanBuilder)
					.fetchOne();
			
			sampleUserDtl.setSampleUserBaseAddr(params.getString("sampleUserBaseAddr"));
			sampleUserDtl.setSampleUserDtlAddr(params.getString("sampleUserDtlAddr"));
			sampleUserDtl.setSysActor(params.getString("sysActor"));
			
			sampleUserDtlRepository.save(sampleUserDtl);
			
			sampleUser.setSampleUserDtl(sampleUserDtl);
			
			resultMap.put("sampleUser", sampleUser);

		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR531, new String[] { "샘플유저" }, e);
		}
		return resultMap;
	}
	
	@Transactional
	public CustomMap deleteSampleUserInfo(CustomMap params) {
		BooleanBuilder booleanBuilder = null;
		CustomMap resultMap = new CustomMap();
		long cnt = 0;
		try {
			QSampleUser qSampleUser = QSampleUser.sampleUser;
			
			cnt += queryFactory.delete(qSampleUser)
					.where(qSampleUser.sampleUserSno.eq(params.getLong("sampleUserSno")))
					.execute();
			
			QSampleUserDtl qSampleUserDtl = QSampleUserDtl.sampleUserDtl;
			
			booleanBuilder = new BooleanBuilder(qSampleUserDtl.sampleUserSno.eq(params.getLong("sampleUserSno")));
			if (!StringUtils.isNVL(params.getString("sampleUserDtlSno"))) {
				booleanBuilder.and(qSampleUserDtl.sampleUserDtlSno.eq(params.getLong("sampleUserDtlSno")));
			}
			
			cnt += queryFactory.delete(qSampleUserDtl)
					.where(booleanBuilder)
					.execute();
			
			resultMap.put("cnt", cnt);

		} catch (Exception e) {
			throw new CustomException(CustomExceptionCode.ERR541, new String[] { "샘플유저" }, e);
		}
		return resultMap;
	}
	
}
