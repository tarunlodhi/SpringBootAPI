package com.tarun.ses.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ipo")
public class IPO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(unique = true)
	private String companyName;
	private Float pricePerShare;
	private Integer totalShare;
	private Date openDateTime;
	private Date closeDateTime;
	private String remarks;
}