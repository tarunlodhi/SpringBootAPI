package com.tarun.sms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tarun.sms.dto.CompanyDTO;
import com.tarun.sms.entity.Company;
import com.tarun.sms.entity.Directors;
import com.tarun.sms.exception.AlreadyExistException;
import com.tarun.sms.repository.ICompanyRepository;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

	@InjectMocks
	private CompanyServiceImpl companyServiceImpl;

	@Mock
	ICompanyRepository companyRepository;

	@Test
	void getCompany() {
		List<Company> list = new ArrayList<>();
		when(companyRepository.findAll()).thenReturn(list);
		List<Company> listReturned = companyServiceImpl.getAllCompany();
		assertIterableEquals(list, listReturned);
	}

	@Test
	void existsByNameTest() {
		CompanyDTO companydto = new CompanyDTO();
		sampleParameters(companydto);
		Company company = new Company(companydto);
		when(companyRepository.existsByName(Mockito.anyString())).thenReturn(true);
		assertThrows(AlreadyExistException.class, () -> companyServiceImpl.createCompany(company));
	}

	@Test
	void getCompanyByName() {
		String name = "Company";
		List<Company> list = new ArrayList<>();
		when(companyRepository.searchByNameStartsWith(name)).thenReturn(list);
		List<Company> listReturned = companyServiceImpl.getSearchCompany(name);
		assertIterableEquals(list, listReturned);
	}

	@Test
	void testCreateCompany() {// AAA
		CompanyDTO companydto = new CompanyDTO();
		sampleParameters(companydto);
		Company company = new Company(companydto);
		when(companyRepository.save(company)).thenReturn(company);
		Company result = companyServiceImpl.createCompany(company);
		assertEquals("Salil Parekh", result.getCeo());
	}

	@Test
	void deleteCompanyTest() {
		CompanyDTO companydto = new CompanyDTO();
		sampleParameters(companydto);
		Company company = new Company(companydto);
		when(companyRepository.findById(1)).thenReturn(Optional.of(company));
		Company deleted = companyServiceImpl.delete(1);
		assertEquals("Salil Parekh", deleted.getCeo());
	}

	@Test
	void updateCompanyTest() {
		CompanyDTO companydto = new CompanyDTO();
		Integer id = 1;
		sampleParameters(companydto);
		Company company = new Company(companydto);
		when(companyRepository.findById(id)).thenReturn(Optional.of(company)).thenReturn(Optional.of(company));
		when(companyRepository.save(company)).thenReturn(company);
		Company updated = companyServiceImpl.updateCompany(id, company);
		assertEquals("Salil Parekh", updated.getCeo());
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