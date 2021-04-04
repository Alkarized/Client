package commands;

import client.Receiver;
import fields.Flat;
import message.MessageColor;
import message.Messages;
import utils.FlatMaker;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class RemoveLowerCommand extends Command implements Serializable {
    private static final long serialVersionUID = 61;


    public RemoveLowerCommand(Receiver receiver) {
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
            if (flat != null && args.length == 1)
                receiver.removeLowerElements(flat);
            else
                Messages.normalMessageOutput("Неправильно введены аргументы", MessageColor.ANSI_RED);
        } catch (IOException | ClassNotFoundException e) {
            Messages.normalMessageOutput( "Что-то пошло не так..." + e.toString(), MessageColor.ANSI_RED);
        }
    }

}
