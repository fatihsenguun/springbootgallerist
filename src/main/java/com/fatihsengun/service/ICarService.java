package com.fatihsengun.service;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);

}
