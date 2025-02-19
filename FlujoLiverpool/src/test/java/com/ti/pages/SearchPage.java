package com.ti.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchPage extends MainPage{



//    @FindBy(xpath = "(//*[contains(text(), 'Cerveza Minerva Stout 355 ml')])[3]")
//    private WebElement titleCerveza;

    @FindBy(linkText = "Generoso")
    private WebElement lnkGeneroso;

    @FindBy(linkText = "Ordenar por:")
    private WebElement drpdSortBy;

    @FindBy(xpath = "(//button[contains(.,'Mayor precio')])[2]")
    private WebElement btnMayorPrecio;

    @FindBy(className = "card-masonry")
    private List<WebElement> productList;

    @FindBy(className = "card-title")
    private List<WebElement> cervesaList;

//    @FindBy(className = "btnGeoStore")
//    private WebElement btnGeoStore;

//    @FindBy(linkText = "AGUASCALIENTES")
//    private WebElement lknState;

    @FindBy(className = "slick-track")
    private WebElement relatedItems;

    @FindBy(id = "opc_pdp_addCartButton")
    private WebElement btnAddToCart;

    /////////////////////////////////////////////////// CERVEZA ///////////////////////////////////////////////////////////
    public SearchPage clickCervezaMinerva(String item){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfAllElements(cervesaList));
        for (WebElement cerveza:cervesaList) {
            if(cerveza.getText().contains(item)){
                cerveza.click();
                break;
            }
        }
        return this;
    }

    public SearchPage scrollToDetails(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOf(btnAddToCart));

        js =(JavascriptExecutor)driver;
        js.executeScript("var element = document.getElementById('o-product__productSpecsDetails');\n" +
                "element.scrollIntoView()");
        return this;
    }

    public SearchPage scrollToRelated() {
        scrollWindow("down");
        scrollWindow("down");
        scrollWindow("down");
        scrollWindow("down");
        scrollWindow("down");
        scrollWindow("down");

        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOf(relatedItems));
//                Thread.sleep(2000);

        JavascriptExecutor js =(JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0,0);");
        return this;
    }

    /////////////////////////////////////////////////// VINO ///////////////////////////////////////////////////

    public SearchPage typeGenerous(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(lnkGeneroso));
        lnkGeneroso.click();
        return this;
    }

    public SearchPage selectTypeSort(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(drpdSortBy));
        drpdSortBy.click();
        return this;
    }

    public SearchPage byHihgPrice(){
//        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.elementToBeClickable(btnMayorPrecio));
        btnMayorPrecio.click();
        return this;
    }

    public SearchPage selectItemWithHighestPrice(){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(productList));
        }catch (TimeoutException te){
//            preLoading();
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(productList));
        }
        WebElement firstProduct = productList.get(0);
        firstProduct.click();
        return this;
    }



}
