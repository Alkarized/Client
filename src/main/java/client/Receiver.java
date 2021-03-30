package client;

import commands.*;
import commands.serializable_commands.SerializableCommandStandard;
import fields.Flat;
import message.MessageColor;
import message.Messages;
import utils.LineReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
        ObjectInputStream objectInputStream = connection.getObjectInputStream();
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void getInfoAboutCollection() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new InfoCommand(this)));
        ObjectInputStream objectInputStream = connection.getObjectInputStream();
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void clear() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new ClearCommand(this)));
        ObjectInputStream objectInputStream = connection.getObjectInputStream();
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void removeFirst() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new RemoveFirstCommand(this)));
        ObjectInputStream objectInputStream = connection.getObjectInputStream();
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void printFieldDescendingNumberOfRooms() throws IOException, ClassNotFoundException {
        connection.sendSerializableCommand(new SerializableCommandStandard(new PrintFieldNumberOfRoomsCommand(this)));
        ObjectInputStream objectInputStream = connection.getObjectInputStream();
        String ans = connection.getStringAnsFromServer();
        Messages.normalMessageOutput(ans, MessageColor.ANSI_YELLOW);
    }

    public void getHead() {

    }

    public void countLessNumberOfRooms(int number) {

    }

    public void printElementWithMinCoordinates() {

    }

    public void removeById(Long id) {
    }

    public void printAllElements() {

    }

    public void addElement(Scanner scanner, Flat flat) {

    }

    public void updateElementById(Scanner scanner, Flat flat, Long id) {

    }

    public void removeLowerElements(Scanner scanner, Flat flat) {

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
