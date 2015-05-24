package model.entity;

import util.Enum.UserAuthority;

/**
 * Created by hdd on 12/05/15.
 */
public class User extends Entity {
    private String username;
    private UserAuthority authority;
    private boolean availability;

    public User() {
        super();
        availability=true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(UserAuthority authority) {
        this.authority = authority;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
