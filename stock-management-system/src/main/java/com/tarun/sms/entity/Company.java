package com.tarun.sms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import com.tarun.sms.dto.CompanyDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Company {
	public Company() {

	}

	public Company(@Valid CompanyDTO companyDTO) {
		name = companyDTO.getName().trim();
		briefDescription = companyDTO.getBriefDescription().trim();
		status = companyDTO.getStatus();
		turnover = companyDTO.getTurnover();
		ceo = companyDTO.getCeo().trim();
		directors = companyDTO.getDirectors();
		sector = companyDTO.getSector().trim();
		stockcodeBSE = companyDTO.getStockcodeBSE();
		stockcodeNSE = companyDTO.getStockcodeNSE().trim();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double turnover;
	private Boolean status;
	private String ceo;
	private String sector;
	private String briefDescription;
	private String stockcodeNSE;
	private Integer stockcodeBSE;
	@OneToMany(targetEntity = Directors.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "companyId", referencedColumnName = "id")
	private List<Directors> directors;
}
