package edu.wmi.dpri.przychodnia.server.security.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by lupus on 22.10.16.
 */
public class UserContext {
    private String username;
    private Long userId;
    private List<GrantedAuthority> authorities;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private UserContext(String username, Long userId, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
        this.userId = userId;
    }

    public static UserContext create(String username, Long userId, List<GrantedAuthority> authorities) {
        if (StringUtils.isBlank(username) || userId == null)
            throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, userId, authorities);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
