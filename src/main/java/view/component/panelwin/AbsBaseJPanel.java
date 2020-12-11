package view.component.panelwin;



import javax.swing.*;

/**
 * Created by lyd on 2017/5/11.
 */
public abstract class AbsBaseJPanel extends JPanel {



    protected void handleExceptionMsg(Exception e){


    }
    /**
     * 控件被选中，tab页时重写即可
     */
    public boolean canLoseFcous(){
        return true;
    }
    /**
     * 控件被选中，tab页时重写即可
     */
    public void onFocus(boolean refresh){

    }
    public  void close(){

    }

}
