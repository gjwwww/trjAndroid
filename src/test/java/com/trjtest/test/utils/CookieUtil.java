package com.trjtest.test.utils;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by gjw on 17/6/27.
 */
public class CookieUtil {
    private static CookieStore cookieStore = null;

    public static Logger log = Logger.getLogger(CookieUtil.class);


    /*
     * 通过CookieStore储存cookie
     */
    public static CookieStore setCookieStore(HttpResponse httpResponse, String domain) {

        log.info("setCookieStore");
        cookieStore = new BasicCookieStore();
        // PHPSESSION
        StringBuilder sb  = new StringBuilder();
//        sb.append(httpResponse.getFirstHeader("Set-Cookie").getValue());
//        System.out.println("log1=" +sb.toString());
//        sb.delete(0, sb.length());
        Header[] headers = httpResponse.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            Header header = headers[i];
            if (header != null && headers[i].getName().equals("Set-Cookie")) {
                sb.append(header.getValue()).append(";");
            }
        }

//        String PHPSESSION = sb.substring("PHPSESSION=".length() - 1,
//                sb.indexOf(";"));
//        log.debug("PHPSESSION:" + PHPSESSION);

        // 新建一个Cookie
        BasicClientCookie cookie = new BasicClientCookie("PHPSESSION", sb.toString());
        cookie.setVersion(0);
        cookie.setDomain(domain);
        cookie.setPath("/");

        cookieStore.addCookie(cookie);
        return cookieStore;
    }

    public static String getCookieString(HttpResponse httpResponse) {

        log.info("setCookieStore");
//        cookieStore = new BasicCookieStore();
//        // PHPSESSION
        return httpResponse.getFirstHeader("Set-Cookie").getValue();

//        System.out.println(setCookie);
//        String PHPSESSION = setCookie.substring("PHPSESSION=".length()-1,
//                setCookie.indexOf(";"));
//        log.debug("PHPSESSION:" + PHPSESSION);
//
//        // 新建一个Cookie
//        BasicClientCookie cookie = new BasicClientCookie("PHPSESSION", PHPSESSION);
//        cookie.setVersion(0);
//        cookie.setDomain("https://wapescrow.tourongjia.com/");
//        cookie.setPath("/");
//
//        cookieStore.addCookie(cookie);
//        return cookieStore;
    }


    public static void printResponse(HttpResponse httpResponse) {

        // 获取响应消息实体
        HttpEntity entity = httpResponse.getEntity();
        // 响应状态
        log.info("status:" + httpResponse.getStatusLine());
        log.info("headers:");
        HeaderIterator iterator = httpResponse.headerIterator();
        while (iterator.hasNext()) {
            log.info("\t" + iterator.next());
        }
        // 判断响应实体是否为空
        if (entity != null) {
            String responseString = null;
            try {
                responseString = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("response length:" + responseString.length());
            log.info("response content:" + responseString.replace("\r\n", ""));
        }
    }
}
