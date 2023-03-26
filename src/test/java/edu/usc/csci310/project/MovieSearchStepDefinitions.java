package edu.usc.csci310.project;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieSearchStepDefinitions {

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

    @Given("I am on the movie search page")
    public void iAmOnTheMovieSearchPage() {
        driver.get(ROOT_URL + "MovieSearch");
    }

    @When("I search for {string}")
    public void iSearchFor(String searchTerm) {
        driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/input")).sendKeys(searchTerm);
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
}
