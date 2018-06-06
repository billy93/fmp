package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Counter {

    @Id
    private String id;

    @Field("sequence_value")
    private int sequenceValue;

    @Field("year")
    private String year;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSequenceValue() {
		return sequenceValue;
	}

	public void setSequenceValue(int sequenceValue) {
		this.sequenceValue = sequenceValue;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
    
    
}
