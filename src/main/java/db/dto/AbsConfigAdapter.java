package db.dto;

public abstract class AbsConfigAdapter extends  CommonConfig {
    public  final static String CONTENT_TYPE_JSON="1";
    public  String  contentType() {
        return "0";
    }
    public void init(CommonConfig commonConfig){
        this.setUrid(commonConfig.getUrid());
        this.setCtype(commonConfig.getCtype());
        this.setContent(commonConfig.getContent());
    }


}
