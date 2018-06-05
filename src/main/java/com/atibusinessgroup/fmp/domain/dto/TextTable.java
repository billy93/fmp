package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class TextTable {
	
	@Field("_id")
	private String tableNo;
	
	@Field("text")
	private List<String> text = new ArrayList<>();

	public String getTableNo() {
		return tableNo;
	}

	public void setTableNo(String tableNo) {
		this.tableNo = tableNo;
	}

	public List<String> getText() {
		return text;
	}

	public void setText(List<String> text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tableNo == null) ? 0 : tableNo.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		TextTable other = (TextTable) obj;
		if (tableNo == null) {
			if (other.tableNo != null) {
				return false;
			}
		} else if (!tableNo.equals(other.tableNo)) {
			return false;
		}
		if (text == null) {
			if (other.text != null) {
				return false;
			}
		} else if (!text.equals(other.text)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TextTable [tableNo=" + tableNo + ", text=" + text + "]";
	}
}
