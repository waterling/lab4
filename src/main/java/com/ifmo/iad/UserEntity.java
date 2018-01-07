package com.ifmo.iad;


import java.util.List;

public class UserEntity {

    public UserEntity() {
    }

    public UserEntity(Long id, String login, String passwordHash, String hashCode) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.hashCode = hashCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }



    private Long id;
    private String login;
    private String passwordHash;
    private String hashCode;

}
