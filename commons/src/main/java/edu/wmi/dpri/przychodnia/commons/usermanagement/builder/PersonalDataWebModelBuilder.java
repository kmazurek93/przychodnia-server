package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.PersonalDataWebModel;

/**
 * Created by lupus on 14.11.16.
 */
public final class PersonalDataWebModelBuilder {
    private String pesel;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long birthDate;
    private String birthPlace;
    private String idNumber;
    private Integer idTypeNo;
    private Integer sexId;
    private String telephone;

    private PersonalDataWebModelBuilder() {
    }

    public static PersonalDataWebModelBuilder aPersonalDataWebModel() {
        return new PersonalDataWebModelBuilder();
    }

    public PersonalDataWebModelBuilder withPesel(String pesel) {
        this.pesel = pesel;
        return this;
    }

    public PersonalDataWebModelBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonalDataWebModelBuilder withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public PersonalDataWebModelBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonalDataWebModelBuilder withBirthDate(Long birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public PersonalDataWebModelBuilder withBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public PersonalDataWebModelBuilder withIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public PersonalDataWebModelBuilder withIdTypeNo(Integer idTypeNo) {
        this.idTypeNo = idTypeNo;
        return this;
    }

    public PersonalDataWebModelBuilder withSexId(Integer sexId) {
        this.sexId = sexId;
        return this;
    }

    public PersonalDataWebModelBuilder withTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public PersonalDataWebModel build() {
        PersonalDataWebModel personalDataWebModel = new PersonalDataWebModel();
        personalDataWebModel.setPesel(pesel);
        personalDataWebModel.setFirstName(firstName);
        personalDataWebModel.setMiddleName(middleName);
        personalDataWebModel.setLastName(lastName);
        personalDataWebModel.setBirthDate(birthDate);
        personalDataWebModel.setBirthPlace(birthPlace);
        personalDataWebModel.setIdNumber(idNumber);
        personalDataWebModel.setIdTypeNo(idTypeNo);
        personalDataWebModel.setSexId(sexId);
        personalDataWebModel.setTelephone(telephone);
        return personalDataWebModel;
    }
}
