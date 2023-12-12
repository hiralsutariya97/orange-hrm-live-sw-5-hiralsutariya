package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class LoginTest extends BaseTest {

    //Declaration
    AddUserPage addUserPage;
    AdminPage adminPage;
    DashboardPage dashboardPage;
    HomePage homePage;
    LoginPage loginPage;
    ViewSystemUsersPage viewSystemUserPage;


    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        addUserPage = new AddUserPage();
        adminPage = new AdminPage();
        dashboardPage = new DashboardPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        viewSystemUserPage = new ViewSystemUsersPage();
    }

    @Test(groups = {"Sanity", "Smoke", "Regression"})
    public void verifyUserShouldLoginSuccessfully() {

        // Enter username
        homePage.setEnterUserName("Admin");

        // Enter password
        homePage.setEnterPassword("admin123");

        //Click on Login Button
        loginPage.setClickOnLogin();

        //Verify "Dashboard' Message
        String expectedMessage = "Welcome";
        String actualMessage = dashboardPage.getVerifyTheTextDashboard();
        Assert.assertEquals(expectedMessage, actualMessage, "WelCome Text is displayed");
    }

    @Test(groups = {"Smoke", "Regression"})
    public void verifyThatTheLogoDisplayedOnLoginPage() {

        //Launch the application
        driver.getCurrentUrl();

        //   Verify Logo is Displayed
        homePage.setHrmLogo();
    }
    @Test(groups = {"Regression" })
    public void verifyUserShouldLogoutSuccessfully() throws InterruptedException {

        //Login to the application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        //Click on User profile logo
        adminPage.setClickOnUserProfilePage();

        //Mouse hover on "Logout" and click
        adminPage.setMouseHoverOnLogout();

        //Verify the text "Login Panel" is displayed
        Thread.sleep(2000);
        String expectedMessage = "Login";
        String actualMessage = homePage.setVerifyLoginPanel();
        Assert.assertEquals(expectedMessage,actualMessage,"login message is not displayed");
    }

    @Test(groups = {"Regression" })
    public void verifyErrorMessageWithInvalidCredentials()
    {
        //Enter username
        homePage.setEnterUserName("promo123@yahoo.com");
        //   Enter password
        homePage.setEnterPassword("promo");
        //   Click on Login Button
        loginPage.setClickOnLogin();
        //   Verify "Dashboard" Message
        String actualMessage = dashboardPage.getVerifyTheTextDashboard();
        String expectedMessage ="Welcome  Text is not displayed";
        Assert.assertEquals(actualMessage,expectedMessage,"Welcome  Text is not displayed");
    }

}
