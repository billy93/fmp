package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AtpcoMasterFareMatrix.
 */
@Document(collection = "atpco_master_fare_matrix")
public class AtpcoMasterFareMatrix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cabin_service")
    private String cabinService;

    @Field("cabin_code")
    private String cabinCode;

    @Field("fare_type_code")
    private AtpcoMasterFareType fareTypeCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCabinService() {
        return cabinService;
    }

    public AtpcoMasterFareMatrix cabinService(String cabinService) {
        this.cabinService = cabinService;
        return this;
    }

    public void setCabinService(String cabinService) {
        this.cabinService = cabinService;
    }

    public String getCabinCode() {
        return cabinCode;
    }

    public AtpcoMasterFareMatrix cabinCode(String cabinCode) {
        this.cabinCode = cabinCode;
        return this;
    }

    public void setCabinCode(String cabinCode) {
        this.cabinCode = cabinCode;
    }

    public AtpcoMasterFareType getFareTypeCode() {
        return fareTypeCode;
    }

    public AtpcoMasterFareMatrix fareTypeCode(AtpcoMasterFareType fareTypeCode) {
        this.fareTypeCode = fareTypeCode;
        return this;
    }

    public void setFareTypeCode(AtpcoMasterFareType fareTypeCode) {
        this.fareTypeCode = fareTypeCode;
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
        AtpcoMasterFareMatrix atpcoMasterFareMatrix = (AtpcoMasterFareMatrix) o;
        if (atpcoMasterFareMatrix.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atpcoMasterFareMatrix.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AtpcoMasterFareMatrix{" +
            "id=" + getId() +
            ", cabinService='" + getCabinService() + "'" +
            ", cabinCode='" + getCabinCode() + "'" +
            ", fareTypeCode='" + getFareTypeCode() + "'" +
            "}";
    }
}
