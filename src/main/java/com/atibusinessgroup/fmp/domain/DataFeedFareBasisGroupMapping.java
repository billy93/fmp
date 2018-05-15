package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * A RMDataFeeds.
 */
@Document(collection = "fare_basis_data_feeds")
public class DataFeedFareBasisGroupMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("origin_city")
    private String originCity;

    @Field("origin_country")
    private String originCountry;

    @Field("destination_city")
    private String destinationCity;

    @Field("destination_country")
    private String destinationCountry;

    @Field("rbd")
    private String rbd;

    @Field("fare_code")
    private String fareBasisCode;

    @Field("fare_group")
    private String fareBasisGroup;

    @Field("priority")
    private String priority;  

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getRbd() {
		return rbd;
	}

	public void setRbd(String rbd) {
		this.rbd = rbd;
	}

	public String getFareBasisCode() {
		return fareBasisCode;
	}

	public void setFareBasisCode(String fareBasisCode) {
		this.fareBasisCode = fareBasisCode;
	}

	public String getFareBasisGroup() {
		return fareBasisGroup;
	}

	public void setFareBasisGroup(String fareBasisGroup) {
		this.fareBasisGroup = fareBasisGroup;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataFeedFareBasisGroupMapping rMDataFeeds = (DataFeedFareBasisGroupMapping) o;
        if (rMDataFeeds.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rMDataFeeds.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
	public String toString() {
		return "DataFeedFareBasisGroupMapping [id=" + id + ", originCity=" + originCity + ", originCountry="
				+ originCountry + ", destinationCity=" + destinationCity + ", destinationCountry=" + destinationCountry
				+ ", rbd=" + rbd + ", fareBasisCode=" + fareBasisCode + ", fareBasisGroup=" + fareBasisGroup
				+ ", priority=" + priority + "]";
	}
	
}
