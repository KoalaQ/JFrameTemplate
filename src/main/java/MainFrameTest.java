import org.apache.log4j.Logger;
import util.LogUtil;
import view.component.panelwin.module.LogPanel;
import view.wedget.AbsFrame;
import view.wedget.MsgDialog;
import view.wedget.PosTray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrameTest extends AbsFrame {
    private  static Logger logger= LogUtil.getLogger(MainFrameTest.class);



    public MainFrameTest() throws HeadlessException {
        super("Demo");
        MsgDialog.init(this);
        //region 托盘创建
        try {
            PosTray.createTray("Demo",this);
        } catch (Exception e) {
            logger.error("创建托盘异常",e);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MsgDialog.showMsg("警告","创建托盘失败，点击主窗口x将会直接退出本程序！");
        }
        LogPanel logPanel=new LogPanel();


        this.setLayout(new BorderLayout());
        this.add(logPanel, BorderLayout.CENTER);

        //region frame基础设置
        setSize(1000,600);
        showFrame();
        //endregion


    }

    public static void main(String[] args) {
        new MainFrameTest();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

    }
}
