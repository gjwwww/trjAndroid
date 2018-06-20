package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/15.
 * 个人中方的各个元素
 */
public class AccountCenter {
    /**账户中头像按钮**/
    public static final By AC_BUTTON_ACCOUNTCRNTER = By.id("com.trj.hp:id/iv_account_center");
    /**个人中心的密码管理**/
    public static final By AC_BUUTTON_PASSWORDMANAGE = By.id("com.trj.hp:id/rl_pwd_management_container");
    /**密码管理里的支付密码管理**/
    public static final By AC_BUTTON_PAYPASSWORD =By.id("com.trj.hp:id/ll_pay_pwd");

    /**支付密码输入框 数字1
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND1 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_1");

    /**支付密码输入框 数字2
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND2 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_2");
    /**支付密码输入框 数字3
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND3 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_3");

    /**支付密码输入框 数字4
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND4 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_4");

    /**支付密码输入框 数字5
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND5 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_5");

    /**支付密码输入框 数字6
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND6 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_6");

    /**支付密码输入框 数字7
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND7 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_7");
    /**支付密码输入框 数字8
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND8 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_8");
    /**支付密码输入框 数字9
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSEND9 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_9");
    /** 支付密码输入框 数字0**/
    public static final By AC_BUTTON_PAYPASSWORDSEND0 = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_0");
    /**支付密码设置提交按钮**/
    public static final By AC_BUTTON_PAYPASSWORDSUBMIT = By.id("com.trj.hp:id/btn_submit");


    /**确认支付密码的输入框
     *  需要在点击的时候加上所需的按键
     * **/
    public static final By AC_BUTTON_PAYPASSWORDSENDCONFIRM = By.id("com.trj.hp:id/pay_pwd_keyboard_ib_");

    /** 确认支付密码的确定按钮**/
    public static final By AC_BUTTON_PAYPASSSWORDSUBMITCONFIRM = By.id("com.trj.hp:id/btn_commit");

    /**重新设置按钮**/
    public static final By AC_BUTTON_PAYPASSWORDRESET = By.id("com.trj.hp:id/btn_reset");

    /**密码管理返回按钮**/
    public static final By AC_BUTTON_BACK = By.id("com.trj.hp:id/btn_back");
    /**个人中心返回按钮 **/
    public static final By AC_BUTTON_ACCOUNTCENTERBACK = By.id("com.trj.hp:id/ib_top_bar_back");


}
