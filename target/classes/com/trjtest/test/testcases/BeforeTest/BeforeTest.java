package com.trjtest.test.testcases.BeforeTest;

import com.trjtest.test.pagesfunction.BaseFunction;
import org.testng.annotations.Test;

/**
 * Created by gjw on 17/7/10.
 */
public class BeforeTest {
    @Test
    public void setData() {
        /**获取测试数据**/
        BaseFunction.getDataFromFtp();
    }
}
