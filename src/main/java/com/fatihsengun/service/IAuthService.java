package com.fatihsengun.service;

import com.fatihsengun.dto.AuthRequest;
import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoUser;

public interface IAuthService {
	public DtoUser register(AuthRequest authRequest);
	public AuthResponse authenticate(AuthRequest authRequest);
	

}
