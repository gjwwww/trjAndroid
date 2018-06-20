package com.trjtest.test.testcases.LogIn;

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
 * Created by gjw on 17/3/8.
 */
public class LogIn_005_loginpassed extends BasePrepare {
    public static Logger logger = Logger.getLogger(LogIn_005_loginpassed.class);
    public static int rownumber = 1;

    @Test
    public void initLogin() {
        /**滑动引导页，并点击先体验一下**/
        InitMethod.initMethod(appiumUtil, driver);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"initLogin"})
    public void LogIn(ITestContext context) {
        /**修改测试数据**/
        InputExcelResult.inputModifyMobile("LogIn", "005", rownumber);
        /**数据提供**/
        List<Map<String, String>> dataList = new ArrayList<>();
        ExcelDataProvider excelDataProvider = new ExcelDataProvider("LogIn", "005");
        Map<String, String> data = null;
        data = (Map<String, String>) excelDataProvider.next2(rownumber)[0];
        dataList.add(data);

        /**用例执行的实际情况**/
        String actual = null;
        String[] loginresult = new String[2];
        try {
            /**设置数据库参数**/
            BaseFunction.setSql(data.get("enviroment"));
            /**点击账户**/
            LogInHelper.clickAccount(appiumUtil, LogIn.LI_BUTTON_ACCOUNT);
            /**登录**/
            loginresult = LoginMethod.loginmethod(appiumUtil, data.get("MOBILE"), data.get("PASSWORD"), driver);
            logger.info("第" + rownumber + "条测试用例的结果为:" + loginresult[1]);
            if (loginresult[0].equals(data.get("EXPECT"))) {
                actual = "Pass";
            } else {
                actual = "Fail";
            }
            /**判断登录是否成功**/
            if (loginresult[0].equals("Fail")) {
                /**点击回退到首页**/

                LogInHelper.clickBack(appiumUtil, LogIn.LI_BUTTON_BACK);
                if (LogInHelper.nextDoseExist(appiumUtil, LogIn.LI_BUTTON_MOBILENEXT)) {
                    LogInHelper.clickBack(appiumUtil, LogIn.LI_BUTTON_BACK);
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
            actual = "Fail";
            loginresult[0] = "Fail";
            loginresult[1] = "其他原因执行用例不成功（网络或者其他）";
            logger.info("第" + rownumber + "条测试用例的结果为:" + loginresult[1]);
            /**写入数据**/
            InputExcelResult.inputExcelResult2("LogIn", "005", loginresult[0], actual, rownumber, loginresult[1]);
            rownumber = rownumber + 1;
            Assert.assertEquals(true, actual.equals("Pass"));
        } finally {
            /**返回到账户页**/
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            String pageSource = driver.getPageSource();
            BackToAccount backToAccount = new BackToAccount();
            backToAccount.backToAccount(appiumUtil, driver, pageSource);
            if (loginresult[0].equals("Pass")) {
                /**退出**/
                LogoutMethod.logoutMethod(appiumUtil);
            }
            /**写入数据**/
            InputExcelResult.inputExcelResult2("LogIn", "005", loginresult[0], actual, rownumber, loginresult[1]);
            rownumber = rownumber + 1;
            Assert.assertEquals(true, actual.equals("Pass"));
        }

    }
}
