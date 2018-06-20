package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/16.
 * 投资页面的各个元素
 */
public class Invest {
    /**投资按钮**/
    public static final By IV_BUTTON_INVEST = By.id("com.trj.hp:id/activity_group_radioButton0");
    /**投资页面中，全部按钮**/
    public static final By IV_BUTTON_ALL = By.id("com.trj.hp:id/tv_tab_1");
    /**投资页面中，铂系按钮**/
    public static final By IV_BUTTON_PLATNINUMGROUP = By.id("com.trj.hp:id/tv_tab_4");
    /**投资页面中，盾系按钮**/
    public static final By IV_BUTTON_SHIELDGROUP = By.id("com.trj.hp:id/tv_tab_2");
    /**投资页面中，赢系按钮**/
    public static final By IV_BUTTON_WINGROUP = By.id("com.trj.hp:id/tv_tab_3");

    /**产品的名称 **/
    public static final By IV_BUTTON_PRODUCTNAME = By.id("com.trj.hp:id/tv_prj_type");
    /**点击产品的按钮**/
    public static final By IV_BUTTON_PRODUCT =By.id("com.trj.hp:id/rl1");

    /** 修改金额的输入框**/
    public static final By IV_INPUT_INVESTAMOUNT = By.id("com.trj.hp:id/cet_m");
    /**立即投资按钮 **/
    public static final By IV_BUTTON_INVESTNOW = By.id("com.trj.hp:id/btn_invest_right_now");

    /**投资完成之后的继续投资按钮**/
    public static final By IV_BUTTON_CONTINUEINVEST = By.id("com.trj.hp:id/btn_contuine");

     /**投资完成之后点击查看账户**/
     public static final By IV_BUTTON_VIEWACCOUNT = By.id("com.trj.hp:id/btn_view_account");


    /**输入金额弹窗的数字1**/
    public static final By IV_BUTTON_SENDAMOUNT1 = By.id("com.trj.hp:id/b1");
    /**输入金额弹窗的数字2**/
    public static final By IV_BUTTON_SENDAMOUNT2 = By.id("com.trj.hp:id/b2");
    /**输入金额弹窗的数字3**/
    public static final By IV_BUTTON_SENDAMOUNT3 = By.id("com.trj.hp:id/b3");
    /**输入金额弹窗的数字4**/
    public static final By IV_BUTTON_SENDAMOUNT4 = By.id("com.trj.hp:id/b4");
    /**输入金额弹窗的数字5**/
    public static final By IV_BUTTON_SENDAMOUNT5 = By.id("com.trj.hp:id/b5");
    /**输入金额弹窗的数字6**/
    public static final By IV_BUTTON_SENDAMOUNT6 = By.id("com.trj.hp:id/b6");
    /**输入金额弹窗的数字7**/
    public static final By IV_BUTTON_SENDAMOUNT7 = By.id("com.trj.hp:id/b7");
    /**输入金额弹窗的数字8**/
    public static final By IV_BUTTON_SENDAMOUNT8 = By.id("com.trj.hp:id/b8");
    /**输入金额弹窗的数字9**/
    public static final By IV_BUTTON_SENDAMOUNT9 = By.id("com.trj.hp:id/b9");
    /**输入金额弹窗的数字0**/
    public static final By IV_BUTTON_SENDAMOUNT0 = By.id("com.trj.hp:id/b0");
    /**输入金额弹窗的删除键**/
    public static final By IV_BUTTON_SENDAMOUNTX = By.id("com.trj.hp:id/bx");


    /**带开标产品，剩余多少时间开标**/
    public static final By IV_BUTTON_DKBREMAININGTIME = By.id("com.trj.hp:id/iv_time");

    /**已满标的产品中，产品详情显示的灰色的已满标按钮**/
    public static final By IV_BUTTON_YMBGRAYBUTTON = By.id("com.trj.hp:id/btn_bid_fill");

    /**已满标等产品中，产品详情页显示去别处看看**/
    public static final By IV_BUTTON_SEEOTHERS = By.id("com.trj.hp:id/btn_see_other");

    /**详情页的返回按钮**/
    public static final By IV_BUTTON_PRODUCTDETAILSBACK = By.id("com.trj.hp:id/ib_top_bar_back");

    /**点击投资后的输入密码弹窗中的 取消 X按钮**/
    public static final By IV_BUTTON_INVESTCLOSE = By.id("com.trj.hp:id/iv_close");

    /**存管用户投资的短信验证码输入框**/
    public static final By IV_INPUT_VERIFYCODE = By.id("com.trj.hp:id/et_sms_code");

    /**存管用户投资点击发送验证按钮**/
    public static final By IV_BUTTON_SENDVERIFYCODE = By.id("com.trj.hp:id/tv_send_sms_code");

    /**存管用户投资确定投资按钮**/
    public static final By IV_BUTTON_TUBECONFIRMINVEST = By.id("com.trj.hp:id/tv_confirm");

    /**存管用户投资输入验证码的返回按钮**／
     *
     */
    public static final By IV_BUTTON_TUBETYPECODEBACK = By.id("com.trj.hp:id/ib_top_bar_back");


}
