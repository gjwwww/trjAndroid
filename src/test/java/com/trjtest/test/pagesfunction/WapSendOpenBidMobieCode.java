package com.trjtest.test.pagesfunction;

import com.google.gson.GsonBuilder;
import com.trjtest.test.WapBean.ResponseBean;
import com.trjtest.test.WapBean.SendOpenAutoBidMobileCodeBean;
import com.trjtest.test.http.HttpClientUtil;
import com.trjtest.test.http.ResponseUtil;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by gjw on 17/6/27.
 */
public class WapSendOpenBidMobieCode {
    public WapObject sendOpenAutoBidMobileCode(String enviroment,CloseableHttpClient httpClient, CookieStore cookieStore){
        /**发送开通一键投资短信验证码的接口地址**/
        //String sendOpenAutoBidMobileCodeUrl = Propertiesutil.getTestData("res/properties/wapconfig.properties","SendOpenBidMobileCodeUrl");

        String sendOpenAutoBidMobileCodeUrl = BaseFunction.getwapurl(enviroment,"SendOpenBidMobileCodeUrl");
        /**发送开通一键投资短信验证码的接口参数**/
        Map<String ,String > sendCodeData = new HashMap<>();

        /**请求接口**/
        httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        CloseableHttpResponse response = HttpClientUtil.doPost(sendOpenAutoBidMobileCodeUrl,sendCodeData,httpClient,cookieStore);


        /**获取接口数据**/
        ResponseBean getCodeBean = ResponseUtil.setResponseBean(response);
        SendOpenAutoBidMobileCodeBean sendOpenAutoBidMobileCodeBean = new GsonBuilder().create().fromJson(getCodeBean.getBody(),SendOpenAutoBidMobileCodeBean.class);

        WapObject wapObject = new WapObject();
        wapObject.setBoolen(sendOpenAutoBidMobileCodeBean.getBoolen());
        wapObject.setMessage(sendOpenAutoBidMobileCodeBean.getMessage());
        return wapObject;
    }
}
