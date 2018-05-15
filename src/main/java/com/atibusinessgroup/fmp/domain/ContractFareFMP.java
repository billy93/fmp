package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Contract Fare.
 */

@Document(collection = "contract_fare_fmp")
public class ContractFareFMP extends ContractFare implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
}
