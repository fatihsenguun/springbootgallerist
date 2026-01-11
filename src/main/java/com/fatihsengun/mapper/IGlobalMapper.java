package com.fatihsengun.mapper;

import com.fatihsengun.dto.*;
import com.fatihsengun.model.*;
import org.mapstruct.Mapper;

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

	DtoSaledCar toDtoSaledCar(SaledCar saledCar);

}
