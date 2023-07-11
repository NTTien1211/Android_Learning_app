package com.hqsoft.esales.doancuoiky;

import android.text.TextUtils;
import android.util.Patterns;

public class Account {
    String email ;
    String pass ;

    public Account(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

   public boolean isvaliEmail(){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
   }
   public boolean isvaliPass(){
        return !TextUtils.isEmpty(pass) && pass.length()>=6;
   }
}
