package db.dao;

import db.SqliteHelper;
import db.dto.CommonConfig;
import util.BeanUtils;
import util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CommonConfigDao {


    protected static List<CommonConfig> getAllConfig() throws Exception {
        return  getConfigList("");
    }
    public static List<CommonConfig> getConfigList(String ctype) throws Exception {
        List<CommonConfig> retList=new ArrayList<>();
        String sql=" select * from "+ CommonConfig.TABLE_NAME;
        if(!StringUtils.isNullOrEmpty(ctype)){
            sql += " where ctype = '"+ctype+"'";
        }
        List<Map<String,Object>> configs=SqliteHelper.getList(sql);
        if(configs!=null && configs.size()>0){
            for (Map<String,Object> map:configs) {
                CommonConfig config=new CommonConfig();
                BeanUtils.covertMapToBean(config,map);
                retList.add(config);
            }
        }
        return  retList;
    }
    protected static CommonConfig getConfig(String ctype)throws Exception{
        CommonConfig ret=null;
        List<CommonConfig> retList=getConfigList(ctype);
        if(retList.size()>0){
            ret=retList.get(0);
        }
        return  ret;
    }
    public static void save(CommonConfig commonConfig) throws Exception {

        String sql=" insert into  %1$s (urid,ctype,content) values('%2$s','%3$s','%4$s') ";
        sql=String.format(sql,commonConfig.getTableName(),commonConfig.getUrid(),commonConfig.getCtype(),commonConfig.getContent());
       int ret=  SqliteHelper.executeSql(sql);
       if(ret!=1){
           throw new Exception("保存不成功，类型"+commonConfig.getCtype()+":"+commonConfig.getContent());
       }

    }

    public static void update(CommonConfig commonConfig)throws Exception{
        String sql=" update  %1$s  set content='%2$s' where urid='%3$s' ";
        sql= String.format(sql,commonConfig.getTableName(),commonConfig.getContent(),commonConfig.getUrid());
        int ret=  SqliteHelper.executeSql(sql);
        if(ret!=1){
            throw new Exception("更新不成功,类型"+commonConfig.getCtype()+":"+commonConfig.getContent());
        }
    }
    public static void delete(String  urid)throws Exception{
        String sql=" delete from  %1$s   where urid='%2$s' ";
        sql=String.format(sql,CommonConfig.TABLE_NAME,urid);
        int ret=  SqliteHelper.executeSql(sql);
        if(ret!=1){
            throw new Exception("更新不成功,主键："+urid);
        }

    }
}
