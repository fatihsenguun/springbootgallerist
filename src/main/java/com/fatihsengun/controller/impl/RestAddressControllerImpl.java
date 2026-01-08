package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestAddressController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IAddressService;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

	@Autowired
	private IAddressService addressService;

	@Override
	@PostMapping("/update/{id}")
	public RootEntity<DtoAddress> updateAddress(@PathVariable(name = "id") Long id,
			@RequestBody DtoAddressIU dtoAddressIU) {

		return ok(addressService.updateAddress(id, dtoAddressIU));
	}

}
