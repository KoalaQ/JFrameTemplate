package view.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ����˵��: <br>
 * ϵͳ�汾: 1.0.0 <br>
 * ������Ա: lyd
 * ����ʱ��: 2017-10-12<br>
 * <br>
 */
public class OutputStreamAdaptor extends OutputStream {
    private JTextArea textArea;
    private  JScrollPane logScrollPane;
    private static volatile Boolean scrollPane=true;
    private byte[] buf=new byte[1024];
    int count=0;
    public OutputStreamAdaptor(JTextArea textArea, JScrollPane logScrollPane) {
        this.textArea = textArea;
        this.logScrollPane=logScrollPane;
    }

    public OutputStreamAdaptor() {
    }

    @Override
    public void write(int b) throws IOException {
        if (count >= buf.length) {
            flushBuffer();
        }
        buf[count++] = (byte)b;
    }
    private void flushBuffer(){
        textArea.append(new String(buf,0,count));
        count=0;
        if(scrollPane){
            scrolltoend();
        }
    }

    @Override
    public void flush() throws IOException {
        flushBuffer();
    }

    public static void setScrollPane(Boolean scrollPane) {
         OutputStreamAdaptor.scrollPane = scrollPane;
    }

    /**
     *
     * ������ײ�
     */
    private void scrolltoend(){
        //��һ���ܴ��ֵ��ȷ������ı�ͻ����ܵ���β
        int height=2000;
        Point p = new Point();
        p.setLocation(0,textArea.getLineCount()*height);
        logScrollPane.getViewport().setViewPosition(p);
    }
}
