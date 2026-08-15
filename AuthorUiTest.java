import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.google.gson.Gson;
import org.openqa.selenium.JavascriptExecutor;

public class AuthorUiTest {

    public Author[] getAuthorsUi()throws InterruptedException{

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            driver.get("https://fakerestapi.azurewebsites.net/index.html");

            WebElement getAuthorsEndpoint = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='operations-Authors-get_api_v1_Authors']//div[@class='opblock-summary opblock-summary-get']")));

            getAuthorsEndpoint.click();
            
            WebElement TryItOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Try it out')]")));

            TryItOutButton.click();

            WebElement executeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Execute')]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", executeButton);

            executeButton.click();

            WebElement responseBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".responses-wrapper .highlight-code .microlight")));
            Thread.sleep(2000);

            String uiJson = responseBody.getText();

            Gson gson = new Gson();

            Author[] uiAuthors = gson.fromJson(uiJson, Author[].class);


            return uiAuthors;

        } finally {
            driver.quit();
        }
    }
}