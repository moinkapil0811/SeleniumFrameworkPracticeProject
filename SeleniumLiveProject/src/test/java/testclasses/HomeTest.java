package testclasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.OnboardingPage;
import testbase.WebTestBase;

public class HomeTest extends WebTestBase {

    public HomePage homePage;
    public LoginPage loginPage;
    public MyAccountPage myAccountPage;
    public OnboardingPage onboardingPage;

    public HomeTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myAccountPage = new MyAccountPage(driver);
        onboardingPage=new OnboardingPage(driver);
    }

    @Test(description = "verify cart option are clickable on home page")
     public  void verifyCartOptionIsWorking() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(3000);
        homePage.clickOnCartImg();
        soft.assertEquals(homePage.getTextOnCartPage(),"CART","Cart,text should be match");
        soft.assertAll();
    }

    @Test(description = "verify add to cart functionality")
     public void verifyAddToCartFunctionality() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(3000);
        homePage.clickOnCartImg();
        homePage.clickOnContinueShoppingBtn();
        homePage.clickOnCoffeeOption();
        Thread.sleep(5000);
        String productName = prop.getProperty("productName");
        String productPrice = prop.getProperty("productPrice");
        homePage.clickShopNowForProduct(productName, productPrice);
        homePage.clickOnAddToCartBtn();
        String msg = homePage.getTextMsgAfterAddToCart();
        soft.assertEquals(msg.toLowerCase(), "item added to your cart!", "Add to cart message mismatch!");
    }

    @Test(description = "verify item is displayed in cart after addition")
       public void verifyItemIsDisplayedInCartAfterAddition() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(3000);
        homePage.clickOnCartImg();
        homePage.clickOnContinueShoppingBtn();
        homePage.clickOnCoffeeOption();
        Thread.sleep(3000);
        String productName = prop.getProperty("productName");
        String productPrice = prop.getProperty("productPrice");
        homePage.clickShopNowForProduct(productName, productPrice);
        homePage.clickOnAddToCartBtn();
        homePage.clickOnCheckNowButton();
        Thread.sleep(3000);
        soft.assertEquals(homePage.getTextOnCartAfterCheckNowClick(),"ECUADOR EL DORADO GEISHA","Ecuador El Dorado Geisha ,text should be match");
        soft.assertAll();
    }

    @Test(description = "verify item are successfully delete from cart")
    public void verifyDeleteCartItemByName() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
        homePage.clickOnCartImg();

    }

    @Test(description = "verify contact us functionality on home page")
    public void verifyContactUsFunctionality() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password") );
        Thread.sleep(3000);
        homePage.clcikOnAboutUs();
        homePage.clcikonsubmenuOptionContactUs();
        soft.assertEquals(homePage.getTextOnAboutUsContactPage(),"CONTACT US","Contact Us,text should be match");
        soft.assertAll();

    }


    @AfterMethod
    public void tearDown() {
        //driver.close();
    }

}
