package com.tarun.ses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarun.ses.entity.StockExchange;

@Repository
public interface IStockExchangeRepository extends JpaRepository<StockExchange, Integer> {
	StockExchange findByName(String name);
}