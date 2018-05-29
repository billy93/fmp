package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Agent.
 */
@Document(collection = "agent")
public class Agent implements Serializable {


    @Id
    private String id;

    @Field("agent_name")
    private String agentName;

    @Field("agent_type")
    private String agentType;

    @Field("agent_category")
    private String agentCategory;

    @Field("pos_city")
    private String posCity;
    
    @Field("pos_country")
    private String posCountry;
    
    @Field("pdeudo_city")
    private String pdeudoCity;

    @Field("crs")
    private String crs;

    @Field("address")
    private String address;

    @Field("telephone")
    private String telephone;

    @Field("fax")
    private String fax;

    @Field("email")
    private String email;

    @Field("contact")
    private String contact;

    @Field("iata_code")
    private String iataCode;

    @Field("is_deleted")
    private Boolean isDeleted;
    
    @Field("effective_date_time")
    private Instant effectiveDateTime;
    
    @Field("discontinue_date_time")
    private Instant discontinueDateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgentName() {
        return agentName;
    }

    public Agent agentName(String agentName) {
        this.agentName = agentName;
        return this;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentType() {
        return agentType;
    }

    public Agent agentType(String agentType) {
        this.agentType = agentType;
        return this;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getAgentCategory() {
        return agentCategory;
    }

    public Agent agentCategory(String agentCategory) {
        this.agentCategory = agentCategory;
        return this;
    }

    public void setAgentCategory(String agentCategory) {
        this.agentCategory = agentCategory;
    }

    public String getPosCity() {
        return posCity;
    }

    public Agent posCity(String posCity) {
        this.posCity = posCity;
        return this;
    }

    public void setPosCity(String posCity) {
        this.posCity = posCity;
    }

    public String getAddress() {
        return address;
    }

    public Agent address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public Agent telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public Agent fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public Agent email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public Agent contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIataCode() {
        return iataCode;
    }

    public Agent iataCode(String iataCode) {
        this.iataCode = iataCode;
        return this;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public Agent isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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

	public String getPosCountry() {
		return posCountry;
	}

	public void setPosCountry(String posCountry) {
		this.posCountry = posCountry;
	}
	
	

	public String getPdeudoCity() {
		return pdeudoCity;
	}

	public void setPdeudoCity(String pdeudoCity) {
		this.pdeudoCity = pdeudoCity;
	}

	public String getCrs() {
		return crs;
	}

	public void setCrs(String crs) {
		this.crs = crs;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Agent agent = (Agent) o;
        if (agent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "Agent [id=" + id + ", agentName=" + agentName + ", agentType=" + agentType + ", agentCategory="
				+ agentCategory + ", posCity=" + posCity + ", posCountry=" + posCountry + ", pdeudoCity=" + pdeudoCity
				+ ", crs=" + crs + ", address=" + address + ", telephone=" + telephone + ", fax=" + fax + ", email="
				+ email + ", contact=" + contact + ", iataCode=" + iataCode + ", isDeleted=" + isDeleted + "]";
	}

	


}
