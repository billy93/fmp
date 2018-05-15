package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Passenger.
 */

@Document(collection = "passenger")
public class Passenger extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("code")
    private String code;
    
    @NotNull
    @Field("name")
    private String name;

    @Field("description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    public void setCreatedBy(String createdBy) {
        super.setCreatedBy(createdBy);;
    }

    public Instant getCreatedDate() {
        return super.getCreatedDate();
    }

    public void setCreatedDate(Instant createdDate) {
    	super.setCreatedDate(createdDate);
    }

    public String getLastModifiedBy() {
        return super.getLastModifiedBy();
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);;
    }

    public Instant getLastModifiedDate() {
        return super.getLastModifiedDate();
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        super.setLastModifiedDate(lastModifiedDate);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passenger passenger = (Passenger) o;
        if(passenger.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, passenger.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + "]";
	}
}
