package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/20.
 * <p>
 * 开通存管的各个元素定位
 */
public class OpenTube {

    /**
     * 账户页面中的立即开通银行存管
     **/
    public static final By OT_BUTTON_OPENTUBENOW = By.id("com.trj.hp:id/tv_open_ecw_account_right_now");

    /**
     * 存管弹窗的立即开通按钮
     **/
    public static final By OT_BUTTON_POSITIVEDEPOSITORY = By.id("com.trj.hp:id/tv_positive");

    /**
     * 开通存管页面，输入客户姓名
     **/
    public static final By OT_INPUT_REALNAME = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[1]/div[2]/input");

    /**
     * 开通存管页面，开户银行选择
     **/
    public static final By OT_BUTTON_BANK = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[4]/div[2]/select");

    /**
     * 开通存管页面，输入证件号码
     **/
    public static final By OT_INPUT_IDNO = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[3]/div[2]/input");

    /**
     * 开通存管页面，省份选择
     **/
    public static final By OT_BUTTON_PROVINCE = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[5]/div[2]/select");

    /**
     * 开通存管页面，城市选择
     **/
    public static final By OT_BUTTON_CITY = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[6]/div[2]/select");

    /**
     * 开通存管页面，开户行选择
     **/
    public static final By OT_BUTTON_KAIHUHANG = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[7]/div[2]/div");

    /**
     * 开通存管页面， 输入银行卡号
     **/
    public static final By OT_INPUT_AACCOUNTNO = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[8]/div[2]/input");

    /**
     * 开通存管页面，手机号输入
     **/
    public static final By OT_INPUT_MOBILE = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[9]/div[2]/input");

    /**
     * 开通存管页面，动态码输入
     **/
    public static final By OT_INPUT_VERIFYCODE = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[10]/div[2]/input");

    /**
     * 开通存管页面，获取动态码按钮
     **/
    public static final By OT_BUTTON_GETCODE = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/ul/li[10]/div[3]/a");

    /**
     * 开通存管页面，确认开通页面
     **/
    public static final By OT_BUTTON_CONFIRMOPEN = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/div/button");

    /**
     * 开通完成之后，返回按钮
     **/
    public static final By OT_BUTTON_OPENBACK = By.xpath("/html/body/ion-nav-view/ion-side-menus/ion-side-menu-content/ion-nav-view/ion-view/ion-content/div[1]/div/div/button");



    /**
     * 开通完成之后银行存管叶脉呢，点击返回
     **/
    public static final By OT_BUTTON_TUBEBack = By.id("com.trj.hp:id/ib_top_bar_back");
    /**
     * 开通存管页面的返回按钮
     **/
    public static final By OT_BUTTON_OPENTUBEBACK = By.id("com.trj.hp:id/btn_back");
}
