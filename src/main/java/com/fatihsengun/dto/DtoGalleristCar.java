package com.fatihsengun.dto;

import com.fatihsengun.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCar extends BaseEntity {

	private DtoGallerist gallerist;

	private DtoCar car;

}
