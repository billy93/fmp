package com.atibusinessgroup.fmp.service.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoTaxX1;
import com.atibusinessgroup.fmp.domain.dto.Tax;
import com.atibusinessgroup.fmp.domain.dto.UserZoneTable;
import com.atibusinessgroup.fmp.repository.custom.AtpcoTaxTableCustomRepository;

@Service
public class TaxQueryMapper {
	
	private final AtpcoTaxTableCustomRepository atpcoTaxTableCustomRepository;
	
	public TaxQueryMapper(AtpcoTaxTableCustomRepository atpcoTaxTableCustomRepository) {
		this.atpcoTaxTableCustomRepository = atpcoTaxTableCustomRepository;
	}
	
	public Tax convertTax(AtpcoTaxX1 atax) {
		Tax result = new Tax();
		
		result.setId(atax.get_id());
		result.setCarrierCode(atax.getTax_carrier());
		result.setNation(atax.getNation());
		result.setTaxCode(atax.getTax_code());
		result.setTaxType(atax.getTax_type());
		result.setSequenceNo(atax.getSeq_no());
		result.setTaxPointTag(atax.getTax_point_tag());
		result.setPercentFlatTag(atax.getPercent_flat_tag());
		result.setTaxUnitTag(atax.getTax_unit_tag());
		result.setTicketedPointTag(atax.getTicketed_point_tag());
		
		if (atax.getSale_dates() != null) {
			result.setSaleFromDate(atax.getSale_dates().getSale_dates_first());
			result.setSaleToDate(atax.getSale_dates().getSale_dates_last());
		}
		
		if (atax.getTravel_dates() != null) {
			if (atax.getTravel_dates().getTravel_dates_tag() != null && !atax.getTravel_dates().getTravel_dates_tag().trim().isEmpty()) {
				if (atax.getTravel_dates().getTravel_dates_tag().trim().contentEquals("J")) {
					result.setTravelApplication("Journey");
				} else if (atax.getTravel_dates().getTravel_dates_tag().trim().contentEquals("T")) {
					result.setTravelApplication("Tax Point");
				}
			}
			
			result.setTravelFromDate(atax.getTravel_dates().getTravel_dates_first());
			result.setTravelToDate(atax.getTravel_dates().getTravel_dates_last());
		}
		
		if (atax.getPos() != null) {
			if (atax.getPos().getPos_geo_type() != null && !atax.getPos().getPos_geo_type().trim().isEmpty()) {
				String pos = "";
				String type = convertGeoSpecType(atax.getPos().getPos_geo_type().trim());
				if (type != null && !type.contentEquals("Table178")) {
					pos += type;
					
					if (atax.getPos().getPos_geo_value() != null && !atax.getPos().getPos_geo_value().isEmpty()) {
						pos += " " + atax.getPos().getPos_geo_value();
					}
				} else {
					if (atax.getPos().getPos_geo_value() != null && !atax.getPos().getPos_geo_value().trim().isEmpty()) {
						pos = convertUserZoneTableToText(atpcoTaxTableCustomRepository.findUserTable178(atax.getPos().getPos_geo_value().trim()));
					}
				}
				
				result.setPointOfSale(pos);
			}
		}
		
		if (atax.getPot() != null) {
			if (atax.getPot().getPot_geo_type() != null && !atax.getPot().getPot_geo_type().trim().isEmpty()) {
				String pot = "";
				String type = convertGeoSpecType(atax.getPot().getPot_geo_type().trim());
				if (type != null && !type.contentEquals("Table178")) {
					pot += type;
					
					if (atax.getPot().getPot_geo_value() != null && !atax.getPot().getPot_geo_value().isEmpty()) {
						pot += " " + atax.getPot().getPot_geo_value();
					}
				} else {
					if (atax.getPot().getPot_geo_value() != null && !atax.getPot().getPot_geo_value().trim().isEmpty()) {
						pot = convertUserZoneTableToText(atpcoTaxTableCustomRepository.findUserTable178(atax.getPot().getPot_geo_value().trim()));
					}
				}
				
				result.setPointOfTicketing(pot);
			}
		}
		
		result.setSecurityTable183(atax.getSecurity_tbl_no_183());
		
		if (atax.getJrny_geo_spec() != null) {
			if (atax.getJrny_geo_spec().getJrny_geo_spec_indicator() != null) {
				if (atax.getJrny_geo_spec().getJrny_geo_spec_indicator().trim().isEmpty()) {
					result.setJourneyDirection("Loc 1 <-->");
				} else if (atax.getJrny_geo_spec().getJrny_geo_spec_indicator().trim().contentEquals("A")) {
					result.setJourneyDirection("Loc 1  -->");
				}
			}
			
			if (atax.getJrny_geo_spec().getJrny_geo_spec_1_type() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_1_type().isEmpty()) {
				String loc1 = "";
				String type = convertGeoSpecType(atax.getJrny_geo_spec().getJrny_geo_spec_1_type());
				if (type != null && !type.contentEquals("Table178")) {
					loc1 += type;
					
					if (atax.getJrny_geo_spec().getJrny_geo_spec_1_value() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_1_value().isEmpty()) {
						loc1 += " " + atax.getJrny_geo_spec().getJrny_geo_spec_1_value();
					}
				} else {
					if (atax.getJrny_geo_spec().getJrny_geo_spec_1_value() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_1_value().trim().isEmpty()) {
						loc1 = convertUserZoneTableToText(atpcoTaxTableCustomRepository.findUserTable178(atax.getJrny_geo_spec().getJrny_geo_spec_1_value().trim()));
					}
				}
				
				result.setJourneyLoc1(loc1);
			}
			
			if (atax.getJrny_geo_spec().getJrny_geo_spec_2_type() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_2_type().isEmpty()) {
				String loc2 = "";
				String type = convertGeoSpecType(atax.getJrny_geo_spec().getJrny_geo_spec_2_type());
				if (type != null && !type.contentEquals("Table178")) {
					loc2 += type;
					
					if (atax.getJrny_geo_spec().getJrny_geo_spec_2_value() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_2_value().isEmpty()) {
						loc2 += " " + atax.getJrny_geo_spec().getJrny_geo_spec_2_value();
					}
				} else {
					if (atax.getJrny_geo_spec().getJrny_geo_spec_2_value() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_2_value().trim().isEmpty()) {
						loc2 = convertUserZoneTableToText(atpcoTaxTableCustomRepository.findUserTable178(atax.getJrny_geo_spec().getJrny_geo_spec_2_value().trim()));
					}
				}
				
				result.setJourneyLoc2(loc2);
			}
			
			if (atax.getJrny_geo_spec().getJrny_geo_spec_journey_type() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_journey_type().isEmpty()) {
				String include = "";
				String type = convertGeoSpecType(atax.getJrny_geo_spec().getJrny_geo_spec_journey_type());
				if (type != null && !type.contentEquals("Table178")) {
					include += type;
					
					if (atax.getJrny_geo_spec().getJrny_geo_spec_journey_value() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_journey_value().isEmpty()) {
						include += " " + atax.getJrny_geo_spec().getJrny_geo_spec_journey_value();
					}
				} else {
					if (atax.getJrny_geo_spec().getJrny_geo_spec_journey_value() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_journey_value().trim().isEmpty()) {
						include = convertUserZoneTableToText(atpcoTaxTableCustomRepository.findUserTable178(atax.getJrny_geo_spec().getJrny_geo_spec_journey_value().trim()));
					}
				}
				
				result.setJourneyIncludes(include);
			}
			
			if (atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_type() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_type().isEmpty()) {
				String within = "";
				String type = convertGeoSpecType(atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_type());
				if (type != null && !type.contentEquals("Table178")) {
					within += type;
					
					if (atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_value() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_value().isEmpty()) {
						within += " " + atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_value();
					}
				} else {
					if (atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_value() != null && !atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_value().trim().isEmpty()) {
						within = convertUserZoneTableToText(atpcoTaxTableCustomRepository.findUserTable178(atax.getJrny_geo_spec().getJrny_geo_spec_travel_wholly_value().trim()));
					}
				}
				
				result.setJourneyWhollyWithin(within);
			}
		}
		
		return result;
	}
	
	private String convertUserZoneTableToText(List<UserZoneTable> uzts) {
		String result = "";
		
		for (UserZoneTable uzt:uzts) {
			String type = convertGeoSpecType(uzt.getGeo_loc_type());
			if (type != null) {
				if (uzt.getGeo_loc_value() != null && !uzt.getGeo_loc_value().trim().isEmpty()) {
					if (uzt.getAppl() != null) {
						if (uzt.getAppl().trim().isEmpty()) {
							result += "Include " + type + " " + uzt.getGeo_loc_value().trim() + "\n";
						} else if (uzt.getAppl().trim().contentEquals("N")) {
							result += "Exclude " + type + " " + uzt.getGeo_loc_value().trim() + "\n";
						}
					}
				}
			}
		}
		
		return result;
	}
	private String convertGeoSpecType(String type) {
		String result = null;
		
		switch (type) {
		case "A" : result = "Area";
		break;
		case "Z" : result = "Zone";
		break;
		case "N" : result = "Country";
		break;
		case "S" : result = "State";
		break;
		case "C" : result = "City";
		break;
		case "P" : result = "Airport";
		break;
		case "U" : result = "Table178";
		break;
		}

		return result;
	}
}
