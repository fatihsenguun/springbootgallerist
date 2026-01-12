package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestGalleristCarController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoGalleristCar;
import com.fatihsengun.dto.DtoGalleristCarIU;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	@GetMapping("/cars")
	public List<DtoCar> findByGalleristId() {

		return galleristCarService.findDtoGalleristCars();
	}

}
