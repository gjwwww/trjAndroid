package com.trjtest.test.pageshelper;

import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Created by gjw on 17/6/22.
 */
public class AutoBidHelper {
    public static Logger logger = Logger.getLogger(AutoBidHelper.class);

    /**
     * 点击个人中心的头像按钮
     **/
    public static void clickAccountCenter(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("头像存在并点击");
        } else {
            logger.info("头像元素不存在");
        }
    }

    /**
     * 点击个人中心的一键投资按钮
     **/
    public static void clickAccountAutoBid(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("个人中心页面中，一键投资按钮存在并点击");
        } else {
            logger.info("个人中心页面中，一键投资按钮不存在");
        }
    }

    /**
     * 点击开通一键投资的立即开通按钮
     **/
    public static void clickOpenAutoBidNow(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("开通一键投资页面中，立即开通按钮存在并点击");
        } else {
            logger.info("开通一键投资页面中，立即开通按钮不存在");
        }
    }

    /**
     * 点击获取验证码
     **/
    public static void clickGetCode(AppiumUtil appiumUtil, By byElement, AppiumDriver driver) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("获取验证码按钮存在，并点击");

        } else {
            logger.info("获取验证码按钮不存在");
        }
    }

    /**
     * 开通一键投资页面的短信验证码输入框
     **/
    public static void typeOpenVerifyCode(AppiumUtil appiumUtil, By byElement, String verifycode) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.typeContent(byElement, verifycode);
            logger.info("输入短信验证码");
        } else {
            logger.info("开通一键投资页面的短信输入框不存在");
        }
    }

    /**
     * 开通一键投资页面的确认开通按钮
     **/
    public static void clickCofirmOpen(AppiumUtil appiumUtil, By byElement, AppiumDriver driver) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
//            //driver.tap(1,driver.findElement(byElement),1);
//            driver.context("NATIVE_APP");
//            driver.tap(1, driver.manage().window().getSize().getWidth() * 1 / 2, driver.manage().window().getSize().getHeight() * 5 / 8, 5);
//            //System.out.println(driver.findElement(byElement));
//            appiumUtil.switchWebview("WEBVIEW_com.trj.hp");
//            //appiumUtil.click(byElement);
            appiumUtil.click(byElement);
            logger.info("开通一键投资页面的确认开通按钮存在，并点击");
        } else {
            logger.info("开通一键投资页面的确认开通按钮不存在");
        }
    }

    /**
     * 点击一键投资开通的确定按钮
     **/

    public static void clickFinshOpen(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("开通一键投资页面的确定按钮存在，并点击");
        } else {
            logger.info("开通一键投资页面的确定按钮不存在");
        }
    }

    /**
     * 点击Wap元素
     **/

    public static void getWapInAppLocation(AppiumUtil appiumUtil, By byElement, AppiumDriver driver) {
        WebElement element = driver.findElement(byElement);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Fetch DOM document full size
        double documentWidth = Double.parseDouble(js.executeScript("return document.body.scrollWidth").toString());
        double documentHeight = Double.parseDouble(js.executeScript("return document.body.scrollHeight").toString());

        // Fetch element center position from the DOM
        double elementLeftCenter = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        double elementTopCenter = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        // Switch back to NATIVE_APP context
        driver.context("NATIVE_APP");

        // Fetch the cordova WebView element

        double screenXMultiplier = driver.manage().window().getSize().getWidth() / documentWidth;
        double screenYMultiplier = driver.manage().window().getSize().getHeight() / documentHeight;

        int tapX = (int) (elementLeftCenter * screenXMultiplier);
        int tapY = (int) (elementTopCenter * screenYMultiplier);
        driver.tap(1,tapX,tapY,200);
        appiumUtil.switchWebview("WEBVIEW_com.trj.hp");


    }
}
