package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "atpco_record_3_cat_004")
public class AtpcoRecord3Cat04 {

	@Id
    private String id;
	
	@Field("record_batch")
    private long recordBatch;
	
	@Field("record_sequence")
    private int recordSequence;
	
	@Field("record_type")
    private String recordType;
	
	@Field("action")
    private String action;
	
	@Field("cat_no")
    private String catNo;
	
	@Field("tbl_no")
    private String tableNo;
	
	@Field("flight_info_appl")
    private String flightNoApplicationIndicator;
	
	@Field("flt_no_1")
    private String flightNo1;
	
	@Field("cxr_1")
    private String carrierCode1;
	
	@Field("cxr_flt_tbl_1_986")
    private String carrierFlight1TableNo986;
	
	@Field("rel_1")
    private String relationshipIndicator1And2;
	
	@Field("flt_no_2")
    private String flightNo2;
	
	@Field("cxr_2")
    private String carrierCode2;
	
	@Field("cxr_flt_tbl_2_986")
    private String carrierFlight2TableNo986;
	
	@Field("rel_2")
    private String relationshipIndicator2And3;
	
	@Field("flt_no_3")
    private String flightNo3;
	
	@Field("cxr_3")
    private String carrierCode3;
	
	@Field("day_of_week")
    private String dayOfWeek;
	
	@Field("travel_io")
    private String travelInboundOutboundIndicator;
	
	@Field("travel_appl")
    private String travelApplicationIndicator;
	
	@Field("travel_loc")
    private String travelLocationIndicator;
	
	@Field("travel_bet_via_geo_tbl_995")
    private String travelBetweenOrViaGeoTableNo995;
	
	@Field("travel_and_via_geo_tbl_995")
    private String travelAndOrViaGeoTableNo995;
	
	@Field("travel_via_indicator")
    private String travelViaIndicator;
	
	@Field("travel_via_geo_tbl_995")
    private String travelViaGeoTableNo995;
	
	@Field("travel_hidden")
    private String travelGeoPointsTag;
	
	@Field("flight_app_non")
    private String nonstopFlightsTag;
	
	@Field("flight_app_dir")
    private String directFlightsTag;
	
	@Field("flight_app_mul")
    private String multistopFlightsTag;
	
	@Field("flight_app_one")
    private String onestopFlightsTag;
	
	@Field("flight_app_onl")
    private String onlineConnectingFlightsTag;
	
	@Field("flight_app_int")
    private String interlineConnectingFlightsTag;
	
	@Field("flight_app_same")
    private String sameFlightNoTag;
	
	@Field("eqp_app")
    private String equipmentApplication;
	
	@Field("eqp_cd")
    private String equipmentTypeCode;
	
	@Field("date_tbl_994")
    private String overrideDateTableNo994;
	
	@Field("txt_tbl_996")
    private String textTable996;
	
	@Field("unavail")
    private String unavailableDataTag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getRecordBatch() {
		return recordBatch;
	}

	public void setRecordBatch(long recordBatch) {
		this.recordBatch = recordBatch;
	}

	public int getRecordSequence() {
		return recordSequence;
	}

	public void setRecordSequence(int recordSequence) {
		this.recordSequence = recordSequence;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCatNo() {
		return catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public String getFlightNoApplicationIndicator() {
		return flightNoApplicationIndicator;
	}

	public void setFlightNoApplicationIndicator(String flightNoApplicationIndicator) {
		this.flightNoApplicationIndicator = flightNoApplicationIndicator;
	}

	public String getFlightNo1() {
		return flightNo1;
	}

	public void setFlightNo1(String flightNo1) {
		this.flightNo1 = flightNo1;
	}

	public String getCarrierCode1() {
		return carrierCode1;
	}

	public void setCarrierCode1(String carrierCode1) {
		this.carrierCode1 = carrierCode1;
	}

	public String getCarrierFlight1TableNo986() {
		return carrierFlight1TableNo986;
	}

	public void setCarrierFlight1TableNo986(String carrierFlight1TableNo986) {
		this.carrierFlight1TableNo986 = carrierFlight1TableNo986;
	}

	public String getRelationshipIndicator1And2() {
		return relationshipIndicator1And2;
	}

	public void setRelationshipIndicator1And2(String relationshipIndicator1And2) {
		this.relationshipIndicator1And2 = relationshipIndicator1And2;
	}

	public String getFlightNo2() {
		return flightNo2;
	}

	public void setFlightNo2(String flightNo2) {
		this.flightNo2 = flightNo2;
	}

	public String getCarrierCode2() {
		return carrierCode2;
	}

	public void setCarrierCode2(String carrierCode2) {
		this.carrierCode2 = carrierCode2;
	}

	public String getCarrierFlight2TableNo986() {
		return carrierFlight2TableNo986;
	}

	public void setCarrierFlight2TableNo986(String carrierFlight2TableNo986) {
		this.carrierFlight2TableNo986 = carrierFlight2TableNo986;
	}

	public String getRelationshipIndicator2And3() {
		return relationshipIndicator2And3;
	}

	public void setRelationshipIndicator2And3(String relationshipIndicator2And3) {
		this.relationshipIndicator2And3 = relationshipIndicator2And3;
	}

	public String getFlightNo3() {
		return flightNo3;
	}

	public void setFlightNo3(String flightNo3) {
		this.flightNo3 = flightNo3;
	}

	public String getCarrierCode3() {
		return carrierCode3;
	}

	public void setCarrierCode3(String carrierCode3) {
		this.carrierCode3 = carrierCode3;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getTravelInboundOutboundIndicator() {
		return travelInboundOutboundIndicator;
	}

	public void setTravelInboundOutboundIndicator(String travelInboundOutboundIndicator) {
		this.travelInboundOutboundIndicator = travelInboundOutboundIndicator;
	}

	public String getTravelApplicationIndicator() {
		return travelApplicationIndicator;
	}

	public void setTravelApplicationIndicator(String travelApplicationIndicator) {
		this.travelApplicationIndicator = travelApplicationIndicator;
	}

	public String getTravelLocationIndicator() {
		return travelLocationIndicator;
	}

	public void setTravelLocationIndicator(String travelLocationIndicator) {
		this.travelLocationIndicator = travelLocationIndicator;
	}

	public String getTravelBetweenOrViaGeoTableNo995() {
		return travelBetweenOrViaGeoTableNo995;
	}

	public void setTravelBetweenOrViaGeoTableNo995(String travelBetweenOrViaGeoTableNo995) {
		this.travelBetweenOrViaGeoTableNo995 = travelBetweenOrViaGeoTableNo995;
	}

	public String getTravelAndOrViaGeoTableNo995() {
		return travelAndOrViaGeoTableNo995;
	}

	public void setTravelAndOrViaGeoTableNo995(String travelAndOrViaGeoTableNo995) {
		this.travelAndOrViaGeoTableNo995 = travelAndOrViaGeoTableNo995;
	}

	public String getTravelViaIndicator() {
		return travelViaIndicator;
	}

	public void setTravelViaIndicator(String travelViaIndicator) {
		this.travelViaIndicator = travelViaIndicator;
	}

	public String getTravelViaGeoTableNo995() {
		return travelViaGeoTableNo995;
	}

	public void setTravelViaGeoTableNo995(String travelViaGeoTableNo995) {
		this.travelViaGeoTableNo995 = travelViaGeoTableNo995;
	}

	public String getTravelGeoPointsTag() {
		return travelGeoPointsTag;
	}

	public void setTravelGeoPointsTag(String travelGeoPointsTag) {
		this.travelGeoPointsTag = travelGeoPointsTag;
	}

	public String getNonstopFlightsTag() {
		return nonstopFlightsTag;
	}

	public void setNonstopFlightsTag(String nonstopFlightsTag) {
		this.nonstopFlightsTag = nonstopFlightsTag;
	}

	public String getDirectFlightsTag() {
		return directFlightsTag;
	}

	public void setDirectFlightsTag(String directFlightsTag) {
		this.directFlightsTag = directFlightsTag;
	}

	public String getMultistopFlightsTag() {
		return multistopFlightsTag;
	}

	public void setMultistopFlightsTag(String multistopFlightsTag) {
		this.multistopFlightsTag = multistopFlightsTag;
	}

	public String getOnestopFlightsTag() {
		return onestopFlightsTag;
	}

	public void setOnestopFlightsTag(String onestopFlightsTag) {
		this.onestopFlightsTag = onestopFlightsTag;
	}

	public String getOnlineConnectingFlightsTag() {
		return onlineConnectingFlightsTag;
	}

	public void setOnlineConnectingFlightsTag(String onlineConnectingFlightsTag) {
		this.onlineConnectingFlightsTag = onlineConnectingFlightsTag;
	}

	public String getInterlineConnectingFlightsTag() {
		return interlineConnectingFlightsTag;
	}

	public void setInterlineConnectingFlightsTag(String interlineConnectingFlightsTag) {
		this.interlineConnectingFlightsTag = interlineConnectingFlightsTag;
	}

	public String getSameFlightNoTag() {
		return sameFlightNoTag;
	}

	public void setSameFlightNoTag(String sameFlightNoTag) {
		this.sameFlightNoTag = sameFlightNoTag;
	}

	public String getEquipmentApplication() {
		return equipmentApplication;
	}

	public void setEquipmentApplication(String equipmentApplication) {
		this.equipmentApplication = equipmentApplication;
	}

	public String getEquipmentTypeCode() {
		return equipmentTypeCode;
	}

	public void setEquipmentTypeCode(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}

	public String getOverrideDateTableNo994() {
		return overrideDateTableNo994;
	}

	public void setOverrideDateTableNo994(String overrideDateTableNo994) {
		this.overrideDateTableNo994 = overrideDateTableNo994;
	}

	public String getTextTable996() {
		return textTable996;
	}

	public void setTextTable996(String textTable996) {
		this.textTable996 = textTable996;
	}

	public String getUnavailableDataTag() {
		return unavailableDataTag;
	}

	public void setUnavailableDataTag(String unavailableDataTag) {
		this.unavailableDataTag = unavailableDataTag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((carrierCode1 == null) ? 0 : carrierCode1.hashCode());
		result = prime * result + ((carrierCode2 == null) ? 0 : carrierCode2.hashCode());
		result = prime * result + ((carrierCode3 == null) ? 0 : carrierCode3.hashCode());
		result = prime * result + ((carrierFlight1TableNo986 == null) ? 0 : carrierFlight1TableNo986.hashCode());
		result = prime * result + ((carrierFlight2TableNo986 == null) ? 0 : carrierFlight2TableNo986.hashCode());
		result = prime * result + ((catNo == null) ? 0 : catNo.hashCode());
		result = prime * result + ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime * result + ((directFlightsTag == null) ? 0 : directFlightsTag.hashCode());
		result = prime * result + ((equipmentApplication == null) ? 0 : equipmentApplication.hashCode());
		result = prime * result + ((equipmentTypeCode == null) ? 0 : equipmentTypeCode.hashCode());
		result = prime * result + ((flightNo1 == null) ? 0 : flightNo1.hashCode());
		result = prime * result + ((flightNo2 == null) ? 0 : flightNo2.hashCode());
		result = prime * result + ((flightNo3 == null) ? 0 : flightNo3.hashCode());
		result = prime * result
				+ ((flightNoApplicationIndicator == null) ? 0 : flightNoApplicationIndicator.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((interlineConnectingFlightsTag == null) ? 0 : interlineConnectingFlightsTag.hashCode());
		result = prime * result + ((multistopFlightsTag == null) ? 0 : multistopFlightsTag.hashCode());
		result = prime * result + ((nonstopFlightsTag == null) ? 0 : nonstopFlightsTag.hashCode());
		result = prime * result + ((onestopFlightsTag == null) ? 0 : onestopFlightsTag.hashCode());
		result = prime * result + ((onlineConnectingFlightsTag == null) ? 0 : onlineConnectingFlightsTag.hashCode());
		result = prime * result + ((overrideDateTableNo994 == null) ? 0 : overrideDateTableNo994.hashCode());
		result = prime * result + (int) (recordBatch ^ (recordBatch >>> 32));
		result = prime * result + recordSequence;
		result = prime * result + ((recordType == null) ? 0 : recordType.hashCode());
		result = prime * result + ((relationshipIndicator1And2 == null) ? 0 : relationshipIndicator1And2.hashCode());
		result = prime * result + ((relationshipIndicator2And3 == null) ? 0 : relationshipIndicator2And3.hashCode());
		result = prime * result + ((sameFlightNoTag == null) ? 0 : sameFlightNoTag.hashCode());
		result = prime * result + ((tableNo == null) ? 0 : tableNo.hashCode());
		result = prime * result + ((textTable996 == null) ? 0 : textTable996.hashCode());
		result = prime * result + ((travelAndOrViaGeoTableNo995 == null) ? 0 : travelAndOrViaGeoTableNo995.hashCode());
		result = prime * result + ((travelApplicationIndicator == null) ? 0 : travelApplicationIndicator.hashCode());
		result = prime * result
				+ ((travelBetweenOrViaGeoTableNo995 == null) ? 0 : travelBetweenOrViaGeoTableNo995.hashCode());
		result = prime * result + ((travelGeoPointsTag == null) ? 0 : travelGeoPointsTag.hashCode());
		result = prime * result
				+ ((travelInboundOutboundIndicator == null) ? 0 : travelInboundOutboundIndicator.hashCode());
		result = prime * result + ((travelLocationIndicator == null) ? 0 : travelLocationIndicator.hashCode());
		result = prime * result + ((travelViaGeoTableNo995 == null) ? 0 : travelViaGeoTableNo995.hashCode());
		result = prime * result + ((travelViaIndicator == null) ? 0 : travelViaIndicator.hashCode());
		result = prime * result + ((unavailableDataTag == null) ? 0 : unavailableDataTag.hashCode());
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
		AtpcoRecord3Cat04 other = (AtpcoRecord3Cat04) obj;
		if (action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!action.equals(other.action)) {
			return false;
		}
		if (carrierCode1 == null) {
			if (other.carrierCode1 != null) {
				return false;
			}
		} else if (!carrierCode1.equals(other.carrierCode1)) {
			return false;
		}
		if (carrierCode2 == null) {
			if (other.carrierCode2 != null) {
				return false;
			}
		} else if (!carrierCode2.equals(other.carrierCode2)) {
			return false;
		}
		if (carrierCode3 == null) {
			if (other.carrierCode3 != null) {
				return false;
			}
		} else if (!carrierCode3.equals(other.carrierCode3)) {
			return false;
		}
		if (carrierFlight1TableNo986 == null) {
			if (other.carrierFlight1TableNo986 != null) {
				return false;
			}
		} else if (!carrierFlight1TableNo986.equals(other.carrierFlight1TableNo986)) {
			return false;
		}
		if (carrierFlight2TableNo986 == null) {
			if (other.carrierFlight2TableNo986 != null) {
				return false;
			}
		} else if (!carrierFlight2TableNo986.equals(other.carrierFlight2TableNo986)) {
			return false;
		}
		if (catNo == null) {
			if (other.catNo != null) {
				return false;
			}
		} else if (!catNo.equals(other.catNo)) {
			return false;
		}
		if (dayOfWeek == null) {
			if (other.dayOfWeek != null) {
				return false;
			}
		} else if (!dayOfWeek.equals(other.dayOfWeek)) {
			return false;
		}
		if (directFlightsTag == null) {
			if (other.directFlightsTag != null) {
				return false;
			}
		} else if (!directFlightsTag.equals(other.directFlightsTag)) {
			return false;
		}
		if (equipmentApplication == null) {
			if (other.equipmentApplication != null) {
				return false;
			}
		} else if (!equipmentApplication.equals(other.equipmentApplication)) {
			return false;
		}
		if (equipmentTypeCode == null) {
			if (other.equipmentTypeCode != null) {
				return false;
			}
		} else if (!equipmentTypeCode.equals(other.equipmentTypeCode)) {
			return false;
		}
		if (flightNo1 == null) {
			if (other.flightNo1 != null) {
				return false;
			}
		} else if (!flightNo1.equals(other.flightNo1)) {
			return false;
		}
		if (flightNo2 == null) {
			if (other.flightNo2 != null) {
				return false;
			}
		} else if (!flightNo2.equals(other.flightNo2)) {
			return false;
		}
		if (flightNo3 == null) {
			if (other.flightNo3 != null) {
				return false;
			}
		} else if (!flightNo3.equals(other.flightNo3)) {
			return false;
		}
		if (flightNoApplicationIndicator == null) {
			if (other.flightNoApplicationIndicator != null) {
				return false;
			}
		} else if (!flightNoApplicationIndicator.equals(other.flightNoApplicationIndicator)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (interlineConnectingFlightsTag == null) {
			if (other.interlineConnectingFlightsTag != null) {
				return false;
			}
		} else if (!interlineConnectingFlightsTag.equals(other.interlineConnectingFlightsTag)) {
			return false;
		}
		if (multistopFlightsTag == null) {
			if (other.multistopFlightsTag != null) {
				return false;
			}
		} else if (!multistopFlightsTag.equals(other.multistopFlightsTag)) {
			return false;
		}
		if (nonstopFlightsTag == null) {
			if (other.nonstopFlightsTag != null) {
				return false;
			}
		} else if (!nonstopFlightsTag.equals(other.nonstopFlightsTag)) {
			return false;
		}
		if (onestopFlightsTag == null) {
			if (other.onestopFlightsTag != null) {
				return false;
			}
		} else if (!onestopFlightsTag.equals(other.onestopFlightsTag)) {
			return false;
		}
		if (onlineConnectingFlightsTag == null) {
			if (other.onlineConnectingFlightsTag != null) {
				return false;
			}
		} else if (!onlineConnectingFlightsTag.equals(other.onlineConnectingFlightsTag)) {
			return false;
		}
		if (overrideDateTableNo994 == null) {
			if (other.overrideDateTableNo994 != null) {
				return false;
			}
		} else if (!overrideDateTableNo994.equals(other.overrideDateTableNo994)) {
			return false;
		}
		if (recordBatch != other.recordBatch) {
			return false;
		}
		if (recordSequence != other.recordSequence) {
			return false;
		}
		if (recordType == null) {
			if (other.recordType != null) {
				return false;
			}
		} else if (!recordType.equals(other.recordType)) {
			return false;
		}
		if (relationshipIndicator1And2 == null) {
			if (other.relationshipIndicator1And2 != null) {
				return false;
			}
		} else if (!relationshipIndicator1And2.equals(other.relationshipIndicator1And2)) {
			return false;
		}
		if (relationshipIndicator2And3 == null) {
			if (other.relationshipIndicator2And3 != null) {
				return false;
			}
		} else if (!relationshipIndicator2And3.equals(other.relationshipIndicator2And3)) {
			return false;
		}
		if (sameFlightNoTag == null) {
			if (other.sameFlightNoTag != null) {
				return false;
			}
		} else if (!sameFlightNoTag.equals(other.sameFlightNoTag)) {
			return false;
		}
		if (tableNo == null) {
			if (other.tableNo != null) {
				return false;
			}
		} else if (!tableNo.equals(other.tableNo)) {
			return false;
		}
		if (textTable996 == null) {
			if (other.textTable996 != null) {
				return false;
			}
		} else if (!textTable996.equals(other.textTable996)) {
			return false;
		}
		if (travelAndOrViaGeoTableNo995 == null) {
			if (other.travelAndOrViaGeoTableNo995 != null) {
				return false;
			}
		} else if (!travelAndOrViaGeoTableNo995.equals(other.travelAndOrViaGeoTableNo995)) {
			return false;
		}
		if (travelApplicationIndicator == null) {
			if (other.travelApplicationIndicator != null) {
				return false;
			}
		} else if (!travelApplicationIndicator.equals(other.travelApplicationIndicator)) {
			return false;
		}
		if (travelBetweenOrViaGeoTableNo995 == null) {
			if (other.travelBetweenOrViaGeoTableNo995 != null) {
				return false;
			}
		} else if (!travelBetweenOrViaGeoTableNo995.equals(other.travelBetweenOrViaGeoTableNo995)) {
			return false;
		}
		if (travelGeoPointsTag == null) {
			if (other.travelGeoPointsTag != null) {
				return false;
			}
		} else if (!travelGeoPointsTag.equals(other.travelGeoPointsTag)) {
			return false;
		}
		if (travelInboundOutboundIndicator == null) {
			if (other.travelInboundOutboundIndicator != null) {
				return false;
			}
		} else if (!travelInboundOutboundIndicator.equals(other.travelInboundOutboundIndicator)) {
			return false;
		}
		if (travelLocationIndicator == null) {
			if (other.travelLocationIndicator != null) {
				return false;
			}
		} else if (!travelLocationIndicator.equals(other.travelLocationIndicator)) {
			return false;
		}
		if (travelViaGeoTableNo995 == null) {
			if (other.travelViaGeoTableNo995 != null) {
				return false;
			}
		} else if (!travelViaGeoTableNo995.equals(other.travelViaGeoTableNo995)) {
			return false;
		}
		if (travelViaIndicator == null) {
			if (other.travelViaIndicator != null) {
				return false;
			}
		} else if (!travelViaIndicator.equals(other.travelViaIndicator)) {
			return false;
		}
		if (unavailableDataTag == null) {
			if (other.unavailableDataTag != null) {
				return false;
			}
		} else if (!unavailableDataTag.equals(other.unavailableDataTag)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat04 [id=" + id + ", recordBatch=" + recordBatch + ", recordSequence=" + recordSequence
				+ ", recordType=" + recordType + ", action=" + action + ", catNo=" + catNo + ", tableNo=" + tableNo
				+ ", flightNoApplicationIndicator=" + flightNoApplicationIndicator + ", flightNo1=" + flightNo1
				+ ", carrierCode1=" + carrierCode1 + ", carrierFlight1TableNo986=" + carrierFlight1TableNo986
				+ ", relationshipIndicator1And2=" + relationshipIndicator1And2 + ", flightNo2=" + flightNo2
				+ ", carrierCode2=" + carrierCode2 + ", carrierFlight2TableNo986=" + carrierFlight2TableNo986
				+ ", relationshipIndicator2And3=" + relationshipIndicator2And3 + ", flightNo3=" + flightNo3
				+ ", carrierCode3=" + carrierCode3 + ", dayOfWeek=" + dayOfWeek + ", travelInboundOutboundIndicator="
				+ travelInboundOutboundIndicator + ", travelApplicationIndicator=" + travelApplicationIndicator
				+ ", travelLocationIndicator=" + travelLocationIndicator + ", travelBetweenOrViaGeoTableNo995="
				+ travelBetweenOrViaGeoTableNo995 + ", travelAndOrViaGeoTableNo995=" + travelAndOrViaGeoTableNo995
				+ ", travelViaIndicator=" + travelViaIndicator + ", travelViaGeoTableNo995=" + travelViaGeoTableNo995
				+ ", travelGeoPointsTag=" + travelGeoPointsTag + ", nonstopFlightsTag=" + nonstopFlightsTag
				+ ", directFlightsTag=" + directFlightsTag + ", multistopFlightsTag=" + multistopFlightsTag
				+ ", onestopFlightsTag=" + onestopFlightsTag + ", onlineConnectingFlightsTag="
				+ onlineConnectingFlightsTag + ", interlineConnectingFlightsTag=" + interlineConnectingFlightsTag
				+ ", sameFlightNoTag=" + sameFlightNoTag + ", equipmentApplication=" + equipmentApplication
				+ ", equipmentTypeCode=" + equipmentTypeCode + ", overrideDateTableNo994=" + overrideDateTableNo994
				+ ", textTable996=" + textTable996 + ", unavailableDataTag=" + unavailableDataTag + "]";
	}
}
