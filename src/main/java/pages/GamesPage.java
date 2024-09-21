package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class GamesPage {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private List<WebElement> list;
    private List<WebElement> list1;

    public GamesPage(WebDriver driver) {
        GamesPage.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://makarovartem.github.io/frontend-avito-tech-test-assignment/");
    }

    public String getHeader() {
        return wait.until(driver -> driver.findElement(By.tagName("h1"))).getText();
    }

    public void clickFilter() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[2]/div[2]")));
        element.click();
    }
    public void chooseFilter(String category) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        WebElement element = wait.until(driver -> driver.findElement(By.className("rc-virtual-list-holder-inner")).findElement(By.cssSelector("div[title="+ category +"]")));
        element.click();
    }
    public void getList() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        list = driver.findElement(By.className("ant-list-items")).findElements(By.className("ant-list-item"));
    }
    public void copyList(){
        list1 = List.copyOf(list);
    }
    public boolean checkGenre(String text, int id) {
        return getGenre(id).toLowerCase().contains(text.toLowerCase());
    }
    public String getGenre(int id) {
        return list.get(id).findElement(By.xpath(".//div[contains(text(), 'Genre:')]")).getText();
    }
    public String getName(int i){
        return list.get(i).findElement(By.tagName("h1")).getText();
    }
    public boolean errorPage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return !driver.findElements(By.id("error-page")).isEmpty();
    }
    public String getError() {
        return driver.findElement(By.xpath("//*[@id=\"error-page\"]/div/div/div[2]/div[2]")).getText();
    }

    public void clickGame() {
        WebElement element = wait.until(driver -> driver.findElement(By.className("ant-card-body")));
        element.click();
    }

    public void clickBackToMain() {
        WebElement element = wait.until(driver -> driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div" +
                "/div[5]/div")).findElement(By.tagName("button")));
        element.click();
    }

    public void clickPage(int num) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        WebElement element =
                wait.until(driver -> driver.findElement(By.cssSelector("li[title=\""+num+"\"]")).findElement(By.tagName("a")));
        element.click();
    }
    public void clickBack() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        WebElement element =
                wait.until(driver -> driver.findElement(By.cssSelector("li[title=\"Previous Page\"]")).findElement(By.tagName("button")));
        element.click();
    }
    public void clickForward() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        WebElement element =
                wait.until(driver -> driver.findElement(By.cssSelector("li[title=\"Next Page\"]")).findElement(By.tagName("button")));
        element.click();
    }
    public boolean compareLists() {
        if (list.size() != list1.size()){
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Objects.equals(list.get(i).findElement(By.tagName("h1")).getText(), list1.get(i).findElement(By.tagName("h1")).getText())){
                return false;
            }
        }
        return true;
    }

    public boolean isPageActive(String pageNumber) {
        WebElement element = wait.until(driver -> driver.findElement(By.cssSelector("li[title=\""+pageNumber+"\"]")));
        return element.getAttribute("class").contains("ant-pagination-item-active");
    }
}
