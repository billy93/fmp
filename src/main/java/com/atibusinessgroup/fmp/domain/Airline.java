package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Airline.
 */
@Document(collection = "airline")
public class Airline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("airline_code")
    private String airline_code;

    @Field("airline_name")
    private String airline_name;

    @Field("iata_code")
    private String iata_code;

    @Field("icao_designator")
    private String icao_designator;

    @Field("country")
    private String country;

    @Field("activated")
    private Boolean activated;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirline_code() {
        return airline_code;
    }

    public Airline airline_code(String airline_code) {
        this.airline_code = airline_code;
        return this;
    }

    public void setAirline_code(String airline_code) {
        this.airline_code = airline_code;
    }

    public String getAirline_name() {
        return airline_name;
    }

    public Airline airline_name(String airline_name) {
        this.airline_name = airline_name;
        return this;
    }

    public void setAirline_name(String airline_name) {
        this.airline_name = airline_name;
    }

    public String getIata_code() {
        return iata_code;
    }

    public Airline iata_code(String iata_code) {
        this.iata_code = iata_code;
        return this;
    }

    public void setIata_code(String iata_code) {
        this.iata_code = iata_code;
    }

    public String getIcao_designator() {
        return icao_designator;
    }

    public Airline icao_designator(String icao_designator) {
        this.icao_designator = icao_designator;
        return this;
    }

    public void setIcao_designator(String icao_designator) {
        this.icao_designator = icao_designator;
    }

    public String getCountry() {
        return country;
    }

    public Airline country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean isActivated() {
        return activated;
    }

    public Airline activated(Boolean activated) {
        this.activated = activated;
        return this;
    }

    public void setActived(Boolean activated) {
        this.activated = activated;
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
        Airline airline = (Airline) o;
        if (airline.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), airline.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Airline{" +
            "id=" + getId() +
            ", airline_code='" + getAirline_code() + "'" +
            ", airline_name='" + getAirline_name() + "'" +
            ", iata_code='" + getIata_code() + "'" +
            ", icao_designator='" + getIcao_designator() + "'" +
            ", country='" + getCountry() + "'" +
            ", activeStatus='" + isActivated() + "'" +
            "}";
    }
}
