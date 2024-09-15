package pages;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    public RegisterPage registerPage;
    public WebDriver driver;
    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPage getRegisterPage() {
        if (registerPage == null) {
            registerPage = new RegisterPage();
        }
        return registerPage;
    }
}
