package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoCustomer;
import com.fatihsengun.dto.DtoCustomerIU;
import com.fatihsengun.model.RootEntity;

public interface IRestCustomerController {
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
	public RootEntity<DtoCustomer> findById(Long id);

}
