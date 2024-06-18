package org.selenium.pom.objects;

public class BillingAddress {
    private String firstName;
    private String lastName;
    private String addressLineOne;
    private String city;
    private String zipCode;
    private String emailAddress;
    private String country;
    private String state;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BillingAddress() {
    }

    public BillingAddress(String firstName, String lastName, String addressLineOne, String city, String zipCode, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.zipCode = zipCode;
        this.emailAddress = emailAddress;
    }


    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public BillingAddress setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public BillingAddress setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public BillingAddress setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

}
