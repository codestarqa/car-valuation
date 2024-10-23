package com.webuyanycare.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

/**
 * By Naresh Savalia
 */
public class CarValuationPage {
    WebDriver driver;

    public CarValuationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators for the web form (hypothetical IDs, to be replaced with actual ones)
    @CacheLookup
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    WebElement acceptCookies;

    @FindBy(id = "vehicleReg")
    WebElement regInput;

    @FindBy(id = "Mileage")
    WebElement mileageInput;

    @FindBy(xpath = "//button[@id='btn-go']")
    WebElement submitBtn;

    @CacheLookup
    @FindBy(xpath = "//section[contains(@class,'primary-section')]//div[@class='details-vrm ng-star-inserted']")
    WebElement vehicleReg;
    @CacheLookup
    @FindBy(xpath = "(//div[@class='d-table-cell heading'][normalize-space()='Manufacturer:'])[2]/following-sibling::*")
    WebElement manufacturer;
    @CacheLookup
    @FindBy(xpath = "//section[contains(@class,'primary-section')]//div[contains(text(),'Model:')]/following-sibling::div")
    WebElement model;
    @CacheLookup
    @FindBy(xpath = "//section[contains(@class,'primary-section')]//div[contains(text(),'Year:')]/following-sibling::div")
    WebElement year;
    @CacheLookup
    @FindBy(xpath = "//section[contains(@class,'primary-section')]//div[contains(text(),'Colour:')]/following-sibling::div")
    WebElement colour;
    @CacheLookup
    @FindBy(xpath = "//section[contains(@class,'primary-section')]//div[contains(text(),'Transmission:')]/following-sibling::div")
    WebElement transmission;
    @CacheLookup
    @FindBy(xpath = "//section[contains(@class,'primary-section')]//div[contains(text(),'Engine Size:')]/following-sibling::div")
    WebElement engineSize;
    @CacheLookup
    @FindBy(xpath = "//section[contains(@class,'primary-section')]//div[contains(text(),'First Registered:')]/following-sibling::div")
    WebElement firstRegistered;
    @CacheLookup
    @FindBy(xpath = "//button[@id='btn-back']")
    WebElement backButton;

    public void setAcceptCookies() {
        acceptCookies.click();
    }

    public void enterVehicleDetails(String reg, String mileage) {
        regInput.clear();
        regInput.sendKeys(reg);
        mileageInput.sendKeys(mileage);
        System.out.println("Click on Button");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        submitBtn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String getMake() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return manufacturer.getText();
    }

    public String getModel() {
        return model.getText();
    }

    public String getYear() {
        return year.getText();
    }

    public void loadPage(String url) {
        driver.get(url);
    }

    public void backButton() {
        backButton.click();

    }
}
