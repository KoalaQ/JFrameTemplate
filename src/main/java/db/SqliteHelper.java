package db;

import db.dto.AbsDto;
import db.dto.CommonConfig;
import org.apache.log4j.Logger;
import util.LogUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-10-12<br>
 * <br>
 */
public class SqliteHelper {
    private  static Logger logger= LogUtil.getLogger(SqliteHelper.class);
    private static Connection conn=null;
    static {
        try {
            initConnection();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
    private static void initConnection() throws ClassNotFoundException, SQLException {
        conn=null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn= DriverManager.getConnection("jdbc:sqlite:config.db");
        } catch (ClassNotFoundException e) {
            logger.error("sqlite 驱动异常",e);
            throw e;
        } catch (SQLException e) {
            logger.error("sqlite获取连接异常",e);
            throw e;
        }
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(conn==null || conn.isClosed()){
            logger.info("配置信息链接异常，尝试重新链接");
            initConnection();
        }
        return conn;
    }
    private static boolean testHasTable(AbsDto absDto){
        String sql = "select count(1) from " + absDto.getTableName();
        try {
            executeSql(sql);
        } catch (SQLException throwables) {
            logger.error("表不存在："+absDto.getTableName(),throwables);
            return false;
        }
        return true;
    }

    public static void  initTable(AbsDto absDto) throws SQLException {
        if(!testHasTable(absDto)){
            logger.info("创建表："+absDto.getTableName() );
            executeSql(absDto.getCreateTableSql());
        }
    }

    public static int executeSql(String sql) throws SQLException {
        Statement statement=conn.createStatement();
        return statement.executeUpdate(sql);
    }
    public static List<Map<String,Object>> getList(String sql) throws SQLException {
        List<Map<String,Object>> retMapList=new ArrayList<Map<String,Object>>();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        try {
            ResultSet rs=pstmt.executeQuery();
            ResultSetMetaData rsm =rs.getMetaData(); //获得列集
            int col = rsm.getColumnCount();
            while (rs.next()){
                Map<String,Object> map=new HashMap<String,Object>();
                for (int i = 1; i <=col ; i++) {
                    String columnName=rsm.getColumnLabel(i);
                    map.put(columnName,rs.getString(columnName));
                }
                retMapList.add(map);
            }
        } finally {
            pstmt.close();
        }
        return  retMapList;
    }

    public static void main(String[] args) throws Exception {
        System.out.println( getConnection());
        CommonConfig commonConfig=new CommonConfig();
        initTable(commonConfig);

    }


    /**
     * 初始化sqllite，如果没有表则创建表
     * @throws Exception
     */
    public static void initDb()throws Exception{
//        Connection connection= getConnection();
//        Statement statement=connection.createStatement();
//
//        RecmentsDao recmentsDao=DaoFactory.getRecmentsDao();
//        if(recmentsDao.needCreateTable()){
//            logger.info("重新构建："+recmentsDao.getTableName());
//            statement.execute(recmentsDao.getCreateTableSql());
//            logger.info("重新构建："+recmentsDao.getTableName()+"完成");
//        }
//
//        SerialConfigDao serialConfigDao=DaoFactory.getSerialConfigDao();
//        if(serialConfigDao.needCreateTable()){
//            logger.info("重新构建："+serialConfigDao.getTableName());
//            statement.execute(serialConfigDao.getCreateTableSql());
//            logger.info("重新构建："+serialConfigDao.getTableName()+"完成");
//        }
//        statement.close();
    }
}
