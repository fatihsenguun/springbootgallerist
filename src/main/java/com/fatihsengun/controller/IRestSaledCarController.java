package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoSaledCar;
import com.fatihsengun.dto.DtoSaledCarIU;
import com.fatihsengun.model.RootEntity;

public interface IRestSaledCarController {
    public RootEntity<DtoSaledCar> saleCar(DtoSaledCarIU dtoSaledCarIU);

}
