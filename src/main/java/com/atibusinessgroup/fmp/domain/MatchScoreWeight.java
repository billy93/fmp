package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

import com.atibusinessgroup.fmp.domain.enumeration.ComponentType;

/**
 * A MatchScoreWeight.
 */
@Document(collection = "match_score_weight")
public class MatchScoreWeight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("default_value")
    private String defaultValue;

    @Field("value")
    private String value;

    @Field("name")
    private ComponentType name;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public MatchScoreWeight defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public MatchScoreWeight value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ComponentType getName() {
        return name;
    }

    public MatchScoreWeight name(ComponentType name) {
        this.name = name;
        return this;
    }

    public void setName(ComponentType name) {
        this.name = name;
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
        MatchScoreWeight matchScoreWeight = (MatchScoreWeight) o;
        if (matchScoreWeight.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matchScoreWeight.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MatchScoreWeight{" +
            "id=" + getId() +
            ", defaultValue='" + getDefaultValue() + "'" +
            ", value='" + getValue() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
