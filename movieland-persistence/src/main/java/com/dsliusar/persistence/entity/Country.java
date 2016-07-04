package com.dsliusar.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(value = { "countryId" })
public class Country{
    @JsonIgnore
    private int countryId;
    private String countryName;

    @Override
    public String toString() {
        return "Countries{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country countries = (Country) o;

        if (countryId != countries.countryId) return false;
        return !(countryName != null ? !countryName.equals(countries.countryName) : countries.countryName != null);

    }

    @Override
    public int hashCode() {
        int result = countryId;
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
