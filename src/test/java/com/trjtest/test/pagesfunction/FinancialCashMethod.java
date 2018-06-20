package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.FinancialCash;
import com.trjtest.test.pageshelper.FinancialCashHelper;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

/**
 * Created by gjw on 17/4/5.
 * 提取理财金的方法
 */
public class FinancialCashMethod {
    public static Logger logger = Logger.getLogger(FinancialCashMethod.class);

    public static String[] financialCashmethod(AppiumUtil appiumUtil, String mobile, AppiumDriver driver) {
        String[] result = new String[2];
        /**上滑**/
        FinancialCashHelper.swipeTOUp(appiumUtil, driver);
        /**点击账户中的理财金**/
        FinancialCashHelper.clickFinacialCashContain(appiumUtil, FinancialCash.FC_BUTTON_FINANCIALCONTAIN);
        /**判断用户是否可用提取理财金**/
        String bonus_stay_total = GetSQLHelper.getUserStayFinancialCash(mobile);
        if (bonus_stay_total.equals("0.00")) {//表示没有可提取的金额
            result[0] = "Fail";
            result[1] = "用户没有可提的理财金，不能点击按钮";
            logger.info(result[1]);
            /**点击返回**/
            FinancialCashHelper.clickFinancialCashBack(appiumUtil, FinancialCash.FC_BUTTON_FINANCIALBACK);
        } else {
            /**点击理财金页面的立即提取到账户**/
            FinancialCashHelper.clickToAccountNow(appiumUtil, FinancialCash.FC_BUTTON_TOACCOUNTNOW);
            result[0] = "Pass";
            result[1] = "用户提取理财金成功";
            logger.info(result[1]);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**点击返回**/
            FinancialCashHelper.clickFinancialCashBack(appiumUtil, FinancialCash.FC_BUTTON_FINANCIALBACK);
        }
        return result;
    }
}
