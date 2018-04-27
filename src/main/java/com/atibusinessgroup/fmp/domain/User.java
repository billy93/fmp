package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.config.Constants;
import com.atibusinessgroup.fmp.service.dto.PasswordHistory;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A user.
 */

@org.springframework.data.mongodb.core.mapping.Document(collection = "jhi_user")
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    @Indexed
    private String login;

    @JsonIgnore
    @NotNull
    @Size(min = 60, max = 60)
    private String password;

    @Size(max = 50)
    @Field("first_name")
    private String firstName;

    @Size(max = 50)
    @Field("last_name")
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    @Indexed
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 6)
    @Field("lang_key")
    private String langKey;

    @Size(max = 256)
    @Field("image_url")
    private String imageUrl;

    @Size(max = 20)
    @Field("activation_key")
    @JsonIgnore
    private String activationKey;

    @Size(max = 20)
    @Field("reset_key")
    @JsonIgnore
    private String resetKey;

    @Field("reset_date")
    private Instant resetDate = null;
    
    @Field("jhi_locked")
    private Boolean locked;

    @Field("last_lockout_date_time")
    private Instant lastLockoutDateTime;

    @Field("suspended")
    private Boolean suspended;

    @Field("last_login_date_time")
    private Instant lastLoginDateTime;

    @Field("failed_login_counter")
    private Integer failedLoginCounter;

    @Field("password_history")
    private List<PasswordHistory> passwordHistory = new ArrayList<>();
    
    @JsonIgnore
    private Set<Authority> authorities = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
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

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
    
    public List<PasswordHistory> getPasswordHistory() {
		return passwordHistory;
	}

	public void setPasswordHistory(List<PasswordHistory> passwordHistory) {
		this.passwordHistory = passwordHistory;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;
        return !(user.getId() == null || getId() == null) && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
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
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", activated=" + activated + ", langKey=" + langKey
				+ ", imageUrl=" + imageUrl + ", activationKey=" + activationKey + ", resetKey=" + resetKey
				+ ", resetDate=" + resetDate + ", locked=" + locked + ", lastLockoutDateTime=" + lastLockoutDateTime
				+ ", suspended=" + suspended + ", lastLoginDateTime=" + lastLoginDateTime + ", failedLoginCounter="
				+ failedLoginCounter + ", passwordHistory=" + passwordHistory + ", authorities=" + authorities + "]";
	}
}
