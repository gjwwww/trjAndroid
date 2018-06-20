package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pages.Register;
import com.trjtest.test.pageshelper.LogInHelper;
import com.trjtest.test.pageshelper.RegisterHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;

/**
 * Created by gjw on 17/3/8.
 * 登录功能
 */
public class LoginMethod {

    /**
     * @param mobile   ：手机号
     * @param password :密码
     * @return
     */
    public static String[] loginmethod(AppiumUtil appiumUtil, String mobile, String password, AppiumDriver driver) {
        /**定义一个数组，第一个元素代表用例执行情况，第二个元素代表具体的执行信息**/
        String[] result = new String[2];
        /**判断是否是二次登录**/
        if (!LogInHelper.forgetDoseExist(appiumUtil, LogIn.LI_BUTTON_FORGET)) {

            /**输入手机号**/
            LogInHelper.typeMoblie(appiumUtil, LogIn.LI_INPUT_USERNAME, mobile);


            /**点击下一步**/
            LogInHelper.clickNext(appiumUtil, LogIn.LI_BUTTON_MOBILENEXT);


            /**判断输入密码页面密码输入框是否存在**/
            if (!LogInHelper.passwordDoseexist(appiumUtil, LogIn.LI_INPUT_PASSWORD)) {
                result[0] = "Fail";
                result[1] = "手机号格式错误";
                return result;
            } /**判断用户是否已经注册**/
            else if (RegisterHelper.registerDoseExist(appiumUtil, Register.RG_BUTTON_REGISTER)) {
                result[0] = "Fail";
                result[1] = "手机号未注册";
                return result;
            } else {
                /**输入密码**
                 */
                LogInHelper.typePassword(appiumUtil, LogIn.LI_INPUT_PASSWORD, password);

                /**点击登录**/
                LogInHelper.clickLogin(appiumUtil, LogIn.LI_BUTTON_LOGININ);

                /**判断输入密码后，登录按钮是否仍然存在**/
                if (LogInHelper.logInDoesExist(appiumUtil, LogIn.LI_BUTTON_LOGININ)) {
                    result[0] = "Fail";
                    result[1] = "密码错误";
                    return result;
                } else {
                    if (appiumUtil.doesElementsExist(LogIn.LI_BUTTON_PATTERNLOCKNEXT)) {
                        /**点击手势密码-下次再说**/
                        LogInHelper.clickPatternLockNet(appiumUtil, LogIn.LI_BUTTON_PATTERNLOCKNEXT);
                    }
                    result[0] = "Pass";
                    result[1] = "登录成功";
                    return result;

                }
            }
        } else {
            /**清空手机号**/
            LogInHelper.clearMobile(appiumUtil, LogIn.LI_INPUT_USERNAME);
            /**输入手机号**/
            LogInHelper.typeMoblie(appiumUtil, LogIn.LI_INPUT_USERNAME, mobile);
            /**输入密码**
             */
            LogInHelper.typePassword(appiumUtil, LogIn.LI_INPUT_PASSWORD, password);
            /**点击登录**/
            LogInHelper.clickLogin(appiumUtil, LogIn.LI_BUTTON_LOGININ);
            if (LogInHelper.logInDoesExist(appiumUtil, LogIn.LI_BUTTON_LOGININ)) {
                result[0] = "Fail";
                result[1] = "手机号或者密码错误或者手机未注册";
                return result;
            } else {
                if (appiumUtil.doesElementsExist(LogIn.LI_BUTTON_PATTERNLOCKNEXT)){
                    /**点击手势密码-下次再说**/
                    LogInHelper.clickPatternLockNet(appiumUtil, LogIn.LI_BUTTON_PATTERNLOCKNEXT);
                }
                result[0] = "Pass";
                result[1] = "登录成功";
                return result;
            }
        }
    }
}
