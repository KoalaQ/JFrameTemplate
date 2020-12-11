package view.component.panelwin;

import javax.swing.*;
import java.awt.*;

public class WindowRootTabPanel extends JPanel {
    private JTabbedPane jTabbedPane;

    public WindowRootTabPanel() {
        jTabbedPane=new JTabbedPane();
        setLayout(new GridLayout(1, 1));
        this.add(jTabbedPane);
    }
    public void closeAllTab(){
        jTabbedPane.removeAll();
    }
    public void addTab(WindowPanelTab tab){
        tab.setRoot(this);
        jTabbedPane.addTab("",tab.getContent());
        jTabbedPane.setTabComponentAt(jTabbedPane.indexOfComponent(tab.getContent()),tab.getTitle());
    }
    public void close(WindowPanelTab tab){
        jTabbedPane.remove(jTabbedPane.indexOfComponent(tab.getContent()));
    }
}
