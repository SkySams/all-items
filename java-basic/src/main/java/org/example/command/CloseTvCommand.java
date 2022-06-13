package org.example.command;

/**
 * @author: zyh
 * @date: 2022/6/13
 */
public class CloseTvCommand implements Command{

    private Television television;

    public CloseTvCommand(Television television) {
        this.television = television;
    }

    @Override
    public void execute(int num) {
        television.close();
    }
}
