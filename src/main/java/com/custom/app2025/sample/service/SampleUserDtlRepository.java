package com.custom.app2025.sample.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.custom.app2025.sample.entity.SampleUser;
import com.custom.app2025.sample.entity.SampleUserDtl;

public interface SampleUserDtlRepository extends JpaRepository<SampleUserDtl, Long> {

	Optional<SampleUserDtl> findBySampleUserSnoAndSampleUserDtlSno(Long sampleUserSno, Long sampleUserDtlSno);
}
