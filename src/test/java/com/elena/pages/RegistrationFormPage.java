package com.elena.pages;

import com.codeborne.selenide.SelenideElement;
import com.elena.pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {
    CalendarComponent calendar = new CalendarComponent();

    // locators
    SelenideElement firstNameInput = $("#firstName"),
                    lastNameInput = $("#lastName"),
                    emailInput = $("#userEmail"),
                    genderInput = $("#genterWrapper"),
                    dateOfBirthInput = $("#dateOfBirthInput"),
                    userNumberInput = $("#userNumber"),
                    subjectsInput = $("#subjectsInput"),
                    hobbiesInput = $("#hobbiesWrapper"),
                    uploadPicture = $("#uploadPicture"),
                    currentAddressInput = $("#currentAddress"),
                    stateInput = $("#state"),
                    cityInput = $("#city"),
                    submitClick = $("#submit"),
                    tableResultInput = $(".table-responsive");

    // actions
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    // we dont use it, only for example
    public RegistrationFormPage clearFirstName() {
        firstNameInput.clear();

        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);

        return this;
    }

    public RegistrationFormPage setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }
    public RegistrationFormPage setHobbies(String value) {
        hobbiesInput.$(byText(value)).click();

        return this;
    }
    public RegistrationFormPage setPicture(String value) {
        uploadPicture.uploadFile(new File(value));

        return this;
    }

    public RegistrationFormPage setAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setState(String value) {
        stateInput.click();
        $(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setCity(String value) {
        cityInput.click();
        $(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setSubmit() {
        submitClick.click();

        return this;
    }

    public RegistrationFormPage checkResult(String key, String value) {
        tableResultInput.$(byText(key))
                .parent().shouldHave(text(value));

        return this;
    }


}
