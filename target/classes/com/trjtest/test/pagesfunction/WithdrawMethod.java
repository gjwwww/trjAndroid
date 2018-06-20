package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.AccountCenter;
import com.trjtest.test.pages.Withdraw;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.PayPasswordHelper;
import com.trjtest.test.pageshelper.WithdrawHelper;
import com.trjtest.test.utils.AppiumUtil;
import org.apache.log4j.Logger;

/**
 * Created by gjw on 17/3/17
 * 提现方法.
 */
public class WithdrawMethod {
    public static Logger logger = Logger.getLogger(WithdrawMethod.class);

    /**
     * @param cashout_amount  :提现的金额
     * @param paypassword     :支付密码
     * @return
     */
    public static String[] withdrawMethod(AppiumUtil appiumUtil,String cashout_amount,String paypassword,String mobile,
                                          String verifycode){
        String[] result = new String[2];

        /**用户想提现的金额**/
        float wantcashout = Float.parseFloat(cashout_amount);

        /**用户实际可提现的金额**/
        float actualcashoutamount = Float.parseFloat(GetSQLHelper.getUserCashoutAmount(mobile))/100;


        /**点击账户页面的提现按钮**/
        WithdrawHelper.clickWithdraw(appiumUtil,Withdraw.WD_BUTTON_WITHDRAWCONTAINER);

        /**判断用户是否已经实名**/
        String realn = GetSQLHelper.getRralName(mobile);
        if (realn == null){//没有进行实名，用户未绑定银行卡
            if (appiumUtil.doesElementsExist(Withdraw.WD_BUTTON_CANCEL)){//没有实名的用户，点击账户的弹窗是否有取消按钮
                result[0] = "Pass";
                result[1] = "用户没有实名，请先绑定银行卡";
                logger.info(result[1]);
                /**点击取消**/
                WithdrawHelper.clickNoRealNameCancel(appiumUtil,Withdraw.WD_BUTTON_CANCEL);
            }else {
                result[0] = "Fail";
                result[1] = "用户没有实名，但仍能进行提现按钮";
                /**点击返回按钮**/
                WithdrawHelper.clickWithdrawBack(appiumUtil,Withdraw.WD_BUTTON_BACK);
            }
        } else {//用户已经绑定银行卡
            /**判断是否开通存管银行**/
            int status = GetSQLHelper.getUserOpenOrNotTube(mobile);
            if (status != 2) {//用户为非存管用户
                /**点击开户行**/
                WithdrawHelper.clickKaiHuHang(appiumUtil, Withdraw.WD_BUTTON_KAIHUHANG);
                /**选择开户城市 省**/
                WithdrawHelper.clickProvince(appiumUtil,Withdraw.WD_BUTTON_PROVINCE);
                /**选择开户城市 市**/
                WithdrawHelper.clickCity(appiumUtil,Withdraw.WD_BUTTON_CITY);
                /**选择开户支行**/
                WithdrawHelper.clickBranch(appiumUtil,Withdraw.WD_BUTTON_BRANCH);
                /**点击提现金额**/
                WithdrawHelper.clickCashOut(appiumUtil,Withdraw.WD_BUTTON_CASHOUTAMOUNT);

                /**输入金额**/
                char[] chars =cashout_amount.toCharArray();
                for (int i=0;i<cashout_amount.length();i++){
                    switch (chars[i]){
                        case '1':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT1);
                            break;
                        case '2':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT2);
                            break;
                        case '3':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT3);
                            break;
                        case '4':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT4);
                            break;
                        case '5':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT5);
                            break;
                        case '6':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT6);
                            break;
                        case '7':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT7);
                            break;
                        case '8':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT8);
                            break;
                        case '9':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT9);
                            break;
                        case '0':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNT0);
                            break;
                        case '.':
                            WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNTpoint);
                            break;
                    }
                }


                /**点击隐藏输入金额框**/
                WithdrawHelper.sendCashout(appiumUtil,Withdraw.WD_BUTTON_SENDAMOUNTHIDE);
                /**点击免费提现
                 */
                WithdrawHelper.clickFreeWithdraw(appiumUtil,Withdraw.WD_BUTTON_FREEWITHDRAW);
                /**判断免费提现按钮是否存在**/
                if(appiumUtil.doesElementsExist(Withdraw.WD_BUTTON_FREEWITHDRAW)){
                    result[0]= "Fail";
                    result[1]= " 提现金额大于可提现金额";
                    logger.info(result[1]);
                    /**点击返回 **/
                    WithdrawHelper.clickWithdrawBack(appiumUtil,Withdraw.WD_BUTTON_BACK);
                }else {
                    /**  输入支付密码**/
                    char[] ch = paypassword.toCharArray();
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
                    try {

                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /** 点击提现**/
                    WithdrawHelper.clickPyWithdraw(appiumUtil, Withdraw.WD_BUTTON_WITHDRAW);

                    /**判断提现按钮是否存在**/
                    if (appiumUtil.doesElementsExist(Withdraw.WD_BUTTON_WITHDRAW)){
                        result[0]= "Fail";
                        result[1]= "支付密码输入错误";
                        logger.info(result[1]);
                        /**点击非存管提现页面的取消按钮**/
                        WithdrawHelper.clickSendPayPasswordCancel(appiumUtil,Withdraw.WD_BUTTON_SENDPAYPASSWORDCANCEL);
                        /**点击返回**/
                        WithdrawHelper.clickWithdrawBack(appiumUtil,Withdraw.WD_BUTTON_BACK);

                    }else {
                        result[0] = "Pass";
                        result[1] = "非存管用户提现成功";
                        /**点击成功提现后的确定按钮 **/
                        WithdrawHelper.clickWithdrawSuccess(appiumUtil, Withdraw.WD_BUTTON_WITHDRAWSUCCESS);
                    }


                }

            } else {//用户为存管用户
                /**输入用户想提取的金额**/
                WithdrawHelper.typeTubeCashoutAmount(appiumUtil, Withdraw.WD_INPUT_TUBECASHOUTAMOUNT, cashout_amount);
                /**输入用户的支付密码**/
                WithdrawHelper.typeTubePayPassword(appiumUtil, Withdraw.WD_INPUT_TUBEPAYPASSWORD, paypassword);
                /**点击免费提现**/
                WithdrawHelper.clickTubeFreeCashOut(appiumUtil, Withdraw.WD_BUTTON_TUBEFREEWITHDRAW);

                if (appiumUtil.doesElementsExist(Withdraw.WD_BUTTON_TUBEFREEWITHDRAW)) {//判断免费提现按钮是否存在，存在表示密码或者金额有误
                    result[0] = "Fail";
                    result[1] = "支付密码或者提现金额错误";
                    logger.info(result[1]);
                } else {//进行输入动态码
                    /**输入动态码**/
                    WithdrawHelper.typeTubeVreifycode(appiumUtil, Withdraw.WD_INPUT_TUBEVERIFYCODE, verifycode);
                    /**点击确认提现**/
                    WithdrawHelper.clickTubeConfirmWithdraw(appiumUtil, Withdraw.WD_BUTTON_TUBEWITHDRAWCONFIRM);
                    /**判断确认提现按钮是否存在  **/
                    if (appiumUtil.doesElementsExist(Withdraw.WD_BUTTON_TUBEWITHDRAWCONFIRM)) {//存在
                        result[0] = "Fail";
                        result[1] = "动态码错误,或者其他错误";
                        logger.info(result[1]);
                        /**点击返回**/
                        WithdrawHelper.clickWithdrawBack(appiumUtil,Withdraw.WD_BUTTON_TUBEWITHDRAWBACK);
                    }else {
                        result[0] ="Pass";
                        result[1] ="存管用户提现成功";
                        logger.info(result[1]);
                        WithdrawHelper.clickWithdrawSuccess(appiumUtil,Withdraw.WD_BUTTON_WITHDRAWSUCCESS);

                    }
                }
            }
        }
        return result;
    }

}
