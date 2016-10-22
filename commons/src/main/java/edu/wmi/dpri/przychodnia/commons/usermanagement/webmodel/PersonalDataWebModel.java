package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import javax.validation.constraints.NotNull;

/**
 * Created by lupus on 18.10.16.
 */
public class PersonalDataWebModel {
    @NotNull
    private String pesel;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private Long birthDate;
    @NotNull
    private String birthPlace;
    @NotNull
    private String idNumber;
    @NotNull
    private Integer idTypeNo;
    @NotNull
    private Integer sexId;

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getIdTypeNo() {
        return idTypeNo;
    }

    public void setIdTypeNo(Integer idTypeNo) {
        this.idTypeNo = idTypeNo;
    }

    public Integer getSexId() {
        return sexId;
    }

    public void setSexId(Integer sexId) {
        this.sexId = sexId;
    }
}
