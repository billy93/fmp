package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Contract.
 */

@Document(collection = "contract_fmp")
public class ContractFMP extends Contract implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
