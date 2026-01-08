package com.fatihsengun.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.Address;
import com.fatihsengun.repository.AddressRepository;
import com.fatihsengun.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private IGlobalMapper globalMapper;


	@Override
	public DtoAddress updateAddress(Long id, DtoAddressIU dtoAddressIU) {
		addressRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.OBJECT_NOT_FOUND, id.toString())));
		Address address = globalMapper.toAddressEntitu(dtoAddressIU);
		address.setId(id);
		address.setCreateTime(new Date());
		return globalMapper.toAddressDto(addressRepository.save(address));
	}

}
