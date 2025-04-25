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

public class MyAccountTest extends WebTestBase {
    public HomePage homePage;
    public LoginPage loginPage;
    public MyAccountPage myAccountPage;
    public OnboardingPage onboardingPage;

public MyAccountTest() {
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

    @Test(description = "verify my account page after valid username and password on login page")
    public void verifyMyAccountPageAfterLogin() {
        SoftAssert soft =new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password") );
    homePage.clickOnUserAvtar();
    soft.assertEquals(myAccountPage.getTextOfMyProfile(),"MY PROFILE","MY PROFILE,text should be match");
    soft.assertAll();
    }
@Test(description = "verify logout button is visible and accessible in my account page")
 public void verifyLogOutButtonOnMyAccountPage(){
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
    homePage.clickOnUserAvtar();
    myAccountPage.clickOnLogoutButton();
    soft.assertEquals(homePage.getTextOnLoginPage(),"LOG IN","LOG IN , text should be match");
    soft.assertAll();
}

@Test(description = "verify change password is mask on my account page")
 public void verifyChangePasswordMask(){
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
    homePage.clickOnUserAvtar();
    soft.assertTrue(myAccountPage.isPasswordMasked(), "Password field should be masked (readonly and of type text)");

}

@Test(description = "verify user information editable without click on edit button on my account page")
 public void verifyPersonalInfoNotEditable() throws InterruptedException {
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
    homePage.clickOnUserAvtar();
    Thread.sleep(5000);
    boolean isEditable = myAccountPage.isFirstNameTextBoxEditable();
    soft.assertFalse(isEditable, "First name textbox should not be editable without clicking edit.");

}

    @Test(description = "Verify that user can edit personal information after clicking the Edit button on My Account page")
    public void verifyUserCanEditPersonalInfoAfterClickingEditButton() throws InterruptedException {
        SoftAssert soft = new SoftAssert();
        homePage.clickOnAcceptCookies();
        homePage.clickOnCloseAd();
        homePage.clickOnUserAvtar();
        loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
        Thread.sleep(3000);
        homePage.clickOnUserAvtar();
        Thread.sleep(5000);
        myAccountPage.clickOnEditButtonOnMyProfilePage();
       myAccountPage.setMyProfilePage(prop.getProperty("firstName1"), prop.getProperty("lastName1"),prop.getProperty("phoneNumber") );
       myAccountPage.clickOnSaveChangesBtn();
        String errorMsg = myAccountPage.getUpdateErrorMessage();
        System.out.println("Update Error Message: " + errorMsg);
    }

@Test(description = "Verify revert changes after click on cancel changes button")
    public void verifyRevertChangesAfterClickOnCancelChangeButton() throws InterruptedException {
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
    Thread.sleep(3000);
    homePage.clickOnUserAvtar();
    Thread.sleep(5000);
    myAccountPage.clickOnEditButtonOnMyProfilePage();
    myAccountPage.setMyProfilePage(prop.getProperty("firstName1"), prop.getProperty("lastName1"),prop.getProperty("phoneNumber") );
    myAccountPage.clickOnCancelChangesBtn();
    soft.assertEquals(myAccountPage.getTextOfMyProfile(),"MY PROFILE","MY PROFILE,text should be match");
    soft.assertAll();
}

@Test(description = "verify data after click on change password  button om my account page")
 public void verifyTextDataAfterClickOnChangePassButton() throws InterruptedException {
    SoftAssert soft = new SoftAssert();
    homePage.clickOnAcceptCookies();
    homePage.clickOnCloseAd();
    homePage.clickOnUserAvtar();
    loginPage.setLogin(prop.getProperty("username"), prop.getProperty("password"));
    homePage.clickOnUserAvtar();
    Thread.sleep(3000);
    myAccountPage.clickOnChangePasswordBtn();
    String actualMsg = myAccountPage.getPasswordResetConfirmationMessage();
    soft.assertTrue(actualMsg.contains("We sent an email to"), "Confirmation message not as expected.");
    soft.assertAll();
}

    @AfterMethod
    public void tearDown() {
        //driver.close();
    }

}

