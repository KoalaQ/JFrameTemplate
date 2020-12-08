package db.dao;


import db.dto.AbsConfigAdapter;
import db.dto.CommonConfig;

import java.util.List;

public class ConfigDao extends CommonConfigDao {
    private static ConfigDao configDao;
    static {
        configDao=new ConfigDao();
    }

    public static ConfigDao getConfigDaoo() {
        return configDao;
    }
    public static  <T extends AbsConfigAdapter> T getConfig(Class<T> cls,String ctype ) throws Exception{
        T instance = null;
        CommonConfig commonConfig= getConfig(ctype);
        if(commonConfig!=null){
            instance = cls.newInstance();
            instance.init(commonConfig);
            if(instance.contentType().equals(AbsConfigAdapter.CONTENT_TYPE_JSON)){

            }
        }
        return instance;
    }
    public <T extends AbsConfigAdapter> List<T> getConfigList(Class<T> cls){
        try {
            T instance = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
