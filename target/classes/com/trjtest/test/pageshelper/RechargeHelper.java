package com.trjtest.test.pageshelper;

import com.trjtest.test.pages.Recharge;
import com.trjtest.test.utils.AppiumUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/24.
 */
public class RechargeHelper {
    public static Logger logger = Logger.getLogger(Recharge.class);
    /**点击账户页面的额充值按钮**/
    public static void clickrecharge (AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag ){
            appiumUtil.click(byElement);
            logger.info("账户页面充值按钮存在，并点击");
        }else {
            logger.info("账户页面充值按钮不存在");
        }
    }

    /**输入存管充值的金额**/
    public static void typeRechargeMoney(AppiumUtil appiumUtil ,By byElement,String rechargeamount){
        Boolean flag  = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.typeContent(byElement,rechargeamount);
            logger.info("存管充值的金额输入框存在，并输入");
        }else {
            logger.info("存管充值的金额输入框不存在");
        }
    }

    /**输入存管充值的支付密码**/
    public static void typePayPassword(AppiumUtil appiumUtil,By byElement,String paypassword){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.typeContent(byElement,paypassword);
            logger.info("存管充值的支付密码输入框存在，并输入");
        }else {
            logger.info(" 存管充值的支付密码输入框不存在");
        }
    }

    /**点击存管充值页面的免费充值**/
    public static void clickFreeRecharge(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("存管充值页面的免费充值按钮存在，并点击");
        }else {
            logger.info("存管充值页面的免费充值按钮不存在");
        }
    }
    /**输入存管充值页面的动态码**/
    public static void typeverifycode(AppiumUtil appiumUtil,By byElement,String verifycode){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.typeContent(byElement, verifycode);
            logger.info("存管充值页面的动态码输入框存在，并点击");
        }else {
            logger.info("存管充值页面的动态码输入框不存在");
        }
    }
    /**点击确认充值按钮**/
    public static void clickRechargeConfirm(AppiumUtil appiumUtil ,By byElement){
        Boolean flag  = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("存管充值页面的确认充值按钮存在，并点击");
        }else {
            logger.info("存管充值页面的确认充值按钮不存在");
        }
    }

    /**点击充值成功的账户概况**/
    public static void clickPassAccountView(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("存管充值成功，成功后账户概况按钮存在并点击");
        }else {
            logger.info("账户概况不存在");
        }
    }

    /**点击充值失败的账户概况**/
    public static void clickFailAccountView(AppiumUtil appiumUtil,By byElement){
        Boolean flag  = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("存管充值失败，失败后账户概况存在，并点击");
        }else {
            logger.info("账户概况不存在");
        }
    }

    /**点击存管充值页面的返回按钮 **/
    public static void clickTubeRechargeBack(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("存管充值页面的返回键存在，并点击");
        }else{
            logger.info(" 存管充值页面的返回键不存在");
        }
    }
}
