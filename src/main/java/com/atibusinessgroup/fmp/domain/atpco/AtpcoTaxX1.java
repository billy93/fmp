package com.atibusinessgroup.fmp.domain.atpco;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.TaxCalculation;
import com.atibusinessgroup.fmp.domain.dto.TaxFiller;
import com.atibusinessgroup.fmp.domain.dto.TaxHistorySale;
import com.atibusinessgroup.fmp.domain.dto.TaxHistoryTravel;
import com.atibusinessgroup.fmp.domain.dto.TaxInformation;
import com.atibusinessgroup.fmp.domain.dto.TaxJourneyGeoSpec;
import com.atibusinessgroup.fmp.domain.dto.TaxPod;
import com.atibusinessgroup.fmp.domain.dto.TaxPointLoc;
import com.atibusinessgroup.fmp.domain.dto.TaxPointQualifying;
import com.atibusinessgroup.fmp.domain.dto.TaxPos;
import com.atibusinessgroup.fmp.domain.dto.TaxPot;
import com.atibusinessgroup.fmp.domain.dto.TaxSaleDate;
import com.atibusinessgroup.fmp.domain.dto.TaxSectorDetail;
import com.atibusinessgroup.fmp.domain.dto.TaxServiceBaggage;
import com.atibusinessgroup.fmp.domain.dto.TaxTicketValue;
import com.atibusinessgroup.fmp.domain.dto.TaxTravelDate;

@Document(collection = "atpco_yqyr_s1")
public class AtpcoTaxX1 {

	@Id
	private String _id;
	
	@Field("travel_dates")
	private TaxTravelDate travel_dates;

	@Field("output_type_indicator")
    private String output_type_indicator;

	@Field("security_tbl_no_183")
    private String security_tbl_no_183;

	@Field("ticketed_point_tag")
    private String ticketed_point_tag;

	@Field("tax_carrier")
    private String tax_carrier;

	@Field("paid_by_third_party")
    private String paid_by_third_party;

	@Field("txt_tbl_no_164")
    private String txt_tbl_no_164;

	@Field("service_baggage")
    private TaxServiceBaggage service_baggage;

	@Field("cxr_flt_tbl_no_186")
    private String cxr_flt_tbl_no_186;

	@Field("pot")
    private TaxPot pot;

	@Field("pos")
    private TaxPos pos;

	@Field("sale_dates")
    private TaxSaleDate sale_dates;

	@Field("tax_unit_tag")
    private String tax_unit_tag;

	@Field("seq_no")
    private String seq_no;

	@Field("percent_flat_tag")
    private String percent_flat_tag;

	@Field("exampt_tag")
    private String exampt_tag;

	@Field("calc_order")
    private String calc_order;

	@Field("ratd_date")
    private String ratd_date;

	@Field("tax_remittance_id")
    private String tax_remittance_id;

	@Field("filler")
    private TaxFiller filler;

	@Field("historic_travel")
    private TaxHistoryTravel historic_travel;

	@Field("ticket_value")
    private TaxTicketValue ticket_value;

	@Field("pod")
    private TaxPod pod;

	@Field("historic_sale")
    private TaxHistorySale historic_sale;

	@Field("batch_number")
    private int batch_number;

	@Field("tax_type")
    private String tax_type;

	@Field("jrny_geo_spec")
    private TaxJourneyGeoSpec jrny_geo_spec;

	@Field("tax_point_loc")
    private TaxPointLoc tax_point_loc;

	@Field("ptc_tbl_no_169")
    private String ptc_tbl_no_169;

	@Field("batch_date")
    private Date batch_date;

	@Field("carrier_appl_tbl_no_190")
    private String carrier_appl_tbl_no_190;

	@Field("rtn_to_orig")
    private String rtn_to_orig;

	@Field("tax_point_qualifying")
    private TaxPointQualifying tax_point_qualifying;

	@Field("nation")
    private String nation;

	@Field("tax_point_tag")
    private String tax_point_tag;

	@Field("tax")
    private TaxInformation tax;

	@Field("tax_code")
    private String tax_code;

	@Field("rec_type")
    private String rec_type;

	@Field("tax_calculation")
    private TaxCalculation tax_calculation;

	@Field("sector_detail")
    private TaxSectorDetail sector_detail;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public TaxTravelDate getTravel_dates() {
		return travel_dates;
	}

	public void setTravel_dates(TaxTravelDate travel_dates) {
		this.travel_dates = travel_dates;
	}

	public String getOutput_type_indicator() {
		return output_type_indicator;
	}

	public void setOutput_type_indicator(String output_type_indicator) {
		this.output_type_indicator = output_type_indicator;
	}

	public String getSecurity_tbl_no_183() {
		return security_tbl_no_183;
	}

	public void setSecurity_tbl_no_183(String security_tbl_no_183) {
		this.security_tbl_no_183 = security_tbl_no_183;
	}

	public String getTicketed_point_tag() {
		return ticketed_point_tag;
	}

	public void setTicketed_point_tag(String ticketed_point_tag) {
		this.ticketed_point_tag = ticketed_point_tag;
	}

	public String getTax_carrier() {
		return tax_carrier;
	}

	public void setTax_carrier(String tax_carrier) {
		this.tax_carrier = tax_carrier;
	}

	public String getPaid_by_third_party() {
		return paid_by_third_party;
	}

	public void setPaid_by_third_party(String paid_by_third_party) {
		this.paid_by_third_party = paid_by_third_party;
	}

	public String getTxt_tbl_no_164() {
		return txt_tbl_no_164;
	}

	public void setTxt_tbl_no_164(String txt_tbl_no_164) {
		this.txt_tbl_no_164 = txt_tbl_no_164;
	}

	public TaxServiceBaggage getService_baggage() {
		return service_baggage;
	}

	public void setService_baggage(TaxServiceBaggage service_baggage) {
		this.service_baggage = service_baggage;
	}

	public String getCxr_flt_tbl_no_186() {
		return cxr_flt_tbl_no_186;
	}

	public void setCxr_flt_tbl_no_186(String cxr_flt_tbl_no_186) {
		this.cxr_flt_tbl_no_186 = cxr_flt_tbl_no_186;
	}

	public TaxPot getPot() {
		return pot;
	}

	public void setPot(TaxPot pot) {
		this.pot = pot;
	}

	public TaxPos getPos() {
		return pos;
	}

	public void setPos(TaxPos pos) {
		this.pos = pos;
	}

	public TaxSaleDate getSale_dates() {
		return sale_dates;
	}

	public void setSale_dates(TaxSaleDate sale_dates) {
		this.sale_dates = sale_dates;
	}

	public String getTax_unit_tag() {
		return tax_unit_tag;
	}

	public void setTax_unit_tag(String tax_unit_tag) {
		this.tax_unit_tag = tax_unit_tag;
	}

	public String getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}

	public String getPercent_flat_tag() {
		return percent_flat_tag;
	}

	public void setPercent_flat_tag(String percent_flat_tag) {
		this.percent_flat_tag = percent_flat_tag;
	}

	public String getExampt_tag() {
		return exampt_tag;
	}

	public void setExampt_tag(String exampt_tag) {
		this.exampt_tag = exampt_tag;
	}

	public String getCalc_order() {
		return calc_order;
	}

	public void setCalc_order(String calc_order) {
		this.calc_order = calc_order;
	}

	public String getRatd_date() {
		return ratd_date;
	}

	public void setRatd_date(String ratd_date) {
		this.ratd_date = ratd_date;
	}

	public String getTax_remittance_id() {
		return tax_remittance_id;
	}

	public void setTax_remittance_id(String tax_remittance_id) {
		this.tax_remittance_id = tax_remittance_id;
	}

	public TaxFiller getFiller() {
		return filler;
	}

	public void setFiller(TaxFiller filler) {
		this.filler = filler;
	}

	public TaxHistoryTravel getHistoric_travel() {
		return historic_travel;
	}

	public void setHistoric_travel(TaxHistoryTravel historic_travel) {
		this.historic_travel = historic_travel;
	}

	public TaxTicketValue getTicket_value() {
		return ticket_value;
	}

	public void setTicket_value(TaxTicketValue ticket_value) {
		this.ticket_value = ticket_value;
	}

	public TaxPod getPod() {
		return pod;
	}

	public void setPod(TaxPod pod) {
		this.pod = pod;
	}

	public TaxHistorySale getHistoric_sale() {
		return historic_sale;
	}

	public void setHistoric_sale(TaxHistorySale historic_sale) {
		this.historic_sale = historic_sale;
	}

	public int getBatch_number() {
		return batch_number;
	}

	public void setBatch_number(int batch_number) {
		this.batch_number = batch_number;
	}

	public String getTax_type() {
		return tax_type;
	}

	public void setTax_type(String tax_type) {
		this.tax_type = tax_type;
	}

	public TaxJourneyGeoSpec getJrny_geo_spec() {
		return jrny_geo_spec;
	}

	public void setJrny_geo_spec(TaxJourneyGeoSpec jrny_geo_spec) {
		this.jrny_geo_spec = jrny_geo_spec;
	}

	public TaxPointLoc getTax_point_loc() {
		return tax_point_loc;
	}

	public void setTax_point_loc(TaxPointLoc tax_point_loc) {
		this.tax_point_loc = tax_point_loc;
	}

	public String getPtc_tbl_no_169() {
		return ptc_tbl_no_169;
	}

	public void setPtc_tbl_no_169(String ptc_tbl_no_169) {
		this.ptc_tbl_no_169 = ptc_tbl_no_169;
	}

	public Date getBatch_date() {
		return batch_date;
	}

	public void setBatch_date(Date batch_date) {
		this.batch_date = batch_date;
	}

	public String getCarrier_appl_tbl_no_190() {
		return carrier_appl_tbl_no_190;
	}

	public void setCarrier_appl_tbl_no_190(String carrier_appl_tbl_no_190) {
		this.carrier_appl_tbl_no_190 = carrier_appl_tbl_no_190;
	}

	public String getRtn_to_orig() {
		return rtn_to_orig;
	}

	public void setRtn_to_orig(String rtn_to_orig) {
		this.rtn_to_orig = rtn_to_orig;
	}

	public TaxPointQualifying getTax_point_qualifying() {
		return tax_point_qualifying;
	}

	public void setTax_point_qualifying(TaxPointQualifying tax_point_qualifying) {
		this.tax_point_qualifying = tax_point_qualifying;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getTax_point_tag() {
		return tax_point_tag;
	}

	public void setTax_point_tag(String tax_point_tag) {
		this.tax_point_tag = tax_point_tag;
	}

	public TaxInformation getTax() {
		return tax;
	}

	public void setTax(TaxInformation tax) {
		this.tax = tax;
	}

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public TaxCalculation getTax_calculation() {
		return tax_calculation;
	}

	public void setTax_calculation(TaxCalculation tax_calculation) {
		this.tax_calculation = tax_calculation;
	}

	public TaxSectorDetail getSector_detail() {
		return sector_detail;
	}

	public void setSector_detail(TaxSectorDetail sector_detail) {
		this.sector_detail = sector_detail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((batch_date == null) ? 0 : batch_date.hashCode());
		result = prime * result + batch_number;
		result = prime * result + ((calc_order == null) ? 0 : calc_order.hashCode());
		result = prime * result + ((carrier_appl_tbl_no_190 == null) ? 0 : carrier_appl_tbl_no_190.hashCode());
		result = prime * result + ((cxr_flt_tbl_no_186 == null) ? 0 : cxr_flt_tbl_no_186.hashCode());
		result = prime * result + ((exampt_tag == null) ? 0 : exampt_tag.hashCode());
		result = prime * result + ((filler == null) ? 0 : filler.hashCode());
		result = prime * result + ((historic_sale == null) ? 0 : historic_sale.hashCode());
		result = prime * result + ((historic_travel == null) ? 0 : historic_travel.hashCode());
		result = prime * result + ((jrny_geo_spec == null) ? 0 : jrny_geo_spec.hashCode());
		result = prime * result + ((nation == null) ? 0 : nation.hashCode());
		result = prime * result + ((output_type_indicator == null) ? 0 : output_type_indicator.hashCode());
		result = prime * result + ((paid_by_third_party == null) ? 0 : paid_by_third_party.hashCode());
		result = prime * result + ((percent_flat_tag == null) ? 0 : percent_flat_tag.hashCode());
		result = prime * result + ((pod == null) ? 0 : pod.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((pot == null) ? 0 : pot.hashCode());
		result = prime * result + ((ptc_tbl_no_169 == null) ? 0 : ptc_tbl_no_169.hashCode());
		result = prime * result + ((ratd_date == null) ? 0 : ratd_date.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((rtn_to_orig == null) ? 0 : rtn_to_orig.hashCode());
		result = prime * result + ((sale_dates == null) ? 0 : sale_dates.hashCode());
		result = prime * result + ((sector_detail == null) ? 0 : sector_detail.hashCode());
		result = prime * result + ((security_tbl_no_183 == null) ? 0 : security_tbl_no_183.hashCode());
		result = prime * result + ((seq_no == null) ? 0 : seq_no.hashCode());
		result = prime * result + ((service_baggage == null) ? 0 : service_baggage.hashCode());
		result = prime * result + ((tax == null) ? 0 : tax.hashCode());
		result = prime * result + ((tax_calculation == null) ? 0 : tax_calculation.hashCode());
		result = prime * result + ((tax_carrier == null) ? 0 : tax_carrier.hashCode());
		result = prime * result + ((tax_code == null) ? 0 : tax_code.hashCode());
		result = prime * result + ((tax_point_loc == null) ? 0 : tax_point_loc.hashCode());
		result = prime * result + ((tax_point_qualifying == null) ? 0 : tax_point_qualifying.hashCode());
		result = prime * result + ((tax_point_tag == null) ? 0 : tax_point_tag.hashCode());
		result = prime * result + ((tax_remittance_id == null) ? 0 : tax_remittance_id.hashCode());
		result = prime * result + ((tax_type == null) ? 0 : tax_type.hashCode());
		result = prime * result + ((tax_unit_tag == null) ? 0 : tax_unit_tag.hashCode());
		result = prime * result + ((ticket_value == null) ? 0 : ticket_value.hashCode());
		result = prime * result + ((ticketed_point_tag == null) ? 0 : ticketed_point_tag.hashCode());
		result = prime * result + ((travel_dates == null) ? 0 : travel_dates.hashCode());
		result = prime * result + ((txt_tbl_no_164 == null) ? 0 : txt_tbl_no_164.hashCode());
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
		AtpcoTaxX1 other = (AtpcoTaxX1) obj;
		if (_id == null) {
			if (other._id != null) {
				return false;
			}
		} else if (!_id.equals(other._id)) {
			return false;
		}
		if (batch_date == null) {
			if (other.batch_date != null) {
				return false;
			}
		} else if (!batch_date.equals(other.batch_date)) {
			return false;
		}
		if (batch_number != other.batch_number) {
			return false;
		}
		if (calc_order == null) {
			if (other.calc_order != null) {
				return false;
			}
		} else if (!calc_order.equals(other.calc_order)) {
			return false;
		}
		if (carrier_appl_tbl_no_190 == null) {
			if (other.carrier_appl_tbl_no_190 != null) {
				return false;
			}
		} else if (!carrier_appl_tbl_no_190.equals(other.carrier_appl_tbl_no_190)) {
			return false;
		}
		if (cxr_flt_tbl_no_186 == null) {
			if (other.cxr_flt_tbl_no_186 != null) {
				return false;
			}
		} else if (!cxr_flt_tbl_no_186.equals(other.cxr_flt_tbl_no_186)) {
			return false;
		}
		if (exampt_tag == null) {
			if (other.exampt_tag != null) {
				return false;
			}
		} else if (!exampt_tag.equals(other.exampt_tag)) {
			return false;
		}
		if (filler == null) {
			if (other.filler != null) {
				return false;
			}
		} else if (!filler.equals(other.filler)) {
			return false;
		}
		if (historic_sale == null) {
			if (other.historic_sale != null) {
				return false;
			}
		} else if (!historic_sale.equals(other.historic_sale)) {
			return false;
		}
		if (historic_travel == null) {
			if (other.historic_travel != null) {
				return false;
			}
		} else if (!historic_travel.equals(other.historic_travel)) {
			return false;
		}
		if (jrny_geo_spec == null) {
			if (other.jrny_geo_spec != null) {
				return false;
			}
		} else if (!jrny_geo_spec.equals(other.jrny_geo_spec)) {
			return false;
		}
		if (nation == null) {
			if (other.nation != null) {
				return false;
			}
		} else if (!nation.equals(other.nation)) {
			return false;
		}
		if (output_type_indicator == null) {
			if (other.output_type_indicator != null) {
				return false;
			}
		} else if (!output_type_indicator.equals(other.output_type_indicator)) {
			return false;
		}
		if (paid_by_third_party == null) {
			if (other.paid_by_third_party != null) {
				return false;
			}
		} else if (!paid_by_third_party.equals(other.paid_by_third_party)) {
			return false;
		}
		if (percent_flat_tag == null) {
			if (other.percent_flat_tag != null) {
				return false;
			}
		} else if (!percent_flat_tag.equals(other.percent_flat_tag)) {
			return false;
		}
		if (pod == null) {
			if (other.pod != null) {
				return false;
			}
		} else if (!pod.equals(other.pod)) {
			return false;
		}
		if (pos == null) {
			if (other.pos != null) {
				return false;
			}
		} else if (!pos.equals(other.pos)) {
			return false;
		}
		if (pot == null) {
			if (other.pot != null) {
				return false;
			}
		} else if (!pot.equals(other.pot)) {
			return false;
		}
		if (ptc_tbl_no_169 == null) {
			if (other.ptc_tbl_no_169 != null) {
				return false;
			}
		} else if (!ptc_tbl_no_169.equals(other.ptc_tbl_no_169)) {
			return false;
		}
		if (ratd_date == null) {
			if (other.ratd_date != null) {
				return false;
			}
		} else if (!ratd_date.equals(other.ratd_date)) {
			return false;
		}
		if (rec_type == null) {
			if (other.rec_type != null) {
				return false;
			}
		} else if (!rec_type.equals(other.rec_type)) {
			return false;
		}
		if (rtn_to_orig == null) {
			if (other.rtn_to_orig != null) {
				return false;
			}
		} else if (!rtn_to_orig.equals(other.rtn_to_orig)) {
			return false;
		}
		if (sale_dates == null) {
			if (other.sale_dates != null) {
				return false;
			}
		} else if (!sale_dates.equals(other.sale_dates)) {
			return false;
		}
		if (sector_detail == null) {
			if (other.sector_detail != null) {
				return false;
			}
		} else if (!sector_detail.equals(other.sector_detail)) {
			return false;
		}
		if (security_tbl_no_183 == null) {
			if (other.security_tbl_no_183 != null) {
				return false;
			}
		} else if (!security_tbl_no_183.equals(other.security_tbl_no_183)) {
			return false;
		}
		if (seq_no == null) {
			if (other.seq_no != null) {
				return false;
			}
		} else if (!seq_no.equals(other.seq_no)) {
			return false;
		}
		if (service_baggage == null) {
			if (other.service_baggage != null) {
				return false;
			}
		} else if (!service_baggage.equals(other.service_baggage)) {
			return false;
		}
		if (tax == null) {
			if (other.tax != null) {
				return false;
			}
		} else if (!tax.equals(other.tax)) {
			return false;
		}
		if (tax_calculation == null) {
			if (other.tax_calculation != null) {
				return false;
			}
		} else if (!tax_calculation.equals(other.tax_calculation)) {
			return false;
		}
		if (tax_carrier == null) {
			if (other.tax_carrier != null) {
				return false;
			}
		} else if (!tax_carrier.equals(other.tax_carrier)) {
			return false;
		}
		if (tax_code == null) {
			if (other.tax_code != null) {
				return false;
			}
		} else if (!tax_code.equals(other.tax_code)) {
			return false;
		}
		if (tax_point_loc == null) {
			if (other.tax_point_loc != null) {
				return false;
			}
		} else if (!tax_point_loc.equals(other.tax_point_loc)) {
			return false;
		}
		if (tax_point_qualifying == null) {
			if (other.tax_point_qualifying != null) {
				return false;
			}
		} else if (!tax_point_qualifying.equals(other.tax_point_qualifying)) {
			return false;
		}
		if (tax_point_tag == null) {
			if (other.tax_point_tag != null) {
				return false;
			}
		} else if (!tax_point_tag.equals(other.tax_point_tag)) {
			return false;
		}
		if (tax_remittance_id == null) {
			if (other.tax_remittance_id != null) {
				return false;
			}
		} else if (!tax_remittance_id.equals(other.tax_remittance_id)) {
			return false;
		}
		if (tax_type == null) {
			if (other.tax_type != null) {
				return false;
			}
		} else if (!tax_type.equals(other.tax_type)) {
			return false;
		}
		if (tax_unit_tag == null) {
			if (other.tax_unit_tag != null) {
				return false;
			}
		} else if (!tax_unit_tag.equals(other.tax_unit_tag)) {
			return false;
		}
		if (ticket_value == null) {
			if (other.ticket_value != null) {
				return false;
			}
		} else if (!ticket_value.equals(other.ticket_value)) {
			return false;
		}
		if (ticketed_point_tag == null) {
			if (other.ticketed_point_tag != null) {
				return false;
			}
		} else if (!ticketed_point_tag.equals(other.ticketed_point_tag)) {
			return false;
		}
		if (travel_dates == null) {
			if (other.travel_dates != null) {
				return false;
			}
		} else if (!travel_dates.equals(other.travel_dates)) {
			return false;
		}
		if (txt_tbl_no_164 == null) {
			if (other.txt_tbl_no_164 != null) {
				return false;
			}
		} else if (!txt_tbl_no_164.equals(other.txt_tbl_no_164)) {
			return false;
		}
		return true;
	}
}

