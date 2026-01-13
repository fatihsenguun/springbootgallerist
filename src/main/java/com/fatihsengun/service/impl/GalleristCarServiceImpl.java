package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoGalleristCar;
import com.fatihsengun.dto.DtoGalleristCarIU;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.Car;
import com.fatihsengun.model.Gallerist;
import com.fatihsengun.model.GalleristCar;
import com.fatihsengun.repository.CarRepository;
import com.fatihsengun.repository.GalleristCarRepository;
import com.fatihsengun.repository.GalleristRepository;
import com.fatihsengun.service.IGalleristCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private GalleristCarRepository galleristCarRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private IGlobalMapper globalMapper;

	@Autowired
	private IdentityService identityService;

	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		Car car = carRepository.findById(dtoGalleristCarIU.getCarId()).orElseThrow(() -> new BaseException(
				new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString())));
		Gallerist gallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId())
				.orElseThrow(() -> new BaseException(
						new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString())));

		GalleristCar galleristCar = new GalleristCar();

		galleristCar.setCar(car);
		galleristCar.setGallerist(gallerist);
		galleristCar.setCreateTime(new Date());

		return globalMapper.tDtoGalleristCarDto(galleristCarRepository.save(galleristCar));
	}

	@Override
	public List<DtoCar> findDtoGalleristCars() {
		List<DtoCar> response = new ArrayList<>();
		List<GalleristCar> cars = galleristCarRepository.findByGalleristId(identityService.getCurrentGallerist().getId());

		if (cars != null) {
			for (GalleristCar car : cars) {
				response.add(globalMapper.toCarDto(car.getCar()));
			}
			return response;

		}
		throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"galleristId :"+ identityService.getCurrentGallerist().getId().toString()));

	}



	public DtoCar findGalleristCarById(Long id){
		DtoCar dtoCar = new DtoCar();

		GalleristCar galleristCar =galleristCarRepository.findByCarId(id);

			if (galleristCar.getGallerist().getId()!=identityService.getCurrentGallerist().getId()){
				throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"carId: "+id));
			}

		return 	globalMapper.toCarDto(galleristCar.getCar());

	}
}




