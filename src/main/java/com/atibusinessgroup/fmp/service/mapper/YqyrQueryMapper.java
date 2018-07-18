package com.atibusinessgroup.fmp.service.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoYqyrS1;
import com.atibusinessgroup.fmp.domain.dto.CarrierApplicationTable;
import com.atibusinessgroup.fmp.domain.dto.CarrierApplicationTableDataSegs;
import com.atibusinessgroup.fmp.domain.dto.CarrierTable;
import com.atibusinessgroup.fmp.domain.dto.CarrierTableDataSegs;
import com.atibusinessgroup.fmp.domain.dto.TextTable;
import com.atibusinessgroup.fmp.domain.dto.UserZoneTable;
import com.atibusinessgroup.fmp.domain.dto.Yqyr;
import com.atibusinessgroup.fmp.repository.custom.AtpcoYqyrTableRepository;

@Service
public class YqyrQueryMapper {
	
	private final AtpcoYqyrTableRepository atpcoYqyrTableRepository;
	
	public YqyrQueryMapper(AtpcoYqyrTableRepository atpcoYqyrTableRepository) {
		this.atpcoYqyrTableRepository = atpcoYqyrTableRepository;
	}
		
	public Yqyr convertYqyr(AtpcoYqyrS1 ayqyr) {
		Yqyr result = new Yqyr();
		
		result.setId(ayqyr.getId());
		result.setCarrierCode(ayqyr.getCxr_code());
		result.setTc(ayqyr.getService_type_tax());
		result.setSc(ayqyr.getService_type_sub_code());
		result.setSequenceNo(ayqyr.getSeq_no());
		
		try {
			result.setSequenceInt(Integer.parseInt(ayqyr.getSeq_no()));
		} catch (Exception e) {
		}
		
		if (ayqyr.getService() != null) {
			if (ayqyr.getService().getService_fee_amount().bigDecimalValue().doubleValue() != 0.0) {
				result.setFeeAmount(ayqyr.getService().getService_fee_amount().bigDecimalValue().doubleValue());
				result.setCurrencyCode(ayqyr.getService().getService_fee_cur());
			}
			
			result.setFeePct(ayqyr.getService().getService_fee_percent().bigDecimalValue().doubleValue());
			
			if (ayqyr.getService().getService_fee_tax_include() != null && ayqyr.getService().getService_fee_tax_include().trim().contentEquals("X")) {
				result.setFeeIncludeTax(true);
			}
			
			if (ayqyr.getService().getService_fee_application() != null) {
				if (ayqyr.getService().getService_fee_application().trim().isEmpty()) {
					result.setFeeApplication("Per Sector");
				} else if (ayqyr.getService().getService_fee_application().trim().contentEquals("1")) {
					result.setFeeApplication("Per Direction");
				} else if (ayqyr.getService().getService_fee_application().trim().contentEquals("2")) {
					result.setFeeApplication("Per Journey");
				}
			}
		}
		
		if (ayqyr.getJrny_geo_spec() != null) {
			if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_indicator() != null) {
				if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_indicator().trim().isEmpty()) {
					result.setJourneyDirection("Loc 1 <-->");
				} else if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_indicator().trim().contentEquals("A")) {
					result.setJourneyDirection("Loc 1  -->");
				}
			}
			
			if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_type() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_type().isEmpty()) {
				String loc1 = "";
				String type = convertGeoSpecType(ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_type());
				if (type != null && !type.contentEquals("Table178")) {
					loc1 += type;
					
					if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_value() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_value().isEmpty()) {
						loc1 += " " + ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_value();
					}
				} else {
					if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_value() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_value().trim().isEmpty()) {
						loc1 = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getJrny_geo_spec().getJrny_geo_spec_1_value().trim()));
					}
				}
				
				result.setJourneyLoc1(loc1);
			}
			
			if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_type() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_type().isEmpty()) {
				String loc2 = "";
				String type = convertGeoSpecType(ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_type());
				if (type != null && !type.contentEquals("Table178")) {
					loc2 += type;
					
					if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_value() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_value().isEmpty()) {
						loc2 += " " + ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_value();
					}
				} else {
					if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_value() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_value().trim().isEmpty()) {
						loc2 = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getJrny_geo_spec().getJrny_geo_spec_2_value().trim()));
					}
				}
				
				result.setJourneyLoc2(loc2);
			}
			
			if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_type() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_type().isEmpty()) {
				String via = "";
				String type = convertGeoSpecType(ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_type());
				if (type != null && !type.contentEquals("Table178")) {
					via += type;
					
					if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_value() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_value().isEmpty()) {
						via += " " + ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_value();
					}
				} else {
					if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_value() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_value().trim().isEmpty()) {
						via = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getJrny_geo_spec().getJrny_geo_spec_via_loc_value().trim()));
					}
				}
				
				result.setJourneyVia(via);
			}
			
			if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_type() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_type().isEmpty()) {
				String within = "";
				String type = convertGeoSpecType(ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_type());
				if (type != null && !type.contentEquals("Table178")) {
					within += type;
					
					if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_value() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_value().isEmpty()) {
						within += " " + ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_value();
					}
				} else {
					if (ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_value() != null && !ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_value().trim().isEmpty()) {
						within = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getJrny_geo_spec().getJrny_geo_spec_within_loc_value().trim()));
					}
				}
				
				result.setJourneyWhollyWithin(within);
			}
		}
		
		if (ayqyr.getSector() != null) {
			if (ayqyr.getSector().getSector_indicator() != null) {
				if (ayqyr.getSector().getSector_indicator().trim().isEmpty()) {
					result.setSectorPortionFlag("Sector");
				} else if (ayqyr.getSector().getSector_indicator().trim().contentEquals("P")) {
					result.setSectorPortionFlag("Portion of Travel");
				}
			}
			
			if (ayqyr.getSector().getSector_from_to() != null && !ayqyr.getSector().getSector_from_to().trim().isEmpty()) {
				result.setSectorDirection(null);
			}
			
			if (ayqyr.getSector().getSector_loc_1_type() != null && !ayqyr.getSector().getSector_loc_1_type().isEmpty()) {
				String loc1 = "";
				String type = convertGeoSpecType(ayqyr.getSector().getSector_loc_1_type());
				if (type != null && !type.contentEquals("Table178")) {
					loc1 += type;
					
					if (ayqyr.getSector().getSector_loc_1_value() != null && !ayqyr.getSector().getSector_loc_1_value().isEmpty()) {
						loc1 += " " + ayqyr.getSector().getSector_loc_1_value();
					}
				} else {
					if (ayqyr.getSector().getSector_loc_1_value() != null && !ayqyr.getSector().getSector_loc_1_value().trim().isEmpty()) {
						loc1 = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getSector().getSector_loc_1_value().trim()));
					}
				}
				
				result.setSectorLoc1(loc1);
			}
			
			if (ayqyr.getSector().getSector_loc_2_type() != null && !ayqyr.getSector().getSector_loc_2_type().isEmpty()) {
				String loc2 = "";
				String type = convertGeoSpecType(ayqyr.getSector().getSector_loc_2_type());
				if (type != null && !type.contentEquals("Table178")) {
					loc2 += type;
					
					if (ayqyr.getSector().getSector_loc_2_value() != null && !ayqyr.getSector().getSector_loc_2_value().isEmpty()) {
						loc2 += " " + ayqyr.getSector().getSector_loc_2_value();
					}
				} else {
					if (ayqyr.getSector().getSector_loc_2_value() != null && !ayqyr.getSector().getSector_loc_2_value().trim().isEmpty()) {
						loc2 = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getSector().getSector_loc_2_value().trim()));
					}
				}
				
				result.setSectorLoc2(loc2);
			}
			
			if (ayqyr.getSector().getSector_via_geo_type() != null && !ayqyr.getSector().getSector_via_geo_type().isEmpty()) {
				String via = "";
				String type = convertGeoSpecType(ayqyr.getSector().getSector_via_geo_type());
				if (type != null && !type.contentEquals("Table178")) {
					via += type;
					
					if (ayqyr.getSector().getSector_via_geo_value() != null && !ayqyr.getSector().getSector_via_geo_value().isEmpty()) {
						via += " " + ayqyr.getSector().getSector_via_geo_value();
					}
				} else {
					if (ayqyr.getSector().getSector_via_geo_value() != null && !ayqyr.getSector().getSector_via_geo_value().trim().isEmpty()) {
						via = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getSector().getSector_via_geo_value().trim()));
					}
				}
				
				result.setSectorVia(via);
			}
			
			if (ayqyr.getSector().getSector_via_stp() != null && !ayqyr.getSector().getSector_via_stp().trim().isEmpty()) {
				if (ayqyr.getSector().getSector_via_stp().trim().contentEquals("C")) {
					result.setStopoverConnection("Must be a connection");
				} else if (ayqyr.getSector().getSector_via_stp().trim().contentEquals("S")) {
					result.setStopoverConnection("Must be a stopover");
				}
			}
			
			if (ayqyr.getSector().getSector_via_exc_stop_time() != null && !ayqyr.getSector().getSector_via_exc_stop_time().trim().isEmpty()) {
				if (ayqyr.getSector().getSector_via_exc_stop_unit() != null && !ayqyr.getSector().getSector_via_exc_stop_unit().trim().isEmpty()) {
					String unit = convertTimeUnitType(ayqyr.getSector().getSector_via_exc_stop_unit());
					if (unit != null) {
						try {
							result.setConnectionMaxTime(Integer.parseInt(ayqyr.getSector().getSector_via_exc_stop_time().trim()) + " " + unit);
						} catch (Exception e) {
						}
					}
				}
			}
			
			if (ayqyr.getSector().getSector_via_cnx_exempt() != null && ayqyr.getSector().getSector_via_cnx_exempt().trim().contentEquals("X")) {
				result.setConnectionsExempt(true);
			}
			
			if (ayqyr.getSector().getSector_via_intl() != null && !ayqyr.getSector().getSector_via_intl().trim().isEmpty()) {
				if (ayqyr.getSector().getSector_via_intl().trim().contentEquals("I")) {
					result.setInternationalDomestic("International only");
				} else if (ayqyr.getSector().getSector_via_intl().trim().contentEquals("D")) {
					result.setInternationalDomestic("Domestic only");
				}
			}
		}
		
		if (ayqyr.getPos() != null) {
			if (ayqyr.getPos().getPos_geo_spec_type() != null && !ayqyr.getPos().getPos_geo_spec_type().trim().isEmpty()) {
				String pos = "";
				String type = convertGeoSpecType(ayqyr.getPos().getPos_geo_spec_type());
				if (type != null && !type.contentEquals("Table178")) {
					pos += type;
					
					if (ayqyr.getPos().getPos_geo_spec_value() != null && !ayqyr.getPos().getPos_geo_spec_value().isEmpty()) {
						pos += " " + ayqyr.getPos().getPos_geo_spec_value();
					}
				} else {
					if (ayqyr.getPos().getPos_geo_spec_value() != null && !ayqyr.getPos().getPos_geo_spec_value().trim().isEmpty()) {
						pos = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getPos().getPos_geo_spec_value().trim()));
					}
				}
				
				result.setPointOfSale(pos);
			}
			
			if (ayqyr.getPos().getPos_type() != null && !ayqyr.getPos().getPos_type().trim().isEmpty()) {
				String code = "";
				if (ayqyr.getPos().getPos_type().trim().contentEquals("I")) {
					code += "IATA ";
				} else if (ayqyr.getPos().getPos_type().trim().contentEquals("T")) {
					code += "Pseudo ";
				}
				if (ayqyr.getPos().getPos_code() != null && !ayqyr.getPos().getPos_code().trim().isEmpty()) {
					result.setPointOfSaleCode(code + ayqyr.getPos().getPos_code().trim());
				}
			}
		}
		
		if (ayqyr.getPot() != null) {
			if (ayqyr.getPot().getPot_geo_spec_type() != null && !ayqyr.getPot().getPot_geo_spec_type().trim().isEmpty()) {
				String pot = "";
				String type = convertGeoSpecType(ayqyr.getPot().getPot_geo_spec_type());
				if (type != null && !type.contentEquals("Table178")) {
					pot += type;
					
					if (ayqyr.getPot().getPot_geo_spec_value() != null && !ayqyr.getPot().getPot_geo_spec_value().isEmpty()) {
						pot += " " + ayqyr.getPot().getPot_geo_spec_value();
					}
				} else {
					if (ayqyr.getPot().getPot_geo_spec_value() != null && !ayqyr.getPot().getPot_geo_spec_value().trim().isEmpty()) {
						pot = convertUserZoneTableToText(atpcoYqyrTableRepository.findUserTable178(ayqyr.getPot().getPot_geo_spec_value().trim()));
					}
				}
				
				result.setPointOfTicketing(pot);
			}
		}
		
		if (ayqyr.getPassenger_type() != null && !ayqyr.getPassenger_type().trim().isEmpty()) {
			result.setPaxType(ayqyr.getPassenger_type().trim());
		}
		
		if (ayqyr.getRbd() != null && !ayqyr.getRbd().trim().isEmpty()) {
			String[] parts = ayqyr.getRbd().split(" ");
			String rbds = "";
			for (String part:parts) {
				rbds += part + ", ";
			}
			if (!rbds.isEmpty()) {
				result.setRbd(rbds.substring(0, rbds.length() - 2));
			}
		}
		
		if (ayqyr.getFare_basis_code() != null && !ayqyr.getFare_basis_code().trim().isEmpty()) {
			result.setFareClass(ayqyr.getFare_basis_code().trim());
		}
		
		if (ayqyr.getEqp() != null && !ayqyr.getEqp().trim().isEmpty()) {
			result.setEquipment(ayqyr.getEqp().trim());
		}
		
		if (ayqyr.getTicket_dates() != null) {
			result.setTravelEffDate(ayqyr.getTicket_dates().getTravel_dates_eff());
			result.setTravelDiscDate(ayqyr.getTicket_dates().getTravel_dates_disc());
			result.setTicketEffDate(ayqyr.getTicket_dates().getTicket_dates_first());
			result.setTicketDiscDate(ayqyr.getTicket_dates().getTicket_dates_last());
		}
		
		if (ayqyr.getCarrier_appl_tbl_190() != null && !ayqyr.getCarrier_appl_tbl_190().trim().isEmpty() && !ayqyr.getCarrier_appl_tbl_190().trim().contentEquals("00000000")) {
			CarrierApplicationTable cat = atpcoYqyrTableRepository.getCarrierApplicationTable190(ayqyr.getCarrier_appl_tbl_190().trim());	
			if (cat != null) {
				String catext = "";
				for (CarrierApplicationTableDataSegs catd:cat.getData_segs()) {
					if (catd.getAppl() != null) {
						if (catd.getCxr() != null && !catd.getCxr().trim().isEmpty()) {
							if (catd.getAppl().trim().isEmpty()) {
								catext += "Include " + catd.getCxr().trim() + "\n";
							} else if (catd.getAppl().trim().contentEquals("X")) {
								catext += "Exclude " + catd.getCxr().trim() + "\n";
							}
						}
					}
				}
				result.setValCarrierCode(catext);
			}
		}
		
		if (ayqyr.getCxr_tbl_186() != null && !ayqyr.getCxr_tbl_186().trim().isEmpty() && !ayqyr.getCxr_tbl_186().trim().contentEquals("00000000")) {
			CarrierTable ct = atpcoYqyrTableRepository.findCarrierTable186(ayqyr.getCxr_tbl_186().trim());
			if (ct != null) {
				String ctext = "";
				for (CarrierTableDataSegs seg:ct.getData_segs()) {
					String stext = "";
					if (seg.getMarketing_carrier() != null && !seg.getMarketing_carrier().trim().isEmpty()) {
						stext += "Marketing: " + seg.getMarketing_carrier().trim();
					}
					if (seg.getOperating_carrier() != null && !seg.getOperating_carrier().trim().isEmpty()) {
						if (!stext.isEmpty()) {
							stext += ", ";
						}
						stext += "Operating: " + seg.getOperating_carrier().trim();
					}
					if (seg.getFlt_no_1() != null && !seg.getFlt_no_1().trim().isEmpty()) {
						if (!stext.isEmpty()) {
							stext += ", ";
						}
						stext += "Flight No: " + seg.getFlt_no_1().trim();
					}
					if (seg.getFlt_no_2() != null && !seg.getFlt_no_2().trim().isEmpty()) {
						if (!stext.isEmpty()) {
							stext += ", ";
						}
						stext += "through: " + seg.getFlt_no_2().trim();
					}
					if (!stext.isEmpty()) {
						ctext += stext + "\n";
					}
				}
				result.setCarrierFlightApplication(ctext);
			}
		}
		
		if (ayqyr.getTxt_tbl_no_196() != null && !ayqyr.getTxt_tbl_no_196().trim().isEmpty() && !ayqyr.getTxt_tbl_no_196().trim().contentEquals("00000000")) {
			TextTable tt = atpcoYqyrTableRepository.findTextTable196(ayqyr.getTxt_tbl_no_196().trim());
			if (tt != null) {
				String text = "";
				for (String line:tt.getText()) {
					text += line + "\n";
				}
				result.setComment(text);
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
	
	private String convertTimeUnitType(String type) {
		String result = null;
		
		switch (type) {
		case "N" : result = "Minutes";
		break;
		case "H" : result = "Hours";
		break;
		case "D" : result = "Days";
		break;
		case "M" : result = "Months";
		break;
		}

		return result;
	}
}
