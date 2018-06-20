package com.trjtest.test.pageshelper;

import com.trjtest.test.utils.AppiumUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/3/17.
 * 提现各个元素的方法
 */
public class WithdrawHelper {

    public static Logger logger =Logger.getLogger(WithdrawHelper.class);


    /** 点击提现**/
    public static void clickWithdraw(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("提现按钮存在，并点击");
        }else {
            logger.info("提现按钮不存在");
        }
    }

    /** 点击开户行**/
    public static void clickKaiHuHang(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if(flag){
            appiumUtil.click(byElement);
            logger.info("开户行输入框存在，并点击");
        }else {
            logger.info("开户行输入框不存在");
        }
    }

    /**点击选取开户城市 省**/
    public static void clickProvince(AppiumUtil appiumUtil ,By byElement) {
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("开户城市省输入框存在，并点击");
        } else {
            logger.info("开户城市省输入框存在，并点击");
        }
    }

        /**点击选取开户城市 市**/
        public static void clickCity(AppiumUtil appiumUtil,By byElement ){
            Boolean flag = appiumUtil.doesElementsExist(byElement);
            if (flag){
                appiumUtil.click(byElement);
                logger.info("开户城市市输入框存在，并点击");
            }else {
                logger.info("开户城市市输入框不存在");
            }
        }

        /**点击开户银行支行**/
        public static void clickBranch(AppiumUtil appiumUtil,By byElement){
            Boolean flag =appiumUtil.doesElementsExist(byElement);
            if (flag){
                appiumUtil.click(byElement);
                logger.info("开户行支行输入框存在，并点击");
            }else {
                logger.info("开户支行输入框不存在");
            }
        }

        /**点击提现金额输入框　**/
        public static  void clickCashOut(AppiumUtil appiumUtil ,By byElement){
            Boolean flag = appiumUtil.doesElementsExist(byElement);
            if (flag){
                appiumUtil.click(byElement);
                logger.info("提现金额的输入框存在，并点击");
            }else {
                logger.info("提现金额的输入框不存在");
            }
        }

        /**点击输入金额弹窗元素**/
        public static void sendCashout(AppiumUtil appiumUtil ,By byElement){
            appiumUtil.click(byElement);
        }

        /**点击免费提现**/
        public static void clickFreeWithdraw(AppiumUtil appiumUtil ,By byELement){
            Boolean flag = appiumUtil.doesElementsExist(byELement);
            if (flag){
                appiumUtil.click(byELement);
                logger.info("免费提现按钮存在，并点击");
            }else {
                logger.info("免费提现按钮不存在");
            }
        }

        /**点击提现 **/
        public static void clickPyWithdraw(AppiumUtil appiumUtil ,By  byElement){
            Boolean flag = appiumUtil.doesElementsExist(byElement);
            if (flag){
                appiumUtil.click(byElement);
                logger.info("提现按钮存在，并点击");
            }else {
                logger.info("提现按钮不存在");
            }
        }
        /**点击提现完成之后的确定按钮**/
        public static void clickWithdrawSuccess(AppiumUtil appiumUtil ,By byElement){
            Boolean flag = appiumUtil.doesElementsExist(byElement);
            if (flag){
                appiumUtil.click(byElement);
                logger.info("提现完成之后确定按钮存在，并点击");
            }else {
                logger.info(" 提现完成之后确定按钮不存在");
            }
        }

        /**点击没有实名的用户点击提现出现的弹窗中的立即去充值按钮**/
        public static void clickNoRealNameRechargeNow(AppiumUtil appiumUtil,By byElement){
            Boolean flag = appiumUtil.doesElementsExist(byElement);
            if (flag){
                appiumUtil.click(byElement);
                logger.info("没有实名用户点击提现后，立即去充值按钮存在，并点击");
            }else {
                logger.info("没有实名用户点击提现后，立即充值按钮不存在");
            }
        }

        /**点击没有实名的用户点击提现出现的弹窗中的取消按钮**/
        public static void clickNoRealNameCancel(AppiumUtil appiumUtil,By byElement){
            Boolean flag = appiumUtil.doesElementsExist(byElement);
            if (flag){
                appiumUtil.click(byElement);
                logger.info("没有实名用户点击提现后，取消按钮存在，并点击");
            }else {
                logger.info("没有实名用户点击提现后，取消按钮不存在");
            }
        }

    /** 点击提现页面的返回按钮**／
     *
     */
    public static void clickWithdrawBack (AppiumUtil appiumUtil,By byELement){
        Boolean flag = appiumUtil.doesElementsExist(byELement);
        if (flag){
            appiumUtil.click(byELement);
            logger.info("提现页面的返回按钮存在，并点击");
        }else {
            logger.info("提现页面的返回按钮不存在");
        }
    }

    /**存管用户输入提现金额**/
    public static void typeTubeCashoutAmount(AppiumUtil appiumUtil,By byElement,String cashoutamount){
        appiumUtil.typeContent(byElement,cashoutamount);
        logger.info("输入金额");
    }

    /**存管用户输入支付密码**/
    public static void typeTubePayPassword(AppiumUtil appiumUtil ,By byElement,String paypassword){
        appiumUtil.typeContent(byElement,paypassword);
        logger.info("输入支付密码");
    }

    /**点击存管用户的免费提现按钮**/
    public static void clickTubeFreeCashOut(AppiumUtil appiumUtil,By byElment){
        Boolean flag = appiumUtil.doesElementsExist(byElment);
        if (flag){
            appiumUtil.click(byElment);
            logger.info("存管用户提现中的免费提现按钮存在，并点击");
        }else {
            logger.info(" 存管用户提现中的免费提现按钮不存在");
        }
    }
    /**存管用户提现输入动态码**/
    public static void typeTubeVreifycode(AppiumUtil appiumUtil,By byElement,String verifycode){
        appiumUtil.typeContent(byElement,verifycode);
        logger.info(" 输入动态码");
    }
    /**点击存管提现页面中的确认提现按钮 **/
    public static void clickTubeConfirmWithdraw(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("存管用户提现页面中，确认提现按钮存在，并点击");
        }else {
            logger.info("存管用户提现页面中，确认提现按钮不存在");
        }
    }

    /**点击非存管用户输入支付密码的取消按钮**/
    public static void clickSendPayPasswordCancel(AppiumUtil appiumUtil ,By byELement){
        Boolean flag = appiumUtil.doesElementsExist(byELement);
        if (flag){
            appiumUtil.click(byELement);
            logger.info("非存管用户提现输入支付密码的页面中，取消按钮存在并点击");
        }else {
            logger.info("非存管用户提现输入支付密码的页面中，取消按钮不存在");
        }
    }
}
