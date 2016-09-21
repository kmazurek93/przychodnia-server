package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.AddressWebModel;

/**
 * Created by kmazu on 06.07.2016.
 */
public final class AddressWebModelBuilder {
    private Integer id;
    private String country;
    private String province;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String postCode;

    private AddressWebModelBuilder() {
    }

    public static AddressWebModelBuilder anAddressWebModel() {
        return new AddressWebModelBuilder();
    }

    public AddressWebModelBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public AddressWebModelBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public AddressWebModelBuilder withProvince(String province) {
        this.province = province;
        return this;
    }

    public AddressWebModelBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressWebModelBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressWebModelBuilder withHouse(String house) {
        this.house = house;
        return this;
    }

    public AddressWebModelBuilder withApartment(String apartment) {
        this.apartment = apartment;
        return this;
    }

    public AddressWebModelBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public AddressWebModel build() {
        AddressWebModel addressWebModel = new AddressWebModel();
        addressWebModel.setId(id);
        addressWebModel.setCountry(country);
        addressWebModel.setProvince(province);
        addressWebModel.setCity(city);
        addressWebModel.setStreet(street);
        addressWebModel.setHouse(house);
        addressWebModel.setApartment(apartment);
        addressWebModel.setPostCode(postCode);
        return addressWebModel;
    }
}
