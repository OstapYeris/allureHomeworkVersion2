package saucedemoTests;

import base.SelenideBase;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import sauceDemo.sauceDemo;

import static com.codeborne.selenide.Selenide.open;

public class sauceDemoCartTest extends SelenideBase {
    @Test
    @Description("Check adding to cart and remove from cart")
    @Severity(SeverityLevel.BLOCKER)
    public void testAddingAndRemoveFromCart () {
        open("");
        new sauceDemo().fillLoginForm("standard_user", "secret_sauce").pressLoginButton();
        new sauceDemo().addAllProductToCart();
        new sauceDemo().goToCart();
        new sauceDemo().checkIfItemsPresentInCart();
        new sauceDemo().removeAllProductFromCart();
        new sauceDemo().checkIfItemsNotPresentInCart();
    }

//    @Test
//    @Description("Check remove items from cart")
//    @Severity(SeverityLevel.BLOCKER)
//    public void testRemoveProductsFromCart () {
//        open("");
//        new sauceDemo().fillLoginForm("standard_user", "secret_sauce").pressLoginButton();
//        new sauceDemo().goToCart();
//        new sauceDemo().removeAllProductFromCart();
//        new sauceDemo().checkIfItemsNotPresentInCart();
//    }

    @Test
    @Description("Check redirect to cart and back")
    @Severity(SeverityLevel.BLOCKER)
    public void testRedirect () {
        open("");
        new sauceDemo().fillLoginForm("standard_user", "secret_sauce").pressLoginButton();
        Assert.assertEquals(WebDriverRunner.url(), "https://www.saucedemo.com/inventory.html");
        new sauceDemo().goToCart();
        Assert.assertEquals(WebDriverRunner.url(), "https://www.saucedemo.com/cart.html");
        new sauceDemo().goToProductList();
        Assert.assertEquals(WebDriverRunner.url(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    @Description("Check direct to cart and back")
    @Severity(SeverityLevel.BLOCKER)
    public void testBuyingProcedure () {
        open("");
        new sauceDemo().fillLoginForm("standard_user", "secret_sauce").pressLoginButton();
        new sauceDemo().addAllProductToCart();
        new sauceDemo().goToCart();
        new sauceDemo().goToBuyingForm();
        new sauceDemo().fillBuyingForm("Ostap", "Homework", "37030" );
        new sauceDemo().ClickContinueButtonInBuyingForm();
        new sauceDemo().clickFinishButton();
        new sauceDemo().successBuyingMessage();
    }
}
