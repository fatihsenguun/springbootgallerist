package com.fatihsengun.dto;

import java.math.BigDecimal;

import com.fatihsengun.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCar extends BaseEntity {

	private String plate;

	private String brand;

	private String model;

	private Integer productionYear;

	private BigDecimal price;

	private String currencyType;

	private BigDecimal damagePrice;

	private String carStatusType;

}
