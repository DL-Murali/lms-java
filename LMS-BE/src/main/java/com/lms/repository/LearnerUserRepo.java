package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.entity.LearnerUser;

public interface LearnerUserRepo extends JpaRepository<LearnerUser, Long> {

}
