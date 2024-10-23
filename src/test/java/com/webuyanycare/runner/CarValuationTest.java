package com.webuyanycare.runner;

import com.webuyanycare.dataInputOutput.CarDetails;
import com.webuyanycare.dataInputOutput.CarOutputReader;
import com.webuyanycare.dataInputOutput.VehicleRegExtractor;
import com.webuyanycare.pages.CarValuationPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * By Naresh Savalia
 */
public class CarValuationTest {
    WebDriver driver;
    CarValuationPage carValuationPage;
    Map<String, CarDetails> expectedCarDetails;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        carValuationPage = new CarValuationPage(driver);
        // Load expected car details from car_output.txt
        String outputFilePath = System.getProperty("user.dir") + "/src/test/resources/testdata/car_output.txt";
        expectedCarDetails = CarOutputReader.loadExpectedCarDetails(outputFilePath);
    }

    @Test
    public void testVehicleValuation() {
        String inputFilePath = System.getProperty("user.dir") + "/src/test/resources/testdata/car_input.txt";
        List<String> vehicleRegs = VehicleRegExtractor.extractVehicleRegs(inputFilePath);
        // Load the car valuation page and enter details
        carValuationPage.loadPage("https://www.webuyanycar.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        carValuationPage.setAcceptCookies();
        System.out.println("Key Set: " + expectedCarDetails.keySet());
        System.out.println("Values: " + expectedCarDetails.values());
        for (String reg : vehicleRegs) {
            // Ensure the registration exists in the expected output file
            if (!expectedCarDetails.containsKey(reg)) {

                System.out.println("Registration not found in output file: " + reg);

            } else {
                try {

                    System.out.println("Registration found in output file: " + reg);
                    CarDetails expectedDetails = expectedCarDetails.get(reg);
                    carValuationPage.enterVehicleDetails(reg, generateRandomMileage());

                    // Retrieve actual details from the website (assuming website returns make, model, year)
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
                    String actualMake = carValuationPage.getMake();
                    String actualModel = carValuationPage.getModel();
                    String actualYear = carValuationPage.getYear();
                    // details matched with car_output.txt file
                    System.out.println(reg + "," + actualMake + "," + actualModel + "," + actualYear);

                    // Compare each field
                    Assert.assertEquals(actualMake, expectedDetails.getMake(), "Make mismatch for: " + reg);
                    Assert.assertEquals(actualModel, expectedDetails.getModel(), "Model mismatch for: " + reg);
                    Assert.assertEquals(actualYear, expectedDetails.getYear(), "Year mismatch for: " + reg);
                    carValuationPage.backButton();

                } catch (StaleElementReferenceException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private String generateRandomMileage() {
        return String.valueOf((int) (Math.random() * 90000 + 10000));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
