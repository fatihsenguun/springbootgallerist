package com.fatihsengun.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatihsengun.controller.IRestAccountController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoAccount;
import com.fatihsengun.dto.DtoAccountIU;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IAccountService;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

	@Autowired
	private IAccountService accountService;

	@Override
	@PostMapping("/update/{id}")
	public RootEntity<DtoAccount> updateAccount(@PathVariable(name = "id") Long id,
			@RequestBody DtoAccountIU dtoAccountIU) {

		return ok(accountService.updateAccount(id, dtoAccountIU));
	}

}
