package com.tarun.ses.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tarun.ses.entity.StockExchange;
import com.tarun.ses.repository.IStockExchangeRepository;

@Service
public class StockExchangeServiceImpl implements IStockExchangeService {

	@Autowired
	IStockExchangeRepository repository;

	/**
	 *
	 * Add stock exchange
	 *
	 * @param stockExchange the stock exchange
	 * @return ResponseEntity<StockExchange>
	 */
	@Override
	@Transactional
	public ResponseEntity<StockExchange> addStockExchange(StockExchange stockExchange) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(stockExchange));
	}

	/**
	 *
	 * Gets the stock exchange
	 *
	 * @return the stock exchange
	 */
	@Override
	public ResponseEntity<List<StockExchange>> getStockExchange() {
		List<StockExchange> stockExchanges = repository.findAll();
		if (stockExchanges.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		return ResponseEntity.status(HttpStatus.OK).body(stockExchanges);
	}

	/**
	 *
	 * Update stock exchange
	 *
	 * @param stockExchange the stock exchange
	 * @return ResponseEntity<StockExchange>
	 */
	@Override
	@Transactional
	public ResponseEntity<StockExchange> updateStockExchange(StockExchange stockExchange) {
		StockExchange updatedStockExchange = repository.findByName(stockExchange.getName());
		if (updatedStockExchange == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		updatedStockExchange
				.setName(stockExchange.getName() != null ? stockExchange.getName() : updatedStockExchange.getName());
		updatedStockExchange.setRemarks(
				stockExchange.getRemarks() != null ? stockExchange.getRemarks() : updatedStockExchange.getRemarks());
		updatedStockExchange.setAddress(
				stockExchange.getAddress() != null ? stockExchange.getAddress() : updatedStockExchange.getAddress());
		updatedStockExchange.setBrief(
				stockExchange.getBrief() != null ? stockExchange.getBrief() : updatedStockExchange.getBrief());
		updatedStockExchange
				.setIpos(stockExchange.getIpos() != null ? stockExchange.getIpos() : updatedStockExchange.getIpos());
		repository.save(updatedStockExchange);
		return ResponseEntity.status(HttpStatus.OK).body(updatedStockExchange);

	}
}