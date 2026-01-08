package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.model.RootEntity;

public interface IRestAddressController {

public RootEntity<DtoAddress>  updateAddress(Long id, DtoAddressIU dtoAddressIU);

}
