package com.atibusinessgroup.fmp.service.mapper;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterFareMatrix;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat03;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat05;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat06;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat07;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat14;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat15;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat50;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord1FareClassInformation;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.service.AtpcoMasterFareMatrixService;
import com.atibusinessgroup.fmp.service.util.DateUtil;
import com.atibusinessgroup.fmp.service.util.TypeConverterUtil;

@Service
public class AfdQueryMapper {
	
	private final AtpcoMasterFareMatrixService atpcoMasterFareMatrixService;
	
	public AfdQueryMapper(AtpcoMasterFareMatrixService atpcoMasterFareMatrixService) {
		this.atpcoMasterFareMatrixService = atpcoMasterFareMatrixService;
	}
	
	public AfdQuery convertAtpcoFare(AtpcoFare afare, AtpcoRecord1 record1, List<CategoryObject> cat03s, List<CategoryObject> cat05s, List<CategoryObject> cat06s, List<CategoryObject> cat07s, 
			List<CategoryObject> cat14s, List<CategoryObject> cat15s, List<CategoryObject> cat27s, List<CategoryObject> cat35s, List<CategoryObject> cat50s, List<CategoryObject> footnote14s, 
			List<CategoryObject> footnote15s, Date focusDate) {

		AfdQuery result = new AfdQuery();
		
		//AtpcoFare attributes
		result.setAtpcoFareId(afare.getId());
		result.setSource(afare.getSource());
		result.setSc("S");
		result.setTariffNo(afare.getTariffNo());
//		result.setTariffCode(tariffCode);
		result.setCarrierCode(afare.getCarrierCode());
		result.setOriginCity(afare.getOriginCity());
		result.setOriginCountry(afare.getOriginCountry());
		result.setDestinationCity(afare.getDestinationCity());
		result.setDestinationCountry(afare.getDestinationCountry());
		result.setFareClassCode(afare.getFareClassCode());
		result.setOwrt(afare.getOwrt());
		result.setFootnote(afare.getFootnote());
		result.setRoutingNo(afare.getRoutingNo());
		result.setRuleNo(afare.getRuleNo());
		result.setMaximumPermittedMileage(afare.getMaximumPermittedMileage());
		result.setCurrencyCode(afare.getFareOriginCurrencyCode());
		result.setBaseAmount(afare.getFareOriginAmount().bigDecimalValue().doubleValue());
		result.setEffectiveDate(afare.getTariffEffectiveDateObject());
		result.setDiscontinueDate(afare.getDiscontinueDateObject());
		result.setGlobalIndicator(afare.getGlobalIndicator());
		result.setSaleStartDate(afare.getFirstSaleDateObject());
		result.setSaleEndDate(afare.getLastSaleDateObject());
		result.setGfsReference(afare.getGfsNumber());
		result.setGfsDate(afare.getGfsDateObject());
		
		//AtpcoRecord1 attributes
		if (record1 != null) {
			for (AtpcoRecord1FareClassInformation fci:record1.getFareClassInformation()) {
				String rbds = "";
				for (String rbd:fci.getRbd()) {
					if (rbd != null && !rbd.trim().isEmpty()) {
						rbds += rbd + ", ";
					}
				}
				result.setBookingClass(!rbds.isEmpty() ? rbds.substring(0, rbds.length() - 2) : "");
				result.setPaxType(fci.getPassengerType());
			}
			
			result.setSeason(record1.getSeasonType());
			result.setFareType(record1.getFareType());
			
			if (result.getFareType() != null && !result.getFareType().trim().isEmpty()) {
				AtpcoMasterFareMatrix fareMatrix = atpcoMasterFareMatrixService.findOneByFareTypeCode(result.getFareType().trim());
				if (fareMatrix != null) {
					result.setCabin(fareMatrix.getCabinCode());
				}
			}
		}
		
		//Rule attributes
		if (cat03s != null) {
			for (CategoryObject cat03o:cat03s) {
				AtpcoRecord3Cat03 cat03 = (AtpcoRecord3Cat03) cat03o.getCategory();
				result.setFirstSeasonDate(DateUtil.convertSeasonDayMonthYearFormat(cat03.getDate_start_dd(), cat03.getDate_start_mm(), cat03.getDate_start_yy()));
				result.setLastSeasonDate(DateUtil.convertSeasonDayMonthYearFormat(cat03.getDate_stop_dd(), cat03.getDate_stop_mm(), cat03.getDate_stop_yy()));
			}
		}
		
		if (cat05s != null) {
			for (CategoryObject cat05o:cat05s) {
				AtpcoRecord3Cat05 cat05 = (AtpcoRecord3Cat05) cat05o.getCategory();
				Integer lp = TypeConverterUtil.convertStringToInt(cat05.getAdvancedReservationLastPeriod());
				String advPur = lp != null ? lp.toString().concat(cat05.getAdvancedReservationLastUnit() != null ? cat05.getAdvancedReservationLastUnit() : "") : "";
				result.setAdvancePurchase(advPur);
			}
		}
		
		if (cat06s != null) {
			for (CategoryObject cat06o:cat06s) {
				AtpcoRecord3Cat06 cat06 = (AtpcoRecord3Cat06) cat06o.getCategory();
				Integer ms = TypeConverterUtil.convertStringToInt(cat06.getMinStay());
				String minStay = ms != null ? ms.toString().concat(cat06.getUnit() != null ? cat06.getUnit() : "") : "";
				result.setMinStay(minStay);
			}
		}
		
		if (cat07s != null) {
			for (CategoryObject cat07o:cat07s) {
				AtpcoRecord3Cat07 cat07 = (AtpcoRecord3Cat07) cat07o.getCategory();
				Integer ms = TypeConverterUtil.convertStringToInt(cat07.getMaxStay());
				String maxStay = ms != null ? ms.toString().concat(cat07.getUnit() != null ? cat07.getUnit() : "") : "";
				result.setMaxStay(maxStay);
			}
		}
		
		if (cat14s != null) {
			for (CategoryObject cat14o:cat14s) {
				AtpcoRecord3Cat14 cat14 = (AtpcoRecord3Cat14) cat14o.getCategory();
				result.setTravelStartDate(cat14.getTravel_dates_comm());
				result.setTravelEndDate(cat14.getTravel_dates_exp());
				result.setTravelComplete(cat14.getTravel_dates_commence_complete());
			}
		}
		
		if (cat15s != null) {
			for (CategoryObject cat15o:cat15s) {
				AtpcoRecord3Cat15 cat15 = (AtpcoRecord3Cat15) cat15o.getCategory();
				result.setSaleStartDate(cat15.getSales_dates_earliest_tktg());
				result.setSaleEndDate(cat15.getSales_dates_latest_tktg());
				result.setResStartDate(cat15.getSales_dates_earliest_res());
				result.setResEndDate(cat15.getSales_dates_latest_res());
			}
		}
		
		if (cat50s != null) {
			for (CategoryObject cat50o:cat50s) {
				AtpcoRecord3Cat50 cat50 = (AtpcoRecord3Cat50) cat50o.getCategory();
				
			}
		}
		
		//Footnote attributes
		if (footnote14s != null) {
			for (CategoryObject footnote14:footnote14s) {
				AtpcoRecord3Cat14 cat14 = (AtpcoRecord3Cat14) footnote14.getCategory();
				result.setTravelStartDate(cat14.getTravel_dates_comm());
				result.setTravelEndDate(cat14.getTravel_dates_exp());
				result.setTravelComplete(cat14.getTravel_dates_commence_complete());
			}
		}
		
		if (footnote15s != null) {
			for (CategoryObject footnote15:footnote15s) {
				AtpcoRecord3Cat15 cat15 = (AtpcoRecord3Cat15) footnote15.getCategory();
				result.setSaleStartDate(cat15.getSales_dates_earliest_tktg());
				result.setSaleEndDate(cat15.getSales_dates_latest_tktg());
				result.setResStartDate(cat15.getSales_dates_earliest_res());
				result.setResEndDate(cat15.getSales_dates_latest_res());
			}
		}
		
		result.setFocusDate(focusDate);
		
		return result;
	}
}
