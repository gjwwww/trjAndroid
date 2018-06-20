package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pageshelper.LogInHelper;
import com.trjtest.test.utils.AppiumUtil;

/**
 * Created by gjw on 17/3/8.
 *  登录后退出功能
 */
public class LogoutMethod {
    public static void logoutMethod(AppiumUtil appiumUtil) {
        /**点击头像进入个人设置**/
        LogInHelper.clickAccountCenter(appiumUtil, LogIn.LI_BUTTON_ACCOUNTCENTER);

        /**点击个人中心中的设置**/
        LogInHelper.clickSetting(appiumUtil, LogIn.LI_BUTTON_SETTING);

        /**点击设置中的退出按钮**/
        LogInHelper.clickExit(appiumUtil, LogIn.LI_BUTTON_EXIT);

        /**点击退出登录后的返回按钮**/
        LogInHelper.clickBack(appiumUtil,LogIn.LI_BUTTON_BACK);

    }

}
