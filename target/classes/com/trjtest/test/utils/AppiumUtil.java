package com.trjtest.test.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.sun.jna.Platform.isWindows;


/**
 * Created by gjw on 17/2/23.
 */
public class AppiumUtil {

    public AppiumDriver driver;
    public ITestResult it;

    /**
     * 定义日志输出对象
     **/
    public static Logger logger = Logger.getLogger(AppiumUtil.class);


    /**
     * 获取driver
     **/
    public AppiumDriver getDriver(String url, DesiredCapabilities capabilities, String platform) {
        if (platform.equalsIgnoreCase("android")) {
            try {
                driver = new AndroidDriver(new URL(url), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (platform.equalsIgnoreCase("ios")) {
            try {
                driver = new IOSDriver(new URL(url), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {

        }
        return driver;
    }
    /**
     * 退出APP
     **/
    public void closeApp(String appName) {
        driver.closeApp();
        logger.info(appName + "已经关闭");
    }

    /**
     * 退出移动浏览器
     **/
    public void quit() {
        driver.quit();
        logger.info("driver已被清理");
    }

    /**
     * 通过By对象，去查找某个元素
     **/
    public WebElement findElement(By by) {
        return driver.findElement(by);
    }

    /**
     * 通过一组By对象，去查找一组元素
     **/
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    /**
     * 根据元素来获取此元素的定位值
     **/
    public String getLocatorByElement(WebElement element, String expectText) {
        String text = element.toString();
        String except = null;
        try {
            except = text.substring(text.indexOf(expectText) + 1, text.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("failed to find the string[" + expectText + "]");
        }
        return except;

    }


    /**
     * 清空元素内容
     **/
    public void clear(By byElement) {
        WebElement element = findElement(byElement);
        element.clear();
        logger.info("清空元素" + getLocatorByElement(element, ">") + "上的内容");
    }

    /**
     * 输入内容
     **/
    public void typeContent(By byElemnet, String str) {
        WebElement element = findElement(byElemnet);

        element.sendKeys(str);

        logger.info("在元素：" + getLocatorByElement(element, ">") + "输入内容" + str);
    }

    /**
     * 点击
     **/
    public void click(By byElemnet) {
        WebElement element = findElement(byElemnet);
        try {
            element.click();
            logger.info("点击元素" + getLocatorByElement(element, ">"));
        } catch (Exception e) {
            logger.error("点击元素：" + getLocatorByElement(element, ">") + "失败", e);
            Assert.fail("点击元素：" + getLocatorByElement(element, ">") + "失败", e);
        }
    }

    /**
     * 查找一个元素－appium新增的查找元素的方法
     */

    public WebElement findElement(String locateWay, String locateValue) {
        WebElement element = null;
        switch (locateWay) {
            case "AccessibilityId":
                element = driver.findElementByAccessibilityId(locateValue);
                break;
            case "ClassName":
                element = driver.findElementByClassName(locateValue);
                break;
            case "CSS":
                element = driver.findElementByCssSelector(locateValue);
                break;
            case "ID":
                element = driver.findElementById(locateValue);
                break;
            case "Linktext":
                element = driver.findElementByLinkText(locateValue);
                break;
            case "Name":
                element = driver.findElementByName(locateValue);
                break;
            case "PartialLinkText":
                element = driver.findElementByPartialLinkText(locateValue);
                break;
            case "TagName":
                element = driver.findElementByTagName(locateValue);
                break;
            case "Xpath":
                element = driver.findElementByXPath(locateValue);
                break;
            default:
                logger.error("定位方式" + locateWay + "不被支持");
                Assert.fail("定位方式" + locateWay + "不被支持");

        }
        return element;
    }

    public List<?> findElements(String locateWay, String locateValue) {
        List<?> element = null;
        switch (locateWay) {
            case "AccessibilityId":
                element = driver.findElementsByAccessibilityId(locateValue);
                break;
            case "ClassName":
                element = driver.findElementsByClassName(locateValue);
                break;
            case "CSS":
                element = driver.findElementsByCssSelector(locateValue);
                break;
            case "ID":
                element = driver.findElementsById(locateValue);
                break;
            case "Linktext":
                element = driver.findElementsByLinkText(locateValue);
                break;
            case "Name":
                element = driver.findElementsByName(locateValue);
                break;
            case "PartialLinkText":
                element = driver.findElementsByPartialLinkText(locateValue);
                break;
            case "TagName":
                element = driver.findElementsByTagName(locateValue);
                break;
            case "Xpath":
                element = driver.findElementsByXPath(locateValue);
                break;
            default:
                logger.error("定位方式" + locateWay + "不被支持");
                Assert.fail("定位方式" + locateWay + "不被支持");
        }
        return element;
    }

    /**
     * 获取文本1
     */
    public String getText(By by) {
        return findElement(by).getText().trim();
    }

    /**
     * 获取文本2
     */
    public String getText(String locateWay, String locateValue) {
        String str = "";
        switch (locateWay) {
            case "AccessibilityID":
                str = driver.findElementByAccessibilityId(locateValue).getText().trim();
                break;
            case "ClassName":
                str = driver.findElementByClassName(locateValue).getText().trim();
                break;
            case "CSS":
                str = driver.findElementByCssSelector(locateValue).getText().trim();
                break;
            case "ID":
                str = driver.findElementById(locateValue).getText().trim();
                break;
            case "Linktext":
                str = driver.findElementByLinkText(locateValue).getText().trim();
                break;
            case "Name":
                str = driver.findElementByName(locateValue).getText().trim();
                break;
            case "PartialLinktext":
                str = driver.findElementByPartialLinkText(locateValue).getText().trim();
                break;
            case "TagName":
                str = driver.findElementByTagName(locateValue).getText().trim();
                break;
            case "XPath":
                str = driver.findElementByXPath(locateValue).getText().trim();
                break;
            default:
                logger.error("定位方式" + locateWay + "不被支持");
                Assert.fail("定位方式" + locateWay + "不被支持");
        }
        return str;
    }


    /**
     * 提交
     **/
    public void submit(By by) {
        WebElement element = findElement(by);
        try {
            element.submit();
        } catch (Exception e) {
            logger.error(" 在元素：" + getLocatorByElement(element, ">") + "做的提交操作失败");
            Assert.fail(" 在元素：" + getLocatorByElement(element, ">") + "做的提交操作失败");
        }
        logger.info("在元素" + getLocatorByElement(element, ">") + "做了提交操作");
    }

    /**
     * 获得webview页面的标题
     **/
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * 获得元素属性的文本
     **/
    public String getAttributeText(By elementLocator, String attribute) {
        return findElement(elementLocator).getAttribute(attribute).trim();
    }

    /**
     * 在给定的时间内查找元素，如果没有找到则超时，抛出异常
     **/
    public void waitForElementToLoad(int elementTimeOut, final By By) {
        logger.info("开始查找元素[" + By + "]");
        try {
            (new WebDriverWait(driver, elementTimeOut)).until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver) {
                    WebElement element = driver.findElement(By);
                    return element.isDisplayed();
                }
            });
        } catch (TimeoutException e) {
            logger.error("超时!! " + elementTimeOut + " 秒之后还没找到元素 [" + By + "]");
            Assert.fail("超时!! " + elementTimeOut + " 秒之后还没找到元素 [" + By + "]");

        }
        logger.info("找到了元素 [" + By + "]");
    }

    /**
     * 判断文本是不是和需求的文本一致
     **/
    public void isTextCorrent(String actual, String expeccted) {
        try {
            Assert.assertEquals(actual, expeccted);
        } catch (AssertionError e) {
            logger.error("期望的文字是+[" + expeccted + "],但是找到了" + actual + "]");
            Assert.fail("期望的文字是+[" + expeccted + "],但是找到了" + actual + "]");
        }
        logger.info("找到了期望的名字：［" + expeccted + "]");
    }

    /**
     * 暂停执行用例的执行，暂停的时间为：sleepTime"
     */

    public void pause(int sleepTime) {
        if (sleepTime <= 0) {
            return;
        }
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
            logger.info("暂停 " + sleepTime + "秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 判断时间文本包含期望文本
     **/

    public void isContains(String actual, String expected) {
        try {
            Assert.assertTrue(actual.contains(expected));
        } catch (AssertionError e) {
            logger.error("实际文本：" + actual + "不包含期望文本" + expected);
            Assert.fail("实际文本" + actual + "不包含期望文本" + expected);
        }
        logger.info("实际文本" + actual + "包含期望文本" + expected);

    }

    /**
     * 跳转到webview页面
     */
    public void switchWebview(String contextName) {
        try {
            Set<String> contexts = driver.getContextHandles();
            for (String context : contexts) {
                System.out.println(context);
                //打印出来看看有哪些context
            }
            driver.context(contextName);
            /**解决第二次切换webview 问题**/
            Set<String> windows = driver.getWindowHandles();
            driver.switchTo().window((String) windows.toArray()[windows.size() - 1]);
        } catch (NoSuchContextException nce) {
            logger.error("没有这个context:" + contextName, nce);
            Assert.fail("没有这个context:" + contextName, nce);
        }

    }


    /**
     * 执行JavaScript 方法
     */
    public void executeJS(String js) {
        ((JavascriptExecutor) driver).executeScript(js);
        logger.info("执行JavaScript语句：[" + js + "]");
    }

    /**
     * 执行JavaScript 方法和对象
     * 用法：seleniumUtil.executeJS("arguments[0].click();", seleniumUtil.findElementBy(MyOrdersPage.MOP_TAB_ORDERCLOSE));
     */
    public void executeJS(String js, Object... args) {
        ((JavascriptExecutor) driver).executeScript(js, args);
        logger.info("执行JavaScript语句：[" + js + "]");
    }

    /**
     * 检查元素是不是存在
     */
    public boolean doesElementsExist(By byElement) {
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            findElement(byElement);
            return true;
        } catch (NoSuchElementException nee) {

            return false;
        }


    }

    /**
     * 长按操作
     */
    public void longPress(By by) {
        TouchAction tAction = new TouchAction(driver);
        tAction.longPress(findElement(by)).perform();
    }

    /**
     * 滑动
     */
    public void swipe(int beginX, int beginY, int endX, int endY) {
        TouchAction tAction = new TouchAction(driver);
        try {
            tAction.press(beginX, beginY).waitAction(2000).moveTo(endX, endY).release().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 滚动 - 根据文本模糊匹配
     */
    public void scroll(String text) {
        driver.scrollTo(text);
    }

    /**
     * 滚动 - 根据文本精准匹配
     */
    public void scrollExact(String text) {
        driver.scrollToExact(text);
    }

    /**
     * 拖拽操作
     */
    public void DragAndDrop(By dragElement, By dropElement) {
        TouchAction act = new TouchAction(driver);
        act.press(findElement(dragElement)).perform();
        act.moveTo(findElement(dropElement)).release().perform();
    }

    /**
     * 放大和缩小
     */
    public void zoomAndPinch(int beginX, int beginY, int endX, int endY) {
        int scrHeight = driver.manage().window().getSize().getHeight();
        int scrWidth = driver.manage().window().getSize().getWidth();
        MultiTouchAction multiTouch = new MultiTouchAction(driver);
        TouchAction tAction0 = new TouchAction(driver);
        TouchAction tAction1 = new TouchAction(driver);
        tAction0.press(scrWidth / 2, scrHeight / 2).waitAction(1000).moveTo(beginX, beginY).release();
        tAction1.press(scrWidth / 2, scrHeight / 2 + 40).waitAction(1000).moveTo(endX, endY).release();
        multiTouch.add(tAction0).add(tAction1);
        multiTouch.perform();

    }

    /**
     * app置于后台运行
     */
    public void runBackgound(int runTimes) {
        driver.runAppInBackground(runTimes);

    }

    /**
     * 收起键盘
     */
    public void hideKeyboard() {
        driver.hideKeyboard();
        logger.info("虚拟键盘已经收起");

    }

    /**
     * 安装app
     */
    public void instalApp(String appPath) {
        try {
            driver.installApp(appPath);
        } catch (Exception e) {
            logger.error("app安装失败", e);
            Assert.fail("app安装失败", e);
        }
    }

    /**
     * app是否安装
     */
    public boolean isAppInstalled(String appPackage) {

        if (driver.isAppInstalled(appPackage)) {
            logger.info(appPackage + ":已经安装");
            return true;
        } else {
            logger.info(appPackage + ":未安装");
            return false;
        }
    }

    /**
     * 页面过长时候滑动页面 window.scrollTo(左边距,上边距);
     */
    public void scrollPage(int x, int y) {
        String js = "window.scrollTo(" + x + "," + y + ");";
        ((JavascriptExecutor) driver).executeScript(js);
    }

    public void swipeToLeft(AppiumDriver driver, int during) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        driver.swipe(width * 3 / 4, height / 2, width / 8, height / 2, during);

    }


    //滑动获取验证码右滑
    public void swipeToRight(AppiumDriver driver, int during) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        try {
            driver.swipe(width / 4, height / 2, width * 19 / 20, height / 2, during);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //上滑
    public void swipeToUp(AppiumDriver driver, int during) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        try {
            driver.swipe(width / 2, height / 100 * 64, width / 2, height / 100 * 47, during);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //上滑，其他三系的产品上滑
    public void swipeToUpOther(AppiumDriver drive, int during) {
        drive.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        try {
            driver.swipe(width / 2, height / 100 * 64, width / 2, height / 100 * 40, during);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitFor() throws InterruptedException {
        Thread.sleep(10);
    }

    // 左滑
    public void swipeRightToLeft(AppiumDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", 0.9);
        swipeObject.put("startY", 0.5);
        swipeObject.put("endX", 0.01);
        swipeObject.put("endY", 0.5);
        swipeObject.put("duration", 3.0);
        js.executeScript("mobile: swipe", swipeObject);
    }

    /**
     * 杀Chrome driver进程
     **/
    public static void killProcess(String processName) {
        try {
            String cmd = isWindows() ? "tskill " + processName : "killall \"" + processName + "\"";
            cmdInvoke(cmd);
        } catch (Exception e) {

        }
    }

    public static String cmdInvoke(String cmd) {
        String cmdOut = "";
        BufferedReader br = null;

        try {
            Process p = Runtime.getRuntime().exec(cmd);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                cmdOut = line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return cmdOut;
    }
}
