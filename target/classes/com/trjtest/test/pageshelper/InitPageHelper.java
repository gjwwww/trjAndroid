package com.trjtest.test.pageshelper;

import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/2/27.
 */
public class InitPageHelper {


    public static Logger logger =Logger.getLogger(InitPageHelper.class);

    /**滑动引导页**/
    public static void swipeLeft(AppiumUtil appiumUtil, AppiumDriver driver){
        logger.info("正在滑动引导页");
        appiumUtil.swipeToLeft(driver,1000);
       // appiumUtil.swipe(900,520,250,520);
        //appiumUtil.swipeRightToLeft(driver);
    }
    /**滑动引导页**/


    /**引导页中点击先体验一下**/
    public static void clickExperience(AppiumUtil appiumUtil, By byElement){
        boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("先体验一下按钮存在，并点击");
        }else
        {
            logger.info("先体验一下按钮不存在");
        }

    }

    /**引导页中点击先注册一下**/
    public static void clickRegister(AppiumUtil appiumUtil,By byElement){

        boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
        }else {
            return;
        }
    }

}
