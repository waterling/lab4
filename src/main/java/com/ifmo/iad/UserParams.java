package com.ifmo.iad;

public class UserParams {
    private String login;
    private String passwordHash;
    private String hashCode;

    public UserParams() {
    }

    public UserParams(String login, String passwordHash, String hashCode) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.hashCode = hashCode;
    }
}
