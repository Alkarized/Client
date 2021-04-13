package commands;

import client.Receiver;
import message.MessageColor;
import message.Messages;

import java.io.IOException;
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
            if (args.length == 2) {
                Integer.parseInt(args[1]);
                receiver.countLessNumberOfRooms(args);
            } else
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Messages.normalMessageOutput("Введено неправильное число", MessageColor.ANSI_RED);
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
            Messages.normalMessageOutput( "Что-то пошло не так..." + e1.toString(), MessageColor.ANSI_RED);
        }
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        execute(args);
    }
}
