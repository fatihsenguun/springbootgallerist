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

	private Integer productionYear;

	private BigDecimal price;

	private String currencyType;

	private BigDecimal damagePrice;

	private String carStatusType;

	private boolean isCarSaled;

}
