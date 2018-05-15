package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Form.
 */

@Document(collection = "form")
public class Form  extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("header_name")
    private String header_name;

    @Field("tag")
    private String tag;

    @Field("form_items")
    private List<FormItem> form_items = new ArrayList<FormItem>();

    @Field("form_items_child")
    private List<FormItem> form_items_child = new ArrayList<FormItem>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHeader_name() {
		return header_name;
	}

	public void setHeader_name(String header_name) {
		this.header_name = header_name;
	}

	public List<FormItem> getForm_items() {
		return form_items;
	}

	public void setForm_items(List<FormItem> form_items) {
		this.form_items = form_items;
	}

	
	public List<FormItem> getForm_items_child() {
		return form_items_child;
	}

	public void setForm_items_child(List<FormItem> form_items_child) {
		this.form_items_child = form_items_child;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Form form = (Form) o;
        if(form.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, form.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Form{" +
            "id=" + id +
            ", headerName='" + header_name + "'" +
            ", tag='" + tag + "'" +
            ", formItems='" + form_items + "'" +
            '}';
    }
}
