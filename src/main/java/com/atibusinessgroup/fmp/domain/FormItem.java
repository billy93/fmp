package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

public class FormItem implements Serializable{

	private static final long serialVersionUID = 5005831958053247766L;

    private String tag;

	private String label;

	private String type;

	private Object value;

	private String tooltip;

	@Field(value="tooltip_input")
	private String tooltip_input;

	private List<String> options = new ArrayList<String>();

	private List<Data> data = new ArrayList<Data>();

	@Field(value="validation_rules")
    private List<Rule> validation_rules = new ArrayList<Rule>();

	@Field("error")
    private List<String> error = new ArrayList<String>();

    @Field("errorAPF")
    private List<String> errorAPF = new ArrayList<String>();

    @Field("errorSabre")
    private List<String> errorSabre = new ArrayList<String>();

    @Field("errorAmadeus")
    private List<String> errorAmadeus = new ArrayList<String>();

    @Field("errors")
    private List<String> errors = new ArrayList<String>();
    
	@Field(value="form_items_child")
	private List<FormItem> form_items_child = new ArrayList<FormItem>();

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}


	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getTooltip_input() {
		return tooltip_input;
	}

	public void setTooltip_input(String tooltip_input) {
		this.tooltip_input = tooltip_input;
	}

	public List<Rule> getValidation_rules() {
		return validation_rules;
	}

	public void setValidation_rules(List<Rule> validation_rules) {
		this.validation_rules = validation_rules;
	}

	public List<FormItem> getForm_items_child() {
		return form_items_child;
	}

	public void setForm_items_child(List<FormItem> form_items_child) {
		this.form_items_child = form_items_child;
	}

	public List<String> getErrorAPF() {
		return errorAPF;
	}

	public void setErrorAPF(List<String> errorAPF) {
		this.errorAPF = errorAPF;
	}

	public List<String> getErrorSabre() {
		return errorSabre;
	}

	public void setErrorSabre(List<String> errorSabre) {
		this.errorSabre = errorSabre;
	}

	public List<String> getErrorAmadeus() {
		return errorAmadeus;
	}

	public void setErrorAmadeus(List<String> errorAmadeus) {
		this.errorAmadeus = errorAmadeus;
	}

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "FormItem [tag=" + tag + ", label=" + label + ", type=" + type + ", value=" + value + ", tooltip="
				+ tooltip + ", tooltip_input=" + tooltip_input + ", options=" + options + ", data=" + data
				+ ", validation_rules=" + validation_rules + ", errorAPF=" + errorAPF + ", errorSabre=" + errorSabre
				+ ", errorAmadeus=" + errorAmadeus + ", form_items_child=" + form_items_child + "]";
	}
}
