package com.cvamedios.clipco_login;

/**
 * Created by cvamedios on 20/07/17.
 */

public class usuarios {

    public String name;
    public String email;
    public String user;
    public String password;
    public String token;// verificar si la vartiable tiene que ser tipo String o un Token

    public usuarios( ) {

    }

    public usuarios(String name, String email, String user, String password,String token) {
        this.name = name;
        this.email = email;
        this.user = user;
        this.password = password;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
