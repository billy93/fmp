package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AtpcoMasterFareType.
 */
@Document(collection = "atpco_master_fare_type")
public class AtpcoMasterFareType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("fare_type_designation")
    private String fareTypeDesignation;

    @Field("type_code")
    private String typeCode;

    @Field("definition")
    private String definition;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFareTypeDesignation() {
        return fareTypeDesignation;
    }

    public AtpcoMasterFareType fareTypeDesignation(String fareTypeDesignation) {
        this.fareTypeDesignation = fareTypeDesignation;
        return this;
    }

    public void setFareTypeDesignation(String fareTypeDesignation) {
        this.fareTypeDesignation = fareTypeDesignation;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public AtpcoMasterFareType typeCode(String typeCode) {
        this.typeCode = typeCode;
        return this;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDefinition() {
        return definition;
    }

    public AtpcoMasterFareType definition(String definition) {
        this.definition = definition;
        return this;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
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
        AtpcoMasterFareType atpcoMasterFareType = (AtpcoMasterFareType) o;
        if (atpcoMasterFareType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atpcoMasterFareType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AtpcoMasterFareType{" +
            "id=" + getId() +
            ", fareTypeDesignation='" + getFareTypeDesignation() + "'" +
            ", typeCode='" + getTypeCode() + "'" +
            ", definition='" + getDefinition() + "'" +
            "}";
    }
}
