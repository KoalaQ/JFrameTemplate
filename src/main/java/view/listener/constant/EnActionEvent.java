package view.listener.constant;

/**
 * Created by lyd on 2017/1/4.
 */
public enum EnActionEvent {
    //region ����
    /**
     * ������ʾ����
     */
    COMMOM_WARNING("COMMOM_WARNING",ENWarningLevel.WARNING),
    //endregion

    /**
     * ��¼���
     */
    LOGINCLICK("loginclick"),
    /**
     *�Ͽ����
     */
    LOGOUTCLICK("logoutclick"),
    /**
     *�˳����
     */
    CLOSECLICK("closeclick"),
    /**
     *���ڵ��
     */
    ABOUTCLICK("aboutclick"),
    /**
     *�´��ڵ��
     */
    NEWTABCLICK("newtabclick"),
    /**
     *�ر����е��
     */
    CLOSEALLTABCLICK("closealltabclick"),
    /**
     *uc������밴ť���
     */
    UCDEFINE_INSERTCLICK("insertclick"),
    /**
     *uc����β�Ӱ�ť���
     */
    UCDEFINE_TAILINSERTCLICK("tailinsertclick"),
    /**
     *uc����ɾ����ť���
     */
    UCDEFINE_DELCLICK("delclick"),
    /**
     *uc���屣�水ť���
     */
    UCDEFINE_SAVECLICK("saveclick"),
    /**
     *uc�����ѯ��ť���
     */
    UCDEFINE_QUERYCLICK("queryclick"),
    /**
     *�쳣
     */
    EXCEPTION("EXCEPTION"),
    /**
     *��ʾ
     */
    INFO("INFO"),
    /**
     *����
     */
    WARNING("WARNING"),
    /**
     * uc������Ȩ�����ѡ���¼�
     */
    UCDEFINE_CLASSSELECT("UCDEFINECLASSSELECT"),
    /**
     *uc�����й�������ѡ���¼�
     */
    UCDEFINE_UCTYPESELECT("UCDEFINEUCTYPESELECT"),
    /**
     *uc���������մ���ʱͣ��ѡ���¼�
     */
    UCDEFINE_ISLIMITSELECT("UCDEFINEISLIMITSELECT"),
    /**
     *uc���������մ���ʱͣ��ѡ���¼�
     */
    POSTRAY_CLICK("POSTRAY_CLICK"),
    /**
     *�رյ��
     */
    EXIT_CLICK("EXIT_CLICK"),
    /**
     *��ʾ����
     */
    SHOWFRAME_CLICK("SHOWFRAME_CLICK"),
    /**
     *���Դ���
     */
    TEST_SERIAL("TEST_SERIAL","���������ѷ���"),
    /**
     *�򿪴��ڷ���
     */
    OPEN_SERIAL("OPEN_SERIAL"),
    /**
     *�򿪴��ڷ���ȴ�
     */
    OPEN_SERIAL_WAIT("OPEN_SERIAL_WAIT"),
    /**
     *�رմ��ڷ���
     */
    CLOSE_SERIAL("CLOSE_SERIAL"),
    /**
     *��������
     */
    START_SOFT("START_SOFT"),


    //<editor-fold desc="��������׳��¼�">
    /**
     *ͨ����Ϣ
     */
    COMMON_INFO("COMMON_INFO",ENWarningLevel.INFO),
    /**
     *ͨ����Ϣ
     */
    COMMON_WARNING("COMMON_WARNING",ENWarningLevel.WARNING),
    /**
     *ͨ����Ϣ
     */
    COMMON_ERROR("COMMON_ERROR",ENWarningLevel.ERROR),
    /**
     *��������
     */
    START_SOFT_ERROR("START_SOFT_ERROR",ENWarningLevel.ERROR),
    /**
     *��ȡ���������쳣����
     */
    READ_SERIAL_ERROR("READ_SERIAL",ENWarningLevel.ERROR),
    /**
     *���ڶϿ��쳣
     */
    DISCONNECT_SERIAL_ERROR("DISCONNECT_SERIAL_ERROR",ENWarningLevel.ERROR),
    /**
     *���������쳣
     */
    RUN_TASK_WARNING("RUN_TASK_WARNING",ENWarningLevel.WARNING),
    /**
     * ��ȡpos�ն˺����
     */
    SUCCGET_TERMNUM("SUCCGET_TERMNUM",ENWarningLevel.INFO),
    /**
     * ��ȡpos�ն˺����
     */
    CRC_VALIDFAIL("CRC_VALIDFAIL",ENWarningLevel.WARNING),

    /**
     * ���ݿ��쳣
     */
    SQLOPERATEEXCEPTION("SQLOPERATEEXCEPTION",ENWarningLevel.WARNING),
    /**
     *���������쳣����
     */
    SERIAL_WARNING("SERIAL_WARNING",ENWarningLevel.WARNING),
    /**
     *socket�쳣,���ڻ�ȡ������
     */
    SCOKET_WARNING("SCOKET_WARNING",ENWarningLevel.WARNING),
    /**
     *��ȡ�����õĴ��ڲ���
     */
    ACQUIRED_SERIALCONFIG("ACQUIRED_SERIALCONFIG",ENWarningLevel.INFO),
    /**
     * ���������쳣
     */
    SOCKETERROR("SOCKETERROR",ENWarningLevel.ERROR),
    /**
     * ��������쳣
     */
    OPENEXPLOREERROR("OPENEXPLOREERROR",ENWarningLevel.ERROR),
    //</editor-fold>



    //region ����ʹ���¼�
    /**
     * ���Ͷ�����
     */
    TEST_PUSHREC("TEST_PUSHREC",ENWarningLevel.WARNING),
    /**
     * ��ѯ֧�����
     */
    TEST_QUERY_PAYSTATE("TEST_QUERY_PAYSTATE",ENWarningLevel.WARNING),
    /**
     * ������ݿ�
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
