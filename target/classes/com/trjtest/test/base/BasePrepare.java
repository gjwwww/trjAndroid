package com.trjtest.test.base;

import com.chrome.handler.ChromedriverHandler;
import com.trjtest.test.pagesfunction.BaseFunction;
import com.trjtest.test.utils.AppiumUtil;
import com.trjtest.test.utils.ExcelDataProvider;
import com.trjtest.test.utils.LogConfiguration;
import com.trjtest.test.utils.SelectDriver;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by gjw on 17/2/23.
 * 启动和结束测试，以及数据提供者，提供数据
 */
public class BasePrepare {

    protected AppiumDriver driver;
    protected AppiumUtil appiumUtil;
    public static Logger logger = Logger.getLogger(BasePrepare.class);
    protected String platformName;
    protected String appFilePath;
    protected String appPackage;
    protected int elementTimeOut;
    public static int rownumber = 1;

    protected String nodePath = "/usr/local/bin/node";
    protected String appiumPath = "/Applications/Appium.app/Contents/Resources/app/node_modules/appium";


    @BeforeSuite
    public void initServer() {
        List<String> list = new ArrayList<String>();
        list.add("/usr/local/bin/node /Applications/Appium.app/Contents/Resources/app/node_modules/appium -a 127.0.0.1 -p 4723 -bp 4724  --session-override --chromedriver-port  4600");
        list.add("/usr/local/bin/node /Applications/Appium.app/Contents/Resources/app/node_modules/appium -a 127.0.0.1 -p 4725 -bp 4726  --session-override --chromedriver-port  4500");
        for (String cmd : list) {
            StartAppiumServer startAppiumServer = new StartAppiumServer(cmd);
            startAppiumServer.start();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @BeforeClass
    @Parameters({"port", "udid", "platformVersion", "deviceName"})
    public void initTest(ITestContext context, String port, String udid, String platformVersion, String deviceName) {
        //使log4j的配置生效，以便输出日志
        LogConfiguration.initLog2(this.getClass().getSimpleName(), rownumber);
        logger.info("正在执行第" + rownumber + "条用例");
        //获取platform,appFilePath,appPackage的值，这个值是从testng的配置文件中获取的
        platformName = context.getCurrentXmlTest().getParameter("platformName");
        appFilePath = context.getCurrentXmlTest().getParameter("appFilePath");
        appPackage = context.getCurrentXmlTest().getParameter("appPackage");
        elementTimeOut = Integer.valueOf(context.getCurrentXmlTest().getParameter("elementTimeOut"));
        appiumUtil = new AppiumUtil();
        Map<String, String> modifyDriver = new HashMap<>();
        modifyDriver.put("udid", udid);
        modifyDriver.put("port", port);
        modifyDriver.put("platformVersion", platformVersion);
        modifyDriver.put("deviceName", deviceName);
        //调用SelectDriver类的selectDriver方法，生成driver对象
        driver = new SelectDriver().selectDriver(context, appiumUtil, modifyDriver);
        ChromedriverHandler.chromeDriverHandlerThread().start();
    }

    @AfterClass
    public void cleanTest() {
        if (driver != null) {
            driver.quit();
        } else {
            Assert.fail("driver没有获得对象，退出操作失败");
        }
        rownumber++;
    }

    @AfterSuite
    public void clenServer() {
        KillAppiumServer.killAppiumServer();
    }

    /**
     * 测试数据提供者－方法
     */
    @DataProvider(name = "testData")
    public Iterator<Object[]> dataFortestMethod() throws IOException {
        String moduleName = null; // 模块的名字
        String caseNum = null; // 用例编号
        String className = this.getClass().getName();
        int dotIndexNum = className.indexOf("."); // 取得第一个.的index
        int underlineIndexNum = className.indexOf("_"); // 取得第一个_的index
        if (dotIndexNum > 0) {
            /**这里的calssName原始值大概是这样的：com.trjtest.test.testcases.LogIn.LogIn_001_loginpassed
             * 那么下面这段代码className.substring(27, className.lastIndexOf("."))是什么意思？substring方法参数有两个
             * 一个开始位置，一个结束位置，27表示这个字符串的第27个位置，这个位置当前字符是l,className.lastIndexOf(".")表示返回这字符串最后一个.所在
             * 的位置，它是23，那么className.substring(27, className.lastIndexOf("."))可以转换成：className.substring(27, 32)，最终取得的值是LogIn，
             * 也就是moduleName的值
             *
             *
             * */
            moduleName = className.substring(27, className.lastIndexOf(".")); // 取到模块的名称
        }
        /**从ftp更新本地的测试数据**/
        BaseFunction.getDataFromFtp(moduleName + ".xls");

        if (underlineIndexNum > 0) {
            //这个分析方法和moduleName的分析方法一样
            caseNum = className.substring(underlineIndexNum + 1, underlineIndexNum + 4); // 取到用例编号
        }
        //将模块名称和用例的编号传给 ExcelDataProvider ，然后进行读取excel数据
        return new ExcelDataProvider(moduleName, caseNum);
    }
}

