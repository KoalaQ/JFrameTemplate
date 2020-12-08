package view.wedget.util;

/**
 * ����˵��: <br>
 * ϵͳ�汾: 1.0.0 <br>
 * ������Ա: lyd
 * ����ʱ��: 2017-10-13<br>
 * <br>
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

/**
 * description:�Զ����Document
 * ���Կ����������
 *     Ĭ�����Ϊ10��
 *     ���������ʱ�������һ�н�����ȡ
 *
 *
 */
public class LimitativeDocument extends PlainDocument{
    private JTextComponent textComponent;
    private int lineMax = 10;//�������
    private int removeLength=0;//��������
    ResizeCallback callback;//�����ص�

    /**
     *
     * @param tc
     * @param lineMax �������
     * @param removeLength  ÿ�γ���������������
     */
    public LimitativeDocument(JTextComponent tc, int lineMax, int removeLength){
        textComponent = tc;
        this.lineMax = lineMax;
        this.removeLength=removeLength;
    }
    public LimitativeDocument(JTextComponent tc, int lineMax, int removeLength, ResizeCallback callback){
        textComponent = tc;
        this.lineMax = lineMax;
        this.removeLength=removeLength;
        this.callback=callback;
    }
    public LimitativeDocument(JTextComponent tc){
        textComponent = tc;
    }
    public void insertString(int offset, String s, AttributeSet attributeSet) throws BadLocationException {

        String value =   textComponent.getText();
        String[] valueSplit=value.split("\\n");
        if(value!=null  && valueSplit.length>=lineMax){
            int index=value.indexOf(valueSplit[removeLength]);
            super.remove(0, index+valueSplit[removeLength].length()+1);
            super.insertString(offset-(index+valueSplit[removeLength].length()+1),   s,   attributeSet);
            if(callback!=null){
                callback.callback();
            }
        }else{
            super.insertString(offset,   s,   attributeSet);
        }
    }
    public interface ResizeCallback{
        /**
         * ����������ص����ڽ��������ʾ����
         */
        void callback();
    }

    public void setCallback(ResizeCallback callback) {
        this.callback = callback;
    }
}