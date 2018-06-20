package com.trjtest.test.pagesfunction;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by gjw on 17/3/9.
 */
public class GetExcelData {

    /**
     * 获取某模块下通过用例的总数
     *  @param moduleName 代表所有的模块名字
     * @param actual     代表实际用例的运行结果状态
     **/
    public static int getTestsNum(XSSFRichTextString moduleName, String actual) {
        /**用来获actual值的用例数**/
        int num = 0;

        /**用来存储表中的列数**/
        int col = 0;

        /**用来存储实际用例结果的列数**/
        int actualColum = 0;

        /**用例存储excel所有的行数**/
        int rown = 0;

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
        HSSFSheet sheet = null;
        if (wb != null) {
            sheet = wb.getSheetAt(0);
        }
        HSSFRow row = null;
        if (sheet != null) {
            row = sheet.getRow(0);
        }
        if (row != null) {
            col = row.getPhysicalNumberOfCells();//获取列数
        }
        if (sheet != null) {
            rown = sheet.getPhysicalNumberOfRows();//获取行数
        }

        /**获取actual的列数**/
        for (int j = 0; j < col; j++) {
            //String cellvalue =row.getCell(i).getRichStringCellValue().getString();
            if ("ACTUAL".equals(row.getCell(j).getRichStringCellValue().getString())) {
                actualColum = j;
            }
        }

        /**获取每行中actual的值**/
        for (int m = 1; m < rown; m++) {
            if (sheet != null && sheet.getRow(m).getCell(actualColum) != null) {

                sheet.getRow(m).getCell(actualColum).setCellType(Cell.CELL_TYPE_STRING);

                if (sheet.getRow(m).getCell(actualColum).getRichStringCellValue().toString().equals(actual)) {
                    num++;
                }
            }
        }
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**如果num =0,表示actual 即不是Pass也不是Fail，即表示actual为All，则 num 应为所有行数－1**/
        if(num == 0){
            if(actual.equals("Pass")||actual.equals("Fail")){

            }else {
                num = rown - 1;
            }
        }

        return num;
    }




    /**获取某模块所有的列数,行数
     * @param moduleName 表示模块名
     * @param roworcol 表示所需要的是行数还是列数**/
    public static int getNum (String moduleName,String roworcol){

        /**存储行数或者列数**/
        int roworcolNum = 0;
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
        HSSFSheet sheet = null;
        if (wb != null) {
            sheet = wb.getSheetAt(0);
        }
        HSSFRow row = null;
        if (sheet != null) {
            row = sheet.getRow(0);
        }

        /**判断是获取行数还是列数**/
        if(roworcol.equals("row"))
        {
            roworcolNum =sheet.getPhysicalNumberOfRows();//获取行数
        }else if(roworcol.equals("col")){
            roworcolNum =row.getPhysicalNumberOfCells();//获取列数
        }

        return  roworcolNum;
    }

    /**获取某行的数据
     * @param moduleName 表示模块名
     * @param rowNum  索取行数**/
    public static String[] getRowValues(String moduleName,int rowNum){
        /**获取列表的列数**/
        int col =GetExcelData.getNum(moduleName,"col");
        String[] rowvalue =new String[col];
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
        HSSFSheet sheet = null;
        if (wb != null) {
            sheet = wb.getSheetAt(0);
        }
        HSSFRow row = null;
        if (sheet != null) {
            row = sheet.getRow(0);
        }
        for (int i =0 ;i<col;i++){
            if (sheet.getRow(rowNum).getCell(i)!= null){
                /**设置Cell的类型，否侧报错**/
                sheet.getRow(rowNum).getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                rowvalue[i] = sheet.getRow(rowNum).getCell(i).getRichStringCellValue().toString();
            }
        }
        return rowvalue;
    }
}
