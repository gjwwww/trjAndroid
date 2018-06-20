package com.trjtest.test.plugins.excelReporter;

import com.trjtest.test.plugins.excelReporter.utils.CreateExcelForResult;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

import java.util.List;

/**
 * Created by gjw on 17/2/24.
 */
public class ExcelReporter implements IReporter {
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        CreateExcelForResult.createExcelReport(xmlSuites, suites,outputDirectory);

    }


}