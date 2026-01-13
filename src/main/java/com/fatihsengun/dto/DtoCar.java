package com.fatihsengun.dto;

import com.fatihsengun.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoCar extends BaseEntity {

	private String plate;

	private String brand;

	private String model;

	private String color;

	private Integer productionYear;

	private BigDecimal price;

	private Integer km;

	private String currencyType;

	private BigDecimal damagePrice;


	private boolean isCarSaled;

}
