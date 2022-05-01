package com.general;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utils {

    public static Map<String, String> assertMap = new HashMap<>();

    public static Map<String, String> environmentMap = new HashMap<>();

    public static void rememberTheValue(String key, String value){
        assertMap.put(key, value);
    }

    public static String whatIsTheValue(String key){
        return assertMap.get(key);
    }

    public static void waitForElement(WebDriver driver, String xPtah){
        WebDriverWait waitDriver = new WebDriverWait(driver, 60);
        waitDriver.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xPtah))));
    }

    public static void waitForElement(WebDriver driver, WebElement we){
        WebDriverWait waitDriver = new WebDriverWait(driver, 60);
        waitDriver.until(ExpectedConditions.elementToBeClickable(we));
    }

    public static String getData(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\" + environmentMap.get("Environment") + ".properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public static void setTestEnvironment() throws IOException {
        Properties envProp = new Properties();
        FileInputStream envFis = new FileInputStream("src\\main\\resources\\debug.properties");
        envProp.load(envFis);
        String selectedEnv = envProp.getProperty("environment").trim();
        String selectedBrowser = envProp.getProperty("browser").trim();
        environmentMap.put("Environment", selectedEnv);
        environmentMap.put("Browser", selectedBrowser);
    }

    public static void rememberJsonValueAsString(String jsonResponse, String key, String path){
        JsonPath jsonPath = new JsonPath(jsonResponse);
        rememberTheValue(key, jsonPath.getString(path));
    }

    public static String encodedAuthToken(String email, String apiToken){
        String encodedString = Base64.getEncoder().encodeToString((email + ":" + apiToken).getBytes());
        return encodedString;
    }

    public static String cleanBasePath(String basePath, String cleanValue){
        return basePath.replaceAll("%.*%", cleanValue);
    }

}
