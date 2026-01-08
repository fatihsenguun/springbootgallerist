package com.fatihsengun.controller;

import java.util.List;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoGalleristCar;
import com.fatihsengun.dto.DtoGalleristCarIU;
import com.fatihsengun.model.RootEntity;

public interface IRestGalleristCarController {
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

	public List<DtoCar>  findByGalleristId(Long id);

}
