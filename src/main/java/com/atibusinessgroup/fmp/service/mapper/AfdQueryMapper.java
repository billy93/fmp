package com.atibusinessgroup.fmp.service.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.AtpcoMasterTariff;
import com.atibusinessgroup.fmp.domain.VoltrasFare;
import com.atibusinessgroup.fmp.domain.WorkPackageFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoAddOn;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterFareMatrix;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat03;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat05;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat06;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat07;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat14;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat15;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat27;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat35;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat35Ticketing;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat50;
import com.atibusinessgroup.fmp.domain.dto.SpecifiedConstructed;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryAddOns;
import com.atibusinessgroup.fmp.domain.dto.AtpcoDateWrapper;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord1FareClassInformation;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.domain.dto.TextTable;
import com.atibusinessgroup.fmp.repository.AtpcoMasterTariffRepository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoRecord3CategoryCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoMasterFareMatrixService;
import com.atibusinessgroup.fmp.service.util.AtpcoDataConverterUtil;
import com.atibusinessgroup.fmp.service.util.DateUtil;
import com.atibusinessgroup.fmp.service.util.TypeConverterUtil;

@Service
public class AfdQueryMapper {
	
	private final AtpcoMasterFareMatrixService atpcoMasterFareMatrixService;
	private final AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository;
	private final AtpcoMasterTariffRepository tariffNumberRepository;
	
	public AfdQueryMapper(AtpcoMasterFareMatrixService atpcoMasterFareMatrixService, AtpcoRecord3CategoryCustomRepository atpcoRecord3CategoryCustomRepository, AtpcoMasterTariffRepository tariffNumberRepository) {
		this.atpcoMasterFareMatrixService = atpcoMasterFareMatrixService;
		this.atpcoRecord3CategoryCustomRepository = atpcoRecord3CategoryCustomRepository;
		this.tariffNumberRepository = tariffNumberRepository;
	}
	
	public SpecifiedConstructed convertAtpcoFare(AtpcoFare afare, AtpcoRecord1 record1, List<CategoryObject> cat03s, List<CategoryObject> cat05s, List<CategoryObject> cat06s, List<CategoryObject> cat07s, 
			List<CategoryObject> cat14s, List<CategoryObject> cat15s, List<CategoryObject> cat27s, List<CategoryObject> cat35s, List<CategoryObject> cat50s, List<CategoryObject> footnote14s, 
			List<CategoryObject> footnote15s, Date focusDate) {

		SpecifiedConstructed result = new SpecifiedConstructed();
		
		//AtpcoFare attributes
		result.setFareId(afare.getId());
		result.setSource(afare.getSource());
		result.setSc("S");
		result.setTariffNo(afare.getTariffNo());
		
		AtpcoMasterTariff tn = tariffNumberRepository.findOneByTarNo(result.getTariffNo());
		if (tn != null && tn.getTarCd() != null) {
			result.setTariffCode(tn.getTarCd());
		}
		
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
		result.setGfsReference(afare.getGfsNumber());
		result.setGfsDate(afare.getGfsDateObject());
		
		//AtpcoRecord1 attributes
		if (record1 != null) {
			
			for (AtpcoRecord1FareClassInformation fci:record1.getFareClassInformation()) {
				for (String rbd:fci.getRbd()) {
					if (rbd != null && !rbd.trim().isEmpty() && !result.getBookingClass().contains(rbd.trim())) {
						result.getBookingClass().add(rbd.trim());
					}
				}
				if (fci.getPassengerType() != null && !fci.getPassengerType().trim().isEmpty() && !result.getPaxType().contains(fci.getPassengerType().trim())) {
					result.getPaxType().add(fci.getPassengerType().trim());
				}
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
			List<AtpcoDateWrapper> seasonDates = new ArrayList<>();
			List<Date> firsts = new ArrayList<>();
			List<Date> lasts = new ArrayList<>();
			for (CategoryObject cat03o:cat03s) {
				AtpcoDateWrapper range = new AtpcoDateWrapper();
				
				AtpcoRecord3Cat03 cat03 = (AtpcoRecord3Cat03) cat03o.getCategory();
				String first = DateUtil.convertSeasonDayMonthYearFormat(cat03.getDate_start_dd(), cat03.getDate_start_mm(), cat03.getDate_start_yy());
				try {
					Date dfirst = new SimpleDateFormat("ddMMMyyyy").parse(first);
					firsts.add(dfirst);
					range.setStartDate(dfirst);
				} catch (Exception e) {
				}
				
				String last = DateUtil.convertSeasonDayMonthYearFormat(cat03.getDate_stop_dd(), cat03.getDate_stop_mm(), cat03.getDate_stop_yy());
				try {
					Date dlast = new SimpleDateFormat("ddMMMyyyy").parse(last);
					lasts.add(dlast);
					range.setEndDate(dlast);
				} catch (Exception e) {
				}
				seasonDates.add(range);
			}
			result.setSeasonDates(seasonDates);
			if (firsts.size() > 0) {
				result.setFirstSeasonDate(Collections.min(firsts));
			}
			if (lasts.size() > 0) {
				result.setLastSeasonDate(Collections.max(lasts));
			}
		}
		
		if (cat05s != null) {
			for (CategoryObject cat05o:cat05s) {
				AtpcoRecord3Cat05 cat05 = (AtpcoRecord3Cat05) cat05o.getCategory();
				Integer lp = TypeConverterUtil.convertStringToInt(cat05.getAdvancedReservationLastPeriod());
				if (lp != 0 && cat05.getAdvancedReservationLastUnit() != null && !cat05.getAdvancedReservationLastUnit().isEmpty() &&
						!result.getAdvancePurchase().contains(lp.toString().concat(cat05.getAdvancedReservationLastUnit()))) {
					result.getAdvancePurchase().add(lp.toString().concat(cat05.getAdvancedReservationLastUnit()));
				}
			}
		}
		
		if (cat06s != null) {
			for (CategoryObject cat06o:cat06s) {
				AtpcoRecord3Cat06 cat06 = (AtpcoRecord3Cat06) cat06o.getCategory();
				Integer ms = TypeConverterUtil.convertStringToInt(cat06.getMinStay());
				if (ms != 0 && cat06.getUnit() != null && !cat06.getUnit().isEmpty() && !result.getMinStay().contains(ms.toString().concat(cat06.getUnit()))) {
					result.getMinStay().add(ms.toString().concat(cat06.getUnit()));
				}
			}
		}
		
		if (cat07s != null) {
			for (CategoryObject cat07o:cat07s) {
				AtpcoRecord3Cat07 cat07 = (AtpcoRecord3Cat07) cat07o.getCategory();
				Integer ms = TypeConverterUtil.convertStringToInt(cat07.getMaxStay());
				if (ms != 0 && cat07.getUnit() != null && !cat07.getUnit().isEmpty() && !result.getMaxStay().contains(ms.toString().concat(cat07.getUnit()))) {
					result.getMaxStay().add(ms.toString().concat(cat07.getUnit()));
				}
			}
		}
		
		if (cat14s != null) {
			List<AtpcoDateWrapper> travelDates = new ArrayList<>();
			List<Date> firsts = new ArrayList<>();
			List<Date> lasts = new ArrayList<>();
			for (CategoryObject cat14o:cat14s) {
				AtpcoDateWrapper range = new AtpcoDateWrapper();
				AtpcoRecord3Cat14 cat14 = (AtpcoRecord3Cat14) cat14o.getCategory();
				Date s = DateUtil.convertObjectToDate(cat14.getTravel_dates_comm());
				if (s != null) {
					firsts.add(s);
					range.setStartDate(s);
				}
				Date e = DateUtil.convertObjectToDate(cat14.getTravel_dates_exp());
				if (e != null) {
					lasts.add(e);
					range.setEndDate(e);
				}
				Date c = DateUtil.convertObjectToDate(cat14.getTravel_dates_commence_complete());
				if (c != null) {
					range.setCompleteDate(c);
				}
				travelDates.add(range);
			}
			result.setTravelDates(travelDates);
			if (firsts.size() > 0) {
				result.setFirstTravelDate(Collections.min(firsts));
			}
			if (lasts.size() > 0) {
				result.setLastTravelDate(Collections.max(lasts));
			}
		}
		
		if (cat15s != null) {
			List<AtpcoDateWrapper> saleDates = new ArrayList<>();
			List<Date> firsts = new ArrayList<>();
			List<Date> lasts = new ArrayList<>();
			List<Date> resfirsts = new ArrayList<>();
			List<Date> reslasts = new ArrayList<>();
			for (CategoryObject cat15o:cat15s) {
				AtpcoDateWrapper range = new AtpcoDateWrapper();
				
				AtpcoRecord3Cat15 cat15 = (AtpcoRecord3Cat15) cat15o.getCategory();
				Date s = DateUtil.convertObjectToDate(cat15.getSales_dates_earliest_tktg());
				if (s != null) {
					firsts.add(s);
					range.setStartDate(s);
				}
				Date e = DateUtil.convertObjectToDate(cat15.getSales_dates_latest_tktg());
				if (e != null) {
					lasts.add(e);
					range.setEndDate(e);
				}
				Date rs = DateUtil.convertObjectToDate(cat15.getSales_dates_earliest_res());
				if (rs != null) {
					resfirsts.add(rs);
					range.setResStartDate(rs);
				}
				Date re = DateUtil.convertObjectToDate(cat15.getSales_dates_latest_res());
				if (re != null) {
					reslasts.add(re);
					range.setResEndDate(re);
				}
				saleDates.add(range);
			}
			result.setSaleDates(saleDates);
			if (firsts.size() > 0) {
				result.setFirstSaleDate(Collections.min(firsts));
			}
			if (lasts.size() > 0) {
				result.setLastSaleDate(Collections.max(lasts));
			}
			if (resfirsts.size() > 0) {
				result.setFirstResDate(Collections.min(resfirsts));
			}
			if (reslasts.size() > 0) {
				result.setLastResDate(Collections.max(reslasts));
			}
		}
		
		if (cat35s != null) {
			for (CategoryObject cat35o:cat35s) {
				AtpcoRecord3Cat35 cat35 = (AtpcoRecord3Cat35) cat35o.getCategory();
				for (AtpcoRecord3Cat35Ticketing ticketing:cat35.getTicketing()) {
					if (ticketing.getTour_car_value_code() != null && !ticketing.getTour_car_value_code().trim().isEmpty() && !result.getTourCode().contains(ticketing.getTour_car_value_code().trim())) {
						result.getTourCode().add(ticketing.getTour_car_value_code().trim());
					}
				}
			}
		}
		
		if (cat27s != null) {
			for (CategoryObject cat27o:cat27s) {
				AtpcoRecord3Cat27 cat27 = (AtpcoRecord3Cat27) cat27o.getCategory();
				if (cat27.getText_table_no_996() != null && !cat27.getText_table_no_996().trim().isEmpty()) {
					TextTable textTable996 = atpcoRecord3CategoryCustomRepository.findRecord3TextTable(cat27.getText_table_no_996().trim());
					String textTable = AtpcoDataConverterUtil.convertTextTableToText(textTable996);
					for (String word:textTable.split("\\W")) {
						word = word.replaceAll("[\n\r\t]", "").trim();
						if (word.startsWith("RZ") && !result.getTourCode().contains(word)) {
							result.getTourCode().add(word);
							break;
						}
					}
				}
			}
		}
		
		if (cat50s != null) {
			for (CategoryObject cat50o:cat50s) {
				AtpcoRecord3Cat50 cat50 = (AtpcoRecord3Cat50) cat50o.getCategory();
				if (cat50.getApplication_title() != null && !cat50.getApplication_title().trim().isEmpty() && !result.getCat50Title().contains(cat50.getApplication_title().trim())) {
					result.getCat50Title().add(cat50.getApplication_title().trim());
				}
			}
		}
		
		//Footnote attributes
		if (footnote14s != null) {
			List<AtpcoDateWrapper> travelDates = new ArrayList<>();
			List<Date> firsts = new ArrayList<>();
			List<Date> lasts = new ArrayList<>();
			for (CategoryObject footnote14:footnote14s) {
				AtpcoDateWrapper range = new AtpcoDateWrapper();
				AtpcoRecord3Cat14 cat14 = (AtpcoRecord3Cat14) footnote14.getCategory();
				Date s = DateUtil.convertObjectToDate(cat14.getTravel_dates_comm());
				if (s != null) {
					firsts.add(s);
					range.setStartDate(s);
				}
				Date e = DateUtil.convertObjectToDate(cat14.getTravel_dates_exp());
				if (e != null) {
					lasts.add(e);
					range.setEndDate(e);
				}
				Date c = DateUtil.convertObjectToDate(cat14.getTravel_dates_commence_complete());
				if (c != null) {
					range.setCompleteDate(c);
				}
				travelDates.add(range);
			}
			result.setTravelDates(travelDates);
			if (firsts.size() > 0) {
				result.setFirstTravelDate(Collections.min(firsts));
			}
			if (lasts.size() > 0) {
				result.setLastTravelDate(Collections.max(lasts));
			}
		}
		
		if (footnote15s != null) {
			List<AtpcoDateWrapper> saleDates = new ArrayList<>();
			List<Date> firsts = new ArrayList<>();
			List<Date> lasts = new ArrayList<>();
			List<Date> resfirsts = new ArrayList<>();
			List<Date> reslasts = new ArrayList<>();
			for (CategoryObject footnote15:footnote15s) {
				AtpcoDateWrapper range = new AtpcoDateWrapper();
				
				AtpcoRecord3Cat15 cat15 = (AtpcoRecord3Cat15) footnote15.getCategory();
				Date s = DateUtil.convertObjectToDate(cat15.getSales_dates_earliest_tktg());
				if (s != null) {
					firsts.add(s);
					range.setStartDate(s);
				}
				Date e = DateUtil.convertObjectToDate(cat15.getSales_dates_latest_tktg());
				if (e != null) {
					lasts.add(e);
					range.setEndDate(e);
				}
				Date rs = DateUtil.convertObjectToDate(cat15.getSales_dates_earliest_res());
				if (rs != null) {
					resfirsts.add(rs);
					range.setResStartDate(rs);
				}
				Date re = DateUtil.convertObjectToDate(cat15.getSales_dates_latest_res());
				if (re != null) {
					reslasts.add(re);
					range.setResEndDate(re);
				}
				saleDates.add(range);
			}
			result.setSaleDates(saleDates);
			if (firsts.size() > 0) {
				result.setFirstSaleDate(Collections.min(firsts));
			}
			if (lasts.size() > 0) {
				result.setLastSaleDate(Collections.max(lasts));
			}
			if (resfirsts.size() > 0) {
				result.setFirstResDate(Collections.min(resfirsts));
			}
			if (reslasts.size() > 0) {
				result.setLastResDate(Collections.max(reslasts));
			}
		}
		
		result.setFocusDate(focusDate);
		
		return result;
	}

	public SpecifiedConstructed convertMarketFare(WorkPackageFare fare, String wpObjectId, String fareId, String woId, String woName) {
		SpecifiedConstructed result = new SpecifiedConstructed();
		
		result.setFareId(fareId);
		result.setSource("M");
		result.setSc("S");
		result.setTariffNo("MKT");
		result.setTariffCode("MARKET");
		result.setCarrierCode(fare.getCarrier());
		result.setOriginCity(fare.getOrigin());
		result.setDestinationCity(fare.getDestination());
		result.setFareClassCode(fare.getFareBasis());
		result.setOwrt(fare.getTypeOfJourney());
		result.setFootnote(fare.getFootnote1());
		result.setRoutingNo(fare.getRtgno());
		result.setRuleNo(fare.getRuleno());
		result.setCurrencyCode(fare.getCurrency());
		result.setBaseAmount(TypeConverterUtil.convertStringToDouble(fare.getAmount()));
		result.setGlobalIndicator(fare.getGlobal());
		
		AtpcoDateWrapper saleDate = new AtpcoDateWrapper();
		saleDate.setStartDate(fare.getSaleStart());
		saleDate.setEndDate(fare.getSaleEnd());
		result.getSaleDates().add(saleDate);
		
		result.getBookingClass().add(fare.getBookingClass());
		result.getPaxType().add(fare.getPassengerType());
		result.setSeason(fare.getSeasonType());
		result.setFareType(fare.getFareType());
		result.setCabin(fare.getCabin());
		
		AtpcoDateWrapper travelDate = new AtpcoDateWrapper();
		travelDate.setStartDate(fare.getTravelStart());
		travelDate.setEndDate(fare.getTravelEnd());
		travelDate.setCompleteDate(fare.getTravelComplete());
		result.getTravelDates().add(travelDate);
		
		result.setWpId(woId);
		result.setWpObjectId(wpObjectId);
		result.setWpName(woName);
		
		return result;
	}

	public SpecifiedConstructed convertVoltrasFare(VoltrasFare vf) {
		SpecifiedConstructed result = new SpecifiedConstructed();
		
		result.setFareId(vf.getId());
		result.setSource("W");
		result.setSc("S");
		result.setTariffNo("WEB");
		result.setTariffCode("WEB FARE");
		
		result.setCarrierCode(vf.getCarrierCode());
		result.setOriginCity(vf.getOrigin());
		result.setDestinationCity(vf.getDestination());
		result.setOwrt(vf.getOwrt());
		result.setCurrencyCode(vf.getCurrency());
		result.setBaseAmount(TypeConverterUtil.convertStringToDouble(vf.getPrice().getLow() + ""));
		
		return result;
	}

	public AfdQueryAddOns convertAtpcoAddOn(AtpcoAddOn addOn) {
		AfdQueryAddOns result = new AfdQueryAddOns();
		
		result.setId(addOn.get_id());
		result.setSource("A");
		result.setTariffNo(addOn.getTar_no());
		
		if (result.getTariffNo() != null) {
			TariffNumber tn = tariffNumberRepository.findOneByTarNo(addOn.getTar_no());
			if (tn != null && tn.getTarCd() != null) {
				result.setTariffCode(tn.getTarCd());
			}
		}
		
		result.setCarrierCode(addOn.getCxr_code());
		result.setZone(addOn.getAdd_on_zone());
		result.setOriginCity(addOn.getMarket_origin());
		result.setOriginCountry(addOn.getCountry_code_origin());
		result.setDestinationCity(addOn.getMarket_destination());
		result.setDestinationCountry(addOn.getCountry_code_destination());
		result.setFareClassCode(addOn.getFare_class_cd());
		result.setOwrt(addOn.getOw_rt());
		result.setFootnote1(addOn.getFtnt());
		result.setRoutingNo(addOn.getRtg_no());
		
		if (addOn.getAdd_on() != null) {
			result.setCurrencyCode(addOn.getAdd_on().getAdd_on_cur_cd());
			result.setBaseAmount(addOn.getAdd_on().getAdd_on_amount().bigDecimalValue().doubleValue());
		}
		
		result.setEffectiveDate(addOn.getDates_tar_eff());
		result.setDiscontinueDate(addOn.getDates_disc());
		result.setGfsDate(addOn.getGfs_date());
		result.setGfsReference(addOn.getGfs_number());
		
		return result;
	}
}
