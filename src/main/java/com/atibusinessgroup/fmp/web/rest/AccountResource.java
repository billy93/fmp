package com.atibusinessgroup.fmp.web.rest;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.SystemParameter;
import com.atibusinessgroup.fmp.domain.User;
import com.atibusinessgroup.fmp.repository.UserRepository;
import com.atibusinessgroup.fmp.security.SecurityUtils;
import com.atibusinessgroup.fmp.service.MailService;
import com.atibusinessgroup.fmp.service.SystemParameterService;
import com.atibusinessgroup.fmp.service.UserService;
import com.atibusinessgroup.fmp.service.dto.PasswordHistory;
import com.atibusinessgroup.fmp.service.dto.UserDTO;
import com.atibusinessgroup.fmp.web.rest.errors.EmailAlreadyUsedException;
import com.atibusinessgroup.fmp.web.rest.errors.EmailNotFoundException;
import com.atibusinessgroup.fmp.web.rest.errors.InternalServerErrorException;
import com.atibusinessgroup.fmp.web.rest.errors.InvalidPasswordException;
import com.atibusinessgroup.fmp.web.rest.errors.LoginAlreadyUsedException;
import com.atibusinessgroup.fmp.web.rest.vm.KeyAndPasswordVM;
import com.atibusinessgroup.fmp.web.rest.vm.ManagedUserVM;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

	private final Logger log = LoggerFactory.getLogger(AccountResource.class);

	private final UserRepository userRepository;

	private final UserService userService;

	private final MailService mailService;

	private final PasswordEncoder passwordEncoder;

	private final SystemParameterService systemParameterService;

	public AccountResource(UserRepository userRepository, UserService userService, MailService mailService,
			PasswordEncoder passwordEncoder, SystemParameterService systemParameterService) {

		this.userRepository = userRepository;
		this.userService = userService;
		this.mailService = mailService;
		this.passwordEncoder = passwordEncoder;
		this.systemParameterService = systemParameterService;
	}

	/**
	 * POST /register : register the user.
	 *
	 * @param managedUserVM
	 *            the managed user View Model
	 * @throws InvalidPasswordException
	 *             400 (Bad Request) if the password is incorrect
	 * @throws EmailAlreadyUsedException
	 *             400 (Bad Request) if the email is already used
	 * @throws LoginAlreadyUsedException
	 *             400 (Bad Request) if the login is already used
	 */
	@PostMapping("/register")
	@Timed
	@ResponseStatus(HttpStatus.CREATED)
	public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
		int minLengthPassword = systemParameterService.getParameterNameValue(SystemParameter.PASSWORD_MIN_LENGTH),
				maxLengthPassword = systemParameterService.getParameterNameValue(SystemParameter.PASSWORD_MAX_LENGTH);
		if (!checkPasswordLength(managedUserVM.getPassword(), minLengthPassword, maxLengthPassword)) {
			throw new InvalidPasswordException("Password length must be at least " + minLengthPassword
					+ " characters and cannot be longer than " + maxLengthPassword + " characters");
		}
		userRepository.findOneByLogin(managedUserVM.getLogin().toLowerCase()).ifPresent(u -> {
			throw new LoginAlreadyUsedException();
		});
		userRepository.findOneByEmailIgnoreCase(managedUserVM.getEmail()).ifPresent(u -> {
			throw new EmailAlreadyUsedException();
		});
		User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
//		mailService.sendActivationEmail(user);
	}

	/**
	 * GET /activate : activate the registered user.
	 *
	 * @param key
	 *            the activation key
	 * @throws RuntimeException
	 *             500 (Internal Server Error) if the user couldn't be activated
	 */
	@GetMapping("/activate")
	@Timed
	public void activateAccount(@RequestParam(value = "key") String key) {
		Optional<User> user = userService.activateRegistration(key);
		if (!user.isPresent()) {
			throw new InternalServerErrorException("No user was found for this reset key");
		}
	}

	/**
	 * GET /authenticate : check if the user is authenticated, and return its login.
	 *
	 * @param request
	 *            the HTTP request
	 * @return the login if the user is authenticated
	 */
	@GetMapping("/authenticate")
	@Timed
	public String isAuthenticated(HttpServletRequest request) {
		log.debug("REST request to check if the current user is authenticated");
		return request.getRemoteUser();
	}

	/**
	 * GET /account : get the current user.
	 *
	 * @return the current user
	 * @throws RuntimeException
	 *             500 (Internal Server Error) if the user couldn't be returned
	 */
	@GetMapping("/account")
	@Timed
	public UserDTO getAccount() {
		return userService.getUserWithAuthorities().map(UserDTO::new)
				.orElseThrow(() -> new InternalServerErrorException("User could not be found"));
	}

	/**
	 * POST /account : update the current user information.
	 *
	 * @param userDTO
	 *            the current user information
	 * @throws EmailAlreadyUsedException
	 *             400 (Bad Request) if the email is already used
	 * @throws RuntimeException
	 *             500 (Internal Server Error) if the user login wasn't found
	 */
	@PostMapping("/account")
	@Timed
	public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
		final String userLogin = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new InternalServerErrorException("Current user login not found"));
		Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
		if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
			throw new EmailAlreadyUsedException();
		}
		Optional<User> user = userRepository.findOneByLogin(userLogin);
		if (!user.isPresent()) {
			throw new InternalServerErrorException("User could not be found");
		}
		userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getLangKey(),
				userDTO.getImageUrl());
	}

	/**
	 * POST /account/change-password : changes the current user's password
	 *
	 * @param password
	 *            the new password
	 * @throws InvalidPasswordException
	 *             400 (Bad Request) if the new password is incorrect
	 */
	@PostMapping(path = "/account/change-password")
	@Timed
	public void changePassword(@RequestBody String password) {
		int minLengthPassword = systemParameterService.getParameterNameValue(SystemParameter.PASSWORD_MIN_LENGTH),
				maxLengthPassword = systemParameterService.getParameterNameValue(SystemParameter.PASSWORD_MAX_LENGTH);

		if (!checkPasswordLength(password, minLengthPassword, maxLengthPassword)) {
			throw new InvalidPasswordException("Password length must be at least " + minLengthPassword
					+ " characters and cannot be longer than " + maxLengthPassword + " characters");
		}

		if (!validPasswordFormat(password)) {
			throw new InvalidPasswordException("Password must be a combination of uppercase, lowercase and digit");
		}

		int minPasswordAgeInDays = systemParameterService
				.getParameterNameValue(SystemParameter.MIN_PASSWORD_AGE_IN_DAYS),
				limitPasswordHistories = systemParameterService
						.getParameterNameValue(SystemParameter.LIMIT_PASSWORD_HISTORIES);

		String response = authorizedToChangePassword(password, false, SecurityUtils.getCurrentUserLogin(),
				minPasswordAgeInDays, limitPasswordHistories);

		if (response.contentEquals("Old Password")) {
			throw new InvalidPasswordException(
					"Your password cannot be the same as last " + limitPasswordHistories + " used passwords");
		} else if (response.contentEquals("Recently Changed")) {
			throw new InvalidPasswordException(
					"You can only change your password every " + minPasswordAgeInDays + " days");
		} else if (response.contentEquals("No User Found")) {
			throw new InvalidPasswordException("No User Found");
		}

		userService.changePassword(password);
	}

	/**
	 * POST /account/reset-password/init : Send an email to reset the password of
	 * the user
	 *
	 * @param mail
	 *            the mail of the user
	 * @throws EmailNotFoundException
	 *             400 (Bad Request) if the email address is not registered
	 */
	@PostMapping(path = "/account/reset-password/init")
	@Timed
	public void requestPasswordReset(@RequestBody String mail) {
		String sender =  systemParameterService.getParameterNameValueString(SystemParameter.EMAIL_SENDER_RESET_PASSWORD);
		mailService
				.sendPasswordResetMail(sender,userService.requestPasswordReset(mail).orElseThrow(EmailNotFoundException::new));
	}

	/**
	 * POST /account/reset-password/finish : Finish to reset the password of the
	 * user
	 *
	 * @param keyAndPassword
	 *            the generated key and the new password
	 * @throws InvalidPasswordException
	 *             400 (Bad Request) if the password is incorrect
	 * @throws RuntimeException
	 *             500 (Internal Server Error) if the password could not be reset
	 */
	@PostMapping(path = "/account/reset-password/finish")
	@Timed
	public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
		int minLengthPassword = systemParameterService.getParameterNameValue(SystemParameter.PASSWORD_MIN_LENGTH),
				maxLengthPassword = systemParameterService.getParameterNameValue(SystemParameter.PASSWORD_MAX_LENGTH);

		if (!checkPasswordLength(keyAndPassword.getNewPassword(), minLengthPassword, maxLengthPassword)) {
			throw new InvalidPasswordException("Password length must be at least " + minLengthPassword
					+ " characters and cannot be longer than " + maxLengthPassword + " characters");
		}

		if (!validPasswordFormat(keyAndPassword.getNewPassword())) {
			throw new InvalidPasswordException("Password must be a combination of uppercase, lowercase and digit");
		}

		Optional<User> user = userService.completePasswordReset(keyAndPassword.getNewPassword(),
				keyAndPassword.getKey());

		if (!user.isPresent()) {
			throw new InternalServerErrorException("No user was found for this reset key");
		}
		
		userService.changePassword(keyAndPassword.getNewPassword());
	}

	private static boolean checkPasswordLength(String password, int minLength, int maxLength) {
		return !StringUtils.isEmpty(password) && password.length() >= minLength && password.length() <= maxLength;
	}

	private boolean validPasswordFormat(String password) {
		boolean hasUppercase = false, hasDigit = false, hasLowercase = false;

		for (int i = 0; i < password.length(); i++) {
			char character = password.charAt(i);

			if (Character.isUpperCase(character)) {
				hasUppercase = true;
			} else if (Character.isDigit(character)) {
				hasDigit = true;
			} else if (Character.isLowerCase(character)) {
				hasLowercase = true;
			}
		}

		return (hasUppercase && hasDigit && hasLowercase);
	}

	private String authorizedToChangePassword(String password, boolean ignoreDaysConstraint,
			Optional<String> loginOptional, int minPasswordAgeInDays, int limitPasswordHistories) {
		String result = "Authorized";

		Optional<User> userOptional = userRepository.findOneByLogin(loginOptional.get());

		if (userOptional.isPresent()) {
			User user = userOptional.get();

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

			if (history.size() > 0) {
				Instant lastModifiedPasswordDateTime = history.get(0).getModifiedDateTime();
				Duration duration = Duration.between(lastModifiedPasswordDateTime, Instant.now());
				long days = duration.toDays();

				log.debug("DAYS " + days);
				log.debug((!ignoreDaysConstraint && (days < minPasswordAgeInDays)) + "");

				if (!ignoreDaysConstraint && days < minPasswordAgeInDays) {
					return "Recently Changed";
				}

				if (history.size() <= limitPasswordHistories)
					limitPasswordHistories = history.size();

				for (int i = 0; i < limitPasswordHistories; i++) {
					if (passwordEncoder.matches(password, history.get(i).getPasswordHash())) {
						return "Old Password";
					}
				}
			}
		} else {
			result = "No User Found";
		}

		return result;
	}
}
