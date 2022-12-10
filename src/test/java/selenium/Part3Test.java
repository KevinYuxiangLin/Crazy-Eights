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

public class Part3Test {

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
    void AltPart3Test() {
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
        //initial hands
        //player 1
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 4H 7S 5D 6D 9D");
        driver.findElement(By.id("send")).click();
        //player 2
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 1 4S 6S KC 8H 10D");
        driver.findElement(By.id("send")).click();
        //player 3
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 2 9S 6C 9C JD 3H");
        driver.findElement(By.id("send")).click();
        //player 4
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 3 7D JH QH KH 5C");
        driver.findElement(By.id("send")).click();

        //initial discard
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 4D");
        driver.findElement(By.id("send")).click();

        //player 1 turn
        driver.findElement(By.id("message")).sendKeys("4H");
        driver.findElement(By.id("send")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("4S");
        driver.findElement(By.id("send")).click();

        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("9S");
        driver.findElement(By.id("send")).click();

        //player 4 turn
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 2C 3C 4C 7S");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();

        //player 1 turn
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("message")).sendKeys("7S");
        driver.findElement(By.id("send")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("6S");
        driver.findElement(By.id("send")).click();

        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("6C");
        driver.findElement(By.id("send")).click();

        //player 4 turn
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("message")).sendKeys("2C");
        driver.findElement(By.id("send")).click();

        //player 1 turn
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 10H JC 7S");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("KC");
        driver.findElement(By.id("send")).click();

        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("9C");
        driver.findElement(By.id("send")).click();

        //player 4 turn
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("message")).sendKeys("3C");
        driver.findElement(By.id("send")).click();

        //player 1 turn
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 7C 7S");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("8H");
        driver.findElement(By.id("send")).click();
        assertEquals("Choose a suit to change to in the textbox", driver.findElement(By.id("eightPlayed")).getText());
        driver.findElement(By.id("message")).sendKeys("D");
        driver.findElement(By.id("send")).click();
        assertEquals("Current card: 8D", driver.findElement(By.id("currentCard")).getText());

        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("JD");
        driver.findElement(By.id("send")).click();

        //player 4 turn
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("message")).sendKeys("7D");
        driver.findElement(By.id("send")).click();

        //player 1 turn
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("message")).sendKeys("9D");
        driver.findElement(By.id("send")).click();

        //player 2 empties hand next turn, row 28, check hands
        driver.switchTo().window(tabs.get(0));
        assertEquals("[5D, 6D, 10H]", driver.findElement(By.id("chat")).getText());
        driver.switchTo().window(tabs.get(2));
        assertEquals("[3H]", driver.findElement(By.id("chat")).getText());
        driver.switchTo().window(tabs.get(3));
        assertEquals("[JH, QH, KH, 5C, 4C]", driver.findElement(By.id("chat")).getText());

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("10D");
        driver.findElement(By.id("send")).click();

        //check scores
        assertEquals("Scores - Player 1: 21 Player 2: 0 Player 3: 3 Player 4: 39", driver.findElement(By.id("scores")).getText());

        //new round, should be player 2's turn
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        //initial discard
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 4D");
        driver.findElement(By.id("send")).click();
        //initial hands
        //player 1
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 7D 4S 7C 4H 5D");
        driver.findElement(By.id("send")).click();
        //player 2
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 1 9D 3S 9C 3H JC");
        driver.findElement(By.id("send")).click();
        //player 3
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 2 3D 9S 3C 9H 5H");
        driver.findElement(By.id("send")).click();
        //player 4
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 3 4D 7S 4C 5S 8D");
        driver.findElement(By.id("send")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("9D");
        driver.findElement(By.id("send")).click();

        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("3D");
        driver.findElement(By.id("send")).click();

        //player 4 turn
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("message")).sendKeys("4D");
        driver.findElement(By.id("send")).click();

        //player 1 turn
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("message")).sendKeys("4S");
        driver.findElement(By.id("send")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("3S");
        driver.findElement(By.id("send")).click();

        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("9S");
        driver.findElement(By.id("send")).click();

        //player 4 turn
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("message")).sendKeys("7S");
        driver.findElement(By.id("send")).click();

        //player 1 turn
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("message")).sendKeys("7C");
        driver.findElement(By.id("send")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("9C");
        driver.findElement(By.id("send")).click();

        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("3C");
        driver.findElement(By.id("send")).click();

        //player 4 turn
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("message")).sendKeys("4C");
        driver.findElement(By.id("send")).click();

        //player 1 turn
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("message")).sendKeys("4H");
        driver.findElement(By.id("send")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("3H");
        driver.findElement(By.id("send")).click();

        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("9H");
        driver.findElement(By.id("send")).click();

        //player 4 turn
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK KS QS KH 7S");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();

        //player 1 turn
        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 6D QD JD 7S");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();

        //player 2 turn
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 6S JS 10S 7S");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();

        //player 3 empties hand next turn, row 63, check hands
        driver.switchTo().window(tabs.get(0));
        assertEquals("[7D, 5D, 6D, QD, JD]", driver.findElement(By.id("chat")).getText());
        driver.switchTo().window(tabs.get(1));
        assertEquals("[JC, 6S, JS, 10S]", driver.findElement(By.id("chat")).getText());
        driver.switchTo().window(tabs.get(3));
        assertEquals("[5S, 8D, KS, QS]", driver.findElement(By.id("chat")).getText());
        //player 3 turn
        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("5H");
        driver.findElement(By.id("send")).click();

        //determine winner and final scores
        assertEquals("Scores - Player 1: 59 Player 2: 36 Player 3: 3 Player 4: 114", driver.findElement(By.id("scores")).getText());
        assertEquals("The winner is: 3!", driver.findElement(By.id("eightPlayed")).getText());
        driver.quit();
    }

}
