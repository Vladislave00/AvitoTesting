package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GamesPage;

public class BackToMainTest {
    private static final Logger logger = LoggerFactory.getLogger(FilterTest.class);
    private WebDriver driver;
    private GamesPage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new GamesPage(driver);
        page.open();
    }

    @Test
    public void test(){
        try {
            logger.info("Запуск теста");

            logger.info("Проверяем заголовок страницы");
            String mainPageHeader = page.getHeader();
            Assertions.assertTrue(mainPageHeader.contains("Main Page"), "Неверный заголовок страницы");

            logger.info("Переходим в карточку первой игры");
            page.clickGame();

            logger.info("Проверяем, что открылась страница игры");
            String gamePageHeader = page.getHeader();
            Assertions.assertTrue(gamePageHeader.contains("Game Page"), "Неверный заголовок страницы");

            logger.info("Нажимаем кнопку Back to main");
            page.clickBackToMain();

            logger.info("Проверяем заголовок страницы");
            String backToMainHeader = page.getHeader();
            Assertions.assertTrue(backToMainHeader.contains("Main Page"), "Неверный заголовок страницы");

            logger.info("Тест пройден");
        } catch (Throwable t) {
            logger.error("Тест не пройден");
            logger.error("Ошибка во время выполнения теста: {}", t.getMessage());
            t.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
