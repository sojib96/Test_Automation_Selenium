package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.sql.Driver;

public class FileUploadTest extends BaseTest {
    private final String fileName = "test.txt";
    private final String filePath = "D:\\Download\\" + fileName;
    private final By fileUpload = By.xpath(String.format(PRECISE_TEXT_XPATH, "File Upload"));

    private final By chooseFile = By.id("file-upload");
    private final By fileSubmit = By.id("file-submit");
    private final By uploadMessage = By.xpath("//h3[text()='File Uploaded!']");
    private final By uploadedFile = By.id("uploaded-files");

    @Test
    public void fileUploadTest() {
        driver.findElement(fileUpload).click();
        File fileToUpload = new File(filePath);
        driver.findElement(chooseFile).sendKeys(fileToUpload.getAbsolutePath());
        driver.findElement(fileSubmit).click();
        Assert.assertTrue(isUploaded(driver.findElement(uploadMessage)));
        Assert.assertEquals(driver.findElement(uploadedFile).getText(),fileName,"File upload was unsuccessful");
    }

    private boolean isUploaded(WebElement element){
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(uploadMessage)));
        }
        catch (TimeoutException exception){
            return false;
        }
        return true;
    }

}
