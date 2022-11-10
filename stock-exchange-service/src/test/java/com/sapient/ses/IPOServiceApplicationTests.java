package com.sapient.ses;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tarun.ses.entity.IPO;
import com.tarun.ses.repository.IPORepository;
import com.tarun.ses.service.IPOServiceImpl;

@ExtendWith(MockitoExtension.class)
public class IPOServiceApplicationTests {

	@InjectMocks
	private IPOServiceImpl ipoServiceImpl;
	@Mock
	IPORepository ipoRepository;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void saveIpoDetailsTest() {
		IPO ipo = new IPO();
		when(ipoRepository.save(Mockito.any(IPO.class))).thenReturn(ipo);
		ResponseEntity<IPO> saved = ipoServiceImpl.save(ipo);
		assertEquals("201 CREATED", saved.getStatusCode().toString());
	}

	@Test
	public void ipoDetailsChronologicalOrderDESCTest() {
		ResponseEntity<List<IPO>> responseList = new ResponseEntity<>(HttpStatus.OK);
		when(ipoRepository.findAll(Sort.by(Sort.Direction.DESC, "openDateTime"))).thenReturn(responseList.getBody());
		ResponseEntity<List<IPO>> list = ipoServiceImpl.getChronologicalOrder("desc");
		assertEquals("200 OK", list.getStatusCode().toString());
	}

	@Test
	public void ipoDetailsChronologicalOrderASCTest() {
		ResponseEntity<List<IPO>> responseList = new ResponseEntity<>(HttpStatus.OK);
		when(ipoRepository.findAll(Sort.by(Sort.Direction.ASC, "openDateTime"))).thenReturn(responseList.getBody());
		ResponseEntity<List<IPO>> list = ipoServiceImpl.getChronologicalOrder("asc");
		assertEquals("200 OK", list.getStatusCode().toString());
	}

	@Test
	public void updateIPOTest() {
		IPO ipo = new IPO();
		when(ipoRepository.findByCompanyName("Sapient")).thenReturn(ipo);
		ipo.setCompanyName("Sapient");
		ResponseEntity<IPO> updated = ipoServiceImpl.update(ipo, "Sapient");
		assertEquals("200 OK", updated.getStatusCode().toString());
	}

	@Test
	public void updateIPONullTest() {
		IPO ipo = new IPO();
		when(ipoRepository.findByCompanyName(null)).thenReturn(ipo);
		ResponseEntity<IPO> updated = ipoServiceImpl.update(ipo, "Sapient");
		assertEquals("404 NOT_FOUND", updated.getStatusCode().toString());
	}
}
