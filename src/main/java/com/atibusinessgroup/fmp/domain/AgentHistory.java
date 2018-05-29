package com.atibusinessgroup.fmp.domain;

import org.springframework.data.mongodb.core.mapping.Field;

@org.springframework.data.mongodb.core.mapping.Document(collection = "agent_history")
public class AgentHistory extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Field("id_history")
    private String idHistory;
	


	public String getIdHistory() {
		return idHistory;
	}

	public void setIdHistory(String idHistory) {
		this.idHistory = idHistory;
	}

	
	
}
