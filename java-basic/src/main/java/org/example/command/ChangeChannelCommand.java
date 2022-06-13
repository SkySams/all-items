package org.example.command;

/**
 * @author: zyh
 * @date: 2022/6/13
 */
public class ChangeChannelCommand implements Command{

    private Television television;

    public ChangeChannelCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute(int num) {
        television.changeCHannese();
    }
}
