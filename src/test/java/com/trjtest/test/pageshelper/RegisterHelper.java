package com.trjtest.test.pageshelper;

import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/6.
 */
public class RegisterHelper {

    public static Logger logger = Logger.getLogger(RegisterHelper.class);

    /**点击账户**/
    public static void clickAccount (AppiumUtil appiumUtil, By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("账户元素存在，并点击");
        }else {
            logger.info("账户元素不存在");
            return;
        }
    }

    /**输入手机号**/
    public static void typeMoblie (AppiumUtil appiumUtil, By byElement,String mobile){
        appiumUtil.typeContent(byElement,mobile);
    }

    /**点击下一步**/
    public static void clickNext(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("下一步按钮存在，并点击");
        }else{
            logger.info("下一步按钮不存在");
            return;
        }
    }

    /**点击发送验证码**/
    public static void clickSendVerifyCode (AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("发送验证码按钮存在，并点击");
        }else{
            logger.info("发送验证码按钮不存在");
        }
    }

    /**输入验证码**/
    public static void typeVerifyCode (AppiumUtil appiumUtil,By byElement,String verifyCode){
        appiumUtil.typeContent(byElement,verifyCode);
    }

    /**输入登录密码**/
    public static void typePassword(AppiumUtil appiumUtil,By byElement,String password){
        appiumUtil.typeContent(byElement,password);
    }

    /**点击注册按钮**/
    public static  void clickRegister(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("注册按钮存在，并点击");
        }else{
            logger.info("注册按钮不存在");
        }
    }

    /**用户手机号错误
     * 判断点击下一不之后，下一步按钮是否仍然存在
     */
    public static Boolean nextDoseExist(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        return flag;
    }

    /** 用户手机号已被注册
     * 输入手机号后直接跳到登录页，判断登录按钮是否存在
     */
    public static Boolean loginDoseExist(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        return flag;
    }

    /**判断注册页面，注册按钮是否存在**/
    public static Boolean registerDoseExist(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        return flag;
    }

    /**滑动获取验证码**/
    public static void swipeRight(AppiumUtil appiumUtil, AppiumDriver driver){
        logger.info("正在滑动获取验证码");

        appiumUtil.swipeToRight(driver,1000);

    }

    /**点击注册完成页面之后的我知道了按钮**/
    public static void clickSuccessKnow(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("我知道了按钮存在，并点击");
        }else {
            logger.info("我知道了按钮不存在");
        }
    }

    /**点击银行存管弹窗中的暂不开启**
     */
    public static void clickNegativePository(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("暂不开通按钮存在，并点击");
        }else {
            logger.info("暂不开通按钮不存在");
        }
    }

    /**点击暂不开通之后的我知道了按钮**/
    public static void clickConfirmKnow(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("暂不开通存管的确认框中我知道了按钮存在，并点击");
        }else {
            logger.info("暂不开通存管的确认框中我知道了按钮不存在");
        }
    }

    /**点击推荐人代码**/
    public static void clickCommend (AppiumUtil appiumUtil ,By byElement){
        Boolean flag  =appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("推荐人代码存在，并点击");
        }else {
            logger.info("推荐人代码不存在");
        }
    }

    /**输入推荐人代码**/
    public static void typeCommend(AppiumUtil appiumUtil ,By byElement,String commendcode){
        appiumUtil.typeContent(byElement,commendcode);

    }

    /**点击推荐人代码的确认按钮**/
    public static void clickCommendConfirm(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("推荐人代码确认框存在，并点击");
        }else {
            logger.info(" 推荐人代码确认框不存在");
        }
    }
    /**点击二次登录页的立即注册按钮 **/
    public static void clickRegisterNow(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("立即注册按钮存在，并点击");
        }else{
            logger.info("立即注册按钮不存在");
        }
    }

    /**点击立即注册后，点击删除手机号**/
    public static void clickDelete(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("点击立即注册后，删除手机号按钮存在，并点击");
        } else{
            logger.info("点击立即注册后，删除手机号按钮不存在");
        }
    }
}
