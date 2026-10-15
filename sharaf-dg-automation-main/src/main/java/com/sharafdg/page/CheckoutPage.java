package com.sharafdg.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="(//div[contains(@class, 'selectize-input') and contains(@class, 'has-options')])[1]")
    WebElement emiratesDropdown;

    @FindBy(xpath = "//input[@id='shipping_method_0_local_pickup_plus']")
    WebElement radioPickup;

    @FindBy(xpath = "//input[@id='sdg-map-search']")
    WebElement mapaddress;

    @FindBy(xpath = "//li[@class='select2-results__option select2-results__option--selectable']")
    WebElement selectPickuplocation;

    @FindBy(xpath = "(//div[@class='selectize-dropdown-content'])[1]")
    WebElement emiratesDropdownContent;

    @FindBy(xpath="(//div[@class='selectize-dropdown-content'])[1]//div[@data-value='DXB']")
    WebElement dubaiEmiratesToSelect;

    @FindBy(xpath = "(//div[@class='selectize-input items has-options full has-items'])[1]//div[@class='item']")
    WebElement selectedEmirate;

    @FindBy(xpath="(//div[contains(@class, 'selectize-input') and contains(@class, 'items')])[2]")
    WebElement areaDropdown;

    @FindBy(xpath = "(//div[@class='selectize-dropdown-content'])[2]")
    WebElement areaDropdownContent;

    @FindBy(xpath="(//div[@class='selectize-dropdown-content'])[2]//div[@data-value='ABU_HAIL']")
    WebElement abuHailAreaToSelect;

    @FindBy(xpath = "(//div[@class='selectize-input items has-options full has-items'])[2]//div[@class='item']")
    WebElement selectedArea;

    @FindBy(id = "shipping_address_1")
    WebElement addressInput;

    @FindBy(xpath = "//input[@id='payment_method_cod']")
    WebElement codRadioButton;

    @FindBy(id = "place_order")
    WebElement placeOrderButton;

    @FindBy(xpath = "//input[@type='radio' and (@value='local_pickup_plus' or contains(@id,'local_pickup'))]")
    WebElement storePickupRadio;

    @FindBy(name = "pickup_location[0]")
    WebElement pickupDropdown;

    @FindBy(xpath = "//td[@class='update_totals_on_change']")
    WebElement pickupLocationSelect;

    @FindBy(xpath = "//input[@id='payment_method_sdg_checkout']")
    WebElement creditcardRadioButton;

    @FindBy(xpath = "//input[@class='input-text card_cvv_input']")
    WebElement cvvCreditCard;

            

    private void selectDubaiForEmiratesDropdown() {
        waitUntilElementVisible(emiratesDropdown).click();
        waitUntilElementVisible(emiratesDropdownContent);
        waitUntilElementVisible(dubaiEmiratesToSelect).click();
    }

    public String selectedEmirate() {
        return waitUntilElementVisible(selectedEmirate).getText();
    }

    private void selectAbuHailForAreaDropdown() {
        waitUntilElementVisible(areaDropdown).click();
        waitUntilElementVisible(areaDropdownContent);
        waitUntilElementVisible(abuHailAreaToSelect).click();
    }

    public String selectedArea() {
        return waitUntilElementVisible(selectedArea).getText();
    }

    private void enterAddress(String address) {
        waitUntilElementVisible(addressInput).sendKeys(address);
    }

    public void performCheckout(String address) {

        radioPickup.click();
        pickupLocationSelect.click();
        selectPickuplocation.click();

        //creditcardRadioButton.click();
        
        waitUntilElementVisible(cvvCreditCard).sendKeys("100");
        try {
        Thread.sleep(5000); // Pause for 2000 milliseconds (2 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        WebElement PlaceOrderButton = waitUntilElementVisible(placeOrderButton);
        js.executeScript("arguments[0].style.border='3px solid red'", PlaceOrderButton);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", PlaceOrderButton);

    }
}
