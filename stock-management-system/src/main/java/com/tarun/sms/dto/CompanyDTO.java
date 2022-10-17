package com.tarun.sms.dto;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.tarun.sms.entity.Directors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {

	private Integer id;
	@Column(unique = true)
	@NotBlank(message = "Company name is mandatory")
	private String name;
	@NotNull(message = "Turnover is mandatory")
	@Range(min = (long) 100000.0, message = "Turnover need to be above 1L(100000.0)")
	private Double turnover;
	@Size(min = 3, max = 25, message = "Ceo Name is mandatory")
	private String ceo;
	@NotNull(message = "Status of company is mandatory")
	private Boolean status;
	@NotEmpty(message = "Please Add director")
	private List<Directors> directors;
	@NotBlank(message = "Sector details is mandatory")
	private String sector;
	private String briefDescription;
	@NotBlank(message = "NSE Stock code is mandatory")
	private String stockcodeNSE;
	@NotNull(message = "BSE Stock code is mandatory")
	private Integer stockcodeBSE;

}