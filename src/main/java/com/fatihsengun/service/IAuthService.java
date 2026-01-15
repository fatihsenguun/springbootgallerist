package com.fatihsengun.service;

import com.fatihsengun.dto.*;

public interface IAuthService {
	public DtoUser register(RegisterRequest authRequest);
	public AuthResponse authenticate(AuthRequest authRequest);
	public DtoUser registerEmp(RegisterRequestEmp authRequest);
	

}
