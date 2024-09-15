package pages;

import base.BasePage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.LogUtils;

import java.util.HashMap;
import java.util.Objects;


public class RegisterPage extends BasePage {

    public RegisterPage() {
        super();
    }

    private @FindBy(id = "firstName")
    WebElement txt_fname;

    private @FindBy(id = "lastName")
    WebElement txt_lname;
    private @FindBy(id = "userEmail")
    WebElement txt_email;

    private @FindBy(css = "div.custom-radio label[for='gender-radio-1']")
    WebElement rdo_male;

    private @FindBy(id = "div.custom-radio label[for='gender-radio-2']")
    WebElement rdo_female;
    private @FindBy(id = "userNumber")
    WebElement txt_phone;
    private @FindBy(id = "dateOfBirthInput")
    WebElement txt_dob;

    private @FindBy(css = "select[class='react-datepicker__month-select']")
    WebElement cbx_month;

    private @FindBy(css = "select[class='react-datepicker__year-select']")
    WebElement cbx_year;
    private @FindBy(id = "subjectsInput")
    WebElement txt_subject;

    private @FindBy(id = "react-select-2-option-0")
    WebElement opt_first_subject_auto_complete;

    private @FindBy(css = "div.custom-checkbox label[for='hobbies-checkbox-1']")
    WebElement chk_sports;
    private @FindBy(css = "div.custom-checkbox label[for='hobbies-checkbox-2']")
    WebElement chk_reading;
    private @FindBy(id = "uploadPicture")
    WebElement upload_picture;
    private @FindBy(id = "currentAddress")
    WebElement txt_current_address;
    private @FindBy(id = "react-select-3-input")
    WebElement txt_state;

    private @FindBy(id = "react-select-4-input")
    WebElement txt_city;

    private @FindBy(id = "city")
    WebElement cbx_city;

    private @FindBy(id = "example-modal-sizes-title-lg")
    WebElement msg_submit_form_success;

    private WebElement txt_date(String value) {
        String locator = String.format("div[class='react-datepicker__week'] div:not([class*='react-datepicker__day--outside-month'])[class*='react-datepicker__day--00%s']", value);
        WebElement object = getElement("css", locator);
        return object;
    }

    private @FindBy(id = "submit")
    WebElement btn_submit;

    private @FindBy(css = "div[class='modal-content'] table")
    WebElement tbl_SubmitFormSuccess;

    /* -----Actions-----*/
    public void openRegisterPage() {
        navigateToURL("https://demoqa.com/automation-practice-form");
    }
    public void enterFirstName(String fname) {
        sendKeys(txt_fname, fname, "First name");
    }

    public void enterLastName(String lname) {
        sendKeys(txt_lname, lname, "Last name");
    }

    public void enterEmail(String email) {
        sendKeys(txt_email, email, "Email");
    }

    public void clickGender(String gender) {
        switch (gender) {
            case "Male":
                scrollToElement(rdo_male);
                click(rdo_male, "Male");
                break;
            case "Female":
                scrollToElement(rdo_female);
                click(rdo_female, "Female");
                break;
            default:
                System.out.println(gender + " does not exist on UI");
        }
    }

    public void enterMobilePhone(String phone) {
        sendKeys(txt_phone, phone, "Phone");
    }

    public void enterDOB(String dob) {
        click(txt_dob, "DOB");

        String[] dateOfBirth = dob.split("-");
        String date = dateOfBirth[0];
        String month = dateOfBirth[1];
        String year = dateOfBirth[2];

        selectByText(cbx_month, month);
        selectByValue(cbx_year, year);
        click(txt_date(date), "Date");
    }

    public void enterSubject(String subject) {
        sendKeys(txt_subject, subject, "Subject");
        click(opt_first_subject_auto_complete, "Subject");
    }

    public void clickHobbies(String hobby) {
        switch (hobby) {
            case "Sports":
                click(chk_sports, "Sports");
                break;
            case "Reading":
                click(chk_reading, "Reading");
                break;
            default:
                System.out.println(hobby + "does not exist on UI");
        }
    }

    public void uploadAvatar(String imageName) {
        uploadImage(upload_picture, imageName);
    }

    public void enterCurrentAddress(String address) {
        sendKeys(txt_current_address, address, "Current Address");
    }

    public void selectState(String value) {
        sendKeys(txt_state, value, "State");
        pressTab(txt_state);
    }

    public void selectCiti(String value) {
        click(cbx_city, "City");
        sendKeys(txt_city, value, "City");
        pressTab(txt_city);
    }

    public void clickSubmit() {
        click(btn_submit, "Submit");
    }

    public HashMap<String, String> infoStudentSubmitted() {
        HashMap<String, String> studentInfo = new HashMap<String, String>();
        String column = "Values";

        studentInfo.put("Student Name", getText(getCell(tbl_SubmitFormSuccess, 1, column)));
        studentInfo.put("Student Email", getText(getCell(tbl_SubmitFormSuccess, 2, column)));
        studentInfo.put("Gender", getText(getCell(tbl_SubmitFormSuccess, 3, column)));
        studentInfo.put("Mobile", getText(getCell(tbl_SubmitFormSuccess, 4, column)));
        studentInfo.put("DOB", getText(getCell(tbl_SubmitFormSuccess, 5, column)));
        studentInfo.put("Subjects", getText(getCell(tbl_SubmitFormSuccess, 6, column)));
        studentInfo.put("Hobbies", getText(getCell(tbl_SubmitFormSuccess, 7, column)));
        studentInfo.put("Picture", getText(getCell(tbl_SubmitFormSuccess, 8, column)));
        studentInfo.put("Address", getText(getCell(tbl_SubmitFormSuccess, 9, column)));
        studentInfo.put("State and City", getText(getCell(tbl_SubmitFormSuccess, 10, column)));

        return studentInfo;

    }

    public String getBorderColorFromField(WebElement element, String fieldName) {
        return getCssValue(element, fieldName, "border-color");
    }

    /* -----Verify----- */
    public String getMessageSuccessSubmitFrom() {
        return getText(msg_submit_form_success);
    }

    public boolean mobileFieldShouldBeRed(String fieldName) {
        String value = "";

        switch (fieldName) {
            case "Mobile":
                value = getBorderColorFromField(txt_phone, fieldName);
                break;
            // Todo others
            default:
                LogUtils.warn("There is no option matched");
        }

        return Objects.equals(value, "rgb(220, 53, 69)");
    }

}
