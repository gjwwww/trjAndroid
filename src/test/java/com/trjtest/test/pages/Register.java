package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/6.
 * 注册时各个元素的定位信息
 * 注册默认从点击账户元素开始进行
 */
public class Register {

    /**首页账户元素**/
    public static final By RG_BUTTON_ACCOUNT = By.id("com.trj.hp:id/activity_group_radioButton2");

    /**输入手机号页面输入手机号元素**/
    public static final By RG_INPUT_USERNAME = By.id("com.trj.hp:id/edit_username");

    /** 输入手机号页面下一步按钮**/
    public static final By RG_BUTTON_MOBILENEXT = By.id("com.trj.hp:id/tv_login");

    /**注册页面发送验证码按钮**/
    public static final By RG_BUTTON_SENDVERIFYCODE = By.id("com.trj.hp:id/tv_send_verify_code");

    /**注册页面输入验证码的输入框**/
    public static final By RG_INPUT_VERIFYCODE = By.id("com.trj.hp:id/et_verify_code");

    /**注册页面输入密码的输入框**/
    public static final By RG_INPUT_PASSWORD = By.id("com.trj.hp:id/et_psw");

    /**注册页面注册按钮**/
    public static final By RG_BUTTON_REGISTER =By.id("com.trj.hp:id/tv_register");

    /**注册成功页面中我知道了按钮**/
    public static final By Rg_BUTTON_SUCCESSKNOW = By.id("com.trj.hp:id/withdraw_succ_submit");

    /**开通银行存管弹窗的暂不开通按钮**/
    public static final By RG_BUTTON_NEGATIVEDEPOSITORY = By.id("com.trj.hp:id/tv_negative");

    /**点击暂不开通之后的我知道了按钮**/
    public static final By RG_BUTTON_CONFIRMKNOW = By.id("com.trj.hp:id/tv_confirm");

    /**注册页面的推荐人代码**/
    public static final By RG_BUTTON_RECOMMEND = By.id("com.trj.hp:id/tv_recommend_code");

    /**输入推荐人代码的输入框**/
    public static final By RG_INPUT_RECOMMEND = By.id("com.trj.hp:id/et_recommend_code");

    /**确认推荐人代码**/
    public static final By RG_BUTTON_RECOMMENDCONFIRM =By.id("com.trj.hp:id/tv_confirm");

    /**二次登录页的马上注册按钮**/
    public static final By RG_BUTTON_REGISTERNOW = By.id("com.trj.hp:id/tv_register_right_now");

    /**点击立即注册后，手机号的删除键**/
    public static final By RG_BUTTON_DELETE =By.id("com.trj.hp:id/iv_user_delete");
}
