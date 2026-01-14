package com.fatihsengun.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGallerist extends DtoBase {

	private String galleristName;

	private DtoAddress address;
}
