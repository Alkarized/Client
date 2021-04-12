package client;

import commands.*;
import commands.serializable_commands.SerializableCommandStandard;
import commands.serializable_commands.SerializableCommandWithArgs;
import commands.serializable_commands.SerializableCommandWithObject;
import commands.serializable_commands.SerializableCommandWithObjectAndArgs;
import fields.Flat;
import message.MessageColor;
import message.Messages;
import utils.LineReader;

import java.io.*;
import java.util.Scanner;

public class Receiver {
    private final Invoker invoker;
    private final Connection connection;

    public Receiver(Invoker invoker, Connection connection) {
        this.invoker = invoker;
        this.connection = connection;
    }

    public void exit() throws IOException {
        connection.endConnection();
        System.exit(0);
    }

    public void getInfoAboutAllCommands() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new HelpCommand(this)));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void getInfoAboutCollection() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new InfoCommand(this)));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void clear() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new ClearCommand(this)));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void removeFirst() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new RemoveFirstCommand(this)));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void printFieldDescendingNumberOfRooms() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new PrintFieldNumberOfRoomsCommand(this)));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void getHead() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new HeadCommand(this)));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void countLessNumberOfRooms(String[] args) throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandWithArgs(new HeadCommand(this), args));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void printElementWithMinCoordinates() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new MinByCoordinatesCommand(this)));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void removeById(String[] args) throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandWithArgs(new RemoveByIdCommand(this), args));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void printAllElements() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new ShowCommand(this)));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void addElement(Flat flat) throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandWithObject(new ShowCommand(this), flat));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void updateElementById(Flat flat, String[] args) throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandWithObjectAndArgs(new UpdateByIdCommand(this),
                flat, args));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void removeLowerElements(Flat flat) throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandWithObject(new RemoveLowerCommand(this), flat));
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public boolean executeScript(String args) {
        LineReader lineReader = new LineReader();
        File file = null;
        try {
            file = new File(args);
            if (!file.exists() || !file.canRead() || !file.canWrite()) {
                throw new IllegalAccessError();
            }
            lineReader.readLine(new Scanner(file), invoker);
        } catch (IllegalAccessError | FileNotFoundException e) {
            Messages.normalMessageOutput("Невозможно работать с данным файлом, попробуйте еще раз", MessageColor.ANSI_RED);
            return false;
        } catch (StackOverflowError | OutOfMemoryError e) {
            Messages.normalMessageOutput("ЭЭЭЭЭ, куда, рекурся зло, вышел и зашел обратно!", MessageColor.ANSI_RED);
            return false;
        }
        Messages.normalMessageOutput("Закончилось выполнение скрипта из файла", MessageColor.ANSI_GREEN);
        return true;
    }
}
