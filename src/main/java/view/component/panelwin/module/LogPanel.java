package view.component.panelwin.module;

import view.component.panelwin.AbsBaseJPanel;
import view.util.FontFactory;
import view.util.OutputStreamAdaptor;
import view.wedget.util.LimitativeDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 暂只支持一个
 */
public class LogPanel extends AbsBaseJPanel {


    private static JTextArea logTextArea= new JTextArea() ;//日志框
    private static JScrollPane logScrollPane= new JScrollPane(logTextArea);

    public LogPanel() {


        //region 设置为自动清除文本框
        LimitativeDocument limitativeDocument = new LimitativeDocument(logTextArea, 500, 100);

        logTextArea.setFont(FontFactory.getLogFont());
        logTextArea.setDocument(limitativeDocument);
        logTextArea.setLineWrap(true);        //激活自动换行功能
        logTextArea.setWrapStyleWord(true);            // 激活断行不断字功能
        logTextArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                OutputStreamAdaptor.setScrollPane(false);
            }
            @Override
            public void focusLost(FocusEvent e) {
                OutputStreamAdaptor.setScrollPane(true);
            }
        });
        OutputStreamAdaptor.setScrollPane(true);

        this.setLayout(new BorderLayout());
        this.add(logScrollPane, BorderLayout.CENTER);

    }

    public static JTextArea getLogTextArea() {
        return logTextArea;
    }

    public static JScrollPane getLogScrollPane() {
        return logScrollPane;
    }

    /**
     * 滚到最底部
     */
    private void scrolltoend() {
        int height = 20;
        Point p = new Point();
        p.setLocation(0, logTextArea.getLineCount() * height);
        logScrollPane.getViewport().setViewPosition(p);
    }
}
