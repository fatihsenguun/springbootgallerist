package com.fatihsengun.service.impl;

import com.fatihsengun.dto.AuthRequest;
import com.fatihsengun.dto.AuthResponse;
import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.dto.RegisterRequest;
import com.fatihsengun.enums.RoleType;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.jwt.JwtService;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.model.Gallerist;
import com.fatihsengun.model.RefreshToken;
import com.fatihsengun.model.User;
import com.fatihsengun.repository.AuthRepository;
import com.fatihsengun.repository.GalleristRepository;
import com.fatihsengun.repository.RefreshTokenRepository;
import com.fatihsengun.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private IGlobalMapper globalMapper;

	public DtoUser register(RegisterRequest authRequest) {
		if (authRepository.existsByUsername(authRequest.getUsername())) {
			throw new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, authRequest.getUsername()));
		}
		User user = new User();

		user.setUsername(authRequest.getUsername());
		user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
		user.setRole(authRequest.getRole());
		user.setCreateTime(new Date());
if (authRequest.getRole()== RoleType.ADMIN){
	Gallerist gallerist = new Gallerist();
	galleristRepository.save(gallerist);
	user.setGallerist(gallerist);
} else if (authRequest.getRole()==RoleType.EMPLOYEE) {
	if (authRequest.getGalleristId()==null){
		throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,"galleristId"));
	}
	Gallerist findedGallerist = galleristRepository.findById(authRequest.getGalleristId())
			.orElseThrow(()-> new BaseException(new ErrorMessage(MessageType.OBJECT_NOT_FOUND,"gallerist")));
	user.setGallerist(findedGallerist);
}
authRepository.save(user);
		return globalMapper.toDtoUser(user);
	}

	@Override
	public AuthResponse authenticate(AuthRequest authRequest) {
		try {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					authRequest.getUsername(), authRequest.getPassword());
			authenticationProvider.authenticate(auth);

			Optional<User> optional = authRepository.findByUsername(authRequest.getUsername());

			Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(optional.get().getId());
			if (optionalRefreshToken.isPresent()) {
				refreshTokenRepository.delete(optionalRefreshToken.get());
			}

			String accessToken = jwtService.generateToken(optional.get());
			RefreshToken refreshToken = refreshTokenService.saveRefreshToken(optional.get());

			return new AuthResponse(accessToken, refreshToken.getRefreshToken(), optional.get().getGallerist().getId().toString());

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password");
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, authRequest.getUsername()));

		}

	}

}
