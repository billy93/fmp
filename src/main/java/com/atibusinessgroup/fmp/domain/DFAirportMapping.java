package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DFAirportMapping.
 */
@Document(collection = "data_feed_airport_maping")
public class DFAirportMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("city_code")
    private String cityCode;

    @Field("airport_code")
    private String airportCode;

    @Field("country_code")
    private String countryCode;

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

    public DFAirportMapping cityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public DFAirportMapping airportCode(String airportCode) {
        this.airportCode = airportCode;
        return this;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public DFAirportMapping countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
        DFAirportMapping dFAirportMapping = (DFAirportMapping) o;
        if (dFAirportMapping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dFAirportMapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DFAirportMapping{" +
            "id=" + getId() +
            ", cityCode='" + getCityCode() + "'" +
            ", airportCode='" + getAirportCode() + "'" +
            ", countryCode='" + getCountryCode() + "'" +
            "}";
    }
}
