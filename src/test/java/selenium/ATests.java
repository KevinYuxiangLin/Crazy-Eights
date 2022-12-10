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

public class ATests {

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

    @Test
    void Row42() {
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
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 2H");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 1H 5H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        //play card
        driver.findElement(By.id("message")).sendKeys("1H");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: 1H", driver.findElement(By.id("currentCard")).getText());
        //check direction
        assertEquals("The current direction is right", driver.findElement(By.id("direction")).getText());
        assertEquals("It is Player 4's turn", driver.findElement(By.id("turn")).getText());

        //switch players
        driver.switchTo().window(tabs.get(3));
//        Thread.sleep(5000);
        assertEquals("You are Player: 4", driver.findElement(By.id("playerId")).getText());
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 3 7H 5H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("7H");
        driver.findElement(By.id("send")).click();
        assertEquals("Current card: 7H", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 3's turn", driver.findElement(By.id("turn")).getText());

        driver.quit();
    }

    @Test
    void Row44() {
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
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 QC 1H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        //play card
        driver.findElement(By.id("message")).sendKeys("QC");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: QC", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 3's turn", driver.findElement(By.id("turn")).getText());
        //switch players
        driver.switchTo().window(tabs.get(1));
        assertEquals("Your turn got skipped!", driver.findElement(By.id("eightPlayed")).getText());
        driver.quit();
    }

    @Test
    void Row45() {
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
        //top card is KC (and set current turn to p4)
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD KC 3");
        driver.findElement(By.id("send")).click();
        driver.switchTo().window(tabs.get(3));
//        Thread.sleep(3000);
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 3 3C 1H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        //play card
        driver.findElement(By.id("message")).sendKeys("3C");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: 3C", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 1's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row46() {
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
        //top card is KC (and set current turn to p4)
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 3H 3");
        driver.findElement(By.id("send")).click();
        driver.switchTo().window(tabs.get(3));
//        Thread.sleep(3000);
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 3 1H 5H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        //play card
        driver.findElement(By.id("message")).sendKeys("1H");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: 1H", driver.findElement(By.id("currentCard")).getText());
        assertEquals("The current direction is right", driver.findElement(By.id("direction")).getText());
        assertEquals("It is Player 3's turn", driver.findElement(By.id("turn")).getText());

        driver.switchTo().window(tabs.get(2));
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 2 7H 5H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("7H");
        driver.findElement(By.id("send")).click();
        assertEquals("Current card: 7H", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());

        driver.quit();
    }

    @Test
    void Row48() {
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
        //top card is KC (and set current turn to p4)
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD KC 3");
        driver.findElement(By.id("send")).click();
        driver.switchTo().window(tabs.get(3));
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 3 QC 1H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        //play card
        driver.findElement(By.id("message")).sendKeys("QC");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: QC", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        //switch players
        driver.switchTo().window(tabs.get(0));
        assertEquals("Your turn got skipped!", driver.findElement(By.id("eightPlayed")).getText());
        driver.quit();
    }

    @Test
    void Row51() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD KC");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 KH 1H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("KH");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: KH", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row52() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD KC");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 7C 1H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("7C");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: 7C", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row53() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD KC");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 8H 1H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("8H");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Choose a suit to change to in the textbox", driver.findElement(By.id("eightPlayed")).getText());
        driver.findElement(By.id("message")).sendKeys("C");
        driver.findElement(By.id("send")).click();
        assertEquals("Current card: 8C", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row54() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD KC");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 5S 1H 2H 3H 4H");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("5S");
        driver.findElement(By.id("send")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        //current card should still be KC
        assertEquals("Current card: KC", driver.findElement(By.id("currentCard")).getText());
        //should still be player 1's turn
        assertEquals("It is Player 1's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row58() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 7C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 3H");
        driver.findElement(By.id("send")).click();
        //must draw card
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 6C 3C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("[3H]", driver.findElement(By.id("chat")).getText());
        assertEquals("Current card: 6C", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row59() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 7C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 3H");
        driver.findElement(By.id("send")).click();
        //must draw card
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 6D 5C 3C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: 5C", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row60() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 7C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 3H");
        driver.findElement(By.id("send")).click();
        //must draw card
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 6D 5S 7H 3C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("Current card: 7H", driver.findElement(By.id("currentCard")).getText());
        assertEquals("[3H, 6D, 5S]", driver.findElement(By.id("chat")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row61() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 7C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 3H");
        driver.findElement(By.id("send")).click();
        //must draw card
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 6D 5S 4H 3C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("[3H, 6D, 5S, 4H]", driver.findElement(By.id("chat")).getText());
        assertEquals("Current card: 7C", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row62() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 7C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 3H");
        driver.findElement(By.id("send")).click();
        //must draw card
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 6D 8H 3C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        driver.findElement(By.id("drawCard")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("[3H, 6D]", driver.findElement(By.id("chat")).getText());
        assertEquals("Choose a suit to change to in the textbox", driver.findElement(By.id("eightPlayed")).getText());
        driver.findElement(By.id("message")).sendKeys("D");
        driver.findElement(By.id("send")).click();
        assertEquals("Current card: 8D", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
        driver.quit();
    }

    @Test
    void Row63() {
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
        //top card is KC
        driver.findElement(By.id("message")).sendKeys("RIGGED_DISCARD 7C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("message")).sendKeys("RIGGED_HAND 0 KS 3C");
        driver.findElement(By.id("send")).click();
        //must draw card
        driver.findElement(By.id("message")).sendKeys("RIGGED_DECK 6C 3C");
        driver.findElement(By.id("send")).click();
        driver.findElement(By.id("drawCard")).click();
        System.out.println("TEST DEBUG, CURRENT TOP CARD: " + driver.findElement(By.id("currentCard")).getText());
        assertEquals("[KS, 3C]", driver.findElement(By.id("chat")).getText());
        assertEquals("Current card: 6C", driver.findElement(By.id("currentCard")).getText());
        assertEquals("It is Player 2's turn", driver.findElement(By.id("turn")).getText());
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
