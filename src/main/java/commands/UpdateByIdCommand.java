package commands;

import client.Receiver;
import fields.Flat;
import message.MessageColor;
import message.Messages;
import utils.FlatMaker;

import java.io.Serializable;
import java.util.Scanner;

public class UpdateByIdCommand extends Command implements Serializable {
    private static final long serialVersionUID = 63;


    public UpdateByIdCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute(String[] args) {
        execute(args, new Scanner(System.in));
    }

    @Override
    public void execute(String[] args, Scanner scanner) {
        Flat flat = new FlatMaker().makeFlat(scanner);
        try {
            if (flat != null && args.length == 2)
                receiver.updateElementById(scanner, flat, Long.valueOf(args[1]));
            else
                throw new Exception();
        } catch (Exception e) {
            Messages.normalMessageOutput("Неправильно введены аргументы", MessageColor.ANSI_RED);
        }

    }
}
