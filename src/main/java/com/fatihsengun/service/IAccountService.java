package com.fatihsengun.service;

import com.fatihsengun.dto.DtoAccount;
import com.fatihsengun.dto.DtoAccountIU;

public interface IAccountService {

	DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU);

}
