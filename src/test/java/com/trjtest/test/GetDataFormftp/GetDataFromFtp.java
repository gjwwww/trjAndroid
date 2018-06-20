package com.trjtest.test.GetDataFormftp;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class GetDataFromFtp {
    //ftp对象
    private FTPClient ftp;
    //需要连接到的ftp端的ip
    private String ip;
    //连接端口，默认21
    private int port;
    //要连接到的ftp端的名字
    private String name;
    //要连接到的ftp端的对应得密码
    private String pwd;

    //调用此方法，输入对应得ip，端口，要连接到的ftp端的名字，要连接到的ftp端的对应得密码。连接到ftp对象，并验证登录进入fto
    public GetDataFromFtp(String ip, int port, String name, String pwd) {
        ftp = new FTPClient();
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.pwd = pwd;

        //验证登录
        try {
            ftp.connect(ip, port);
            ftp.login(name, pwd);
            ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlEncoding("UTF-8");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    //获取ftp某一文件（路径）下的文件名字,用于查看文件列表
    public void getFilesName() {
        try {
            //获取ftp里面，“Windows”文件夹里面的文件名字，存入数组中
            FTPFile[] files = ftp.listFiles("/Windows");
            //打印出ftp里面，“Windows”文件夹里面的文件名字
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i].getName());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 下载ftp固定目录下的所有文件
     **/
    public void downFtpList(String ftpdirectory, String localdirectory,String excelName) {
        try {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(ftpdirectory);

            FTPFile[] files = ftp.listFiles(ftpdirectory);
            for (int i = 0; i < files.length; i++) {
                if(files[i].getName().equals(excelName)){
                    File file = new File(System.getProperty("user.dir") + localdirectory + excelName);
                    downFile(ftpdirectory, files[i].getName(), new FileOutputStream(file));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //上传文件
    public void putFile() {
        try {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //将本地的"D:/1.zip"文件上传到ftp的根目录文件夹下面，并重命名为"aaa.zip"
            System.out.println(ftp.storeFile("app/ios/test/touRong.ipa", new FileInputStream(new File("/Users/gjw/Desktop/ios/touRong.ipa"))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 更换ftp目录
     **/
    public void changedirectory(String directory) {
        try {
            ftp.changeWorkingDirectory(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param ftpdirectory:         ftp目录
     * @param filename:ftp目录中存取的文件名
     * @param inputStream:需要上传的文件流
     **/
    public void uploadFile(String ftpdirectory, String filename, FileInputStream inputStream) {
        try {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(ftpdirectory);
            ftp.storeFile(filename, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //上传文件的第二种方法，优化了传输速度
    public void putFile2() {
        try {
            OutputStream os = ftp.storeFileStream("aaa.zip");
            FileInputStream fis = new FileInputStream(new File("D:/1.zip"));

            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //下载文件
    public void getFile() {
        try {
            //将ftp根目录下的"jsoup-1.10.2.jar"文件下载到本地目录文件夹下面，并重命名为"1.jar"
            ftp.retrieveFile("jsoup-1.10.2.jar", new FileOutputStream(new File("D:/1.jar")));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //下载文件的第二种方法，优化了传输速度
    public void getFile2() {
        try {
            InputStream is = ftp.retrieveFileStream("Invest.xls");

            FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir") + "/ftpdata/Invest.xls"));

            byte[] b = new byte[1024];
            int len = 0;
            while ((len = is.read(b)) != -1) {
                fos.write(b, 0, len);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void removeFile() {
        try {
            System.out.println(ftp.deleteFile("Invest.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeCon() {
        if (ftp != null) {
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void downFile(String ftpdirectory, String fileName, FileOutputStream fileOutputStream) {
        try {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(ftpdirectory);
            ftp.retrieveFile(fileName, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        GetDataFromFtp m = new GetDataFromFtp("192.168.0.19", 21, "guojiawei", "guojiawei");

        // m.putFile();
        // m.putFile2();
        //m.getFile();
        //m.putFile()
        // m.removeFile();
        //m.getFile2();

        try {
            //m.uploadFile("/data/testdata", "Invest.xls", new FileInputStream(new File(System.getProperty("user.dir") + "/data/Invest.xls")));
            //m.downFile("/data/testdata","Invest.xls",new FileOutputStream(new File(System.getProperty("user.dir")+"/ftpdata/testdata/Invest.xls")));
            //m.downFtpList("/data/testdata","/ftpdata/testdata/");
           // m.downFile("Enviroment", "Enviroment.txt", new FileOutputStream(new File(System.getProperty("user.dir") + "/Enviroment/Enviroment.txt")));
            m.putFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.closeCon();
        //getTxtdata(System.getProperty("user.dir")+"/Enviroment/Enviroment.txt");

    }


    /**读取txt文件**/
    public  String  getTxtdata(String filePath){
        String enviroment = null;
        List<String> list = null;
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                enviroment = bufferedReader.readLine();

                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    if (list != null) {
                        list.add(lineTxt);
                    }
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        if (list != null) {
            enviroment = list.get(0);
        }
        //System.out.println(enviroment);
        return enviroment;
    }
}