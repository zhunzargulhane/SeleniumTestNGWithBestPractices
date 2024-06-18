package utils;

import com.github.javafaker.Faker;

public class JavaFakerUtils {

    public static String getFirstName() {
        Faker faker = new Faker();
        return faker.regexify("[a-zA-Z]{10}");
    }

    public static String getLastName() {
        Faker faker = new Faker();
        return faker.regexify("[a-zA-Z]{10}");
    }

    public static String getStreetAddress() {
        Faker faker = new Faker();
        return faker.address().streetAddress();
    }

    public static String getCity() {
        Faker faker = new Faker();
        return faker.address().city();
    }

    public static String getZipCode() {
        Faker faker = new Faker();
        return faker.address().zipCode();
    }

    public static String getEmailAddress() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
}
