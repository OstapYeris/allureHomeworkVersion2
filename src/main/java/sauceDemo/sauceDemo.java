package sauceDemo;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.*;

public class sauceDemo {
    SelenideElement userNameField = $(By.name("user-name"));
    SelenideElement passwordField = $(By.name("password"));
    SelenideElement logginButton = $(By.id("login-button"));
    SelenideElement wrongCredentialsMessage = $("div#login_button_container>div>form>h3");
    SelenideElement siteMenu = $(By.id("react-burger-menu-btn"));
    SelenideElement logoutButton = $(By.id("logout_sidebar_link"));
    String productPath = "(//div[@class='inventory_item_price']/following-sibling::button)[%d]";
    String productInCart = "(//button[text()='REMOVE'])[%d]";
    SelenideElement cart = $(By.id("shopping_cart_container"));
    SelenideElement continueShoppingButton = $x("//div[@class='cart_footer']//a[1]");
    SelenideElement checkoutButton = $x("//a[@class='btn_action checkout_button']");
    SelenideElement continueBuyingProcedureButton = $x("//input[@type='submit']");
    SelenideElement finishButton = $x("//a[@class='btn_action cart_button']");
    SelenideElement succsessBuyingMessage = $x("//h2[text()='THANK YOU FOR YOUR ORDER']");
    SelenideElement SecondSuccsessBuyingMessage = $x("//div[text()='Your order has been dispatched, and will arrive just as fast as the pony can get there!']");

    String elementPath = "//div[text()='%s']";
    String[] elementList = {"Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light", "Sauce Labs Fleece Jacket", "Test.allTheThings() T-Shirt (Red)", "Sauce Labs Onesie"};

//    SelenideElement elementBackpack = $x("//div[text()='Sauce Labs Backpack']");
//    SelenideElement elementBoltTshirt = $x("//div[text()='Sauce Labs Bolt T-Shirt']");
//    SelenideElement elementBike = $x("//div[text()='Sauce Labs Bike Light']");
//    SelenideElement elementBoltJacket = $x("//div[text()='Sauce Labs Fleece Jacket']");
//    SelenideElement elementRedTshirt = $x("//div[text()='Test.allTheThings() T-Shirt (Red)']");
//    SelenideElement elementBoltOnesie = $x("//div[text()='Sauce Labs Onesie']");

    SelenideElement firstNameField = $x("//input[@data-test='firstName']");
    SelenideElement lastNameField = $x("//input[@data-test='lastName']");
    SelenideElement zipCodeField = $x("//input[@data-test='postalCode']");

    @Step("Fill login={login} and passwords field={passwd}")
    public sauceDemo fillLoginForm(String login, String passwd) {
        userNameField.shouldBe(visible).sendKeys(login);
        passwordField.shouldBe(visible).sendKeys(passwd);
        return this;
    }

    @Step
    public void pressLoginButton() {
        logginButton.click();
    }

    @Step
    public sauceDemo wrongCredentialsMessage() {
        wrongCredentialsMessage.shouldHave(text("Username and password do not match any user in this service"));
        return this;
    }

    @Step
    public sauceDemo successBuyingMessage() {
        succsessBuyingMessage.shouldHave(text("THANK YOU FOR YOUR ORDER"));
        SecondSuccsessBuyingMessage.shouldHave(text("Your order has been dispatched, and will arrive just as fast as the pony can get there!"));
        return this;
    }

    @Step
    public sauceDemo userLockedMessage() {
        wrongCredentialsMessage.shouldHave(text("Sorry, this user has been locked out."));
        return this;
    }

    @Step
    public void logoutProcedure() {
        siteMenu.shouldBe(visible).click();
        logoutButton.shouldBe(visible).click();
    }

    @Step
    public void addAllProductToCart() {
        for (int i = 1; i <= 6; i++) {
            $x(String.format(productPath, i)).shouldBe(visible).click();
        }
    }

    @Step
    public void removeAllProductFromCart() {
        for (int i = 6; i >= 1; i--) {
            $x(String.format(productInCart, i)).shouldBe(visible).click();
        }
    }

    @Step
    public void goToCart() {
        cart.shouldBe(visible).click();
    }

    @Step
    public void goToProductList() {
        continueShoppingButton.shouldBe(visible).click();
    }

    @Step
    public void checkIfItemsPresentInCart() {
        for (String elementName: elementList) {
            $x(String.format(elementPath, elementName)).shouldBe(visible);
        }
    }

    @Step
    public void checkIfItemsNotPresentInCart() {
        for (String elementName: elementList) {
            $x(String.format(elementPath, elementName)).shouldNotBe(visible);
        }
    }

    @Step
    public void goToBuyingForm() {
        checkoutButton.shouldBe(visible).click();
    }

    @Step("Fill First Name={firstName}, Last Name={lastName} and zip code={zipCode}")
    public void fillBuyingForm(String firstName, String lastName, String zipCode) {
        firstNameField.shouldBe(visible).sendKeys(firstName);
        lastNameField.shouldBe(visible).sendKeys(lastName);
        zipCodeField.shouldBe(visible).sendKeys(zipCode);
    }

    @Step
    public void ClickContinueButtonInBuyingForm() {
        continueBuyingProcedureButton.shouldBe(visible).click();
    }

    @Step
    public void clickFinishButton() {
        finishButton.shouldBe(visible).click();
    }
}
