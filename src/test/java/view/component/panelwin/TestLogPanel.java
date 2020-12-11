package view.component.panelwin;

import org.apache.log4j.Logger;
import util.LogUtil;
import view.component.panelwin.module.BlankPanel;
import view.component.panelwin.module.LogPanel;

import java.awt.*;

public class TestLogPanel {
    private  static Logger logger= LogUtil.getLogger(TestLogPanel.class);




    public static void main(String[] args) {
        LogPanel logPanel=new LogPanel();
        BlankPanel blankPanel=new BlankPanel();

        WindowPanelTab tab=new WindowPanelTab("新窗口");
        WindowPanelTab tab2=new WindowPanelTab("新窗口");
        tab.setContent(logPanel);
        tab.isShowClose(false);
        tab2.setContent(blankPanel);

        WindowRootTabPanel windowRootPanel =new WindowRootTabPanel();
        windowRootPanel.addTab(tab);
        windowRootPanel.addTab(tab2);



        TestFrame testFrame=new TestFrame();
        testFrame.setLayout(new BorderLayout());
        testFrame.add(windowRootPanel, BorderLayout.CENTER);

        //region frame基础设置
        testFrame.setSize(1000,600);
        testFrame.showFrame();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        logger.info("日志："+System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
