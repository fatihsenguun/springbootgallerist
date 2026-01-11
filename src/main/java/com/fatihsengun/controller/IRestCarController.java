package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoCarIU;
import com.fatihsengun.model.RootEntity;

public interface IRestCarController {
	
	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
	

}
