package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Contract Fare.
 */

@Document(collection = "contract_fare")
public class ContractFare extends AbstractAuditingEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Field("idContract")
	private String idContract;
	
	@Field("forms")
	private List<Form> forms = new ArrayList<Form>();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public String getIdContract() {
		return idContract;
	}

	public void setIdContract(String idContract) {
		this.idContract = idContract;
	}
}
