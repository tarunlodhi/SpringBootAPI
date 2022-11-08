package com.tarun.ses.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tarun.ses.Utility;
import com.tarun.ses.dto.IPODTO;
import com.tarun.ses.entity.IPO;
import com.tarun.ses.service.IPOService;

@RequestMapping("/ipo")
@RestController
public class IPOController extends BaseController {
	@Autowired
	IPOService service;

	Logger logger = LoggerFactory.getLogger(IPOController.class);

	/**
	 * @param orderBy
	 * @return
	 */
	@GetMapping("")
	public ResponseEntity<List<IPO>> ipoDetailsChronologicalOrder(@RequestParam(required = false) String orderBy) {
		if (orderBy == null)
			orderBy = "asc";
		return service.getChronologicalOrder(orderBy);
	}

	/**
	 *
	 * @param ipoDetailDTO
	 * @return
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public ResponseEntity<IPO> saveIpoDetail(@Valid @RequestBody IPODTO ipoDTO) {
		return service.save((IPO) Utility.map(ipoDTO, IPO.class));
	}

	/**
	 *
	 * @param ipoDetailDTO
	 * @param name
	 * @return
	 */
	@PutMapping("/{name}")
	public ResponseEntity<IPO> updateIpoDetail(@RequestBody IPODTO ipoDetailDTO, @PathVariable String name) {
		return service.update((IPO) Utility.map(ipoDetailDTO, IPO.class), name);
	}
}