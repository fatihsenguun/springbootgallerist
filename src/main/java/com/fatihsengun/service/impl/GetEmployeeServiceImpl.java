package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.User;
import com.fatihsengun.repository.GetEmployeeRepository;
import com.fatihsengun.service.IGetEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetEmployeeServiceImpl implements IGetEmployeeService {

    @Autowired
    private GetEmployeeRepository getEmployeeRepository;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private IGlobalMapper globalMapper;

    @Override
    public List<DtoUser> getEmployee() {
        List<User> users = getEmployeeRepository.findByGallerist(identityService.getCurrentGallerist());
        if (!users.isEmpty()) {
            List<DtoUser> dtoUsers = new ArrayList<>();
            for (User user : users) {
                dtoUsers.add(globalMapper.toDtoUser(user));
            }
            return dtoUsers;
        }
        return List.of();
    }
}
