package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/17.
 *
 *  提现的各个元素
 */
public class Withdraw {

    /**账户页面中的提现按钮**/
    public static final By WD_BUTTON_WITHDRAWCONTAINER = By.id("com.trj.hp:id/ll_withdrawals_container");

    /**提现页面中的开户行 **/
    public static final By WD_BUTTON_KAIHUHANG = By.id("com.trj.hp:id/withdrawals_ll_kaihuhang");

    /**开户城市 省**/

    public static final By WD_BUTTON_PROVINCE = By.id("com.trj.hp:id/tv_name");

    /**开户城市 市 **/
    public static final By WD_BUTTON_CITY =By.id("com.trj.hp:id/tv_name");

    /** 开户支行 **/
    public static final By WD_BUTTON_BRANCH = By.id("com.trj.hp:id/tv_name");

    /**提现金额**/
    public static final By WD_BUTTON_CASHOUTAMOUNT =By.id("com.trj.hp:id/user_keyboard_edit_text_tv_value");

    /** 点击免费提现**/
    public static  final By WD_BUTTON_FREEWITHDRAW = By.id("com.trj.hp:id/withdrawals_bt_submit");

    /** 输入支付密码的提现按钮 **/
    public static final By WD_BUTTON_WITHDRAW = By.id("com.trj.hp:id/pay_pwd_pay_bt_submit");

    /**提现完成的确定按钮**/
    public static final By WD_BUTTON_WITHDRAWSUCCESS = By.id("com.trj.hp:id/withdraw_succ_submit");

    /**输入金额键盘1**/
    public static final By WD_BUTTON_SENDAMOUNT1 = By.id("com.trj.hp:id/keyborad_bt_1");
    /**输入金额键盘2**/
    public static final By WD_BUTTON_SENDAMOUNT2 = By.id("com.trj.hp:id/keyborad_bt_2");
    /**输入金额键盘3**/
    public static final By WD_BUTTON_SENDAMOUNT3 = By.id("com.trj.hp:id/keyborad_bt_3");
    /**输入金额键盘4**/
    public static final By WD_BUTTON_SENDAMOUNT4 = By.id("com.trj.hp:id/keyborad_bt_4");
    /**输入金额键盘5**/
    public static final By WD_BUTTON_SENDAMOUNT5 = By.id("com.trj.hp:id/keyborad_bt_5");
    /**输入金额键盘6**/
    public static final By WD_BUTTON_SENDAMOUNT6 = By.id("com.trj.hp:id/keyborad_bt_6");
    /**输入金额键盘7**/
    public static final By WD_BUTTON_SENDAMOUNT7 = By.id("com.trj.hp:id/keyborad_bt_7");
    /**输入金额键盘8**/
    public static final By WD_BUTTON_SENDAMOUNT8 = By.id("com.trj.hp:id/keyborad_bt_8");
    /**输入金额键盘9**/
    public static final By WD_BUTTON_SENDAMOUNT9 = By.id("com.trj.hp:id/keyborad_bt_9");
    /**输入金额键盘0**/
    public static final By WD_BUTTON_SENDAMOUNT0 = By.id("com.trj.hp:id/keyborad_bt_0");
    /**输入金额键盘.**/
    public static final By WD_BUTTON_SENDAMOUNTpoint = By.id("com.trj.hp:id/keyborad_bt_dot");
    /**输入金额键盘隐藏**/
    public static final By WD_BUTTON_SENDAMOUNTHIDE = By.id("com.trj.hp:id/keyboard_ib_hidden");

    /**没有实名的情况下，点击账户页面提现的弹框中现在去充值按钮**/
    public static final By WD_BUTTON_RECHARGENOW = By.id("com.trj.hp:id/dialog_message_bt_absolute");

    /**没有实名的情况下，点击账户页面提现的弹框中取消按钮**/
    public static final By WD_BUTTON_CANCEL = By.id("com.trj.hp:id/dialog_message_bt_negative");

    /**提现页面中的返回按钮**/
    public static final By WD_BUTTON_TUBEWITHDRAWBACK = By.id("com.trj.hp:id/ib_top_bar_back");
    /**存管用户输入提现金额**/
    public static final By WD_INPUT_TUBECASHOUTAMOUNT = By.id("com.trj.hp:id/et_cashout_amount");
    /**存管用户输入支付密码**/
    public static final By WD_INPUT_TUBEPAYPASSWORD = By.id("com.trj.hp:id/et_pay_password");

    /**存管用户提现的免费提现按钮**/
    public static final By WD_BUTTON_TUBEFREEWITHDRAW = By.id("com.trj.hp:id/btn_cashout_submit");

    /**存管用户提现输入动态码**/
    public static final By WD_INPUT_TUBEVERIFYCODE = By.id("com.trj.hp:id/et_sms_code");
    /**存管用户点击确认提现按钮**/
    public static final By WD_BUTTON_TUBEWITHDRAWCONFIRM = By.id("com.trj.hp:id/tv_confirm");
    /**非存管提现页面的返回按钮 **/
    public static final By WD_BUTTON_BACK = By.id("com.trj.hp:id/btn_back");

    /**非存管提现输入支付密码的q取消按钮**/
    public static final By WD_BUTTON_SENDPAYPASSWORDCANCEL = By.id("com.trj.hp:id/pay_pwd_pay_bt_cancel");
}
