package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A YQYRQuery.
 */
@Document(collection = "yqyrquery")
public class YQYRQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("description")
    private String description;

    @Field("marketing_carrier")
    private String marketingCarrier;

    @Field("sequence")
    private Long sequence;

    @Field("fee_type")
    private String feeType;

    @Field("sub_code")
    private String subCode;

    @Field("efective_date")
    private ZonedDateTime efectiveDate;

    @Field("discontinue")
    private ZonedDateTime discontinue;

    @Field("amount")
    private String amount;

    @Field("currency")
    private String currency;

    @Field("applies_per")
    private String appliesPer;

    @Field("tax_included")
    private String taxIncluded;

    @Field("percent")
    private Float percent;

    @Field("return_to_journey_origin")
    private String returnToJourneyOrigin;

    @Field("first_location_is_journey_origin")
    private String firstLocationIsJourneyOrigin;

    @Field("journey_first_loc")
    private String journeyFirstLoc;

    @Field("airport")
    private String airport;

    @Field("journey_second_loc")
    private String journeySecondLoc;

    @Field("second_loc_airport")
    private String secondLocAirport;

    @Field("journey_via_loc")
    private String journeyViaLoc;

    @Field("journey_via_loc_airport")
    private String journeyViaLocAirport;

    @Field("travel_wholly_within_loc")
    private String travelWhollyWithinLoc;

    @Field("travel_wholly_loc_airport")
    private String travelWhollyLocAirport;

    @Field("location_apply_per")
    private String locationApplyPer;

    @Field("from_or_to")
    private String fromOrTo;

    @Field("soctor_or_portion_of_travel_first_loc")
    private String soctorOrPortionOfTravelFirstLoc;

    @Field("sector_or_portion_travel_loc_airport")
    private String sectorOrPortionTravelLocAirport;

    @Field("sector_or_portion_of_travel_second_loc")
    private String sectorOrPortionOfTravelSecondLoc;

    @Field("sector_or_portion_travel_loc_second_airport")
    private String sectorOrPortionTravelLocSecondAirport;

    @Field("sector_or_portion_of_travel_via_loc")
    private String sectorOrPortionOfTravelViaLoc;

    @Field("sector_or_portion_of_travel_via_loc_airport")
    private String sectorOrPortionOfTravelViaLocAirport;

    @Field("international_or_domestic")
    private String internationalOrDomestic;

    @Field("via_points_must_be")
    private String viaPointsMustBe;

    @Field("stopover_time")
    private String stopoverTime;

    @Field("stopover_unit")
    private String stopoverUnit;

    @Field("connect_exempt")
    private String connectExempt;

    @Field("crx_flight_table")
    private String crxFlightTable;

    @Field("fare_basis")
    private String fareBasis;

    @Field("fare_class_table")
    private String fareClassTable;

    @Field("tkt_designator_table")
    private String tktDesignatorTable;

    @Field("rdb_1")
    private String rdb1;

    @Field("rdb_2")
    private String rdb2;

    @Field("rdb_3")
    private String rdb3;

    @Field("rdb_table")
    private String rdbTable;

    @Field("cabin")
    private String cabin;

    @Field("first_ticket")
    private String firstTicket;

    @Field("last_ticket")
    private String lastTicket;

    @Field("ptc")
    private String ptc;

    @Field("crx_table")
    private String crxTable;

    @Field("poc_loc")
    private String pocLoc;

    @Field("poc_loc_airport")
    private String pocLocAirport;

    @Field("agency_code")
    private String agencyCode;

    @Field("agency_number")
    private String agencyNumber;

    @Field("ticketing_location")
    private String ticketingLocation;

    @Field("ticketing_loc_airport")
    private String ticketingLocAirport;

    @Field("equipment")
    private String equipment;

    @Field("note_text_table")
    private String noteTextTable;

    @Field("batch_id")
    private String batchID;

    @Field("status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public YQYRQuery description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarketingCarrier() {
        return marketingCarrier;
    }

    public YQYRQuery marketingCarrier(String marketingCarrier) {
        this.marketingCarrier = marketingCarrier;
        return this;
    }

    public void setMarketingCarrier(String marketingCarrier) {
        this.marketingCarrier = marketingCarrier;
    }

    public Long getSequence() {
        return sequence;
    }

    public YQYRQuery sequence(Long sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getFeeType() {
        return feeType;
    }

    public YQYRQuery feeType(String feeType) {
        this.feeType = feeType;
        return this;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getSubCode() {
        return subCode;
    }

    public YQYRQuery subCode(String subCode) {
        this.subCode = subCode;
        return this;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public ZonedDateTime getEfectiveDate() {
        return efectiveDate;
    }

    public YQYRQuery efectiveDate(ZonedDateTime efectiveDate) {
        this.efectiveDate = efectiveDate;
        return this;
    }

    public void setEfectiveDate(ZonedDateTime efectiveDate) {
        this.efectiveDate = efectiveDate;
    }

    public ZonedDateTime getDiscontinue() {
        return discontinue;
    }

    public YQYRQuery discontinue(ZonedDateTime discontinue) {
        this.discontinue = discontinue;
        return this;
    }

    public void setDiscontinue(ZonedDateTime discontinue) {
        this.discontinue = discontinue;
    }

    public String getAmount() {
        return amount;
    }

    public YQYRQuery amount(String amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public YQYRQuery currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAppliesPer() {
        return appliesPer;
    }

    public YQYRQuery appliesPer(String appliesPer) {
        this.appliesPer = appliesPer;
        return this;
    }

    public void setAppliesPer(String appliesPer) {
        this.appliesPer = appliesPer;
    }

    public String getTaxIncluded() {
        return taxIncluded;
    }

    public YQYRQuery taxIncluded(String taxIncluded) {
        this.taxIncluded = taxIncluded;
        return this;
    }

    public void setTaxIncluded(String taxIncluded) {
        this.taxIncluded = taxIncluded;
    }

    public Float getPercent() {
        return percent;
    }

    public YQYRQuery percent(Float percent) {
        this.percent = percent;
        return this;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    public String getReturnToJourneyOrigin() {
        return returnToJourneyOrigin;
    }

    public YQYRQuery returnToJourneyOrigin(String returnToJourneyOrigin) {
        this.returnToJourneyOrigin = returnToJourneyOrigin;
        return this;
    }

    public void setReturnToJourneyOrigin(String returnToJourneyOrigin) {
        this.returnToJourneyOrigin = returnToJourneyOrigin;
    }

    public String getFirstLocationIsJourneyOrigin() {
        return firstLocationIsJourneyOrigin;
    }

    public YQYRQuery firstLocationIsJourneyOrigin(String firstLocationIsJourneyOrigin) {
        this.firstLocationIsJourneyOrigin = firstLocationIsJourneyOrigin;
        return this;
    }

    public void setFirstLocationIsJourneyOrigin(String firstLocationIsJourneyOrigin) {
        this.firstLocationIsJourneyOrigin = firstLocationIsJourneyOrigin;
    }

    public String getJourneyFirstLoc() {
        return journeyFirstLoc;
    }

    public YQYRQuery journeyFirstLoc(String journeyFirstLoc) {
        this.journeyFirstLoc = journeyFirstLoc;
        return this;
    }

    public void setJourneyFirstLoc(String journeyFirstLoc) {
        this.journeyFirstLoc = journeyFirstLoc;
    }

    public String getAirport() {
        return airport;
    }

    public YQYRQuery airport(String airport) {
        this.airport = airport;
        return this;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getJourneySecondLoc() {
        return journeySecondLoc;
    }

    public YQYRQuery journeySecondLoc(String journeySecondLoc) {
        this.journeySecondLoc = journeySecondLoc;
        return this;
    }

    public void setJourneySecondLoc(String journeySecondLoc) {
        this.journeySecondLoc = journeySecondLoc;
    }

    public String getSecondLocAirport() {
        return secondLocAirport;
    }

    public YQYRQuery secondLocAirport(String secondLocAirport) {
        this.secondLocAirport = secondLocAirport;
        return this;
    }

    public void setSecondLocAirport(String secondLocAirport) {
        this.secondLocAirport = secondLocAirport;
    }

    public String getJourneyViaLoc() {
        return journeyViaLoc;
    }

    public YQYRQuery journeyViaLoc(String journeyViaLoc) {
        this.journeyViaLoc = journeyViaLoc;
        return this;
    }

    public void setJourneyViaLoc(String journeyViaLoc) {
        this.journeyViaLoc = journeyViaLoc;
    }

    public String getJourneyViaLocAirport() {
        return journeyViaLocAirport;
    }

    public YQYRQuery journeyViaLocAirport(String journeyViaLocAirport) {
        this.journeyViaLocAirport = journeyViaLocAirport;
        return this;
    }

    public void setJourneyViaLocAirport(String journeyViaLocAirport) {
        this.journeyViaLocAirport = journeyViaLocAirport;
    }

    public String getTravelWhollyWithinLoc() {
        return travelWhollyWithinLoc;
    }

    public YQYRQuery travelWhollyWithinLoc(String travelWhollyWithinLoc) {
        this.travelWhollyWithinLoc = travelWhollyWithinLoc;
        return this;
    }

    public void setTravelWhollyWithinLoc(String travelWhollyWithinLoc) {
        this.travelWhollyWithinLoc = travelWhollyWithinLoc;
    }

    public String getTravelWhollyLocAirport() {
        return travelWhollyLocAirport;
    }

    public YQYRQuery travelWhollyLocAirport(String travelWhollyLocAirport) {
        this.travelWhollyLocAirport = travelWhollyLocAirport;
        return this;
    }

    public void setTravelWhollyLocAirport(String travelWhollyLocAirport) {
        this.travelWhollyLocAirport = travelWhollyLocAirport;
    }

    public String getLocationApplyPer() {
        return locationApplyPer;
    }

    public YQYRQuery locationApplyPer(String locationApplyPer) {
        this.locationApplyPer = locationApplyPer;
        return this;
    }

    public void setLocationApplyPer(String locationApplyPer) {
        this.locationApplyPer = locationApplyPer;
    }

    public String getFromOrTo() {
        return fromOrTo;
    }

    public YQYRQuery fromOrTo(String fromOrTo) {
        this.fromOrTo = fromOrTo;
        return this;
    }

    public void setFromOrTo(String fromOrTo) {
        this.fromOrTo = fromOrTo;
    }

    public String getSoctorOrPortionOfTravelFirstLoc() {
        return soctorOrPortionOfTravelFirstLoc;
    }

    public YQYRQuery soctorOrPortionOfTravelFirstLoc(String soctorOrPortionOfTravelFirstLoc) {
        this.soctorOrPortionOfTravelFirstLoc = soctorOrPortionOfTravelFirstLoc;
        return this;
    }

    public void setSoctorOrPortionOfTravelFirstLoc(String soctorOrPortionOfTravelFirstLoc) {
        this.soctorOrPortionOfTravelFirstLoc = soctorOrPortionOfTravelFirstLoc;
    }

    public String getSectorOrPortionTravelLocAirport() {
        return sectorOrPortionTravelLocAirport;
    }

    public YQYRQuery sectorOrPortionTravelLocAirport(String sectorOrPortionTravelLocAirport) {
        this.sectorOrPortionTravelLocAirport = sectorOrPortionTravelLocAirport;
        return this;
    }

    public void setSectorOrPortionTravelLocAirport(String sectorOrPortionTravelLocAirport) {
        this.sectorOrPortionTravelLocAirport = sectorOrPortionTravelLocAirport;
    }

    public String getSectorOrPortionOfTravelSecondLoc() {
        return sectorOrPortionOfTravelSecondLoc;
    }

    public YQYRQuery sectorOrPortionOfTravelSecondLoc(String sectorOrPortionOfTravelSecondLoc) {
        this.sectorOrPortionOfTravelSecondLoc = sectorOrPortionOfTravelSecondLoc;
        return this;
    }

    public void setSectorOrPortionOfTravelSecondLoc(String sectorOrPortionOfTravelSecondLoc) {
        this.sectorOrPortionOfTravelSecondLoc = sectorOrPortionOfTravelSecondLoc;
    }

    public String getSectorOrPortionTravelLocSecondAirport() {
        return sectorOrPortionTravelLocSecondAirport;
    }

    public YQYRQuery sectorOrPortionTravelLocSecondAirport(String sectorOrPortionTravelLocSecondAirport) {
        this.sectorOrPortionTravelLocSecondAirport = sectorOrPortionTravelLocSecondAirport;
        return this;
    }

    public void setSectorOrPortionTravelLocSecondAirport(String sectorOrPortionTravelLocSecondAirport) {
        this.sectorOrPortionTravelLocSecondAirport = sectorOrPortionTravelLocSecondAirport;
    }

    public String getSectorOrPortionOfTravelViaLoc() {
        return sectorOrPortionOfTravelViaLoc;
    }

    public YQYRQuery sectorOrPortionOfTravelViaLoc(String sectorOrPortionOfTravelViaLoc) {
        this.sectorOrPortionOfTravelViaLoc = sectorOrPortionOfTravelViaLoc;
        return this;
    }

    public void setSectorOrPortionOfTravelViaLoc(String sectorOrPortionOfTravelViaLoc) {
        this.sectorOrPortionOfTravelViaLoc = sectorOrPortionOfTravelViaLoc;
    }

    public String getSectorOrPortionOfTravelViaLocAirport() {
        return sectorOrPortionOfTravelViaLocAirport;
    }

    public YQYRQuery sectorOrPortionOfTravelViaLocAirport(String sectorOrPortionOfTravelViaLocAirport) {
        this.sectorOrPortionOfTravelViaLocAirport = sectorOrPortionOfTravelViaLocAirport;
        return this;
    }

    public void setSectorOrPortionOfTravelViaLocAirport(String sectorOrPortionOfTravelViaLocAirport) {
        this.sectorOrPortionOfTravelViaLocAirport = sectorOrPortionOfTravelViaLocAirport;
    }

    public String getInternationalOrDomestic() {
        return internationalOrDomestic;
    }

    public YQYRQuery internationalOrDomestic(String internationalOrDomestic) {
        this.internationalOrDomestic = internationalOrDomestic;
        return this;
    }

    public void setInternationalOrDomestic(String internationalOrDomestic) {
        this.internationalOrDomestic = internationalOrDomestic;
    }

    public String getViaPointsMustBe() {
        return viaPointsMustBe;
    }

    public YQYRQuery viaPointsMustBe(String viaPointsMustBe) {
        this.viaPointsMustBe = viaPointsMustBe;
        return this;
    }

    public void setViaPointsMustBe(String viaPointsMustBe) {
        this.viaPointsMustBe = viaPointsMustBe;
    }

    public String getStopoverTime() {
        return stopoverTime;
    }

    public YQYRQuery stopoverTime(String stopoverTime) {
        this.stopoverTime = stopoverTime;
        return this;
    }

    public void setStopoverTime(String stopoverTime) {
        this.stopoverTime = stopoverTime;
    }

    public String getStopoverUnit() {
        return stopoverUnit;
    }

    public YQYRQuery stopoverUnit(String stopoverUnit) {
        this.stopoverUnit = stopoverUnit;
        return this;
    }

    public void setStopoverUnit(String stopoverUnit) {
        this.stopoverUnit = stopoverUnit;
    }

    public String getConnectExempt() {
        return connectExempt;
    }

    public YQYRQuery connectExempt(String connectExempt) {
        this.connectExempt = connectExempt;
        return this;
    }

    public void setConnectExempt(String connectExempt) {
        this.connectExempt = connectExempt;
    }

    public String getCrxFlightTable() {
        return crxFlightTable;
    }

    public YQYRQuery crxFlightTable(String crxFlightTable) {
        this.crxFlightTable = crxFlightTable;
        return this;
    }

    public void setCrxFlightTable(String crxFlightTable) {
        this.crxFlightTable = crxFlightTable;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public YQYRQuery fareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
        return this;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    public String getFareClassTable() {
        return fareClassTable;
    }

    public YQYRQuery fareClassTable(String fareClassTable) {
        this.fareClassTable = fareClassTable;
        return this;
    }

    public void setFareClassTable(String fareClassTable) {
        this.fareClassTable = fareClassTable;
    }

    public String getTktDesignatorTable() {
        return tktDesignatorTable;
    }

    public YQYRQuery tktDesignatorTable(String tktDesignatorTable) {
        this.tktDesignatorTable = tktDesignatorTable;
        return this;
    }

    public void setTktDesignatorTable(String tktDesignatorTable) {
        this.tktDesignatorTable = tktDesignatorTable;
    }

    public String getRdb1() {
        return rdb1;
    }

    public YQYRQuery rdb1(String rdb1) {
        this.rdb1 = rdb1;
        return this;
    }

    public void setRdb1(String rdb1) {
        this.rdb1 = rdb1;
    }

    public String getRdb2() {
        return rdb2;
    }

    public YQYRQuery rdb2(String rdb2) {
        this.rdb2 = rdb2;
        return this;
    }

    public void setRdb2(String rdb2) {
        this.rdb2 = rdb2;
    }

    public String getRdb3() {
        return rdb3;
    }

    public YQYRQuery rdb3(String rdb3) {
        this.rdb3 = rdb3;
        return this;
    }

    public void setRdb3(String rdb3) {
        this.rdb3 = rdb3;
    }

    public String getRdbTable() {
        return rdbTable;
    }

    public YQYRQuery rdbTable(String rdbTable) {
        this.rdbTable = rdbTable;
        return this;
    }

    public void setRdbTable(String rdbTable) {
        this.rdbTable = rdbTable;
    }

    public String getCabin() {
        return cabin;
    }

    public YQYRQuery cabin(String cabin) {
        this.cabin = cabin;
        return this;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getFirstTicket() {
        return firstTicket;
    }

    public YQYRQuery firstTicket(String firstTicket) {
        this.firstTicket = firstTicket;
        return this;
    }

    public void setFirstTicket(String firstTicket) {
        this.firstTicket = firstTicket;
    }

    public String getLastTicket() {
        return lastTicket;
    }

    public YQYRQuery lastTicket(String lastTicket) {
        this.lastTicket = lastTicket;
        return this;
    }

    public void setLastTicket(String lastTicket) {
        this.lastTicket = lastTicket;
    }

    public String getPtc() {
        return ptc;
    }

    public YQYRQuery ptc(String ptc) {
        this.ptc = ptc;
        return this;
    }

    public void setPtc(String ptc) {
        this.ptc = ptc;
    }

    public String getCrxTable() {
        return crxTable;
    }

    public YQYRQuery crxTable(String crxTable) {
        this.crxTable = crxTable;
        return this;
    }

    public void setCrxTable(String crxTable) {
        this.crxTable = crxTable;
    }

    public String getPocLoc() {
        return pocLoc;
    }

    public YQYRQuery pocLoc(String pocLoc) {
        this.pocLoc = pocLoc;
        return this;
    }

    public void setPocLoc(String pocLoc) {
        this.pocLoc = pocLoc;
    }

    public String getPocLocAirport() {
        return pocLocAirport;
    }

    public YQYRQuery pocLocAirport(String pocLocAirport) {
        this.pocLocAirport = pocLocAirport;
        return this;
    }

    public void setPocLocAirport(String pocLocAirport) {
        this.pocLocAirport = pocLocAirport;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public YQYRQuery agencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
        return this;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAgencyNumber() {
        return agencyNumber;
    }

    public YQYRQuery agencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
        return this;
    }

    public void setAgencyNumber(String agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public String getTicketingLocation() {
        return ticketingLocation;
    }

    public YQYRQuery ticketingLocation(String ticketingLocation) {
        this.ticketingLocation = ticketingLocation;
        return this;
    }

    public void setTicketingLocation(String ticketingLocation) {
        this.ticketingLocation = ticketingLocation;
    }

    public String getTicketingLocAirport() {
        return ticketingLocAirport;
    }

    public YQYRQuery ticketingLocAirport(String ticketingLocAirport) {
        this.ticketingLocAirport = ticketingLocAirport;
        return this;
    }

    public void setTicketingLocAirport(String ticketingLocAirport) {
        this.ticketingLocAirport = ticketingLocAirport;
    }

    public String getEquipment() {
        return equipment;
    }

    public YQYRQuery equipment(String equipment) {
        this.equipment = equipment;
        return this;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getNoteTextTable() {
        return noteTextTable;
    }

    public YQYRQuery noteTextTable(String noteTextTable) {
        this.noteTextTable = noteTextTable;
        return this;
    }

    public void setNoteTextTable(String noteTextTable) {
        this.noteTextTable = noteTextTable;
    }

    public String getBatchID() {
        return batchID;
    }

    public YQYRQuery batchID(String batchID) {
        this.batchID = batchID;
        return this;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getStatus() {
        return status;
    }

    public YQYRQuery status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
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
        YQYRQuery yQYRQuery = (YQYRQuery) o;
        if (yQYRQuery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), yQYRQuery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "YQYRQuery{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", marketingCarrier='" + getMarketingCarrier() + "'" +
            ", sequence=" + getSequence() +
            ", feeType='" + getFeeType() + "'" +
            ", subCode='" + getSubCode() + "'" +
            ", efectiveDate='" + getEfectiveDate() + "'" +
            ", discontinue='" + getDiscontinue() + "'" +
            ", amount='" + getAmount() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", appliesPer='" + getAppliesPer() + "'" +
            ", taxIncluded='" + getTaxIncluded() + "'" +
            ", percent=" + getPercent() +
            ", returnToJourneyOrigin='" + getReturnToJourneyOrigin() + "'" +
            ", firstLocationIsJourneyOrigin='" + getFirstLocationIsJourneyOrigin() + "'" +
            ", journeyFirstLoc='" + getJourneyFirstLoc() + "'" +
            ", airport='" + getAirport() + "'" +
            ", journeySecondLoc='" + getJourneySecondLoc() + "'" +
            ", secondLocAirport='" + getSecondLocAirport() + "'" +
            ", journeyViaLoc='" + getJourneyViaLoc() + "'" +
            ", journeyViaLocAirport='" + getJourneyViaLocAirport() + "'" +
            ", travelWhollyWithinLoc='" + getTravelWhollyWithinLoc() + "'" +
            ", travelWhollyLocAirport='" + getTravelWhollyLocAirport() + "'" +
            ", locationApplyPer='" + getLocationApplyPer() + "'" +
            ", fromOrTo='" + getFromOrTo() + "'" +
            ", soctorOrPortionOfTravelFirstLoc='" + getSoctorOrPortionOfTravelFirstLoc() + "'" +
            ", sectorOrPortionTravelLocAirport='" + getSectorOrPortionTravelLocAirport() + "'" +
            ", sectorOrPortionOfTravelSecondLoc='" + getSectorOrPortionOfTravelSecondLoc() + "'" +
            ", sectorOrPortionTravelLocSecondAirport='" + getSectorOrPortionTravelLocSecondAirport() + "'" +
            ", sectorOrPortionOfTravelViaLoc='" + getSectorOrPortionOfTravelViaLoc() + "'" +
            ", sectorOrPortionOfTravelViaLocAirport='" + getSectorOrPortionOfTravelViaLocAirport() + "'" +
            ", internationalOrDomestic='" + getInternationalOrDomestic() + "'" +
            ", viaPointsMustBe='" + getViaPointsMustBe() + "'" +
            ", stopoverTime='" + getStopoverTime() + "'" +
            ", stopoverUnit='" + getStopoverUnit() + "'" +
            ", connectExempt='" + getConnectExempt() + "'" +
            ", crxFlightTable='" + getCrxFlightTable() + "'" +
            ", fareBasis='" + getFareBasis() + "'" +
            ", fareClassTable='" + getFareClassTable() + "'" +
            ", tktDesignatorTable='" + getTktDesignatorTable() + "'" +
            ", rdb1='" + getRdb1() + "'" +
            ", rdb2='" + getRdb2() + "'" +
            ", rdb3='" + getRdb3() + "'" +
            ", rdbTable='" + getRdbTable() + "'" +
            ", cabin='" + getCabin() + "'" +
            ", firstTicket='" + getFirstTicket() + "'" +
            ", lastTicket='" + getLastTicket() + "'" +
            ", ptc='" + getPtc() + "'" +
            ", crxTable='" + getCrxTable() + "'" +
            ", pocLoc='" + getPocLoc() + "'" +
            ", pocLocAirport='" + getPocLocAirport() + "'" +
            ", agencyCode='" + getAgencyCode() + "'" +
            ", agencyNumber='" + getAgencyNumber() + "'" +
            ", ticketingLocation='" + getTicketingLocation() + "'" +
            ", ticketingLocAirport='" + getTicketingLocAirport() + "'" +
            ", equipment='" + getEquipment() + "'" +
            ", noteTextTable='" + getNoteTextTable() + "'" +
            ", batchID='" + getBatchID() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
