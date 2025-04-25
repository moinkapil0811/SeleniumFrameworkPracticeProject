package testclasses;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;
import pages.OnboardingPage;
import testbase.WebTestBase;

import java.time.Duration;

public class LoginTest extends WebTestBase {

   public HomePage homePage;
   public LoginPage loginPage;
   public MyAccountPage myAccountPage;
   public OnboardingPage onboardingPage;

   public LoginTest(){
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

    @Test(description = "verify Login with valid username and valid password")
    public void verifyLoginWithValidCredential() {
      SoftAssert soft = new SoftAssert();
       homePage.clickOnAcceptCookies();
       homePage.clickOnCloseAd();
       homePage.clickOnUserAvtar();
       loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
       homePage.clickOnUserAvtar();
       soft.assertEquals(myAccountPage.getTextOfMyProfile(),"MY PROFILE","MY PROFILE text should be match");
       soft.assertAll();
    }

    @Test(description = "verify login with invalid username and password")
    public void verifyLoginWithInvalidCredential(){
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        loginPage.setLogin(prop.getProperty("username1"), prop.getProperty("password1"));
       soft.assertEquals(loginPage.getTextOfInvalidEmailOrPass(),"Incorrect email or password. Please try again","Incorrect email or password. Please try again text should be match");
        soft.assertAll();
    }

@Test(description = "verify error message when username and password are blank")
  public void verifyLoginWhenInputBlank(){
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.setLogin(prop.getProperty("username2"), prop.getProperty("password2"));

    soft.assertEquals(loginPage.getTextOnEmailIdTextBoxMsg(),"Email is mandatory","Email is mandatory. text should be match");
    soft.assertEquals(loginPage.getTextOnPasswordTextBoxMsg(),"Password is mandatory","Password is mandatory. text should be match");
    soft.assertAll();
}

@Test(description = "verify forget password link navigate to forget password page")
 public void verifyForgetPasswordLink(){
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.clickOnForgetPasswordLink();
    soft.assertEquals(loginPage.getTextOfResetPasswordPage(),"RESET YOUR PASSWORD","RESET YOUR PASSWORD, text should be match");
    soft.assertAll();
}

@Test(description = "verify create an account link navigate to onboarding page")
 public void verifyCreateAnAccountLink(){
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.clickOnCreateAccountLink();
    soft.assertEquals(loginPage.getTextOfCreateAnAccountPage(),"CREATE AN ACCOUNT","CREATE AN ACCOUNT, text should be match");
    soft.assertAll();
}


    @AfterMethod
    public void tearDown() {
       driver.close();

    }

}
