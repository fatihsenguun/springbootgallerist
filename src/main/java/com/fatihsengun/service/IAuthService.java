package com.fatihsengun.service;

import com.fatihsengun.dto.AuthRequest;
import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.dto.RegisterRequest;

public interface IAuthService {
	public DtoUser register(RegisterRequest authRequest);
	public AuthResponse authenticate(AuthRequest authRequest);
	

}
