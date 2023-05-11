package com.placelab.aldinrizvo.tests;

import com.placelab.aldinrizvo.pages.ForgotPasswordPage;
import com.placelab.aldinrizvo.pages.LoginPage;
import com.placelab.aldinrizvo.pages.RecoveryEmailSentPage;
import com.placelab.aldinrizvo.utils.WebDriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RestorePasswordTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ForgotPasswordPage forgotPasswordPage;
    private RecoveryEmailSentPage recoveryEmailSentPage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true, groups = {"Positive", "Negative"})
    public void setup(final String browser) {
        this.driver = WebDriverSetup.getWebDriver(browser);
        this.driver.get("https://demo.placelab.com/");
        this.loginPage = new LoginPage(driver);
        this.forgotPasswordPage = new ForgotPasswordPage(driver);
        this.recoveryEmailSentPage = new RecoveryEmailSentPage(driver);
    }

    @Parameters("email")
    @Test(priority = 5, groups = {"PasswordRestore", "Positive"})
    public void testPasswordRestore(final String email) {
        this.loginPage.validateLoginPageContent();
        this.loginPage.clickForgotPasswordLink();

        this.forgotPasswordPage.validateForgotPasswordPageContent();
        this.forgotPasswordPage.enterEmailCredential(email);
        this.forgotPasswordPage.clickContinueButton();

        this.recoveryEmailSentPage.validateRecoveryEmailSentPageContent();
    }

    @AfterMethod(alwaysRun = true, groups = {"Positive", "Negative"})
    public void teardown() {
        driver.close();
    }
}
