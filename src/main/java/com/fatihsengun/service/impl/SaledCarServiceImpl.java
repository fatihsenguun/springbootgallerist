package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoSaledCar;
import com.fatihsengun.dto.DtoSaledCarIU;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.*;
import com.fatihsengun.repository.*;
import com.fatihsengun.service.ISaledCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private IGlobalMapper globalMapper;

    @Autowired
    private IdentityService identityService;


    @Override
    public DtoSaledCar saleCar(DtoSaledCarIU dtoSaledCarIU) {
        Car car = carRepository.findById(dtoSaledCarIU.getCar())
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.NO_RECORD_EXIST, "CarId : " + dtoSaledCarIU.getCar().toString())));
        Gallerist gallerist = identityService.getCurrentGallerist();
        Customer customer = new Customer();
        Optional<Customer> optionalCustomer = customerRepository.findByTckn(dtoSaledCarIU.getCustomer().getTckn());

        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        } else {
            customer = customerRepository.save(globalMapper.toCustomerEntity(dtoSaledCarIU.getCustomer()));
            customer.setCreateTime(new Date());
        }


        car.setCarSaled(true);
        carRepository.save(car);
        if (galleristCarRepository.findByCarId(car.getId()) == null) {
            GalleristCar galleristCar = new GalleristCar();
            galleristCar.setCar(car);
            galleristCar.setGallerist(gallerist);
            galleristCarRepository.save(galleristCar);
        }

        SaledCar saledCar = new SaledCar();
        saledCar.setCar(car);
        saledCar.setCustomer(customer);
        saledCar.setGallerist(gallerist);
        saledCar.setCreateTime(new Date());
        DtoSaledCar response = globalMapper.toDtoSaledCar(saledCarRepository.save(saledCar));
        return response;
    }
}
