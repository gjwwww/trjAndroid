package com.trjtest.test.pagesfunction;

import com.trjtest.test.pages.LogIn;
import com.trjtest.test.pages.Register;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.LogInHelper;
import com.trjtest.test.pageshelper.RegisterHelper;
import com.trjtest.test.utils.AppiumUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;

/**
 * Created by gjw on 17/3/23.
 *  判断用户是注册还是登录， 并返回结果
 */
public class LogInOrRegisterMethod {
    public static Logger logger =Logger.getLogger(LogInOrRegisterMethod.class);

    /**
     *
     * @param appiumUtil
     * @param mobile      ：手机号
     * @param password    ：密码
     * @param recommend   ：推荐人代码
     * @param driver
     * @return
     */

    public static String loInOrRegisterMsthod(AppiumUtil appiumUtil, String mobile , String password, String recommend,AppiumDriver driver){

        /**定义用户登录或者注册的结果**/
        String lrresult = null;
        int uid = GetSQLHelper.getUID(mobile);
        if (uid != 0){
            logger.info("用户已注册，走登录流程");
            String[] loginresult = LoginMethod.loginmethod(appiumUtil,mobile,password,driver);
            if(loginresult[0].equals("Fail")){
                lrresult =loginresult[0];
                logger.info(loginresult[1]);
                /**点击回退到首页**/

                LogInHelper.clickBack(appiumUtil, LogIn.LI_BUTTON_BACK);
                if (LogInHelper.nextDoseExist(appiumUtil,LogIn.LI_BUTTON_MOBILENEXT)){
                    LogInHelper.clickBack(appiumUtil,LogIn.LI_BUTTON_BACK);
                }
            }else {
                lrresult = loginresult[0];
                logger.info(loginresult[1]);
            }

        }else {
            logger.info("用户未注册，走注册流程");
            String[] registerresult = RegisterMethod.registerMethod(appiumUtil,mobile,password,driver,recommend);
            if (registerresult[0].equals("Pass")) {
                lrresult = registerresult[0];
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

            }
            else {
                lrresult = registerresult[0];
                logger.info(registerresult[1]);
                /**点击回退到首页**/

                LogInHelper.clickBack(appiumUtil,LogIn.LI_BUTTON_BACK);
                if (LogInHelper.nextDoseExist(appiumUtil,LogIn.LI_BUTTON_MOBILENEXT)){
                    LogInHelper.clickBack(appiumUtil,LogIn.LI_BUTTON_BACK);
                }

            }
        }
        return  lrresult;
    }
}
