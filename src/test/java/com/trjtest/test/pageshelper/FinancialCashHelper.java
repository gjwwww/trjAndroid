package com.trjtest.test.pageshelper;

import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/4/5.
 * 理财金各个元素操作方法
 */
public class FinancialCashHelper {
    public static Logger logger = Logger.getLogger(FinancialCashHelper.class);

    /**
     * 上滑账户页面
     **/
    public static void swipeTOUp(AppiumUtil appiumUtil, AppiumDriver driver) {
        appiumUtil.swipeToUp(driver, 1000);
        logger.info("上滑");
    }

    /**
     * 点击账户页面的额理财金按钮
     **/
    public static void clickFinacialCashContain(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("账户页面理财金按钮存在，并点击");
        } else {
            logger.info("账户页面理财金按钮不存在");
        }
    }

    /**
     * 点击理财页面的立即提取到账户
     **/
    public static void clickToAccountNow(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("理财金页面立即提取到账户按钮存在，并点击");
        } else {
            logger.info("理财金页面立即提取到账户按钮不存在");
        }
    }

    /**
     * 点击理财金页面的返回按钮
     **/
    public static void clickFinancialCashBack(AppiumUtil appiumUtil, By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("理财金页面返回按钮存在，并点击");
        } else {
            logger.info("理财金页面返回按钮不存在");
        }
    }
}
