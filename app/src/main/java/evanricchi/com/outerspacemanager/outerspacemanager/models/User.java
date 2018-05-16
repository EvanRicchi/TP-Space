package evanricchi.com.outerspacemanager.outerspacemanager.models;

import android.util.Log;

public class User {
    private String email;
    private String username;
    private String password;

    public User(String pEmail, String pUsername, String pPassword) {
        this.email = pEmail;
        this.username = pUsername;
        this.password = pPassword;
    }

    public User(String pUsername, String pPassword) {
        this.username = pUsername;
        this.password = pPassword;
    }

    public void log(){
        Log.e("Email",this.email);
        Log.e("Username",this.username);
        Log.e("Password",this.password);
    }
}
