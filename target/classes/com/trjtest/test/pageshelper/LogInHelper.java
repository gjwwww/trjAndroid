package com.trjtest.test.pageshelper;


import com.trjtest.test.utils.AppiumUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/2/27.
 */
public class LogInHelper{

    public static Logger logger = Logger.getLogger(LogInHelper.class);


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

    /** 输入密码**/
    public static void typePassword(AppiumUtil appiumUtil,By byElement,String password){
        appiumUtil.typeContent(byElement,password);
    }

    /** 点击登录**/
    public static void clickLogin(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("登录按钮存在，并点击");
        }else{
            logger.info("登录按钮不存在");
            return;
        }
    }

    /**设置手势密码点击下次再说**/
    public static void clickPatternLockNet(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("手势密码的下次再说按钮存在，并点击");
        }else{
            logger.info("手势密码的下次再说按钮不存在");
            return;
        }
    }

    /**设置手势密码点击立即设置**/
    public static void clickPatternLockIm(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info ("手势密码的立即设置按钮存在，并点击");
        }else{
            logger.info("手势密码的立即设置按钮不存在");
            return;
        }
    }
    /**判断是否成功登录
     * 以账户页面的充值按钮是否存在为依据**/
    public static Boolean rechargeDoesExist(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        return flag;
    }

    /**用户名密码错误
     * 判断登录按钮是否仍然存在
     */
    public static Boolean logInDoesExist(AppiumUtil appiumUtil ,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        return flag;
    }

    /**用户手机号错误
     * 判断点击下一不之后，下一步按钮是否仍然存在
     */
    public static Boolean nextDoseExist(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        return flag;
    }

    /** 点击账户中的头像按钮**/
    public static void clickAccountCenter(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("账户中的头像按钮存在，并点击");
        }else {
            logger.info("账户中的头像按钮不存在");
            return;
        }
    }

    /** 点击个人中心的设置按钮**/
    public static void clickSetting(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("个人中心中的设置按钮存在，并点击");
        }else {
            logger.info("个人中心中的设置按钮不存在");
            return;
        }
    }

    /**点击设置中的退出按钮**/
    public static void clickExit(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("设置中的退出按钮存在，并点击");
        }else {
            logger.info("设置中的退出按钮不存在");
            return;
        }
    }

    /**清空输入手机号的内容**/
    public static void clearMobile(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.clear(byElement);
            logger.info ("清空手机号");
        }else {
            logger.info(" 手机号元素不存在");
            return;
        }
    }

    /**获取输入手机号的内容**/
    public static String getMobile(AppiumUtil appiumUtil,By byElement){
        String mobile = appiumUtil.findElement(byElement).getText();
        return mobile;
    }
    /**判断输入密码的元素是否存在**/
    public static Boolean passwordDoseexist(AppiumUtil appiumUtil, By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        return flag;
    }

    /**点击返回按钮**/
    public static void clickBack(AppiumUtil appiumUtil,By byElementy){
        Boolean flag =appiumUtil.doesElementsExist(byElementy);
        if(flag){
            appiumUtil.click(byElementy);
            logger.info("返回按钮存在，并点击");

        }else {
            logger.info("返回按钮不存在");
        }
    }
    /**判断忘记密码按钮受否存在**/
    public static Boolean forgetDoseExist(AppiumUtil appiumUtil,By byElement){
        Boolean flag =appiumUtil.doesElementsExist(byElement);
        return flag;
    }

}
