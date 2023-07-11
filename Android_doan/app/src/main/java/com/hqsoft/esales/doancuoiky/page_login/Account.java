package com.hqsoft.esales.doancuoiky.page_login;

import android.text.TextUtils;
import android.util.Patterns;

public class Account {
    String user ;
    String email ;

    public Account(String user, String email) {
        this.user = user;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
