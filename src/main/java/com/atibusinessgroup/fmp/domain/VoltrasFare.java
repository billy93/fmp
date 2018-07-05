package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "voltras_fare")
public class VoltrasFare implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    private String id;
    
    @Field("destination")
    private String destination;
    
    @Field("origin")
    private String origin;
    
    @Field("capture_date_time")
    private Object captureDateTime;
    
    @Field("tax")
    private Decimal128 tax;
    
    @Field("carrier_code")
    private String carrierCode;
    
    @Field("travel_time")
    private String travelTime;
    
    @Field("batch_date")
    private Date batchDate;
    
    @Field("depart_date_time")
    private Object departureDateTime;
    
    @Field("direction")
    private String direction;
    
    @Field("flight_number")
    private String flightNo;
    
    @Field("price")
    private Decimal128 price;
    
    @Field("rount_trip")
    private String owrt;
    
    @Field("return_date_time")
    private Object returnDateTime;
    
    @Field("currency")
    private String currency;
    
    @Field("site_name")
    private String siteName;
    
    @Field("connection")
    private String connection;
    
    @Field("total_price")
    private Decimal128 totalPrice;
    
    @Field("batch_number")
    private int batchNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Object getCaptureDateTime() {
		return captureDateTime;
	}

	public void setCaptureDateTime(Object captureDateTime) {
		this.captureDateTime = captureDateTime;
	}

	public Decimal128 getTax() {
		return tax;
	}

	public void setTax(Decimal128 tax) {
		this.tax = tax;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public Object getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(Object departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public Decimal128 getPrice() {
		return price;
	}

	public void setPrice(Decimal128 price) {
		this.price = price;
	}

	public String getOwrt() {
		return owrt;
	}

	public void setOwrt(String owrt) {
		this.owrt = owrt;
	}

	public Object getReturnDateTime() {
		return returnDateTime;
	}

	public void setReturnDateTime(Object returnDateTime) {
		this.returnDateTime = returnDateTime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public Decimal128 getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Decimal128 totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchDate == null) ? 0 : batchDate.hashCode());
		result = prime * result + batchNo;
		result = prime * result + ((captureDateTime == null) ? 0 : captureDateTime.hashCode());
		result = prime * result + ((carrierCode == null) ? 0 : carrierCode.hashCode());
		result = prime * result + ((connection == null) ? 0 : connection.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((departureDateTime == null) ? 0 : departureDateTime.hashCode());
		result = prime * result + ((destination == null) ? 0 : destination.hashCode());
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((flightNo == null) ? 0 : flightNo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result + ((owrt == null) ? 0 : owrt.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((returnDateTime == null) ? 0 : returnDateTime.hashCode());
		result = prime * result + ((siteName == null) ? 0 : siteName.hashCode());
		result = prime * result + ((tax == null) ? 0 : tax.hashCode());
		result = prime * result + ((totalPrice == null) ? 0 : totalPrice.hashCode());
		result = prime * result + ((travelTime == null) ? 0 : travelTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		VoltrasFare other = (VoltrasFare) obj;
		if (batchDate == null) {
			if (other.batchDate != null) {
				return false;
			}
		} else if (!batchDate.equals(other.batchDate)) {
			return false;
		}
		if (batchNo != other.batchNo) {
			return false;
		}
		if (captureDateTime == null) {
			if (other.captureDateTime != null) {
				return false;
			}
		} else if (!captureDateTime.equals(other.captureDateTime)) {
			return false;
		}
		if (carrierCode == null) {
			if (other.carrierCode != null) {
				return false;
			}
		} else if (!carrierCode.equals(other.carrierCode)) {
			return false;
		}
		if (connection == null) {
			if (other.connection != null) {
				return false;
			}
		} else if (!connection.equals(other.connection)) {
			return false;
		}
		if (currency == null) {
			if (other.currency != null) {
				return false;
			}
		} else if (!currency.equals(other.currency)) {
			return false;
		}
		if (departureDateTime == null) {
			if (other.departureDateTime != null) {
				return false;
			}
		} else if (!departureDateTime.equals(other.departureDateTime)) {
			return false;
		}
		if (destination == null) {
			if (other.destination != null) {
				return false;
			}
		} else if (!destination.equals(other.destination)) {
			return false;
		}
		if (direction == null) {
			if (other.direction != null) {
				return false;
			}
		} else if (!direction.equals(other.direction)) {
			return false;
		}
		if (flightNo == null) {
			if (other.flightNo != null) {
				return false;
			}
		} else if (!flightNo.equals(other.flightNo)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (origin == null) {
			if (other.origin != null) {
				return false;
			}
		} else if (!origin.equals(other.origin)) {
			return false;
		}
		if (owrt == null) {
			if (other.owrt != null) {
				return false;
			}
		} else if (!owrt.equals(other.owrt)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (returnDateTime == null) {
			if (other.returnDateTime != null) {
				return false;
			}
		} else if (!returnDateTime.equals(other.returnDateTime)) {
			return false;
		}
		if (siteName == null) {
			if (other.siteName != null) {
				return false;
			}
		} else if (!siteName.equals(other.siteName)) {
			return false;
		}
		if (tax == null) {
			if (other.tax != null) {
				return false;
			}
		} else if (!tax.equals(other.tax)) {
			return false;
		}
		if (totalPrice == null) {
			if (other.totalPrice != null) {
				return false;
			}
		} else if (!totalPrice.equals(other.totalPrice)) {
			return false;
		}
		if (travelTime == null) {
			if (other.travelTime != null) {
				return false;
			}
		} else if (!travelTime.equals(other.travelTime)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "VoltrasFare [id=" + id + ", destination=" + destination + ", origin=" + origin + ", captureDateTime="
				+ captureDateTime + ", tax=" + tax + ", carrierCode=" + carrierCode + ", travelTime=" + travelTime
				+ ", batchDate=" + batchDate + ", departureDateTime=" + departureDateTime + ", direction=" + direction
				+ ", flightNo=" + flightNo + ", price=" + price + ", owrt=" + owrt + ", returnDateTime="
				+ returnDateTime + ", currency=" + currency + ", siteName=" + siteName + ", connection=" + connection
				+ ", totalPrice=" + totalPrice + ", batchNo=" + batchNo + "]";
	}
}
