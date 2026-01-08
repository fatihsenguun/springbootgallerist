package com.fatihsengun.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoCustomer;
import com.fatihsengun.dto.DtoCustomerIU;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.Customer;
import com.fatihsengun.repository.CustomerRepository;
import com.fatihsengun.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private IGlobalMapper globalMapper;

	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {

		Customer customer = globalMapper.toCustomerEntity(dtoCustomerIU);
		Date currentTime = new Date();
		customer.setCreateTime(currentTime);
		customer.getAddress().setCreateTime(currentTime);
		customer.getAccount().setCreateTime(currentTime);
		return globalMapper.toCustomerDto((customerRepository.save(customer)));
	}

	@Override
	public DtoCustomer findById(Long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
		}
		Customer customer = optional.get();
		return globalMapper.toCustomerDto(customer);
		
		//Customer customer = customerRepository.findById(id).orElseThrow(()->new BaseException());
		

	}

}
