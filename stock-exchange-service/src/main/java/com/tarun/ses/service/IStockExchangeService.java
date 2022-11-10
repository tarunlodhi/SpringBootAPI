package com.tarun.ses.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tarun.ses.entity.StockExchange;

public interface IStockExchangeService {
	ResponseEntity<StockExchange> addStockExchange(StockExchange stockExchange);

	ResponseEntity<List<StockExchange>> getStockExchange();

	ResponseEntity<StockExchange> updateStockExchange(StockExchange map);
}