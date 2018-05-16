package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TariffNumber.
 */
@Document(collection = "tariff_number")
public class TariffNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tar_no")
    private String tarNo;

    @Field("tar_cd")
    private String tarCd;

    @Field("global")
    private String global;

    @Field("description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarNo() {
        return tarNo;
    }

    public TariffNumber tarNo(String tarNo) {
        this.tarNo = tarNo;
        return this;
    }

    public void setTarNo(String tarNo) {
        this.tarNo = tarNo;
    }

    public String getTarCd() {
        return tarCd;
    }

    public TariffNumber tarCd(String tarCd) {
        this.tarCd = tarCd;
        return this;
    }

    public void setTarCd(String tarCd) {
        this.tarCd = tarCd;
    }

    public String getGlobal() {
        return global;
    }

    public TariffNumber global(String global) {
        this.global = global;
        return this;
    }

    public void setGlobal(String global) {
        this.global = global;
    }

    public String getDescription() {
        return description;
    }

    public TariffNumber description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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
        TariffNumber tariffNumber = (TariffNumber) o;
        if (tariffNumber.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tariffNumber.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TariffNumber{" +
            "id=" + getId() +
            ", tarNo='" + getTarNo() + "'" +
            ", tarCd='" + getTarCd() + "'" +
            ", global='" + getGlobal() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
