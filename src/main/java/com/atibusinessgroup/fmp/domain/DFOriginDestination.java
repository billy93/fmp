package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DFOriginDestination.
 */
@Document(collection = "data_feed_origin_destination")
public class DFOriginDestination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("origin_airport")
    private String origAirport;

    @Field("destination_airport")
    private String destAirport;

    @Field("origin_city")
    private String origCity;

    @Field("destination_city")
    private String destCity;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigAirport() {
        return origAirport;
    }

    public DFOriginDestination origAirport(String origAirport) {
        this.origAirport = origAirport;
        return this;
    }

    public void setOrigAirport(String origAirport) {
        this.origAirport = origAirport;
    }

    public String getDestAirport() {
        return destAirport;
    }

    public DFOriginDestination destAirport(String destAirport) {
        this.destAirport = destAirport;
        return this;
    }

    public void setDestAirport(String destAirport) {
        this.destAirport = destAirport;
    }

    public String getOrigCity() {
        return origCity;
    }

    public DFOriginDestination origCity(String origCity) {
        this.origCity = origCity;
        return this;
    }

    public void setOrigCity(String origCity) {
        this.origCity = origCity;
    }

    public String getDestCity() {
        return destCity;
    }

    public DFOriginDestination destCity(String destCity) {
        this.destCity = destCity;
        return this;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
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
        DFOriginDestination dFOriginDestination = (DFOriginDestination) o;
        if (dFOriginDestination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dFOriginDestination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DFOriginDestination{" +
            "id=" + getId() +
            ", origAirport='" + getOrigAirport() + "'" +
            ", destAirport='" + getDestAirport() + "'" +
            ", origCity='" + getOrigCity() + "'" +
            ", destCity='" + getDestCity() + "'" +
            "}";
    }
}
