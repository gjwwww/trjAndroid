package com.trjtest.test.testcases.Invest;

import com.trjtest.test.base.BasePrepare;
import com.trjtest.test.pages.Invest;
import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pagesfunction.*;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.InvestHelper;
import com.trjtest.test.pageshelper.LogInHelper;
import com.trjtest.test.utils.ExcelDataProvider;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by gjw on 17/3/16.
 */
public class Invest_001_investTest extends BasePrepare {
    public static Logger logger = Logger.getLogger(Invest_001_investTest.class);
    public static int rownumber = 1;

    @Test
    public void initLogin() {
        /**滑动引导页，并点击先体验一下**/
        InitMethod.initMethod(appiumUtil, driver);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"initLogin"})
    public void invest(ITestContext context) {
        /**修改测试数据**/
        InputExcelResult.inputModifyMobile("Invest", "001", rownumber);
        /**数据提供**/
        List<Map<String, String>> dataList = new ArrayList<>();

        ExcelDataProvider excelDataProvider = new ExcelDataProvider("Invest", "001");
        Map<String, String> data = null;

        data = (Map<String, String>) excelDataProvider.next2(rownumber)[0];
        dataList.add(data);

        /**用例执行的实际情况**/
        String actual = null;
        String[] investresult = new String[2];
        String lrresult = null;
        try {

            /**设置数据库参数**/
            BaseFunction.setSql(data.get("enviroment"));

            /**点击账户**/
            LogInHelper.clickAccount(appiumUtil, LogIn.LI_BUTTON_ACCOUNT);
            /**调用登录或者注册方法,得到结果**/
            lrresult = LogInOrRegisterMethod.loInOrRegisterMsthod(appiumUtil, data.get("MOBILE"), data.get("PASSWORD"), data.get("RECOMMEND"), driver);
            if (lrresult.equals("Pass")) {

                /**判断是否需要开通存管
                 * 0表示不需要开通
                 * 1表示需要开通
                 */
                if (data.get("OPENTUBE").equals("0")) {

                    /**判断是否需要充值
                     * 0表示不需要充值
                     * 1表示要充值
                     */
                    if (data.get("RECHARGEORNOT").equals("0")) {
                        /**判断用户是否设置支付密码  变量等于0表示没有设置支付密码**/
                        int is_paypwd_mobile_set = GetSQLHelper.getUserSetOrNotPayPassword(data.get("MOBILE"));
                        if (is_paypwd_mobile_set == 0) {
                            logger.info("用户没有设置支付密码，进行设置支付密码");
                            String[] setpaypasswordresult = SetPayPassWordMethod.setPayPassword(appiumUtil, data.get("PAYPASSWORD"), data.get("PAYPASSWORDCONFIRM"));

                            if (setpaypasswordresult.equals("Pass")) {
                                logger.info(setpaypasswordresult[1]);
                                /**判断用户是否已经实名**/
                                String realn = GetSQLHelper.getRralName(data.get("MOBILE"));
                                if (realn == null) {
                                    logger.info("用户没有实名，进行实名");
                                    /**实名**/
                                    GetSQLHelper.updateRealName(data.get("REALNAME"), data.get("MOBILE"), data.get("PERSONID"));
                                    /**绑定银行卡**/
                                    GetSQLHelper.bindingBankCard(data.get("MOBILE"), data.get("ACCOUNTNO"), data.get("REALNAME"), data.get("PERSONID"));

                                    /**绑定提现银行卡**/
                                    GetSQLHelper.withdrawBankcard(data.get("MOBILE"), data.get("ACCOUNTNO"), data.get("REALNAME"), data.get("PERSONID"));
                                }
                                /**点击投资按钮**/
                                InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                /**调用投资方法**/
                                investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                        data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));

                            } else {
                                logger.info(setpaypasswordresult);
                                investresult = setpaypasswordresult;
                            }
                        } else {//用户已经设置过支付密码
                            logger.info("用户已经设置过支付密码");
                            /**点击投资按钮**/
                            InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                            /**调用投资方法**/
                            investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                    data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));
                        }
                    } else {

                        /**判断是否开通存管银行
                         * 2表示已经开通存管**/
                        int status = GetSQLHelper.getUserOpenOrNotTube(data.get("MOBILE"));
                        if (status != 2) {//未开通存管用户充值
                            /** 充值**/
                            GetSQLHelper.recharge(data.get("AMOUNT"), data.get("MOBILE"));
                            /**判断用户是否设置支付密码  变量等于0表示没有设置支付密码**/
                            int is_paypwd_mobile_set = GetSQLHelper.getUserSetOrNotPayPassword(data.get("MOBILE"));
                            if (is_paypwd_mobile_set == 0) {
                                logger.info("用户没有设置支付密码，进行设置支付密码");
                                String[] setpaypasswordresult = SetPayPassWordMethod.setPayPassword(appiumUtil, data.get("PAYPASSWORD"), data.get("PAYPASSWORDCONFIRM"));

                                if (setpaypasswordresult[0].equals("Pass")) {
                                    logger.info(setpaypasswordresult[1]);
                                    /**判断用户是否已经实名**/
                                    String realn = GetSQLHelper.getRralName(data.get("MOBILE"));
                                    if (realn == null) {
                                        /**实名**/
                                        GetSQLHelper.updateRealName(data.get("REALNAME"), data.get("MOBILE"), data.get("PERSONID"));
                                        /**绑定银行卡**/
                                        GetSQLHelper.bindingBankCard(data.get("MOBILE"), data.get("ACCOUNTNO"), data.get("REALNAME"), data.get("PERSONID"));

                                        /**绑定提现银行卡**/
                                        GetSQLHelper.withdrawBankcard(data.get("MOBILE"), data.get("ACCOUNTNO"), data.get("REALNAME"), data.get("PERSONID"));
                                    }
                                    /**点击投资按钮**/
                                    InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                    /**调用投资方法**/
                                    investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                            data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));

                                } else {
                                    logger.info(setpaypasswordresult);
                                    investresult = setpaypasswordresult;
                                }
                            } else {//用户已经设置过支付密码

                                /**判断用户是否已经实名**/
                                String realn = GetSQLHelper.getRralName(data.get("MOBILE"));
                                if (realn == null) {
                                    /**实名**/
                                    GetSQLHelper.updateRealName(data.get("REALNAME"), data.get("MOBILE"), data.get("PERSONID"));
                                    /**绑定银行卡**/
                                    GetSQLHelper.bindingBankCard(data.get("MOBILE"), data.get("ACCOUNTNO"), data.get("REALNAME"), data.get("PERSONID"));

                                    /**绑定提现银行卡**/
                                    GetSQLHelper.withdrawBankcard(data.get("MOBILE"), data.get("ACCOUNTNO"), data.get("REALNAME"), data.get("PERSONID"));
                                }
                                logger.info("用户已经设置过支付密码");
                                /**点击投资按钮**/
                                InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                /**调用投资方法**/
                                investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                        data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));
                            }
                        } else {//已开通存管用户充值

                            /** 存管用户充值**/
                            String[] tuberechargeresult = TubeRechargeMethod.tubeRechargeMethod(appiumUtil, data.get("MOBILE"), driver, data.get("PAYPASSWORD"), data.get("PAYPASSWORDCONFIRM"), data.get("BANKOPTION"), data.get("REALNAME"), data.get("PERSONID"), data.get("PROVINCEOPTION"),
                                    data.get("CITYOPTION"), data.get("KAIHUHANGOPTION"), data.get("ACCOUNTNO"), data.get("VERIFYCODE"), data.get("RECHARGEMONEY"));

                            if (tuberechargeresult[0].equals("Fail")) {//存管用户充值失败
                                logger.info(tuberechargeresult[1]);
                                investresult = tuberechargeresult;
                            } else {//存管用户充值成功
                                /**点击投资按钮**/
                                InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                /**调用投资方法**/
                                investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                        data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));
                            }
                        }
                    }


                } else {//需要开通存管
                    /**判断是否开通存管银行
                     * 2表示已经开通存管**/
                    int status = GetSQLHelper.getUserOpenOrNotTube(data.get("MOBILE"));
                    if (status != 2) {//未开通存管用户充值
                        /**开通存管**/
                        String[] opentuberesult = OpenTubeMethod.openTubeMethod(appiumUtil, driver, data.get("MOBILE"), data.get("BANKOPTION"), data.get("REALNAME"), data.get("PERSONID"), data.get("PROVINCEOPTION"),
                                data.get("CITYOPTION"), data.get("KAIHUHANGOPTION"), data.get("ACCOUNTNO"), data.get("VERIFYCODE"));
                        if (opentuberesult[0].equals("Fail")) {//存管开通失败
                            logger.info(opentuberesult[1]);
                            investresult = opentuberesult;
                        } else {//存管开通成功

                            /**判断是否需要充值
                             * 0表示不需要充值
                             * 1表示要充值
                             */
                            if (data.get("RECHARGEORNOT").equals("0")) {
                                /**判断用户是否设置支付密码  变量等于0表示没有设置支付密码**/
                                int is_paypwd_mobile_set = GetSQLHelper.getUserSetOrNotPayPassword(data.get("MOBILE"));
                                if (is_paypwd_mobile_set == 0) {
                                    logger.info("用户没有设置支付密码，进行设置支付密码");
                                    String[] setpaypasswordresult = SetPayPassWordMethod.setPayPassword(appiumUtil, data.get("PAYPASSWORD"), data.get("PAYPASSWORDCONFIRM"));

                                    if (setpaypasswordresult[0].equals("Pass")) {
                                        logger.info(setpaypasswordresult[1]);
                                        /**点击投资按钮**/
                                        InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                        /**调用投资方法**/
                                        investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                                data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));

                                    } else {
                                        logger.info(setpaypasswordresult[1]);
                                        investresult = setpaypasswordresult;
                                    }
                                } else {//用户已经设置过支付密码
                                    logger.info("用户已经设置过支付密码");
                                    /**点击投资按钮**/
                                    InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                    /**调用投资方法**/
                                    investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                            data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));
                                }
                            } else {// 需要充值
                                /** 存管用户充值**/
                                String[] tuberechargeresult = TubeRechargeMethod.tubeRechargeMethod(appiumUtil, data.get("MOBILE"), driver, data.get("PAYPASSWORD"), data.get("PAYPASSWORDCONFIRM"), data.get("BANKOPTION"), data.get("REALNAME"), data.get("PERSONID"), data.get("PROVINCEOPTION"),
                                        data.get("CITYOPTION"), data.get("KAIHUHANGOPTION"), data.get("ACCOUNTNO"), data.get("VERIFYCODE"), data.get("RECHARGEMONEY"));

                                if (tuberechargeresult[0].equals("Fail")) {//存管用户充值失败
                                    logger.info(tuberechargeresult[1]);
                                    investresult = tuberechargeresult;
                                } else {//存管用户充值成功
                                    /**点击投资按钮**/
                                    InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                    /**调用投资方法**/
                                    investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                            data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));
                                }
                            }
                        }
                    } else {//已开通存管充值

                        /**判断是否需要充值
                         * 0表示不需要充值
                         * 1表示要充值
                         */
                        if (data.get("RECHARGEORNOT").equals("0")) {
                            /**判断用户是否设置支付密码  变量等于0表示没有设置支付密码**/
                            int is_paypwd_mobile_set = GetSQLHelper.getUserSetOrNotPayPassword(data.get("MOBILE"));
                            if (is_paypwd_mobile_set == 0) {
                                logger.info("用户没有设置支付密码，进行设置支付密码");
                                String[] setpaypasswordresult = SetPayPassWordMethod.setPayPassword(appiumUtil, data.get("PAYPASSWORD"), data.get("PAYPASSWORDCONFIRM"));

                                if (setpaypasswordresult[0].equals("Pass")) {
                                    logger.info(setpaypasswordresult[1]);
                                    /**点击投资按钮**/
                                    InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                    /**调用投资方法**/
                                    investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                            data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));

                                } else {
                                    logger.info(setpaypasswordresult[1]);
                                    investresult = setpaypasswordresult;
                                }
                            } else {//用户已经设置过支付密码
                                logger.info("用户已经设置过支付密码");
                                /**点击投资按钮**/
                                InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                /**调用投资方法**/
                                investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                        data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));
                            }
                        } else {// 需要充值
                            /** 存管用户充值**/
                            String[] tuberechargeresult = TubeRechargeMethod.tubeRechargeMethod(appiumUtil, data.get("MOBILE"), driver, data.get("PAYPASSWORD"), data.get("PAYPASSWORDCONFIRM"), data.get("BANKOPTION"), data.get("REALNAME"), data.get("PERSONID"), data.get("PROVINCEOPTION"),
                                    data.get("CITYOPTION"), data.get("KAIHUHANGOPTION"), data.get("ACCOUNTNO"), data.get("VERIFYCODE"), data.get("RECHARGEMONEY"));

                            if (tuberechargeresult[0].equals("Fail")) {//存管用户充值失败
                                logger.info(tuberechargeresult[1]);
                                investresult = tuberechargeresult;
                            } else {//存管用户充值成功
                                /**点击投资按钮**/
                                InvestHelper.clickInvest(appiumUtil, Invest.IV_BUTTON_INVEST);
                                /**调用投资方法**/
                                investresult = IvestMethod.investmethod(appiumUtil, data.get("GROUP"), data.get("PRODUCTNAME"), data.get("INVESTAMOUNT"), driver, data.get("PAYPASSWORD"), data.get("MOBILE"),
                                        data.get("PRODUCTSUBNAME"), data.get("VERIFYCODE"), data.get("enviroment"));
                            }
                        }
                    }
                }
                logger.info("第" + rownumber + "条测试用例的结果为:" + investresult[1]);
            } else {
                investresult[0] = lrresult;
                investresult[1] = "用户登录或者注册失败";
                logger.info("第" + rownumber + "条测试用例的结果为:" + investresult[1]);
            }
            if (investresult[0].equals(data.get("EXPECT"))) {
                actual = "Pass";
            } else {
                actual = "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            actual = "Fail";
            investresult[0] = "Fail";
            investresult[1] = "其他原因执行用例不成功（网络或者其他）";
            logger.info("第" + rownumber + "条测试用例的结果为:" + investresult[1]);
            /**写入数据**/
            InputExcelResult.inputExcelResult2("Invest", "001", investresult[0], actual, rownumber, investresult[1]);
            rownumber = rownumber + 1;
            Assert.assertEquals(true, actual.equals("Pass"));
        } finally {
            /**返回到账户页**/
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            String pageSource = driver.getPageSource();
            BackToAccount backToAccount = new BackToAccount();
            backToAccount.backToAccount(appiumUtil, driver, pageSource);
            if (lrresult.equals("Pass")) {
                /**退出登录**/
                LogoutMethod.logoutMethod(appiumUtil);
            }
            /**写入数据**/
            InputExcelResult.inputExcelResult2("Invest", "001", investresult[0], actual, rownumber, investresult[1]);
            rownumber = rownumber + 1;
            Assert.assertEquals(true, actual.equals("Pass"));
        }


    }

}


