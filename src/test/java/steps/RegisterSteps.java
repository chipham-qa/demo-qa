package steps;

import hooks.TestContextSetup;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.testng.Assert.assertEquals;

public class RegisterSteps {

    TestContextSetup testContextSetup;
    RegisterPage registerPage;
    public RegisterSteps(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.registerPage = testContextSetup.pageObjectManager.getRegisterPage();
    }


    @Given("user navigates to the register page")
    public void userGoToRegisterPage() {
        registerPage.openRegisterPage();
    }

    @When("user fills out the registration form with the following details")
    public void userFillsOutTheRegistrationFormWithTheFollowingDetails(DataTable table) {
        List<Map<String, String>> map = table.asMaps(String.class, String.class);

        String fname = map.get(0).get("First name");
        String lname = map.get(0).get("Last name");
        String email = map.get(0).get("Email");
        String gender = map.get(0).get("Gender");
        String mobile = map.get(0).get("Mobile");
        String dateOfBirth = map.get(0).get("Date Of Birth");
        String subject = map.get(0).get("Subjects");
        String hobbies = map.get(0).get("Hobbies");
        String picture = map.get(0).get("Picture");
        String currentAddress = map.get(0).get("Address");
        String state = map.get(0).get("State");
        String city = map.get(0).get("City");

        if (!Objects.equals(fname, null)) {
            registerPage.enterFirstName(fname);
        }
        if (!Objects.equals(lname, null)) {
            registerPage.enterLastName(lname);
        }
        if (!Objects.equals(email, null)) {
            registerPage.enterEmail(email);
        }
        if (!Objects.equals(gender, null)) {
            registerPage.clickGender(gender);
        }
        if (!Objects.equals(mobile, null)) {
            registerPage.enterMobilePhone(mobile);
        }
        if (!Objects.equals(dateOfBirth, null)) {
            registerPage.enterDOB(dateOfBirth);
        }
        if (!Objects.equals(subject, null)) {
            registerPage.enterSubject(subject);
        }
        if (!Objects.equals(hobbies, null)) {
            registerPage.clickHobbies(hobbies);
        }
        if (!Objects.equals(picture, null)) {
            registerPage.uploadAvatar(picture);
        }
        if (!Objects.equals(currentAddress, null)) {
            registerPage.enterCurrentAddress(currentAddress);
        }
        if (!Objects.equals(state, null)) {
            registerPage.selectState(state);
        }
        if (!Objects.equals(city, null)) {
            registerPage.selectCiti(city);
        }

        registerPage.clickSubmit();
    }

    @Then("page title should display {string} upon form submission")
    public void pageTitleShouldDisplayUponFormSubmission(String message) {
        assertEquals(message, registerPage.getMessageSuccessSubmitFrom());
    }

    @And("submitted user info should match the following")
    public void submittedUserInfoShouldMatchTheFollowing(DataTable table) {
        List<Map<String, String>> map = table.asMaps(String.class, String.class);

        String fname = map.get(0).get("Student Name");
        String lname = map.get(0).get("Student Email");
        String gender = map.get(0).get("Gender");
        String mobile = map.get(0).get("Mobile");
        String dateOfBirth = map.get(0).get("DOB");
        String subject = map.get(0).get("Subjects");
        String hobbies = map.get(0).get("Hobbies");
        String picture = map.get(0).get("Picture");
        String currentAddress = map.get(0).get("Address");
        String stateAndCity = map.get(0).get("State and City");

        HashMap<String, String > student = registerPage.infoStudentSubmitted();
        assertEquals(map.get(0), student);

    }

    @Then("{string} field should be red")
    public void fieldShouldBeRed(String fieldName) {
        registerPage.mobileFieldShouldBeRed(fieldName);
    }
}
