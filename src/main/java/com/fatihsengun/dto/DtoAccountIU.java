package com.fatihsengun.dto;

import java.math.BigDecimal;

import com.fatihsengun.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccountIU {
	
	@NotNull(message = "AccountNo Cannot be Empty")
	private String accountNo;
	
	@NotNull(message = "IBAN Cannot be Empty")
	private String iban;
	
	@NotNull(message = "Amount Cannot be Empty")
	private BigDecimal amount;
	
	@NotNull(message = "CurrencyType Cannot be Empty")
	private CurrencyType currencyType;
}
