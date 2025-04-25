package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.ActionsUtility;
import utility.JavaScriptUtility;
import utility.WaitUtility;
import utility.WebElementUtility;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.List;

import static utility.WaitUtility.wait;

public class HomePage {
    WebDriver driver;

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptAllCookies;


    @FindBy(xpath = "//button[@title='Close']")
    WebElement closeAd;

    @FindBy(xpath = "//img[@alt='user avatar']")
    WebElement userAvtar;

    @FindBy(xpath = "//h1[text()='Log In']")
    WebElement getTextOnLoginPage;

    @FindBy(xpath = "//img[@src='/webruntime/org-asset/d65cb44aea/resource/081J4000000TPqk']\n")
    WebElement clickOnCartImg;

    @FindBy(xpath = "//h1[text()='Cart']")
    WebElement getTextOnCartPage;

    @FindBy(xpath = "//a[text()='Continue Shopping']")
    WebElement clickOnContinueShoppingBtn;

    @FindBy(xpath = "//a[text()='Coffee']")
    WebElement clickOnCoffeeOption;

    @FindBy(xpath = "//button[text()='Checkout Now']")
     WebElement clickOnCheckNowButton;

    @FindBy(xpath = "//h2[text()='Ecuador El Dorado Geisha']")
       WebElement getTextOnCartAfterCheckNowClick;

    @FindBy(xpath = "//div[contains(@class, 'tile-product-main')]")
    List<WebElement> productTiles;


    @FindBy(xpath = "//span[text()='Add to Cart']")
    WebElement clickOnAddToCartBtn;

    @FindBy(xpath = "//h1[text()='item added to your cart!']")
    WebElement getTextMsgAfterAddToCart;

    @FindBy(xpath = "//button[text()='Delete']")
    WebElement clickOnDeleteBtn;

    @FindBy(xpath = "//ul[contains(@class,'submenu')]//li[@data-label='Contact Us']")
    WebElement clcikonsubmenuOptionContactUs;

    @FindBy(xpath = "//div[text()='About Us']")
    WebElement clcikOnAboutUs;

    @FindBy(xpath = "//h1[text()='Contact Us']")
    WebElement getTextOnAboutUsContactPage;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void clickOnAcceptCookies() {
        WaitUtility.waitUntilElementToBeClickable(driver, acceptAllCookies);
    }

    public void clcikOnAboutUs(){
        WaitUtility.waitUntilElementToBeClickable(driver,clcikOnAboutUs);
   }

   public void clcikonsubmenuOptionContactUs(){
        WaitUtility.waitUntilElementToBeClickable(driver,clcikonsubmenuOptionContactUs);
   }

    public  void clickOnCloseAd(){
        WaitUtility.waitUntilElementToBeClickable(driver,closeAd);
    }

    public void clickOnDeleteBtn(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnDeleteBtn);
    }

    public void clickOnUserAvtar() {
        try {
            WaitUtility.waitUntilElementToBeClickable(driver, userAvtar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        WaitUtility.waitUntilElementToBeClickable(driver, userAvtar);
    }

    public String getTextOnLoginPage(){
        WaitUtility.waitUntilElementToBeVisible(driver,getTextOnLoginPage);
        return WebElementUtility.getTextOfElement(getTextOnLoginPage);
    }

    public String getTextOnAboutUsContactPage(){
        WaitUtility.waitUntilElementToBeVisible(driver,getTextOnAboutUsContactPage);
        return WebElementUtility.getTextOfElement(getTextOnAboutUsContactPage);
    }

    public String getTextOnCartAfterCheckNowClick(){
        WaitUtility.waitUntilElementToBeVisible(driver,getTextOnCartAfterCheckNowClick);
        return WebElementUtility.getTextOfElement(getTextOnCartAfterCheckNowClick);
    }

    public void clickOnCartImg(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnCartImg);
    }

    public String getTextOnCartPage(){
        WaitUtility.waitUntilElementToBeVisible(driver,getTextOnCartPage);
        return WebElementUtility.getTextOfElement(getTextOnCartPage);
    }

    public String getTextMsgAfterAddToCart(){

        WaitUtility.waitUntilElementToBeVisible(driver,getTextMsgAfterAddToCart);
        return WebElementUtility.getTextOfElement(getTextMsgAfterAddToCart);
    }

    public void clickOnContinueShoppingBtn(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnContinueShoppingBtn);
    }

    public void clickOnCoffeeOption(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnCoffeeOption);
    }

    public void clickOnAddToCartBtn(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnAddToCartBtn);
    }

    public void clickOnCheckNowButton(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnCheckNowButton);
    }

   public void clickShopNowForProduct(String productName, String expectedPrice) {
       for (WebElement tile : productTiles) {
           String name = tile.findElement(By.cssSelector(".tile__product-name")).getText().trim();
           String price = tile.findElement(By.cssSelector(".tile-product_price span")).getText().trim();

           if (name.equalsIgnoreCase(productName) && price.equalsIgnoreCase(expectedPrice)) {

               JavaScriptUtility.scrollIntoView(driver, tile);
               ActionsUtility.mouseHover(driver, tile);

               WebElement shopNowBtn = tile.findElement(By.cssSelector("button.tile_shopNowBtn"));

               try {
                   WaitUtility.waitUntilElementToBeClickable(driver, shopNowBtn);
               } catch (Exception e) {
                   JavaScriptUtility.clickWithJS(driver, shopNowBtn);
               }

               return;
           }
       }

       throw new NoSuchElementException("Product with name '" + productName + "' and price '" + expectedPrice + "' not found.");
   }


}
