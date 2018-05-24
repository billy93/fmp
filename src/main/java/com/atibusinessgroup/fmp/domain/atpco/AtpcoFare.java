package com.atibusinessgroup.fmp.domain.atpco;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.AtpcoFareChangeTags;

@Document(collection = "atpco_fare")
public class AtpcoFare {

	@Id
    private String id;
	
	@Field("source")
    private String source;
	
	@Field("tar_no")
    private String tariffNo;
	
	@Field("cxr_cd")
    private String carrierCode;
	
	@Field("airport_origin")
    private String originAirport;
	
	@Field("origin_city")
    private String originCity;
	
	@Field("origin_country")
    private String originCountry;
	
	@Field("airport_destination")
    private String destinationAirport;
	
	@Field("destination_city")
    private String destinationCity;
	
	@Field("destination_country")
    private String destinationCountry;
	
	@Field("fare_class_cd")
    private String fareClassCode;
	
	@Field("tar_eff_date")
    private Object tariffEffectiveDateObject;
	
	@Field("dates_effective")
    private Object effectiveDateObject;
	
	@Field("dates_discontinue")
    private Object discontinueDateObject;
	
	@Field("sale_dates_first")
    private Object firstSaleDateObject;
	
	@Field("sale_dates_last")
    private Object lastSaleDateObject;
	
	@Field("rules_no")
    private String ruleNo;
	
	@Field("rtg_no")
    private String routingNo;
	
	@Field("ow_rt")
    private String owrt;
	
	@Field("fare_origin_amount")
    private Decimal128 fareOriginAmount; 

	@Field("fare_origin_cur_cd")
    private String fareOriginCurrencyCode;
	
	@Field("fare_origin_dec")
    private String fareOriginAmountDecimal;
	
	@Field("fare_destination_amount")
    private Decimal128 fareDestinationAmount;
	
	@Field("fare_destination_cur_cd")
    private String fareDestinationCurrencyCode;
	
	@Field("fare_destination_dec")
    private String fareDestinationAmountDecimal;
	
	@Field("fare_other_amount")
    private Decimal128 fareOtherAmount;
	
	@Field("fare_other_cur_cd")
    private String fareOtherCurrencyCode;
	
	@Field("fare_other_dec")
    private String fareOtherAmountDecimal;
	
	@Field("ftnt")
    private String footnote;
	
	@Field("directional_indicator")
    private String directionalIndicator;
	
	@Field("global_indicator")
    private String globalIndicator;
	
	@Field("mpm")
    private String maximumPermittedMileage;
	
	@Field("cab")
    private String cab;
	
	@Field("origin_addon_fare_class_cd")
    private String addOnOriginFareClassCode;
	
	@Field("origin_addon_rtg")
    private String addOnOriginRouting;
	
	@Field("origin_addon_ftnt")
    private String addOnOriginFootnote;
	
	@Field("origin_addon_gateway")
    private String addOnOriginGateway;
	
	@Field("origin_addon_sign")
    private String addOnOriginSign;
	
	@Field("origin_addon_amount")
    private Decimal128 addOnOriginAmount;
	
	@Field("origin_addon_cur")
    private String addOnOriginCurrencyCode;
	
	@Field("origin_addon_dec")
    private String addOnOriginAmountDecimal;
	
	@Field("destination_addon_fare_class_cd")
    private String addOnDestinationFareClassCode;
	
	@Field("destination_addon_rtg")
    private String addOnDestinationRouting;
	
	@Field("destination_addon_ftnt")
    private String addOnDestinationFootnote;
	
	@Field("destination_addon_gateway")
    private String addOnDestinationGateway;
	
	@Field("destination_addon_sign")
    private String addOnDestinationSign;
	
	@Field("destination_addon_amount")
    private Decimal128 addOnDestinationAmount;
	
	@Field("destination_addon_cur")
    private String addOnDestinationCurrencyCode;
	
	@Field("destination_addon_dec")
    private String addOnDestinationAmountDecimal;
	
	@Field("pub_fare_ftnt")
    private String publishedFareFootnote;
	
	@Field("pub_fare_amount")
    private Decimal128 publishedFareAmount;
	
	@Field("pub_fare_cur")
    private String publishedFareCurrencyCode;
	
	@Field("pub_fare_dec")
    private String publishedFareAmountDecimal;
	
	@Field("action")
    private String action;
	
	@Field("mcn")
    private String mcn;
	
	@Field("old_mcn")
    private String oldMcn;
	
	@Field("batch_ci")
    private String batchCi;
	
	@Field("batch_no")
    private String batchNo;
	
	@Field("prop")
    private String proposalNumber;
	
	@Field("link_no")
    private String linkNo;
	
	@Field("link_seq")
    private String linkSequence;
	
	@Field("type")
    private String fareType;
	
	@Field("gfs_date")
    private Object gfsDateObject;
	
	@Field("gfs_number")
    private String gfsNumber;
	
	@Field("change_tags")
	private AtpcoFareChangeTags changeTags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTariffNo() {
		return tariffNo;
	}

	public void setTariffNo(String tariffNo) {
		this.tariffNo = tariffNo;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getOriginAirport() {
		return originAirport;
	}

	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getFareClassCode() {
		return fareClassCode;
	}

	public void setFareClassCode(String fareClassCode) {
		this.fareClassCode = fareClassCode;
	}

	public Object getTariffEffectiveDateObject() {
		return tariffEffectiveDateObject;
	}

	public void setTariffEffectiveDateObject(Object tariffEffectiveDateObject) {
		this.tariffEffectiveDateObject = tariffEffectiveDateObject;
	}

	public Object getEffectiveDateObject() {
		return effectiveDateObject;
	}

	public void setEffectiveDateObject(Object effectiveDateObject) {
		this.effectiveDateObject = effectiveDateObject;
	}

	public Object getDiscontinueDateObject() {
		return discontinueDateObject;
	}

	public void setDiscontinueDateObject(Object discontinueDateObject) {
		this.discontinueDateObject = discontinueDateObject;
	}

	public Object getFirstSaleDateObject() {
		return firstSaleDateObject;
	}

	public void setFirstSaleDateObject(Object firstSaleDateObject) {
		this.firstSaleDateObject = firstSaleDateObject;
	}

	public Object getLastSaleDateObject() {
		return lastSaleDateObject;
	}

	public void setLastSaleDateObject(Object lastSaleDateObject) {
		this.lastSaleDateObject = lastSaleDateObject;
	}

	public Object getGfsDateObject() {
		return gfsDateObject;
	}

	public void setGfsDateObject(Object gfsDateObject) {
		this.gfsDateObject = gfsDateObject;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getRoutingNo() {
		return routingNo;
	}

	public void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public Decimal128 getFareOriginAmount() {
		return fareOriginAmount;
	}

	public void setFareOriginAmount(Decimal128 fareOriginAmount) {
		this.fareOriginAmount = fareOriginAmount;
	}

	public String getFareOriginCurrencyCode() {
		return fareOriginCurrencyCode;
	}

	public void setFareOriginCurrencyCode(String fareOriginCurrencyCode) {
		this.fareOriginCurrencyCode = fareOriginCurrencyCode;
	}

	public String getFareOriginAmountDecimal() {
		return fareOriginAmountDecimal;
	}

	public void setFareOriginAmountDecimal(String fareOriginAmountDecimal) {
		this.fareOriginAmountDecimal = fareOriginAmountDecimal;
	}

	public Decimal128 getFareDestinationAmount() {
		return fareDestinationAmount;
	}

	public void setFareDestinationAmount(Decimal128 fareDestinationAmount) {
		this.fareDestinationAmount = fareDestinationAmount;
	}

	public String getFareDestinationCurrencyCode() {
		return fareDestinationCurrencyCode;
	}

	public void setFareDestinationCurrencyCode(String fareDestinationCurrencyCode) {
		this.fareDestinationCurrencyCode = fareDestinationCurrencyCode;
	}

	public String getFareDestinationAmountDecimal() {
		return fareDestinationAmountDecimal;
	}

	public void setFareDestinationAmountDecimal(String fareDestinationAmountDecimal) {
		this.fareDestinationAmountDecimal = fareDestinationAmountDecimal;
	}

	public Decimal128 getFareOtherAmount() {
		return fareOtherAmount;
	}

	public void setFareOtherAmount(Decimal128 fareOtherAmount) {
		this.fareOtherAmount = fareOtherAmount;
	}

	public String getFareOtherCurrencyCode() {
		return fareOtherCurrencyCode;
	}

	public void setFareOtherCurrencyCode(String fareOtherCurrencyCode) {
		this.fareOtherCurrencyCode = fareOtherCurrencyCode;
	}

	public String getFareOtherAmountDecimal() {
		return fareOtherAmountDecimal;
	}

	public void setFareOtherAmountDecimal(String fareOtherAmountDecimal) {
		this.fareOtherAmountDecimal = fareOtherAmountDecimal;
	}

	public String getFootnote() {
		return footnote;
	}

	public void setFootnote(String footnote) {
		this.footnote = footnote;
	}

	public String getDirectionalIndicator() {
		return directionalIndicator;
	}

	public void setDirectionalIndicator(String directionalIndicator) {
		this.directionalIndicator = directionalIndicator;
	}

	public String getGlobalIndicator() {
		return globalIndicator;
	}

	public void setGlobalIndicator(String globalIndicator) {
		this.globalIndicator = globalIndicator;
	}

	public String getMaximumPermittedMileage() {
		return maximumPermittedMileage;
	}

	public void setMaximumPermittedMileage(String maximumPermittedMileage) {
		this.maximumPermittedMileage = maximumPermittedMileage;
	}

	public String getCab() {
		return cab;
	}

	public void setCab(String cab) {
		this.cab = cab;
	}

	public String getAddOnOriginFareClassCode() {
		return addOnOriginFareClassCode;
	}

	public void setAddOnOriginFareClassCode(String addOnOriginFareClassCode) {
		this.addOnOriginFareClassCode = addOnOriginFareClassCode;
	}

	public String getAddOnOriginRouting() {
		return addOnOriginRouting;
	}

	public void setAddOnOriginRouting(String addOnOriginRouting) {
		this.addOnOriginRouting = addOnOriginRouting;
	}

	public String getAddOnOriginFootnote() {
		return addOnOriginFootnote;
	}

	public void setAddOnOriginFootnote(String addOnOriginFootnote) {
		this.addOnOriginFootnote = addOnOriginFootnote;
	}

	public String getAddOnOriginGateway() {
		return addOnOriginGateway;
	}

	public void setAddOnOriginGateway(String addOnOriginGateway) {
		this.addOnOriginGateway = addOnOriginGateway;
	}

	public String getAddOnOriginSign() {
		return addOnOriginSign;
	}

	public void setAddOnOriginSign(String addOnOriginSign) {
		this.addOnOriginSign = addOnOriginSign;
	}

	public Decimal128 getAddOnOriginAmount() {
		return addOnOriginAmount;
	}

	public void setAddOnOriginAmount(Decimal128 addOnOriginAmount) {
		this.addOnOriginAmount = addOnOriginAmount;
	}

	public String getAddOnOriginCurrencyCode() {
		return addOnOriginCurrencyCode;
	}

	public void setAddOnOriginCurrencyCode(String addOnOriginCurrencyCode) {
		this.addOnOriginCurrencyCode = addOnOriginCurrencyCode;
	}

	public String getAddOnOriginAmountDecimal() {
		return addOnOriginAmountDecimal;
	}

	public void setAddOnOriginAmountDecimal(String addOnOriginAmountDecimal) {
		this.addOnOriginAmountDecimal = addOnOriginAmountDecimal;
	}

	public String getAddOnDestinationFareClassCode() {
		return addOnDestinationFareClassCode;
	}

	public void setAddOnDestinationFareClassCode(String addOnDestinationFareClassCode) {
		this.addOnDestinationFareClassCode = addOnDestinationFareClassCode;
	}

	public String getAddOnDestinationRouting() {
		return addOnDestinationRouting;
	}

	public void setAddOnDestinationRouting(String addOnDestinationRouting) {
		this.addOnDestinationRouting = addOnDestinationRouting;
	}

	public String getAddOnDestinationFootnote() {
		return addOnDestinationFootnote;
	}

	public void setAddOnDestinationFootnote(String addOnDestinationFootnote) {
		this.addOnDestinationFootnote = addOnDestinationFootnote;
	}

	public String getAddOnDestinationGateway() {
		return addOnDestinationGateway;
	}

	public void setAddOnDestinationGateway(String addOnDestinationGateway) {
		this.addOnDestinationGateway = addOnDestinationGateway;
	}

	public String getAddOnDestinationSign() {
		return addOnDestinationSign;
	}

	public void setAddOnDestinationSign(String addOnDestinationSign) {
		this.addOnDestinationSign = addOnDestinationSign;
	}

	public Decimal128 getAddOnDestinationAmount() {
		return addOnDestinationAmount;
	}

	public void setAddOnDestinationAmount(Decimal128 addOnDestinationAmount) {
		this.addOnDestinationAmount = addOnDestinationAmount;
	}

	public String getAddOnDestinationCurrencyCode() {
		return addOnDestinationCurrencyCode;
	}

	public void setAddOnDestinationCurrencyCode(String addOnDestinationCurrencyCode) {
		this.addOnDestinationCurrencyCode = addOnDestinationCurrencyCode;
	}

	public String getAddOnDestinationAmountDecimal() {
		return addOnDestinationAmountDecimal;
	}

	public void setAddOnDestinationAmountDecimal(String addOnDestinationAmountDecimal) {
		this.addOnDestinationAmountDecimal = addOnDestinationAmountDecimal;
	}

	public String getPublishedFareFootnote() {
		return publishedFareFootnote;
	}

	public void setPublishedFareFootnote(String publishedFareFootnote) {
		this.publishedFareFootnote = publishedFareFootnote;
	}

	public Decimal128 getPublishedFareAmount() {
		return publishedFareAmount;
	}

	public void setPublishedFareAmount(Decimal128 publishedFareAmount) {
		this.publishedFareAmount = publishedFareAmount;
	}

	public String getPublishedFareCurrencyCode() {
		return publishedFareCurrencyCode;
	}

	public void setPublishedFareCurrencyCode(String publishedFareCurrencyCode) {
		this.publishedFareCurrencyCode = publishedFareCurrencyCode;
	}

	public String getPublishedFareAmountDecimal() {
		return publishedFareAmountDecimal;
	}

	public void setPublishedFareAmountDecimal(String publishedFareAmountDecimal) {
		this.publishedFareAmountDecimal = publishedFareAmountDecimal;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMcn() {
		return mcn;
	}

	public void setMcn(String mcn) {
		this.mcn = mcn;
	}

	public String getOldMcn() {
		return oldMcn;
	}

	public void setOldMcn(String oldMcn) {
		this.oldMcn = oldMcn;
	}

	public String getBatchCi() {
		return batchCi;
	}

	public void setBatchCi(String batchCi) {
		this.batchCi = batchCi;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public String getLinkNo() {
		return linkNo;
	}

	public void setLinkNo(String linkNo) {
		this.linkNo = linkNo;
	}

	public String getLinkSequence() {
		return linkSequence;
	}

	public void setLinkSequence(String linkSequence) {
		this.linkSequence = linkSequence;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}
	
	public String getGfsNumber() {
		return gfsNumber;
	}

	public void setGfsNumber(String gfsNumber) {
		this.gfsNumber = gfsNumber;
	}

	public AtpcoFareChangeTags getChangeTags() {
		return changeTags;
	}

	public void setChangeTags(AtpcoFareChangeTags changeTags) {
		this.changeTags = changeTags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((addOnDestinationAmount == null) ? 0 : addOnDestinationAmount.hashCode());
		result = prime * result
				+ ((addOnDestinationAmountDecimal == null) ? 0 : addOnDestinationAmountDecimal.hashCode());
		result = prime * result
				+ ((addOnDestinationCurrencyCode == null) ? 0 : addOnDestinationCurrencyCode.hashCode());
		result = prime * result
				+ ((addOnDestinationFareClassCode == null) ? 0 : addOnDestinationFareClassCode.hashCode());
		result = prime * result + ((addOnDestinationFootnote == null) ? 0 : addOnDestinationFootnote.hashCode());
		result = prime * result + ((addOnDestinationGateway == null) ? 0 : addOnDestinationGateway.hashCode());
		result = prime * result + ((addOnDestinationRouting == null) ? 0 : addOnDestinationRouting.hashCode());
		result = prime * result + ((addOnDestinationSign == null) ? 0 : addOnDestinationSign.hashCode());
		result = prime * result + ((addOnOriginAmount == null) ? 0 : addOnOriginAmount.hashCode());
		result = prime * result + ((addOnOriginAmountDecimal == null) ? 0 : addOnOriginAmountDecimal.hashCode());
		result = prime * result + ((addOnOriginCurrencyCode == null) ? 0 : addOnOriginCurrencyCode.hashCode());
		result = prime * result + ((addOnOriginFareClassCode == null) ? 0 : addOnOriginFareClassCode.hashCode());
		result = prime * result + ((addOnOriginFootnote == null) ? 0 : addOnOriginFootnote.hashCode());
		result = prime * result + ((addOnOriginGateway == null) ? 0 : addOnOriginGateway.hashCode());
		result = prime * result + ((addOnOriginRouting == null) ? 0 : addOnOriginRouting.hashCode());
		result = prime * result + ((addOnOriginSign == null) ? 0 : addOnOriginSign.hashCode());
		result = prime * result + ((batchCi == null) ? 0 : batchCi.hashCode());
		result = prime * result + ((batchNo == null) ? 0 : batchNo.hashCode());
		result = prime * result + ((cab == null) ? 0 : cab.hashCode());
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((changeTags == null) ? 0 : changeTags.hashCode());
		result = prime * result + ((destinationAirport == null) ? 0 : destinationAirport.hashCode());
		result = prime * result + ((destinationCity == null) ? 0 : destinationCity.hashCode());
		result = prime * result + ((destinationCountry == null) ? 0 : destinationCountry.hashCode());
		result = prime * result + ((directionalIndicator == null) ? 0 : directionalIndicator.hashCode());
		result = prime * result + ((discontinueDateObject == null) ? 0 : discontinueDateObject.hashCode());
		result = prime * result + ((effectiveDateObject == null) ? 0 : effectiveDateObject.hashCode());
		result = prime * result + ((fareClassCode == null) ? 0 : fareClassCode.hashCode());
		result = prime * result + ((fareDestinationAmount == null) ? 0 : fareDestinationAmount.hashCode());
		result = prime * result
				+ ((fareDestinationAmountDecimal == null) ? 0 : fareDestinationAmountDecimal.hashCode());
		result = prime * result + ((fareDestinationCurrencyCode == null) ? 0 : fareDestinationCurrencyCode.hashCode());
		result = prime * result + ((fareOriginAmount == null) ? 0 : fareOriginAmount.hashCode());
		result = prime * result + ((fareOriginAmountDecimal == null) ? 0 : fareOriginAmountDecimal.hashCode());
		result = prime * result + ((fareOriginCurrencyCode == null) ? 0 : fareOriginCurrencyCode.hashCode());
		result = prime * result + ((fareOtherAmount == null) ? 0 : fareOtherAmount.hashCode());
		result = prime * result + ((fareOtherAmountDecimal == null) ? 0 : fareOtherAmountDecimal.hashCode());
		result = prime * result + ((fareOtherCurrencyCode == null) ? 0 : fareOtherCurrencyCode.hashCode());
		result = prime * result + ((fareType == null) ? 0 : fareType.hashCode());
		result = prime * result + ((firstSaleDateObject == null) ? 0 : firstSaleDateObject.hashCode());
		result = prime * result + ((footnote == null) ? 0 : footnote.hashCode());
		result = prime * result + ((gfsDateObject == null) ? 0 : gfsDateObject.hashCode());
		result = prime * result + ((gfsNumber == null) ? 0 : gfsNumber.hashCode());
		result = prime * result + ((globalIndicator == null) ? 0 : globalIndicator.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastSaleDateObject == null) ? 0 : lastSaleDateObject.hashCode());
		result = prime * result + ((linkNo == null) ? 0 : linkNo.hashCode());
		result = prime * result + ((linkSequence == null) ? 0 : linkSequence.hashCode());
		result = prime * result + ((maximumPermittedMileage == null) ? 0 : maximumPermittedMileage.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((oldMcn == null) ? 0 : oldMcn.hashCode());
		result = prime * result + ((originAirport == null) ? 0 : originAirport.hashCode());
		result = prime * result + ((originCity == null) ? 0 : originCity.hashCode());
		result = prime * result + ((originCountry == null) ? 0 : originCountry.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((proposalNumber == null) ? 0 : proposalNumber.hashCode());
		result = prime * result + ((publishedFareAmount == null) ? 0 : publishedFareAmount.hashCode());
		result = prime * result + ((publishedFareAmountDecimal == null) ? 0 : publishedFareAmountDecimal.hashCode());
		result = prime * result + ((publishedFareCurrencyCode == null) ? 0 : publishedFareCurrencyCode.hashCode());
		result = prime * result + ((publishedFareFootnote == null) ? 0 : publishedFareFootnote.hashCode());
		result = prime * result + ((routingNo == null) ? 0 : routingNo.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((tariffEffectiveDateObject == null) ? 0 : tariffEffectiveDateObject.hashCode());
		result = prime * result + ((tariffNo == null) ? 0 : tariffNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AtpcoFare other = (AtpcoFare) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (addOnDestinationAmount == null) {
			if (other.addOnDestinationAmount != null) {
				return false;
			}
		} else if (!addOnDestinationAmount.equals(other.addOnDestinationAmount)) {
			return false;
		}
		if (addOnDestinationAmountDecimal == null) {
			if (other.addOnDestinationAmountDecimal != null) {
				return false;
			}
		} else if (!addOnDestinationAmountDecimal.equals(other.addOnDestinationAmountDecimal)) {
			return false;
		}
		if (addOnDestinationCurrencyCode == null) {
			if (other.addOnDestinationCurrencyCode != null) {
				return false;
			}
		} else if (!addOnDestinationCurrencyCode.equals(other.addOnDestinationCurrencyCode)) {
			return false;
		}
		if (addOnDestinationFareClassCode == null) {
			if (other.addOnDestinationFareClassCode != null) {
				return false;
			}
		} else if (!addOnDestinationFareClassCode.equals(other.addOnDestinationFareClassCode)) {
			return false;
		}
		if (addOnDestinationFootnote == null) {
			if (other.addOnDestinationFootnote != null) {
				return false;
			}
		} else if (!addOnDestinationFootnote.equals(other.addOnDestinationFootnote)) {
			return false;
		}
		if (addOnDestinationGateway == null) {
			if (other.addOnDestinationGateway != null) {
				return false;
			}
		} else if (!addOnDestinationGateway.equals(other.addOnDestinationGateway)) {
			return false;
		}
		if (addOnDestinationRouting == null) {
			if (other.addOnDestinationRouting != null) {
				return false;
			}
		} else if (!addOnDestinationRouting.equals(other.addOnDestinationRouting)) {
			return false;
		}
		if (addOnDestinationSign == null) {
			if (other.addOnDestinationSign != null) {
				return false;
			}
		} else if (!addOnDestinationSign.equals(other.addOnDestinationSign)) {
			return false;
		}
		if (addOnOriginAmount == null) {
			if (other.addOnOriginAmount != null) {
				return false;
			}
		} else if (!addOnOriginAmount.equals(other.addOnOriginAmount)) {
			return false;
		}
		if (addOnOriginAmountDecimal == null) {
			if (other.addOnOriginAmountDecimal != null) {
				return false;
			}
		} else if (!addOnOriginAmountDecimal.equals(other.addOnOriginAmountDecimal)) {
			return false;
		}
		if (addOnOriginCurrencyCode == null) {
			if (other.addOnOriginCurrencyCode != null) {
				return false;
			}
		} else if (!addOnOriginCurrencyCode.equals(other.addOnOriginCurrencyCode)) {
			return false;
		}
		if (addOnOriginFareClassCode == null) {
			if (other.addOnOriginFareClassCode != null) {
				return false;
			}
		} else if (!addOnOriginFareClassCode.equals(other.addOnOriginFareClassCode)) {
			return false;
		}
		if (addOnOriginFootnote == null) {
			if (other.addOnOriginFootnote != null) {
				return false;
			}
		} else if (!addOnOriginFootnote.equals(other.addOnOriginFootnote)) {
			return false;
		}
		if (addOnOriginGateway == null) {
			if (other.addOnOriginGateway != null) {
				return false;
			}
		} else if (!addOnOriginGateway.equals(other.addOnOriginGateway)) {
			return false;
		}
		if (addOnOriginRouting == null) {
			if (other.addOnOriginRouting != null) {
				return false;
			}
		} else if (!addOnOriginRouting.equals(other.addOnOriginRouting)) {
			return false;
		}
		if (addOnOriginSign == null) {
			if (other.addOnOriginSign != null) {
				return false;
			}
		} else if (!addOnOriginSign.equals(other.addOnOriginSign)) {
			return false;
		}
		if (batchCi == null) {
			if (other.batchCi != null) {
				return false;
			}
		} else if (!batchCi.equals(other.batchCi)) {
			return false;
		}
		if (batchNo == null) {
			if (other.batchNo != null) {
				return false;
			}
		} else if (!batchNo.equals(other.batchNo)) {
			return false;
		}
		if (cab == null) {
			if (other.cab != null) {
				return false;
			}
		} else if (!cab.equals(other.cab)) {
			return false;
		}
		if (carrierCode == null) {
			if (other.carrierCode != null) {
				return false;
			}
		} else if (!carrierCode.equals(other.carrierCode)) {
			return false;
		}
		if (changeTags == null) {
			if (other.changeTags != null) {
				return false;
			}
		} else if (!changeTags.equals(other.changeTags)) {
			return false;
		}
		if (destinationAirport == null) {
			if (other.destinationAirport != null) {
				return false;
			}
		} else if (!destinationAirport.equals(other.destinationAirport)) {
			return false;
		}
		if (destinationCity == null) {
			if (other.destinationCity != null) {
				return false;
			}
		} else if (!destinationCity.equals(other.destinationCity)) {
			return false;
		}
		if (destinationCountry == null) {
			if (other.destinationCountry != null) {
				return false;
			}
		} else if (!destinationCountry.equals(other.destinationCountry)) {
			return false;
		}
		if (directionalIndicator == null) {
			if (other.directionalIndicator != null) {
				return false;
			}
		} else if (!directionalIndicator.equals(other.directionalIndicator)) {
			return false;
		}
		if (discontinueDateObject == null) {
			if (other.discontinueDateObject != null) {
				return false;
			}
		} else if (!discontinueDateObject.equals(other.discontinueDateObject)) {
			return false;
		}
		if (effectiveDateObject == null) {
			if (other.effectiveDateObject != null) {
				return false;
			}
		} else if (!effectiveDateObject.equals(other.effectiveDateObject)) {
			return false;
		}
		if (fareClassCode == null) {
			if (other.fareClassCode != null) {
				return false;
			}
		} else if (!fareClassCode.equals(other.fareClassCode)) {
			return false;
		}
		if (fareDestinationAmount == null) {
			if (other.fareDestinationAmount != null) {
				return false;
			}
		} else if (!fareDestinationAmount.equals(other.fareDestinationAmount)) {
			return false;
		}
		if (fareDestinationAmountDecimal == null) {
			if (other.fareDestinationAmountDecimal != null) {
				return false;
			}
		} else if (!fareDestinationAmountDecimal.equals(other.fareDestinationAmountDecimal)) {
			return false;
		}
		if (fareDestinationCurrencyCode == null) {
			if (other.fareDestinationCurrencyCode != null) {
				return false;
			}
		} else if (!fareDestinationCurrencyCode.equals(other.fareDestinationCurrencyCode)) {
			return false;
		}
		if (fareOriginAmount == null) {
			if (other.fareOriginAmount != null) {
				return false;
			}
		} else if (!fareOriginAmount.equals(other.fareOriginAmount)) {
			return false;
		}
		if (fareOriginAmountDecimal == null) {
			if (other.fareOriginAmountDecimal != null) {
				return false;
			}
		} else if (!fareOriginAmountDecimal.equals(other.fareOriginAmountDecimal)) {
			return false;
		}
		if (fareOriginCurrencyCode == null) {
			if (other.fareOriginCurrencyCode != null) {
				return false;
			}
		} else if (!fareOriginCurrencyCode.equals(other.fareOriginCurrencyCode)) {
			return false;
		}
		if (fareOtherAmount == null) {
			if (other.fareOtherAmount != null) {
				return false;
			}
		} else if (!fareOtherAmount.equals(other.fareOtherAmount)) {
			return false;
		}
		if (fareOtherAmountDecimal == null) {
			if (other.fareOtherAmountDecimal != null) {
				return false;
			}
		} else if (!fareOtherAmountDecimal.equals(other.fareOtherAmountDecimal)) {
			return false;
		}
		if (fareOtherCurrencyCode == null) {
			if (other.fareOtherCurrencyCode != null) {
				return false;
			}
		} else if (!fareOtherCurrencyCode.equals(other.fareOtherCurrencyCode)) {
			return false;
		}
		if (fareType == null) {
			if (other.fareType != null) {
				return false;
			}
		} else if (!fareType.equals(other.fareType)) {
			return false;
		}
		if (firstSaleDateObject == null) {
			if (other.firstSaleDateObject != null) {
				return false;
			}
		} else if (!firstSaleDateObject.equals(other.firstSaleDateObject)) {
			return false;
		}
		if (footnote == null) {
			if (other.footnote != null) {
				return false;
			}
		} else if (!footnote.equals(other.footnote)) {
			return false;
		}
		if (gfsDateObject == null) {
			if (other.gfsDateObject != null) {
				return false;
			}
		} else if (!gfsDateObject.equals(other.gfsDateObject)) {
			return false;
		}
		if (gfsNumber == null) {
			if (other.gfsNumber != null) {
				return false;
			}
		} else if (!gfsNumber.equals(other.gfsNumber)) {
			return false;
		}
		if (globalIndicator == null) {
			if (other.globalIndicator != null) {
				return false;
			}
		} else if (!globalIndicator.equals(other.globalIndicator)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastSaleDateObject == null) {
			if (other.lastSaleDateObject != null) {
				return false;
			}
		} else if (!lastSaleDateObject.equals(other.lastSaleDateObject)) {
			return false;
		}
		if (linkNo == null) {
			if (other.linkNo != null) {
				return false;
			}
		} else if (!linkNo.equals(other.linkNo)) {
			return false;
		}
		if (linkSequence == null) {
			if (other.linkSequence != null) {
				return false;
			}
		} else if (!linkSequence.equals(other.linkSequence)) {
			return false;
		}
		if (maximumPermittedMileage == null) {
			if (other.maximumPermittedMileage != null) {
				return false;
			}
		} else if (!maximumPermittedMileage.equals(other.maximumPermittedMileage)) {
			return false;
		}
		if (mcn == null) {
			if (other.mcn != null) {
				return false;
			}
		} else if (!mcn.equals(other.mcn)) {
			return false;
		}
		if (oldMcn == null) {
			if (other.oldMcn != null) {
				return false;
			}
		} else if (!oldMcn.equals(other.oldMcn)) {
			return false;
		}
		if (originAirport == null) {
			if (other.originAirport != null) {
				return false;
			}
		} else if (!originAirport.equals(other.originAirport)) {
			return false;
		}
		if (originCity == null) {
			if (other.originCity != null) {
				return false;
			}
		} else if (!originCity.equals(other.originCity)) {
			return false;
		}
		if (originCountry == null) {
			if (other.originCountry != null) {
				return false;
			}
		} else if (!originCountry.equals(other.originCountry)) {
			return false;
		}
		if (owrt == null) {
			if (other.owrt != null) {
				return false;
			}
		} else if (!owrt.equals(other.owrt)) {
			return false;
		}
		if (proposalNumber == null) {
			if (other.proposalNumber != null) {
				return false;
			}
		} else if (!proposalNumber.equals(other.proposalNumber)) {
			return false;
		}
		if (publishedFareAmount == null) {
			if (other.publishedFareAmount != null) {
				return false;
			}
		} else if (!publishedFareAmount.equals(other.publishedFareAmount)) {
			return false;
		}
		if (publishedFareAmountDecimal == null) {
			if (other.publishedFareAmountDecimal != null) {
				return false;
			}
		} else if (!publishedFareAmountDecimal.equals(other.publishedFareAmountDecimal)) {
			return false;
		}
		if (publishedFareCurrencyCode == null) {
			if (other.publishedFareCurrencyCode != null) {
				return false;
			}
		} else if (!publishedFareCurrencyCode.equals(other.publishedFareCurrencyCode)) {
			return false;
		}
		if (publishedFareFootnote == null) {
			if (other.publishedFareFootnote != null) {
				return false;
			}
		} else if (!publishedFareFootnote.equals(other.publishedFareFootnote)) {
			return false;
		}
		if (routingNo == null) {
			if (other.routingNo != null) {
				return false;
			}
		} else if (!routingNo.equals(other.routingNo)) {
			return false;
		}
		if (ruleNo == null) {
			if (other.ruleNo != null) {
				return false;
			}
		} else if (!ruleNo.equals(other.ruleNo)) {
			return false;
		}
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		if (tariffEffectiveDateObject == null) {
			if (other.tariffEffectiveDateObject != null) {
				return false;
			}
		} else if (!tariffEffectiveDateObject.equals(other.tariffEffectiveDateObject)) {
			return false;
		}
		if (tariffNo == null) {
			if (other.tariffNo != null) {
				return false;
			}
		} else if (!tariffNo.equals(other.tariffNo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoFare [id=" + id + ", source=" + source + ", tariffNo=" + tariffNo + ", carrierCode=" + carrierCode
				+ ", originAirport=" + originAirport + ", originCity=" + originCity + ", originCountry=" + originCountry
				+ ", destinationAirport=" + destinationAirport + ", destinationCity=" + destinationCity
				+ ", destinationCountry=" + destinationCountry + ", fareClassCode=" + fareClassCode
				+ ", tariffEffectiveDateObject=" + tariffEffectiveDateObject + ", effectiveDateObject="
				+ effectiveDateObject + ", discontinueDateObject=" + discontinueDateObject + ", firstSaleDateObject="
				+ firstSaleDateObject + ", lastSaleDateObject=" + lastSaleDateObject + ", ruleNo=" + ruleNo
				+ ", routingNo=" + routingNo + ", owrt=" + owrt + ", fareOriginAmount=" + fareOriginAmount
				+ ", fareOriginCurrencyCode=" + fareOriginCurrencyCode + ", fareOriginAmountDecimal="
				+ fareOriginAmountDecimal + ", fareDestinationAmount=" + fareDestinationAmount
				+ ", fareDestinationCurrencyCode=" + fareDestinationCurrencyCode + ", fareDestinationAmountDecimal="
				+ fareDestinationAmountDecimal + ", fareOtherAmount=" + fareOtherAmount + ", fareOtherCurrencyCode="
				+ fareOtherCurrencyCode + ", fareOtherAmountDecimal=" + fareOtherAmountDecimal + ", footnote="
				+ footnote + ", directionalIndicator=" + directionalIndicator + ", globalIndicator=" + globalIndicator
				+ ", maximumPermittedMileage=" + maximumPermittedMileage + ", cab=" + cab
				+ ", addOnOriginFareClassCode=" + addOnOriginFareClassCode + ", addOnOriginRouting="
				+ addOnOriginRouting + ", addOnOriginFootnote=" + addOnOriginFootnote + ", addOnOriginGateway="
				+ addOnOriginGateway + ", addOnOriginSign=" + addOnOriginSign + ", addOnOriginAmount="
				+ addOnOriginAmount + ", addOnOriginCurrencyCode=" + addOnOriginCurrencyCode
				+ ", addOnOriginAmountDecimal=" + addOnOriginAmountDecimal + ", addOnDestinationFareClassCode="
				+ addOnDestinationFareClassCode + ", addOnDestinationRouting=" + addOnDestinationRouting
				+ ", addOnDestinationFootnote=" + addOnDestinationFootnote + ", addOnDestinationGateway="
				+ addOnDestinationGateway + ", addOnDestinationSign=" + addOnDestinationSign
				+ ", addOnDestinationAmount=" + addOnDestinationAmount + ", addOnDestinationCurrencyCode="
				+ addOnDestinationCurrencyCode + ", addOnDestinationAmountDecimal=" + addOnDestinationAmountDecimal
				+ ", publishedFareFootnote=" + publishedFareFootnote + ", publishedFareAmount=" + publishedFareAmount
				+ ", publishedFareCurrencyCode=" + publishedFareCurrencyCode + ", publishedFareAmountDecimal="
				+ publishedFareAmountDecimal + ", action=" + action + ", mcn=" + mcn + ", oldMcn=" + oldMcn
				+ ", batchCi=" + batchCi + ", batchNo=" + batchNo + ", proposalNumber=" + proposalNumber + ", linkNo="
				+ linkNo + ", linkSequence=" + linkSequence + ", fareType=" + fareType + ", gfsDateObject="
				+ gfsDateObject + ", gfsNumber=" + gfsNumber + ", changeTags=" + changeTags + "]";
	}
}
