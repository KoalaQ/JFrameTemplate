package view.wedget;

import view.listener.constant.EnActionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbsFrame extends JFrame implements ActionListener {

    public AbsFrame() throws HeadlessException {
        super();
    }
    public AbsFrame(String title) throws HeadlessException {
        super(title);
    }
    protected void showFrame(){
        setLocationRelativeTo(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(EnActionEvent.SHOWFRAME_CLICK.getCmd())||
                e.getActionCommand().equals(EnActionEvent.POSTRAY_CLICK.getCmd())){
            showFrame();
        }else if(e.getActionCommand().equals(EnActionEvent.EXIT_CLICK.getCmd())||
                e.getActionCommand().equals(EnActionEvent.CLOSECLICK.getCmd())){
            System.exit(0);
        }
    }

}
