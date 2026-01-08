package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoAccount;
import com.fatihsengun.dto.DtoAccountIU;
import com.fatihsengun.model.RootEntity;

public interface IRestAccountController {

	public RootEntity<DtoAccount> updateAccount(Long id, DtoAccountIU dtoAccountIU);

}
