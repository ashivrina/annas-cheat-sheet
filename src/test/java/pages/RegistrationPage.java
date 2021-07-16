package pages;

import com.codeborne.selenide.SelenideElement;
import components.Calendar;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private Calendar calendar = new Calendar();
    private SelenideElement modal = $("[role=dialog]");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
        return this;
    }

    public RegistrationPage typeFirstName(String firstName) {
        $("#firstName").val(firstName);
        return this;
    }

    public RegistrationPage typeLastName(String lastName) {
        $("#lastName").val(lastName);
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        $("#userEmail").val(email);
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        $(byText(gender)).click();
        return this;
    }

    public RegistrationPage typePhone(String phone) {
        $("#userNumber").scrollTo().val(phone);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage selectSubject(String subject) {
        $("#subjectsInput").val(subject).pressEnter();
        return this;
    }

    public RegistrationPage selectHobby(String hobby) {
        $(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadFile(String pathname) {
        $("#uploadPicture").uploadFile(new File(pathname));
        return this;
    }

    public RegistrationPage enterAddress(String address) {
        $("#currentAddress").val(address);
        return this;
    }

    public RegistrationPage selectState(String state) {
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    public RegistrationPage submitForm() {
        $("#submit").click();
        return this;
    }

    public RegistrationPage checkResultsTitle() {
        modal.$(".modal-title").shouldHave(text(RESULTS_TITLE));
        return this;
    }

    public RegistrationPage checkResultsValue(String value) {
        modal.$(".table-responsive").shouldHave(text(value));
        return this;
    }
}
