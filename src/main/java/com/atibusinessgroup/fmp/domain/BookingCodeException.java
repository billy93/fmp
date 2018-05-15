package com.atibusinessgroup.fmp.domain;

import java.util.ArrayList;
import java.util.List;

public class BookingCodeException {
	private List<Form> forms = new ArrayList<Form>();

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}
}
