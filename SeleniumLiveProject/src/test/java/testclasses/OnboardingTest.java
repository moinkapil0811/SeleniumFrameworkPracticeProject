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

public class OnboardingTest extends WebTestBase {

    public HomePage homePage;
    public LoginPage loginPage;
    public MyAccountPage myAccountPage;
    public OnboardingPage onboardingPage;

    public OnboardingTest(){
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


    @Test(description = "verify create account link is clickable ")
    public void verifyCreateAccoutLink(){
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        onboardingPage.clickOnRegisterLink();
        soft.assertEquals(onboardingPage.getTextOfOnboardingPage(),"CREATE AN ACCOUNT","CREATE AN ACCOUNT text should be match");
        soft.assertAll();
    }

    @Test(description = "verify account creation with valid input data on onboarding page ")
        public void verifyCreateAnAccount() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        onboardingPage.clickOnRegisterLink();
        onboardingPage.setOnboarding(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("emailaddress"),
                prop.getProperty("createpassword"), prop.getProperty("confirmpassword") );
        Thread.sleep(5000);
        homePage.clickOnUserAvtar();
        soft.assertEquals(myAccountPage.getTextOfMyProfile(),"MY PROFILE","MY PROFILE text should be match");
        soft.assertAll();
    }

    @Test(description = "verify account creation with invalid confirm password")
    public void verifyAccountCreationWithInavlidPassword(){
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        onboardingPage.clickOnRegisterLink();
        onboardingPage.setOnboarding(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("emailaddress"),
                prop.getProperty("createpassword"), prop.getProperty("confirmpassword1") );
        soft.assertEquals(onboardingPage.getErrorMsgofConfirmPassTexBox(), "Password does not match. Please make sure both passwords match.", "Password does not match. Please make sure both passwords match. text should be match");
        soft.assertAll();
    }

    @Test(description = "verify account creation with already registered user email ID")
    public void verifyAccoutCreationWithegisteredUser(){
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        onboardingPage.clickOnRegisterLink();
        onboardingPage.setOnboarding(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("emailaddress"),
                prop.getProperty("createpassword"), prop.getProperty("confirmpassword") );
        soft.assertEquals(onboardingPage.getTextOfEmailErrorMsg(), "An account with your email address already exists.", "An account with your email address already exists.text should be match");
        soft.assertAll();

    }

     @Test(description = "verify account creation email id without '@' symbol")
       public void verifyEmailWithoutSymbol(){
         SoftAssert soft = new SoftAssert();
         homePage.clickOnAcceptCookies();
         homePage.clickOnCloseAd();
         homePage.clickOnUserAvtar();
         onboardingPage.clickOnRegisterLink();
         onboardingPage.setOnboarding(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("invalidemailaddress"),
                                       prop.getProperty("createpassword"), prop.getProperty("confirmpassword") );
         soft.assertEquals(onboardingPage.getErrorMsgOnMailIdTextBox(), "Please enter a valid email address", "Please enter a valid email address, text should be match");
         soft.assertAll();
     }

@Test(description = "verify email address contains more than 50 character on onboarding page")
  public void verifyEmailWithMaxChar(){
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    onboardingPage.clickOnRegisterLink();
    onboardingPage.setOnboarding(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("mailaddress"),
            prop.getProperty("createpassword"), prop.getProperty("confirmpassword") );
}

@Test(description = "verify password creation length on onboarding page ")
   public  void verifyAccountCreationWithMaxPassLength(){
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    onboardingPage.clickOnRegisterLink();
    onboardingPage.setOnboarding(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("emailaddress"),
            prop.getProperty("createpass"), prop.getProperty("confirmpassword") );
    soft.assertEquals(onboardingPage.getErrorMsgonPasswordTextBox(), "Password must contain at least 8 characters.", "Password must contain at least 8 characters., text should be match");
    soft.assertAll();
}

@Test(description = "verify privacy policy link is clickable on onboarding page")
 public void verifyPrivacyLinkClickable(){
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    onboardingPage.clickOnRegisterLink();
    onboardingPage.clickOnPrivacyLink();
    soft.assertEquals(onboardingPage.getTextOnPrivacyPolicy(), "CAMPOS COFFEE PRIVACY POLICY", "CAMPOS COFFEE PRIVACY POLICY, text should be match");
    soft.assertAll();
}

@Test(description = "verify account creation without email id and password on onboarding page")
  public void verifyAccountCreationWithoutEmailId(){
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    onboardingPage.clickOnRegisterLink();
    onboardingPage.setOnboarding(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("emailaddr"),
            prop.getProperty("passworld"), prop.getProperty("confirmpassword") );
        soft.assertEquals(onboardingPage.getErrorMsgMailIdTextBox(),"Email is mandatory.","Email is mandatory. text should be match");
        soft.assertEquals(onboardingPage.getErrorMsgOnBlankPassTextBox(),"Password is mandatory.","Password is mandatory. text should be match");
        soft.assertAll();
}

    @AfterMethod
    public void tearDown() {
         driver.close();
    }


}
