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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tarun.ses.controller.IPOController;
import com.tarun.ses.dto.IPODTO;
import com.tarun.ses.entity.IPO;
import com.tarun.ses.service.IPOService;

@ExtendWith(MockitoExtension.class)
public class IPOControllerTest {

	@InjectMocks
	IPOController controller;

	@Mock
	IPOService service;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void saveIpoDetailsTest() {
		IPODTO ipoDTO = new IPODTO();
		ResponseEntity<IPO> responseIpoDetail = new ResponseEntity<>(HttpStatus.CREATED);
		when(service.save(Mockito.any(IPO.class))).thenReturn(responseIpoDetail);
		ResponseEntity<IPO> saved = controller.saveIpoDetail(ipoDTO);
		assertEquals("201 CREATED", saved.getStatusCode().toString());
	}

	@Test
	public void ipoDetailsChronologicalOrderDESCTest() {
		ResponseEntity<List<IPO>> responseList = new ResponseEntity<>(HttpStatus.OK);
		when(service.getChronologicalOrder("desc")).thenReturn(responseList);
		String value = "desc";
		ResponseEntity<List<IPO>> list = controller.ipoDetailsChronologicalOrder(value);
		assertEquals("200 OK", list.getStatusCode().toString());
	}

	@Test
	public void updateTest() {
		IPODTO ipoDTO = new IPODTO();
		when(service.update(Mockito.any(IPO.class), Mockito.eq("Sapient")))
				.thenReturn(new ResponseEntity<>(HttpStatus.OK));
		ResponseEntity<IPO> response = controller.updateIpoDetail(ipoDTO, "Sapient");
		assertEquals("200 OK", response.getStatusCode().toString());
	}
}