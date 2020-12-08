package view.util;


import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;
import view.module.LogPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * ����˵��: <br>
 * ϵͳ�汾: 1.0.0 <br>
 * ������Ա: lyd
 * ����ʱ��: 2017-10-12<br>
 * <br>
 */
public class TextAreaLogAppender extends WriterAppender {
    public TextAreaLogAppender( ) {
        this.setWriter((Writer)(new OutputStreamWriter(new OutputStreamAdaptor(LogPanel.getLogTextArea(), LogPanel.getLogScrollPane()))));
       // this.setWriter(this.createWriter(new SystemOutStream()));
    }

    public TextAreaLogAppender(Layout layout) {
        this.setLayout(layout);
        //this.setWriter((Writer)(new OutputStreamWriter(new OutputStreamAdaptor(SetAndLogTab.getLogTextArea()))));
        this.setWriter(this.createWriter(new SystemOutStream()));
    }


    private static class SystemOutStream extends OutputStream {
        public SystemOutStream() {
        }

        public void close() {
        }

        public void flush() {
            System.out.flush();
        }

        public void write(byte[] b) throws IOException {
            System.out.write(b);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            System.out.write(b, off, len);
        }

        public void write(int b) throws IOException {
            System.out.write(b);
        }
    }

}
