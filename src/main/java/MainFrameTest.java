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
        //region ���̴���
        try {
            PosTray.createTray("Demo",this);
        } catch (Exception e) {
            logger.error("���������쳣",e);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MsgDialog.showMsg("����","��������ʧ�ܣ����������x����ֱ���˳�������");
        }
        LogPanel logPanel=new LogPanel();


        this.setLayout(new BorderLayout());
        this.add(logPanel, BorderLayout.CENTER);

        //region frame��������
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
