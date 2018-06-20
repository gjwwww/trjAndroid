package com.trjtest.test.pagesfunction;

import com.trjtest.test.GetDataFormftp.GetDataFromFtp;
import com.trjtest.test.pages.GetSQL;
import com.trjtest.test.utils.Propertiesutil;
import io.appium.java_client.AppiumDriver;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.openqa.selenium.Cookie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by gjw on 17/6/27.
 */
public class BaseFunction {

    /**
     * 使用appium登录后的cookieStore
     **/
    public static CookieStore setCookieStore(AppiumDriver driver) {
        CookieStore cookieStore = new BasicCookieStore();
        Set<Cookie> cookies = driver.manage().getCookies();
        Iterator<Cookie> itr = cookies.iterator();
        while (itr.hasNext()) {
            Cookie cookie = itr.next();
            BasicClientCookie bcco = new BasicClientCookie(cookie.getName(), cookie.getValue());
            bcco.setDomain(cookie.getDomain());
            bcco.setPath(cookie.getPath());
            cookieStore.addCookie(bcco);
        }
        return cookieStore;
    }

    /**
     * 获取接口地址
     **/
    public static String geturl(String environment, String methodurl) {
        String url;
        if (environment.equals("测试")) {
            url = Propertiesutil.getTestData("res/properties/appconfig.properties", "appdomaintest")
                    + Propertiesutil.getTestData("res/properties/appconfig.properties", methodurl);
        } else if (environment.equals("运维")) {
            url = Propertiesutil.getTestData("res/properties/appconfig.properties", "appdomainyw")
                    + Propertiesutil.getTestData("res/properties/appconfig.properties", methodurl);
        } else {
            url = Propertiesutil.getTestData("res/properties/appconfig.properties", "appdomaintest")
                    + Propertiesutil.getTestData("res/properties/appconfig.properties", methodurl);
        }
        return url;
    }

    /**
     * 获取WAP接口地址
     **/
    public static String getwapurl(String environment, String methodurl) {
        String url;
        if (environment.equals("测试")) {
            url = Propertiesutil.getTestData("res/properties/wapconfig.properties", "wapdomaintest")
                    + Propertiesutil.getTestData("res/properties/wapconfig.properties", methodurl);
        } else if (environment.equals("运维")) {
            url = Propertiesutil.getTestData("res/properties/wapconfig.properties", "wapdomainyw")
                    + Propertiesutil.getTestData("res/properties/wapconfig.properties", methodurl);
        } else {
            url = Propertiesutil.getTestData("res/properties/wapconfig.properties", "appdomaintest")
                    + Propertiesutil.getTestData("res/properties/wapconfig.properties", methodurl);
        }
        return url;
    }

    /**
     * 获取所需的domain值
     **/
    public static String getdomain(String enviroment, String aftermain) {
        String domain;
        if (enviroment.equals("测试")) {
            if (aftermain.equals("App")) {
                domain = Propertiesutil.getTestData("res/properties/appconfig.properties", "appdomaintest");
            } else {
                domain = Propertiesutil.getTestData("res/properties/appconfig.properties", "wapdomaintest");
            }
        } else {
            if (aftermain.equals("App")) {
                domain = Propertiesutil.getTestData("res/properties/appconfig.properties", "appdomainyw");
            } else {
                domain = Propertiesutil.getTestData("res/properties/appconfig.properties", "wapdomainyw");
            }
        }
        return domain;
    }

    /**
     * 设置数据库参数
     **/
    public static void setSql(String enviroment) {
        if (enviroment.equals("测试")) {
            GetSQL getSQL = new GetSQL();
            getSQL.setName(Propertiesutil.getTestData("res/properties/sqlconfig.properties", "testname"));
            getSQL.setSqlpassword(Propertiesutil.getTestData("res/properties/sqlconfig.properties", "testsqlpassword"));
            getSQL.setUrl(Propertiesutil.getTestData("res/properties/sqlconfig.properties", "testurl"));
            getSQL.setUser(Propertiesutil.getTestData("res/properties/sqlconfig.properties", "testuser"));
        } else {
            GetSQL getSQL = new GetSQL();
            getSQL.setName(Propertiesutil.getTestData("res/properties/sqlconfig.properties", "ywname"));
            getSQL.setSqlpassword(Propertiesutil.getTestData("res/properties/sqlconfig.properties", "ywsqlpassword"));
            getSQL.setUrl(Propertiesutil.getTestData("res/properties/sqlconfig.properties", "ywurl"));
            getSQL.setUser(Propertiesutil.getTestData("res/properties/sqlconfig.properties", "ywuser"));
        }
    }


    /**
     * 从ftp中获取测试数据
     **/
    public static void getDataFromFtp(String excelName) {
        try {
            GetDataFromFtp getDataFromFtp = new GetDataFromFtp("192.168.0.19", 21, "guojiawei", "guojiawei");
            /**从ftp中导入Enviroment.txt**/
            getDataFromFtp.downFile("Enviroment", "Enviroment.txt", new FileOutputStream(new File(System.getProperty("user.dir") + "/Enviroment/Enviroment.txt")));

            /**去读Enrieomnent.txt中的环境变量值**/
            String enviroment = getDataFromFtp.getTxtdata(System.getProperty("user.dir") + "/Enviroment/Enviroment.txt");

            if (enviroment.equals("test")) {
                getDataFromFtp.downFtpList("/data/testdata", "/data/",excelName);
                getDataFromFtp.downFile("/app/android/test", "TrjAndroid.apk", new FileOutputStream(System.getProperty("user.dir") + "/res/app/android/TrjAndroid.apk"));
            } else {
                getDataFromFtp.downFtpList("/data/ywdata", "/data/",excelName);
                getDataFromFtp.downFile("/app/android/yw", "TrjAndroid.apk", new FileOutputStream(System.getProperty("user.dir") + "/res/app/android/TrjAndroid.apk"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int  setInvocation(XSSFRichTextString moudule , String actual){
        int i = GetExcelData.getTestsNum(moudule,actual);
        return i;
    }

    /**修改测试数据中的手机号**/
    public static String modifyMobile(){
        String mobile ;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dataString = formatter.format(date);
        mobile = "13"+dataString.substring(3,12);
        return mobile;
    }

    /**修改测试数据中的身份证**/
    public static String modifyPersonId(){
        String persionid;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dataString = formatter.format(date);

        persionid = "3306811992"+dataString.substring(4,12);
        return persionid;
    }
}
