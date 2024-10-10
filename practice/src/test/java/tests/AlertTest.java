package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AlertTest extends BaseTest {
    private final By jsAlert = By.xpath(String.format(PRECISE_TEXT_XPATH, "JavaScript Alerts"));
    private final By clickForJsAlertButton = By.xpath("//button[@onclick='jsAlert()']");
    private final By successMessagePath = By.id("result");

    @Test
    public void alertTest() {
        driver.findElement(jsAlert).click();
        driver.findElement(clickForJsAlertButton).click();
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
        Assert.assertTrue(driver.findElement(successMessagePath).isDisplayed(), "Success message not showed!");
    }
}
