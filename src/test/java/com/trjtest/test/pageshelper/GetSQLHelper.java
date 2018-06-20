package com.trjtest.test.pageshelper;

import com.trjtest.test.pages.GetSQL;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjw on 17/3/6.
 * 连接数据库获取验证码
 */
public class GetSQLHelper {
    public static Logger logger = Logger.getLogger(GetSQLHelper.class);

    public static final String url = "jdbc:mysql://192.168.10.80";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String sqlpassword = "tourongjia123!";
    static String sql = null;
    static GetSQL db1 = null;
    static ResultSet ret = null;
    public static Connection conn = null;
    public static PreparedStatement pst = null;


    /**
     * 获取验证码
     **/
    public String getCode() {
        sql = "SELECT code from tourongjia.fi_mobile_validate_code where id =(SELECT max(id) from tourongjia.fi_mobile_validate_code);";//获取最新的验证码
        db1 = new GetSQL(sql);
        String code = null;
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果
            while (ret.next()) {
                code = ret.getString(1);
            }
            ret.close();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 判断手机号是否注册成功
     **/
    public static String getRegisterResult(String mobile) {
        sql = "SELECT mobile from tourongjia.fi_user where uid=(SELECT max(uid) from tourongjia.fi_user);";//获取最新注册的手机号
        db1 = new GetSQL(sql);
        String result = null;

        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果
            while (ret.next()) {
                result = ret.getString(1);
            }
            ret.close();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result.equals(mobile)) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    /**
     * 实名
     **/
    public static void updateRealName(String name, String mobile, String personid) {

        // sql ="UPDATE fi_user SET is_xia_recharge = '1',real_name ="+"" '你才啊',is_id_auth = '1',person_id = '140781198904301871' \n" + "where mobile = '13700006002';";//获取最新的验证码
        //UPDATE fi_user t SET t.`is_xia_recharge`='1',t.`real_name`='顾才人',t.`is_id_auth`='1',t.`person_id`='140781198904301871'
        //WHERE t.`mobile`='15900001172';
        //sql = "UPDATE tourongjia.fi_user set is_xia_recharge = '1',real_name ='"+name+"',is_id_auth = '1',person_id = '"+personid+"' where mobile = '"+mobile+"';";
        //sql = "UPDATE tourongjia.fi_user set is_xia_recharge = \'1\' where mobile = \'13709090110\';";

        sql = "update tourongjia.fi_user set is_xia_recharge = ? ,real_name = ? ,is_id_auth = ? ,person_id = ? where mobile = ? ;";
        Object[] objects = new Object[]{"1", name, "1", personid, mobile};

        doSQl(sql, objects);
        logger.info("执行实名sql");
    }


    /**
     * 充值
     **/
    public static void recharge(String amount, String mobile) {
        //UPDATE fi_user_account t SET t.amount='100000' WHERE t.uid IN (SELECT uid FROM fi_user WHERE mobile='15900001172');
        sql = "update tourongjia.fi_user_account set amount = ? where uid in(select uid from tourongjia.fi_user where  mobile = ?);";
        Object[] objects = new Object[]{amount, mobile};
        doSQl(sql, objects);
        logger.info("执行充值sql");

    }

    /**
     * 执行SQL
     **/
    public static void doSQl(String sql, Object[] objects) {
        try {
            db1 = new GetSQL(sql);
            for (int i = 0; i < objects.length; i++) {
                db1.pst.setObject(i + 1, objects[i]);
            }
            db1.pst.execute();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 执行SQL
     **/
    public static void doSQl2(String sql, Object[] objects) {
        try {
            db1 = new GetSQL(sql);
            for (int i = 0; i < objects.length; i++) {
                db1.pst.setObject(i, objects[i]);
            }
            db1.pst.execute();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取账户的UID
     **/
    public static int getUID(String mobile) {
        int uid;
        sql = "select uid from tourongjia.fi_user where mobile = ?;";
        uid = doSQLGetResult(sql, mobile);
        return uid;

    }

    /**
     * 获取fi_fund_account中的recharge_bank_id**
     */

    public static int getRechargeBankId() {
        int recharge_bank_id = 0;
        sql = "select recharge_bank_id from tourongjia.fi_fund_account where id = (select max(id) from tourongjia.fi_fund_account);";
        db1 = new GetSQL(sql);
        try {
            ret = db1.pst.executeQuery();
            while (ret.next()) {
                recharge_bank_id = ret.getInt(1);
            }
            db1.close();
            db1.pst.close();
            ret.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recharge_bank_id;
    }

    /**
     * 执行语句，获取结果INT
     **/
    public static int doSQLGetResult(String sql, Object object) {
        int result = 0;
        try {
            db1 = new GetSQL(sql);
            db1.pst.setObject(1, object);

            ret = db1.pst.executeQuery();
            while (ret.next()) {
                result = ret.getInt(1);
            }
            db1.close();
            db1.pst.close();
            ret.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行语句，获取结果String
     **/
    public static String doSQLGetResult2(String sql, Object object) {
        String result = null;
        try {
            db1 = new GetSQL(sql);
            db1.pst.setObject(1, object);

            ret = db1.pst.executeQuery();
            while (ret.next()) {
                result = ret.getString(1);
            }
            db1.close();
            db1.pst.close();
            ret.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行语句，获取结果List
     **/
    public static List doSQLGetResultList(String sql, Object object) {
        List result = new ArrayList();
        try {
            db1 = new GetSQL(sql);
            db1.pst.setObject(1, object);

            ret = db1.pst.executeQuery();
            while (ret.next()) {
                result.add(ret.getString(1));
            }
            db1.close();
            db1.pst.close();
            ret.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 绑定银行卡
     *
     * @param mobile     : 手机号
     * @param account_no :银行卡号
     * @param real_name  ;名字
     * @param person_id  ：身份证
     **/
    public static void bindingBankCard(String mobile, String account_no, String real_name, String person_id) {
//        INSERT INTO `fi_recharge_bank_no` ( `uid`, `account_no`, `channel`, `acount_name`, `bank`, `bank_name`, `sub_bank`, `sub_bank_id`, `bank_province`,
// `bank_city`, `is_active`, `is_init`, `is_default`, `real_name`, `person_id`, `mobile`, `is_bankck`, `is_ld_reg`, `ctime`, `mtime`,
// `cashout_amount`, `version`, `freeze_cashout_amount`)
//        VALUES('999970506','6214850212331638','xianxia','6214850212331638','CMB','招商银行','招商银行股份有限公司杭州城西支行',
//                '0','0','0','1','0','0','顾才人','140781198904301871',NULL,'1','0','1462266848','1485344195','200','43','0');

        String sql2 = "insert into tourongjia.fi_recharge_bank_no (uid,account_no,channel,acount_name,bank,bank_name,sub_bank,sub_bank_id,bank_province,bank_city,is_active,is_init,is_default,real_name, person_id,is_bankck,is_ld_reg,ctime,mtime,cashout_amount,version,freeze_cashout_amount) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        int uid = getUID(mobile);
        Object[] objects = new Object[]{uid, account_no, "xianxia", account_no, "CMB", "招商银行",
                "招商银行股份有限公司杭州城西支行", "0", "0", "0", "1", "0", "0", real_name, person_id, "1", "0", "1462266848",
                "1485344195", "200", "43", "0"};
        doSQl(sql2, objects);
    }

    /**
     *  提现卡绑定
     *
     * @param mobile     :手机号
     * @param account_no :银行卡号
     * @param real_name  :名字
     * @param person_id  :省份证
     **/
    public static void withdrawBankcard(String mobile, String account_no, String real_name, String person_id) {

        String sql2 = "insert into tourongjia.fi_fund_account (uid,account_no,channel,acount_name,bank,bank_name,sub_bank,sub_bank_id,bank_province,bank_city,is_active,is_init,is_default,real_name, person_id,is_bankck,ctime,mtime,recharge_bank_id) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        int uid = getUID(mobile);
        int recharge_bank_id = getRechargeBankId() + 1;
        Object[] objects = {uid, account_no, "xianxia", account_no, "CMB", "招商银行", "招商银行股份有限公司杭州城西支行", "0", "0", "0", "1", "0", "0", real_name, person_id, "1", "1462266848", "1485344195", recharge_bank_id};
        doSQl(sql2, objects);

        logger.info("执行提现卡sql");
    }

    /**
     * 提现金额
     *
     * @param mobile         :手机号
     * @param cashout_amount : 可提现的金额
     **/
    public static void updateCashoutAmount(String mobile, String cashout_amount) {
        String sql2 = "update tourongjia.fi_user_cashout_tamount set cashout_amount = ? where uid =?";
        int uid = getUID(mobile);
        Object[] objects = new Object[]{cashout_amount, uid};
        doSQl(sql2, objects);

        logger.info("执行更新提现金额sql");
    }

    /**
     * 判断用户是否开通存管银行
     **/
    public static int getUserOpenOrNotTube(String mobile) {
        int status = 1;
        int uid = getUID(mobile);
        String sql2 = "select status from tourongjia.fi_user_escrow where uid =?;";
        status = doSQLGetResult(sql2, uid);
        return status;
    }

    /**
     * 判断是否实名
     **/
    public static String getRralName(String mobile) {
        String realname = null;
        String sql2 = "select real_name from tourongjia.fi_user where mobile = ?;";
        realname = doSQLGetResult2(sql2, mobile);
        return realname;
    }

    /**
     * 判断用户是否设置支付密码
     **/
    public static int getUserSetOrNotPayPassword(String mobile) {
        int is_paypwd_mobile_set = 0;
        String sql2 = "select is_paypwd_mobile_set from tourongjia.fi_user where mobile = ?;";
        is_paypwd_mobile_set = doSQLGetResult(sql2, mobile);
        return is_paypwd_mobile_set;
    }

    /**
     * 获得产品的开标状态
     **/
    public static int getProductStutas(String productname) {
        int bid_status = 0;
        String sql2 = "select bid_status from tourongjia.fi_prj where prj_name = ?;";
        bid_status = doSQLGetResult(sql2, productname);
        return bid_status;
    }

    /**
     * 获取集合标的开标状态
     **/
    public static int getBidProductStatus(String productname) {
        int bid_status = 0;
        String sql2 = "select bid_status from tourongjia.fi_prj_collection where prj_name = ?;";
        bid_status = doSQLGetResult(sql2, productname);
        return bid_status;
    }

    /**
     * 获得产品的ID
     **/
    public static int getProductId(String productname) {
        int id = 0;
        String sql2 = "select id from tourongjia.fi_prj where prj_name = ?;";
        id = doSQLGetResult(sql2, productname);
        return id;

    }

    /**
     * 获得购买产品的用户
     **/
    public static List getProdctUser(int prj_id) {
        List uids = new ArrayList();
        String sql2 = "select uid from tourongjia.fi_prj_order where prj_id = ?";
        uids = doSQLGetResultList(sql2, prj_id);
        return uids;
    }

    /**
     * 获得产品是否为新课标结果
     **/
    public static int getProductIsNew(String productname) {
        int is_new = 0;
        String sql2 = "select is_new from tourongjia.fi_prj where prj_name = ?;";
        is_new = doSQLGetResult(sql2, productname);
        return is_new;
    }

    /**
     * 获得用户是否为新客
     **/
    public static int getUserIsNewBie(String mobile) {
        int is_newbie = 0;
        String sql2 = "select is_newbie from tourongjia.fi_user where mobile = ?;";
        is_newbie = doSQLGetResult(sql2, mobile);
        return is_newbie;
    }

    /**
     * 获得产品的起投金额
     **/
    public static String getProductMinAmount(String productname) {
        String min_bid_amount = null;
        String sql2 = "select min_bid_amount from tourongjia.fi_prj where prj_name = ?;";
        min_bid_amount = doSQLGetResult2(sql2, productname);
        return min_bid_amount;
    }

    /**
     * 获取集合标产品的起投金额
     **/
    public static String getBidproductMinAmount(String productname) {
        String min_bid_amount = null;
        String sql2 = "select min_bid_amount from tourongjia.fi_prj_collection where prj_name = ?;";
        min_bid_amount = doSQLGetResult2(sql2, productname);
        return min_bid_amount;
    }

    /**
     * 获得用户的可用金额
     **/
    public static String getUserAmount(String mobile) {
        String amount = null;
        int uid = getUID(mobile);
        String sql2 = "select amount from tourongjia.fi_user_account where uid = ?;";
        amount = doSQLGetResult2(sql2, uid);
        return amount;
    }

    /**
     * 获取产品的剩余额度
     **/
    public static String getProductRemianingAmount(String prductname) {
        String remaining_amount = null;
        String sql2 = "select remaining_amount from tourongjia.fi_prj where prj_name =?;";
        remaining_amount = doSQLGetResult2(sql2, prductname);
        return remaining_amount;
    }

    /**
     * 获取集合产品的剩余额度
     **/
    public static String getBidProductRemainingAmount(String productname) {
        String remaining_amount = null;
        String sql2 = "select remaining_amount from tourongjia.fi_prj_collection where prj_name =?;";
        remaining_amount = doSQLGetResult2(sql2, productname);
        return remaining_amount;
    }

    /**
     * 获取用户可提现的金额
     **/
    public static String getUserCashoutAmount(String mobile) {
        String cashout_amount = null;
        int uid = getUID(mobile);
        String sql2 = "select cashout_amount from tourongjia.fi_user_cashout_tamount where uid = ?;";
        cashout_amount = doSQLGetResult2(sql2, uid);
        return cashout_amount;
    }

    /**
     * 获取用户推荐人数
     **/
    public static int getUserRecommendCount(String mobile) {
        int count = 0;
        int uid = getUID(mobile);
        String sql2 = "select invite_person from tourongjia.fi_user_ext where uid = ?;";
        count = doSQLGetResult(sql2, uid);
        return count;
    }

    /**
     * 获取用户理财金可提取金额
     **/
    public static String getUserStayFinancialCash(String mobile) {
        String bonus_stay_total = null;
        int uid = getUID(mobile);
        String sql2 = "select bonus_stay_total from tourongjia.fi_tyj_bonus_sum where uid = ?;";
        bonus_stay_total = doSQLGetResult2(sql2, uid);
        return bonus_stay_total;
    }

    /**
     * 获取用户理财金已拿到的收益
     **/
    public static String getUserGetFinancialCash(String mobile) {
        String bonus_in_total = null;
        int uid = getUID(mobile);
        String sql2 = "select bonus_in_total from tourongjia.fi_tyj_bonus_sum where uid = ?;";
        bonus_in_total = doSQLGetResult2(sql2, uid);
        return bonus_in_total;
    }

    /**
     * 获取短信验证码
     **/
    public static String getUserMobileCode(String mobile) {
        sql = "SELECT code from tourongjia.fi_mobile_validate_code where id =(SELECT max(id) from tourongjia.fi_mobile_validate_code);";//获取最新的验证码
        db1 = new GetSQL(sql);
        String code = null;
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果
            while (ret.next()) {
                code = ret.getString(1);
            }
            ret.close();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;

    }

    /**
     * 判断产品是否为集合标
     **/
    public static int getPrjIsCollection(String prjname) {
        int is_collection;
        String sql2 = "select is_collection from tourongjia.fi_prj_collection where prj_name = ?;";
        is_collection = doSQLGetResult(sql2, prjname);
        return is_collection;
    }

    /**
     * 判断用户是否已经开通一键投资
     **/
    public static int getUserIsAutoBid(String mobile) {
        int is_auto;
        int uid = getUID(mobile);
        String sql2 = "select is_auto from tourongjia.fi_user_auto_bid where uid = ?; ";
        is_auto = doSQLGetResult(sql2, uid);
        return is_auto;

    }
}
