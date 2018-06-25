package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DFFareBasisGroupMapping.
 */
@Document(collection = "data_feed_fare_basis_group_mapping")
public class DFFareBasisGroupMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("origin_city")
    private String originCity;

    @Field("origin_country")
    private String originCountry;

    @Field("destination_city")
    private String destinationCity;

    @Field("destination_country")
    private String destinationCountry;

    @Field("rbd")
    private String rbd;

    @Field("fare_basis_code")
    private String fareBasisCode;

    @Field("fare_basis_group")
    private String fareBasisGroup;

    @Field("priority")
    private String priority;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginCity() {
        return originCity;
    }

    public DFFareBasisGroupMapping originCity(String originCity) {
        this.originCity = originCity;
        return this;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public DFFareBasisGroupMapping originCountry(String originCountry) {
        this.originCountry = originCountry;
        return this;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public DFFareBasisGroupMapping destinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
        return this;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public DFFareBasisGroupMapping destinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
        return this;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getRbd() {
        return rbd;
    }

    public DFFareBasisGroupMapping rbd(String rbd) {
        this.rbd = rbd;
        return this;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    public String getFareBasisCode() {
        return fareBasisCode;
    }

    public DFFareBasisGroupMapping fareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
        return this;
    }

    public void setFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
    }

    public String getFareBasisGroup() {
        return fareBasisGroup;
    }

    public DFFareBasisGroupMapping fareBasisGroup(String fareBasisGroup) {
        this.fareBasisGroup = fareBasisGroup;
        return this;
    }

    public void setFareBasisGroup(String fareBasisGroup) {
        this.fareBasisGroup = fareBasisGroup;
    }

    public String getPriority() {
        return priority;
    }

    public DFFareBasisGroupMapping priority(String priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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
        DFFareBasisGroupMapping dFFareBasisGroupMapping = (DFFareBasisGroupMapping) o;
        if (dFFareBasisGroupMapping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dFFareBasisGroupMapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DFFareBasisGroupMapping{" +
            "id=" + getId() +
            ", originCity='" + getOriginCity() + "'" +
            ", originCountry='" + getOriginCountry() + "'" +
            ", destinationCity='" + getDestinationCity() + "'" +
            ", destinationCountry='" + getDestinationCountry() + "'" +
            ", rbd='" + getRbd() + "'" +
            ", fareBasisCode='" + getFareBasisCode() + "'" +
            ", fareBasisGroup='" + getFareBasisGroup() + "'" +
            ", priority='" + getPriority() + "'" +
            "}";
    }
}
