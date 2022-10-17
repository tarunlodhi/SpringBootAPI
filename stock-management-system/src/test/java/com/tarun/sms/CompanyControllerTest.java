package com.tarun.sms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.tarun.sms.controller.CompanyController;
import com.tarun.sms.dto.CompanyDTO;
import com.tarun.sms.entity.Company;
import com.tarun.sms.entity.Directors;
import com.tarun.sms.service.ICompanyService;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {
	@InjectMocks
	CompanyController controller;
	@Mock
	ICompanyService service;

	@Test
	void getAllCompanyTest() {
		List<Company> response = new ArrayList<Company>();
		when(service.getAllCompany()).thenReturn(response);
		List<Company> result = controller.getAllCompany();
		assertEquals(response, result);
	}

	@Test
	void testGetCompanyByName() {
		List<Company> company = new ArrayList<Company>();
		String name = "sapient";
		when(service.getSearchCompany(name)).thenReturn(company);
		List<Company> result = controller.getSearchedCompany(name);
		assertEquals(company, result);
	}

	@Test
	@MockitoSettings(strictness = Strictness.LENIENT)
	void testCreateCompany() {// AAA
		CompanyDTO companydto = new CompanyDTO();
		sampleParameters(companydto);
		Company company = new Company(companydto);
		when(service.createCompany(Mockito.any(Company.class))).thenReturn(company);
		ResponseEntity<?> result = controller.createCompany(companydto);
		assertEquals("201 CREATED", result.getStatusCode().toString());
	}

	@Test
	void updateCompanyTest() {
		CompanyDTO companyDTO = new CompanyDTO();
		Integer id = 1;
		sampleParameters(companyDTO);
		Company company = new Company(companyDTO);
		when(service.updateCompany(Mockito.eq(id), Mockito.any(Company.class))).thenReturn(company);
		Company result = controller.updateCompany(1, companyDTO);
		assertEquals("Salil Parekh", result.getCeo());
	}

	@Test
	void deleteCompanyTest() {
		CompanyDTO companyDTO = new CompanyDTO();
		Integer id = 1;
		sampleParameters(companyDTO);
		Company company = new Company(companyDTO);
		when(service.delete(id)).thenReturn(company);
		Company c = controller.deleteCompany(id);
		assertEquals("Infosys", c.getName());
	}

	private void sampleParameters(CompanyDTO companyDTO) {
		List<Directors> dlist = new ArrayList<>();
		dlist.add(Directors(20, "name"));
		companyDTO.setName("Infosys");
		companyDTO.setBriefDescription(
				"Infosys Limited is an Indian multinational information technology company that provides business consulting, information technology and outsourcing services");
		companyDTO.setTurnover(12312000.324);
		companyDTO.setCeo("Salil Parekh");
		companyDTO.setDirectors(dlist);
		companyDTO.setSector("IT");
		companyDTO.setStockcodeBSE(500209);
		companyDTO.setStockcodeNSE("INFY");
		companyDTO.setStatus(true);
	}
	private Directors Directors(int i, String string) {
		return null;
	}
}