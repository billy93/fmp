package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A BusinessArea.
 */
@Document(collection = "business_area")
public class BusinessArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("description")
    private String description;

    @Field("pic")
    private String pic;

    @Field("contact_number")
    private Integer contactNumber;

    @Field("active_status")
    private Boolean activeStatus;

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

    public BusinessArea name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public BusinessArea description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public BusinessArea pic(String pic) {
        this.pic = pic;
        return this;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getContactNumber() {
        return contactNumber;
    }

    public BusinessArea contactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Boolean isActiveStatus() {
        return activeStatus;
    }

    public BusinessArea activeStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
        return this;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
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
        BusinessArea businessArea = (BusinessArea) o;
        if (businessArea.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessArea.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessArea{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", pic='" + getPic() + "'" +
            ", contactNumber=" + getContactNumber() +
            ", activeStatus='" + isActiveStatus() + "'" +
            "}";
    }
}
