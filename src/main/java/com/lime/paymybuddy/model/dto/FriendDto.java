package com.lime.paymybuddy.model.dto;

import com.lime.paymybuddy.model.DaoUser;

public class FriendDto {
    private DaoUser user;
    private String friendEmail;

    public DaoUser getUser() {
        return user;
    }

    public void setUser(DaoUser user) {
        this.user = user;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }
}
