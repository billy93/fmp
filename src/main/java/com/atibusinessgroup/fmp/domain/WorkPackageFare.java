package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Fare.
 */
@Document(collection = "work_package_fare")
public class WorkPackageFare implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("status")
    private String status;
        
    @Field("action")
    private String action;
    
    @Field("tour_code")
    private String tourCode;
    
    @Field("recommended_amount")
    private String recommendedAmount;
    
    @Field("tariff_number")
    private TariffNumber tariffNumber;
    
    @Field("tarno")
    private String tarno;
    
    @Field("tarcd")
    private String tarcd;
    
    @Field("cabin")
    private String cabin;
    
    @Field("fareType")
    private String fareType;
    
    @Field("footnote1")
    private String footnote1;
    
    @Field("footnote2")
    private String footnote2;
    
    @Field("ssn")
    private String ssn;
    
    @Field("eff_date")
    private String effDate;
    
    @Field("itinerary")
    private String itinerary;
    
    @Field("override_indicator")
    private String overrideIndicator;
    
    @Field("loc1")
    private String loc1;
    
    @Field("loc1_type")
    private String loc1Type;
    
    @Field("loc2")
    private String loc2;
    
    @Field("loc2_type")
    private String loc2Type;
    
    @Field("base_fare_basis")
    private String baseFareBasis;
    
    @Field("calc_type")
    private String calcType;
    
    @Field("percent_base_fare")
    private String percentBaseFare;
    
    @Field("base_rule_no")
    private String baseRuleNo;
    
	public String getBaseRuleNo() {
		return baseRuleNo;
	}

	public void setBaseRuleNo(String baseRuleNo) {
		this.baseRuleNo = baseRuleNo;
	}

	public String getBaseFareBasis() {
		return baseFareBasis;
	}

	public void setBaseFareBasis(String baseFareBasis) {
		this.baseFareBasis = baseFareBasis;
	}

	public TariffNumber getTariffNumber() {
		return tariffNumber;
	}

	public void setTariffNumber(TariffNumber tariffNumber) {
		this.tariffNumber = tariffNumber;
	}

	public String getPercentBaseFare() {
		return percentBaseFare;
	}

	public void setPercentBaseFare(String percentBaseFare) {
		this.percentBaseFare = percentBaseFare;
	}

	public String getCalcType() {
		return calcType;
	}

	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}

	public String getLoc1() {
		return loc1;
	}

	public void setLoc1(String loc1) {
		this.loc1 = loc1;
	}

	public String getLoc1Type() {
		return loc1Type;
	}

	public void setLoc1Type(String loc1Type) {
		this.loc1Type = loc1Type;
	}

	public String getLoc2() {
		return loc2;
	}

	public void setLoc2(String loc2) {
		this.loc2 = loc2;
	}

	public String getLoc2Type() {
		return loc2Type;
	}

	public void setLoc2Type(String loc2Type) {
		this.loc2Type = loc2Type;
	}

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	public String getOverrideIndicator() {
		return overrideIndicator;
	}

	public void setOverrideIndicator(String overrideIndicator) {
		this.overrideIndicator = overrideIndicator;
	}

	public String getFootnote1() {
		return footnote1;
	}

	public void setFootnote1(String footnote1) {
		this.footnote1 = footnote1;
	}

	public String getFootnote2() {
		return footnote2;
	}

	public void setFootnote2(String footnote2) {
		this.footnote2 = footnote2;
	}

	public String getFareType() {
		return fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getTarno() {
		return tarno;
	}

	public void setTarno(String tarno) {
		this.tarno = tarno;
	}

	public String getTarcd() {
		return tarcd;
	}

	public void setTarcd(String tarcd) {
		this.tarcd = tarcd;
	}

	public String getRecommendedAmount() {
		return recommendedAmount;
	}

	public void setRecommendedAmount(String recommendedAmount) {
		this.recommendedAmount = recommendedAmount;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}

	public String getTarif() {
		return tarif;
	}

	public void setTarif(String tarif) {
		this.tarif = tarif;
	}

	@Field("tarif")
    private String tarif;
    
    public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	@Field("carrier")
    private String carrier;
    
    @Field("booking_class")
    private String bookingClass;
    
    @Field("origin")
    private String origin;
    
    @Field("direction")
    private String direction;
    
    @Field("destination")
    private String destination;
    
    @Field("fare_basis")
    private String fareBasis;
   
    @Field("new_fare_basis")
    private String newFareBasis;
    
    public String getNewFareBasis() {
		return newFareBasis;
	}

	public void setNewFareBasis(String newFareBasis) {
		this.newFareBasis = newFareBasis;
	}

	@Field("type_of_journey")
    private String typeOfJourney;
    
	@Field("new_type_of_journey")
    private String newTypeOfJourney;
    
    @Field("passenger_type")
    private String passengerType;
  
    @Field("currency")
    private String currency;
    
    @Field("amount")
    private String amount;
    
    @Field("taxes")
    private List<Tax> taxes;
    
    @Field("yqyr")
    private String yqyr;
    
    @Field("cat12")
    private String cat12;
    
    @Field("total_tax")
    private String totalTax;
    
    @Field("tfc")
    private String tfc;
    
    @Field("aif")
    private String aif;
    
    @Field("global")
    private String global;
    
    @Field("cabin_class")
    private String cabinClass;
    
    @Field("travel_start")
    private String travelStart;
    
    @Field("travel_end")
    private String travelEnd;
    
    @Field("sale_start")
    private String saleStart;
    
    @Field("sale_end")
    private String saleEnd;
    
    @Field("travel_complete")
    private String travelComplete;
    
    @Field("travel_complete_indicator")
    private String travelCompleteIndicator;
    
    @Field("dow_in")
    private String dowIn;
    
    @Field("dow_out")
    private String dowOut;
    
    @Field("season_type")
    private String seasonType;
    
    @Field("season_start")
    private String seasonStart;
    
    @Field("season_end")
    private String seasonEnd;
    
    @Field("adv_pur")
    private String advPur;
    
    @Field("min_stay")
    private String minStay;
    
    @Field("max_stay")
    private String maxStay;
    
    @Field("bucket")
    private String bucket;
    
    @Field("zone")
    private String zone;
    
    @Field("rtgno")
    private String rtgno;
    
    @Field("rtgnoTarno")
    private String rtgnoTarno;
    
    @Field("ruleno")
    private String ruleno;
    
    @Field("base_ruleno")
    private String baseRuleno;
    
	@Field("effDt")
    private String effDt;

    @Field("comment")
    private String comment;
    
    @Field("ratesheet_comment")
    private String ratesheetComment;
    
    @Field("deal_code")
    private String dealCode;
    
    @Field("ticket_code")
    private String ticketCode;
    
    @Field("ticket_designator")
    private String ticketDesignator;
    
    @Field("new_booking_code")
    private String newBookingCode;
    
    @Field("work_package")
    private ObjectId workPackage;
    
    @Field("sheet_number")
    private int sheetNumber;
    
	@Field("version")
    private String version;

    private boolean added = false;
      
    public int getSheetNumber() {
		return sheetNumber;
	}
    
    public void setSheetNumber(int sheetNumber) {
		this.sheetNumber = sheetNumber;
	}
    
    public String getRtgnoTarno() {
		return rtgnoTarno;
	}

	public void setRtgnoTarno(String rtgnoTarno) {
		this.rtgnoTarno = rtgnoTarno;
	}

	public String getBaseRuleno() {
		return baseRuleno;
	}

	public void setBaseRuleno(String baseRuleno) {
		this.baseRuleno = baseRuleno;
	}

    public String getNewBookingCode() {
		return newBookingCode;
	}

	public void setNewBookingCode(String newBookingCode) {
		this.newBookingCode = newBookingCode;
	}

	public String getNewTypeOfJourney() {
		return newTypeOfJourney;
	}

	public void setNewTypeOfJourney(String newTypeOfJourney) {
		this.newTypeOfJourney = newTypeOfJourney;
	}

	public String getTicketDesignator() {
		return ticketDesignator;
	}

	public void setTicketDesignator(String ticketDesignator) {
		this.ticketDesignator = ticketDesignator;
	}

	public String getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	public String getRuleno() {
		return ruleno;
	}

	public void setRuleno(String ruleno) {
		this.ruleno = ruleno;
	}

	public String getTravelComplete() {
		return travelComplete;
	}

	public void setTravelComplete(String travelComplete) {
		this.travelComplete = travelComplete;
	}

	public String getTravelCompleteIndicator() {
		return travelCompleteIndicator;
	}

	public void setTravelCompleteIndicator(String travelCompleteIndicator) {
		this.travelCompleteIndicator = travelCompleteIndicator;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getRtgno() {
		return rtgno;
	}

	public void setRtgno(String rtgno) {
		this.rtgno = rtgno;
	}

	public String getEffDt() {
		return effDt;
	}

	public void setEffDt(String effDt) {
		this.effDt = effDt;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getMinStay() {
		return minStay;
	}

	public void setMinStay(String minStay) {
		this.minStay = minStay;
	}

	public String getMaxStay() {
		return maxStay;
	}

	public void setMaxStay(String maxStay) {
		this.maxStay = maxStay;
	}

	public String getAdvPur() {
		return advPur;
	}

	public void setAdvPur(String advPur) {
		this.advPur = advPur;
	}

	public String getSeasonStart() {
		return seasonStart;
	}

	public void setSeasonStart(String seasonStart) {
		this.seasonStart = seasonStart;
	}

	public String getSeasonEnd() {
		return seasonEnd;
	}

	public void setSeasonEnd(String seasonEnd) {
		this.seasonEnd = seasonEnd;
	}

	public String getTfc() {
		return tfc;
	}

	public void setTfc(String tfc) {
		this.tfc = tfc;
	}

	public String getYqyr() {
		return yqyr;
	}

	public void setYqyr(String yqyr) {
		this.yqyr = yqyr;
	}

	public String getCat12() {
		return cat12;
	}

	public void setCat12(String cat12) {
		this.cat12 = cat12;
	}

	public String getAif() {
		return aif;
	}

	public void setAif(String aif) {
		this.aif = aif;
	}

	public String getDowIn() {
		return dowIn;
	}

	public void setDowIn(String dowIn) {
		this.dowIn = dowIn;
	}

	public String getDowOut() {
		return dowOut;
	}

	public void setDowOut(String dowOut) {
		this.dowOut = dowOut;
	}

	public String getSaleStart() {
		return saleStart;
	}

	public void setSaleStart(String saleStart) {
		this.saleStart = saleStart;
	}

	public String getSaleEnd() {
		return saleEnd;
	}

	public void setSaleEnd(String saleEnd) {
		this.saleEnd = saleEnd;
	}

	public String getTravelStart() {
		return travelStart;
	}

	public void setTravelStart(String travelStart) {
		this.travelStart = travelStart;
	}

	public String getTravelEnd() {
		return travelEnd;
	}

	public void setTravelEnd(String travelEnd) {
		this.travelEnd = travelEnd;
	}

	public boolean isAdded() {
		return added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}

	public String getGlobal() {
		return global;
	}

	public void setGlobal(String global) {
		this.global = global;
	}

	public String getFareBasis() {
		return fareBasis;
	}

	public void setFareBasis(String fareBasis) {
		this.fareBasis = fareBasis;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<Tax> getTaxes() {
		return taxes;
	}

	public void setTaxes(List<Tax> taxes) {
		this.taxes = taxes;
	}

	public String getBookingClass() {
		return bookingClass;
	}

	public void setBookingClass(String bookingClass) {
		this.bookingClass = bookingClass;
	}

	public String getTypeOfJourney() {
		return typeOfJourney;
	}

	public void setTypeOfJourney(String typeOfJourney) {
		this.typeOfJourney = typeOfJourney;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public String getCabinClass() {
		return cabinClass;
	}

	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}
	
	
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public ObjectId getWorkPackage() {
		return workPackage;
	}

	public void setWorkPackage(ObjectId workPackage) {
		this.workPackage = workPackage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return version;
	}
	public String getEffDate() {
		return effDate;
	}

	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}

	public String getRatesheetComment() {
		return ratesheetComment;
	}

	public void setRatesheetComment(String ratesheetComment) {
		this.ratesheetComment = ratesheetComment;
	}

	public String getDealCode() {
		return dealCode;
	}

	public void setDealCode(String dealCode) {
		this.dealCode = dealCode;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkPackageFare fare = (WorkPackageFare) o;
        if (fare.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fare.getId());
    }

	@Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "WorkPackageFare [id=" + id + ", status=" + status + ", action=" + action + ", tourCode=" + tourCode
				+ ", recommendedAmount=" + recommendedAmount + ", tarno=" + tarno + ", tarcd=" + tarcd + ", cabin="
				+ cabin + ", fareType=" + fareType + ", footnote1=" + footnote1 + ", footnote2=" + footnote2 + ", ssn="
				+ ssn + ", effDate=" + effDate + ", itinerary=" + itinerary + ", overrideIndicator=" + overrideIndicator
				+ ", loc1=" + loc1 + ", loc1Type=" + loc1Type + ", loc2=" + loc2 + ", loc2Type=" + loc2Type
				+ ", calcType=" + calcType + ", percentBaseFare=" + percentBaseFare + ", tarif=" + tarif + ", carrier="
				+ carrier + ", bookingClass=" + bookingClass + ", origin=" + origin + ", direction=" + direction
				+ ", destination=" + destination + ", fareBasis=" + fareBasis + ", newFareBasis=" + newFareBasis
				+ ", typeOfJourney=" + typeOfJourney + ", newTypeOfJourney=" + newTypeOfJourney + ", passengerType="
				+ passengerType + ", currency=" + currency + ", amount=" + amount + ", taxes=" + taxes + ", yqyr="
				+ yqyr + ", cat12=" + cat12 + ", totalTax=" + totalTax + ", tfc=" + tfc + ", aif=" + aif + ", global="
				+ global + ", cabinClass=" + cabinClass + ", travelStart=" + travelStart + ", travelEnd=" + travelEnd
				+ ", saleStart=" + saleStart + ", saleEnd=" + saleEnd + ", travelComplete=" + travelComplete
				+ ", travelCompleteIndicator=" + travelCompleteIndicator + ", dowIn=" + dowIn + ", dowOut=" + dowOut
				+ ", seasonType=" + seasonType + ", seasonStart=" + seasonStart + ", seasonEnd=" + seasonEnd
				+ ", advPur=" + advPur + ", minStay=" + minStay + ", maxStay=" + maxStay + ", bucket=" + bucket
				+ ", zone=" + zone + ", rtgno=" + rtgno + ", ruleno=" + ruleno + ", baseRuleno=" + baseRuleno
				+ ", effDt=" + effDt + ", comment=" + comment + ", ratesheetComment=" + ratesheetComment + ", dealCode="
				+ dealCode + ", ticketCode=" + ticketCode + ", ticketDesignator=" + ticketDesignator
				+ ", newBookingCode=" + newBookingCode + ", workPackage=" + workPackage + ", added=" + added + "]";
	}

	
	
}
