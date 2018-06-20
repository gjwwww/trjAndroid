package com.trjtest.test.pageshelper;

import com.trjtest.test.utils.AppiumUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * Created by gjw on 17/4/1.
 * 邀请好友各个元素操作的方法
 */
public class InviteFriendsHelper {
    public static Logger logger = Logger.getLogger(InviteFriendsHelper.class);

    /**点击账户页面的邀请好友按钮**/
    public static void clickInviteFriends(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("账户页面邀请好友按钮存在，并点击");
        }else {
            logger.info("账户页面的邀请还有按钮不存在");
        }
    }

    /**点击邀请好友页面的分享到社交媒体按钮 **/
    public static void clickShareToMedia (AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("邀请好友页面分享到社交媒体按钮存在，并点击");
        }else {
            logger.info("邀请好友页面分享到社交媒体按钮不存在");
        }
    }

    /**点击分享到QQ**/
    public static void clickShareToQQ (AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("分享到QQ元素存在，并点击");
        }else {
            logger.info("分享到QQ元素不存在");
        }
    }

    /**点击QQ的分享到我的电脑**/
    public static void clickMyComputer(AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag) {
            appiumUtil.click(byElement);
            logger.info("QQ中我的电脑按钮存在，并点击");
        }else {
            logger.info("QQ中我的电脑按钮不存在");
        }
    }
    /**点击我的电脑之后的弹窗中发送按钮**/
    public static void clickMyComputerSend(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info(" 点击我的电脑后发送按钮存在，并点击");
        }else {
            logger.info("点击我的电脑后，发送按钮不存在");
        }
    }

    /**点击发送之后返回投融家APP按钮**/
    public static void clickBackToAPP(AppiumUtil appiumUtil ,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("返回投融家理财按钮存在，并点击");
        }else {
            logger.info("返回投融家理财按钮不存在");
        }
    }
    /**点击邀请好友的返回按钮**/
    public static void clickInviiteBack (AppiumUtil appiumUtil,By byElement){
        Boolean flag = appiumUtil.doesElementsExist(byElement);
        if (flag){
            appiumUtil.click(byElement);
            logger.info("邀请好友页面的返回按钮存在，并点击");
        }else{
            logger.info("邀请好友页面的返回按钮不存在");
        }
    }
}
