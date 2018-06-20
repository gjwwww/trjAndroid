package com.trjtest.test.pageshelper;

import com.trjtest.test.utils.AppiumUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/15.
 * 支付密码的元素操作方法
 */
public class PayPasswordHelper {
    public static Logger logger =Logger.getLogger(PayPasswordHelper.class);

    /** 点击头像元素 **/
    public static void clickAccountCenter(AppiumUtil appiumUtil, By byElement){
        Boolean flag  = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("头像存在并点击");
        }else {
            logger.info("头像元素不存在");
        }
    }

    /**点击密码管理**/
    public static void clickPasswordManage(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("密码管理按钮存在，并点击");
        }else {
            logger.info("密码故哪里按钮不存在");
        }
    }

    /**点击支付密码**/
    public static  void clickPayPassword(AppiumUtil appiumUtil, By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("支付密码按钮存在，并点击");
        }else {
            logger.info ("支付密码按钮不存在");
        }
    }

    /**输入支付密码**/
    public static void sendPayPassword(AppiumUtil appiumUtil ,By byElement){
        appiumUtil.click(byElement);
    }
    /**点击确定**/
    public static void clickPayPasswordSubmit(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("确定按钮存在，并点击");
        }else {
            logger.info("确定按钮不存在");
        }
    }
    /**再次输入支付密码**/
    public static void sendPayPasswordConfirm(AppiumUtil appiumUtil,By byElement){
        appiumUtil.click(byElement);
    }

    /**点击确认支付密码的确定按钮**/
    public static void clickPayPasswordSubmitConfirm(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag) {
            appiumUtil.click(byElement);
            logger.info("确认按钮存在，并点击");
        }else {
            logger.info("确认按钮不存在");
        }
    }

    /**点击密码管理中的返回按钮 **/
    public static void  clickBack(AppiumUtil appiumUtil ,By  byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info(" 密码管理中的返回按钮存在，并点击");
        }else {
            logger.info("返回按钮不存在");
        }
    }
    /** 点击个人中心的返回按钮**/
    public static void  clickAccountCenterBack(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("个人中心的返回按钮存在，并点击");
        }else {
            logger.info("个人中心中的返回按钮不存在");
        }
    }
    public static void clickBackToPasswordManage(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("设置支付密码的返回键存在，并点击");
        }else {
            logger.info("设置支付密码的的返回键不存在");
        }
    }
}
