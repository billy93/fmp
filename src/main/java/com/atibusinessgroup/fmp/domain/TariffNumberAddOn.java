package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TariffNumberAddOn.
 */
@Document(collection = "tariff_number_add_on")
public class TariffNumberAddOn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("tar_no_add_on")
    private String tarNo;

    @Field("tar_cd_add_on")
    private String tarCd;

    @Field("global_add_on")
    private String global;

    @Field("description_add_on")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public String getTarNo() {
		return tarNo;
	}

	public void setTarNo(String tarNo) {
		this.tarNo = tarNo;
	}

	public String getTarCd() {
		return tarCd;
	}

	public void setTarCd(String tarCd) {
		this.tarCd = tarCd;
	}

	public String getGlobal() {
		return global;
	}

	public void setGlobal(String global) {
		this.global = global;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TariffNumberAddOn tariffNumberAddOn = (TariffNumberAddOn) o;
        if (tariffNumberAddOn.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tariffNumberAddOn.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "TariffNumberAddOn [id=" + id + ", tarNo=" + tarNo + ", tarCd=" + tarCd + ", global=" + global
				+ ", description=" + description + "]";
	}

    
}
