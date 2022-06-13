package org.example.command;

/**
 * @author: zyh
 * @date: 2022/6/13
 */
public class Controller {

    private Command openTVCommad;
    private Command closeTVCommad;
    private Command changeChanel;

    public int nowChannel = 0;
    public int priorChannel;

    public Controller(Command openTVCommad, Command closeTVCommad, Command changeChanel) {
        this.openTVCommad = openTVCommad;
        this.closeTVCommad = closeTVCommad;
        this.changeChanel = changeChanel;
    }

    public void open(){
        openTVCommad.execute(0);
    }

    public void close(){
        closeTVCommad.execute(0);
    }

    public void changeChanel(){
        priorChannel = nowChannel;            //换频道前记录当前频道
        nowChannel++; // 频道加
        changeChanel.execute(nowChannel);
    }

    public void ChannelUndo(){
        changeChanel.execute(priorChannel);          //将以前的频道传入
        //当前频道与前一个频道进行互换
        int tempChannel;
        tempChannel = priorChannel;
        priorChannel = nowChannel;
        nowChannel = tempChannel;
    }

}
