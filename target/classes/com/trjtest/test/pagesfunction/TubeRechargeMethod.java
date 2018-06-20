package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.Recharge;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.RechargeHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

import java.util.Set;

/**
 * Created by gjw on 17/3/23.
 * 存管用户充值
 */
public class TubeRechargeMethod {
    public static Logger logger = Logger.getLogger(TubeRechargeMethod.class);

    public static String[] tubeRechargeMethod(AppiumUtil appiumUtil, String mobile, AppiumDriver driver, String paypassword, String paypasswordconfirm
            , String bankoption, String realname, String personid, String provinceoption, String cityoption, String kaihuhangoption, String accountoption, String verifycode,
                                              String rechargemoney) {
        String[] result = new String[2];
        String[] setpaypasswordresult = new String[2];

        /**判断用户是否设置支付密码  变量等于0表示没有设置支付密码**/
        int is_paypwd_mobile_set = GetSQLHelper.getUserSetOrNotPayPassword(mobile);
        if (is_paypwd_mobile_set == 0) {
            logger.info("用户没有设置支付密码，进行设置支付密码");
            /**调用设置支付密码**/
            setpaypasswordresult = SetPayPassWordMethod.setPayPassword(appiumUtil, paypassword, paypasswordconfirm);
            if (setpaypasswordresult[0].equals("Pass")) {
                logger.info("设置支付密码成功");
                /**点击充值**/
                RechargeHelper.clickrecharge(appiumUtil, Recharge.RC_BUTTON_RECHARGE);
                /**切换到 webview**/
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //APP需要有debug模式，不然只有NATIVE_APP，具体问开发。

                //需特别注意手机内部的Webview的版本，是否与chromedriver对应
                appiumUtil.switchWebview("WEBVIEW_com.trj.hp");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Boolean b = appiumUtil.doesElementsExist(Recharge.RG_BUTTON_RECHARGECONFIRM);
                System.out.print(b);
                /**输入存管充值页面的金额**/
                RechargeHelper.typeRechargeMoney(appiumUtil, Recharge.RG_INPUT_RECHARGEMONEY, rechargemoney);
                /**输入存管充值页面的支付密码**/
                RechargeHelper.typePayPassword(appiumUtil, Recharge.RG_INPUT_PAYPASSWORD, paypassword);
                /**点击存管充值页面的免费开通**/
                RechargeHelper.clickFreeRecharge(appiumUtil, Recharge.RG_BUTTON_FREERECHARGE);
                /**切换到webview**/
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //APP需要有debug模式，不然只有NATIVE_APP，具体问开发。

                //需特别注意手机内部的Webview的版本，是否与chromedriver对应
                //appiumUtil.switchWebview("WEBVIEW_com.trj.hp");
                //Boolean a = appiumUtil.doesElementsExist(Recharge.RG_BUTTON_FREERECHARGE);
                //System.out.println(driver.findElement(Recharge.RG_BUTTON_FREERECHARGE).isDisplayed());
                /**判断免费充值按钮是否存在，存在表示支付密码错误**/
                if (driver.findElement(Recharge.RG_BUTTON_FREERECHARGE).isDisplayed()) {

                    result[0] = "Fail";
                    result[1] = "支付密码或者充值金额格式错误";
                    logger.info("支付密码或者充值金额格式错误");

                } else {//金额格式或者支付密码正确

                    /**输入存管充值页面的动态码**/
                    RechargeHelper.typeverifycode(appiumUtil, Recharge.RG_INPUT_VERIFYCODE, verifycode);
                    /**点击存管充值页面的额确认开通按钮**/
                    RechargeHelper.clickRechargeConfirm(appiumUtil, Recharge.RG_BUTTON_RECHARGECONFIRM);

                    /**切换到webview**/
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //APP需要有debug模式，不然只有NATIVE_APP，具体问开发。

                    //需特别注意手机内部的Webview的版本，是否与chromedriver对应
                    //appiumUtil.switchWebview("WEBVIEW_com.trj.hp");
                    //System.out.println(driver.findElement(Recharge.RG_BUTTON_FREERECHARGE).isDisplayed());
                    if (appiumUtil.doesElementsExist(Recharge.RG_BUTTON_RECHARGECONFIRM)) {//判断动态码是否正确
                        result[0] = "Fail";
                        result[1] = "动态码错误";
                    } else if (appiumUtil.doesElementsExist(Recharge.RG_BUTTON_CONTINUERECHARGE)) {//判断继续充值按钮是否存在，也是判断是否充值成功的标志
                        result[0] = "Pass";
                        result[1] = "充值成功";
                        /**点击充值成功账户概况**/
                        RechargeHelper.clickPassAccountView(appiumUtil, Recharge.RG_BUTTON_ACCOUNTVIEW);

                    } else {//充值失败
                        result[0] = "Fail";
                        result[1] = "充值金额错误,充值金额大于50000万，或者卡内余额不足";
                        /**点击充值失败后的账户概况**/
                        RechargeHelper.clickFailAccountView(appiumUtil, Recharge.RG_BUTTON_FAILACCOUNTVIEW);
                    }
                }
                /**切换到NATIVE_APP**/
                driver.context("NATIVE_APP");
                AppiumUtil.killProcess("chromedriver");
                if (result[0].equals("Fail")) {
                    if (appiumUtil.doesElementsExist(Recharge.RG_BUTTON_TUBERECHARGEBACK)) {//判断存管充值页面中的返回键是否存在
                        /**点击存管充值页面的返回键 **/
                        appiumUtil.click(Recharge.RG_BUTTON_TUBERECHARGEBACK);
                        logger.info("点击存管充值页面的返回按钮");
                    }
                }

            } else {
                logger.info(result[1]);
                result = setpaypasswordresult;
            }

        } else {//用户已经设置过支付密码

            logger.info("用户已经设置过支付密码");
            /**点击充值**/
            RechargeHelper.clickrecharge(appiumUtil, Recharge.RC_BUTTON_RECHARGE);
            /**切换到 webview**/
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //APP需要有debug模式，不然只有NATIVE_APP，具体问开发。

            //需特别注意手机内部的Webview的版本，是否与chromedriver对应
            appiumUtil.switchWebview("WEBVIEW_com.trj.hp");

            Boolean b= appiumUtil.doesElementsExist(Recharge.RG_BUTTON_RECHARGECONFIRM);
            System.out.print(b);
            /**输入存管充值页面的金额**/
            RechargeHelper.typeRechargeMoney(appiumUtil, Recharge.RG_INPUT_RECHARGEMONEY,rechargemoney);
            /**输入存管充值页面的支付密码**/
            RechargeHelper.typePayPassword(appiumUtil,Recharge.RG_INPUT_PAYPASSWORD,paypassword);
            /**点击存管充值页面的免费开通**/
            RechargeHelper.clickFreeRecharge(appiumUtil,Recharge.RG_BUTTON_FREERECHARGE);
            /**切换到webview**/
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //APP需要有debug模式，不然只有NATIVE_APP，具体问开发。

            //需特别注意手机内部的Webview的版本，是否与chromedriver对应
            //appiumUtil.switchWebview("WEBVIEW_com.trj.hp");
            //Boolean a = appiumUtil.doesElementsExist(Recharge.RG_BUTTON_FREERECHARGE);
            //System.out.println(driver.findElement(Recharge.RG_BUTTON_FREERECHARGE).isDisplayed());
            /**判断免费充值按钮是否存在，存在表示支付密码错误**/
            if (driver.findElement(Recharge.RG_BUTTON_FREERECHARGE).isDisplayed()){

                result[0] = "Fail";
                result[1] = "支付密码或者充值金额格式错误" ;
                logger.info("支付密码或者充值金额格式错误");

            }else {//金额格式或者支付密码正确

                /**输入存管充值页面的动态码**/
                RechargeHelper.typeverifycode(appiumUtil,Recharge.RG_INPUT_VERIFYCODE,verifycode);
                /**点击存管充值页面的额确认开通按钮**/
                RechargeHelper.clickRechargeConfirm(appiumUtil,Recharge.RG_BUTTON_RECHARGECONFIRM);

                /**切换到webview**/
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //APP需要有debug模式，不然只有NATIVE_APP，具体问开发。

                //需特别注意手机内部的Webview的版本，是否与chromedriver对应
                //appiumUtil.switchWebview("WEBVIEW_com.trj.hp");
                //System.out.println(driver.findElement(Recharge.RG_BUTTON_FREERECHARGE).isDisplayed());
                if (appiumUtil.doesElementsExist(Recharge.RG_BUTTON_RECHARGECONFIRM)) {//判断动态码是否正确
                    result[0] = "Fail";
                    result[1] = "动态码错误";
                } else if (appiumUtil.doesElementsExist(Recharge.RG_BUTTON_CONTINUERECHARGE)){//判断继续充值按钮是否存在，也是判断是否充值成功的标志
                    result[0]= "Pass";
                    result[1]= "充值成功";
                    /**点击充值成功账户概况**/
                    RechargeHelper.clickPassAccountView(appiumUtil,Recharge.RG_BUTTON_ACCOUNTVIEW);

                }else{//充值失败
                    result[0] = "Fail";
                    result[1] = "充值金额错误,充值金额大于50000万，或者卡内余额不足";
                    /**点击充值失败后的账户概况**/
                    RechargeHelper.clickFailAccountView(appiumUtil,Recharge.RG_BUTTON_FAILACCOUNTVIEW);
                }
            }
            /**切换到NATIVE_APP**/
            driver.context("NATIVE_APP");
            AppiumUtil.killProcess("chromedriver");
            if (result[0].equals("Fail")){
                if (appiumUtil.doesElementsExist(Recharge.RG_BUTTON_TUBERECHARGEBACK)){//判断存管充值页面中的返回键是否存在
                    /**点击存管充值页面的返回键 **/
                    appiumUtil.click(Recharge.RG_BUTTON_TUBERECHARGEBACK);
                    logger.info("点击存管充值页面的返回按钮");
                }
            }
        }


        return result;
    }
}
