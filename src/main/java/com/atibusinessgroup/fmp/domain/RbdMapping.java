package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RbdMapping.
 */
@Document(collection = "rbd_mapping")
public class RbdMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("oal_cxr")
    private String oalCxr;

    @Field("oal_cabin")
    private String oalCabin;

    @Field("own_cabin")
    private String ownCabin;

    @Field("oal_rbd")
    private String oalRbd;

    @Field("own_rbd")
    private String ownRbd;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOalCxr() {
        return oalCxr;
    }

    public RbdMapping oalCxr(String oalCxr) {
        this.oalCxr = oalCxr;
        return this;
    }

    public void setOalCxr(String oalCxr) {
        this.oalCxr = oalCxr;
    }

    public String getOalCabin() {
        return oalCabin;
    }

    public RbdMapping oalCabin(String oalCabin) {
        this.oalCabin = oalCabin;
        return this;
    }

    public void setOalCabin(String oalCabin) {
        this.oalCabin = oalCabin;
    }

    public String getOwnCabin() {
        return ownCabin;
    }

    public RbdMapping ownCabin(String ownCabin) {
        this.ownCabin = ownCabin;
        return this;
    }

    public void setOwnCabin(String ownCabin) {
        this.ownCabin = ownCabin;
    }

    public String getOalRbd() {
        return oalRbd;
    }

    public RbdMapping oalRbd(String oalRbd) {
        this.oalRbd = oalRbd;
        return this;
    }

    public void setOalRbd(String oalRbd) {
        this.oalRbd = oalRbd;
    }

    public String getOwnRbd() {
        return ownRbd;
    }

    public RbdMapping ownRbd(String ownRbd) {
        this.ownRbd = ownRbd;
        return this;
    }

    public void setOwnRbd(String ownRbd) {
        this.ownRbd = ownRbd;
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
        RbdMapping rbdMapping = (RbdMapping) o;
        if (rbdMapping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rbdMapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RbdMapping{" +
            "id=" + getId() +
            ", oalCxr='" + getOalCxr() + "'" +
            ", oalCabin='" + getOalCabin() + "'" +
            ", ownCabin='" + getOwnCabin() + "'" +
            ", oalRbd='" + getOalRbd() + "'" +
            ", ownRbd='" + getOwnRbd() + "'" +
            "}";
    }
}
