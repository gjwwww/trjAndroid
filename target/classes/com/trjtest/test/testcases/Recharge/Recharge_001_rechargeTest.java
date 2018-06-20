package com.trjtest.test.testcases.Recharge;

import com.trjtest.test.base.BasePrepare;
import com.trjtest.test.pages.Register;
import com.trjtest.test.pagesfunction.*;
import com.trjtest.test.pageshelper.GetSQLHelper;
import com.trjtest.test.pageshelper.RegisterHelper;
import com.trjtest.test.utils.ExcelDataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gjw on 17/3/15.
 * 非存管用户设置支付密码和充值
 */
public class Recharge_001_rechargeTest extends BasePrepare {
    @Test
    public void recharge(){
        /**用例执行的实际情况**/

        String actual;

        /**滑动引导页，并点击先体验一下**/
        InitMethod.initMethod(appiumUtil,driver);

        /**数据提供**/
        List<Map<String, String>> dataList = new ArrayList<>();

        ExcelDataProvider excelDataProvider = new ExcelDataProvider("Recharge","001");

        /**定义所读取数据的行数**/
        int rownumber=1;
        while (excelDataProvider.hasNext()) {
            Map<String, String> data = (Map<String, String>) excelDataProvider.next()[0];
            dataList.add(data);
            /**点击账户**/
            RegisterHelper.clickAccount(appiumUtil, Register.RG_BUTTON_ACCOUNT);
            /**登录  **/
            String[] result = LoginMethod.loginmethod(appiumUtil, data.get("MOBILE"), data.get("PASSWORD"), driver);

            if(appiumUtil.doesElementsExist(Register.RG_BUTTON_NEGATIVEDEPOSITORY)){
                RegisterHelper.clickNegativePository(appiumUtil,Register.RG_BUTTON_NEGATIVEDEPOSITORY);
                RegisterHelper.clickConfirmKnow(appiumUtil,Register.RG_BUTTON_CONFIRMKNOW);
            }
            if(result[0].equals(data.get("EXPECT"))){
                actual ="Pass";
            }else {
                actual ="Fail";
            }
            /**写入数据**/
            InputExcelResult.inputExcelResult("LogIn","005",result[0],actual,rownumber);
            rownumber =rownumber+1;
            if(result[0].equals("Pass")){
                /**实名**/
                GetSQLHelper.updateRealName(data.get("REALNAME"),data.get("MOBILE"),data.get("PERSONID"));
                /**  充值**/
                GetSQLHelper.recharge(data.get("AMOUNT"),data.get("MOBILE"));

                /**绑定银行卡**/
                GetSQLHelper.bindingBankCard(data.get("MOBILE"),data.get("ACCOUNTNO"),data.get("REALNAME"),data.get("PERSONID"));

                /**绑定提现银行卡**/
                GetSQLHelper.withdrawBankcard(data.get("MOBILE"),data.get("ACCOUNTNO"), data.get("REALNAME"),data.get("PERSONID"));

                /** 更新提现金额 **/
                GetSQLHelper.updateCashoutAmount(data.get("MOBILE"),data.get("CASHOUTAMOUNT"));

                /**设置支付密码**/
                SetPayPassWordMethod.setPayPassword(appiumUtil,data.get("PAYPASSWORD"),data.get("PAYPASSWORDCONFIRM"));


            }
            /**退出 **/
            LogoutMethod.logoutMethod(appiumUtil);
        }
    }
}
