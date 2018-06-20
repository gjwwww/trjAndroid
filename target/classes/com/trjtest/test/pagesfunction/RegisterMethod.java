package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pages.Register;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.LogInHelper;
import com.trjtest.test.pageshelper.RegisterHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by gjw on 17/3/14.
 * 注册方法
 */
public class RegisterMethod {

    /**
     * @param mobile    :手机号
     * @param password  :密码
     * @param recommendcode  :推荐人代码
     * * @return
     */

    public static  String[] registerMethod(AppiumUtil appiumUtil, String mobile, String password, AppiumDriver driver,String recommendcode) {
        /**定义一个数组，第一个元素代表用例执行情况，第二个元素代表具体的执行信息**/
        String[] result = new String[2];


        /**判断是否是二次登录**/
        if (!LogInHelper.forgetDoseExist(appiumUtil, LogIn.LI_BUTTON_FORGET)) {
            /**输入手机号**/
            RegisterHelper.typeMoblie(appiumUtil, Register.RG_INPUT_USERNAME, mobile);
            /**点击下一步**/
            RegisterHelper.clickNext(appiumUtil, Register.RG_BUTTON_MOBILENEXT);

            /**判断手机号是否输入正确**/
            if (appiumUtil.doesElementsExist(Register.RG_BUTTON_SENDVERIFYCODE)) {

                /**点击获取验证码**/
                RegisterHelper.clickSendVerifyCode(appiumUtil, Register.RG_BUTTON_SENDVERIFYCODE);
                /**滑动验获取验证码**/
                //appiumUtil.swipe(720,1280,1240,1280);
                //driver.swipe(720,1280,1190,1280,2000);
                RegisterHelper.swipeRight(appiumUtil, driver);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                /**从数据库获取验证码**/
                GetSQLHelper getSQLHelper = new GetSQLHelper();
                String code = getSQLHelper.getCode();
                /**输入验证码**/
                RegisterHelper.typeVerifyCode(appiumUtil, Register.RG_INPUT_VERIFYCODE, code);
                /**输入密码**/
                RegisterHelper.typePassword(appiumUtil, Register.RG_INPUT_PASSWORD, password);

                /**判断是否需要填写推荐人代码**/
                if (!Objects.equals(recommendcode, "0")) {
                    /** 点击推荐人代码**/
                    RegisterHelper.clickCommend(appiumUtil, Register.RG_BUTTON_RECOMMEND);
                    /**输入推荐人代码 **/
                    RegisterHelper.typeCommend(appiumUtil, Register.RG_INPUT_RECOMMEND, recommendcode);
                    /**点击推荐人代码的确认框**/
                    RegisterHelper.clickCommendConfirm(appiumUtil, Register.RG_BUTTON_RECOMMENDCONFIRM);
                }
                /**点击注册**/
                RegisterHelper.clickRegister(appiumUtil, Register.RG_BUTTON_REGISTER);

                /**判断点击注册后，我知道了按钮是否存在**/
                if (appiumUtil.doesElementsExist(Register.Rg_BUTTON_SUCCESSKNOW)) {
                    result[0] = GetSQLHelper.getRegisterResult(mobile);
                    if(result[0].equals("Pass")){
                        result[1] = "注册成功 ";
                    }else {
                        result[1] = "注册的手机号未在数据库中存在";
                    }
                } else { //注册失败
                    result[0] = "Fail";
                    result[1] = " 推荐人代码或者动态码或者密码格式错误";
                }
            } else if (appiumUtil.doesElementsExist(LogIn.LI_BUTTON_FORGET))     //判断是否为登录
            {
                result[0] = "Fail";
                result[1] = "手机号已被注册";
            } else {     //手机号格式错误
                result[0] = "Fail";
                result[1] = "手机号格式错误";
            }
        }
        //登录后注册，或者注册后注册
        else{
            /**在二次登录页点击立即注册**/
            RegisterHelper.clickRegisterNow(appiumUtil,Register.RG_BUTTON_REGISTERNOW);
            /**点击立即注册后的删除手机号按钮**/
            RegisterHelper.clickDelete(appiumUtil,Register.RG_BUTTON_DELETE);
            /**输入手机号**/
            RegisterHelper.typeMoblie(appiumUtil, Register.RG_INPUT_USERNAME, mobile);
            /**点击下一步**/
            RegisterHelper.clickNext(appiumUtil, Register.RG_BUTTON_MOBILENEXT);

            /**判断手机号是否输入正确**/
            if (appiumUtil.doesElementsExist(Register.RG_BUTTON_SENDVERIFYCODE)) {

                /**点击获取验证码**/
                RegisterHelper.clickSendVerifyCode(appiumUtil, Register.RG_BUTTON_SENDVERIFYCODE);
                /**滑动验获取验证码**/
                //appiumUtil.swipe(720,1280,1240,1280);
                //driver.swipe(720,1280,1190,1280,2000);
                RegisterHelper.swipeRight(appiumUtil, driver);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                /**从数据库获取验证码**/
                GetSQLHelper getSQLHelper = new GetSQLHelper();
                String code = getSQLHelper.getCode();
                /**输入验证码**/
                RegisterHelper.typeVerifyCode(appiumUtil, Register.RG_INPUT_VERIFYCODE, code);
                /**输入密码**/
                RegisterHelper.typePassword(appiumUtil, Register.RG_INPUT_PASSWORD, password);

                /**判断是否需要填写推荐人代码**/
                if (!Objects.equals(recommendcode, "0")) {//0表示不需要填写
                    /** 点击推荐人代码**/
                    RegisterHelper.clickCommend(appiumUtil, Register.RG_BUTTON_RECOMMEND);
                    /**输入推荐人代码 **/
                    RegisterHelper.typeCommend(appiumUtil, Register.RG_INPUT_RECOMMEND, recommendcode);
                    /**点击推荐人代码的确认框**/
                    RegisterHelper.clickCommendConfirm(appiumUtil, Register.RG_BUTTON_RECOMMENDCONFIRM);
                }
                /**点击注册**/
                RegisterHelper.clickRegister(appiumUtil, Register.RG_BUTTON_REGISTER);

                /**判断点击注册后，我知道了按钮是否存在**/
                if (appiumUtil.doesElementsExist(Register.Rg_BUTTON_SUCCESSKNOW)) {
                    result[0] = GetSQLHelper.getRegisterResult(mobile);
                    if(result[0].equals("Pass")){
                        result[1] = "注册成功 ";
                    }else {
                        result[1] = "注册的手机号未在数据库中存在";
                    }
                } else { //注册失败
                    result[0] = "Fail";
                    result[1] = " 推荐人代码或者动态码或者密码格式错误";
                }
            } else if (appiumUtil.doesElementsExist(LogIn.LI_BUTTON_FORGET))     //判断是否为登录
            {
                result[0] = "Fail";
                result[1] = "手机号已被注册";
            } else {     //手机号格式错误
                result[0] = "Fail";
                result[1] = "手机号格式错误";
            }

        }
        return result;
    }
}
