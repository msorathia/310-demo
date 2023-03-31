/*package edu.usc.csci310.project;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WatchListStepDefinitions {

    private static final String ROOT_URL = "http://localhost:8080/";
    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Setting Up Driver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().driverVersion("110.0.5481").setup();
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

    @Given("I am on the movie search page")
    public void iAmOnTheMovieSearchPage() {
        driver.get(ROOT_URL + "MovieSearch");
    }

    @Given("the user is on the search results page")
    public void theUserIsOnTheSearchResultsPage() {
        driver.get(ROOT_URL + "MovieSearch");
    }

    @When("the user hovers over a search result on a desktop device")
    public void theUserHoversOverASearchResultOnADesktopDevice() {
        WebElement searchResult = driver.findElement(By.className("movie-item"));
        Actions action = new Actions(driver);
        action.moveToElement(searchResult).perform();
    }

    @Then("additional controls \\(\\+ button) should appear")
    public void additionalControlsShouldAppear() {
        assertTrue(driver.findElement(By.className("add-button")).isDisplayed());
    }

    @When("the user clicks the add to watch list button \\(\\+ button)")
    public void theUserClicksTheAddToWatchListButton() {
        driver.findElement(By.className("add-button")).click();
    }

    @Then("the user should be able to add the movie to an existing list or create a new list")
    public void theUserShouldBeAbleToAddTheMovieToAnExistingListOrCreateANewList() {
        assertTrue(driver.findElement(By.className("watchlist-prompt")).isDisplayed());
    }

    @Given("the user is adding a movie to a watch list")
    public void theUserIsAddingAMovieToAWatchList() {
        theUserIsOnTheSearchResultsPage();
        theUserClicksTheAddToWatchListButton();
    }

    @When("the user chooses to create a new list")
    public void theUserChoosesToCreateANewList() {
        driver.findElement(By.xpath("//button[text()='Create']")).click();
    }
}*/