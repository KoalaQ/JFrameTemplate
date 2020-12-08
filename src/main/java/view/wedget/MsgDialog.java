package view.wedget;

import org.apache.log4j.Logger;
import util.LogUtil;

import javax.swing.*;

public class MsgDialog {
    private  static Logger logger= LogUtil.getLogger(MsgDialog.class);
    private JLabel msgLabel;
    JDialog dialog;


   static MsgDialog msgDialog=null;

    private MsgDialog(JFrame containFrame) {
        dialog=new JDialog(containFrame);
        msgLabel=new JLabel();
        dialog.add(msgLabel);
    }

    public static void init(JFrame jFrame){
       if(msgDialog==null){
           msgDialog=new MsgDialog(jFrame);
       }
    }
    private void show(String tltle, String msg){
        msgLabel.setText(msg);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle(tltle);
        dialog.setSize(350,150);
        dialog.add(msgLabel);
        dialog.setVisible(true);
    }
    public static void showMsg(String tltle, String msg){
        if(msgDialog!=null){
            msgDialog.show(tltle,msg);
        }else{
            logger.error("未初始化，无法提示！");
        }

    }


}
