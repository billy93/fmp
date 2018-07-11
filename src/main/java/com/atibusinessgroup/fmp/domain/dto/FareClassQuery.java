package com.atibusinessgroup.fmp.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_1")
public class FareClassQuery implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    private String id;
	
	@Field("cxr_code")
	private String cxr;
	
	@Field("tar_no")
	private String tarNo;
	
	@Field("tar_cd")
	private String tarCd;
	
	@Field("description")
	private String description;
	
	@Field("rule_no")
	private String ruleNo;
	
	@Field("fare_class")
	private String fareClass;
	
	@Field("seq_no")
	private String seqNo;
	
	@Field("geo_type_1")
	private String geoType1;
	
	@Field("geo_loc_1")
	private String geoLoc1;
	
	@Field("geo_type_2")
	private String geoType2;
	
	@Field("geo_loc_2")
	private String geoLoc2;
	
	@Field("ft_nt")
	private String ftnt;
	
	@Field("rtg_no")
	private String rtgNo;
	
	@Field("season_type")
	private String seasonType;
	
	@Field("day_of_week_type")
	private String dowType;
	
	@Field("fare_type")
	private String fareType;
	
	@Field("owrt")
	private String owrt;
	
	@Field("normal_special")
	private String normalSpecial;
	
	@Field("display_type")
	private String displayType;
	
	@Field("dates_eff")
	private String datesEff;
	
	@Field("dates_disc")
	private String datesDisc;
	
	@Field("fare_class_information")
    private List<AtpcoRecord1FareClassInformation> fareClassInformation = new ArrayList<>();
	
	private List<String> bkcd = new ArrayList<>();
	private List<String> paxType = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCxr() {
		return cxr;
	}

	public void setCxr(String cxr) {
		this.cxr = cxr;
	}

	public String getTarNo() {
		return tarNo;
	}

	public void setTarNo(String tarNo) {
		this.tarNo = tarNo;
	}

	public String getTarCd() {
		return tarCd;
	}

	public void setTarCd(String tarCd) {
		this.tarCd = tarCd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getFareClass() {
		return fareClass;
	}

	public void setFareClass(String fareClass) {
		this.fareClass = fareClass;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getGeoType1() {
		return geoType1;
	}

	public void setGeoType1(String geoType1) {
		this.geoType1 = geoType1;
	}

	public String getGeoLoc1() {
		return geoLoc1;
	}

	public void setGeoLoc1(String geoLoc1) {
		this.geoLoc1 = geoLoc1;
	}

	public String getGeoType2() {
		return geoType2;
	}

	public void setGeoType2(String geoType2) {
		this.geoType2 = geoType2;
	}

	public String getGeoLoc2() {
		return geoLoc2;
	}

	public void setGeoLoc2(String geoLoc2) {
		this.geoLoc2 = geoLoc2;
	}

	public String getFtnt() {
		return ftnt;
	}

	public void setFtnt(String ftnt) {
		this.ftnt = ftnt;
	}

	public String getRtgNo() {
		return rtgNo;
	}

	public void setRtgNo(String rtgNo) {
		this.rtgNo = rtgNo;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}

	public String getDowType() {
		return dowType;
	}

	public void setDowType(String dowType) {
		this.dowType = dowType;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public String getNormalSpecial() {
		return normalSpecial;
	}

	public void setNormalSpecial(String normalSpecial) {
		this.normalSpecial = normalSpecial;
	}

	public String getDisplayType() {
		return displayType;
	}

	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}

	public String getDatesEff() {
		return datesEff;
	}

	public void setDatesEff(String datesEff) {
		this.datesEff = datesEff;
	}

	public String getDatesDisc() {
		return datesDisc;
	}

	public void setDatesDisc(String datesDisc) {
		this.datesDisc = datesDisc;
	}
	
	public List<AtpcoRecord1FareClassInformation> getFareClassInformation() {
		return fareClassInformation;
	}

	public void setFareClassInformation(List<AtpcoRecord1FareClassInformation> fareClassInformation) {
		this.fareClassInformation = fareClassInformation;
	}

	public List<String> getBkcd() {
		return bkcd;
	}

	public void setBkcd(List<String> bkcd) {
		this.bkcd = bkcd;
	}
	
	public List<String> getPaxType() {
		return paxType;
	}

	public void setPaxType(List<String> paxType) {
		this.paxType = paxType;
	}

	@Override
	public String toString() {
		return "FareClassQuery [id=" + id + ", cxr=" + cxr + ", tarNo=" + tarNo + ", tarCd=" + tarCd + ", description="
				+ description + ", ruleNo=" + ruleNo + ", fareClass=" + fareClass + ", seqNo=" + seqNo + ", geoType1="
				+ geoType1 + ", geoLoc1=" + geoLoc1 + ", geoType2=" + geoType2 + ", geoLoc2=" + geoLoc2 + ", ftnt="
				+ ftnt + ", rtgNo=" + rtgNo + ", seasonType=" + seasonType + ", dowType=" + dowType + ", fareType="
				+ fareType + ", owrt=" + owrt + ", normalSpecial=" + normalSpecial + ", displayType=" + displayType
				+ ", datesEff=" + datesEff + ", datesDisc=" + datesDisc + ", fareClassInformation="
				+ fareClassInformation + ", bkcd=" + bkcd + ", paxType=" + paxType + "]";
	}

}
