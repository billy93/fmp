package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ProductTypeSubType.
 */
@Document(collection = "product_type_sub_type")
public class ProductTypeSubType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("product_type")
    private String productType;

    @Field("product_sub_type")
    private String productSubType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public ProductTypeSubType productType(String productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductSubType() {
        return productSubType;
    }

    public ProductTypeSubType productSubType(String productSubType) {
        this.productSubType = productSubType;
        return this;
    }

    public void setProductSubType(String productSubType) {
        this.productSubType = productSubType;
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
        ProductTypeSubType productTypeSubType = (ProductTypeSubType) o;
        if (productTypeSubType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productTypeSubType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductTypeSubType{" +
            "id=" + getId() +
            ", productType='" + getProductType() + "'" +
            ", productSubType='" + getProductSubType() + "'" +
            "}";
    }
}
