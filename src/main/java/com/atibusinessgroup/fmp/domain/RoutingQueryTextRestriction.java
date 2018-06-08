package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Routingquery.
 */
@Document(collection = "Full_Map_Routing_Text_Restriction")
public class RoutingQueryTextRestriction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("batch_number")
    private int batchNumber;

    @Field("link_no")
    private String linkNo;

    @Field("type")
    private String type;
    
    @Field("text")
    private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getLinkNo() {
		return linkNo;
	}

	public void setLinkNo(String linkNo) {
		this.linkNo = linkNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "RoutingQueryTextRestriction [id=" + id + ", batchNumber=" + batchNumber + ", linkNo=" + linkNo
				+ ", type=" + type + ", text=" + text + "]";
	}


}