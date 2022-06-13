package org.example.command;

/**
 * @author: zyh
 * @date: 2022/6/13
 */
public class Client {

    public static void main(String[] args) {
        Television tv = new Television();
        Command openCommand,closeCommand,changeCommand;
        openCommand = new OpenTvCommand(tv);
        closeCommand = new CloseTvCommand(tv);
        changeCommand = new ChangeChannelCommand(tv);
        Controller controller = new Controller(openCommand,closeCommand,changeCommand);

        controller.open();
        controller.changeChanel();
        controller.close();
    }

}
