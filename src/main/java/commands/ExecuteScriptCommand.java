package commands;

import client.Receiver;
import message.MessageColor;
import message.Messages;

import java.io.Serializable;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command implements Serializable {
    private static final long serialVersionUID = 53;


    public ExecuteScriptCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 2)
            receiver.executeScript(args[1]);
        else
            Messages.normalMessageOutput("Неправильно введены аргументы", MessageColor.ANSI_RED);
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        execute(args);
    }

}
