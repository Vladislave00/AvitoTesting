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


public class PaginationTest {
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
            logger.info("Переходим на 3 страницу");
            page.clickPage(3);
            logger.info("Проверяем, что в пагинации выделена правильная страница");
            Assertions.assertTrue(page.isPageActive("3"), "Открыта не та страница");
            logger.info("Сохраняем массив игр со страницы");
            page.getList();
            page.copyList();
            logger.info("Переходим на страницу назад");
            page.clickBack();
            logger.info("Проверяем, что в пагинации выделена правильная страница");
            Assertions.assertTrue(page.isPageActive("2"), "Открыта не та страница");
            logger.info("Переходим на страницу вперед");
            page.clickForward();
            logger.info("Проверяем, что в пагинации выделена правильная страница");
            Assertions.assertTrue(page.isPageActive("3"), "Открыта не та страница");
            page.getList();
            logger.info("Сверяем игры с сохраненным ранее списком");
            Assertions.assertTrue(page.compareLists(), "Элементы на странице не совпадают с сохраненными с этой страницы ранее");
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
