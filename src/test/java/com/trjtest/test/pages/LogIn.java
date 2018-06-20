package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/2/27.
 * 登录模块各个元素
 */
public class LogIn {


    /**登录元素
     * 默认从账户进入
     **/

    /**首页账户元素**/
    public static final By LI_BUTTON_ACCOUNT = By.id("com.trj.hp:id/activity_group_radioButton2");

    /**输入手机号页面输入手机号元素**/
    public static final By LI_INPUT_USERNAME = By.id("com.trj.hp:id/edit_username");

    /** 输入手机号页面下一步按钮**/
    public static final By LI_BUTTON_MOBILENEXT = By.id("com.trj.hp:id/tv_login");

    /**输入密码页面输入密码元素**/
    public static final By LI_INPUT_PASSWORD = By.id("com.trj.hp:id/edit_pwd");

    /**输入密码页面登录按钮 **/
    public static final By LI_BUTTON_LOGININ =By.id("com.trj.hp:id/tv_login");

    /**输入密码忘记密码按钮**/
    public static final By LI_BUTTON_FORGET = By.id("com.trj.hp:id/tv_forget_pwd");

    /**设置手势密码-下次再说**/
    public static final By LI_BUTTON_PATTERNLOCKNEXT = By.id("com.trj.hp:id/dialog_nomessage_bt_negative");

    /**设置手势密码－立即设置**/
    public static final By LI_BUTTON_PATTERNLOCKIM  =By.id("com.trj.hp:id/dialog_nomessage_bt_absolute");

    /**账户中的充值按钮**/
    public static  final By LI_BUTTON_RECHARGE=By.id("com.trj.hp:id/tv_recharge");

    /**账户中的头像按钮**/
    public static final By LI_BUTTON_ACCOUNTCENTER =By.id("com.trj.hp:id/iv_account_center");

    /**个人中心中的设置按钮**/
    public static final By LI_BUTTON_SETTING = By.id("com.trj.hp:id/rl_setting_container");

    /**设置中的退出按钮**/
    public static final By LI_BUTTON_EXIT = By.id("com.trj.hp:id/exit");

    /** 退出登录后的返回按钮**/
    public static final By LI_BUTTON_BACK = By.id("com.trj.hp:id/btn_back");


}
