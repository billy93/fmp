package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A Routingquery.
 */
@Document(collection = "Full_Map_Routing_Header")
public class RoutingQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("batch_number")
    private int batchNumber;

    @Field("link_no")
    private String linkNo;

    //@Field("src")
    private String src;

    @Field("cxr_cd")
    private String cxr;

    @Field("tar_no")
    private String tarNo;

    @Field("rtg_no")
    private String routingNo;

    @Field("dates_effective")
    private Object effectiveDate;

    @Field("dates_discontinue")
    private Object discontinuedDate;

    @Field("drv")
    private String drv;

    @Field("cp")
    private String cpi;

    @Field("di")
    private String di;

    @Field("int_pt")
    private String intPt;

    @Field("unt_pt")
    private String untPt;
    
    private ArrayList<ArrayList<String>> details;
    private List<RoutingQueryRestriction> restrictions;
    private List<RoutingQueryTextRestriction> texts;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
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

    public RoutingQuery linkNo(String linkNo) {
        this.linkNo = linkNo;
        return this;
    }

    public void setLinkNo(String linkNo) {
        this.linkNo = linkNo;
    }

    public String getSrc() {
        return src;
    }

    public RoutingQuery src(String src) {
        this.src = src;
        return this;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getCxr() {
        return cxr;
    }

    public RoutingQuery cxr(String cxr) {
        this.cxr = cxr;
        return this;
    }

    public void setCxr(String cxr) {
        this.cxr = cxr;
    }

    public String getTarNo() {
        return tarNo;
    }

    public RoutingQuery tarNo(String tarNo) {
        this.tarNo = tarNo;
        return this;
    }

    public void setTarNo(String tarNo) {
        this.tarNo = tarNo;
    }

    public String getRoutingNo() {
        return routingNo;
    }

    public RoutingQuery routingNo(String routingNo) {
        this.routingNo = routingNo;
        return this;
    }

    public void setRoutingNo(String routingNo) {
        this.routingNo = routingNo;
    }

    public Object getEffectiveDate() {
        return effectiveDate;
    }

    public RoutingQuery effectiveDate(Object effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Object effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Object getDiscontinuedDate() {
        return discontinuedDate;
    }

    public RoutingQuery discontinuedDate(Object discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
        return this;
    }

    public void setDiscontinuedDate(Object discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    public String getDrv() {
        return drv;
    }

    public RoutingQuery drv(String drv) {
        this.drv = drv;
        return this;
    }

    public void setDrv(String drv) {
        this.drv = drv;
    }

    public String getCpi() {
        return cpi;
    }

    public RoutingQuery cpi(String cpi) {
        this.cpi = cpi;
        return this;
    }

    public void setCpi(String cpi) {
        this.cpi = cpi;
    }

    public String getDi() {
        return di;
    }

    public RoutingQuery di(String di) {
        this.di = di;
        return this;
    }

    public void setDi(String di) {
        this.di = di;
    }

    public String getIntPt() {
        return intPt;
    }

    public RoutingQuery intPt(String intPt) {
        this.intPt = intPt;
        return this;
    }

    public void setIntPt(String intPt) {
        this.intPt = intPt;
    }

    public String getUntPt() {
        return untPt;
    }

    public RoutingQuery untPt(String untPt) {
        this.untPt = untPt;
        return this;
    }

    public void setUntPt(String untPt) {
        this.untPt = untPt;
    }

    public ArrayList<ArrayList<String>> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<ArrayList<String>> details) {
		this.details = details;
	}

	public List<RoutingQueryRestriction> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(List<RoutingQueryRestriction> restrictions) {
		this.restrictions = restrictions;
	}

	public List<RoutingQueryTextRestriction> getTexts() {
		return texts;
	}

	public void setTexts(List<RoutingQueryTextRestriction> texts) {
		this.texts = texts;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoutingQuery routingquery = (RoutingQuery) o;
        if (routingquery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), routingquery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "Routingquery [id=" + id + ", batchNumber=" + batchNumber + ", linkNo=" + linkNo + ", src=" + src
				+ ", cxr=" + cxr + ", tarNo=" + tarNo + ", routingNo=" + routingNo + ", effectiveDate=" + effectiveDate
				+ ", discontinuedDate=" + discontinuedDate + ", drv=" + drv + ", cpi=" + cpi + ", di=" + di + ", intPt="
				+ intPt + ", untPt=" + untPt + ", details=" + details + ", restrictions=" + restrictions + ", texts="
				+ texts + "]";
	}

    
}
