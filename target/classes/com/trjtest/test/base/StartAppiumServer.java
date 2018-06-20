package com.trjtest.test.base;

import com.trjtest.test.utils.CmdUtil;
import org.apache.log4j.Logger;

/**
 * Created by gjw on 2017/8/23.
 */
public class StartAppiumServer extends Thread {

    private String command;

    //    public static String cmd1 = "/usr/local/bin/node /Applications/Appium.app/Contents/Resources/app/node_modules/appium -a 127.0.0.1 -p 4723 -bp 4724 --chromedriver-port  4600";
//    public static String cmd2 = "/usr/local/bin/node /Applications/Appium.app/Contents/Resources/app/node_modules/appium -a 127.0.0.1 -p 4725 -bp 4726 --chromedriver-port  4500";
    public StartAppiumServer(String command) {
        this.command = command;
    }

    public static Logger logger = Logger.getLogger(StartAppiumServer.class);

    public void run() {
        logger.info("Start Appium > > > " + command);
        CmdUtil.run(command);

    }
}
