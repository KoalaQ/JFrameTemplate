package view.wedget;


import org.apache.log4j.Logger;
import util.LogUtil;
import view.listener.ViewActionListener;
import view.listener.constant.EnActionEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ����˵��: <br>
 * ϵͳ�汾: 1.0.0 <br>
 * ������Ա: lyd
 * ����ʱ��: 2017-10-12<br>
 * <br>
 */
public class PosTray {
    private   Logger logger= LogUtil.getLogger(PosTray.class);
    protected ActionListener myActionListener;

    public static void createTray(String title,ActionListener myActionListener) throws Exception {
        PosTray posTray=  new PosTray(myActionListener);
        posTray.createTray(title);
    }

    public PosTray(ActionListener myActionListener) {
        this.myActionListener = myActionListener;

    }
    private void createTray(String title) throws Exception {
        if(SystemTray.isSupported()){
            //����һ������ͼ�����
            Image image = Toolkit.getDefaultToolkit().getImage("img/log.png");
            //Image image = Toolkit.getDefaultToolkit().getImage(PosTray.class.getClassLoader().getResource("img/postray.png"));
            TrayIcon icon =
            new TrayIcon(image,title);
            icon.setImageAutoSize(true);
            //���������˵�
            PopupMenu menu = new PopupMenu();
            //���һ�����ڴ򿪴���İ�ť
            MenuItem showFrameitem = new MenuItem("��");
            showFrameitem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    myActionListener.actionPerformed(ViewActionListener.getActionEvent(EnActionEvent.SHOWFRAME_CLICK));

                }
            });
            //���һ�������˳��İ�ť
            MenuItem exititem = new MenuItem("�˳�");
            exititem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    myActionListener.actionPerformed(ViewActionListener.getActionEvent(EnActionEvent.EXIT_CLICK));
                }
            });
            menu.add(showFrameitem);
            menu.add(exititem);
            //��ӵ����˵�������ͼ��
            icon.setPopupMenu(menu);
            //��������
            icon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getButton()==MouseEvent.BUTTON1){
                        //������
                        myActionListener.actionPerformed(ViewActionListener.getActionEvent(EnActionEvent.POSTRAY_CLICK));

                    }
                }
            });
            SystemTray tray = SystemTray.getSystemTray();//��ȡϵͳ����
            try {
                tray.add(icon);
            } catch (AWTException e1) {
                throw e1;
            }//������ͼ����ӵ�ϵͳ����
        }else{
            throw new Exception("ϵͳ��֧������");
        }
    }

}
