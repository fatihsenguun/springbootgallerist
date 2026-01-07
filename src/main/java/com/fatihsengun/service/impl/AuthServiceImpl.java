package com.fatihsengun.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fatihsengun.dto.DtoUser;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.jwt.AuthRequest;
import com.fatihsengun.jwt.AuthResponse;
import com.fatihsengun.jwt.JwtService;
import com.fatihsengun.model.RefreshToken;
import com.fatihsengun.model.User;
import com.fatihsengun.repository.AuthRepository;
import com.fatihsengun.repository.RefreshTokenRepository;
import com.fatihsengun.service.IAuthService;

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

	public DtoUser register(AuthRequest authRequest) {
		if (authRepository.existsByUsername(authRequest.getUsername())) {
			throw new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, authRequest.getUsername()));
		}
		User user = new User();

		user.setUsername(authRequest.getUsername());
		user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
		user.setCreateTime(new Date());

		DtoUser response = new DtoUser();
		BeanUtils.copyProperties(authRepository.save(user), response);
		return response;
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

			return new AuthResponse(accessToken, refreshToken.getRefreshToken());

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password");
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, authRequest.getUsername()));

		}

	}

}
