package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.AutoBid;
import com.trjtest.test.pageshelper.AutoBidHelper;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;

/**
 * Created by gjw on 17/6/23.
 */
public class OpenAutoBidMethod {

    public static Logger logger = Logger.getLogger(OpenTubeMethod.class);

    public static String[] openAutoBid(AppiumUtil appiumUtil, AppiumDriver driver, String verifycode,String mobile,String enviroment) {


        String[] result = new String[2];

        /**判断用户是否为存管用户**/
        int status  = GetSQLHelper.getUserOpenOrNotTube(mobile);

        /**切换webview**/

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //APP需要有debug模式，不然只有NATIVE_APP，具体问开发。

        //需特别注意手机内部的Webview的版本，是否与chromedriver对应
        appiumUtil.switchWebview("WEBVIEW_com.trj.hp");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**点击开通一键投资的立即开通按钮**/
        AutoBidHelper.clickOpenAutoBidNow(appiumUtil, AutoBid.AB_BUTTON_OPENNOW);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        /**在开通一键投资页面中点击获取验证码 **/
//        AutoBidHelper.clickGetCode(appiumUtil,AutoBid.AB_BUTTON_GETCODE,driver);
//        if (status!=2){
//            verifycode = GetSQLHelper.getUserMobileCode(mobile);
//        }
        if (status !=2) {
            /**设置cookiestore**/
            CookieStore cookieStore = BaseFunction.setCookieStore(driver);
            CloseableHttpClient httpClient = null;
            /**请求开通一键投资发送短信验证码接口**/
            WapSendOpenBidMobieCode wapSendOpenBidMobieCode = new WapSendOpenBidMobieCode();
            WapObject wapObject = wapSendOpenBidMobieCode.sendOpenAutoBidMobileCode(enviroment,httpClient, cookieStore);
            if (wapObject.getMessage().contains("动态码发送成功!")){
                verifycode =GetSQLHelper.getUserMobileCode(mobile);
            }
        }

        /**在开通一键投资页面中输入短信验证码**/
        AutoBidHelper.typeOpenVerifyCode(appiumUtil, AutoBid.AB_INPUT_OPENVERIFYCODE, verifycode);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**点击开通一键投资页面的确认开通按钮**/
        AutoBidHelper.clickCofirmOpen(appiumUtil, AutoBid.AB_BUTTON_CONFIRMOPEN,driver);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int is_auto = GetSQLHelper.getUserIsAutoBid(mobile);
        if (is_auto != 1) {
            result[0] = "Fail";
            result[1] = "短信验证码错误";
            logger.info(result[1]);
            return result;
        } else {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**点击开通一键投资页面的确定按钮**/
            AutoBidHelper.clickFinshOpen(appiumUtil, AutoBid.AB_BUTTON_FINSHOPEN);



            /**webview页面操作完，将driver转为NATIVE_APP**/
            driver.context("NATIVE_APP");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            result[0] = "Pass";
            result[1] = "开通一键投资成功";
            logger.info(result[1]);
            return result;
        }

    }
}
