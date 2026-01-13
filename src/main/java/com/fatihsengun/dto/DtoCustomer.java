package com.fatihsengun.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer  extends DtoBase{
	
	
	private String firstName;

	private String lastName;

	private String tckn;

	private String birthOfDate;

	private DtoAddress address;


}
