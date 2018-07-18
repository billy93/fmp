package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CcfCarrier.
 */
@Document(collection = "atpco_ccf_carrier")
public class CcfCarrier implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("svc_tags_psgdom")
    private String svcTagsPsgdom;

    @Field("filler")
    private String filler;

    @Field("svc_tags_cardom")
    private String svcTagsCardom;

    @Field("cxr_code")
    private String cxrCode;
    
    @Field("cxr_name")
    private String cxrName;

    @Field("rec_type")
    private String recType;

    @Field("batch_number")
    private String batchNumber;

    @Field("svc_tags_psgint")
    private String svcTagsPsgint;

    @Field("batch_date")
    private Object batchDate;

    @Field("svg_tags_carint")
    private String svgTagsCarint;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSvcTagsPsgdom() {
		return svcTagsPsgdom;
	}

	public void setSvcTagsPsgdom(String svcTagsPsgdom) {
		this.svcTagsPsgdom = svcTagsPsgdom;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public String getSvcTagsCardom() {
		return svcTagsCardom;
	}

	public void setSvcTagsCardom(String svcTagsCardom) {
		this.svcTagsCardom = svcTagsCardom;
	}

	public String getCxrCode() {
		return cxrCode;
	}

	public void setCxrCode(String cxrCode) {
		this.cxrCode = cxrCode;
	}

	public String getCxrName() {
		return cxrName;
	}

	public void setCxrName(String cxrName) {
		this.cxrName = cxrName;
	}

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getSvcTagsPsgint() {
		return svcTagsPsgint;
	}

	public void setSvcTagsPsgint(String svcTagsPsgint) {
		this.svcTagsPsgint = svcTagsPsgint;
	}

	public Object getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Object batchDate) {
		this.batchDate = batchDate;
	}

	public String getSvgTagsCarint() {
		return svgTagsCarint;
	}

	public void setSvgTagsCarint(String svgTagsCarint) {
		this.svgTagsCarint = svgTagsCarint;
	}

	@Override
	public String toString() {
		return "CcfCarrier [id=" + id + ", svcTagsPsgdom=" + svcTagsPsgdom + ", filler=" + filler + ", svcTagsCardom="
				+ svcTagsCardom + ", cxrCode=" + cxrCode + ", cxrName=" + cxrName + ", recType=" + recType
				+ ", batchNumber=" + batchNumber + ", svcTagsPsgint=" + svcTagsPsgint + ", batchDate=" + batchDate
				+ ", svgTagsCarint=" + svgTagsCarint + "]";
	}

    
    
}
