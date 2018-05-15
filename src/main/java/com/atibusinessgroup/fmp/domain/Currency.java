package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Currency.
 */

@Document(collection = "currency")
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("currency_code")
    private String currency_code;

    @NotNull
    @Field("currency")
    private String currency;

    @NotNull
    @Field("location_currencies")
    private String location_currencies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocation_currencies() {
        return location_currencies;
    }

    public void setLocation_currencies(String location_currencies) {
        this.location_currencies = location_currencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Currency currency = (Currency) o;
        if(currency.id == null || id == null) {
            return false;
        }
        
        return Objects.equals(id, currency.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Currency{" +
            "id=" + id +
            ", currency_code='" + currency_code + "'" +
            ", currency='" + currency + "'" +
            ", location_currencies='" + location_currencies + "'" +
            '}';
    }
}
