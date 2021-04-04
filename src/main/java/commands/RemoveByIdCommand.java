package commands;


import client.Receiver;
import message.MessageColor;
import message.Messages;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public class RemoveByIdCommand extends Command implements Serializable {
    private static final long serialVersionUID = 59;


    public RemoveByIdCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute(String[] args) {

        try {
            if (args.length == 2) {
                Long.valueOf(args[1]);
                receiver.removeById(args);
            } else
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            Messages.normalMessageOutput("Неправильно введены аргументы", MessageColor.ANSI_RED);
        } catch (IOException | ClassNotFoundException e) {
        Messages.normalMessageOutput( "Что-то пошло не так..." + e.toString(), MessageColor.ANSI_RED);
    }

    }

    @Override
    public void execute(String[] args, Scanner scanner) {

    }

}
