package com.fatihsengun.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCarIU {

	@NotEmpty
	private String plate ;
	@NotEmpty
	private String brand;
	@NotEmpty
	private String model;

	private Integer productionYear;

	private BigDecimal price;
	@NotEmpty
	private String currencyType;

	private BigDecimal damagePrice;
	@NotEmpty
	private String carStatusType;
	
}
