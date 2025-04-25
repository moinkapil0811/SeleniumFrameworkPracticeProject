package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.WaitUtility;
import utility.WebElementUtility;

import java.time.Duration;

import static org.openqa.selenium.By.*;

public class MyAccountPage {

  WebDriver driver;

@FindBy(xpath = "//h2[contains(text(), 'My Profile')]")
    WebElement myProfileText;

@FindBy(xpath = "//a[text()='Log Out']")
WebElement clickOnLogoutBtn;

@FindBy(xpath = "//input[@id='input-143']")
WebElement checkFirstNameIsEditable;

@FindBy(xpath = "//button[@class='slds-button']")
WebElement clcikOnEditButton;

//@FindBy(xpath = "//input[@id='input-143']")
 @FindBy(xpath = "//label[text()='First Name']/following::input[1]")
WebElement editFirstNameTextBox;

@FindBy(xpath = "//label[contains(text(),'Last Name')]/following::input[1]")
WebElement editLastNameTextBox;

@FindBy(xpath = "//label[contains(text(),'Phone Number')]/following::input[1]")
WebElement editPhoneNumberTextBox;

@FindBy(xpath = "//button[@class='slds-button slds-button_brand slds-button_stretch']")
WebElement clickOnSaveChangesBtn;

//@FindBy(xpath = "//*[contains(text(), "We couldn't update your details")]")
//WebElement getTextOnErrorMsg;

@FindBy(xpath = "//button[@class='slds-button slds-button_neutral slds-button_stretch']")
WebElement clickOnCancelChangesBtn;

@FindBy(xpath = "//button[text()='Change' and @aria-label='Change Password']")
WebElement clickOnChangePasswordBtn;

@FindBy(xpath = "//p[contains(text(), 'We sent an email to')]")
WebElement getDisplayDataAfterClickPassChange;

@FindBy(xpath = "//input[@id='input-47']")
    WebElement checkPasswordMask;

    public MyAccountPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String getTextOfMyProfile(){
        WaitUtility.waitUntilElementToBeVisible(driver,myProfileText);
        return WebElementUtility.getTextOfElement(myProfileText);
    }

    public void clickOnLogoutButton(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnLogoutBtn);
    }

    public void  clickOnCancelChangesBtn(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnCancelChangesBtn);
    }

    public void clickOnChangePasswordBtn(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnChangePasswordBtn);
    }

    public boolean isFirstNameTextBoxEditable() {
        try {
            WaitUtility.waitUntilElementToBeClickable(driver, checkFirstNameIsEditable);
            return checkFirstNameIsEditable.isEnabled() && checkFirstNameIsEditable.getAttribute("readonly") == null;
        } catch (Exception e) {
        System.out.println("Element not clickable: " + e.getMessage());
        return false;
        }
    }

    public void clickOnEditButtonOnMyProfilePage(){
     WaitUtility.waitUntilElementToBeClickable(driver,clcikOnEditButton);
    }

    public void clickOnSaveChangesBtn(){
        WaitUtility.waitUntilElementToBeClickable(driver,clickOnSaveChangesBtn);
    }

   public String getUpdateErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    xpath("//*[contains(text(), \"We couldn't update your details\")]")));
            return msg.getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public String getPasswordResetConfirmationMessage() {
        try {
            WaitUtility.waitUntilElementToBeVisible(driver, getDisplayDataAfterClickPassChange);
            return getDisplayDataAfterClickPassChange.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isPasswordMasked() {
        WaitUtility.waitUntilElementToBeVisible(driver,checkPasswordMask);
        boolean isReadOnly = checkPasswordMask.getAttribute("readonly") != null;
        boolean isTypeText = checkPasswordMask.getAttribute("type").equals("text");
        return isReadOnly && isTypeText;
    }


    public void setMyProfilePage(String firstName,String lastName,String phoneNumber){

        WaitUtility.waitUntilElementToBeClickable(driver,editFirstNameTextBox);
        editFirstNameTextBox.clear();
        editFirstNameTextBox.sendKeys(firstName);

        WaitUtility.waitUntilElementToBeClickable(driver,editLastNameTextBox);
        editLastNameTextBox.clear();
        editLastNameTextBox.sendKeys(lastName);

        WaitUtility.waitUntilElementToBeClickable(driver,editPhoneNumberTextBox);
        editPhoneNumberTextBox.sendKeys(phoneNumber);

        WaitUtility.waitUntilElementToBeClickable(driver,clickOnSaveChangesBtn);
    }
}
