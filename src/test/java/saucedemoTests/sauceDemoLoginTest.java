package saucedemoTests;

import base.SelenideBase;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sauceDemo.sauceDemo;

import static com.codeborne.selenide.Selenide.open;

public class sauceDemoLoginTest extends SelenideBase {
    @Test
    @Description("Check login with invalid credentials")
    @Severity(SeverityLevel.MINOR)
    public void testInvalidCredentialsLogin() {
        open("");
        new sauceDemo().fillLoginForm("wrong_login", "wrong_password").pressLoginButton();
        Assert.assertEquals(WebDriverRunner.url(), "https://www.saucedemo.com/");
        new sauceDemo().wrongCredentialsMessage();
    }

    @Test
    @Description("Check locked customer login procedure")
    @Severity(SeverityLevel.BLOCKER)
    public void testLockedCustomerLogin() {
        open("");
        new sauceDemo().fillLoginForm("locked_out_user", "secret_sauce").pressLoginButton();
        Assert.assertEquals(WebDriverRunner.url(), "https://www.saucedemo.com/");
        new sauceDemo().userLockedMessage();
    }

    @Test(dataProvider = "LoginData")
    @Description("Check customer login procedure")
    @Severity(SeverityLevel.BLOCKER)
    public void testCustomerLogin(String userName, String password) {
        open("");
        new sauceDemo().fillLoginForm(userName, password).pressLoginButton();
        Assert.assertEquals(WebDriverRunner.url(), "https://www.saucedemo.com/inventory.html");
        new sauceDemo().logoutProcedure();
        Assert.assertEquals(WebDriverRunner.url(), "https://www.saucedemo.com/");
    }

    @DataProvider(name = "LoginData")
    public Object[][] getLoginFormDataProvider() {
        return new Object[][]{
                {"performance_glitch_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
        };
    }

    @Test
    @Description("Check standard customer login procedure")
    @Severity(SeverityLevel.BLOCKER)
    public void testStandardCustomerLogin() {
        open("");
        new sauceDemo().fillLoginForm("standard_user", "secret_sauce").pressLoginButton();
        Assert.assertEquals(WebDriverRunner.url(), "https://www.saucedemo.com/inventory.html");
    }
}
