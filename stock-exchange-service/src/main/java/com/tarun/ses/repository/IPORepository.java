package com.tarun.ses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarun.ses.entity.IPO;
@Repository
public interface IPORepository extends JpaRepository<IPO, Integer> {
	IPO findByCompanyName(String name);
}
