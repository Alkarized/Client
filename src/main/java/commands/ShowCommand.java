package commands;

import client.Receiver;
import message.MessageColor;
import message.Messages;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class ShowCommand extends Command implements Serializable {
    private static final long serialVersionUID = 62;


    public ShowCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute(String[] args) {
        try {if (args.length == 1)
            receiver.printAllElements();
        else
            Messages.normalMessageOutput("Неправильно введены аргументы", MessageColor.ANSI_RED);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Messages.normalMessageOutput( "Что-то пошло не так..." + e.toString(), MessageColor.ANSI_RED);
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        execute(args);
    }


}
