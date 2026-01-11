package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestSaledCarController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoSaledCar;
import com.fatihsengun.dto.DtoSaledCarIU;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.ISaledCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {



    @Autowired
    private ISaledCarService saledCarService;



    @Override
    @PostMapping("/sell")
    public RootEntity<DtoSaledCar> saleCar( @RequestBody DtoSaledCarIU dtoSaledCarIU) {


        return ok(saledCarService.saleCar(dtoSaledCarIU));
    }
}
