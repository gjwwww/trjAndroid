package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/15.
 * 充值时各个元素
 */
public class Recharge {
    /**
     * 充值按钮元素
     **/
    public static final By RC_BUTTON_RECHARGE = By.id("com.trj.hp:id/tv_recharge");

    /**
     * 存管充值页面，输入金额
     **/
    public static final By RG_INPUT_RECHARGEMONEY = By.xpath("//*[@id=\"submitPassword\"]/div[2]/p/input");

    /**
     * 存管充值页面,输入支付密码
     **/
    public static final By RG_INPUT_PAYPASSWORD = By.xpath("//*[@id=\"submitPassword\"]/div[3]/p/input");

    /**
     * 存管充值页面，免费充值按钮
     **/
    public static final By RG_BUTTON_FREERECHARGE = By.xpath("//*[@id=\"submitPassword\"]/div[4]/p/input");

    /**
     * 存管充值页面，输入动态码
     **/
    public static final By RG_INPUT_VERIFYCODE = By.xpath("//*[@id=\"code\"]");

    /**
     * 存管充值页面，确认充值按钮
     **/
    public static final By RG_BUTTON_RECHARGECONFIRM = By.xpath("//*[@id=\"submitPay\"]/div[1]/p/input");

    /**
     * 存管充值成功页面，账户概况按钮
     **/
    public static final By RG_BUTTON_ACCOUNTVIEW = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div[2]/a[2]");

    /**
     * 存管充值完成页面，立即投资按钮
     **/
    public static final By RG_BUTTON_INVESTNOW = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div[2]/a[1]");

    /**
     * 存管充值完成页面，继续充值按钮
     **/
    public static final By RG_BUTTON_CONTINUERECHARGE = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div[2]/a[3]");


    /**
     * 存管充值失败页面，重新充值按钮
     **/
    public static final By RG_BUTTON_RERECHARGE = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div[2]/a[1]");

    /**
     * 存管充值失败页面，账户概况按钮
     **/
    public static final By RG_BUTTON_FAILACCOUNTVIEW = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div[2]/a[2]");

    /**
     * 窜管充值页面的返回按钮
     **/
    public static final By RG_BUTTON_TUBERECHARGEBACK = By.id("com.trj.hp:id/btn_back");

}
