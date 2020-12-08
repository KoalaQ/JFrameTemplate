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
 * 功能说明: <br>
 * 系统版本: 1.0.0 <br>
 * 开发人员: lyd
 * 开发时间: 2017-10-12<br>
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
            //创建一个托盘图标对象
            Image image = Toolkit.getDefaultToolkit().getImage("img/log.png");
            //Image image = Toolkit.getDefaultToolkit().getImage(PosTray.class.getClassLoader().getResource("img/postray.png"));
            TrayIcon icon =
            new TrayIcon(image,title);
            icon.setImageAutoSize(true);
            //创建弹出菜单
            PopupMenu menu = new PopupMenu();
            //添加一个用于打开窗体的按钮
            MenuItem showFrameitem = new MenuItem("打开");
            showFrameitem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    myActionListener.actionPerformed(ViewActionListener.getActionEvent(EnActionEvent.SHOWFRAME_CLICK));

                }
            });
            //添加一个用于退出的按钮
            MenuItem exititem = new MenuItem("退出");
            exititem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    myActionListener.actionPerformed(ViewActionListener.getActionEvent(EnActionEvent.EXIT_CLICK));
                }
            });
            menu.add(showFrameitem);
            menu.add(exititem);
            //添加弹出菜单到托盘图标
            icon.setPopupMenu(menu);
            //单击监听
            icon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getButton()==MouseEvent.BUTTON1){
                        //左键点击
                        myActionListener.actionPerformed(ViewActionListener.getActionEvent(EnActionEvent.POSTRAY_CLICK));

                    }
                }
            });
            SystemTray tray = SystemTray.getSystemTray();//获取系统托盘
            try {
                tray.add(icon);
            } catch (AWTException e1) {
                throw e1;
            }//将托盘图表添加到系统托盘
        }else{
            throw new Exception("系统不支持托盘");
        }
    }

}
