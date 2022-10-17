package com.tarun.sms.service;

import java.util.List;

import com.tarun.sms.entity.Company;

/**
 * 
 * @author anaaugus2
 *
 */
public interface ICompanyService {

	List<Company> getAllCompany();

	List<Company> getSearchCompany(String name);

	Company updateCompany(int id, Company company);

	Company createCompany(Company company);

	Company delete(int id);
}