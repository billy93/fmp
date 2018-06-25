package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DFPublishedFareRates.
 */
@Document(collection = "data_feed_pfc_rates")
public class DFPublishedFareRates implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("pos_country")
    private String postCountry;

    @Field("from_td")
    private Object travelDateFrom;

    @Field("to_td")
    private Object travelDateTo;

    @Field("rate")
    private String rate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostCountry() {
        return postCountry;
    }

    public DFPublishedFareRates postCountry(String postCountry) {
        this.postCountry = postCountry;
        return this;
    }

    public void setPostCountry(String postCountry) {
        this.postCountry = postCountry;
    }

    public Object getTravelDateFrom() {
        return travelDateFrom;
    }

    public DFPublishedFareRates travelDateFrom(String travelDateFrom) {
        this.travelDateFrom = travelDateFrom;
        return this;
    }

    public void setTravelDateFrom(String travelDateFrom) {
        this.travelDateFrom = travelDateFrom;
    }

    public Object getTravelDateTo() {
        return travelDateTo;
    }

    public DFPublishedFareRates travelDateTo(String travelDateTo) {
        this.travelDateTo = travelDateTo;
        return this;
    }

    public void setTravelDateTo(String travelDateTo) {
        this.travelDateTo = travelDateTo;
    }

    public String getRate() {
        return rate;
    }

    public DFPublishedFareRates rate(String rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(String rate) {
        this.rate = rate;
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
        DFPublishedFareRates dFPublishedFareRates = (DFPublishedFareRates) o;
        if (dFPublishedFareRates.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dFPublishedFareRates.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DFPublishedFareRates{" +
            "id=" + getId() +
            ", postCountry='" + getPostCountry() + "'" +
            ", travelDateFrom='" + getTravelDateFrom() + "'" +
            ", travelDateTo='" + getTravelDateTo() + "'" +
            ", rate='" + getRate() + "'" +
            "}";
    }
}
