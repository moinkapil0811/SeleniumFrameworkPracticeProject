package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.WaitUtility;
import utility.WebElementUtility;

public class OnboardingPage {
     WebDriver driver;

     @FindBy(xpath = "//a[@class='register-text']")
    WebElement clickOnRigister;

     @FindBy(xpath = "//span[text()='Create an Account']")
     WebElement onboardingPage;

     @FindBy(xpath = "//input[@data-id='firstName']")
     WebElement firstNameTextBox;

     @FindBy(xpath = "//input[@data-id='lastName']")
     WebElement lastNameTextBox;

     @FindBy(xpath = "//input[@data-id='email']")
     WebElement emailAddressTextBox;

     @FindBy(xpath = "//input[@data-id='password']")
     WebElement createPasswordTextBox;

     @FindBy(xpath = "//input[@data-id='confirmPassword']")
     WebElement confirmPasswordTextBox;

     @FindBy(xpath = "//button[text()='Register']")
     WebElement registerButton;

   @FindBy(xpath = "//div[text()='Password does not match. Please make sure both passwords match.']")
   WebElement errorMsgofConfirmPassTexBox;

   @FindBy(xpath = "//div[text()='Please enter a valid email address']")
   WebElement errorMsgOnMailIdTextBox;

   @FindBy(xpath = "//div[text()='Password must contain at least 8 characters.']")
   WebElement errorMsgonPasswordTextBox;

   @FindBy(xpath = "//a[text()='privacy policy.']")
   WebElement clickOnPrivacyLink;

   @FindBy(xpath = "//h1[text()='CAMPOS COFFEE PRIVACY POLICY']")
   WebElement privacyPolicy;

   @FindBy(xpath = "//div[text()='Email is mandatory.']")
   WebElement errorMsgBlankMailIdTextBox;

   @FindBy(xpath = "//div[text()='Password is mandatory.']")
   WebElement errorMsgOnBlankPassTextBox;

   @FindBy(xpath = "//*[contains(text(),'email address already exists')]")
   WebElement emailErrorMsg;


    public OnboardingPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOnRegisterLink(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnRigister);
    }

    public void clickOnPrivacyLink(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnPrivacyLink);
    }

    public String getTextOfOnboardingPage(){
        return WebElementUtility.getTextOfElement(onboardingPage);
    }
    public String getErrorMsgofConfirmPassTexBox(){
        return WebElementUtility.getTextOfElement(errorMsgofConfirmPassTexBox);
    }

    public String getErrorMsgOnMailIdTextBox(){
        return WebElementUtility.getTextOfElement(errorMsgOnMailIdTextBox);
    }
    public String getErrorMsgonPasswordTextBox(){
        return WebElementUtility.getTextOfElement(errorMsgonPasswordTextBox);
    }
    public String getErrorMsgMailIdTextBox(){
        return WebElementUtility.getTextOfElement(errorMsgBlankMailIdTextBox);
    }
    public String getErrorMsgOnBlankPassTextBox(){
        return WebElementUtility.getTextOfElement(errorMsgOnBlankPassTextBox);
    }
    public String getTextOnPrivacyPolicy(){
        WaitUtility.waitUntilElementToBeVisible(driver,privacyPolicy);
        return WebElementUtility.getTextOfElement(privacyPolicy);
    }

    public String getTextOfEmailErrorMsg(){
        WaitUtility.waitUntilElementToBeVisible(driver,emailErrorMsg);
        return WebElementUtility.getTextOfElement(emailErrorMsg);
    }

    public void setOnboarding(String firstName,String lastName,String emailAddress,String createPassword,String confirmPassword){
        WaitUtility.waitUntilElementToBeClickable(driver,firstNameTextBox);
        firstNameTextBox.sendKeys(firstName);

        WaitUtility.waitUntilElementToBeClickable(driver,lastNameTextBox);
        lastNameTextBox.sendKeys(lastName);

        WaitUtility.waitUntilElementToBeClickable(driver,emailAddressTextBox);
        emailAddressTextBox.sendKeys(emailAddress);

        WaitUtility.waitUntilElementToBeClickable(driver,createPasswordTextBox);
        createPasswordTextBox.sendKeys(createPassword);

        WaitUtility.waitUntilElementToBeClickable(driver,confirmPasswordTextBox);
        confirmPasswordTextBox.sendKeys(confirmPassword);

        WaitUtility.waitUntilElementToBeClickable(driver,registerButton);
    }

}
