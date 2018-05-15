package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Fare.
 */
@Document(collection = "work_package_fare_history_data")
public class WorkPackageFareHistoryData extends WorkPackageFare implements Serializable {

    private static final long serialVersionUID = 1L;
	
    public WorkPackageFareHistoryData() {
		// TODO Auto-generated constructor stub
	}

	public WorkPackageFareHistoryData(WorkPackageFare fares, String version) {
		// TODO Auto-generated constructor stub
		BeanUtils.copyProperties(fares, this);
		this.setId(null);
		this.setVersion(version);
	}
}
