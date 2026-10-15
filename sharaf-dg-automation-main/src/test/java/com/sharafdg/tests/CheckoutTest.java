package com.sharafdg.tests;

import com.sharafdg.base.BaseTest;

import com.sharafdg.page.CheckoutPage;
import com.sharafdg.page.LoginPage;
import com.sharafdg.page.OrderSummaryPage;
import com.sharafdg.page.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {
    LoginPage loginPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    OrderSummaryPage orderSummaryPage;

    private static final String PRODUCT_URL = "https://uae.sdgstage.com/product/apple-iphone-14-pro-256gb-silver-physical-dual-sim-international-version";
    private static final String PRODUCT_NAME_SMALL_HP = "Apple iPhone 14 Pro - (256 GB) - Silver";
    private static final String PRODUCT_NAME_BIG_HP = "Apple iPhone 14 Pro – (256 GB) – Silver";
    private static final String PRODUCT_PRICE = "1,969.00";
    private static final String ADDRESS = "address";

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderSummaryPage = new OrderSummaryPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.loginWithEmailPassword("abc123@gmail.com", "abcd1234");
        loginPage.navigateToProductPage();
    }

    @Test
    public void verifyProductPage() {
        loginPage.navigateToProductPage();
        String productName = productPage.getProductTitle();
        String productPrice = productPage.getSalePrice();
        Assert.assertEquals(productName, PRODUCT_NAME_BIG_HP, "Product name on Product page is incorrect.");
        Assert.assertEquals(productPrice, PRODUCT_PRICE, "Product price on Product page is incorrect.");

        productPage.addToCart();
        String cartProductName = productPage.getCartProductTitle();
        Assert.assertEquals(cartProductName, PRODUCT_NAME_SMALL_HP, "Product name in Cart is incorrect.");
        String cartProductPrice = productPage.getCartPrice();

        productPage.checkoutBtn();
        checkoutPage.performCheckout(ADDRESS);
        Assert.assertTrue(true, "Checkout is not successful.");

        String orderNumberMessage = orderSummaryPage.getOrderNumberMessage();
        String osProductTitle = orderSummaryPage.getOrderSummaryProductTitle();
        Assert.assertTrue(orderNumberMessage.contains("Order number "), "Order Number is not displayed.");
    }

}
