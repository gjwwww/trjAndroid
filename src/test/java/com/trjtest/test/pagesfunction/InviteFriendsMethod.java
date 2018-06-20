package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.InviteFriends;
import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pages.Register;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.InviteFriendsHelper;
import com.trjtest.test.pageshelper.LogInHelper;
import com.trjtest.test.pageshelper.RegisterHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

/**
 * Created by gjw on 17/4/1.
 */
public class InviteFriendsMethod {
    public static Logger logger = Logger.getLogger(InviteFriendsMethod.class);
    /**@param imobile :被邀请人的手机号**/
    public static String[] inviteFriendsMethod (AppiumUtil appiumUtil, String imobile, String ipassword, AppiumDriver driver,String irecommend){
        String[] result = new String[2];
        /**点击账户的邀请好友**/
        InviteFriendsHelper.clickInviteFriends(appiumUtil, InviteFriends.IF_BUTTON_INVITEFRIENDSCONTAINER);
        /**点击分享到社交媒体**/
        InviteFriendsHelper.clickShareToMedia(appiumUtil,InviteFriends.IF_BUTTON_SHARETOMEDIA);
        /**点击分享到QQ**/
        InviteFriendsHelper.clickShareToQQ(appiumUtil,InviteFriends.IF_BUTTON_QQ);
        /** 点击分享到我的电脑**/
        InviteFriendsHelper.clickMyComputer(appiumUtil,InviteFriends.IF_BUTTON_QQMYCOMPUTER);
        /**点击发送**/
        InviteFriendsHelper.clickMyComputerSend(appiumUtil,InviteFriends.IF_BUTTON_QQMYCOMPUTERSENDER);
        /**点击返回APP**/
        InviteFriendsHelper.clickBackToAPP(appiumUtil,InviteFriends.IF_BUTTON_QQBACKTOTOURONGJIA);
        /**点击邀请好友的返回按钮**/
        InviteFriendsHelper.clickInviiteBack(appiumUtil,InviteFriends.IF_BUTTON_INVITEBACK);

        /**调用注册功能**/
        int uid = GetSQLHelper.getUID(imobile);
        if (uid!=0){
            result[0] = "Fail";
            result[1] = "被邀请人已注册，不能进行邀请好友功能";
        }else {
            logger.info("被邀请人未注册可进行邀请好友功能");
            /**退出**/
            LogoutMethod.logoutMethod(appiumUtil);
            /**点击账户**/
            RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);

            String[] registerresult = RegisterMethod.registerMethod(appiumUtil, imobile, ipassword, driver, irecommend);
            if (registerresult[0].equals("Pass")) {
                result[0] = "Pass";
                result[1] = "邀请好友成功";
                logger.info(registerresult[1]);
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
                result[0] = "Fail";
                result[1] = "因用户注册失败，邀请好友失败";
                logger.info(registerresult[1]);
                /**点击回退到首页**/

                LogInHelper.clickBack(appiumUtil, LogIn.LI_BUTTON_BACK);
                if (LogInHelper.nextDoseExist(appiumUtil, LogIn.LI_BUTTON_MOBILENEXT)) {
                    LogInHelper.clickBack(appiumUtil, LogIn.LI_BUTTON_BACK);
                }
            }
        }
        return result;
    }
}
