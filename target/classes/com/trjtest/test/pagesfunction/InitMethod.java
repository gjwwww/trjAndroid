package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.InitPage;
import com.trjtest.test.pageshelper.InitPageHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by gjw on 17/3/8.
 */
public class InitMethod {
    public static void initMethod(AppiumUtil appiumUtil, AppiumDriver driver) {

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        /**滑动引导页**/
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                InitPageHelper.swipeLeft(appiumUtil, driver);
            } else {
                InitPageHelper.swipeLeft(appiumUtil, driver);
            }
        }
        /**引导页中点击先体验一下**/
        InitPageHelper.clickExperience(appiumUtil, InitPage.IP_BUTTON_EXPERIENCE);
    }
}
