package db.dao;


import db.dto.CommonConfig;
import db.dto.config.SystemInit;
import org.junit.Test;
import util.StringUtils;

public class ConfigDaoTest {

    @Test
    public void getAllConfig() {
        try {
            System.out.println( ConfigDao .getAllConfig());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConfigList() {
        try {
            System.out.println( ConfigDao.getConfigList("2"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConfig() {
        try {
            System.out.println( ConfigDao.getConfig("1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 @Test
    public void getConfig2() {
        try {
            System.out.println( ConfigDao.getConfig(SystemInit.class,"1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void save() {
        CommonConfig commonConfig=new CommonConfig();
        commonConfig.setUrid(StringUtils.UUID());
        commonConfig.setCtype("2");
        commonConfig.setContent("3");
        try {
            ConfigDao.save(commonConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void update() {
        CommonConfig commonConfig=new CommonConfig();
        commonConfig.setUrid("96bcbf51c9a24610af1d40a1ad08262f");
        commonConfig.setCtype("2");
        commonConfig.setContent("33");
        try {
            ConfigDao.update(commonConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            ConfigDao.delete("96bcbf51c9a24610af1d40a1ad08262f");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}