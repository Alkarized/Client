package commands;

import client.Receiver;
import message.MessageColor;
import message.Messages;

import java.io.Serializable;
import java.util.Scanner;

public class PrintFieldNumberOfRoomsCommand extends Command implements Serializable {
    private static final long serialVersionUID = 58;


    public PrintFieldNumberOfRoomsCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1)
            receiver.printFieldDescendingNumberOfRooms();
        else
            Messages.normalMessageOutput("Неправильно введены аргументы", MessageColor.ANSI_RED);
    }

    @Override
    public void execute(String[] args, Scanner scanner) {

    }

}
