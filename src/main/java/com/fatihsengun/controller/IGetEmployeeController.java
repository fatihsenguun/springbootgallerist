package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.model.RootEntity;

import java.util.List;

public interface IGetEmployeeController {
    public RootEntity<List<DtoUser>> getEmployee();
}
