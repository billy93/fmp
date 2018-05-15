package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "addOn")
public class AddOn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Field("addOnGDS")
	private String addOnGDS;

	@Field("addOnID")
	private String addOnID;
	
	@Field("addOnGdsName")
	private String addOnGdsName;
	
	@Field("supplierID")
	private String supplierID;

	@Field("description")
	private String description;

	@Field("carrier")
	private Airline carrier;

	@Field("typeOfJourney")
	private String typeOfJourney;

	@Field("direction")
	private String direction;

	@Field("fare")
	private String fare;

	@Field("effectiveDate")
	private ZonedDateTime effectiveDate;

	@Field("discontinueDate")
	private ZonedDateTime discontinueDate;

	@Field("currencyAmount")
	private CurrencyAmount currencyAmount;

	@Field("interiorCityZone")
	private String interiorCityZone;
	
	@Field("endPointCityZone")
	private String endPointCityZone;

	@Field("locationInteriorCityZone")
	private String locationInteriorCityZone;

	@Field("gatewayCityZone")
	private String gatewayCityZone;

	@Field("locationGatewayCityZone")
	private String locationGatewayCityZone;

	@Field("permittedCarrier")
	private List<PermittedCarrier> permittedCarrier;

	@Field("ruleIDS")
	private String ruleIDS;

	@Field("fareTypeoption")
	private boolean fareTypeoption;

	@Field("fareBasisCodeoption")
	private boolean fareBasisCodeoption;

	@Field("fareType")
	private List<String> fareType;

	@Field("fareBasisCode")
	private String fareBasisCode;
	
	@Field("passengerType")
	private Passenger passengerType;

	@Field("secondaryPassengerType")
	private List<SecondaryPassengerType> secondaryPassengerType;

    @Field("errorAPF")
    private List<String> errorAPF = new ArrayList<String>();

    @Field("errorAmadeus")
    private List<String> errorAmadeus = new ArrayList<String>();

    @Field("isUpdate")
	private boolean isUpdate;
	
	
	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getAddOnID() {
		return addOnID;
	}

	public void setAddOnID(String addOnID) {
		this.addOnID = addOnID;
	}

	public String getAddOnGdsName() {
		return addOnGdsName;
	}

	public void setAddOnGdsName(String addOnGdsName) {
		this.addOnGdsName = addOnGdsName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Airline getCarrier() {
		return carrier;
	}

	public void setCarrier(Airline carrier) {
		this.carrier = carrier;
	}

	public String getTypeOfJourney() {
		return typeOfJourney;
	}

	public void setTypeOfJourney(String typeOfJourney) {
		this.typeOfJourney = typeOfJourney;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public ZonedDateTime getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(ZonedDateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public ZonedDateTime getDiscontinueDate() {
		return discontinueDate;
	}

	public void setDiscontinueDate(ZonedDateTime discontinueDate) {
		this.discontinueDate = discontinueDate;
	}

	public Passenger getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(Passenger passengerType) {
		this.passengerType = passengerType;
	}

	public String getEndPointCityZone() {
		return endPointCityZone;
	}

	public void setEndPointCityZone(String endPointCityZone) {
		this.endPointCityZone = endPointCityZone;
	}
	public String getInteriorCityZone() {
		return interiorCityZone;
	}

	public void setInteriorCityZone(String interiorCityZone) {
		this.interiorCityZone = interiorCityZone;
	}

	public String getLocationInteriorCityZone() {
		return locationInteriorCityZone;
	}

	public void setLocationInteriorCityZone(String locationInteriorCityZone) {
		this.locationInteriorCityZone = locationInteriorCityZone;
	}

	public String getGatewayCityZone() {
		return gatewayCityZone;
	}

	public void setGatewayCityZone(String gatewayCityZone) {
		this.gatewayCityZone = gatewayCityZone;
	}

	public String getLocationGatewayCityZone() {
		return locationGatewayCityZone;
	}

	public void setLocationGatewayCityZone(String locationGatewayCityZone) {
		this.locationGatewayCityZone = locationGatewayCityZone;
	}

	public List<PermittedCarrier> getPermittedCarrier() {
		return permittedCarrier;
	}

	public void setPermittedCarrier(List<PermittedCarrier> permittedCarrier) {
		this.permittedCarrier = permittedCarrier;
	}

	public String getFare() {
		return fare;
	}

	public void setFare(String fare) {
		this.fare = fare;
	}

	public CurrencyAmount getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(CurrencyAmount currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getRuleIDS() {
		return ruleIDS;
	}

	public void setRuleIDS(String ruleIDS) {
		this.ruleIDS = ruleIDS;
	}

	public boolean isFareTypeoption() {
		return fareTypeoption;
	}

	public void setFareTypeoption(boolean fareTypeoption) {
		this.fareTypeoption = fareTypeoption;
	}

	public boolean isFareBasisCodeoption() {
		return fareBasisCodeoption;
	}

	public void setFareBasisCodeoption(boolean fareBasisCodeoption) {
		this.fareBasisCodeoption = fareBasisCodeoption;
	}

	public List<String> getFareType() {
		return fareType;
	}

	public void setFareType(List<String> fareType) {
		this.fareType = fareType;
	}

	public String getFareBasisCode() {
		return fareBasisCode;
	}

	public void setFareBasisCode(String fareBasisCode) {
		this.fareBasisCode = fareBasisCode;
	}

	public List<SecondaryPassengerType> getSecondaryPassengerType() {
		return secondaryPassengerType;
	}

	public void setSecondaryPassengerType(List<SecondaryPassengerType> secondaryPassengerType) {
		this.secondaryPassengerType = secondaryPassengerType;
	}

    public List<String> getErrorAPF() {
        return errorAPF;
    }

    public void setErrorAPF(List<String> errorAPF) {
        this.errorAPF = errorAPF;
    }

    public List<String> getErrorAmadeus() {
        return errorAmadeus;
    }

    public void setErrorAmadeus(List<String> errorAmadeus) {
        this.errorAmadeus = errorAmadeus;
    }
}
