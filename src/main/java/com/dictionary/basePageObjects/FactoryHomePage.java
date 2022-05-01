package com.dictionary.basePageObjects;

import com.dictionary.pageSubComponents.HomePageNavLinks;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static com.general.Utils.waitForElement;

public class FactoryHomePage {

    public WebDriver driver;

    //private String SAMPLE_FORMS = "//a[text()='Sample Forms']";
    @FindBy(xpath = "//a[text()='Sample Forms']")
    WebElement sampleForms;

    public FactoryHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Open MyContactForm Website")
    public FactoryHomePage openWebsite(String url) throws IOException {

        driver.get(url);
        waitForElement(driver, sampleForms);
        return new FactoryHomePage(driver);

    }

    @Step("Redirect Control to Navigation Links Section")
    public HomePageNavLinks getNavigationLinks(){
        return new HomePageNavLinks(driver);
    }

}
