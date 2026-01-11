package com.fatihsengun.service;

import com.fatihsengun.dto.DtoSaledCar;
import com.fatihsengun.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar saleCar(DtoSaledCarIU dtoSaledCarIU);
}
