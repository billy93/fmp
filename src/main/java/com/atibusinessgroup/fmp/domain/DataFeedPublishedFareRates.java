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
@Document(collection = "pfc_rates_data_feeds")
public class DataFeedPublishedFareRates implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("pos_country")
    private String posCountry;

    @Field("from_td")
    private String travelDateFrom;

    @Field("to_td")
    private String travelDateTo;

    @Field("rate")
    private String rate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPosCountry() {
		return posCountry;
	}

	public void setPosCountry(String posCountry) {
		this.posCountry = posCountry;
	}

	public String getTravelDateFrom() {
		return travelDateFrom;
	}

	public void setTravelDateFrom(String travelDateFrom) {
		this.travelDateFrom = travelDateFrom;
	}

	public String getTravelDateTo() {
		return travelDateTo;
	}

	public void setTravelDateTo(String travelDateTo) {
		this.travelDateTo = travelDateTo;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataFeedPublishedFareRates rMDataFeeds = (DataFeedPublishedFareRates) o;
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
		return "DataFeedPublishedFareRates [id=" + id + ", posCountry=" + posCountry + ", travelDateFrom="
				+ travelDateFrom + ", travelDateTo=" + travelDateTo + ", rate=" + rate + "]";
	}


   	
}
