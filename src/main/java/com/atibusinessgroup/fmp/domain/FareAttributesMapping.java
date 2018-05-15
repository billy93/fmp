package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.enumeration.OWRT;
import com.atibusinessgroup.fmp.domain.enumeration.Operation;
import com.atibusinessgroup.fmp.domain.enumeration.UnitType;

/**
 * A FareAttributesMapping.
 */
@Document(collection = "fare_attributes_mapping")
public class FareAttributesMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("oal")
    @DBRef(db="airline")
    private Airline oal;

    @Field("oal_origin")
    private String oalOrigin;

    @Field("oal_destination")
    private String oalDestination;

    @Field("oal_fbc")
    private String oalFBC;

    @Field("oal_cabin")
    private String oalCabin;

    @Field("oal_ftnt")
    private String oalFtnt;

    @Field("own_cxr")
    @DBRef(db="airline")
    private Airline ownCxr;

    @Field("own_alt_origin")
    private String ownAltOrigin;

    @Field("own_alt_destination")
    private String ownAltDestination;

    @Field("own_fbc")
    private String ownFBC;

    @Field("own_cabin")
    private String ownCabin;

    @Field("own_ftnt")
    private String ownFtnt;

    @Field("response_value")
    private String responseValue;

    @Field("response_type")
    private UnitType responseType;

    @Field("bidirectional")
    private String bidirectional;

    @Field("comment")
    private String comment;

    @Field("oal_owrt")
    private OWRT oalOWRT;

    @Field("own_owrt")
    private OWRT ownOWRT;

    @Field("response_criteria")
    private Operation responseCriteria;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Airline getOal() {
		return oal;
	}

	public void setOal(Airline oal) {
		this.oal = oal;
	}

	public Airline getOwnCxr() {
		return ownCxr;
	}

	public void setOwnCxr(Airline ownCxr) {
		this.ownCxr = ownCxr;
	}

	public String getOalOrigin() {
        return oalOrigin;
    }

    public FareAttributesMapping oalOrigin(String oalOrigin) {
        this.oalOrigin = oalOrigin;
        return this;
    }

    public void setOalOrigin(String oalOrigin) {
        this.oalOrigin = oalOrigin;
    }

    public String getOalDestination() {
        return oalDestination;
    }

    public FareAttributesMapping oalDestination(String oalDestination) {
        this.oalDestination = oalDestination;
        return this;
    }

    public void setOalDestination(String oalDestination) {
        this.oalDestination = oalDestination;
    }

    public String getOalFBC() {
        return oalFBC;
    }

    public FareAttributesMapping oalFBC(String oalFBC) {
        this.oalFBC = oalFBC;
        return this;
    }

    public void setOalFBC(String oalFBC) {
        this.oalFBC = oalFBC;
    }

    public String getOalCabin() {
        return oalCabin;
    }

    public FareAttributesMapping oalCabin(String oalCabin) {
        this.oalCabin = oalCabin;
        return this;
    }

    public void setOalCabin(String oalCabin) {
        this.oalCabin = oalCabin;
    }

    public String getOalFtnt() {
        return oalFtnt;
    }

    public FareAttributesMapping oalFtnt(String oalFtnt) {
        this.oalFtnt = oalFtnt;
        return this;
    }

    public void setOalFtnt(String oalFtnt) {
        this.oalFtnt = oalFtnt;
    }

    public String getOwnAltOrigin() {
        return ownAltOrigin;
    }

    public FareAttributesMapping ownAltOrigin(String ownAltOrigin) {
        this.ownAltOrigin = ownAltOrigin;
        return this;
    }

    public void setOwnAltOrigin(String ownAltOrigin) {
        this.ownAltOrigin = ownAltOrigin;
    }

    public String getOwnAltDestination() {
        return ownAltDestination;
    }

    public FareAttributesMapping ownAltDestination(String ownAltDestination) {
        this.ownAltDestination = ownAltDestination;
        return this;
    }

    public void setOwnAltDestination(String ownAltDestination) {
        this.ownAltDestination = ownAltDestination;
    }

    public String getOwnFBC() {
        return ownFBC;
    }

    public FareAttributesMapping ownFBC(String ownFBC) {
        this.ownFBC = ownFBC;
        return this;
    }

    public void setOwnFBC(String ownFBC) {
        this.ownFBC = ownFBC;
    }

    public String getOwnCabin() {
        return ownCabin;
    }

    public FareAttributesMapping ownCabin(String ownCabin) {
        this.ownCabin = ownCabin;
        return this;
    }

    public void setOwnCabin(String ownCabin) {
        this.ownCabin = ownCabin;
    }

    public String getOwnFtnt() {
        return ownFtnt;
    }

    public FareAttributesMapping ownFtnt(String ownFtnt) {
        this.ownFtnt = ownFtnt;
        return this;
    }

    public void setOwnFtnt(String ownFtnt) {
        this.ownFtnt = ownFtnt;
    }

    public String getResponseValue() {
        return responseValue;
    }

    public FareAttributesMapping responseValue(String responseValue) {
        this.responseValue = responseValue;
        return this;
    }

    public void setResponseValue(String responseValue) {
        this.responseValue = responseValue;
    }

    public UnitType getResponseType() {
        return responseType;
    }

    public FareAttributesMapping responseType(UnitType responseType) {
        this.responseType = responseType;
        return this;
    }

    public void setResponseType(UnitType responseType) {
        this.responseType = responseType;
    }

    public String getBidirectional() {
        return bidirectional;
    }

    public FareAttributesMapping bidirectional(String bidirectional) {
        this.bidirectional = bidirectional;
        return this;
    }

    public void setBidirectional(String bidirectional) {
        this.bidirectional = bidirectional;
    }

    public String getComment() {
        return comment;
    }

    public FareAttributesMapping comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public OWRT getOalOWRT() {
        return oalOWRT;
    }

    public FareAttributesMapping oalOWRT(OWRT oalOWRT) {
        this.oalOWRT = oalOWRT;
        return this;
    }

    public void setOalOWRT(OWRT oalOWRT) {
        this.oalOWRT = oalOWRT;
    }

    public OWRT getOwnOWRT() {
        return ownOWRT;
    }

    public FareAttributesMapping ownOWRT(OWRT ownOWRT) {
        this.ownOWRT = ownOWRT;
        return this;
    }

    public void setOwnOWRT(OWRT ownOWRT) {
        this.ownOWRT = ownOWRT;
    }

    public Operation getResponseCriteria() {
        return responseCriteria;
    }

    public FareAttributesMapping responseCriteria(Operation responseCriteria) {
        this.responseCriteria = responseCriteria;
        return this;
    }

    public void setResponseCriteria(Operation responseCriteria) {
        this.responseCriteria = responseCriteria;
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
        FareAttributesMapping fareAttributesMapping = (FareAttributesMapping) o;
        if (fareAttributesMapping.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fareAttributesMapping.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FareAttributesMapping{" +
            "id=" + getId() +
            ", oal='" + getOal() + "'" +
            ", oalOrigin='" + getOalOrigin() + "'" +
            ", oalDestination='" + getOalDestination() + "'" +
            ", oalFBC='" + getOalFBC() + "'" +
            ", oalCabin='" + getOalCabin() + "'" +
            ", oalFtnt='" + getOalFtnt() + "'" +
            ", ownCxr='" + getOwnCxr() + "'" +
            ", ownAltOrigin='" + getOwnAltOrigin() + "'" +
            ", ownAltDestination='" + getOwnAltDestination() + "'" +
            ", ownFBC='" + getOwnFBC() + "'" +
            ", ownCabin='" + getOwnCabin() + "'" +
            ", ownFtnt='" + getOwnFtnt() + "'" +
            ", responseValue='" + getResponseValue() + "'" +
            ", responseType='" + getResponseType() + "'" +
            ", bidirectional='" + getBidirectional() + "'" +
            ", comment='" + getComment() + "'" +
            ", oalOWRT='" + getOalOWRT() + "'" +
            ", ownOWRT='" + getOwnOWRT() + "'" +
            ", responseCriteria='" + getResponseCriteria() + "'" +
            "}";
    }
}
