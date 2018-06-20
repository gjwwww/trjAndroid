package com.trjtest.test.testcases.Register;

import com.trjtest.test.base.BasePrepare;
import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pages.Register;
import com.trjtest.test.pagesfunction.*;
import com.trjtest.test.pageshelper.LogInHelper;
import com.trjtest.test.pageshelper.RegisterHelper;
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
 * Created by gjw on 17/3/14.
 */
public class Register_009_registerTest extends BasePrepare {

    public static Logger logger = Logger.getLogger(Register_009_registerTest.class);
    public static int rownumber = 1;

    @Test
    public void initLogin() {
        /**滑动引导页，并点击先体验一下**/
        InitMethod.initMethod(appiumUtil, driver);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"initLogin"})
    public void register(ITestContext context) {
        /**修改测试数据**/
        InputExcelResult.inputModifyMobile("Register", "009", rownumber);

        /**数据提供**/
        List<Map<String, String>> dataList = new ArrayList<>();

        ExcelDataProvider excelDataProvider = new ExcelDataProvider("Register", "009");
        Map<String, String> data = null;

        data = (Map<String, String>) excelDataProvider.next2(rownumber)[0];
        dataList.add(data);

        /**用例执行的实际情况**/
        String actual = null;
        String[] registerresult = new String[2];
        try {
            /**设置数据库参数**/
            BaseFunction.setSql(data.get("enviroment"));

            /**点击账户**/
            LogInHelper.clickAccount(appiumUtil, LogIn.LI_BUTTON_ACCOUNT);
            /**注册**/
            registerresult = RegisterMethod.registerMethod(appiumUtil, data.get("MOBILE"), data.get("PASSWORD"), driver, data.get("RECOMMEND"));
            logger.info("第" + rownumber + "条测试用例的结果为:" + registerresult[1]);
            if (registerresult[0].equals(data.get("EXPECT"))) {
                actual = "Pass";
            } else {
                actual = "Fail";
            }
            if (registerresult[0].equals("Pass")) {
                /**点击注册完成之后的我知道了按钮**/
                RegisterHelper.clickSuccessKnow(appiumUtil, Register.Rg_BUTTON_SUCCESSKNOW);
                /**点击理财金提取页面的返回按钮**/
                LogInHelper.clickBack(appiumUtil, LogIn.LI_BUTTON_BACK);

                /**判断首页是否有开通银行存管的弹窗**/
                if (appiumUtil.doesElementsExist(Register.RG_BUTTON_NEGATIVEDEPOSITORY)) {
                    /**点击暂不开通银行存管**/
                    RegisterHelper.clickNegativePository(appiumUtil, Register.RG_BUTTON_NEGATIVEDEPOSITORY);
                    /**点击暂不开通之后的确认框的我知道了**/
                    RegisterHelper.clickConfirmKnow(appiumUtil, Register.RG_BUTTON_CONFIRMKNOW);
                    /**点击账户元素**/
                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                } else {
                    /**点击账户元素**/
                    RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
                    if (appiumUtil.doesElementsExist(Register.RG_BUTTON_NEGATIVEDEPOSITORY)) {
                        /**点击暂不开通银行存管**/
                        RegisterHelper.clickNegativePository(appiumUtil, Register.RG_BUTTON_NEGATIVEDEPOSITORY);
                        /**点击暂不开通之后的确认框的我知道了**/
                        RegisterHelper.clickConfirmKnow(appiumUtil, Register.RG_BUTTON_CONFIRMKNOW);

                    }
                }

            } else {
                /**点击回退到首页**/
                LogInHelper.clickBack(appiumUtil, LogIn.LI_BUTTON_BACK);
                if (LogInHelper.nextDoseExist(appiumUtil, LogIn.LI_BUTTON_MOBILENEXT)) {
                    LogInHelper.clickBack(appiumUtil, LogIn.LI_BUTTON_BACK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            actual = "Fail";
            registerresult[0] = "Fail";
            registerresult[1] = "其他原因执行用例不成功（网络或者其他）";
            logger.info("第" + rownumber + "条测试用例的结果为:" + registerresult[1]);
        } finally {
            /**返回到账户页**/
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            String pageSource = driver.getPageSource();
            BackToAccount backToAccount = new BackToAccount();
            backToAccount.backToAccount(appiumUtil, driver, pageSource);
            if (registerresult[0].equals("Pass")) {
                /**退出登录**/
                LogoutMethod.logoutMethod(appiumUtil);
            }
            /**写入数据**/
            InputExcelResult.inputExcelResult2("Register", "009", registerresult[0], actual, rownumber, registerresult[1]);
            rownumber = rownumber + 1;
            Assert.assertEquals(true, actual.equals("Pass"));
        }

    }
}
