package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

/**
 * Created by kmazu on 06.07.2016.
 */
public class UserDataSimpleModel {
    private Long userId;
    private String pesel;
    private String name;
    private String email;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
