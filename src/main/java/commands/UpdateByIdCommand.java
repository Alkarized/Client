package commands;

import client.Receiver;
import fields.Flat;
import message.MessageColor;
import message.Messages;
import utils.FlatMaker;

import java.io.IOException;
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
        try {
            if (args.length == 2) {
                Flat flat = new FlatMaker().makeFlat(scanner);
                if (flat != null) {
                    Long.valueOf(args[1]);
                    receiver.updateElementById(flat, args);
                } else {
                    throw new NumberFormatException();
                }
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Messages.normalMessageOutput("Неправильно введены аргументы", MessageColor.ANSI_RED);
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
            Messages.normalMessageOutput( "Что-то пошло не так..." + e1.toString(), MessageColor.ANSI_RED);
        }

    }
}
