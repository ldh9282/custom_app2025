package com.custom.app2025.sample.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.custom.app2025.sample.entity.SampleUser;

public interface SampleUserRepository extends JpaRepository<SampleUser, Long> {

}
