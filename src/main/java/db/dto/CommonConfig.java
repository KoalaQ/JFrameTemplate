package db.dto;

public class CommonConfig extends AbsDto {
    public final static String TABLE_NAME="tb_commonconfig";
    private String urid;
    private String ctype;
    private Integer orderno=1;
    private String content;

    public String getUrid() {
        return urid;
    }

    public void setUrid(String urid) {
        this.urid = urid;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    @Override
    public String toString() {
        return "CommonConfig{" +
                "urid='" + urid + '\'' +
                ", ctype='" + ctype + '\'' +
                ", orderno=" + orderno +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public String getCreateTableSql() {
        return "CREATE TABLE tb_commonconfig( " +
                "urid TEXT(32) PRIMARY KEY NOT NULL, " +
                "ctype TEXT(2)   NOT NULL, " +
                "orderno integer   NOT NULL, " +
                "content TEXT(2048) NOT NULL " +
                ")";
    }
}
