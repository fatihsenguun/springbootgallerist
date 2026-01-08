package com.fatihsengun.service;

import com.fatihsengun.dto.DtoCustomer;
import com.fatihsengun.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
	public DtoCustomer findById(Long id);
	
}
