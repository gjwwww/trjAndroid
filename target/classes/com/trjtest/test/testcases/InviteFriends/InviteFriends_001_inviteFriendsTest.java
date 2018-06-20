package com.trjtest.test.testcases.InviteFriends;

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
 * Created by gjw on 17/4/1.
 */
public class InviteFriends_001_inviteFriendsTest extends BasePrepare {
    public static Logger logger = Logger.getLogger(InviteFriends_001_inviteFriendsTest.class);
    public static int rownumber = 1;

    @Test
    public void intiLogin() {
        /**滑动引导页，并点击先体验一下**/
        InitMethod.initMethod(appiumUtil, driver);
    }

    @Test(dataProvider = "testData", dependsOnMethods = {"intiLogin"})
    public void inviteFriends(ITestContext context) {
        /**修改测试数据**/
        InputExcelResult.inputModifyMobile("InviteFriends", "001", rownumber);

        /**数据提供**/
        List<Map<String, String>> dataList = new ArrayList<>();

        ExcelDataProvider excelDataProvider = new ExcelDataProvider("InviteFriends", "001");
        Map<String, String> data = null;

        data = (Map<String, String>) excelDataProvider.next2(rownumber)[0];
        dataList.add(data);

        /**用例执行的实际情况**/
        String actual = null;
        String[] invitefriendsresult = new String[2];
        String lrresult = null;
        try {
            /**设置数据库参数**/
            BaseFunction.setSql(data.get("enviroment"));

            /**点击账户**/
            LogInHelper.clickAccount(appiumUtil, LogIn.LI_BUTTON_ACCOUNT);
            /**调用登录或者注册方法,得到结果**/
            lrresult = LogInOrRegisterMethod.loInOrRegisterMsthod(appiumUtil, data.get("MOBILE"), data.get("PASSWORD"), data.get("RECOMMEND"), driver);
            if (lrresult.equals("Pass")) {
                /**分享之前的推荐人数**/
                int invitecountbefore = GetSQLHelper.getUserRecommendCount(data.get("MOBILE"));
                /**调用分享方法**/
                invitefriendsresult = InviteFriendsMethod.inviteFriendsMethod(appiumUtil, data.get("IMOBILE"), data.get("IPASSWORD"),
                        driver, data.get("IRECOMMEND"));
                int invitecountafter = GetSQLHelper.getUserRecommendCount(data.get("MOBILE"));
                if (invitefriendsresult[0].equals("Pass")) {
                    if (invitecountafter == invitecountbefore) {//判断两次获取推荐人数是否相等
                        invitefriendsresult[0] = "Fail";
                        invitefriendsresult[1] = "用户已经邀请好友，但是推荐人的数目没有增加";
                    }
                }

            } else {
                invitefriendsresult[0] = lrresult;
                invitefriendsresult[1] = "登录或者注册失败";
            }
            logger.info("第" + rownumber + "条测试用例的结果为:" + invitefriendsresult[1]);
            if (invitefriendsresult[0].equals(data.get("EXPECT"))) {
                actual = "Pass";
            } else {
                actual = "Fail";
            }

        } catch (Exception e) {
            e.printStackTrace();
            actual = "Fail";
            invitefriendsresult[0] = "Fail";
            invitefriendsresult[1] = "其他原因执行用例不成功（网络或者其他）";
            logger.info("第" + rownumber + "条测试用例的结果为:" + invitefriendsresult[1]);
            /**写入数据**/
            InputExcelResult.inputExcelResult2("InviteFriends", "001", invitefriendsresult[0], actual, rownumber, invitefriendsresult[1]);
            rownumber = rownumber + 1;
            Assert.assertEquals(true, actual.equals("Pass"));
        } finally {
            /**返回到账户页**/
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            String pageSource = driver.getPageSource();
            BackToAccount backToAccount = new BackToAccount();
            backToAccount.backToAccount(appiumUtil, driver, pageSource);
            if (lrresult.equals("Pass")) {
                /**退出**/
                LogoutMethod.logoutMethod(appiumUtil);
            }
            /**写入数据**/
            InputExcelResult.inputExcelResult2("InviteFriends", "001", invitefriendsresult[0], actual, rownumber, invitefriendsresult[1]);
            rownumber = rownumber + 1;
            Assert.assertEquals(true, actual.equals("Pass"));
        }

    }
}


