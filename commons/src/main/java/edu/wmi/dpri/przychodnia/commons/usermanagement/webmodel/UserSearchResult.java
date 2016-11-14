package edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel;

import java.util.List;

/**
 * Created by lupus on 14.11.16.
 */
public class UserSearchResult {
    private Integer amountOfPages;
    private List<UserDataSimpleModel> users;

    public Integer getAmountOfPages() {
        return amountOfPages;
    }

    public void setAmountOfPages(Integer amountOfPages) {
        this.amountOfPages = amountOfPages;
    }

    public List<UserDataSimpleModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserDataSimpleModel> users) {
        this.users = users;
    }
}
