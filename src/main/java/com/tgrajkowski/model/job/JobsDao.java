package com.tgrajkowski.model.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JobsDao extends JpaRepository<Job, Long> {
}
