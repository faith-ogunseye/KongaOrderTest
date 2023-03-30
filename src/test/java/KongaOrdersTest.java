import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class KongaOrdersTest{

    //Konga odering flow
    private WebDriver driver;


    @Test
    public void start() throws InterruptedException{
        //locate where the chromedriver.exe is
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        //Allow remote access for web access
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //1.open your Chrome browser
        driver = new ChromeDriver(options);
        //. maximize the browser
        driver.manage().window().maximize();
        //2.imput your Konga URL (https://konga.com)
        driver.get("https://konga.com");
        Thread.sleep(7000);
        //3.sign In to konga sucessfully
       // driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/nav/div[2]/div[1]/div/div/div[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
        Thread.sleep(3000);

        //3a. Find Input with Name username and provide username
        driver.findElement(By.id("username")).sendKeys("**********");
        //3b. Find Input with Name password and provide password
        driver.findElement(By.id("password")).sendKeys("********");
        //3c. Find Sign In button and initiate click
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
        Thread.sleep(4000);
        //4. Click on Computers and accessories
        driver.findElement(By.xpath( "//*[@id=\"nav-bar-fix\"]/div[2]/div/a[2]")).click();
        Thread.sleep(4000);
        //4.click of the laptop Subcategory
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a")).click();
        Thread.sleep(4000);
        //5. click on the Apple Macbooks
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li/a/ul/li[1]/a")).click();
        Thread.sleep(3000);
        //6. add a selected macbook to cart
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/section/section/section/ul/li[2]/div/div/div[2]/form/div[3]/button")).click();
        Thread.sleep(3000);
        //7. click on Mycart
        driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]")).click();
        Thread.sleep(3000);
        //8. Click on checkout button
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
        Thread.sleep(4000);
        //9. CLick on Add delivery address
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
        Thread.sleep(3000);

        //10.Select address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
        Thread.sleep(3000);
        // click on use this address
        driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
        Thread.sleep(3000);

        //11.continue to make payment - click on pay now
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
        Thread.sleep(7000);
        // switch driver to the konga pay iframe to simulate acivites on the portal
        driver.switchTo().frame("kpg-frame-component");
        //12.select a card payment method
        driver.findElement(By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button")).click();
        Thread.sleep(3000);
        //13.input invalid card details
        driver.findElement(By.id("card-number")).sendKeys("5462675733125093");
        Thread.sleep(1000);
        driver.findElement(By.id("expiry")).sendKeys("02/25");
        Thread.sleep(1000);
        driver.findElement(By.id("cvv")).sendKeys("110");
        Thread.sleep(1000);
        driver.findElement(By.id("card-pin-new")).click();
        Thread.sleep(1000);
        // click on buttons to set card pin
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[5]")).click();
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[4]")).click();
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"keypads\"]/button[1]")).click();
        //click on pay now
        driver.findElement(By.id("validateCardForm")).click();
        //14.check that a validation error thrown is indicating invalid card number
        Assert.assertEquals(driver.findElement(By.id("card-number_unhappy")).getText(), "Invalid card number");

        //15.close the iframe that displays the input card modal
        driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[1]/aside")).click();
        //16.quit the browser
        driver.quit();



    }


}
