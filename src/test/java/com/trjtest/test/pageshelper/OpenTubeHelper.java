package com.trjtest.test.pageshelper;

import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.NoSuchContextException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/21.
 * 开通存管的各个元素操作
 */
public class OpenTubeHelper {
    public static Logger logger = Logger.getLogger(OpenTubeHelper.class);

    /**
     * 点击开通存管
     **/
    public static void clickOpenTubeNow(AppiumUtil appiumUtil, By byElemernt) {
        Boolean flag = appiumUtil.doesElementsExist(byElemernt);
        if (flag) {
            appiumUtil.click(byElemernt);
            logger.info("账户页面立即开通存管按钮存在，并点击");
        } else {
            logger.info("账户页面立即开通存管按钮不存在");
        }
    }

    /**
     * 点击存管开通弹窗中的立即开通按钮
     **/
    public static void clickPositiveDepositry(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("开通存管弹窗中立即开通按钮存在，并点击");
        } else {
            logger.info("开通存管弹窗中立即开通按钮不存在");
        }
    }

    /**
     * 点击开户银行
     *
     * @param bankopation :银行卡列表中，银行的所处的位置
     **/
    public static void clickBank(By byElement, AppiumDriver driver, String bankopation) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //driver.findElement(byElement).click();
        //Select select =new Select(driver.findElement(byElement));
        driver.findElement(By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[4]/div[2]/select/option[" + bankopation + "]")).click();

        logger.info(" 点击开户银行");
    }

    /**
     * 输入名字
     **/
    public static void typeRealName(By byElement, AppiumDriver driver, String realname) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(byElement).sendKeys(realname);
        logger.info("填写名字");
    }

    /**
     * 输入身份证
     **/
    public static void typePersonID(By byEment, AppiumDriver driver, String personID) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(byEment).sendKeys(personID);
        logger.info("输入身份证");
    }

    /**
     * 选择省份
     **/
    public static void clickProvince(By byElement, AppiumDriver driver, String provinceoption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //driver.findElement(byElement).click();
        driver.findElement(By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[5]/div[2]/select/option[" + provinceoption + "]")).click();
        logger.info("选择省份");

    }

    /**
     * 选择城市
     **/
    public static void clickCity(By byElement, AppiumDriver driver, String cityoption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //driver.findElement(byElement).click();
        driver.findElement(By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[6]/div[2]/select/option[" + cityoption + "]")).click();
        logger.info("选择城市");
    }

    /**
     * 选择开户行
     **/
    public static void clickKaiHuHang(By byElement, AppiumDriver driver, String kaihuhangoption) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(byElement).click();
        driver.findElement(By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/section/div[1]/ion-content/div[1]/div/div[" + kaihuhangoption + "]")).click();

        driver.findElement(By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/section/div[1]/div/span[2]")).click();

        logger.info("选择开户行");
    }

    /**
     * 输入银行卡号
     **/
    public static void typeAccountNo(By byElement, AppiumDriver driver, String accountno) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(byElement).sendKeys(accountno);
        logger.info(" 输入银行卡号");
    }

    /**
     * 输入手机号码*8/
     */
    public static void typeMobile(By byElement, AppiumDriver driver, String mobile) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(byElement).sendKeys(mobile);
        logger.info("输入手机号");
    }

    /**
     * 点击获取动态码
     **/

    public static void clickGetCode(AppiumUtil appiumUtil, By byElement) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Boolean flag= appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info(" 点击获取验证码");
        }else {
            logger.info("获取验证码按钮不存在");
        }

    }

    /**
     * 输入动态吗
     **/
    public static void typeVerifyCode(By byElement, AppiumDriver driver, String verifycode) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(byElement).sendKeys(verifycode);
        logger.info("输入动态码");
    }

    /**
     * 点击开通
     **/
    public static void clickOpenConfirm(By byElement, AppiumDriver driver) {
        Boolean flag= driver.findElement(byElement).isDisplayed();
        if(flag){
            driver.findElement(byElement).click();
            logger.info("点击确认开通按钮");
        }else {
            logger.info("开通按钮不存在");
        }
    }

    /**
     * 判断获取验证码是否存在
     **/
    public static boolean existopenback(By byElement, AppiumDriver driver) {
        try {
            driver.findElement(byElement);
            return true;
        } catch (NoSuchContextException nee) {
            return false;
        }
    }

    /**
     * 点击开通完成之后的返回按钮
     **/
    public static final void clickOpenback(By byElement, AppiumDriver driver) {
        driver.findElement(byElement).click();
        logger.info("开通存管完成，并返回");
    }

    /**
     * 银行存管页面，点击返回
     **/
    public static final void clickTubeBack(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("银行存管页面的返回按钮存在，并点击");
        } else {
            logger.info("银行存管页面的返回按钮不存在");
        }
    }

    /**
     * 开通存管页面，点击返回
     **/
    public static final void clickOpenTubeBack(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("开通存管页面的返回按钮存在，并点击");
        } else {
            logger.info("开通存管页面的返回按钮不存在");
        }
    }

    /**
     * 判断立即开通确认开通之后立即开通是否存在
     **/
    public static boolean existopentubeconfirm(AppiumUtil appiumUtil, By byElemnt, AppiumDriver driver) {
        if (appiumUtil.doesElementsExist(byElemnt)) {
            return true;
        } else {
            return false;
        }
    }

}
