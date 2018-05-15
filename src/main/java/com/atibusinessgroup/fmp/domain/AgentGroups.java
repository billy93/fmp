package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A AgentGroups.
 */
@Document(collection = "agent_groups")
public class AgentGroups implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("agency_group_name")
    private String agencyGroupName;

   
    @Field("agent_name")
    private List<String> agentName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgencyGroupName() {
        return agencyGroupName;
    }

    public AgentGroups agencyGroupName(String agencyGroupName) {
        this.agencyGroupName = agencyGroupName;
        return this;
    }

    public void setAgencyGroupName(String agencyGroupName) {
        this.agencyGroupName = agencyGroupName;
    }

    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AgentGroups agentGroups = (AgentGroups) o;
        if (agentGroups.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agentGroups.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "AgentGroups [id=" + id + ", agencyGroupName=" + agencyGroupName + ", agentName=" + agentName + "]";
	}

	public List<String> getAgentName() {
		return agentName;
	}

	public void setAgentName(List<String> agentName) {
		this.agentName = agentName;
	}

    
}
