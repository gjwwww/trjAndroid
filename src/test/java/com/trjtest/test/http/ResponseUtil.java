package com.trjtest.test.http;

import com.trjtest.test.WapBean.ResponseBean;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Created by gjw on 17/6/27.
 */
public class ResponseUtil {
    public static Logger log = Logger.getLogger(ResponseUtil.class);

    private static ResponseBean responseBean=null;

    public static ResponseBean setResponseBean(CloseableHttpResponse httpResponse) {

        // 使用响应对象获取响应实体
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null)
            try {
                // 将响应实体转为字符串
                String responseString = EntityUtils.toString(entity, "utf-8");
                String rs = responseString.replace("\r\n", "");

                responseBean = new ResponseBean();
                responseBean.setStatus(httpResponse.getStatusLine().getReasonPhrase());
                responseBean.setStatusCode(Integer.toString(httpResponse.getStatusLine().getStatusCode()));
                responseBean.setBody(rs);

                log.info("\n" + "***************************返回开始**********************************" + "\n"
                        + httpResponse.getStatusLine().getReasonPhrase() + "\n"
                        + Integer.toString(httpResponse.getStatusLine().getStatusCode()) + "\n"  + rs + "\n"
                        + "***************************返回结束**********************************");

                HeaderIterator iterator = httpResponse.headerIterator();
                while (iterator.hasNext()) {
                    log.debug("\t" + iterator.next());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return responseBean;
    }
}
