package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import project.game.JavalinWebsocketExampleApp;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Part2Tests {

    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
        JavalinWebsocketExampleApp.main(null);
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void Row77() {
        driver.get("http://localhost:3000");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("http://localhost:3000");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("http://localhost:3000");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("http://localhost:3000");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("newGame")).click();
        //player 1
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 1S");
        driver.findElement(By.id("send")).click();
        //player 2
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 1 ");
        driver.findElement(By.id("send")).click();
        //player 3
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 2 8H JH 6H KH KS");
        driver.findElement(By.id("send")).click();
        //player 4
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 3 8C 8D 2D");
        driver.findElement(By.id("send")).click();

        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK ");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();

        //determine winner
        assertEquals("Scores - Player 1: 1 Player 2: 0 Player 3: 86 Player 4: 102", driver.findElement(By.id("scores")).getText());
        assertEquals("The winner is: 2!", driver.findElement(By.id("eightPlayed")).getText());
        driver.quit();
    }

}
