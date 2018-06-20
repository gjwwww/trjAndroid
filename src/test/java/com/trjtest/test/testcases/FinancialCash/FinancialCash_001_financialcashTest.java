package com.trjtest.test.testcases.FinancialCash;

import com.trjtest.test.base.BasePrepare;
import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pagesfunction.*;
import com.trjtest.test.pageshelper.GetSQLHelper;
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
 * Created by gjw on 17/4/5.
 */
public class FinancialCash_001_financialcashTest extends BasePrepare {
    public static Logger logger = Logger.getLogger(FinancialCash_001_financialcashTest.class);
    public static int rownumber = 1;

    @Test
    public void initLogin() {
        /**滑动引导页，并点击先体验一下**/
        InitMethod.initMethod(appiumUtil, driver);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"initLogin"})
    public void financialcash(ITestContext context) {
        /**修改测试数据**/
        InputExcelResult.inputModifyMobile("FinancialCash", "001", rownumber);

        /**数据提供**/
        List<Map<String, String>> dataList = new ArrayList<>();

        ExcelDataProvider excelDataProvider = new ExcelDataProvider("FinancialCash", "001");
        Map<String, String> data = null;

        data = (Map<String, String>) excelDataProvider.next2(rownumber)[0];
        dataList.add(data);

        /**用例执行的实际情况**/
        String actual = null;
        String[] financialcashresult = new String[2];
        String lrresult = null;
        try {
            /**设置数据库参数**/
            BaseFunction.setSql(data.get("enviroment"));
            /**点击账户**/
            LogInHelper.clickAccount(appiumUtil, LogIn.LI_BUTTON_ACCOUNT);
            /**调用登录或者注册方法,得到结果**/
            lrresult = LogInOrRegisterMethod.loInOrRegisterMsthod(appiumUtil, data.get("MOBILE"), data.get("PASSWORD"), data.get("RECOMMEND"), driver);
            if (lrresult.equals("Pass")) {
                String bonus_in_totalbefore = GetSQLHelper.getUserGetFinancialCash(data.get("MOBILE"));
                /**调用提取理财金方法**/
                financialcashresult = FinancialCashMethod.financialCashmethod(appiumUtil, data.get("MOBILE"), driver);
                if (financialcashresult[0].equals("Pass")) {
                    String bonus_in_totalafter = GetSQLHelper.getUserGetFinancialCash(data.get("MOBILE"));
                    if (bonus_in_totalafter.compareTo(bonus_in_totalbefore) <= 0) {
                        financialcashresult[0] = "Fail";
                        financialcashresult[1] = "提取理财金后，已取金额和原来相同，错误";
                        logger.info(financialcashresult[1]);
                    }
                }
            } else {
                financialcashresult[0] = lrresult;
                financialcashresult[1] = "登录或者注册失败";
            }
            if (financialcashresult[0].equals(data.get("EXPECT"))) {
                actual = "Pass";
            } else {
                actual = "Fail";
            }
            logger.info("第" + rownumber + "条测试用例的结果为:" + financialcashresult[1]);
        } catch (Exception e) {
            e.printStackTrace();
            actual = "Fail";
            financialcashresult[0] = "Fail";
            financialcashresult[1] = "其他原因执行用例不成功（网络或者其他）";
            logger.info("第" + rownumber + "条测试用例的结果为:" + financialcashresult[1]);
        } finally {
            /**返回到账户页**/
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            String pageSource = driver.getPageSource();
            BackToAccount backToAccount = new BackToAccount();
            backToAccount.backToAccount(appiumUtil, driver, pageSource);
            if (lrresult != null && lrresult.equals("Pass")) {
                /**退出**/
                LogoutMethod.logoutMethod(appiumUtil);
            }
            /**写入数据**/
            InputExcelResult.inputExcelResult2("FinancialCash", "001", financialcashresult[0], actual, rownumber, financialcashresult[1]);
            rownumber = rownumber + 1;
            if (actual != null) {
                Assert.assertEquals(true, actual.equals("Pass"));
            }
        }
    }
}
