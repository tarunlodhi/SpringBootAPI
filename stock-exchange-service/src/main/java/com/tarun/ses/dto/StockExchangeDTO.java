package com.tarun.ses.dto;

import java.util.Set;

import com.tarun.ses.entity.Address;
import com.tarun.ses.entity.IPO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockExchangeDTO {
	private String name;
	private String brief;
	private String remarks;
	private Address address;
	private Set<IPO> ipos;
}
