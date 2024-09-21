package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GamesPage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class FilterTest {
    private static final Logger logger = LoggerFactory.getLogger(FilterTest.class);
    private WebDriver driver;
    private GamesPage page;

    private List<String> errors = new ArrayList<>();
    public final String filter = "strategy";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new GamesPage(driver);
        page.open();
    }

    @Test
    public void test(){
        logger.info("Запуск теста");
        try {
            logger.info("Проверяем заголовок страницы");
            if (!page.getHeader().contains("Main Page")) {
                String errorMessage = "Неверный заголовок страницы";
                logger.error(errorMessage);
                errors.add(errorMessage);
            }
            logger.info("Кликаем на фильтр");
            page.clickFilter();
            logger.info("Выбираем фильтр");
            page.chooseFilter(filter);
            page.getList();
            logger.info("Проверяем игры на соответствие фильтру");
            for (int i = 0; i < 10; i++) {
                if (!page.checkGenre(filter, i)) {
                    String errorMessage =
                            "Игра " + page.getName(i) + " не соответствует фильтру: " + page.getGenre(i) + " вместо " + filter;
                    logger.error(errorMessage);
                    errors.add(errorMessage);
                }
            }
            logger.info("Убираем фильтр, выбрав Not chosen");
            page.clickFilter();
            page.chooseFilter("\"not chosen\"");

            if (page.errorPage()){
                logger.error("Попали на страницу ошибки. Текст ошибки: " + page.getError());
            }
            else {
                logger.info("Проверим, что фильтр убрался и на старнице игры разных жанров");
                page.getList();
                Set<String> set = new HashSet<>();
                for (int i = 0; i < 10; i++) {
                    set.add(page.getGenre(i));
                }
                if (set.size() == 1){
                    logger.error("Отображены только игры в одном жанре");
                    errors.add("Отображены только игры в одном жанре, когда фильтр уже убран");
                }
            }

        } catch (Throwable t) {
            logger.error("Ошибка во время выполнения теста: {}", t.getMessage());
            t.printStackTrace();
            errors.add("Ошибка во время выполнения теста: " + t.getMessage());
        } finally {
            if (!errors.isEmpty()) {
                throw new AssertionError("Тест провален. Ошибки: " + String.join(", ", errors));
            }
            else {
                logger.info("Тест пройден успешно");
            }
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
