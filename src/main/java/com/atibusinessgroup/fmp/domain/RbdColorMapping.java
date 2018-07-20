package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RbdColorMapping.
 */
@Document(collection = "rbd_color_mapping")
public class RbdColorMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("rbd")
    private String rbd;

    @Field("color")
    private String color;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRbd() {
        return rbd;
    }

    public RbdColorMapping rbd(String rbd) {
        this.rbd = rbd;
        return this;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    public String getColor() {
        return color;
    }

    public RbdColorMapping color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
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
        RbdColorMapping rbdColorMapping = (RbdColorMapping) o;
        if (rbdColorMapping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rbdColorMapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RbdColorMapping{" +
            "id=" + getId() +
            ", rbd='" + getRbd() + "'" +
            ", color='" + getColor() + "'" +
            "}";
    }
}
