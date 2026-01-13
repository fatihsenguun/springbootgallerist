package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoGalleristCar;
import com.fatihsengun.dto.DtoGalleristCarIU;
import com.fatihsengun.model.RootEntity;

import java.util.List;

public interface IRestGalleristCarController {
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

	public RootEntity<List<DtoCar> > findByGalleristId();
	public RootEntity<DtoCar> findByCarId(Long id);

}
