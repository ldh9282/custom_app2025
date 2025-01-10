package com.custom.app2025.sample.entity;

import com.custom.app2025.shared.converter.CustomConverterS2I;
import com.custom.app2025.shared.model.CustomEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity @Builder
@Table(schema = "APP2025", name = "SAMPLE_USER")
@SequenceGenerator(
	    name = "SAMPLE_USER_SNO", // 시퀀스 생성기의 이름
	    sequenceName = "APP2025.SAMPLE_USER_SEQ", // 실제 데이터베이스 시퀀스 이름
	    allocationSize = 1 // 시퀀스 증가 크기
	)
@Getter @Setter @AllArgsConstructor
public class SampleUser extends CustomEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SAMPLE_USER_SNO")
	@Column(name = "SAMPLE_USER_SNO")
	private Long sampleUserSno;
	
	@Column(name = "SAMPLE_USER_NAME")
	private String sampleUserName;
	
	@Column(name = "SAMPLE_USER_EMAIL")
	private String sampleUserEmail;
	
	@Column(name = "SAMPLE_USER_AGE")
	@Convert(converter = CustomConverterS2I.class)
	private String sampleUserAge;
	
	@Transient
	private SampleUserDtl sampleUserDtl;
	
	public SampleUser() {
		
	}


}
