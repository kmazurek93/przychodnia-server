package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.creation;

import javax.validation.constraints.NotNull;

/**
 * Created by kmazu on 06.07.2016.
 */
public class AddressCreationWebModel {


    @NotNull
    private String street;
    @NotNull
    private String house;
    private String apartment;
    @NotNull
    private String postCode;
    @NotNull
    private String city;
    @NotNull
    private String country;
    private String province;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
