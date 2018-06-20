package com.trjtest.test.pages;

import org.openqa.selenium.By;

/**
 * Created by gjw on 17/4/1.
 * 邀请好友页面的各个元素
 */
public class InviteFriends {
    /**账户页面中的邀请好友按钮**/
    public static final By IF_BUTTON_INVITEFRIENDSCONTAINER = By.id("com.trj.hp:id/ll_invite_friend_container");
    /**分享到社交媒体**/
    public static final By IF_BUTTON_SHARETOMEDIA = By.id("com.trj.hp:id/invite_bt_submit");
    /**我的推荐**/
    public static final By IF_BUTTON_MYINVITE = By.id("com.trj.hp:id/my_invite_btn");

    /**微信好友分享按钮**/
    public static final By IF_BUTTON_WECHATFRIENDS = By.xpath("//android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]");
    /**微信朋友圈分享按钮**/
    public static final By IF_BUTTON_WECHARTCIRCLEFRIENDS = By.xpath("//android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]");
    /**QQ分享按钮 **/
    public static final By IF_BUTTON_QQ = By.xpath("//android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[3]");

    /**QQ分享页，我的电脑按钮**/
    public static final By IF_BUTTON_QQMYCOMPUTER = By.id("com.tencent.mobileqq:id/text1");

    /**QQ分享页，点击我的电脑后的发送按钮**/
    public static final By IF_BUTTON_QQMYCOMPUTERSENDER = By.id("com.tencent.mobileqq:id/dialogRightBtn");
    /**QQ分享页，点击我的电脑后的取消按钮**/
    public static final By IF_BUTTON_QQMYCONPUTERCANCEL = By.id("com.tencent.mobileqq:id/dialogLeftBtn");
    /**QQ分享完之后返回投融家理财**/
    public static final By IF_BUTTON_QQBACKTOTOURONGJIA = By.id("com.tencent.mobileqq:id/dialogLeftBtn");
    /**QQ分享完之后留在QQ **/
    public static final By IF_BUTTON_QQSTAYQQ = By.id("com.tencent.mobileqq:id/dialogRightBtn");
    /**邀请好友页的返回按钮**/
    public static final By IF_BUTTON_INVITEBACK = By.id("com.trj.hp:id/btn_back");

}
