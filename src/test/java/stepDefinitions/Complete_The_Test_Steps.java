package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static driver.DriverFactory.getDriver;

public class Complete_The_Test_Steps {

    private WebDriver driver = getDriver();

    @Given("I access witwiser login page")
    public void i_access_witwiser_login_page() {
        driver.get("https://testing-app.witwiser.io/login");
    }

    @When("I enter username {}")
    public void i_enter_username(String username) {
        driver.findElement(By.name("username")).sendKeys(username);
    }

    @And("I enter password {}")
    public void i_enter_password(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @And("I click the login button")
    public void i_click_the_login_button() throws InterruptedException {
        driver.findElement(By.className("src-scenes-dashboard-components-LoginCard-LoginCard__buttonText")).click();
        Thread.sleep(6000);
    }

    @And("I click on the Start Test Button")
    public void i_click_on_the_start_test_button() throws InterruptedException {
        driver.findElement(By.className("src-scenes-dashboard-components-WarningInfo-WarningInfo__buttonElementText")).click();
        Thread.sleep(3000);
    }

    @And("I click on the Start Test")
    public void i_click_on_the_start_test() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[1]/button")).click();
        Thread.sleep(2000);
    }

    @And("I answer the question one and click next button")
    public void i_answer_the_question_one_and_click_next_button() throws InterruptedException {
        this.answerTheQuestion();
        Thread.sleep(2000);
        this.findNextButton().click();
        Thread.sleep(2000);
    }

    @And("I answer the question two and click next button")
    public void i_answer_the_question_two_and_click_next_button() throws InterruptedException {
        this.answerTheQuestion();
        Thread.sleep(2000);
        this.findNextButton().click();
        Thread.sleep(2000);
    }

    @And("I answer the question three and click next button")
    public void i_answer_the_question_three_and_click_next_button() throws InterruptedException {
        this.answerTheQuestion();
        Thread.sleep(2000);
        this.findNextButton().click();
        Thread.sleep(2000);
    }


    @And("I answer the question four and click finish button")
    public void i_answer_the_question_four_and_click_next_button() throws InterruptedException {
        List<WebElement> dragAndDropElements = driver.findElements(By.cssSelector("div.src-common-components-Questions-DragAndDrop-BlankList__blankWrapper span"));
        List<WebElement> targetElements = driver.findElements(By.cssSelector("div.ql-editor span[data-react-beautiful-dnd-droppable='0']"));
        Actions act = new Actions(driver);
        System.out.println("___: " + dragAndDropElements.size() + "  " + targetElements.size());
        for (int i = 0; i < dragAndDropElements.size() - 1; i++) {

            int x = targetElements.get(i).getLocation().x;
            int y = targetElements.get(i).getLocation().y;


            Action dragAndDrop = act.clickAndHold(dragAndDropElements.get(i))
                    .pause(Duration.ofSeconds(1))
                    .moveToElement(targetElements.get(i))
                    .release(targetElements.get(i)).build();
            dragAndDrop.perform();
        }
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button.src-scenes-session-pages-test-SessionTest__finishTestButton")).click();
        Thread.sleep(2000);

    }


    @And("I click finish test button")
    public void i_click_finish_test_button() throws InterruptedException {
        driver.findElement(By.cssSelector("button.src-common-components-AssesmentButton-AssesmentButton__button")).click();
        Thread.sleep(5000);
    }

    @Then("I should see Completed Message on screen")
    public void i_should_see_completed_message_on_screen(){
        String buttonText = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[1]/button")).getText();
        Assert.assertEquals(buttonText, "Completed");
    }


    private WebElement findListQuestionAnswer(){
        return driver.findElement(By.cssSelector("div.src-common-components-Questions-Questions__listItem label .src-common-components-RichLabel-RichLabel__richLabel"));
    }

    private WebElement findNextButton(){
        return driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div[3]/div/div[2]/div[3]/div[2]/div/img[2]"));
    }

    private void answerTheQuestion() {

        if (isListQuestion()) {
            this.findListQuestionAnswer().click();
        } else if (isTextQuestion()) {
            driver.findElement(By.cssSelector("div.src-common-components-Questions-Questions__textEditor div.ql-container div.ql-editor")).sendKeys("deneme");
        }
    }

    private boolean isListQuestion() {
        try {
            if (driver.findElement(By.cssSelector("div.src-common-components-Questions-Questions__listItem label .src-common-components-RichLabel-RichLabel__richLabel")) != null) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isTextQuestion() {
        try {
            if (driver.findElement(By.cssSelector("div.src-common-components-Questions-Questions__textEditor div.ql-container div.ql-editor")) != null) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
