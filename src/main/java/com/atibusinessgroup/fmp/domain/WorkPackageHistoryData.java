package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A WorkPackage.
 */
@Document(collection = "work_package_history_data")
public class WorkPackageHistoryData extends WorkPackage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Field("work_package")
    private ObjectId workPackage;

	@Field("version")
    private String version;
	
	public WorkPackageHistoryData() {
		// TODO Auto-generated constructor stub
	}
	
	public WorkPackageHistoryData(WorkPackage workPackage2, String version) {
		// TODO Auto-generated constructor stub
		BeanUtils.copyProperties(workPackage2, this);
		this.setId(null);
		this.setVersion(version);
	}
	
	public ObjectId getWorkPackage() {
		return workPackage;
	}
	
	public void setWorkPackage(ObjectId workPackage) {
		this.workPackage = workPackage;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "WorkPackageHistoryData [workPackage=" + workPackage + "]";
	}
	
	
}
