package org.selenium.pom.objects;

public class Users {
    private String username;
    private String password;


    private String emailAddress;

    public Users() {

    }

    public Users(String username, String password, String emailAddress) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public Users setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Users setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }


}
