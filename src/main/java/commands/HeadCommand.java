package commands;

import client.Receiver;
import message.MessageColor;
import message.Messages;

import java.io.Serializable;
import java.util.Scanner;

public class HeadCommand extends Command implements Serializable {
    private static final long serialVersionUID = 54;


    public HeadCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1)
            receiver.getHead();
        else
            Messages.normalMessageOutput("Неправильно введены аргументы", MessageColor.ANSI_RED);
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        execute(args);
    }


}
