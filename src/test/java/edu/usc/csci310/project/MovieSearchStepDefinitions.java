package edu.usc.csci310.project;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieSearchStepDefinitions {

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

    @When("I search for {string} using the Enter key with the keyword radio button pressed")
    public void iSearchForUsingTheEnterKeyWithTheRadioButtonPressed(String arg0) {
        driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/input")).sendKeys(arg0);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/button")).click();
    }

    @Then("I should see a list of search results")
    public void iShouldSeeAListOfSearchResults() {
        assertNotEquals(0, driver.findElement(By.xpath("/html/body/div/div/div/div")).findElements(By.className("movie-item")).size());
    }

    @Then("I should see more search results")
    public void iShouldSeeMoreSearchResults() {
        int initialCount = driver.findElements(By.className("movie-item")).size();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/button")).click();
        int finalCount = driver.findElements(By.className("movie-item")).size();
        assertEquals(true, finalCount > initialCount);
    }

    @When("I click on the {string} button")
    public void iClickOnTheButton(String buttonName) {
        driver.findElement(By.xpath("//button[contains(text(), '"+buttonName+"')]")).click();
    }

    @Then("I should see details about the movie")
    public void iShouldSeeDetailsAboutTheMovie() {
        assertEquals(true, driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[0]/div/p[1]")).getText().contains("rating"));
    }

    @Given("I am viewing details about a movie")
    public void iAmViewingDetailsAboutAMovie() {
        driver.findElement(By.className("movie-item")).click();
        iShouldSeeDetailsAboutTheMovie();
    }

    @When("I click the close button")
    public void iClickTheCloseButton() {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[3]")).click();
    }

    @Then("the movie details popup should close")
    public void theMovieDetailsPopupShouldClose() {
        assertEquals(false, driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[0]/div/p[1]")).getText().contains("rating"));
    }


    @When("I search for {string} using the Enter key")
    public void iSearchForUsingTheEnterKey(String arg0) {
        driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/input")).sendKeys(arg0);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/input")).sendKeys(Keys.ENTER);

    }

    @And("I press the keyword radio button")
    public void iPressTheKeywordRadioButton() {
        driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(1) > label:nth-child(1) > input[type=radio]")).click();
    }

    @Then("I should see a list of search results that includes {string}")
    public void iShouldSeeAListOfSearchResultsThatIncludes(String arg0) {
        assertTrue(driver.getPageSource().contains(arg0));
    }

    @And("I search for {string} using the Search button")
    public void iSearchForUsingTheSearchButton(String arg0) {
        driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/input")).sendKeys(arg0);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/button")).click();
    }

    @When("I press the actor radio button")
    public void iPressTheActorRadioButton() {
        driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(1) > label:nth-child(2) > input[type=radio]")).click();
    }

    @And("I click on the first Search Result")
    public void iClickOnTheFirstSearchResult() {
        Duration d = Duration.ofSeconds(20);
        new WebDriverWait(driver, d).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]")));
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[1]")).click();
    }

    @When("I press the title radio button")
    public void iPressTheTitleRadioButton() {
        driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(1) > label:nth-child(3) > input[type=radio]")).click();
    }

    @Then("I should see {string} on the page")
    public void iShouldSeeOnThePage(String arg0) {
        assertTrue(driver.getPageSource().contains(arg0));
    }

    @Then("I should not see {string} on the page")
    public void iShouldNotSeeOnThePage(String arg0) {
        assertFalse(driver.getPageSource().contains(arg0));
    }


    @Then("The list should be scrollable")
    public void theListShouldBeScrollable() {
        assertTrue(driver.findElement(By.cssSelector("#root > div > div > div > div:nth-child(1) > div > div > div")).getCssValue("overflow-y").equals("scroll"));
    }

    @Then("The list should not be scrollable")
    public void theListShouldNotBeScrollable() {
        assertTrue(driver.findElement(By.cssSelector("#root > div > div > div > div:nth-child(1) > div > div > div")).getCssValue("overflow-y").equals("scroll"));
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

    @Then("additional controls should appear")
    public void additionalControlsShouldAppear() {
        assertTrue(driver.findElement(By.className("add-button")).isDisplayed());
    }

    @When("the user clicks the add to watch list button")
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

}
