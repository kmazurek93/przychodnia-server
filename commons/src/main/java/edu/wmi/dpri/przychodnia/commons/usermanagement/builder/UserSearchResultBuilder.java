package edu.wmi.dpri.przychodnia.commons.usermanagement.builder;

import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserDataSimpleModel;
import edu.wmi.dpri.przychodnia.commons.usermanagement.webmodel.UserSearchResult;

import java.util.List;

/**
 * Created by lupus on 14.11.16.
 */
public final class UserSearchResultBuilder {
    private Integer amountOfPages;
    private List<UserDataSimpleModel> users;

    private UserSearchResultBuilder() {
    }

    public static UserSearchResultBuilder anUserSearchResult() {
        return new UserSearchResultBuilder();
    }

    public UserSearchResultBuilder withAmountOfPages(Integer amounOfPages) {
        this.amountOfPages = amounOfPages;
        return this;
    }

    public UserSearchResultBuilder withUsers(List<UserDataSimpleModel> users) {
        this.users = users;
        return this;
    }

    public UserSearchResultBuilder but() {
        return anUserSearchResult().withAmountOfPages(amountOfPages).withUsers(users);
    }

    public UserSearchResult build() {
        UserSearchResult userSearchResult = new UserSearchResult();
        userSearchResult.setAmountOfPages(amountOfPages);
        userSearchResult.setUsers(users);
        return userSearchResult;
    }
}
