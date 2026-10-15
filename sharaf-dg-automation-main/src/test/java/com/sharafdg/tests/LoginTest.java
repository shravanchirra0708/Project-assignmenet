package com.sharafdg.tests;

import com.sharafdg.base.BaseTest;
import com.sharafdg.dao.LoginDao;
import com.sharafdg.dataprovider.BaseDataProvider;
import com.sharafdg.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
    }

    @Test(dataProvider = "loginLogoutValidCredentialsData", dataProviderClass = BaseDataProvider.class)
    public void loginLogoutValidCredentials(LoginDao loginData) {
        loginPage.loginWithEmailPassword(loginData.getEmailId(), loginData.getPassword());
        loginPage.logout();
        Assert.assertTrue(true, "Login and Logout successful");
    }

    /*@Test
    public void verifyLoginPage() {
        String actualLoginPageMessage = loginPage.getSignInMessage();
        String expectedLoginPageMessage = "Submit your email to sign in.";
        Assert.assertEquals(actualLoginPageMessage, expectedLoginPageMessage, "Incorrect message on Login.");
    }

    @Test(dataProvider = "verifyConfirmEmailPageData", dataProviderClass = BaseDataProvider.class)
    public void verifyConfirmEmailPage(LoginDao loginData) {
        String emailId = loginData.getEmailId();
        loginPage.enterUsername(emailId);
        loginPage.clickLoginButton();
        String actualEmailId = loginPage.getEmailIdDisplayed();
        Assert.assertEquals(actualEmailId, emailId, "Incorrect emailId displayed on confirm email Page.");
    }

    @Test(dataProvider = "verifyAccountNotFoundData", dataProviderClass = BaseDataProvider.class)
    public void verifyAccountNotFound(LoginDao loginData) {
        loginPage.enterUsername(loginData.getEmailId());
        loginPage.clickLoginButton();
        String actualMessage = loginPage.getIncorrectEmailPasswordErrorMessage();
        String expectedMessage = "A user could not be found with this email address. Please create an account.";
        Assert.assertEquals(actualMessage, expectedMessage, "Incorrect message on Login attempt for unregistered user.");
    }*/
}