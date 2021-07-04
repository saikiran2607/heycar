package com.heycar.heycardealerslisting.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "LISTING")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Dealer dealer;
    @JsonProperty("code")
    private String code;
    @JsonProperty("make")
    private String make;
    @JsonProperty("model")
    private String model;
    @JsonProperty("kW")
    private Long kiloWatts;
    @JsonProperty("year")
    private Long year;
    @JsonProperty("color")
    private String color;
    @JsonProperty("price")
    private Long price;
    
    

    @Override
    public boolean equals(Object o) 
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Listing listing = (Listing) o;
        return Objects.equals(id, listing.id) &&
                Objects.equals(dealer, listing.dealer) &&
                Objects.equals(code, listing.code) &&
                Objects.equals(make, listing.make) &&
                Objects.equals(model, listing.model) &&
                Objects.equals(kiloWatts, listing.kiloWatts) &&
                Objects.equals(year, listing.year) &&
                Objects.equals(color, listing.color) &&
                Objects.equals(price, listing.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        Long dealerId = dealer == null ? 0 : dealer.getId();
        return "Listing{" +
                "id=" + id +
                ", dealerId=" + dealerId +
                ", code='" + code + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", kiloWatts=" + kiloWatts +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
