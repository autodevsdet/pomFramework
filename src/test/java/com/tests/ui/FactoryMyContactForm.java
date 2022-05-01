package com.tests.ui;

import com.dictionary.basePageObjects.FactoryHomePage;
import com.dictionary.basePageObjects.HomePage;
import com.dictionary.basePageObjects.SampleFormsPage;
import com.engine.DriverManager;
import com.engine.UiTestCase;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.general.Utils.*;

public class FactoryMyContactForm extends DriverManager implements UiTestCase {

    @Description("Setting Up Prerequisites")
    @BeforeTest(description = "Setup Functionality")
    public void setup() throws IOException {
        setTestEnvironment();
    }

    @DataProvider
    public Object[][] getUseridPassword() throws IOException {
        return new Object[][]{
                {getData("userid1"), getData("password1")},
                //{getData("userid2"), getData("password2")},
                //{getData("userid3"), getData("password3")},
        };
    }

    @Description("MyContactForm : Testing First Invalid Login")
    @Test(description = "Tests First Invalid Login", dataProvider = "getUseridPassword")
    public void firstTest(String userid, String password) throws IOException {

        FactoryHomePage homePage = new FactoryHomePage(getDriver());
        homePage
                .openWebsite(getData("sampleforms_url"))
                .getNavigationLinks()
                .openSampleForms().getLoginModule().login(userid, password);

        Assert.assertEquals(whatIsTheValue("Login Error"), getData("loginerrormessage"));

    }

    @Description("Closing Activities")
    @AfterTest(description = "Teardown Functionality")
    public void tearDown(){

        //quitTest();

    }

}
