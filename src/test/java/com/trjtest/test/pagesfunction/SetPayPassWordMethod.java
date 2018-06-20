package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.AccountCenter;
import com.trjtest.test.pageshelper.PayPasswordHelper;
import com.trjtest.test.utils.AppiumUtil;

/**
 * Created by gjw on 17/3/15.
 * 设置支付密码方法
 */
public class SetPayPassWordMethod {

    /**
     * @param paypassword        : 支付密码
     * @param paypasswordconfirm : 确认支付密码
     */
    public static String[] setPayPassword(AppiumUtil appiumUtil, String paypassword, String paypasswordconfirm) {
        String[] result = new String[2];
        char[] ch = paypassword.toCharArray();
        char[] chs = paypasswordconfirm.toCharArray();
        /**点击账户中的头像按钮 **/
        PayPasswordHelper.clickAccountCenter(appiumUtil, AccountCenter.AC_BUTTON_ACCOUNTCRNTER);
        /**点击密码管理按钮**/
        PayPasswordHelper.clickPasswordManage(appiumUtil, AccountCenter.AC_BUUTTON_PASSWORDMANAGE);
        /**点击支付密码**/
        PayPasswordHelper.clickPayPassword(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORD);

        /**输入支付密码**/
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

        /**点击确认**/
        PayPasswordHelper.clickPayPasswordSubmit(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSUBMIT);

        /**判断设置支付密码的重新设置按钮是否存在，不存在表示支付密码不足6位**/
        if (!appiumUtil.doesElementsExist(AccountCenter.AC_BUTTON_PAYPASSWORDRESET)) {
            result[0] = "Fail";
            result[1] = "支付密码不足6位";
            /** 点击返回至密码管理**/
            PayPasswordHelper.clickBackToPasswordManage(appiumUtil, AccountCenter.AC_BUTTON_BACK);
            /**点击返回至个人中心**/
            PayPasswordHelper.clickBack(appiumUtil, AccountCenter.AC_BUTTON_BACK);
            /**点击返回至账户**／
             *
             */
            PayPasswordHelper.clickAccountCenterBack(appiumUtil, AccountCenter.AC_BUTTON_ACCOUNTCENTERBACK);
        } else {
            /**确认支付密码输入**/
            for (int i = 0; i < paypasswordconfirm.length(); i++) {

                switch (chs[i]) {
                    case '1':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND1);
                        break;
                    case '2':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND2);
                        break;
                    case '3':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND3);
                        break;
                    case '4':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND4);
                        break;
                    case '5':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND5);
                        break;
                    case '6':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND6);
                        break;
                    case '7':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND7);
                        break;
                    case '8':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND8);
                        break;
                    case '9':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND9);
                        break;
                    case '0':
                        PayPasswordHelper.sendPayPasswordConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSWORDSEND0);
                        break;
                }

            }
            /**点击确认支付密码确认按钮 **/
            PayPasswordHelper.clickPayPasswordSubmitConfirm(appiumUtil, AccountCenter.AC_BUTTON_PAYPASSSWORDSUBMITCONFIRM);


            /**判断设置支付密码的重新设置是否存在，存在表示确认支付密码错误**/
            if (appiumUtil.doesElementsExist(AccountCenter.AC_BUTTON_PAYPASSWORDRESET)) {
                result[0] = "Fail";
                result[1] = "确认支付密码不足6位，或者两次密码不同";
                /** 点击返回至密码管理**/
                PayPasswordHelper.clickBackToPasswordManage(appiumUtil, AccountCenter.AC_BUTTON_BACK);
                /**点击返回至个人中心**/
                PayPasswordHelper.clickBack(appiumUtil, AccountCenter.AC_BUTTON_BACK);
                /**点击返回至账户**／
                 *
                 */
                PayPasswordHelper.clickAccountCenterBack(appiumUtil, AccountCenter.AC_BUTTON_ACCOUNTCENTERBACK);
            } else {
                result[0] = "Pass";
                result[1] = "设置支付密码成功";
                /**点击返回至个人中心**/
                PayPasswordHelper.clickBack(appiumUtil, AccountCenter.AC_BUTTON_BACK);
                /**点击返回至账户**／
                 *
                 */
                PayPasswordHelper.clickAccountCenterBack(appiumUtil, AccountCenter.AC_BUTTON_ACCOUNTCENTERBACK);

            }
        }
        return result;
    }
}
