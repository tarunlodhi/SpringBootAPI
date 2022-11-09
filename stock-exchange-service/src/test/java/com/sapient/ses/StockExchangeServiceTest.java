package com.sapient.ses;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.tarun.ses.entity.StockExchange;
import com.tarun.ses.repository.IStockExchangeRepository;
import com.tarun.ses.service.StockExchangeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StockExchangeServiceTest {

	@Mock
	IStockExchangeRepository repository;

	@InjectMocks
	StockExchangeServiceImpl service;

	private List<StockExchange> stockExchangeList;
	private StockExchange stockExchange;
	private List<StockExchange> stockExchangeWithNullList;

	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		stockExchange = new StockExchange();
		stockExchangeList = Arrays.asList(new StockExchange());
		stockExchangeWithNullList = Arrays.asList();
	}

	@Test
	public void saveStockExchangeTest() {
		when(repository.save(Mockito.any(StockExchange.class))).thenReturn(stockExchange);
		ResponseEntity<StockExchange> saved = service.addStockExchange(stockExchange);
		assertEquals("201 CREATED", saved.getStatusCode().toString());
	}

	@Test
	public void findAllStockExchangeTest() {
		when(repository.findAll()).thenReturn(stockExchangeList);
		ResponseEntity<List<StockExchange>> get = service.getStockExchange();
		assertEquals("200 OK", get.getStatusCode().toString());
	}

	@Test
	public void findNoStockExchangeTest() {
		when(repository.findAll()).thenReturn(stockExchangeWithNullList);
		ResponseEntity<List<StockExchange>> saved = service.getStockExchange();
		assertEquals("204 NO_CONTENT", saved.getStatusCode().toString());
	}

	@Test
	public void updateWithNullStockExchangeTest() {
		when(repository.findByName("Sapient")).thenReturn(stockExchange);
		ResponseEntity<StockExchange> updated = service.updateStockExchange(stockExchange);
		assertEquals("404 NOT_FOUND", updated.getStatusCode().toString());
	}

	@Test
	public void updateStockExchangeTest() {
		when(repository.findByName("Sapient")).thenReturn(stockExchange);
		stockExchange.setName("Sapient");
		ResponseEntity<StockExchange> updated = service.updateStockExchange(stockExchange);
		assertEquals("200 OK", updated.getStatusCode().toString());
	}

	@Test
	public void updateStockNullExchangeTest() {
		when(repository.findByName(null)).thenReturn(stockExchange);
		stockExchange.setName(null);
		ResponseEntity<StockExchange> updated = service.updateStockExchange(stockExchange);
		assertEquals("200 OK", updated.getStatusCode().toString());
	}

}