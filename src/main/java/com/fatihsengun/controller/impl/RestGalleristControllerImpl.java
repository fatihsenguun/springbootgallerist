package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestGalleristController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoGallerist;
import com.fatihsengun.dto.DtoGalleristIU;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IGalleristService;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {

	@Autowired
	private IGalleristService galleristService;

	@Override
	@PostMapping("/save")
	public RootEntity<DtoGallerist> saveGallerist(@RequestBody DtoGalleristIU dtoGalleristIU) {

		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}

}
