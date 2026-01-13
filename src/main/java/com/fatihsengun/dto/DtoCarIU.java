package com.fatihsengun.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoCarIU {

	@NotEmpty
	private String plate ;
	@NotEmpty
	private String brand;
	@NotEmpty
	private String model;

	@NotEmpty
	private String color;

	private Integer productionYear;

	private Integer km;

	private BigDecimal price;
	@NotEmpty
	private String currencyType;

	private BigDecimal damagePrice;


	@NotNull
	private boolean isCarSaled;
	
}
