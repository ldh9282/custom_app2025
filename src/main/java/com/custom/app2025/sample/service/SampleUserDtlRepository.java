package com.custom.app2025.sample.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.custom.app2025.sample.entity.SampleUser;
import com.custom.app2025.sample.entity.SampleUserDtl;

public interface SampleUserDtlRepository extends JpaRepository<SampleUserDtl, Long> {

}
