package com.custom.app2025.data.s.entity;

import com.custom.app2025.shared.model.CustomEntity;
import com.custom.app2025.shared.utils.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity @Builder
@Table(schema = "APP2025", name = "SAMPLE_USER_DTL")
@SequenceGenerator(
	    name = "SAMPLE_USER_DTL_SNO", // 시퀀스 생성기의 이름
	    sequenceName = "APP2025.SAMPLE_USER_DTL_SEQ", // 실제 데이터베이스 시퀀스 이름
	    allocationSize = 1 // 시퀀스 증가 크기
	)
@Getter @Setter @AllArgsConstructor
public class SampleUserDtl extends CustomEntity {

	@Column(name = "SAMPLE_USER_SNO")
	private Long sampleUserSno;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAMPLE_USER_DTL_SNO")
	@Column(name = "SAMPLE_USER_DTL_SNO")
	private Long sampleUserDtlSno;
	
	
	@Column(name = "SAMPLE_USER_BASE_ADDR")
	private String sampleUserBaseAddr;
	
	@Column(name = "SAMPLE_USER_DTL_ADDR")
	private String sampleUserDtlAddr;
	
	public SampleUserDtl() {
	
	}

	public void setSampleUserSno(Long sampleUserSno) {
		if (sampleUserSno > 0) {
			this.sampleUserSno = sampleUserSno;
		}
	}
	
	/**
	 * <pre>
	 * 메서드명: setSampleUserBaseAddr
	 * 설명: 빈값 체크후 세터
	 * </pre>
	 * @param sampleUserBaseAddr
	 */
	public void setSampleUserBaseAddr(String sampleUserBaseAddr) {
		if (!StringUtils.isNVL(sampleUserBaseAddr)) {
			this.sampleUserBaseAddr = sampleUserBaseAddr;
		}
	}

	/**
	 * <pre>
	 * 메서드명: setSampleUserDtlAddr
	 * 설명: 빈값 체크후 세터
	 * </pre>
	 * @param sampleUserDtlAddr
	 */
	public void setSampleUserDtlAddr(String sampleUserDtlAddr) {
		if (StringUtils.isNVL(sampleUserDtlAddr)) {
			this.sampleUserDtlAddr = sampleUserDtlAddr;
		}
	}
	
	

}
