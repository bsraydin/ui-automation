package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static driver.DriverFactory.getDriver;

public class Create_Session_Steps {
    private WebDriver driver = getDriver();

    @Given("I access the witwiser login page")
    public void i_access_the_witwiser_login_page() {
        driver.get("https://testing-app.witwiser.io/login");
    }

    @When("I enter a username {}")
    public void i_enter_a_username(String username) {
        driver.findElement(By.name("username")).sendKeys(username); //by'dan sonrasına emin değilim????
    }

    @And("I enter a password {}")
    public void i_enter_a_password(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() throws InterruptedException {
        driver.findElement(By.className("src-scenes-dashboard-components-LoginCard-LoginCard__buttonText")).click();
        Thread.sleep(5000);
    }

    @And("I click on Sessions Menu")
    public void i_click_on_session_menu() {
        WebElement sessionLink = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/div/div[1]/div[5]/a"));
        System.out.println(sessionLink);
        sessionLink.click();
    }

    @And("I click on Assign Sessions Button")
    public void i_click_on_assigne_sessions_button() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.className("src-scenes-session-components-SessionCreateButton-SessionCreateButton__button")).click();
        Thread.sleep(2000);
    }

    @And("I click on the Session Template Input Box and click on session template")
    public void i_click_on_session_template_input_box_and_click_on_session_template() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[2]/div/div/div/div/input"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        Thread.sleep(3000);

        driver.findElement(By.xpath("/html/body/div[4]/div/div[2]/form/div[2]/div/div/div/div[2]/div[1]/div/div/div")).click();
        Thread.sleep(2000);

    }

    @And("I click on the Period Start Date and End Date")
    public void i_click_on_the_period_start_date_and_end_date() throws InterruptedException {

        List<WebElement> dateInputs = driver.findElements(By.cssSelector("div.unstackable div.src-common-components-DateInput-DateInput__customDateInput"));

        dateInputs.get(0).click();
        WebElement datePicker = driver.findElement(By.className("react-datepicker__month"));
        List<WebElement> enableDates = datePicker.findElements(By.cssSelector("div[aria-disabled='false']"));
        enableDates.get(0).click();

        driver.findElement(By.className("react-datepicker__time-list-item")).click();

        dateInputs = driver.findElements(By.cssSelector("div.unstackable div.src-common-components-DateInput-DateInput__customDateInput"));
        dateInputs.get(1).click();
        Thread.sleep(2000);

        datePicker = driver.findElement(By.className("react-datepicker__month"));
        enableDates = datePicker.findElements(By.cssSelector("div[aria-disabled='false']"));
        enableDates.get(0).click();
        Thread.sleep(4000);

        List<WebElement> allTimeList = driver.findElements(By.className("react-datepicker__time-list-item"));
        List<WebElement> enableTimeListItems = allTimeList.stream()
                .filter(e -> !e.getAttribute("class").contains("react-datepicker__time-list-item--disabled"))
                .collect(Collectors.toList());

        enableTimeListItems.get(0).click();
        Thread.sleep(5000);

    }

    @And("I click on Assignment Type ComboBox and Assigment Type")
    public void i_click_on_assigment_type_combobox_and_assigment_type() throws InterruptedException {
        driver.findElement(By.cssSelector("div[name='assignmentType'] div.text")).click();
        Thread.sleep(5000);
        WebElement groupAssignmentOption = driver.findElement(By.cssSelector("div[name='assignmentType'] div.item[aria-selected='false'] span.text"));
        groupAssignmentOption.click();
        Thread.sleep(5000);

    }

    @And("I click on Test Taker and enter student {}")
    public void i_click_on_test_taker_and_i_enter_student(String name) throws InterruptedException {
        WebElement studentSearchInput = driver.findElement(By.cssSelector("div.src-common-components-UserSelector-UserSelector__userSelectorContainer input.prompt"));
        studentSearchInput.click();
        studentSearchInput.sendKeys(name);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"user-profile-witwises-admin\"]")).click();
        Thread.sleep(5000);
    }

    @And("I click on Assign Session Button")
    public void i_click_on_assign_session_button() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[4]/div/div[3]/button[2]")).click();
        Thread.sleep(2000);
    }

    @Then("I should see success message")
    public void i_should_be_see_home_page() {
        String message = driver.findElement(By.cssSelector("div.Toastify__toast--success div[role='alert'].Toastify__toast-body")).getText();
        Assert.assertEquals(message, "Session successfully created.");
    }


}
