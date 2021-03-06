package view.util;

import java.awt.*;

/**
 * Created by lyd on 2017/5/11.
 */
public class FontFactory {


    /**
     * 新窗口，上面home字体大小
     * @return
     */
    public static Font getTabtitleName(){
        Font font=new Font("Default", Font.TYPE1_FONT,15);
        return font;
    }

    /**
     * 新窗口，上面home旁边 x 大小
     * @return
     */
    public static Font getTabtitleX(){
        Font font=new Font("Default", Font.TYPE1_FONT,15);
        return font;
    }
    /**
     * 新窗口，上面home旁边 x 大小
     * @return
     */
    public static Font getTabtitleXOn(){
        Font font=new Font("Default", Font.TYPE1_FONT,15);
        return font;
    }
    /**
     * 新窗口，上面home旁边 x 鼠标离开后字体
     * @return
     */
    public static Font getTabtitleXOut(){
        Font font=new Font("Default", Font.BOLD,15);
        return font;
    }
    public static Font getContentTabTitle(){
        Font font=new Font("Default", Font.BOLD,13);
        return font;
    }

    /**
     * 获得显示table字体
     * @return
     */
    public static Font getJTableFont(){
        Font font=new Font("Default", Font.PLAIN,12);
        return font;
    }

    /**
     * uc定义底部编辑字体
     * @return
     */
    public static Font getUcDefineFootFont(){
        Font font=new Font("Default", Font.PLAIN,15);
        return font;
    }
    /**
     * uc定义底部编辑字体
     * @return
     */
    public static Font getLogFont(){
        Font font=new Font("Default", Font.PLAIN,15);
        return font;
    }
    /**
     * 展示数据的表头，上面home字体大小
     * @return
     */
    public static Font getDataTabtitleName(){
        Font font=new Font("Default",Font.BOLD, 15);
        return font;
    }
    public static Font getEditComBoxFont(){
        Font font=new Font("Default",Font.PLAIN, 15);
        return font;
    }
    /**
     * sql定义输入框字体
     * @return
     */
    public static Font getBtnFont(){
        Font font=new Font("Default",Font.PLAIN, 15);
        return font;
    }
}
