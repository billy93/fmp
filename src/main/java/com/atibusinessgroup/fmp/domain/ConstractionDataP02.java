package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_construction_record_P02")
public class ConstractionDataP02 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    @Field("key_id")
    private String keyId;
    
    @Field("key_tar_no")
    private String keyTarNo;
    
    @Field("key_filler")
    private String keyFiller;
    
    @Field("key_cxr_code")
    private String keyCxrCode;
    
    @Field("key_intl_fare_class")
    private String keyIntlFareClass;
    
    @Field("action")
    private String action;
    
    @Field("ow_rt")
    private String owrt;
    
    @Field("us_all")
    private String usAll;
    
    @Field("us_gen")
    private String usGen;
    
    @Field("nus_all")
    private String nusAll;
    
    @Field("nus_gen")
    private String nusGen;
    
    @Field("nuc")
    private String nuc;
    
    @Field("eff_date")
    private Object effDate;
    
    @Field("disc_date")
    private Object discDate;
    
    @Field("filler")
    private String filler;
    
    @Field("batch_number")
    private int batchNumber;
    
    @Field("batch_date")
    private Object batchDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getKeyTarNo() {
		return keyTarNo;
	}

	public void setKeyTarNo(String keyTarNo) {
		this.keyTarNo = keyTarNo;
	}

	public String getKeyFiller() {
		return keyFiller;
	}

	public void setKeyFiller(String keyFiller) {
		this.keyFiller = keyFiller;
	}

	public String getKeyCxrCode() {
		return keyCxrCode;
	}

	public void setKeyCxrCode(String keyCxrCode) {
		this.keyCxrCode = keyCxrCode;
	}

	public String getKeyIntlFareClass() {
		return keyIntlFareClass;
	}

	public void setKeyIntlFareClass(String keyIntlFareClass) {
		this.keyIntlFareClass = keyIntlFareClass;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public String getUsAll() {
		return usAll;
	}

	public void setUsAll(String usAll) {
		this.usAll = usAll;
	}

	public String getUsGen() {
		return usGen;
	}

	public void setUsGen(String usGen) {
		this.usGen = usGen;
	}

	public String getNusAll() {
		return nusAll;
	}

	public void setNusAll(String nusAll) {
		this.nusAll = nusAll;
	}

	public String getNusGen() {
		return nusGen;
	}

	public void setNusGen(String nusGen) {
		this.nusGen = nusGen;
	}

	public String getNuc() {
		return nuc;
	}

	public void setNuc(String nuc) {
		this.nuc = nuc;
	}

	public Object getEffDate() {
		return effDate;
	}

	public void setEffDate(Object effDate) {
		this.effDate = effDate;
	}

	public Object getDiscDate() {
		return discDate;
	}

	public void setDiscDate(Object discDate) {
		this.discDate = discDate;
	}

	public String getFiller() {
		return filler;
	}

	public void setFiller(String filler) {
		this.filler = filler;
	}

	public int getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(int batchNumber) {
		this.batchNumber = batchNumber;
	}

	public Object getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Object batchDate) {
		this.batchDate = batchDate;
	}

	@Override
	public String toString() {
		return "ConstractionDataP02 [id=" + id + ", keyId=" + keyId + ", keyTarNo=" + keyTarNo + ", keyFiller="
				+ keyFiller + ", keyCxrCode=" + keyCxrCode + ", keyIntlFareClass=" + keyIntlFareClass + ", action="
				+ action + ", owrt=" + owrt + ", usAll=" + usAll + ", usGen=" + usGen + ", nusAll=" + nusAll
				+ ", nusGen=" + nusGen + ", nuc=" + nuc + ", effDate=" + effDate + ", discDate=" + discDate
				+ ", filler=" + filler + ", batchNumber=" + batchNumber + ", batchDate=" + batchDate + "]";
	}
    
    
}
