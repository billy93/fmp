package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A City.
 */
@Document(collection = "city")
public class City implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Field("city_name")
	private String cityName;

	@Field("city_code")
	private String cityCode;

	@Field("country_name")
	private String countryName;

	@Field("country_code")
	private String countryCode;

	@Field("airport_code")
	private String airportCode;

	@Field("airport_name")
	private String airportName;

	@Field("worldarea_code")
	private String worldareaCode;

	public City() {
		// TODO Auto-generated constructor stub
	}
	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public City cityName(String cityName) {
		this.cityName = cityName;
		return this;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public City cityCode(String cityCode) {
		this.cityCode = cityCode;
		return this;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public City countryName(String countryName) {
		this.countryName = countryName;
		return this;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public City countryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getWorldareaCode() {
		return worldareaCode;
	}

	public void setWorldareaCode(String worldareaCode) {
		this.worldareaCode = worldareaCode;
	}

	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		City city = (City) o;
		if (city.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), city.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", cityCode=" + cityCode + ", countryName=" + countryName
				+ ", countryCode=" + countryCode + ", airportCode=" + airportCode + ", airportName=" + airportName
				+ ", worldareaCode=" + worldareaCode + "]";
	}

}
