package com.elena;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;

public class TestPracticeForm {
    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";

    }

    @Test
    void FillFormTest(){
        open("/automation-practice-form"); //open required page

        //workaround
        Selenide.executeJavaScript("document.getElementById('fixedban').hidden = 'true'");
        executeJavaScript("$('footer').remove()");

        String firstName = "Name";
        String lastName = "Surname";
        String userEmail = "mail@mail.ru";
        String gender = "Female";
        String userNumber = "1112223334";
        String year = "2012";
        String month = "April";
        String day = "12";
        String subject = "English";
        String hobby = "Music";
        String address = "Country, City, Street 5";
        String state = "NCR";
        String city = "Delhi";


        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        //date pain
        $("#dateOfBirthInput").click(); //open calendar
        $(" .react-datepicker__year-select").selectOption(year);
        $(" .react-datepicker__month-select").selectOption(month);
        $(byText(day)).click();

        $("#subjectsInput").setValue(subject).pressEnter();
        $(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath("cat.jpg");

        //location
        $("#currentAddress").setValue(address);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(userEmail),
                text(gender),
                text(userNumber),
                text(day + " " + month + "," + year),
                text(subject),
                text(hobby),
                text("cat.jpg"),
                text(address),
                text(state + " " + city)
        );
    }
}
