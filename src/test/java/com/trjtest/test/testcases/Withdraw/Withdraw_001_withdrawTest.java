package com.trjtest.test.testcases.Withdraw;

import com.trjtest.test.base.BasePrepare;
import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pagesfunction.*;
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
 * Created by gjw on 17/3/31.
 */
public class Withdraw_001_withdrawTest extends BasePrepare {

    public static Logger logger = Logger.getLogger(Withdraw_001_withdrawTest.class);
    public static int rownumber = 1;

    @Test
    public void initLogin() {
        /**滑动引导页，并点击先体验一下**/
        InitMethod.initMethod(appiumUtil, driver);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"initLogin"})
    public void withdraw(ITestContext context) {
        /**修改测试数据**/
        InputExcelResult.inputModifyMobile("Withdraw", "001", rownumber);

        /**数据提供**/
        List<Map<String, String>> dataList = new ArrayList<>();

        ExcelDataProvider excelDataProvider = new ExcelDataProvider("Withdraw", "001");
        Map<String, String> data = null;

        data = (Map<String, String>) excelDataProvider.next2(rownumber)[0];
        dataList.add(data);

        /**用例执行的实际情况**/
        String actual = null;
        String[] withdrawresult = new String[2];
        String lrresult = null;
        try {
            /**设置数据库参数**/
            BaseFunction.setSql(data.get("enviroment"));

            /**点击账户**/
            LogInHelper.clickAccount(appiumUtil, LogIn.LI_BUTTON_ACCOUNT);
            /**调用登录或者注册方法,得到结果**/
            lrresult = LogInOrRegisterMethod.loInOrRegisterMsthod(appiumUtil, data.get("MOBILE"), data.get("PASSWORD"), data.get("RECOMMEND"), driver);
            /**判断登录注册的结果**/
            if (lrresult.equals("Pass")) {
                /**调用提现的方法**/
                withdrawresult = WithdrawMethod.withdrawMethod(appiumUtil, data.get("GETCASHOUTAMOUNT"), data.get("PAYPASSWORD"), data.get("MOBILE"),
                        data.get("VERIFYCODE"));
                /**退出登录**/
                LogoutMethod.logoutMethod(appiumUtil);
            } else {
                withdrawresult[0] = "Fail";
                withdrawresult[1] = "登录或者注册出错";
            }
            logger.info("第" + rownumber + "条测试用例的结果为:" + withdrawresult[1]);
            if (withdrawresult[0].equals(data.get("EXPECT"))) {
                actual = "Pass";
            } else {
                actual = "Fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            actual = "Fail";
            withdrawresult[0] = "Fail";
            withdrawresult[1] = "其他原因执行用例不成功（网络或者其他）";
            logger.info("第" + rownumber + "条测试用例的结果为:" + withdrawresult[1]);
        } finally {
            /**返回到账户页**/
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            String pageSource = driver.getPageSource();
            BackToAccount backToAccount = new BackToAccount();
            backToAccount.backToAccount(appiumUtil, driver, pageSource);
            if (lrresult.equals("Pass")) {
                /** 退出**/
                LogoutMethod.logoutMethod(appiumUtil);
            }
            /**写入数据**/
            InputExcelResult.inputExcelResult2("Withdraw", "001", withdrawresult[0], actual, rownumber, withdrawresult[1]);
            rownumber = rownumber + 1;
            Assert.assertEquals(true, actual.equals("Pass"));
        }


    }
}
