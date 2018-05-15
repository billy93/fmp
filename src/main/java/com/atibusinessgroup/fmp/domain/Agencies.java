package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Agencies.
 */
@Document(collection = "agencies")
public class Agencies implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("agent_name")
    private String agentName;

    @Field("iata_code")
    private String iataCode;

    @Field("agent_type")
    private String agentType;

    @Field("agent_category")
    private String agentCategory;

    @Field("pdeudo_city")
    private String pdeudoCity;

    @Field("crs")
    private String crs;

    @Field("pos_city")
    private String posCity;

    @Field("pos_country")
    private String posCountry;

    @Field("address")
    private String address;

    @Field("telephone")
    private Integer telephone;

    @Field("fax")
    private Integer fax;

    @Field("email")
    private String email;

    @Field("contact")
    private Integer contact;

    @Field("deleted")
    private Integer deleted;

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

    public Agencies agentName(String agentName) {
        this.agentName = agentName;
        return this;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getIataCode() {
        return iataCode;
    }

    public Agencies iataCode(String iataCode) {
        this.iataCode = iataCode;
        return this;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getAgentType() {
        return agentType;
    }

    public Agencies agentType(String agentType) {
        this.agentType = agentType;
        return this;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public String getAgentCategory() {
        return agentCategory;
    }

    public Agencies agentCategory(String agentCategory) {
        this.agentCategory = agentCategory;
        return this;
    }

    public void setAgentCategory(String agentCategory) {
        this.agentCategory = agentCategory;
    }

    public String getPdeudoCity() {
        return pdeudoCity;
    }

    public Agencies pdeudoCity(String pdeudoCity) {
        this.pdeudoCity = pdeudoCity;
        return this;
    }

    public void setPdeudoCity(String pdeudoCity) {
        this.pdeudoCity = pdeudoCity;
    }

    public String getCrs() {
        return crs;
    }

    public Agencies crs(String crs) {
        this.crs = crs;
        return this;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }

    public String getPosCity() {
        return posCity;
    }

    public Agencies posCity(String posCity) {
        this.posCity = posCity;
        return this;
    }

    public void setPosCity(String posCity) {
        this.posCity = posCity;
    }

    public String getPosCountry() {
        return posCountry;
    }

    public Agencies posCountry(String posCountry) {
        this.posCountry = posCountry;
        return this;
    }

    public void setPosCountry(String posCountry) {
        this.posCountry = posCountry;
    }

    public String getAddress() {
        return address;
    }

    public Agencies address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public Agencies telephone(Integer telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Integer getFax() {
        return fax;
    }

    public Agencies fax(Integer fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(Integer fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public Agencies email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getContact() {
        return contact;
    }

    public Agencies contact(Integer contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public Agencies deleted(Integer deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Agencies agencies = (Agencies) o;
        if (agencies.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agencies.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Agencies{" +
            "id=" + getId() +
            ", agentName='" + getAgentName() + "'" +
            ", iataCode='" + getIataCode() + "'" +
            ", agentType='" + getAgentType() + "'" +
            ", agentCategory='" + getAgentCategory() + "'" +
            ", pdeudoCity='" + getPdeudoCity() + "'" +
            ", crs='" + getCrs() + "'" +
            ", posCity='" + getPosCity() + "'" +
            ", posCountry='" + getPosCountry() + "'" +
            ", address='" + getAddress() + "'" +
            ", telephone=" + getTelephone() +
            ", fax=" + getFax() +
            ", email='" + getEmail() + "'" +
            ", contact=" + getContact() +
            ", deleted=" + getDeleted() +
            "}";
    }
}
