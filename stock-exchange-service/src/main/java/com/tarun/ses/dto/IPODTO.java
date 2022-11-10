package com.tarun.ses.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPODTO {
	@Column(name = "companyName")
	@NotBlank(message = "Company name is Required")
	private String companyName;
	@DecimalMin(value = "0.0", inclusive = false, message = "Price of a share is required")
	@Digits(integer = 3, fraction = 2)
	private BigDecimal pricePerShare;
	private Integer totalShare;
	@NotNull(message = "Open date need to be added in dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date openDateTime;
	@Size(min = 5, max = 250)
	private String remarks;
	@NotNull(message = "Close date need to be added in dd-MM-yyyy")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date closeDateTime;
}