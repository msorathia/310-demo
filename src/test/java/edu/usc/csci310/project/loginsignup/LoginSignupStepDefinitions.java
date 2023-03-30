package edu.usc.csci310.project.loginsignup;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
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
        //WebDriverManager.chromedriver().setup();
    }

    @After
    public void After()
    {
        driver.quit();
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

    @When("I click on the sign up button on the login page")
    public void iClickOnTheSignUpButtonOnTheLoginPage() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/p/button")).click();
    }

    @Then("I should be on the sign up section")
    public void iShouldBeOnTheSignUpSection() {
        boolean value = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/h2")).getText().equals("Sign Up");
        assertTrue(value);
    }

    @And("I click on the login button on the sign up page")
    public void iClickOnTheLoginButtonOnTheSignUpPage() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/p/button")).click();
    }

    @Then("I should be on the login section")
    public void iShouldBeOnTheLoginSection() {
        boolean value = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/h2")).getText().equals("Login");
        assertTrue(value);
    }


    @When("I enter no inputs")
    public void iEnterNoInputs() {

    }

    @And("I click on the login button on the login page")
    public void iClickOnTheLoginButtonOnTheLoginPage() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")).click();
    }

    @Then("I should see {string} message on the login page")
    public void iShouldSeeMessageOnTheLoginPage(String arg0) {
        assertTrue(driver.getPageSource().contains(arg0));
    }

    @And("I enter {string} in log in password field")
    public void iEnterInLogInSPasswordField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[2]")).sendKeys(arg0);
    }

    @When("I enter no inputs for the email id field")
    public void iEnterNoInputsForTheEmailIdField() {
    }

    @And("I enter {string} in the sign up name field")
    public void iEnterInTheSignUpNameField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[1]")).sendKeys(arg0);
    }


    @And("I enter {string} in the sign up email field")
    public void iEnterInTheSignUpEmailField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[2]")).sendKeys(arg0);
    }


    @And("I enter {string} in the sign up password field")
    public void iEnterInTheSignUpPasswordField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[3]")).sendKeys(arg0);
    }

    @And("I enter {string} in the sign up confirm password field")
    public void iEnterInTheSignUpConfirmPasswordField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[4]")).sendKeys(arg0);
    }


    @And("I click on the sign up button on the sign up page")
    public void iClickOnTheSignUpButtonOnTheSignUpPage() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")).click();
    }


    @And("I enter no inputs for the password field")
    public void iEnterNoInputsForThePasswordField() {

    }

    @And("I enter {string} in login's email id field")
    public void iEnterInLoginSEmailIdField(String arg0) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/input[1]")).sendKeys(arg0);
    }


    @Then("I should see an automated message on the sign up page while remaining on the sign up page")
    public void iShouldSeeAnAutomatedMessageOnTheSignUpPageWhileRemainingOnTheSignUpPage() {
        assertTrue(driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/h2")).getText().equals("Sign Up"));
    }


    @When("I enter no inputs in the name field")
    public void iEnterNoInputsInTheNameField() {

    }

    @And("I enter nothing in the email field")
    public void iEnterNothingInTheEmailField() {

    }

    @And("I enter nothing in the password section")
    public void iEnterNothingInThePasswordSection() {

    }

    @And("I enter no inputs in the confirm password field")
    public void iEnterNoInputsInTheConfirmPasswordField() {

    }

    @Then("I should see {string} message on the sign up page")
    public void iShouldSeeMessageOnTheSignUpPage(String arg0) {
        assertTrue(driver.getPageSource().contains(arg0));
    }
}
