package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.AccountCenter;
import com.trjtest.test.pages.Invest;
import com.trjtest.test.pages.Register;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.InvestHelper;
import com.trjtest.test.pageshelper.PayPasswordHelper;
import com.trjtest.test.pageshelper.RegisterHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

import java.util.List;

import static com.trjtest.test.pagesfunction.OpenAutoBidMethod.openAutoBid;

/**
 * Created by gjw on 17/3/16.
 * 投资的方法
 */
public class IvestMethod {
    public static Logger logger = Logger.getLogger(IvestMethod.class);

    /**
     * @param group          :投资列表页中的，铂系，盾系，赢系
     * @param productname    ：产品名称
     * @param investamount   ：投资的金额
     * @param paypassword    ：支付密码
     * @param productsubname :产品名称（在数据库存的名称）
     * @return
     */
    public static String[] investmethod(AppiumUtil appiumUtil, String group, String productname, String investamount, AppiumDriver driver,
                                        String paypassword, String mobile, String productsubname, String verifycode,String enviroment) {
        /**投资的结果**/
        String[] results = new String[2];

        char[] ch = paypassword.toCharArray();

        /**设置支付密码的结果**/
        String[] setpaypasswordresult = new String[2];

        /**判断是否开通存管银行**/
        int status = GetSQLHelper.getUserOpenOrNotTube(mobile);

        /** 判断产品是否是开标中**/
        int bid_status;


        /**判断用户是否设置支付密码  变量等于0表示没有设置支付密码**/
        int is_paypwd_mobile_set = GetSQLHelper.getUserSetOrNotPayPassword(mobile);

        /**产品是否为新客标**/
        int is_new = GetSQLHelper.getProductIsNew(productsubname);


        /**产品的起投金额**/
        float min_bid_aomunt;


        /**用户的可用余额**/
        float amount = Float.parseFloat(GetSQLHelper.getUserAmount(mobile)) / 100;

        /**产品的剩余额度**/
        int remaining_amount;


        /**用户是否为新客**/
        int is_newbie = GetSQLHelper.getUserIsNewBie(mobile);


        /**产品是否为集合标**/
        int is_collection = GetSQLHelper.getPrjIsCollection(productsubname);

        /**用户是否开通一键投资**/
        int is_auto = GetSQLHelper.getUserIsAutoBid(mobile);

        /**判断产品是否为集合标**/
        if (is_collection == 1) {
            min_bid_aomunt = Float.parseFloat(GetSQLHelper.getBidproductMinAmount(productsubname)) / 100;
            remaining_amount = Integer.parseInt(GetSQLHelper.getBidProductRemainingAmount(productsubname)) / 100;
            bid_status = GetSQLHelper.getBidProductStatus(productsubname);
        } else {
            min_bid_aomunt = Float.parseFloat(GetSQLHelper.getProductMinAmount(productsubname)) / 100;
            remaining_amount = Integer.parseInt(GetSQLHelper.getProductRemianingAmount(productsubname)) / 100;
            bid_status = GetSQLHelper.getProductStutas(productsubname);
        }

        /**匹配产品名字是否找到标签**/
        int result = 1;
        /**点击投资类型**/
        switch (group) {
            case "platinum":
                /**点击铂系**/
                InvestHelper.clickPlatinumGroup(appiumUtil, Invest.IV_BUTTON_PLATNINUMGROUP);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**上滑 去掉banner**/
                InvestHelper.swipeUpOtherGroup(appiumUtil, driver, 1000);
                break;
            case "shield":
                /**点击盾系**/
                InvestHelper.clickShieldGroup(appiumUtil, Invest.IV_BUTTON_SHIELDGROUP);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**上滑 去掉banner**/
                InvestHelper.swipeUpOtherGroup(appiumUtil, driver, 1000);
                break;
            case "all":
                /**点击全部**/
                InvestHelper.clickAll(appiumUtil, Invest.IV_BUTTON_ALL);
                break;
            case "win":
                /**点击赢系**/
                InvestHelper.clickWinGroup(appiumUtil, Invest.IV_BUTTON_WINGROUP);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**上滑 去掉banner**/
                InvestHelper.swipeUpOtherGroup(appiumUtil, driver, 1000);
                break;
        }
        /**匹配产品名字**/
        while (result == 1) {
            System.out.println(productname);
            System.out.println(InvestHelper.getProductName(appiumUtil, Invest.IV_BUTTON_PRODUCTNAME));
            if (productname.equals(InvestHelper.getProductName(appiumUtil, Invest.IV_BUTTON_PRODUCTNAME))) {

                result = 0;
                /** 点击产品**/
                InvestHelper.clickProduct(appiumUtil, Invest.IV_BUTTON_PRODUCT);
            } else {
                /**上滑产品列表**/
                InvestHelper.swipeUp(appiumUtil, driver, 1000);
            }
        }

        /** 产品为开标中**/
        if (bid_status == 2) {
            if (amount < min_bid_aomunt) {//用户余额不足
                if (driver.findElement(Invest.IV_BUTTON_INVESTNOW).getText().equals("立即充值")) {//产品详情页显示立即充值按钮
                    results[0] = "Pass";
                    results[1] = "用户余额不足，请充值";
                    /**点击产品详情页的返回按钮  **/
                    InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                    /**点击账户**/
                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                    logger.info(results[1]);
                } else {
                    results[0] = "Fail";
                    results[1] = "用户余额不足，但是没有显示立即充值";
                    /**点击产品详情页的返回按钮  **/
                    InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                    /**点击账户**/
                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                    logger.info(results[1]);
                }

            } else {//用户余额充足
                /** 用户投资的金额**/
                int wantamount = Integer.parseInt(investamount);
                if (wantamount > remaining_amount) {//用户输入的金额比产品剩余可投金额要大
                    String rm = Integer.toString(remaining_amount);
                    /**输入金额 **/
                    InvestHelper.clickInvestAmount(appiumUtil, Invest.IV_INPUT_INVESTAMOUNT);
                    InvestHelper.clearAmount(appiumUtil, Invest.IV_INPUT_INVESTAMOUNT);
                    char[] chars = rm.toCharArray();
                    for (int i = 0; i < rm.length(); i++) {
                        char payChar = chars[i];
                        switch (payChar) {
                            case '1':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT1);
                                break;
                            case '2':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT2);
                                break;
                            case '3':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT3);
                                break;
                            case '4':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT4);
                                break;
                            case '5':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT5);
                                break;
                            case '6':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT6);
                                break;
                            case '7':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT7);
                                break;
                            case '8':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT8);
                                break;
                            case '9':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT9);
                                break;
                            case '0':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT0);
                                break;
                        }
                    }
                    /**判断产品是否为集合标**/
                    if (is_collection == 1) {//产品为集合标
                        if (is_auto == 1) {//用户已开通一键投资
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                        } else {
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                            /**开通一键投资**/
                            String[] openResult = openAutoBid(appiumUtil, driver, verifycode, mobile,enviroment);
                            if (openResult[0].equals("Fail")) {
                                results = openResult;
                                return results;
                            }
                            for (int i = 0; i < rm.length(); i++) {
                                char payChar = chars[i];
                                switch (payChar) {
                                    case '1':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT1);
                                        break;
                                    case '2':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT2);
                                        break;
                                    case '3':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT3);
                                        break;
                                    case '4':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT4);
                                        break;
                                    case '5':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT5);
                                        break;
                                    case '6':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT6);
                                        break;
                                    case '7':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT7);
                                        break;
                                    case '8':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT8);
                                        break;
                                    case '9':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT9);
                                        break;
                                    case '0':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT0);
                                        break;
                                }
                            }
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                        }
                    } else {
                        /** 点击立即投资**/
                        InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                    }
                    /** 输入支付密码**/
                    for (int i = 0; i < paypassword.length(); i++) {

                        switch (ch[i]) {
                            case '1':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND1);
                                break;
                            case '2':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND2);
                                break;
                            case '3':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND3);
                                break;
                            case '4':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND4);
                                break;
                            case '5':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND5);
                                break;
                            case '6':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND6);
                                break;
                            case '7':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND7);
                                break;
                            case '8':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND8);
                                break;
                            case '9':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND9);
                                break;
                            case '0':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND0);
                                break;
                        }

                    }
                    if (appiumUtil.doesElementsExist(AccountCenter.AC_BUTTON_PAYPASSWORDSEND0)) {//如果输入支付密码的按键存在
                        results[0] = "Fail";
                        results[1] = "用户的支付密码错误";
                        logger.info(results[1]);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        /**点击支付密码的弹窗中的取消按钮**/
                        InvestHelper.clickInvestSendPayPasswordClose(appiumUtil, Invest.IV_BUTTON_INVESTCLOSE);
                        /**点击产品详情页的返回按钮  **/
                        InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                        /**点击账户**/
                        RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);

                    } else {//支付密码输入正确

                        if (status != 2) {//用户为非存管用户
                            /**点击查看账户**/
                            InvestHelper.clickViewAccount(appiumUtil, Invest.IV_BUTTON_VIEWACCOUNT);
                            results[0] = "Pass";
                            results[1] = "非存管用户投资成功并返回账户";
                            logger.info(results[1]);
                        } else {//用户为存管用户
                            /** 判断产品是否为一键投资产品**/
                            if (is_collection == 1) {
                                /**点击查看账户**/
                                InvestHelper.clickViewAccount(appiumUtil, Invest.IV_BUTTON_VIEWACCOUNT);
                                results[0] = "Pass";
                                results[1] = "存管用户投资成功";
                            } else {
                                /**在存管用户投资页面，输入验证码**/
                                InvestHelper.typeTubeVerifyCode(appiumUtil, Invest.IV_INPUT_VERIFYCODE, verifycode);
                                /**在存管用户投资页面，点击确认投资按钮**/
                                InvestHelper.clickTubeConfirmInvest(appiumUtil, Invest.IV_BUTTON_TUBECONFIRMINVEST);
                                /**判断短信验证码是否正确**/
                                if (appiumUtil.doesElementsExist(Invest.IV_BUTTON_TUBECONFIRMINVEST)) {
                                    results[0] = "Fail";
                                    results[1] = "存管用户投资短信验证码输入错误";
                                    logger.info(results[1]);
                                    /**点击存管投资的返回按钮**/
                                    InvestHelper.clickTubeTypeCodeBack(appiumUtil, Invest.IV_BUTTON_TUBETYPECODEBACK);
                                    /**点击产品详情页的返回按钮  **/
                                    InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                                    /**点击账户**/
                                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                                } else {
                                    /**点击查看账户**/
                                    InvestHelper.clickViewAccount(appiumUtil, Invest.IV_BUTTON_VIEWACCOUNT);
                                    results[0] = "Pass";
                                    results[1] = "存管用户投资成功并返回账户";
                                    logger.info(results[1]);
                                }
                            }
                        }
                    }

                } else if (wantamount < min_bid_aomunt) {//输入的金额小于起投金额
                    /**输入金额 **/
                    InvestHelper.clickInvestAmount(appiumUtil, Invest.IV_INPUT_INVESTAMOUNT);
                    InvestHelper.clearAmount(appiumUtil, Invest.IV_INPUT_INVESTAMOUNT);
                    char[] chars = investamount.toCharArray();
                    for (int i = 0; i < investamount.length(); i++) {
                        switch (chars[i]) {
                            case '1':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT1);
                                break;
                            case '2':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT2);
                                break;
                            case '3':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT3);
                                break;
                            case '4':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT4);
                                break;
                            case '5':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT5);
                                break;
                            case '6':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT6);
                                break;
                            case '7':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT7);
                                break;
                            case '8':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT8);
                                break;
                            case '9':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT9);
                                break;
                            case '0':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT0);
                                break;
                        }
                    }

                    /**判断产品是否为集合标**/
                    if (is_collection == 1) {//产品为集合标
                        if (is_auto == 1) {//用户已开通一键投资
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                        } else {
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                            /**开通一键投资**/
                            String[] openBidResult = openAutoBid(appiumUtil, driver, verifycode, mobile,enviroment);
                            if (openBidResult.equals("Fail")) {
                                results = openBidResult;
                                return results;
                            }

                            InvestHelper.clearAmount(appiumUtil, Invest.IV_INPUT_INVESTAMOUNT);
                            for (int i = 0; i < investamount.length(); i++) {
                                switch (chars[i]) {
                                    case '1':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT1);
                                        break;
                                    case '2':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT2);
                                        break;
                                    case '3':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT3);
                                        break;
                                    case '4':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT4);
                                        break;
                                    case '5':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT5);
                                        break;
                                    case '6':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT6);
                                        break;
                                    case '7':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT7);
                                        break;
                                    case '8':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT8);
                                        break;
                                    case '9':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT9);
                                        break;
                                    case '0':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT0);
                                        break;
                                }
                            }
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);

                        }
                    } else {
                        /** 点击立即投资**/
                        InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                    }
                    /**判断立即投资按钮是否存在**/
                    if (appiumUtil.doesElementsExist(Invest.IV_BUTTON_INVESTNOW)) {
                        results[0] = "Pass";
                        results[1] = "输入的金额小于起投金额，请重新输入";
                        /**点击产品详情页的返回按钮  **/
                        InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                        /**点击账户**/
                        RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                        logger.info(results[1]);
                    } else {
                        results[0] = "Fail";
                        results[1] = "输入的金额小于起投金额，但是仍能执行";
                        /**点击支付密码的弹窗中的取消按钮**/
                        InvestHelper.clickInvestSendPayPasswordClose(appiumUtil, Invest.IV_BUTTON_INVESTCLOSE);
                        /**点击产品详情页的返回按钮  **/
                        InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                        /**点击账户**/
                        RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                        logger.info(results[1]);
                    }

                } else {//用户输入的金额小于产品的剩余金额
                    /**输入金额 **/
                    InvestHelper.clickInvestAmount(appiumUtil, Invest.IV_INPUT_INVESTAMOUNT);
                    InvestHelper.clearAmount(appiumUtil, Invest.IV_INPUT_INVESTAMOUNT);
                    char[] chars = investamount.toCharArray();
                    for (int i = 0; i < investamount.length(); i++) {
                        switch (chars[i]) {
                            case '1':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT1);
                                break;
                            case '2':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT2);
                                break;
                            case '3':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT3);
                                break;
                            case '4':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT4);
                                break;
                            case '5':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT5);
                                break;
                            case '6':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT6);
                                break;
                            case '7':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT7);
                                break;
                            case '8':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT8);
                                break;
                            case '9':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT9);
                                break;
                            case '0':
                                InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT0);
                                break;
                        }
                    }
                    /**判断产品是否为集合标**/
                    if (is_collection == 1) {//产品为集合标
                        if (is_auto == 1) {//用户已开通一键投资
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                        } else {
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                            /**开通一键投资**/
                            String[] openresult = OpenAutoBidMethod.openAutoBid(appiumUtil, driver, verifycode, mobile,enviroment);
                            if (openresult[0].equals("Fail")) {
                                results = openresult;
                                return results;
                            }
                            InvestHelper.clearAmount(appiumUtil, Invest.IV_INPUT_INVESTAMOUNT);
                            for (int i = 0; i < investamount.length(); i++) {
                                switch (chars[i]) {
                                    case '1':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT1);
                                        break;
                                    case '2':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT2);
                                        break;
                                    case '3':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT3);
                                        break;
                                    case '4':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT4);
                                        break;
                                    case '5':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT5);
                                        break;
                                    case '6':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT6);
                                        break;
                                    case '7':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT7);
                                        break;
                                    case '8':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT8);
                                        break;
                                    case '9':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT9);
                                        break;
                                    case '0':
                                        InvestHelper.clickPayPassword(appiumUtil, Invest.IV_BUTTON_SENDAMOUNT0);
                                        break;
                                }
                            }
                            /** 点击立即投资**/
                            InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                        }
                    } else {
                        /** 点击立即投资**/
                        InvestHelper.clickInvestNow(appiumUtil, Invest.IV_BUTTON_INVESTNOW);
                    }
                    /** 输入支付密码**/
                    for (int i = 0; i < paypassword.length(); i++) {

                        switch (ch[i]) {
                            case '1':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND1);
                                break;
                            case '2':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND2);
                                break;
                            case '3':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND3);
                                break;
                            case '4':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND4);
                                break;
                            case '5':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND5);
                                break;
                            case '6':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND6);
                                break;
                            case '7':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND7);
                                break;
                            case '8':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND8);
                                break;
                            case '9':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND9);
                                break;
                            case '0':
                                PayPasswordHelper.sendPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND0);
                                break;
                        }

                    }
                    if (appiumUtil.doesElementsExist(AccountCenter.AC_BUTTON_PAYPASSWORDSEND0)) {//如果输入支付密码的按键存在
                        results[0] = "Fail";
                        results[1] = "用户的支付密码错误";
                        logger.info(results[1]);
                        /**点击支付密码的弹窗中的取消按钮**/
                        InvestHelper.clickInvestSendPayPasswordClose(appiumUtil, Invest.IV_BUTTON_INVESTCLOSE);

                        /**点击产品详情页的返回按钮  **/
                        InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                        /**点击账户**/
                        RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                    } else {//支付密码输入正确
                        if (status != 2) {//用户为非存管用户
                            /**点击查看账户**/
                            InvestHelper.clickViewAccount(appiumUtil, Invest.IV_BUTTON_VIEWACCOUNT);
                            results[0] = "Pass";
                            results[1] = "非存管用户投资成功并返回账户";
                            logger.info(results[1]);
                        } else {//用户为存管用户
                            /** 判断产品是否为一键投资产品**/
                            if (is_collection == 1) {
                                /**点击查看账户**/
                                InvestHelper.clickViewAccount(appiumUtil, Invest.IV_BUTTON_VIEWACCOUNT);
                                results[0] = "Pass";
                                results[1] = "存管用户投资成功";
                            } else {
                                /**在存管用户投资页面，输入验证码**/
                                InvestHelper.typeTubeVerifyCode(appiumUtil, Invest.IV_INPUT_VERIFYCODE, verifycode);
                                /**在存管用户投资页面，点击确认投资按钮**/
                                InvestHelper.clickTubeConfirmInvest(appiumUtil, Invest.IV_BUTTON_TUBECONFIRMINVEST);
                                /**判断短信验证码是否正确**/
                                if (appiumUtil.doesElementsExist(Invest.IV_BUTTON_TUBECONFIRMINVEST)) {
                                    results[0] = "Fail";
                                    results[1] = "存管用户投资短信验证码输入错误";
                                    logger.info(results[1]);
                                    /**点击存管投资的返回按钮**/
                                    InvestHelper.clickTubeTypeCodeBack(appiumUtil, Invest.IV_BUTTON_TUBETYPECODEBACK);
                                    /**点击产品详情页的返回按钮  **/
                                    InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                                    /**点击账户**/
                                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                                } else {
                                    /**点击查看账户**/
                                    InvestHelper.clickViewAccount(appiumUtil, Invest.IV_BUTTON_VIEWACCOUNT);
                                    results[0] = "Pass";
                                    results[1] = "存管用户投资成功并返回账户";
                                    logger.info(results[1]);
                                }
                            }
                        }
                    }
                }
            }
        } else if (bid_status == 1) {//产品为待开标
            logger.info("该产品的状态为待开标，不能投资");
            /**判断待开标的产品，剩余多少时间是否显示**/
            if (appiumUtil.doesElementsExist(Invest.IV_BUTTON_DKBREMAININGTIME)) {
                results[0] = "Pass";
                results[1] = "产品为待开标不能投资";
                logger.info(results[1]);
                /**点击返回**/
                InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                /**点击账户**/
                RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);

            } else {
                results[0] = "Fail";
                results[1] = "产品为待开标，但是仍能进行操作，可能由于改了服务器时间有关";
                logger.info(results[1]);
                /**点击返回**/
                InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                /**点击账户**/
                RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
            }
        } else {//产品状态为已满标或者其他状态
            /**用户是否购买产品的标志，0表示未购买，1表示已购买；**/
            int userbuyproduct = 0;
            /**获取用户uid**/
            int uid = GetSQLHelper.getUID(mobile);
            /**获取产品ID **/
            int id = GetSQLHelper.getProductId(productsubname);
            /**获得购买该产品的用户**/
            List uids = GetSQLHelper.getProdctUser(id);
            for (int i = 0; i < uids.size(); i++) {
                if (uids.get(i).equals(Integer.toString(uid))) {
                    userbuyproduct = 1;
                }
            }
            if (userbuyproduct == 1) {//表示用户是否已买该产品
                if (appiumUtil.doesElementsExist(Invest.IV_BUTTON_YMBGRAYBUTTON)) {
                    results[0] = "Pass";
                    results[1] = "用户已经购买过该产品，产品详情中显示已满标信息";
                    logger.info(results[1]);
                    /**点击详情页的返回按钮**/
                    InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                    /**点击账户**/
                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                } else {
                    results[0] = "Fail";
                    results[1] = "已满标产品，详情页显示错误";
                    logger.info(results[1]);
                    /**点击详情页的返回按钮**/
                    InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                    /**点击账户**/
                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                }

            } else {//用户没有买该产品
                if (appiumUtil.doesElementsExist(Invest.IV_BUTTON_SEEOTHERS)) {
                    results[0] = "Pass";
                    results[1] = "用户没有购买过该产品，产品详情中显示去别处看看";
                    logger.info(results[1]);
                    /** 点击返回页的返回按钮**/
                    InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                    /**点击账户**/
                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                } else {
                    results[0] = "Fail";
                    results[1] = "已满标产品，详情页显示错误";
                    logger.info(results[1]);
                    /** 点击返回页的返回按钮**/
                    InvestHelper.clickProductDetailsBack(appiumUtil, Invest.IV_BUTTON_PRODUCTDETAILSBACK);
                    /**点击账户**/
                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                }
            }
        }
        return results;
    }
}
