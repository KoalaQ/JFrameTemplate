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
 * ��ֻ֧��һ��
 */
public class LogPanel extends AbsBaseJPanel {


    private static JTextArea logTextArea= new JTextArea() ;//��־��
    private static JScrollPane logScrollPane= new JScrollPane(logTextArea);

    public LogPanel() {


        //region ����Ϊ�Զ�����ı���
        LimitativeDocument limitativeDocument = new LimitativeDocument(logTextArea, 500, 100);

        logTextArea.setFont(FontFactory.getLogFont());
        logTextArea.setDocument(limitativeDocument);
        logTextArea.setLineWrap(true);        //�����Զ����й���
        logTextArea.setWrapStyleWord(true);            // ������в����ֹ���
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
     * ������ײ�
     */
    private void scrolltoend() {
        int height = 20;
        Point p = new Point();
        p.setLocation(0, logTextArea.getLineCount() * height);
        logScrollPane.getViewport().setViewPosition(p);
    }
}
