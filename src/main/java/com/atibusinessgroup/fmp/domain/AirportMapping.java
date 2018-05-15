package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AirportMapping.
 */
@Document(collection = "airport_mapping")
public class AirportMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("city_code")
    private String cityCode;

    @Field("city_name")
    private String cityName;

    @Field("airport_code")
    private String airportCode;

    @Field("airport_name")
    private String airportName;

    @Field("country_code")
    private String countryCode;

    @Field("country_name")
    private String countryName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public AirportMapping cityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public AirportMapping cityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public AirportMapping airportCode(String airportCode) {
        this.airportCode = airportCode;
        return this;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public AirportMapping airportName(String airportName) {
        this.airportName = airportName;
        return this;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public AirportMapping countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public AirportMapping countryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
        AirportMapping airportMapping = (AirportMapping) o;
        if (airportMapping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), airportMapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AirportMapping{" +
            "id=" + getId() +
            ", cityCode='" + getCityCode() + "'" +
            ", cityName='" + getCityName() + "'" +
            ", airportCode='" + getAirportCode() + "'" +
            ", airportName='" + getAirportName() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            ", countryName='" + getCountryName() + "'" +
            "}";
    }
}
