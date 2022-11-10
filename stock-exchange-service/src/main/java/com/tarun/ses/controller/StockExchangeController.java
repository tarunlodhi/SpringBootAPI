package com.tarun.ses.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarun.ses.Utility;
import com.tarun.ses.dto.StockExchangeDTO;
import com.tarun.ses.entity.StockExchange;
import com.tarun.ses.service.IStockExchangeService;

@RequestMapping("/stockExchange")
@RestController
public class StockExchangeController extends BaseController {
	@Autowired
	IStockExchangeService service;
	Logger logger;

	/**
	 * @param stockExchangeDTO
	 * @return
	 */
	public StockExchangeController() {
		logger = LoggerFactory.getLogger(StockExchange.class);
	}

	@PostMapping("")
	public ResponseEntity<StockExchange> addStockExchange(@Valid @RequestBody StockExchangeDTO stockExchangeDTO) {
		return service.addStockExchange((StockExchange) Utility.map(stockExchangeDTO, StockExchange.class));
	}

	@PutMapping("")
	public ResponseEntity<StockExchange> updateStockExchange(@RequestBody StockExchangeDTO stockExchangeDTO) {
		return service.updateStockExchange((StockExchange) Utility.map(stockExchangeDTO, StockExchange.class));
	}

	@GetMapping("")
	public ResponseEntity<List<StockExchange>> getAllStockExchange() {
		return service.getStockExchange();
	}
}