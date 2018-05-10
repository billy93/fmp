package com.atibusinessgroup.fmp.service.dto;

import java.time.Instant;

public class PasswordHistory {
    private String passwordHash;
    private Instant modifiedDateTime;
    
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public Instant getModifiedDateTime() {
		return modifiedDateTime;
	}
	
	public void setModifiedDateTime(Instant modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	@Override
	public String toString() {
		return "PasswordHistory [passwordHash=" + passwordHash + ", modifiedDateTime=" + modifiedDateTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((modifiedDateTime == null) ? 0 : modifiedDateTime.hashCode());
		result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordHistory other = (PasswordHistory) obj;
		if (modifiedDateTime == null) {
			if (other.modifiedDateTime != null)
				return false;
		} else if (!modifiedDateTime.equals(other.modifiedDateTime))
			return false;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		return true;
	}
	
	
}