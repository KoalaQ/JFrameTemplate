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
 * ����˵��: <br>
 * ϵͳ�汾: 1.0.0 <br>
 * ������Ա: lyd
 * ����ʱ��: 2017-10-12<br>
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
            logger.error("sqlite �����쳣",e);
            throw e;
        } catch (SQLException e) {
            logger.error("sqlite��ȡ�����쳣",e);
            throw e;
        }
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(conn==null || conn.isClosed()){
            logger.info("������Ϣ�����쳣��������������");
            initConnection();
        }
        return conn;
    }
    private static boolean testHasTable(AbsDto absDto){
        String sql = "select count(1) from " + absDto.getTableName();
        try {
            executeSql(sql);
        } catch (SQLException throwables) {
            logger.error("�����ڣ�"+absDto.getTableName(),throwables);
            return false;
        }
        return true;
    }

    public static void  initTable(AbsDto absDto) throws SQLException {
        if(!testHasTable(absDto)){
            logger.info("������"+absDto.getTableName() );
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
            ResultSetMetaData rsm =rs.getMetaData(); //����м�
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
     * ��ʼ��sqllite�����û�б��򴴽���
     * @throws Exception
     */
    public static void initDb()throws Exception{
//        Connection connection= getConnection();
//        Statement statement=connection.createStatement();
//
//        RecmentsDao recmentsDao=DaoFactory.getRecmentsDao();
//        if(recmentsDao.needCreateTable()){
//            logger.info("���¹�����"+recmentsDao.getTableName());
//            statement.execute(recmentsDao.getCreateTableSql());
//            logger.info("���¹�����"+recmentsDao.getTableName()+"���");
//        }
//
//        SerialConfigDao serialConfigDao=DaoFactory.getSerialConfigDao();
//        if(serialConfigDao.needCreateTable()){
//            logger.info("���¹�����"+serialConfigDao.getTableName());
//            statement.execute(serialConfigDao.getCreateTableSql());
//            logger.info("���¹�����"+serialConfigDao.getTableName()+"���");
//        }
//        statement.close();
    }
}
