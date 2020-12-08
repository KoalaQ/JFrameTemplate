package view.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-10-12<br>
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
     * 滚到最底部
     */
    private void scrolltoend(){
        //赋一个很大的值，确保字体改变和换行能到结尾
        int height=2000;
        Point p = new Point();
        p.setLocation(0,textArea.getLineCount()*height);
        logScrollPane.getViewport().setViewPosition(p);
    }
}
