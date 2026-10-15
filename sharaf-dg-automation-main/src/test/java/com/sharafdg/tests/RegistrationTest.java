package com.sharafdg.tests;

import com.sharafdg.base.BaseTest;
import com.sharafdg.dao.RegistrationDao;
import com.sharafdg.dataprovider.BaseDataProvider;
import com.sharafdg.page.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    private String testName = "";
    private static final String EMAIL_ERR_MSG = "Please enter a valid email address";
    private static final String PASSWORD_ERR_MSG = "Please enter your password";
    private static final String FNAME_ERR_MSG = "Please enter a first name";
    private static final String LNAME_ERR_MSG = "Please enter a last name";
    private static final String MOBILE_ERR_MSG = "Please enter a valid mobile number";
    RegistrationPage registrationPage;

    @BeforeMethod
    public void setUp() {
        registrationPage = new RegistrationPage(driver);
        registrationPage.navigateToRegistrationPage();
    }

    @Test(dataProvider = "submitValidRegistrationFormData", dataProviderClass = BaseDataProvider.class)
    public void submitValidRegistrationForm(RegistrationDao registrationDao) {
        registrationPage.enterEmailId(registrationDao.getEmailId());
        registrationPage.enterPassword(registrationDao.getPassword());
        registrationPage.enterFirstName(registrationDao.getFirstName());
        registrationPage.enterLastName(registrationDao.getLastName());
        registrationPage.enterMobileNumber(registrationDao.getMobileNumber());
        registrationPage.clickTncCheckBox();
        registrationPage.clickRegisterButton();
        registrationPage.logout();
        Assert.assertTrue(true, "Registration is successful. User Logged in and Logged out successful");
    }

}
