package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testbase.WebTestBase;
import utility.WaitUtility;
import utility.WebElementUtility;

import javax.xml.xpath.XPath;

public class LoginPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@data-id='inputUsername']")
    WebElement userNameTextBox;

    @FindBy(xpath = "//input[@data-id='inputPassword']")
    WebElement passwordTextBox;

    @FindBy(xpath = " //button[text()=' Login']")
    WebElement loginBtn;

    @FindBy(xpath = "//span[text()='Incorrect email or password. Please try again']")
    WebElement invalidEmailOrPass;

    @FindBy(xpath = "//div[text()='Email is mandatory']")
    WebElement emailIdTextBoxMsg;

    @FindBy(xpath = "//div[text()='Password is mandatory']")
    WebElement passwordTextBoxMsg;

    @FindBy(xpath = "//a[text()='Forgot your password?']")
    WebElement clickOnForgetPasswordLink;

    @FindBy(xpath = "//h2[text()='RESET YOUR PASSWORD']")
    WebElement resetPassPageText;

    @FindBy(xpath = "//a[text()='Create an Account']")
    WebElement  clickOnCreateAccoutLink;

    @FindBy(xpath = "//span[text()='Create an Account']")
        WebElement createAnAccountPageText;


    public void clickOnForgetPasswordLink(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnForgetPasswordLink);
    }

    public void clickOnCreateAccountLink(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnCreateAccoutLink);
    }

    public String getTextOfResetPasswordPage(){
        return WebElementUtility.getTextOfElement(resetPassPageText);
    }
    public String getTextOfCreateAnAccountPage(){
        return WebElementUtility.getTextOfElement(createAnAccountPageText);
    }

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String getTextOfInvalidEmailOrPass(){
        WaitUtility.waitUntilElementToBeVisible(driver,invalidEmailOrPass);
        return WebElementUtility.getTextOfElement(invalidEmailOrPass);
    }

    public String getTextOnEmailIdTextBoxMsg(){
        return WebElementUtility.getTextOfElement(emailIdTextBoxMsg);
    }

    public String getTextOnPasswordTextBoxMsg(){
        return WebElementUtility.getTextOfElement(passwordTextBoxMsg);
    }

    public void setLogin(String userName,String password){
        WaitUtility.waitUntilElementToBeClickable(driver,userNameTextBox);
        userNameTextBox.sendKeys(userName);
        WaitUtility.waitUntilElementToBeClickable(driver,passwordTextBox);
        passwordTextBox.sendKeys(password);
        WaitUtility.waitUntilElementToBeClickable(driver,loginBtn);
    }
}
