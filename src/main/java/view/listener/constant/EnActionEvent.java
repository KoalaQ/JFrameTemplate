package view.listener.constant;

/**
 * Created by lyd on 2017/1/4.
 */
public enum EnActionEvent {
    //region 公共
    /**
     * 用于提示警告
     */
    COMMOM_WARNING("COMMOM_WARNING",ENWarningLevel.WARNING),
    //endregion

    /**
     * 登录点击
     */
    LOGINCLICK("loginclick"),
    /**
     *断开点击
     */
    LOGOUTCLICK("logoutclick"),
    /**
     *退出点击
     */
    CLOSECLICK("closeclick"),
    /**
     *关于点击
     */
    ABOUTCLICK("aboutclick"),
    /**
     *新窗口点击
     */
    NEWTABCLICK("newtabclick"),
    /**
     *关闭所有点击
     */
    CLOSEALLTABCLICK("closealltabclick"),
    /**
     *uc定义插入按钮点击
     */
    UCDEFINE_INSERTCLICK("insertclick"),
    /**
     *uc定义尾加按钮点击
     */
    UCDEFINE_TAILINSERTCLICK("tailinsertclick"),
    /**
     *uc定义删除按钮点击
     */
    UCDEFINE_DELCLICK("delclick"),
    /**
     *uc定义保存按钮点击
     */
    UCDEFINE_SAVECLICK("saveclick"),
    /**
     *uc定义查询按钮点击
     */
    UCDEFINE_QUERYCLICK("queryclick"),
    /**
     *异常
     */
    EXCEPTION("EXCEPTION"),
    /**
     *提示
     */
    INFO("INFO"),
    /**
     *警告
     */
    WARNING("WARNING"),
    /**
     * uc定义中权限类别选中事件
     */
    UCDEFINE_CLASSSELECT("UCDEFINECLASSSELECT"),
    /**
     *uc定义中功能类型选中事件
     */
    UCDEFINE_UCTYPESELECT("UCDEFINEUCTYPESELECT"),
    /**
     *uc定义中日终处理时停用选中事件
     */
    UCDEFINE_ISLIMITSELECT("UCDEFINEISLIMITSELECT"),
    /**
     *uc定义中日终处理时停用选中事件
     */
    POSTRAY_CLICK("POSTRAY_CLICK"),
    /**
     *关闭点击
     */
    EXIT_CLICK("EXIT_CLICK"),
    /**
     *显示窗口
     */
    SHOWFRAME_CLICK("SHOWFRAME_CLICK"),
    /**
     *测试串口
     */
    TEST_SERIAL("TEST_SERIAL","测试命令已发送"),
    /**
     *打开串口服务
     */
    OPEN_SERIAL("OPEN_SERIAL"),
    /**
     *打开串口服务等待
     */
    OPEN_SERIAL_WAIT("OPEN_SERIAL_WAIT"),
    /**
     *关闭串口服务
     */
    CLOSE_SERIAL("CLOSE_SERIAL"),
    /**
     *启动服务
     */
    START_SOFT("START_SOFT"),


    //<editor-fold desc="服务程序抛出事件">
    /**
     *通用消息
     */
    COMMON_INFO("COMMON_INFO",ENWarningLevel.INFO),
    /**
     *通用消息
     */
    COMMON_WARNING("COMMON_WARNING",ENWarningLevel.WARNING),
    /**
     *通用消息
     */
    COMMON_ERROR("COMMON_ERROR",ENWarningLevel.ERROR),
    /**
     *启动服务
     */
    START_SOFT_ERROR("START_SOFT_ERROR",ENWarningLevel.ERROR),
    /**
     *读取串口数据异常服务
     */
    READ_SERIAL_ERROR("READ_SERIAL",ENWarningLevel.ERROR),
    /**
     *串口断开异常
     */
    DISCONNECT_SERIAL_ERROR("DISCONNECT_SERIAL_ERROR",ENWarningLevel.ERROR),
    /**
     *运行任务异常
     */
    RUN_TASK_WARNING("RUN_TASK_WARNING",ENWarningLevel.WARNING),
    /**
     * 读取pos终端号完成
     */
    SUCCGET_TERMNUM("SUCCGET_TERMNUM",ENWarningLevel.INFO),
    /**
     * 读取pos终端号完成
     */
    CRC_VALIDFAIL("CRC_VALIDFAIL",ENWarningLevel.WARNING),

    /**
     * 数据库异常
     */
    SQLOPERATEEXCEPTION("SQLOPERATEEXCEPTION",ENWarningLevel.WARNING),
    /**
     *串口数据异常服务
     */
    SERIAL_WARNING("SERIAL_WARNING",ENWarningLevel.WARNING),
    /**
     *socket异常,用于获取订单号
     */
    SCOKET_WARNING("SCOKET_WARNING",ENWarningLevel.WARNING),
    /**
     *获取到配置的串口参数
     */
    ACQUIRED_SERIALCONFIG("ACQUIRED_SERIALCONFIG",ENWarningLevel.INFO),
    /**
     * 监听服务异常
     */
    SOCKETERROR("SOCKETERROR",ENWarningLevel.ERROR),
    /**
     * 打开浏览器异常
     */
    OPENEXPLOREERROR("OPENEXPLOREERROR",ENWarningLevel.ERROR),
    //</editor-fold>



    //region 测试使用事件
    /**
     * 推送订单号
     */
    TEST_PUSHREC("TEST_PUSHREC",ENWarningLevel.WARNING),
    /**
     * 查询支付结果
     */
    TEST_QUERY_PAYSTATE("TEST_QUERY_PAYSTATE",ENWarningLevel.WARNING),
    /**
     * 清空数据库
     */
    TEST_QUERY_CLEANDB("TEST_QUERY_CLEANDB",ENWarningLevel.WARNING),
    //endregion
    ;
    private String cmd;
    private String msg;
    private Object obj;
    private ENWarningLevel warningLevel;



    EnActionEvent(String cmd) {
        this.cmd = cmd;
    }

    EnActionEvent(String cmd, String msg) {
        this.cmd = cmd;
        this.msg = msg;
    }

    EnActionEvent(String cmd, ENWarningLevel warningLevel) {
        this.cmd = cmd;
        this.warningLevel = warningLevel;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCmd(){
        return this.cmd;
    }

    public ENWarningLevel getWarningLevel() {
        return warningLevel;
    }

    /*public void setWarningLevel(ENWarningLevel warningLevel) {
        this.warningLevel = warningLevel;
    }*/
}
