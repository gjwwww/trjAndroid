package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/6/22.
 * 一键投资的各个元素
 */
public class AutoBid {

    /**
     * 头像按钮
     **/
    public static final By AB_BUTTON_ACCOUNTERCENTER = By.id("com.trj.hp:id/iv_account_center");
    /**
     * 设置中的一键投资按钮
     **/
    public static final By AB_BUTTON_ACCOUNTERAUTOBID = By.id("com.trj.hp:id/rl_auto_invest_container");

    /**
     * 开通一键投资的立即开通按钮
     **/
    public static final By AB_BUTTON_OPENNOW = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/div[4]/button");

    /**
     * 开通一键投资页面的获取短信验证码
     **/

    public static final By AB_BUTTON_GETCODE = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/div[2]/div/label/div");
    /**
     * 开通一键投资的短信验证码输入框
     **/
        public static final By AB_INPUT_OPENVERIFYCODE = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/div[2]/div/label/input");

    /**
     * 开通一键投资的确认开通按钮
     **/
    public static final By AB_BUTTON_CONFIRMOPEN = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/div[3]/button");

    /**
     * 开通一键投资的确定按钮
     **/
    public static final By AB_BUTTON_FINSHOPEN = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/div[3]/button");
}