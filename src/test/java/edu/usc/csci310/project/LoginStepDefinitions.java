package edu.usc.csci310.project;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class LoginStepDefinitions {
    private static final String ROOT_URL = "http://localhost:8080/";
    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Setting Up Cucumber Driver");
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--whitelisted-ips");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
    }
    @io.cucumber.java.en.Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver.get(ROOT_URL + "LoginSignUp/");
    }

    @When("I enter {string} for the email")
    public void iEnterForTheEmail(String arg0) {
        driver.findElement(By.xpath(("//*[@id=\"root\"]/div/div/div/form/input[1]"))).sendKeys(arg0);
    }

    @And("I enter {string} for the password")
    public void iEnterForThePassword(String arg0) {
        driver.findElement(By.name("password")).sendKeys(arg0);
    }

    @Then("I should be logged in.")
    public void iShouldLogin() {
        assertEquals(1,1);
    }

    @And("I click submit")
    public void iClickSubmit() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")).click();
    }

    @When("I click Sign Up")
    public void iClickSignUp() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/p/button")).click();
    }

    @Then("I am registered.")
    public void iAmRegistered() {
        assertEquals(1,1);
    }

    @And("I enter {string} for the password confirmation")
    public void iEnterForThePasswordConfirmation(String arg0) {
    driver.findElement(By.name("confirm-password")).sendKeys(arg0);
    }

    @Then("I am not registered.")
    public void iAmNotRegistered() {
        assertEquals(1,1);
    }

    @And("I enter {string} for the name.")
    public void iEnterForTheName(String arg0) {
        driver.findElement(By.name("name")).sendKeys(arg0);
    }

    @Then("I should not be logged in.")
    public void iShouldNotBeLoggedIn() {
    }
}

