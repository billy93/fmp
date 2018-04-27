package com.atibusinessgroup.fmp.security;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.atibusinessgroup.fmp.domain.SystemParameter;
import com.atibusinessgroup.fmp.domain.User;
import com.atibusinessgroup.fmp.repository.UserRepository;
import com.atibusinessgroup.fmp.service.SystemParameterService;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;
    
    private final SystemParameterService systemParameterService;
    
    public DomainUserDetailsService(UserRepository userRepository, SystemParameterService systemParameterService) {
        this.userRepository = userRepository;
        this.systemParameterService = systemParameterService;
    }

    @Override
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
       
        Optional<User> userOptional = userRepository.findOneByLogin(lowercaseLogin);
        
        if(userOptional.isPresent()) {
        	User user = userOptional.get();
        	
        	if (!user.getActivated()) {
        		throw new UserErrorException("Your account is not activated. Please contact the administrator");
            }
        	
			Instant lockoutDateTime = user.getLastLockoutDateTime();
			Instant loginDateTime = user.getLastLoginDateTime();

			boolean modified = false;

			if (loginDateTime != null) {
				Duration duration = Duration.between(loginDateTime, Instant.now());
				long days = duration.toDays();
				
				if (days > systemParameterService.getParameterNameValue(SystemParameter.MAX_UNUSED_DAYS_TO_SUSPEND)) {
					user.setSuspended(true);
					modified = true;
				}
			}

			if (lockoutDateTime != null) {
				Duration duration = Duration.between(lockoutDateTime, Instant.now());
				long minutes = duration.toMinutes();

				if (minutes > systemParameterService.getParameterNameValue(SystemParameter.LOCKOUT_DURATION_IN_MINUTES) && user.getLocked()) {
					user.setLocked(false);
					user.setFailedLoginCounter(0);
					modified = true;
				}
			}

			if (modified)
				user = userRepository.save(user);
    		
        	if (user.getLocked() != null && user.getLocked()) {
        		throw new UserErrorException("Your account is locked. Please contact the administrator");
    		}
    		
    		if (user.getSuspended() != null && user.getSuspended()) {
    			throw new UserErrorException("Your account is suspended. Please contact the administrator");
    		}
    		
    		return createSpringSecurityUser(lowercaseLogin, user);
        } else {
        	throw new UserErrorException("Invalid credentials");
        }
    }
    
	private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
        
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            grantedAuthorities);
    }
}
