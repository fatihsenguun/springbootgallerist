package com.fatihsengun.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoCarIU;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.Car;
import com.fatihsengun.repository.CarRepository;
import com.fatihsengun.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private IGlobalMapper globalMapper;

	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		Car car = globalMapper.toCarEntity(dtoCarIU);
		car.setCreateTime(new Date());
		return globalMapper.toCarDto(carRepository.save(car));
		
	}

}
