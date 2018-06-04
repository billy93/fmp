package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;

public class AtpcoFareAfdQueryWithRecords {

	@Field("atpco_fare")
	private AtpcoFare atpcoFare;
	
	@Field("atpco_record_1")
	private List<AtpcoRecord1> atpcoRecord1 = new ArrayList<>();

	@Field("atpco_record_2")
	private List<AtpcoRecord2GroupByCatNo> atpcoRecord2 = new ArrayList<>();
	
	@Field("footnote_record")
	private List<AtpcoFootnoteRecord2GroupByCatNo> footnoteRecord = new ArrayList<>();
	
	public AtpcoFare getAtpcoFare() {
		return atpcoFare;
	}

	public void setAtpcoFare(AtpcoFare atpcoFare) {
		this.atpcoFare = atpcoFare;
	}

	public List<AtpcoRecord1> getAtpcoRecord1() {
		return atpcoRecord1;
	}

	public void setAtpcoRecord1(List<AtpcoRecord1> atpcoRecord1) {
		this.atpcoRecord1 = atpcoRecord1;
	}
	
	public List<AtpcoFootnoteRecord2GroupByCatNo> getFootnoteRecord() {
		return footnoteRecord;
	}

	public void setFootnoteRecord(List<AtpcoFootnoteRecord2GroupByCatNo> footnoteRecord) {
		this.footnoteRecord = footnoteRecord;
	}
	
	public List<AtpcoRecord2GroupByCatNo> getAtpcoRecord2() {
		return atpcoRecord2;
	}

	public void setAtpcoRecord2(List<AtpcoRecord2GroupByCatNo> atpcoRecord2) {
		this.atpcoRecord2 = atpcoRecord2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atpcoFare == null) ? 0 : atpcoFare.hashCode());
		result = prime * result + ((atpcoRecord1 == null) ? 0 : atpcoRecord1.hashCode());
		result = prime * result + ((atpcoRecord2 == null) ? 0 : atpcoRecord2.hashCode());
		result = prime * result + ((footnoteRecord == null) ? 0 : footnoteRecord.hashCode());
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
		AtpcoFareAfdQueryWithRecords other = (AtpcoFareAfdQueryWithRecords) obj;
		if (atpcoFare == null) {
			if (other.atpcoFare != null) {
				return false;
			}
		} else if (!atpcoFare.equals(other.atpcoFare)) {
			return false;
		}
		if (atpcoRecord1 == null) {
			if (other.atpcoRecord1 != null) {
				return false;
			}
		} else if (!atpcoRecord1.equals(other.atpcoRecord1)) {
			return false;
		}
		if (atpcoRecord2 == null) {
			if (other.atpcoRecord2 != null) {
				return false;
			}
		} else if (!atpcoRecord2.equals(other.atpcoRecord2)) {
			return false;
		}
		if (footnoteRecord == null) {
			if (other.footnoteRecord != null) {
				return false;
			}
		} else if (!footnoteRecord.equals(other.footnoteRecord)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoFareAfdQueryWithRecords [atpcoFare=" + atpcoFare + ", atpcoRecord1=" + atpcoRecord1
				+ ", atpcoRecord2=" + atpcoRecord2 + ", footnoteRecord=" + footnoteRecord + "]";
	}
}
