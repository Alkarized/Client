package commands;

import client.Receiver;
import message.MessageColor;
import message.Messages;

import java.io.Serializable;
import java.util.Scanner;

public class CountLessCommand extends Command implements Serializable {
    private static final long serialVersionUID = 52;


    public CountLessCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length == 2)
                receiver.countLessNumberOfRooms(Integer.parseInt(args[1]));
            else
                throw new Exception();
        } catch (Exception e) {
            Messages.normalMessageOutput("Введено неправильное число", MessageColor.ANSI_RED);
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        execute(args);
    }
}
