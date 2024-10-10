package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class DynamicControlsTest extends BaseTest {
    private final By dynamicControls = By.xpath(String.format(PRECISE_TEXT_XPATH, "Dynamic Controls"));
    private final By enable = By.xpath(String.format(PRECISE_TEXT_XPATH, "Enable"));
    private final By input = By.xpath("//*[@id=\"input-example\"]//input");

    @Test
    public void dynamicControlsTest() {
        driver.findElement(dynamicControls).click();
        driver.findElement(enable).click();
        WebElement element = driver.findElement(input);
        Assert.assertTrue(isClickable(element),"Input box is disabled");
        String randomText = UUID.randomUUID().toString();
        driver.findElement(input).sendKeys(randomText);
        String actualText = driver.findElement(input).getAttribute("value");
        Assert.assertEquals(actualText, randomText, "String does not match");

    }

    private boolean isClickable(WebElement element){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
        catch (TimeoutException exception){
            return false;
        }
        return true;
    }
}
