package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Rule.
 */

@Document(collection = "work_package_history")
public class WorkPackageHistory  extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("work_package")
    private ObjectId workPackage;

    @Field("type")
    private String type;

    @Field("username")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public ObjectId getWorkPackage() {
		return workPackage;
	}

	public void setWorkPackage(ObjectId workPackage) {
		this.workPackage = workPackage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkPackageHistory rule = (WorkPackageHistory) o;
        if(rule.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, rule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

	@Override
	public String toString() {
		return "WorkPackageHistory [id=" + id + ", workPackage=" + workPackage + ", type=" + type + ", username="
				+ username + "]";
	}

    
}
