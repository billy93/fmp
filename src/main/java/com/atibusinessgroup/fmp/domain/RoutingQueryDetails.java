package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Routingquery.
 */
@Document(collection = "Full_Map_Routing_Details")
public class RoutingQueryDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("batch_number")
    private int batchNumber;

    @Field("link_no")
    private String linkNo;

    @Field("type")
    private String type;
    
    @Field("city_1_no")
    private String cityNo;
    
    @Field("city_1_id")
    private String cityId;
    
    @Field("city_1_name")
    private String cityName;
    
    @Field("city_1_tag")
    private String cityTag;
    
    @Field("next_city")
    private String nextCity;
    
    @Field("alternate_city")
    private String alternateCity;
    
    @Field("reserved")
    private String reserved;

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

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityTag() {
		return cityTag;
	}

	public void setCityTag(String cityTag) {
		this.cityTag = cityTag;
	}

	public String getNextCity() {
		return nextCity;
	}

	public void setNextCity(String nextCity) {
		this.nextCity = nextCity;
	}

	public String getAlternateCity() {
		return alternateCity;
	}

	public void setAlternateCity(String alternateCity) {
		this.alternateCity = alternateCity;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	@Override
	public String toString() {
		return "RoutingQueryDetails [id=" + id + ", batchNumber=" + batchNumber + ", linkNo=" + linkNo + ", type="
				+ type + ", cityNo=" + cityNo + ", cityId=" + cityId + ", cityName=" + cityName + ", cityTag=" + cityTag
				+ ", nextCity=" + nextCity + ", alternateCity=" + alternateCity + ", reserved=" + reserved + "]";
	}


}
