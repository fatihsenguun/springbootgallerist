package com.fatihsengun.service;

import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;

public interface IAddressService {

	

	public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU);

}
