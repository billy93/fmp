package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Contract.
 */

@Document(collection = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("contract_type")
    private String contractType;

    @Field("status")
    private String status;

    @Field("target_system")
    private List<String> target_system;

    @Field("data")
    private List<Section> data;

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Section> getData() {
		return data;
	}

	public void setData(List<Section> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getTarget_system() {
		return target_system;
	}

	public void setTarget_system(List<String> target_system) {
		this.target_system = target_system;
	}
	
	
}
