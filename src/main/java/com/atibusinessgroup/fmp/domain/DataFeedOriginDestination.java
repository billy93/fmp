package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A RMDataFeeds.
 */
@Document(collection = "data_feed_origin_destination")
public class DataFeedOriginDestination implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Field("origin_airport")
	private String origAirport;

	@Field("destination_airport")
	private String destAirport;

	@Field("origin_city")
	private String origCity;

	@Field("destination_city")
	private String destCity;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DataFeedOriginDestination rMDataFeeds = (DataFeedOriginDestination) o;
		if (rMDataFeeds.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), rMDataFeeds.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrigAirport() {
		return origAirport;
	}

	public void setOrigAirport(String origAirport) {
		this.origAirport = origAirport;
	}

	public String getDestAirport() {
		return destAirport;
	}

	public void setDestAirport(String destAirport) {
		this.destAirport = destAirport;
	}

	public String getOrigCity() {
		return origCity;
	}

	public void setOrigCity(String origCity) {
		this.origCity = origCity;
	}

	public String getDestCity() {
		return destCity;
	}

	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	@Override
	public String toString() {
		return "DataFeedOriginDestination [id=" + id + ", origAirport=" + origAirport + ", destAirport=" + destAirport
				+ ", origCity=" + origCity + ", destCity=" + destCity + "]";
	}

}
