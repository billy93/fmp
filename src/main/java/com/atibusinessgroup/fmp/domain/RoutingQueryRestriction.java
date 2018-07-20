package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Routingquery.
 */
@Document(collection = "atpco_routing_restriction")
public class RoutingQueryRestriction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("batch_number")
    private int batchNumber;

    @Field("link_no")
    private String linkNo;

    @Field("type")
    private String type;
    
    @Field("seq_no")
    private String seqNo;
    
    @Field("rest_no")
    private String restNo;
    
    @Field("market_info_ind")
    private String marketInfoInd;
    
    @Field("market_info_city_1")
    private String marketInfoCity1;
    
    @Field("market_info_city_2")
    private String marketInfoCity2;
    
    @Field("appl")
    private String appl;
    
    @Field("via_ind")
    private String viaInd;
    
    @Field("via_value")
    private String viaValue;
    
    @Field("via_non_dir")
    private String viaNonDir;
    
    @Field("viaAirSur")
    private String viaAirSur;
    
    @Field("mpm")
    private String mpm;
    
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

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getRestNo() {
		return restNo;
	}

	public void setRestNo(String restNo) {
		this.restNo = restNo;
	}

	public String getMarketInfoInd() {
		return marketInfoInd;
	}

	public void setMarketInfoInd(String marketInfoInd) {
		this.marketInfoInd = marketInfoInd;
	}

	public String getMarketInfoCity1() {
		return marketInfoCity1;
	}

	public void setMarketInfoCity1(String marketInfoCity1) {
		this.marketInfoCity1 = marketInfoCity1;
	}

	public String getMarketInfoCity2() {
		return marketInfoCity2;
	}

	public void setMarketInfoCity2(String marketInfoCity2) {
		this.marketInfoCity2 = marketInfoCity2;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	public String getViaInd() {
		return viaInd;
	}

	public void setViaInd(String viaInd) {
		this.viaInd = viaInd;
	}

	public String getViaValue() {
		return viaValue;
	}

	public void setViaValue(String viaValue) {
		this.viaValue = viaValue;
	}

	public String getViaNonDir() {
		return viaNonDir;
	}

	public void setViaNonDir(String viaNonDir) {
		this.viaNonDir = viaNonDir;
	}

	public String getViaAirSur() {
		return viaAirSur;
	}

	public void setViaAirSur(String viaAirSur) {
		this.viaAirSur = viaAirSur;
	}

	public String getMpm() {
		return mpm;
	}

	public void setMpm(String mpm) {
		this.mpm = mpm;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	@Override
	public String toString() {
		return "RoutingQueryRestriction [id=" + id + ", batchNumber=" + batchNumber + ", linkNo=" + linkNo + ", type="
				+ type + ", seqNo=" + seqNo + ", restNo=" + restNo + ", marketInfoInd=" + marketInfoInd
				+ ", marketInfoCity1=" + marketInfoCity1 + ", marketInfoCity2=" + marketInfoCity2 + ", appl=" + appl
				+ ", viaInd=" + viaInd + ", viaValue=" + viaValue + ", viaNonDir=" + viaNonDir + ", viaAirSur="
				+ viaAirSur + ", mpm=" + mpm + ", reserved=" + reserved + "]";
	}

	
}
