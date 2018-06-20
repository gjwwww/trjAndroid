package com.trjtest.test.http;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gjw on 17/6/27.
 */
public class HttpClientUtil {
    public static Logger logger = Logger.getLogger(HttpClientUtil.class);
    private static Charset s = Consts.UTF_8;
    // 请求实体
    private static UrlEncodedFormEntity entitys = null;

    /**
     * get请求
     * @param url
     * @param paramsMap
     * @param httpclient
     * @param cookieStore
     * @return
     */
    public static CloseableHttpResponse doGet(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient, CookieStore cookieStore) {

        HttpGet httpget = null;
        entitys = getFormEntity(paramsMap);

        try {
            httpget = new HttpGet(url + '?' + EntityUtils.toString(entitys));
            if (cookieStore != null) {
                httpget.setHeader("Cookie", cookieStore.getCookies().get(0).getValue().trim());
            }

            // 执行get请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
            CloseableHttpResponse httpResponse = httpclient.execute(httpget);
            return httpResponse;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * post请求
     * @param url
     * @param paramsMap
     * @param httpclient
     * @param cookieStore
     * @return
     */
    public static CloseableHttpResponse doPost(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient,
                                               CookieStore cookieStore) {

        HttpPost httppost = new HttpPost(url);

        if (cookieStore != null) {
            String cookiesLogin =   cookieStore.getCookies().get(0).getValue().trim();
            System.out.println(cookiesLogin);
            httppost.setHeader("Cookie", cookiesLogin);
        }

        // 请求实体
        entitys = getFormEntity(paramsMap);
        httppost.setEntity(entitys);
        // httppost.setHeader("Content-type","application/json,charset=utf-8");
        // httppost.setHeader("Accept", "application/json");

        // 执行post请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
        try {
            CloseableHttpResponse httpResponse = httpclient.execute(httppost);
            return httpResponse;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            logger.info("接口请求不成功");

        } catch (IOException e) {
            e.printStackTrace();
            logger.info("接口请求不成功");
        }
        return null;
    }

    /**
     * put请求
     * @param url
     * @param paramsMap
     * @param httpclient
     * @param cookieStore
     * @return
     */
    public static CloseableHttpResponse doPut(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient, CookieStore cookieStore) {

        HttpPut httpput = new HttpPut(url);
        if (cookieStore != null) {
            httpput.setHeader("Cookie", "JSESSIONID=" + cookieStore.getCookies().get(0).getValue().trim());
        }

        entitys = getFormEntity(paramsMap);
        httpput.setEntity(entitys);
        // 执行putt请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
        try {
            CloseableHttpResponse httpResponse = httpclient.execute(httpput);
            return httpResponse;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * delete请求
     * @param url
     * @param paramsMap
     * @param httpclient
     * @param cookieStore
     * @return
     */
    public static CloseableHttpResponse doDelete(String url, Map<String, String> paramsMap, CloseableHttpClient httpclient, CookieStore cookieStore) {

        HttpDelete httpdelete  = null;
        entitys = getFormEntity(paramsMap);

        try {
            httpdelete = new HttpDelete(url + '?' + EntityUtils.toString(entitys));
            if (cookieStore != null) {
                httpdelete.setHeader("Cookie", "JSESSIONID=" + cookieStore.getCookies().get(0).getValue().trim());
            }

            // 执行get请求，返回response服务器响应对象, 其中包含了状态信息和服务器返回的数据
            CloseableHttpResponse httpResponse = httpclient.execute(httpdelete);
            return httpResponse;

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 封装一个获取请求实体的方法
     * @param paramsMap
     * @param ucode
     * @return
     */
    public static UrlEncodedFormEntity getFormEntity(Map<String, String> paramsMap, Charset... ucode) {
        Charset f = null;
        if (ucode.equals("")) {
            f = Consts.UTF_8;
        } else {
            f = s;
        }
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        Set<String> keySet = paramsMap.keySet();
        for (String key : keySet) {
            paramsList.add(new BasicNameValuePair(key, paramsMap.get(key)));
        }
        UrlEncodedFormEntity entitys = new UrlEncodedFormEntity(paramsList, f);
        return entitys;
    }
}
