package com.trjtest.test.pageshelper;

import com.trjtest.test.pages.Invest;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/16.
 * 投资各个元素操作方法
 */
public class InvestHelper {

    public static Logger logger =Logger.getLogger(InvestHelper.class);

    /**点击投资按钮**/
    public static void clickInvest(AppiumUtil appiumUtil, By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("投资按钮存在，并点击");
        }else {
            logger.info("投资按钮不存在");
        }
    }

    /** 点击投资页面的全部按钮**/
    public static void clickAll(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("全部按钮存在，并点击");
        }else {
            logger.info("全部按钮不存在 ");
        }
    }

     /**点击铂系按钮 **/
     public static void clickPlatinumGroup(AppiumUtil appiumUtil,By byElement){
         Boolean flag = appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info("铂系按钮存在，并点击");
         } else{
             logger.info("铂系按钮不存在");
         }
     }

     /** 点击盾系**/
     public static void clickShieldGroup(AppiumUtil appiumUtil ,By byElement){
         Boolean flag = appiumUtil.doesElementsExist(byElement);
         if (flag ){
             appiumUtil.click(byElement);
             logger.info("盾系按钮存在，并点击");
         }else {
             logger.info ("盾系按钮不存在");
         }
     }

     /**点击赢系**/
     public static void clickWinGroup(AppiumUtil appiumUtil ,By byElement){
         Boolean flag  =appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info("赢系按钮存在,并点击");
         }else {
             logger.info("赢系按钮不存在");
         }
     }

     /**获取产品名称**/
     public static String getProductName(AppiumUtil appiumUtil ,By byElement){
         String productname = null;
         Boolean flag = appiumUtil.doesElementsExist(byElement);
         if(flag){
             productname = appiumUtil.findElement(byElement).getText();
         }
         return  productname;
     }

     /** 点击产品**/
     public static void clickProduct(AppiumUtil appiumUtil,By byElement){
         Boolean flag = appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info("点击产品");
         }

     }
     /** 输入投资金额**/
     public static void clickInvestAmount(AppiumUtil appiumUtil ,By byElement ){
         appiumUtil.click( Invest.IV_INPUT_INVESTAMOUNT);

     }
     /**点击立即投资**/
     public static void clickInvestNow(AppiumUtil appiumUtil,By byElement){
         Boolean flag = appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info ("立即投资按钮存在，并点击");
         }else {
             logger.info("立即投资按钮不存在");
         }
     }
     /** 输入支付密码**/
     public static void clickPayPassword(AppiumUtil appiumUtil ,By byElement){
         appiumUtil.click(byElement);
     }
     /** 点击查看账户**/
     public static void clickViewAccount(AppiumUtil appiumUtil,By byElement){
         Boolean flag = appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info("查看账户按钮存在，并点击 ");
         }else {
             logger.info("查看账户按钮不存在");
         }
     }
     /**  上滑全部产品列表**/
     public static void swipeUp(AppiumUtil appiumUtil,AppiumDriver driver,int during ){
         appiumUtil.swipeToUp(driver,during);
     }

     /**其他三系的产品列表**/
     public static void swipeUpOtherGroup(AppiumUtil appiumUtil,AppiumDriver driver ,int during){
         appiumUtil.swipeToUpOther(driver,during);
     }
     /**判断输入金额框中的金额是否为0**/
     public static void clearAmount(AppiumUtil appiumUtil,By byElement){
         while(!(appiumUtil.findElement(byElement).getText().equals("0"))){
             appiumUtil.click(Invest.IV_BUTTON_SENDAMOUNTX);
         }
     }

     /**点击已满标等状态的去别处看看**/
     public static void clickYMBViewOthers(AppiumUtil appiumUtil ,By byElement){
         Boolean flag= appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info("去别处看看按钮存在，并点击");
         }else{
             logger.info("去别处看看按钮不存在");
         }
     }
     /**点击产品详情页的返回键**/
     public static void clickProductDetailsBack(AppiumUtil appiumUtil ,By byElement){
         Boolean flag = appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info("产品详情页的返回键存在，并点击");
         }else {
             logger.info(" 产品详情页的返回按钮不存在");
         }
     }

     /**点击投资输入密码弹窗的取消按钮**/
     public static void clickInvestSendPayPasswordClose(AppiumUtil appiumUtil,By byElement){
         Boolean flag = appiumUtil .doesElementsExist(byElement);
         if (flag) {
             appiumUtil.click(byElement);
             logger.info("投资输入密码弹窗的取消按钮存在，并点击");
         }else {
             logger.info("投资输入密码弹窗的曲线按钮不存在");
         }
     }

     /**输入存管用户投资的短息验证码 **/
     public static void typeTubeVerifyCode(AppiumUtil appiumUtil ,By byelement,String verifycode){
         appiumUtil.typeContent(byelement,verifycode);
         logger.info("输入验证码");
     }

     /**存管投资页面，点击确认投资**/
     public static void clickTubeConfirmInvest(AppiumUtil appiumUtil,By byElement){
         Boolean flag =appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info("存管投资页面确认投资按钮存在，并点击");
         }else{
             logger.info("存管投资页面，确认投资按钮不存在");
         }
     }

     /**存管投资输入验证码页面，点击返回按钮**/
     public static void clickTubeTypeCodeBack(AppiumUtil appiumUtil,By byElement){
         Boolean flag = appiumUtil.doesElementsExist(byElement);
         if (flag){
             appiumUtil.click(byElement);
             logger.info("存管投资输入动态吗页面返回按钮存在，并点击");
         }else {
             logger.info("存管投资输入动态码页面返回按钮不存在");
         }
     }
}
