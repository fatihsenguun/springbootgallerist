package com.fatihsengun.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCarIU  {


    @NotEmpty
    private  Long car;

    private DtoCustomerIU customer;

}
