package com.fatihsengun.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoAccount;
import com.fatihsengun.dto.DtoAccountIU;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.Account;
import com.fatihsengun.repository.AccountRepository;
import com.fatihsengun.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private IGlobalMapper globalMapper;

	@Override
	public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU) {

		accountRepository.findById(id)
				.orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.OBJECT_NOT_FOUND, id.toString())));
		Account account = globalMapper.toAccountEntity(dtoAccountIU);
		account.setId(id);
		account.setCreateTime(new Date());

		return globalMapper.toAccountDto(accountRepository.save(account));
	}

}
