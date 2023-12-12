package com.orangehrmlive.demo.testsuite;

import com.orangehrmlive.demo.customlisteners.CustomListeners;
import com.orangehrmlive.demo.pages.*;
import com.orangehrmlive.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class UsersTest extends BaseTest {


    //Declaration
    AddUserPage addUserPage;
    AdminPage adminPage;
    DashboardPage dashboardPage;
    HomePage homePage;
    LoginPage loginPage;
    ViewSystemUsersPage viewSystemUserPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        //Initialisation
        addUserPage = new AddUserPage();
        adminPage = new AdminPage();
        dashboardPage = new DashboardPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        viewSystemUserPage = new ViewSystemUsersPage();
    }

    @Test(groups = {"Sanity", "Smoke", "Regression"})
    public void adminShouldAddUserSuccessFully() {

        //Login to application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        //Click on "Admin" tab
        adminPage.setClickOnAdmin();

        //Verify "System User" Text
        String expectedText = "System Users";
        String actualText = viewSystemUserPage.setVerifyTextSystemUser();
        Assert.assertEquals(expectedText, actualText, "System User is not displayed");

        //Click on "Add" button
        viewSystemUserPage.setClickingAddButton();

        //Verify "Add User" text
        String expectedMessage = "Add User";
        String actualMessage = addUserPage.setVerifyAddUserText();
        Assert.assertEquals(expectedMessage, actualMessage, "Add User is not displayed");

        //Select user role "Admin"
        addUserPage.setSelectAdmin();

        //Enter employee name "Ananya Dash"
        addUserPage.setEnterEmployeeName("Ananya Dash");

        //Enter username
        viewSystemUserPage.setEnterUsername("Ananya Dash");

        //Select status "Disable"
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();

        //Enter password
        homePage.setEnterPassword("admin123");

        //Enter Confirm password
        addUserPage.setConfirmPassword("admin123");

        //Click on "Save" button
        addUserPage.setClickOnSaveButton();

        //	verify message "Successfully Saved"

    }

    @Test(groups = {"Smoke", "Regression"})
    public void searchTheUserCreatedAndVerifyIt() throws InterruptedException {

        //Login to Application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        //	click On "Admin" Tab
        adminPage.setClickOnAdmin();

        //	Verify "System Users" Text
        String actualText = viewSystemUserPage.setVerifyTextSystemUser();
        String expectedText = "System Users";
        Assert.assertEquals(actualText, expectedText, "System User is not displayed");

        //	Enter Username
        Thread.sleep(2000);
        viewSystemUserPage.setEnterUsername("Ananya.Dash");

        //	Select User Role
        addUserPage.setSelectUserRole();

        //	Select Status
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();

        //	Click on "Search" Button
        viewSystemUserPage.setEnterSearch();

        //	Verify the User should be in Result list.
        String actualResult = viewSystemUserPage.setVerifyTheResult();
        String expectedResult = "Linda.Anderson";
        Assert.assertEquals(actualResult, expectedResult, "Linda.Anderson is not displayed");
    }

    @Test(groups = {"Regression"})
    public void verifyThatAdminShouldDeleteTheUserSuccessFully() {

        //Login to Application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        //	click On "Admin" Tab
        adminPage.setClickOnAdmin();

        //	Verify "System Users" Text
        String actualText = viewSystemUserPage.setVerifyTextSystemUser();
        String expectedText = "System Users";
        Assert.assertEquals(actualText, expectedText, "System User is not displayed");

        //	Enter Username
        viewSystemUserPage.setEnterUsername("Ananya.Dash");

        //	Select User Role
        addUserPage.setSelectAdmin();

        //	Select Status
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();

        //	Click on "Search" Button
        viewSystemUserPage.setEnterSearch();

        //	Verify the User should be in Result list.
        String actualResult = viewSystemUserPage.setVerifyTheResult();
        String expectedResult = "Ananya.Dash";
        Assert.assertEquals(actualResult, expectedResult, "Ananya.Dash is not displayed");

        //	Click on Check box
        viewSystemUserPage.setTickOnCheckbox();

        //	Click on Delete Button
        viewSystemUserPage.setClickOnDeleteButton();

        //	Popup will display
        viewSystemUserPage.notifyAll();

        //	Click on Ok Button on Popup
        viewSystemUserPage.acceptAlert();

        //	verify message "Successfully Deleted"
        String expectedMessage = "Successfully Deleted";
        String actualMessage = viewSystemUserPage.getTextFromAlert();
        Assert.assertEquals(actualMessage, expectedMessage, "Successfully Deleted message is not displayed");
    }

    @Test(groups = {"Regression"})
    public void searchTheDeletedUserAndVerifyTheMessageNoRecordFound() {

        //Login to Application
        homePage.setEnterUserName("Admin");
        homePage.setEnterPassword("admin123");
        loginPage.setClickOnLogin();

        //	click On "Admin" Tab
        adminPage.setClickOnAdmin();

        //	Verify "System Users" Text
        String actualText = viewSystemUserPage.setVerifyTextSystemUser();
        String expectedText = "System Users";
        Assert.assertEquals(actualText, expectedText, "System User is not displayed");

        //	Enter Username
        viewSystemUserPage.setEnterUsername("Ananya.Dash");

        //	Select User Role
        addUserPage.setSelectAdmin();

        //	Select Status
        addUserPage.setSelectStatus();
        addUserPage.setSelectDisable();

        //	Click on "Search" Button
        viewSystemUserPage.setEnterSearch();

        //	verify message "No Records Found"

    }
}
