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

import com.tarun.ses.controller.StockExchangeController;
import com.tarun.ses.dto.StockExchangeDTO;
import com.tarun.ses.entity.StockExchange;
import com.tarun.ses.service.IStockExchangeService;

@ExtendWith(MockitoExtension.class)
public class StockExchangeControllerTest {

	@InjectMocks
	StockExchangeController controller;

	@Mock
	IStockExchangeService service;

	private ResponseEntity<StockExchange> saveResponseStockExchange;
	private ResponseEntity<List<StockExchange>> responseStockExchangeListOk;
	private ResponseEntity<List<StockExchange>> responseStockExchangeListNoContent;
	private StockExchangeDTO stockExchangeDTO;
	private ResponseEntity<StockExchange> updateResponseStockExchange;
	private ResponseEntity<StockExchange> updateWithNullResponseStockExchange;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		saveResponseStockExchange = new ResponseEntity<>(HttpStatus.CREATED);
		updateResponseStockExchange = new ResponseEntity<>(HttpStatus.OK);
		stockExchangeDTO = new StockExchangeDTO();
		responseStockExchangeListNoContent = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		responseStockExchangeListOk = new ResponseEntity<>(HttpStatus.OK);
		updateWithNullResponseStockExchange = new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Test
	public void saveStockExchangeTest() {
		when(service.addStockExchange(Mockito.any(StockExchange.class))).thenReturn(saveResponseStockExchange);
		ResponseEntity<StockExchange> saved = controller.addStockExchange(stockExchangeDTO);
		assertEquals("201 CREATED", saved.getStatusCode().toString());
	}

	@Test
	public void findAllStockExchangeTest() {
		when(service.getStockExchange()).thenReturn(responseStockExchangeListOk);
		ResponseEntity<List<StockExchange>> get = controller.getAllStockExchange();
		assertEquals("200 OK", get.getStatusCode().toString());
	}

	@Test
	public void findNoStockExchangeTest() {
		when(service.getStockExchange()).thenReturn(responseStockExchangeListNoContent);
		ResponseEntity<List<StockExchange>> saved = controller.getAllStockExchange();
		assertEquals("204 NO_CONTENT", saved.getStatusCode().toString());
	}

	@Test
	public void updateStockExchange() {
		when(service.updateStockExchange(Mockito.any(StockExchange.class))).thenReturn(updateResponseStockExchange);
		ResponseEntity<StockExchange> updated = controller.updateStockExchange(stockExchangeDTO);
		assertEquals("200 OK", updated.getStatusCode().toString());
	}

	@Test
	public void updateWithNullStockExchange() {
		when(service.updateStockExchange(Mockito.any(StockExchange.class)))
				.thenReturn(updateWithNullResponseStockExchange);
		ResponseEntity<StockExchange> updated = controller.updateStockExchange(stockExchangeDTO);
		assertEquals("404 NOT_FOUND", updated.getStatusCode().toString());
	}

}