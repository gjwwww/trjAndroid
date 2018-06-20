package com.trjtest.test.pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by gjw on 17/3/14.
 */
public class GetSQL {
    private static String url = null;
    private static String name = null;
    private static String user = null;
    private static String sqlpassword = null;
    public Connection conn = null;
    public PreparedStatement pst = null;

    public GetSQL() {

    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSqlpassword() {
        return sqlpassword;
    }

    public void setSqlpassword(String sqlpassword) {
        this.sqlpassword = sqlpassword;
    }

    public GetSQL(String sql) {
        try {
            //System.out.println("runs here");
            Class.forName(name); //指定链接类型
            conn = DriverManager.getConnection(url, user, sqlpassword);
            pst = conn.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
