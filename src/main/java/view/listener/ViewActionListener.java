package view.listener;

import view.listener.constant.EnActionEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {

    }
    /**
     * 新ActionEvent，source也来自enActionEvent
     * @param enActionEvent
     * @return
     */
    public static ActionEvent getActionEvent(EnActionEvent enActionEvent){
        ActionEvent actionEvent=new ActionEvent(enActionEvent,0,enActionEvent.getCmd());
        return actionEvent;
    }
    public static ActionEvent getActionEvent(EnActionEvent enActionEvent, String msg){
        enActionEvent.setMsg(msg);
        return getActionEvent( enActionEvent);
    }
}
