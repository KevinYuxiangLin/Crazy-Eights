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

}
