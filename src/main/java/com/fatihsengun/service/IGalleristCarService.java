package com.fatihsengun.service;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoGalleristCar;
import com.fatihsengun.dto.DtoGalleristCarIU;

import java.util.List;

public interface IGalleristCarService {
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
	public List<DtoCar>  findDtoGalleristCars();

}
