package com.fatihsengun.dto;

import com.fatihsengun.enums.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;


    @Enumerated(EnumType.STRING)
    private RoleType role;


    private DtoGalleristIU gallerist;
}
