package com.ifmo.iad;

import org.mindrot.jbcrypt.BCrypt;

public class Authentication {
    private String username;
    private String password;

    public Authentication() {
        setUsername("myuser");
        setPassword("mypass");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String getPassword() {
        return this.password;
    }

    public Boolean authenticate(String username, String password){
        this.username = username;
//        this.password = BCrypt.hashpw(password,"ilovevaadin");
//        BCrypt.checkpw()
        RestClient rest = new RestClient();

        return rest.login(this.username,password);
    }

}
