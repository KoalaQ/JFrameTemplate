package view.component.panelwin;

import view.util.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowPanelTab implements MouseListener  {
    private String title;
    private JLabel titleX;
    //父容器
    private WindowRootTabPanel root;

    private JPanel titlePanel;
    private AbsBaseJPanel content;

    public WindowPanelTab(String title) {
        this.title = title;
        init();
    }

    public Component getContent(){
        return content;
    }
    public Component getTitle(){
        return titlePanel;
    }

    public void setContent(AbsBaseJPanel content) {
        this.content = content;
    }

    private void init(){
        //标题
        titlePanel=new JPanel();
        JLabel titleName=new JLabel(title);
         titleX=new JLabel("x");

        titleX.setFont(FontFactory.getTabtitleX());
        titleX.setHorizontalAlignment(JLabel.RIGHT);
        //titlePanel添加名字和x
        titlePanel.add(titleName);
        titlePanel.add(new JLabel("  "));
        titlePanel.add(titleX);
        //添加关闭监听
        titleX.addMouseListener(this);
    }

    public void isShowClose(boolean isShow){
        if(!isShow){
            titlePanel.remove(titleX);
        }
    }

    public void setRoot(WindowRootTabPanel root) {
        this.root = root;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        root.close(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        titleX.setFont(FontFactory.getTabtitleXOn());
        titleX.setForeground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        titleX.setFont(FontFactory.getTabtitleXOut());
        titleX.setForeground(Color.BLACK);
    }
}
