package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestCustomerController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoCustomer;
import com.fatihsengun.dto.DtoCustomerIU;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

	@Autowired
	private ICustomerService customerService;

	@Override
	@PostMapping("/save")
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

	@Override
	@GetMapping("/list/{id}")
	public RootEntity<DtoCustomer> findById(@PathVariable(name = "id") Long id) {
		return ok(customerService.findById(id));
	}

}
