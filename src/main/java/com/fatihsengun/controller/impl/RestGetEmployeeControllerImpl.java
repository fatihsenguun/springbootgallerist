package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IGetEmployeeController;
import com.fatihsengun.controller.RestBaseController;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.model.RootEntity;
import com.fatihsengun.service.IGetEmployeeService;
import com.fatihsengun.service.impl.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/api")
public class RestGetEmployeeControllerImpl extends RestBaseController implements IGetEmployeeController {

    @Autowired
    private IGetEmployeeService getEmployeeService;

    @Autowired
    private IdentityService identityService;

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/get/employee")
    public RootEntity<List<DtoUser>> getEmployee() {

        return ok(getEmployeeService.getEmployee());
    }
}
