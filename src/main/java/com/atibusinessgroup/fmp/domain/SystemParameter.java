package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A SystemParameter.
 */
@Document(collection = "system_parameter")
public class SystemParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String MAX_UNUSED_DAYS_TO_SUSPEND = "MAX_UNUSED_DAYS_TO_SUSPEND", 
    		LOCKOUT_DURATION_IN_MINUTES = "LOCKOUT_DURATION_IN_MINUTES",
    		MAX_PASSWORD_AGE_IN_DAYS = "MAX_PASSWORD_AGE_IN_DAYS",
    		MIN_PASSWORD_AGE_IN_DAYS = "MIN_PASSWORD_AGE_IN_DAYS",
    		LIMIT_PASSWORD_HISTORIES = "LIMIT_PASSWORD_HISTORIES",
    		MAX_FAILED_LOGIN_COUNTERS = "MAX_FAILED_LOGIN_COUNTERS",
			PASSWORD_MIN_LENGTH = "PASSWORD_MIN_LENGTH",
			PASSWORD_MAX_LENGTH = "PASSWORD_MAX_LENGTH",
			MAX_UNUSED_DAYS_TO_SUSPEND_NEW_USER = "MAX_UNUSED_DAYS_TO_SUSPEND_NEW_USER";
    
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("value")
    private String value;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SystemParameter name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public SystemParameter value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
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
        SystemParameter systemParameter = (SystemParameter) o;
        if (systemParameter.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), systemParameter.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SystemParameter{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
