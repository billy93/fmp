package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rbdquery.
 */
@Document(collection = "rules_record_6")
public class RbdQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("record_batch")
    private Integer recordBatch;

    @Field("record_sequence")
    private Integer recordSequence;

    @Field("rec_type")
    private Integer recType;

    @Field("action")
    private String action;
    
    @Field("conv")
    private String conv;
    
    @Field("cxr_code")
    private String cxrCode;
    
    @Field("rules_tar")
    private String rulesTar;
    
    @Field("mcn")
    private String mcn;
    
    @Field("seq_no")
    private String sequenceNo;
    
    @Field("rule_no")
    private String ruleNo;
    
    @Field("dates_eff")
    private Object effectiveDate;
    
    @Field("dates_disc")
    private Object discontinueDate;
    
    @Field("batch_ci")
    private String batchCi;
    
    @Field("batch_no")
    private String batchNo;
    
    @Field("booking_code_tbl_no_999")
    private String bookingCodeTblNo999;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRecordBatch() {
        return recordBatch;
    }

    public RbdQuery recordBatch(Integer recordBatch) {
        this.recordBatch = recordBatch;
        return this;
    }

    public void setRecordBatch(Integer recordBatch) {
        this.recordBatch = recordBatch;
    }

    public Integer getRecordSequence() {
        return recordSequence;
    }

    public RbdQuery recordSequence(Integer recordSequence) {
        this.recordSequence = recordSequence;
        return this;
    }

    public void setRecordSequence(Integer recordSequence) {
        this.recordSequence = recordSequence;
    }

    public Integer getRecType() {
        return recType;
    }

    public RbdQuery recType(Integer recType) {
        this.recType = recType;
        return this;
    }

    public void setRecType(Integer recType) {
        this.recType = recType;
    }

    public String getAction() {
        return action;
    }

    public RbdQuery action(String action) {
        this.action = action;
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public String getConv() {
        return conv;
    }
    
    public RbdQuery conv(String conv) {
        this.conv = conv;
        return this;
    }
    
    public void setConv(String conv) {
        this.conv = conv;
    }
    
    public String getCxrCode() {
    	return cxrCode;
    }
    
    public RbdQuery cxrCode(String cxrCode) {
    	this.cxrCode = cxrCode;
    	return this;
    }
    
    public void setCxrCode(String cxrCode) {
    	this.cxrCode = cxrCode;
    }
    
    public String getRulesTar() {
    	return rulesTar;
    }
    
    public RbdQuery rulesTar(String rulesTar) {
    	this.rulesTar = rulesTar;
    	return this;
    }
    
    public void setRulesTar(String rulesTar) {
    	this.rulesTar = rulesTar;
    }
    
    public String getMcn() {
    	return mcn;
    }
    
    public RbdQuery mcn(String mcn) {
    	this.mcn = mcn;
    	return this;
    }
    
    public void setMcn(String mcn) {
    	this.mcn = mcn;
    }
    
    public String getSequenceNo() {
    	return sequenceNo;
    }
    
    public RbdQuery sequenceNo(String sequenceNo) {
    	this.sequenceNo = sequenceNo;
    	return this;
    }
    
    public void setSequenceNo(String sequenceNo) {
    	this.sequenceNo = sequenceNo;
    }
    
    public String getRuleNo() {
    	return ruleNo;
    }
    
    public RbdQuery ruleNo(String ruleNo) {
    	this.ruleNo = ruleNo;
    	return this;
    }
    
    public void setRuleNo(String ruleNo) {
    	this.ruleNo = ruleNo;
    }
    
    public Object getEffectiveDate() {
        return effectiveDate;
    }

    public RbdQuery effectiveDate(Object effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Object effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Object getDiscontinueDate() {
        return discontinueDate;
    }

    public RbdQuery discontinueDate(Object discontinueDate) {
        this.discontinueDate = discontinueDate;
        return this;
    }

    public void setDiscontinueDate(Object discontinueDate) {
        this.discontinueDate = discontinueDate;
    }
    
    public String getBatchCi() {
    	return batchCi;
    }
    
    public RbdQuery batchCi(String batchCi) {
    	this.batchCi = batchCi;
    	return this;
    }
    
    public void setBatchCi(String batchCi) {
    	this.batchCi = batchCi;
    }
    
    public String getBatchNo() {
    	return batchNo;
    }
    
    public RbdQuery batchNo(String batchNo) {
    	this.batchNo = batchNo;
    	return this;
    }
    
    public void setBatchNo(String batchNo) {
    	this.batchNo = batchNo;
    }
    
    public String getBookingCodeTblNo999() {
    	return bookingCodeTblNo999;
    }
    
    public RbdQuery bookingCodeTblNo999(String bookingCodeTblNo999) {
    	this.bookingCodeTblNo999 = bookingCodeTblNo999;
    	return this;
    }
    
    public void setBookingCodeTblNo999(String bookingCodeTblNo999) {
    	this.bookingCodeTblNo999 = bookingCodeTblNo999;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RbdQuery rbdquery = (RbdQuery) o;
        if (rbdquery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rbdquery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Rbdquery{" +
            "id=" + getId() +
            ", recordBatch=" + getRecordBatch() +
            ", recordSequence=" + getRecordSequence() +
            ", recType=" + getRecType() +
            ", action='" + getAction() + "'" +
            ", conv='" + getConv() + "'" +
            ", cxrCode='" + getCxrCode() + "'" +
            ", rulesTar='" + getRulesTar() + "'" +
            ", mcn='" + getMcn() + "'" +
            ", sequenceNo='" + getSequenceNo() + "'" +
            ", ruleNo='" + getRuleNo() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", discontinueDate='" + getDiscontinueDate() + "'" +
            ", batchCi='" + getBatchCi() + "'" +
            ", batchNo='" + getBatchNo() + "'" +
            ", bookingCodeTblNo999='" + getBookingCodeTblNo999() + "'" +
            "}";
    }
}
