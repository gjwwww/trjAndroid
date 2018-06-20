package com.trjtest.test.pagesfunction;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by gjw on 17/3/9.
 */
public class InputExcelResult {


    /**
     * 输入用例的操作结果
     *
     * @param moduleName:模块名
     * @param caseNum              :用例编号即sheet号
     * @param resultValue: 运行用例的结果
     * @param actualValue:用例是否通过
     * @param rownumber            :运行的用例所处的行数
     **/

    public static void inputExcelResult(String moduleName, String caseNum, String resultValue, String actualValue, int rownumber) {
        /**表的列数**/
        int col = 0;

        /**result的列数**/
        int resultColum = 0;

        /**actual的列数**/
        int actualColum = 0;
        FileInputStream fs = null;  //获取d://test.xls
        try {
            fs = new FileInputStream("data/" + moduleName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        POIFSFileSystem ps = null;  //使用POI提供的方法得到excel的信息
        try {
            ps = new POIFSFileSystem(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(ps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheet(caseNum);  //获取到工作表，因为一个excel可能有多个工作表
        HSSFRow row = sheet.getRow(0);
        col = row.getPhysicalNumberOfCells();//获取列数


        /**获取result，actual的列数**/
        for (int i = 0; i < col; i++) {
            //String cellvalue =row.getCell(i).getRichStringCellValue().getString();
            if ("RESULT".equals(row.getCell(i).getRichStringCellValue().getString())) {

                resultColum = i;
            } else if ("ACTUAL".equals(row.getCell(i).getRichStringCellValue().getString())) {
                actualColum = i;
            }
        }


        /**在result，actual下写入数据**/
        FileOutputStream out = null;  //向data/modeule.xls中写数据
        try {
            out = new FileOutputStream("data/" + moduleName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sheet.getRow(rownumber).createCell(resultColum).setCellValue(resultValue);
        //String cellvalue=sheet.getRow(rownumber).getCell(resultColum).getRichStringCellValue().getString();

        sheet.getRow(rownumber).createCell(actualColum).setCellValue(actualValue);


        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void inputExcelResult2(String moduleName, String caseNum, String resultValue, String actualValue, int rownumber, String exctresult) {
        /**表的列数**/
        int col = 0;

        /**result的列数**/
        int resultColum = 0;
        /**actual的列数**/
        int actualColum = 0;
        /**exctresult的列数**/
        int exctresultColum = 0;
        FileInputStream fs = null;  //获取d://test.xls
        try {
            fs = new FileInputStream("data/" + moduleName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        POIFSFileSystem ps = null;  //使用POI提供的方法得到excel的信息
        try {
            ps = new POIFSFileSystem(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(ps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheet(caseNum);  //获取到工作表，因为一个excel可能有多个工作表
        HSSFRow row = sheet.getRow(0);
        col = row.getPhysicalNumberOfCells();//获取列数


        /**获取result，actual的列数**/
        for (int i = 0; i < col; i++) {
            //String cellvalue =row.getCell(i).getRichStringCellValue().getString();
            if ("RESULT".equals(row.getCell(i).getRichStringCellValue().getString())) {

                resultColum = i;
            } else if ("ACTUAL".equals(row.getCell(i).getRichStringCellValue().getString())) {
                actualColum = i;
            } else if ("EXCTRESULT".equals(row.getCell(i).getRichStringCellValue().getString())) {
                exctresultColum = i;
            }
        }

        if (actualValue == null) {
            actualValue = "Fail";
            resultValue = "Fail";
            exctresult = "其他原因执行用例不成功（网络或者其他）";
        }
        /**在result，actual下写入数据**/
        FileOutputStream out = null;  //向data/modeule.xls中写数据
        try {
            out = new FileOutputStream("data/" + moduleName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sheet.getRow(rownumber).createCell(resultColum).setCellValue(resultValue);
        //String cellvalue=sheet.getRow(rownumber).getCell(resultColum).getRichStringCellValue().getString();

        sheet.getRow(rownumber).createCell(actualColum).setCellValue(actualValue);
        sheet.getRow(rownumber).createCell(exctresultColum).setCellValue(exctresult);


        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 修改excel中的手机号，身份证
     **/
    public static void inputModifyMobile(String moduleName, String caseNum, int rownumber) {

        String mobile = BaseFunction.modifyMobile();
        String personid = BaseFunction.modifyPersonId();

        /**表的列数**/
        int col = 0;

        /**mobile的列数**/
        int mobileColum = 0;

        /**备注的列数**/
        int bzColum = 0;

        /**身份证的列数**/
        int idCloum = 0;

        FileInputStream fs = null;  //获取d://test.xls
        try {
            fs = new FileInputStream("data/" + moduleName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        POIFSFileSystem ps = null;  //使用POI提供的方法得到excel的信息
        try {
            ps = new POIFSFileSystem(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(ps);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheet(caseNum);  //获取到工作表，因为一个excel可能有多个工作表
        HSSFRow row = sheet.getRow(0);
        col = row.getPhysicalNumberOfCells();//获取列数


        /**获取mobile的列数**/
        for (int i = 0; i < col; i++) {
            //String cellvalue =row.getCell(i).getRichStringCellValue().getString();
            if ("MOBILE".equals(row.getCell(i).getRichStringCellValue().getString())) {
                mobileColum = i;
            } else if ("备注".equals(row.getCell(i).getRichStringCellValue().getString())) {
                bzColum = i;
            } else if ("PERSONID".equals(row.getCell(i).getRichStringCellValue().getString())) {
                idCloum = i;
            }
        }
        boolean flag;

//        if (moduleName.equals("Register")) {
//            if (sheet.getRow(rownumber).getCell(bzColum).getRichStringCellValue().getString().contains("已注册")) {
//                flag = false;
//            } else {
//                flag = true;
//            }
//        } else {
//            if (sheet.getRow(rownumber).getCell(bzColum).getRichStringCellValue().getString().contains("用户注册")) {
//                flag = true;
//            } else {
//                flag = false;
//            }
//        }
        if (sheet.getRow(rownumber).getCell(bzColum).getRichStringCellValue().getString().contains("修改数据")) {
            flag = true;
        } else {
            flag = false;
        }

        /**在mobile下写入数据**/
        FileOutputStream out = null;  //向data/modeule.xls中写数据
        try {
            out = new FileOutputStream("data/" + moduleName + ".xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (flag) {
            sheet.getRow(rownumber).createCell(mobileColum).setCellValue(mobile);
            if (idCloum != 0) {
                sheet.getRow(rownumber).createCell(idCloum).setCellValue(personid);
            }
        }
        //String cellvalue=sheet.getRow(rownumber).getCell(resultColum).getRichStringCellValue().getString();

        //sheet.getRow(rownumber).createCell(actualColum).setCellValue(actualValue);
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
