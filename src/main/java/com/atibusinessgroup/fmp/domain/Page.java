package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable{

	private static final long serialVersionUID = -14676031839472122L;

	private RuleException exception;
	private List<Form> forms = new ArrayList<Form>();
	private String pagesIndex;
	private Group group;

	public RuleException getException() {
		return exception;
	}

	public void setException(RuleException exception) {
		this.exception = exception;
	}

	public List<Form> getForms() {
		return forms;
	}
	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

	public String getPagesIndex() {
		return pagesIndex;
	}

	public void setPagesIndex(String pagesIndex) {
		this.pagesIndex = pagesIndex;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
