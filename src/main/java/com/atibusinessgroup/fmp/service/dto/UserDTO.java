package com.atibusinessgroup.fmp.service.dto;

import com.atibusinessgroup.fmp.config.Constants;

import com.atibusinessgroup.fmp.domain.Authority;
import com.atibusinessgroup.fmp.domain.User;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

	private String id;

	@NotBlank
	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 50)
	private String login;

	@Size(max = 50)
	private String firstName;

	@Size(max = 50)
	private String lastName;

	@Email
	@Size(min = 5, max = 100)
	private String email;

	@Size(max = 256)
	private String imageUrl;

	private boolean activated = false;

	@Size(min = 2, max = 6)
	private String langKey;

	private String createdBy;

	private Instant createdDate;

	private String lastModifiedBy;

	private Instant lastModifiedDate;

	private Set<String> authorities;

	private String password;

	private Set<String> passwordHistory;

	private String resetKey;

	private Instant resetDate;

	private Instant effectiveDateTime;

	private Instant discontinueDateTime;
	
	private Boolean locked;
	
	private Instant lastLockoutDateTime;
	
	private Boolean suspended;
	
	private Instant lastLoginDateTime;

	private Integer failedLoginCounter;
	
    private List<String> reviewLevels;
    
    private List<String> businessAreas;
    
	public UserDTO() {
		// Empty constructor needed for Jackson.
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.activated = user.getActivated();
		this.imageUrl = user.getImageUrl();
		this.langKey = user.getLangKey();
		this.createdBy = user.getCreatedBy();
		this.createdDate = user.getCreatedDate();
		this.lastModifiedBy = user.getLastModifiedBy();
		this.lastModifiedDate = user.getLastModifiedDate();
		this.authorities = user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet());
		this.password = user.getPassword();
		this.resetKey = user.getResetKey();
		this.resetDate = user.getResetDate();
		this.effectiveDateTime = user.getEffectiveDateTime();
		this.discontinueDateTime = user.getDiscontinueDateTime();
		this.passwordHistory = user.getPasswordHistory().stream().map(PasswordHistory::getPasswordHash)
				.collect(Collectors.toSet());
		this.locked = user.getLocked();
		this.lastLockoutDateTime = user.getLastLockoutDateTime();
		this.suspended = user.getSuspended();
		this.lastLoginDateTime = user.getLastLoginDateTime();
		this.failedLoginCounter = user.getFailedLoginCounter();
		this.reviewLevels = user.getReviewLevels();
		this.businessAreas = user.getBusinessAreas();
	}

	
	public List<String> getReviewLevels() {
		return reviewLevels;
	}

	public void setReviewLevels(List<String> reviewLevels) {
		this.reviewLevels = reviewLevels;
	}
	
	public List<String> getBusinessAreas() {
		return businessAreas;
	}

	public void setBusinessAreas(List<String> businessAreas) {
		this.businessAreas = businessAreas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getPasswordHistory() {
		return passwordHistory;
	}

	public void setPasswordHistory(Set<String> passwordHistory) {
		this.passwordHistory = passwordHistory;
	}

	public String getResetKey() {
		return resetKey;
	}

	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}

	public Instant getResetDate() {
		return resetDate;
	}

	public void setResetDate(Instant resetDate) {
		this.resetDate = resetDate;
	}

	public Instant getEffectiveDateTime() {
		return effectiveDateTime;
	}

	public void setEffectiveDateTime(Instant effectiveDateTime) {
		this.effectiveDateTime = effectiveDateTime;
	}

	public Instant getDiscontinueDateTime() {
		return discontinueDateTime;
	}

	public void setDiscontinueDateTime(Instant discontinueDateTime) {
		this.discontinueDateTime = discontinueDateTime;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Instant getLastLockoutDateTime() {
		return lastLockoutDateTime;
	}

	public void setLastLockoutDateTime(Instant lastLockoutDateTime) {
		this.lastLockoutDateTime = lastLockoutDateTime;
	}

	public Boolean getSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	public Instant getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(Instant lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public Integer getFailedLoginCounter() {
		return failedLoginCounter;
	}

	public void setFailedLoginCounter(Integer failedLoginCounter) {
		this.failedLoginCounter = failedLoginCounter;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", imageUrl=" + imageUrl + ", activated=" + activated + ", langKey=" + langKey
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", lastModifiedDate=" + lastModifiedDate + ", authorities=" + authorities + ", password=" + password
				+ ", passwordHistory=" + passwordHistory + ", resetKey=" + resetKey + ", resetDate=" + resetDate
				+ ", effectiveDateTime=" + effectiveDateTime + ", discontinueDateTime=" + discontinueDateTime
				+ ", locked=" + locked + ", lastLockoutDateTime=" + lastLockoutDateTime + ", suspended=" + suspended
				+ ", lastLoginDateTime=" + lastLoginDateTime + ", failedLoginCounter=" + failedLoginCounter
				+ ", reviewLevels=" + reviewLevels + ", businessAreas=" + businessAreas + "]";
	}


}
