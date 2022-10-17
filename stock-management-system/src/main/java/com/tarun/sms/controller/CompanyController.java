package com.tarun.sms.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tarun.sms.dto.CompanyDTO;
import com.tarun.sms.entity.Company;
import com.tarun.sms.service.ICompanyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class CompanyController extends BaseController {

	Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	ICompanyService service;

	/**
	 * @author tarun
	 * @param id
	 * @param company
	 * @return created company
	 */
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/company")
	@Operation(description = "API to create a new company")
	public ResponseEntity<Company> createCompany(@Valid @RequestBody CompanyDTO companyDTO) {
		Company company = new Company(companyDTO);
		Company result = null;
		result = service.createCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	/**
	 * 
	 * @return all companies
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/company")
	public List<Company> getAllCompany() {
		return service.getAllCompany();
	}

	/**
	 * 
	 * @param companyName
	 * @return company by name
	 */
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/company/{name}")
	public List<Company> getSearchedCompany(@PathVariable String name) {
		return service.getSearchCompany(name);
	}

	/**
	 * 
	 * @param id
	 * @param companyDTO
	 * @return updated company record
	 */
	@Transactional
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping("/company/{id}")
	public Company updateCompany(@Valid @PathVariable int id, @RequestBody CompanyDTO companyDTO) {
		Company company = new Company(companyDTO);
		return service.updateCompany(id, company);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/company/{id}")
	public Company deleteCompany(@PathVariable int id) {
		return service.delete(id);
	}
}
