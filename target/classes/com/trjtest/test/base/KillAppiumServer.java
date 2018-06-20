package com.trjtest.test.base;

import com.trjtest.test.utils.CmdUtil;
import org.apache.log4j.Logger;

/**
 * Created by gjw on 2017/8/23.
 */
public class KillAppiumServer {
    public static String cmd = "ps aux|grep session-override | awk '{print $2}'|xargs kill -9";
    public static Logger logger = Logger.getLogger(KillAppiumServer.class);

    public static void killAppiumServer() {
        CmdUtil.run(cmd);
        logger.info("kill Appium Server!!");
    }
}
