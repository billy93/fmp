package com.atibusinessgroup.fmp.web.rest;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.SystemParameter;
import com.atibusinessgroup.fmp.domain.User;
import com.atibusinessgroup.fmp.repository.UserRepository;
import com.atibusinessgroup.fmp.security.UserErrorException;
import com.atibusinessgroup.fmp.security.jwt.JWTConfigurer;
import com.atibusinessgroup.fmp.security.jwt.TokenProvider;
import com.atibusinessgroup.fmp.service.SystemParameterService;
import com.atibusinessgroup.fmp.service.dto.PasswordHistory;
import com.atibusinessgroup.fmp.web.rest.vm.LoginVM;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

	private final Logger log = LoggerFactory.getLogger(UserJWTController.class);

	private final TokenProvider tokenProvider;

	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;

	private final SystemParameterService systemParameterService;

	public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager,
			UserRepository userRepository, SystemParameterService systemParameterService) {
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.systemParameterService = systemParameterService;
	}

	@PostMapping("/authenticate")
	@Timed
	public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
		log.debug("Request to authenticate user: {}", loginVM.getUsername());

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginVM.getUsername(), loginVM.getPassword());

		Optional<User> userOptional = userRepository.findOneByLogin(loginVM.getUsername());

		try {
			Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
			String jwt = tokenProvider.createToken(authentication, rememberMe);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
			
			if (userOptional.isPresent()) {
				User user = userOptional.get();

				user.setFailedLoginCounter(0);
				user.setLastLoginDateTime(Instant.now());

				if (userNeedsToChangePassword(user)) {
					httpHeaders.add("ChangePasswordNeeded", "1");
				} else {
					httpHeaders.add("ChangePasswordNeeded", "0");
				}

				user = userRepository.save(user);
			}

			return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
		} catch (org.springframework.security.authentication.BadCredentialsException ex) {
			if (userOptional.isPresent()) {
				User user = userOptional.get();

				user.setFailedLoginCounter(user.getFailedLoginCounter() + 1);

				if (user.getFailedLoginCounter() >= systemParameterService
						.getParameterNameValue(SystemParameter.MAX_FAILED_LOGIN_COUNTERS)) {
					user.setLocked(true);
					user.setLastLockoutDateTime(Instant.now());
				}

				user = userRepository.save(user);
			}

			throw new UserErrorException("Invalid credentials");
		}
	}

	private boolean userNeedsToChangePassword(User user) {
		boolean result = false;

		List<PasswordHistory> history = user.getPasswordHistory();

		log.debug("BEFORE SORT");
		for (PasswordHistory ph : history) {
			log.debug(ph.toString());
		}

		Collections.sort(history, new Comparator<PasswordHistory>() {
			@Override
			public int compare(PasswordHistory o1, PasswordHistory o2) {
				return o2.getModifiedDateTime().compareTo(o1.getModifiedDateTime());
			}
		});

		log.debug("AFTER SORT");
		for (PasswordHistory ph : history) {
			log.debug(ph.toString());
		}

		if (history != null && history.size() > 0) {
			PasswordHistory latest = history.get(0);

			Instant lastModifiedPassword = latest.getModifiedDateTime();
			Duration duration = Duration.between(lastModifiedPassword, Instant.now());
			long days = duration.toDays();

			if (days > systemParameterService.getParameterNameValue(SystemParameter.MAX_PASSWORD_AGE_IN_DAYS)) {
				result = true;
			}

		} else {
			// PasswordHistory initPassword = new PasswordHistory();
			// initPassword.setModifiedDateTime(user.getLastLoginDateTime());
			// initPassword.setPasswordHash(user.getPassword());
			//
			// user.getPasswordHistory().add(initPassword);
			// user = userRepository.save(user);
		}

		return result;
	}

	/**
	 * Object to return as body in JWT Authentication.
	 */
	static class JWTToken {

		private String idToken;

		JWTToken(String idToken) {
			this.idToken = idToken;
		}

		@JsonProperty("id_token")
		String getIdToken() {
			return idToken;
		}

		void setIdToken(String idToken) {
			this.idToken = idToken;
		}
	}
}
