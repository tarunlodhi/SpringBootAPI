package com.tarun.sms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tarun.sms.entity.Company;
import com.tarun.sms.exception.AlreadyExistException;
import com.tarun.sms.repository.ICompanyRepository;

@Configuration
@Service
public class CompanyServiceImpl implements ICompanyService {
	private static final String COMPANY_EXISTS = "Company already exists";
	@Autowired
	ICompanyRepository companyRepository;

	@Override
	public Company createCompany(Company company) {
		if (companyRepository.existsByName(company.getName()) != null
				&& companyRepository.existsByName(company.getName()))
			throw new AlreadyExistException(COMPANY_EXISTS);
		return companyRepository.save(company);
	}

	@Override
	public List<Company> getAllCompany() {
		Company company = new Company();
		return companyRepository.findAll();
	}

	@Override
	public List<Company> getSearchCompany(String name) {
		if (companyRepository.searchByNameStartsWith(name) == null)
			throw new AlreadyExistException(COMPANY_EXISTS);
		return companyRepository.searchByNameStartsWith(name);
	}

	@Override
	public Company delete(int id) {
		Company deleteCompany = companyRepository.findById(id).orElse(null);
		if (deleteCompany == null)
			throw new AlreadyExistException(COMPANY_EXISTS);
		deleteCompany.setStatus(false);
		companyRepository.save(deleteCompany);
		return companyRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	/**
	 * @author tarlodhi
	 * 
	 */
	public Company updateCompany(int id, Company company) {
		Company updateCompany = companyRepository.findById(id).orElse(null);
		if (updateCompany == null)
			throw new AlreadyExistException(COMPANY_EXISTS);
		updateCompany.setName(company.getName());
		updateCompany.setTurnover(company.getTurnover());
		updateCompany.setDirectors(company.getDirectors());
		updateCompany.setCeo(company.getCeo());
		updateCompany.setStatus(company.getStatus());
		updateCompany.setSector(company.getSector());
		updateCompany.setBriefDescription(company.getBriefDescription());
		updateCompany.setStockcodeNSE(company.getStockcodeNSE());
		updateCompany.setStockcodeBSE(company.getStockcodeBSE());
		return companyRepository.save(updateCompany);
	}
}