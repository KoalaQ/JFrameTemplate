package util;

/**
 * ����˵��: <br>
 * ϵͳ�汾: 1.0.0 <br>
 * ������Ա: lyd
 * ����ʱ��: 2017-10-11<br>
 * <br>
 */

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ��չ��һ�����������appender��, ��ʱ��֧��datePattern����, ���ǿ�������maxBackupIndex
 *
 * @author Dan Shan
 *
 */
public class CustomLogAppender extends FileAppender {

    /** �������д��datepattern */
    private static final String datePattern = "'.'yyyy-MM-dd.'log'";
    /** ����ļ��������� */
    private int maxBackupIndex = 2;
    /** �ļ���+�ϴ�������ʱ�� */
    private String scheduledFilename;

    /**
     * The next time we estimate a rollover should occur.
     */
    private long nextCheck = System.currentTimeMillis() - 1;

    Date now = new Date();
    SimpleDateFormat sdf;

    /**
     * The default constructor does nothing.
     */
    public CustomLogAppender() {
    }

    /**
     * ������Ĺ�����
     */
    public CustomLogAppender(Layout layout, String filename, int maxBackupIndex) throws IOException {
        super(layout, filename, true);
        this.maxBackupIndex = maxBackupIndex;
        activateOptions();
    }

    /** * ��ʼ����Appender�����ʱ�����һ�� */
    @Override
    public void activateOptions() {
        super.activateOptions();
        if (fileName != null) {
            // perf.log now.setTime(System.currentTimeMillis());
            sdf = new SimpleDateFormat(datePattern);
            File file = new File(fileName);
            // ��ȡ������ʱ��ƴ�ɵ��ļ���
            scheduledFilename = fileName + sdf.format(new Date(file.lastModified()));
        } else {
            LogLog.error("File is not set for appender [" + name + "].");
        } if (maxBackupIndex <= 0) {
            LogLog.error("maxBackupIndex reset to default value[2],orignal value is:" + maxBackupIndex);
            maxBackupIndex = 2;
        }
    }

    /**
     * �����ļ��ĺ���:<br>
     * 1. ���ļ�������ʱ������бȽ�, ȷ���Ƿ����<br>
     * 2. if��Ҫ����, ��ǰ�ļ�rename���ļ���+����, ���¿�ʼд�ļ�<br>
     * 3. ������õ�maxBackupIndex,ɾ�����ڵ��ļ�
     */
    void rollOver() throws IOException {
        String datedFilename = fileName + sdf.format(now);
        // ����ϴ�д�����ڸ���ǰ������ͬ������Ҫ���ļ�
        if (scheduledFilename.equals(datedFilename)) {
            return;
        }
        // close current file, and rename it to datedFilename
        this.closeFile();
        File target = new File(scheduledFilename);
        if (target.exists()) {
            try {
                target.delete();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

        File file = new File(fileName);
        boolean result = file.renameTo(target);
        if (result) {
            LogLog.debug(fileName + " -> " + scheduledFilename);
        } else {
            LogLog.error("Failed to rename [" + fileName + "] to [" + scheduledFilename + "].");
        }

        // ɾ�������ļ�
        if (maxBackupIndex > 0) {
            File folder = new File(file.getParent());
            List<String> maxBackupIndexDates = getMaxBackupIndexDates();
            for (File ff : folder.listFiles()) {
                // ����Ŀ¼�������ڲ��ڱ��ݷ�Χ�ڵ���־ɾ��
                if (ff.getName().startsWith(file.getName()) && !ff.getName().equals(file.getName())) {
                    // ��ȡ�ļ�����������ʱ���
                    String markedDate = ff.getName().substring( file.getName().length());
                    if (!maxBackupIndexDates.contains(markedDate)) {
                        result = ff.delete();
                    }
                    if (result) {
                        LogLog.debug(ff.getName() + " -> deleted ");
                    } else {
                        LogLog.error("Failed to deleted old DayRollingFileAppender file :" + ff.getName());
                    }
                }
            }
        }

        try {
            // This will also close the file. This is OK since multiple
            // close operations are safe.
            this.setFile(fileName, false, this.bufferedIO, this.bufferSize);
        } catch (IOException e) {
            errorHandler.error("setFile(" + fileName + ", false) call failed.");
        }

        scheduledFilename = datedFilename;
        // �������������ڴ�
    }

    /**
     * Actual writing occurs here.
     * ���������д����������ִ�й���.
     */
    @Override
    protected void subAppend(LoggingEvent event) {

        long n = System.currentTimeMillis();
        if (n >= nextCheck) {

            // ��ÿ��д����ǰ�ж�һ���Ƿ���Ҫ�����ļ�
            now.setTime(n);
            nextCheck = getNextDayCheckPoint(now);
            try {
                rollOver();
            } catch (IOException ioe) {
                LogLog.error("rollOver() failed.", ioe);
            }
        } super.subAppend(event);
    }

    /**
     * ��ȡ��һ���ʱ������
     *
     * @param now
     * @return
     */
    long getNextDayCheckPoint(Date now) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);// ע��MILLISECOND,����ҲҪ��0.�����������Ҳ�Ҳ������� calendar.add(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }

    /**
     * ����maxBackupIndex���õı����ļ���������ȡҪ����log�ļ������ڷ�Χ����
     * @return list<'fileName+yyyy-MM-dd'>
     */
    List<String> getMaxBackupIndexDates() {

        List<String> result = new ArrayList<String>();
        if (maxBackupIndex > 0) {
            for (int i = 1; i <= maxBackupIndex; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                // ע��MILLISECOND,����ҲҪ��0...�������Ҳ�Ҳ�������
                calendar.add(Calendar.DATE, -i);
                result.add(sdf.format(calendar.getTime()));
            }
        }

        return result;
    }

    public int getMaxBackupIndex() {
        return maxBackupIndex;
    }
    public void setMaxBackupIndex(int maxBackupIndex) {
        this.maxBackupIndex = maxBackupIndex;
    }
    public String getDatePattern() {
        return datePattern;
    }
}
