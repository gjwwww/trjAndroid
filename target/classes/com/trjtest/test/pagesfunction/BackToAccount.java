package com.trjtest.test.pagesfunction;

import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gjw on 2017/8/29.
 */
public class BackToAccount {
    /**
     * 账户按钮元素定位
     **/
    private final By BT_BUTTON_ACCOUNT = By.id("com.trj.hp:id/activity_group_radioButton2");

    /**
     * 返回按钮元素定位
     **/
    private List<By> backList;


    /**
     * 账户按钮元素
     **/
    private String account = "activity_group_radioButton2";
    /**
     * 返回按钮元素
     **/
    private List<String> backWebelementList;

    public BackToAccount() {
        backList = new ArrayList<>();
        backWebelementList = new ArrayList<>();
        backList.add(By.id("com.trj.hp:id/btn_back"));
        backList.add(By.id("com.trj.hp:id/ib_top_bar_back"));
        backWebelementList.add("btn_back");
        backWebelementList.add("ib_top_bar_back");

    }

    public void backToAccount(AppiumUtil appiumUtil, AppiumDriver driver, String pageSource) {
        if (!pageSource.contains(account)) {
            for (int i = 0; i < backWebelementList.size(); i++) {
                if (pageSource.contains(backWebelementList.get(i))) {
                    appiumUtil.click(backList.get(i));
                    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    String newPageSource = driver.getPageSource();
                    backToAccount(appiumUtil, driver, newPageSource);
                }
            }
        }
    }
}
