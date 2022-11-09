package com.tarun.ses.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tarun.ses.entity.IPO;
import com.tarun.ses.exception.AlreadyExistException;
import com.tarun.ses.repository.IPORepository;

@Service
public class IPOServiceImpl implements IPOService {

	@Autowired
	IPORepository repository;

	/**
	 *
	 * @param orderBy
	 * @return
	 */
	@Override
	public ResponseEntity<List<IPO>> getChronologicalOrder(String orderBy) {
		Enum<Direction> direction;
		if (orderBy.equals("desc"))
			direction = Sort.Direction.DESC;
		else
			direction = Sort.Direction.ASC;
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.findAll(Sort.by((Sort.Direction) direction, "openDateTime")));
	}

	/**
	 *
	 * @param ipoDetail
	 * @return
	 */
	@Override
	@Transactional
	public ResponseEntity<IPO> save(IPO ipoDetail) {
		Date date = new Date();
		ipoDetail.setOpenDateTime(date);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(ipoDetail));
	}

	/**
	 *
	 * @param ipoDetail
	 * @param name
	 * @return
	 * @throws AlreadyExistException
	 */
	@Override
	@Transactional
	public ResponseEntity<IPO> update(IPO ipoDetail, String name) {
		IPO updateIpoDetail = repository.findByCompanyName(name);
		if (updateIpoDetail == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		updateIpoDetail.setCompanyName(ipoDetail.getCompanyName());
		updateIpoDetail.setTotalShare(ipoDetail.getTotalShare());
		updateIpoDetail.setPricePerShare(ipoDetail.getPricePerShare());
		updateIpoDetail.setRemarks(ipoDetail.getRemarks());
		updateIpoDetail.setOpenDateTime(ipoDetail.getOpenDateTime());
		updateIpoDetail.setCloseDateTime(ipoDetail.getCloseDateTime());
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(updateIpoDetail));
	}

}