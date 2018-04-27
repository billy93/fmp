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
}