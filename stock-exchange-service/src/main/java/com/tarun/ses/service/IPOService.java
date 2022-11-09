package com.tarun.ses.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.tarun.ses.entity.IPO;

public interface IPOService {
	ResponseEntity<List<IPO>> getChronologicalOrder(String orderBy);

	ResponseEntity<IPO> save(IPO map);

	ResponseEntity<IPO> update(IPO map, String name);
}
