package com.elena.tests;

import com.codeborne.selenide.Configuration;
import com.elena.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;


public class TestPracticeFormPageObjectFaker {
    Faker faker = new Faker();
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            address = faker.address().fullAddress(),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            gender = "Female",
            year = "2012",
            month = "April",
            day = "12",
            subject = "English",
            hobby = "Music",
            state = "NCR",
            file = "src/test/resources/cat.jpg",
            fileName = "cat.jpg",
            city = "Delhi";

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";

    }

    @Test
    void fillFormTest(){
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setBirthDate(day,month,year)
                .setUserNumber(userNumber)
                .setSubjects(subject)
                .setHobbies(hobby)
                .setPicture(file)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .setSubmit()
                .checkResult("Student Name",(firstName + " " + lastName))
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", (day + " " + month + "," + year))
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", fileName)
                .checkResult("Address", address)
                .checkResult("State and City", (state + " " + city));

    }
}
