package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class FramesTest extends BaseTest {
    private final String initText = "Your content goes here.";
    private final String textIsNotDisplayedMsg = "Text is not displayed";
    private final By frames = By.xpath(String.format(PRECISE_TEXT_XPATH, "Frames"));
    private final By iframe = By.xpath(String.format(PRECISE_TEXT_XPATH, "iFrame"));
    private final By edit = By.xpath(String.format(PRECISE_TEXT_XPATH, "Edit"));
    private final By undo = By.xpath(String.format(PRECISE_TEXT_XPATH, "Undo"));
    private String frameId = "mce_0_ifr";
    private final String randomText = UUID.randomUUID().toString();
    private final By TextField = By.id("tinymce");

    @Test
    public void iFrameTest() {
        try {
            driver.findElement(frames).click();
            driver.findElement(iframe).click();

            driver.switchTo().frame(frameId);
            driver.findElement(TextField).sendKeys(randomText);

            Assert.assertTrue(driver.findElement(By.xpath(String.format(PRECISE_TEXT_XPATH, initText + randomText))).isDisplayed(),
                    textIsNotDisplayedMsg);
            driver.switchTo().defaultContent();
            driver.findElement(edit).click();
            driver.findElement(undo).click();

            driver.switchTo().frame(frameId);

            Assert.assertTrue(driver.findElement(By.xpath(String.format(PRECISE_TEXT_XPATH, initText))).isDisplayed(),
                    textIsNotDisplayedMsg);

        } catch (Exception e) {
            System.out.println("Write access denied: " + e.getMessage());
        }

    }
}
