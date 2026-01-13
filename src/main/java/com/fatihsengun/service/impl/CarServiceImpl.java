package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoCarIU;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.Car;
import com.fatihsengun.model.GalleristCar;
import com.fatihsengun.repository.CarRepository;
import com.fatihsengun.repository.GalleristCarRepository;
import com.fatihsengun.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private IGlobalMapper globalMapper;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private GalleristCarRepository galleristCarRepository;



	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		Car car = globalMapper.toCarEntity(dtoCarIU);
		GalleristCar galleristCar = new GalleristCar();
		car.setCreateTime(new Date());

		Car responseCar = carRepository.save(car);
	galleristCar.setCar(responseCar);
	galleristCar.setGallerist(identityService.getCurrentGallerist());
	galleristCar.setCreateTime(new Date());
	galleristCarRepository.save(galleristCar);

		return globalMapper.toCarDto(responseCar);
		
	}

}
