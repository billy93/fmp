package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

@Document(collection = "tax_type")
public class TaxType extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

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

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaxType taxType = (TaxType) o;
        if(taxType.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, taxType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
	@Override
	public String toString() {
		return "TaxType [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
