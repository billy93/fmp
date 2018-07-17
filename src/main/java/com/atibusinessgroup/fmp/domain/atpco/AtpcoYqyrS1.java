package com.atibusinessgroup.fmp.domain.atpco;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.dto.YqyrFiller;
import com.atibusinessgroup.fmp.domain.dto.YqyrJourneyGeoSpec;
import com.atibusinessgroup.fmp.domain.dto.YqyrPos;
import com.atibusinessgroup.fmp.domain.dto.YqyrPot;
import com.atibusinessgroup.fmp.domain.dto.YqyrSector;
import com.atibusinessgroup.fmp.domain.dto.YqyrService;
import com.atibusinessgroup.fmp.domain.dto.YqyrTicketDates;

@Document(collection = "atpco_yqyr_s1")
public class AtpcoYqyrS1 {

	@Id
	private String id;
	
	@Field("sector")
	private YqyrSector sector;

	@Field("passenger_filler")
    private String passenger_filler;

	@Field("fare_basis_code")
    private String fare_basis_code;

	@Field("pot")
    private YqyrPot pot;

	@Field("pos")
    private YqyrPos pos;

	@Field("cxr_code")
    private String cxr_code;

	@Field("mcn")
    private String mcn;

	@Field("seq_no")
    private String seq_no;

	@Field("batch_ci")
    private String batch_ci;

	@Field("eqp")
    private String eqp;

	@Field("filler")
    private YqyrFiller filler;

	@Field("batch_no")
    private int batch_no;

	@Field("rbd")
    private String rbd;

	@Field("txt_tbl_no_196")
    private String txt_tbl_no_196;

	@Field("batch_number")
    private String batch_number;

	@Field("jrny_geo_spec")
    private YqyrJourneyGeoSpec jrny_geo_spec;

	@Field("batch_date")
    private String batch_date;

	@Field("rtn_to_orig")
    private String rtn_to_orig;

	@Field("passenger_type")
    private String passenger_type;

	@Field("service_type_tax")
    private String service_type_tax;

	@Field("ticket_dates")
    private YqyrTicketDates ticket_dates;

	@Field("rec_type")
    private String rec_type;

	@Field("service_type_sub_code")
    private String service_type_sub_code;

	@Field("service")
    private YqyrService service;

	@Field("cxr_tbl_186")
    private String cxr_tbl_186;

	@Field("service_type_filler")
    private String service_type_filler;

	@Field("carrier_appl_tbl_190")
    private String carrier_appl_tbl_190;

	public YqyrSector getSector() {
		return sector;
	}

	public void setSector(YqyrSector sector) {
		this.sector = sector;
	}

	public String getPassenger_filler() {
		return passenger_filler;
	}

	public void setPassenger_filler(String passenger_filler) {
		this.passenger_filler = passenger_filler;
	}

	public String getFare_basis_code() {
		return fare_basis_code;
	}

	public void setFare_basis_code(String fare_basis_code) {
		this.fare_basis_code = fare_basis_code;
	}

	public YqyrPot getPot() {
		return pot;
	}

	public void setPot(YqyrPot pot) {
		this.pot = pot;
	}

	public YqyrPos getPos() {
		return pos;
	}

	public void setPos(YqyrPos pos) {
		this.pos = pos;
	}

	public String getCxr_code() {
		return cxr_code;
	}

	public void setCxr_code(String cxr_code) {
		this.cxr_code = cxr_code;
	}

	public String getMcn() {
		return mcn;
	}

	public void setMcn(String mcn) {
		this.mcn = mcn;
	}

	public String getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}

	public String getBatch_ci() {
		return batch_ci;
	}

	public void setBatch_ci(String batch_ci) {
		this.batch_ci = batch_ci;
	}

	public String getEqp() {
		return eqp;
	}

	public void setEqp(String eqp) {
		this.eqp = eqp;
	}

	public YqyrFiller getFiller() {
		return filler;
	}

	public void setFiller(YqyrFiller filler) {
		this.filler = filler;
	}

	public int getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(int batch_no) {
		this.batch_no = batch_no;
	}

	public String getRbd() {
		return rbd;
	}

	public void setRbd(String rbd) {
		this.rbd = rbd;
	}

	public String getTxt_tbl_no_196() {
		return txt_tbl_no_196;
	}

	public void setTxt_tbl_no_196(String txt_tbl_no_196) {
		this.txt_tbl_no_196 = txt_tbl_no_196;
	}

	public String getBatch_number() {
		return batch_number;
	}

	public void setBatch_number(String batch_number) {
		this.batch_number = batch_number;
	}

	public YqyrJourneyGeoSpec getJrny_geo_spec() {
		return jrny_geo_spec;
	}

	public void setJrny_geo_spec(YqyrJourneyGeoSpec jrny_geo_spec) {
		this.jrny_geo_spec = jrny_geo_spec;
	}

	public String getBatch_date() {
		return batch_date;
	}

	public void setBatch_date(String batch_date) {
		this.batch_date = batch_date;
	}

	public String getRtn_to_orig() {
		return rtn_to_orig;
	}

	public void setRtn_to_orig(String rtn_to_orig) {
		this.rtn_to_orig = rtn_to_orig;
	}

	public String getPassenger_type() {
		return passenger_type;
	}

	public void setPassenger_type(String passenger_type) {
		this.passenger_type = passenger_type;
	}

	public String getService_type_tax() {
		return service_type_tax;
	}

	public void setService_type_tax(String service_type_tax) {
		this.service_type_tax = service_type_tax;
	}

	public YqyrTicketDates getTicket_dates() {
		return ticket_dates;
	}

	public void setTicket_dates(YqyrTicketDates ticket_dates) {
		this.ticket_dates = ticket_dates;
	}

	public String getRec_type() {
		return rec_type;
	}

	public void setRec_type(String rec_type) {
		this.rec_type = rec_type;
	}

	public String getService_type_sub_code() {
		return service_type_sub_code;
	}

	public void setService_type_sub_code(String service_type_sub_code) {
		this.service_type_sub_code = service_type_sub_code;
	}

	public YqyrService getService() {
		return service;
	}

	public void setService(YqyrService service) {
		this.service = service;
	}

	public String getCxr_tbl_186() {
		return cxr_tbl_186;
	}

	public void setCxr_tbl_186(String cxr_tbl_186) {
		this.cxr_tbl_186 = cxr_tbl_186;
	}

	public String getService_type_filler() {
		return service_type_filler;
	}

	public void setService_type_filler(String service_type_filler) {
		this.service_type_filler = service_type_filler;
	}

	public String getCarrier_appl_tbl_190() {
		return carrier_appl_tbl_190;
	}

	public void setCarrier_appl_tbl_190(String carrier_appl_tbl_190) {
		this.carrier_appl_tbl_190 = carrier_appl_tbl_190;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batch_ci == null) ? 0 : batch_ci.hashCode());
		result = prime * result + ((batch_date == null) ? 0 : batch_date.hashCode());
		result = prime * result + batch_no;
		result = prime * result + ((batch_number == null) ? 0 : batch_number.hashCode());
		result = prime * result + ((carrier_appl_tbl_190 == null) ? 0 : carrier_appl_tbl_190.hashCode());
		result = prime * result + ((cxr_code == null) ? 0 : cxr_code.hashCode());
		result = prime * result + ((cxr_tbl_186 == null) ? 0 : cxr_tbl_186.hashCode());
		result = prime * result + ((eqp == null) ? 0 : eqp.hashCode());
		result = prime * result + ((fare_basis_code == null) ? 0 : fare_basis_code.hashCode());
		result = prime * result + ((filler == null) ? 0 : filler.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jrny_geo_spec == null) ? 0 : jrny_geo_spec.hashCode());
		result = prime * result + ((mcn == null) ? 0 : mcn.hashCode());
		result = prime * result + ((passenger_filler == null) ? 0 : passenger_filler.hashCode());
		result = prime * result + ((passenger_type == null) ? 0 : passenger_type.hashCode());
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((pot == null) ? 0 : pot.hashCode());
		result = prime * result + ((rbd == null) ? 0 : rbd.hashCode());
		result = prime * result + ((rec_type == null) ? 0 : rec_type.hashCode());
		result = prime * result + ((rtn_to_orig == null) ? 0 : rtn_to_orig.hashCode());
		result = prime * result + ((sector == null) ? 0 : sector.hashCode());
		result = prime * result + ((seq_no == null) ? 0 : seq_no.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + ((service_type_filler == null) ? 0 : service_type_filler.hashCode());
		result = prime * result + ((service_type_sub_code == null) ? 0 : service_type_sub_code.hashCode());
		result = prime * result + ((service_type_tax == null) ? 0 : service_type_tax.hashCode());
		result = prime * result + ((ticket_dates == null) ? 0 : ticket_dates.hashCode());
		result = prime * result + ((txt_tbl_no_196 == null) ? 0 : txt_tbl_no_196.hashCode());
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
		AtpcoYqyrS1 other = (AtpcoYqyrS1) obj;
		if (batch_ci == null) {
			if (other.batch_ci != null) {
				return false;
			}
		} else if (!batch_ci.equals(other.batch_ci)) {
			return false;
		}
		if (batch_date == null) {
			if (other.batch_date != null) {
				return false;
			}
		} else if (!batch_date.equals(other.batch_date)) {
			return false;
		}
		if (batch_no != other.batch_no) {
			return false;
		}
		if (batch_number == null) {
			if (other.batch_number != null) {
				return false;
			}
		} else if (!batch_number.equals(other.batch_number)) {
			return false;
		}
		if (carrier_appl_tbl_190 == null) {
			if (other.carrier_appl_tbl_190 != null) {
				return false;
			}
		} else if (!carrier_appl_tbl_190.equals(other.carrier_appl_tbl_190)) {
			return false;
		}
		if (cxr_code == null) {
			if (other.cxr_code != null) {
				return false;
			}
		} else if (!cxr_code.equals(other.cxr_code)) {
			return false;
		}
		if (cxr_tbl_186 == null) {
			if (other.cxr_tbl_186 != null) {
				return false;
			}
		} else if (!cxr_tbl_186.equals(other.cxr_tbl_186)) {
			return false;
		}
		if (eqp == null) {
			if (other.eqp != null) {
				return false;
			}
		} else if (!eqp.equals(other.eqp)) {
			return false;
		}
		if (fare_basis_code == null) {
			if (other.fare_basis_code != null) {
				return false;
			}
		} else if (!fare_basis_code.equals(other.fare_basis_code)) {
			return false;
		}
		if (filler == null) {
			if (other.filler != null) {
				return false;
			}
		} else if (!filler.equals(other.filler)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (jrny_geo_spec == null) {
			if (other.jrny_geo_spec != null) {
				return false;
			}
		} else if (!jrny_geo_spec.equals(other.jrny_geo_spec)) {
			return false;
		}
		if (mcn == null) {
			if (other.mcn != null) {
				return false;
			}
		} else if (!mcn.equals(other.mcn)) {
			return false;
		}
		if (passenger_filler == null) {
			if (other.passenger_filler != null) {
				return false;
			}
		} else if (!passenger_filler.equals(other.passenger_filler)) {
			return false;
		}
		if (passenger_type == null) {
			if (other.passenger_type != null) {
				return false;
			}
		} else if (!passenger_type.equals(other.passenger_type)) {
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
		if (rbd == null) {
			if (other.rbd != null) {
				return false;
			}
		} else if (!rbd.equals(other.rbd)) {
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
		if (sector == null) {
			if (other.sector != null) {
				return false;
			}
		} else if (!sector.equals(other.sector)) {
			return false;
		}
		if (seq_no == null) {
			if (other.seq_no != null) {
				return false;
			}
		} else if (!seq_no.equals(other.seq_no)) {
			return false;
		}
		if (service == null) {
			if (other.service != null) {
				return false;
			}
		} else if (!service.equals(other.service)) {
			return false;
		}
		if (service_type_filler == null) {
			if (other.service_type_filler != null) {
				return false;
			}
		} else if (!service_type_filler.equals(other.service_type_filler)) {
			return false;
		}
		if (service_type_sub_code == null) {
			if (other.service_type_sub_code != null) {
				return false;
			}
		} else if (!service_type_sub_code.equals(other.service_type_sub_code)) {
			return false;
		}
		if (service_type_tax == null) {
			if (other.service_type_tax != null) {
				return false;
			}
		} else if (!service_type_tax.equals(other.service_type_tax)) {
			return false;
		}
		if (ticket_dates == null) {
			if (other.ticket_dates != null) {
				return false;
			}
		} else if (!ticket_dates.equals(other.ticket_dates)) {
			return false;
		}
		if (txt_tbl_no_196 == null) {
			if (other.txt_tbl_no_196 != null) {
				return false;
			}
		} else if (!txt_tbl_no_196.equals(other.txt_tbl_no_196)) {
			return false;
		}
		return true;
	}
}

