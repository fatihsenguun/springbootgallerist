package com.fatihsengun.mapper;

import org.mapstruct.Mapper;

import com.fatihsengun.dto.DtoAccount;
import com.fatihsengun.dto.DtoAccountIU;
import com.fatihsengun.dto.DtoAddress;
import com.fatihsengun.dto.DtoAddressIU;
import com.fatihsengun.dto.DtoCar;
import com.fatihsengun.dto.DtoCarIU;
import com.fatihsengun.dto.DtoCustomer;
import com.fatihsengun.dto.DtoCustomerIU;
import com.fatihsengun.dto.DtoGallerist;
import com.fatihsengun.dto.DtoGalleristCar;
import com.fatihsengun.dto.DtoGalleristCarIU;
import com.fatihsengun.dto.DtoGalleristIU;
import com.fatihsengun.model.Account;
import com.fatihsengun.model.Address;
import com.fatihsengun.model.Car;
import com.fatihsengun.model.Customer;
import com.fatihsengun.model.Gallerist;
import com.fatihsengun.model.GalleristCar;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

	Customer toCustomerEntity(DtoCustomerIU dtoCustomerIU);

	DtoCustomer toCustomerDto(Customer customer);

	Address toAddressEntitu(DtoAddressIU dtoAddressIU);

	DtoAddress toAddressDto(Address address);

	Account toAccountEntity(DtoAccountIU dtoAccountIU);

	DtoAccount toAccountDto(Account account);

	Gallerist toGalleristEntity(DtoGalleristIU dtoGalleristIU);

	DtoGallerist toGalleristDto(Gallerist gallerist);

	Car toCarEntity(DtoCarIU dtoCarIU);

	DtoCar toCarDto(Car car);

	GalleristCar toGalleristCarEntity(DtoGalleristCarIU dtoGalleristCarIU);

	DtoGalleristCar tDtoGalleristCarDto(GalleristCar galleristCar);

}
