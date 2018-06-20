package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.OpenTube;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.OpenTubeHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;

/**
 * Created by gjw on 17/3/21.
 * 开通存管银行的方法
 */
public class OpenTubeMethod {

    /**
     * @param appiumUtil
     * @param driver
     * @param mobile     :手机号
     * @param bankoption ：银行的位置
     * @param realname   :名字
     * @return
     */
    public static String[] openTubeMethod(AppiumUtil appiumUtil, AppiumDriver driver, String mobile, String bankoption, String realname, String personid, String provinceoption,
                                          String cityoption, String kaihuhangopyion, String accountno, String verifycode) {
        String[] result = new String[2];
        /**判断是否开通存管银行**/
        int status = GetSQLHelper.getUserOpenOrNotTube(mobile);
        if (status != 2) {
            /**点击立即开通存管**/
            OpenTubeHelper.clickOpenTubeNow(appiumUtil, OpenTube.OT_BUTTON_OPENTUBENOW);
            /** 点击开通的存管的立即开通**/
            OpenTubeHelper.clickPositiveDepositry(appiumUtil, OpenTube.OT_BUTTON_POSITIVEDEPOSITORY);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //APP需要有debug模式，不然只有NATIVE_APP，具体问开发。

            //需特别注意手机内部的Webview的版本，是否与chromedriver对应
            appiumUtil.switchWebview("WEBVIEW_com.trj.hp");

            /**判断用户是否已经实名**/
            String realn = GetSQLHelper.getRralName(mobile);
            //System.out.print(realn);
            if (realn == null) {

                /**填写名字**/
                OpenTubeHelper.typeRealName(OpenTube.OT_INPUT_REALNAME, driver, realname);

                /**填写身份证**/
                OpenTubeHelper.typePersonID(OpenTube.OT_INPUT_IDNO, driver, personid);
                /**选择银行**/
                OpenTubeHelper.clickBank(OpenTube.OT_BUTTON_BANK, driver, bankoption);
                /**选择省份**/
                OpenTubeHelper.clickProvince(OpenTube.OT_BUTTON_PROVINCE, driver, provinceoption);
                /**选择城市**/
                OpenTubeHelper.clickCity(OpenTube.OT_BUTTON_CITY, driver, cityoption);
                /**选择开户行**/
                OpenTubeHelper.clickKaiHuHang(OpenTube.OT_BUTTON_KAIHUHANG, driver, kaihuhangopyion);
                /**输入银行卡号
                 *
                 * 测试环境银行卡号只要末尾是10即可**/
                OpenTubeHelper.typeAccountNo(OpenTube.OT_INPUT_AACCOUNTNO, driver, accountno);
                appiumUtil.click(OpenTube.OT_INPUT_AACCOUNTNO);
                /**输入手机号码**/
                OpenTubeHelper.typeMobile(OpenTube.OT_INPUT_MOBILE, driver, mobile);

                /** 点击获取验证码**
                 *
                 */
                OpenTubeHelper.clickGetCode(appiumUtil, OpenTube.OT_BUTTON_GETCODE);
                /**输入动态码*8/
                 *
                 */
                OpenTubeHelper.typeVerifyCode(OpenTube.OT_INPUT_VERIFYCODE, driver, verifycode);
                /**点击开通**/
                OpenTubeHelper.clickOpenConfirm(OpenTube.OT_BUTTON_CONFIRMOPEN, driver);
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                appiumUtil.switchWebview("WEBVIEW_com.trj.hp");

                /**判断点击开通之后,是否开通成功**/
                if (OpenTubeHelper.existopentubeconfirm(appiumUtil, OpenTube.OT_BUTTON_CONFIRMOPEN, driver)) {
                    result[0] = "Fail";
                    result[1] = "用户已实名，身份证，手机号，银行卡号，或者验证码错误";
                } else {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**银行存管开通之后，点击返回**/
                    OpenTubeHelper.clickOpenback(OpenTube.OT_BUTTON_OPENBACK, driver);
                    result[0] = "Pass";
                    result[1] = "银行存管开通成功";

                }
            } else {//用户已实名，不需填写姓名和身份证
                /**选择银行**/
                OpenTubeHelper.clickBank(OpenTube.OT_BUTTON_BANK, driver, bankoption);
                /**选择省份**/
                OpenTubeHelper.clickProvince(OpenTube.OT_BUTTON_PROVINCE, driver, provinceoption);
                /**选择城市**/
                OpenTubeHelper.clickCity(OpenTube.OT_BUTTON_CITY, driver, cityoption);
                /**选择开户行**/
                OpenTubeHelper.clickKaiHuHang(OpenTube.OT_BUTTON_KAIHUHANG, driver, kaihuhangopyion);
                /**输入银行卡号
                 *
                 * 测试环境银行卡号只要末尾是10即可**/
                OpenTubeHelper.typeAccountNo(OpenTube.OT_INPUT_AACCOUNTNO, driver, accountno);
                /**输入手机号码**/
                OpenTubeHelper.typeMobile(OpenTube.OT_INPUT_MOBILE, driver, mobile);

                /** 点击获取验证码**
                 *
                 */
                OpenTubeHelper.clickGetCode(appiumUtil, OpenTube.OT_BUTTON_GETCODE);
                /**输入动态码*8/
                 *
                 */
                OpenTubeHelper.typeVerifyCode(OpenTube.OT_INPUT_VERIFYCODE, driver, verifycode);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**点击开通**/
                OpenTubeHelper.clickOpenConfirm(OpenTube.OT_BUTTON_CONFIRMOPEN, driver);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**判断点击开通之后,是否开通成功**/
                if (OpenTubeHelper.existopentubeconfirm(appiumUtil, OpenTube.OT_BUTTON_CONFIRMOPEN, driver)) {
                    result[0] = "Fail";
                    result[1] = "用户已实名，身份证，手机号，银行卡号，或者验证码错误";


                } else {

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**银行存管开通之后，点击返回**/
                    OpenTubeHelper.clickOpenback(OpenTube.OT_BUTTON_OPENBACK, driver);
                    result[0] = "Pass";
                    result[1] = "银行存管开通成功";
                }

            }
            /**webview页面操作完，将driver转为NATIVE_APP**/
            driver.context("NATIVE_APP");


            if (result[0].equals("Pass")) {
                /**点击银行存管的返回按钮**/
                OpenTubeHelper.clickTubeBack(appiumUtil, OpenTube.OT_BUTTON_TUBEBack);
            } else {
                /**点击开通存管银行的返回**/
                OpenTubeHelper.clickOpenTubeBack(appiumUtil, OpenTube.OT_BUTTON_OPENTUBEBACK);
            }


        }//已开通存管
        else {
            result[0] = "Pass";
            result[1] = "用户已开通存管";
        }
        return result;
    }
}
