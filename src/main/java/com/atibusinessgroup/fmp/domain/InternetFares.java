package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A InternetFares.
 */
@Document(collection = "internet_fares")
public class InternetFares implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("site_name")
    private String siteName;

    @Field("travel_time")
    private String travelTime;

    @NotNull
    @Field("depart_date_time")
    private ZonedDateTime departDateTime;

    @Field("return_date_time")
    private ZonedDateTime returnDateTime;

    @Field("price")
    private BigDecimal price;

    @Field("tax")
    private BigDecimal tax;

    @NotNull
    @Field("total_price")
    private BigDecimal totalPrice;

    @Field("flight_number")
    private String flightNumber;

    @NotNull
    @Field("capture_date_time")
    private ZonedDateTime captureDateTime;

    @Field("flight_connection")
    private String flightConnection;

    @Field("round_trip")
    private String roundTrip;

    @NotNull
    @Field("carrier_code")
    private String carrierCode;

    @NotNull
    @Field("flight_origin")
    private String flightOrigin;

    @NotNull
    @Field("flight_destination")
    private String flightDestination;

    @NotNull
    @Field("currency")
    private String currency;

    @NotNull
    @Field("flight_direction")
    private String flightDirection;

    @Field("fare_basis_code")
    private String fareBasisCode;
    
    @Field("amount_oal")
    private BigDecimal amountOal;
    
    @Field("ownSelisih")
    private BigDecimal ownSelisih;
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    
    
    public BigDecimal getOwnSelisih() {
		return ownSelisih;
	}



	public void setOwnSelisih(BigDecimal ownSelisih) {
		this.ownSelisih = ownSelisih;
	}



	public void setId(String id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public InternetFares siteName(String siteName) {
        this.siteName = siteName;
        return this;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public InternetFares travelTime(String travelTime) {
        this.travelTime = travelTime;
        return this;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public ZonedDateTime getDepartDateTime() {
        return departDateTime;
    }

    public InternetFares departDateTime(ZonedDateTime departDateTime) {
        this.departDateTime = departDateTime;
        return this;
    }

    public void setDepartDateTime(ZonedDateTime departDateTime) {
        this.departDateTime = departDateTime;
    }

    public ZonedDateTime getReturnDateTime() {
        return returnDateTime;
    }

    public InternetFares returnDateTime(ZonedDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
        return this;
    }

    public void setReturnDateTime(ZonedDateTime returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public InternetFares price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public InternetFares tax(BigDecimal tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public InternetFares totalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public InternetFares flightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public ZonedDateTime getCaptureDateTime() {
        return captureDateTime;
    }

    public InternetFares captureDateTime(ZonedDateTime captureDateTime) {
        this.captureDateTime = captureDateTime;
        return this;
    }

    public void setCaptureDateTime(ZonedDateTime captureDateTime) {
        this.captureDateTime = captureDateTime;
    }

    public String getFlightConnection() {
        return flightConnection;
    }

    public InternetFares flightConnection(String flightConnection) {
        this.flightConnection = flightConnection;
        return this;
    }

    public void setFlightConnection(String flightConnection) {
        this.flightConnection = flightConnection;
    }

    public String getRoundTrip() {
        return roundTrip;
    }

    public InternetFares roundTrip(String roundTrip) {
        this.roundTrip = roundTrip;
        return this;
    }

    public void setRoundTrip(String roundTrip) {
        this.roundTrip = roundTrip;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public InternetFares carrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
        return this;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public String getFlightOrigin() {
        return flightOrigin;
    }

    public InternetFares flightOrigin(String flightOrigin) {
        this.flightOrigin = flightOrigin;
        return this;
    }

    public void setFlightOrigin(String flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public InternetFares flightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
        return this;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }

    public String getCurrency() {
        return currency;
    }

    public InternetFares currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFlightDirection() {
        return flightDirection;
    }

    public InternetFares flightDirection(String flightDirection) {
        this.flightDirection = flightDirection;
        return this;
    }

    public void setFlightDirection(String flightDirection) {
        this.flightDirection = flightDirection;
    }

    public String getFareBasisCode() {
        return fareBasisCode;
    }

    public InternetFares fareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
        return this;
    }

    public void setFareBasisCode(String fareBasisCode) {
        this.fareBasisCode = fareBasisCode;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove


	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InternetFares internetFares = (InternetFares) o;
        if (internetFares.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), internetFares.getId());
    }

	
    public BigDecimal getAmountOal() {
		return amountOal;
	}

	public void setAmountOal(BigDecimal amountOal) {
		this.amountOal = amountOal;
	}

	@Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "InternetFares [id=" + id + ", siteName=" + siteName + ", travelTime=" + travelTime + ", departDateTime="
				+ departDateTime + ", returnDateTime=" + returnDateTime + ", price=" + price + ", tax=" + tax
				+ ", totalPrice=" + totalPrice + ", flightNumber=" + flightNumber + ", captureDateTime="
				+ captureDateTime + ", flightConnection=" + flightConnection + ", roundTrip=" + roundTrip
				+ ", carrierCode=" + carrierCode + ", flightOrigin=" + flightOrigin + ", flightDestination="
				+ flightDestination + ", currency=" + currency + ", flightDirection=" + flightDirection
				+ ", fareBasisCode=" + fareBasisCode + ", amountOal=" + amountOal + "]";
	}

    
}
