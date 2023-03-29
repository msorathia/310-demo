package edu.usc.csci310.project.loginsignup;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertTrue;


public class LoginSignupStepDefinitions {

    private static final String ROOT_URL = "http://localhost:8080/";
    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Setting Up Driver");
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void before() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        driver = new ChromeDriver(options);
    }
    @Given("I am on the login signup page")
    public void iAmOnTheLoginSignupPage() {
        driver.get(ROOT_URL + "LoginSignUp");
    }

    @When("I click on the sign up button")
    public void iClickOnTheSignUpButton() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/p/button")).click();
    }


    @Then("I should be on the sign up section")
    public void iShouldBeOnTheSignUpSection() {
        assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")) != null);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")).click();
    }

    @Then("I should be on the login section")
    public void iShouldBeOnTheLoginSection() {
        assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/p/button")) != null);
    }


    @When("I enter no inputs")
    public void iEnterNoInputs() {

    }

    @Then("I should see {string} message on the login page")
    public void iShouldSeeMessageOnTheLoginPage(String arg0) {
        assertTrue(driver.getPageSource().contains(arg0));
    }

    @When("I enter no inputs for the email id field")
    public void iEnterNoInputsForTheEmailIdField() {

    }

    @And("I enter {string} in the password field")
    public void iEnterInThePasswordField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[2]")).sendKeys(arg0);
    }


    @When("I enter no inputs for the password field")
    public void iEnterNoInputsForThePasswordField() {

    }

    @And("I enter {string} in the email id field")
    public void iEnterInTheEmailIdField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[1]")).sendKeys(arg0);
    }


    @Given("I am on the sign up section")
    public void iAmOnTheSignUpSection() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/p/button")).click();
    }

    @Then("I should see an automated message on the sign up page while remaining on the sign up page")
    public void iShouldSeeAnAutomatedMessageOnTheSignUpPageWhileRemainingOnTheSignUpPage() {
        assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/h2")).getText().equals("Sign Up"));
    }


    @When("I enter no inputs in the name field")
    public void iEnterNoInputsInTheNameField() {

    }

    @And("I enter {string} in the email field")
    public void iEnterInTheEmailField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[2]")).sendKeys(arg0);
    }


    @And("I enter {string} in the password section")
    public void iEnterInThePasswordSection(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[3]")).sendKeys(arg0);
    }


    @And("I enter {string} in the confirm password section")
    public void iEnterInTheConfirmPasswordSection(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[4]")).sendKeys(arg0);
    }


    @When("I enter {string} in the name field")
    public void iEnterInTheNameField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[1]")).sendKeys(arg0);
    }


    @And("I enter nothing in the email field")
    public void iEnterNothingInTheEmailField() {
        
    }

    @And("I enter nothing in the password section")
    public void iEnterNothingInThePasswordSection() {
        
    }

    @And("I enter no inputs in the confirm password section")
    public void iEnterNoInputsInTheConfirmPasswordSection() {

    }

    @Then("I should see {string} message on the sign up page")
    public void iShouldSeeMessageOnTheSignUpPage(String arg0) {
        assertTrue(driver.getPageSource().contains(arg0));
    }
}
