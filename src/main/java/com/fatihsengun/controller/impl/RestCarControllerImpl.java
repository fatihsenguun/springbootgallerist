package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestCarController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoCarIU;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

	@Autowired
	private ICarService carService;

	@Override
	@PostMapping("/save")
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {

		return ok(carService.saveCar(dtoCarIU));
	}

}
