package com.fatihsengun.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestGalleristCarController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoGalleristCar;
import com.fatihsengun.dto.DtoGalleristCarIU;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/galleristcar")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

	@Autowired
	private IGalleristCarService galleristCarService;

	@Override
	@PostMapping("/save")
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {

		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

	@Override
	@GetMapping("/car/{id}")
	public List<DtoCar> findByGalleristId(@PathVariable(name = "id") Long id) {

		return galleristCarService.findDtoGalleristCars(id);
	}

}
