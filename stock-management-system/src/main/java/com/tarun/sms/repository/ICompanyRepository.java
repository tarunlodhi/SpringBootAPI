package com.tarun.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarun.sms.entity.Company;

@Repository
public interface ICompanyRepository extends JpaRepository<Company, Integer> {
	List<Company> searchByNameStartsWith(String name);
	Boolean existsByName(String name);
}
