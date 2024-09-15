package base;

import driver.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.LogUtils;

import java.util.List;


public class BasePage {

    final long timeOutInSeconds = 10;
    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public void navigateToURL(String URL) {
        getDriver().get(URL);
    }

    protected void sendKeys(WebElement element, String text, String objectName) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
            LogUtils.info(String.format("Send keys at %s successfully", objectName));
        } catch (Exception e) {
            LogUtils.error(String.format("Cannot send keys at %s", objectName));
        }

    }

    protected void click(WebElement element, String objectName) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            LogUtils.info(String.format("Click on %s successfully", objectName));
        } catch (Exception e) {
            LogUtils.error(String.format("Cannot click on %s", objectName));
        }
    }

    protected void selectByValue(WebElement element, String value) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Select objSelect = new Select(element);
        objSelect.selectByValue(value);
    }

    protected void selectByText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Select objSelect = new Select(element);
        objSelect.selectByVisibleText(text);
    }

    protected WebElement getElement(String by,  String locator) {
        WebElement object = null;
        switch (by) {
            case "xpath":
                object = getDriver().findElement(By.xpath(locator));
                break;
            case "css":
                object =  getDriver().findElement(By.cssSelector(locator));
            default:
                System.out.println(by + " is not supported");
        }

        return object;
    }

    protected void clearText(WebElement element) {
        element.clear();
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    protected void pressTab(WebElement element) {
        element.sendKeys(Keys.TAB);
    }

    protected void uploadImage(WebElement element, String imageName) {
        String path = String.format(System.getProperty("user.dir") + "/src/main/java/data/images/%s", imageName);
        element.sendKeys(path);
    }

    protected String getText(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    protected boolean isElementDisplay(WebElement element) {
        return element.isDisplayed();
    }

    protected WebElement getRow(WebElement table, int row)
    {
        WebElement object = null;
        List<WebElement> Rows = table.findElements(By.cssSelector("tr"));
        int CountRows = Rows.size();
        for (int i = 0; i < CountRows; i++)
        {
            object = Rows.get(i);
            if(i == row) {
                break;
            }
        }
        return object;
    }

    protected WebElement getCell(WebElement table, int row, String col)
    {
        WebElement object = null;
        WebElement HeaderRow = getRow(table, 0);
        List <WebElement> HeadeCols = HeaderRow.findElements(By.cssSelector("th"));
        WebElement Row = getRow(table, row);
        List <WebElement> Cols = Row.findElements(By.cssSelector("td"));
        int CountCols = Cols.size();
        for (int i = 0; i < CountCols; i++)
        {
            String ColName = HeadeCols.get(i).getText();
            if(ColName.contentEquals(col)) {
                object =  Cols.get(i);
                break;
            }
        }
        return object;
    }

    protected String getCssValue(WebElement element, String fieldName, String propertyName) {
        String value = element.getCssValue(propertyName);
        LogUtils.info(String.format("Css value of the %s field is: %s", fieldName, value));
        return value;
    }
}
