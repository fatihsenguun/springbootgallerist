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

	private Integer productionYear;

	private BigDecimal price;
	@NotEmpty
	private String currencyType;

	private BigDecimal damagePrice;
	@NotEmpty
	private String carStatusType;

	@NotNull
	private boolean isCarSaled;
	
}
