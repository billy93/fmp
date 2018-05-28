package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A MasterTariff.
 */
@Document(collection = "master_tariff")
public class MasterTariffDomestic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("fare_tariff")
    private TariffObject fareTariff;
    
    @Field("rule_tariff")
    private TariffObject ruleTariff;
    
    @Field("general_rules")
    private List<TariffObject> generalRules = new ArrayList<>();
    
    @Field("arbitary_tariff")
    private TariffObject arbitaryTariff;
    
    @Field("routing_tariff")
    private TariffObject routingTariffName;
    
    @Field("gfs_global_area")
    private String gfsGlobalArea;

    @Field("area")
    private String area;
    
    

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
   
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TariffObject getFareTariff() {
		return fareTariff;
	}

	public void setFareTariff(TariffObject fareTariff) {
		this.fareTariff = fareTariff;
	}

	public TariffObject getRuleTariff() {
		return ruleTariff;
	}

	public void setRuleTariff(TariffObject ruleTariff) {
		this.ruleTariff = ruleTariff;
	}

	public List<TariffObject> getGeneralRules() {
		return generalRules;
	}

	public void setGeneralRules(List<TariffObject> generalRules) {
		this.generalRules = generalRules;
	}

	public TariffObject getArbitaryTariff() {
		return arbitaryTariff;
	}

	public void setArbitaryTariff(TariffObject arbitaryTariff) {
		this.arbitaryTariff = arbitaryTariff;
	}

	public TariffObject getRoutingTariffName() {
		return routingTariffName;
	}

	public void setRoutingTariffName(TariffObject routingTariffName) {
		this.routingTariffName = routingTariffName;
	}

	public String getGfsGlobalArea() {
		return gfsGlobalArea;
	}

	public void setGfsGlobalArea(String gfsGlobalArea) {
		this.gfsGlobalArea = gfsGlobalArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MasterTariffDomestic masterTariff = (MasterTariffDomestic) o;
        if (masterTariff.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), masterTariff.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "MasterTariffDomestic [id=" + id + ", fareTariff=" + fareTariff + ", ruleTariff=" + ruleTariff
				+ ", generalRules=" + generalRules + ", arbitaryTariff=" + arbitaryTariff + ", routingTariffName="
				+ routingTariffName + ", gfsGlobalArea=" + gfsGlobalArea + ", area=" + area + "]";
	}

	

	

    
}
