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

public class Part1Tests {

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
    void Row41() {
        //load 4 players
        driver.get("http://localhost:3000");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("http://localhost:3000");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("http://localhost:3000");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("http://localhost:3000");
        //switch players
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        //start game
        driver.findElement(By.id("newGame")).click();
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD KC");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 3C 1H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        //play card
        driver.findElement(By.id("message")).sendKeys("3C");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: 3C", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

}
