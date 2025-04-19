package com.custom.app2025.data.s.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.custom.app2025.data.s.entity.SampleUser;
import com.custom.app2025.data.s.entity.SampleUserDtl;

public interface SampleUserDtlRepository extends JpaRepository<SampleUserDtl, Long> {

}
