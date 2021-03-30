
package utils;


import client.Connection;
import client.Invoker;
import client.Receiver;
import commands.*;
import message.MessageColor;
import message.Messages;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import java.util.Scanner;


public class ProgramStarter {

    private final Invoker invoker;
    private final Receiver receiver;

    public ProgramStarter(String host, int port) throws IOException {
        this.invoker = new Invoker();
        Messages.normalMessageOutput("Подключаемся к серверу...", MessageColor.ANSI_CYAN);
        this.receiver = new Receiver(invoker,new Connection(new Socket(InetAddress.getByName(host), port)));
    }


    public void start() {
        registerAllCommands();
        LineReader lineReader = new LineReader();
        lineReader.readLine(new Scanner(System.in), invoker);
    }

    /*public void addAllFlatsFromCSV() {
        CSVParser csvParser = new CSVParser();
        CSVFileReader csvFileReader = new CSVFileReader();
        ArrayList<String> listOfLines;
        if ((listOfLines = csvFileReader.readAllLines(collectionManager.getFile())) != null) {
            for (int i = 0; i < listOfLines.size(); i++) {
                Flat flat;
                String[] args = csvParser.parseLineToArray(listOfLines.get(i));
                if (args != null) {
                    if ((flat = csvParser.parseArrayToFlat(args)) != null) {
                        collectionManager.getCollection().add(flat);
                    } else {
                        Messages.normalMessageOutput("Программа не смогла считать " + i + " строку, ошибка в формате самих полей, пропускам ее.", MessageColor.ANSI_RED);
                    }
                } else {
                    Messages.normalMessageOutput("Ошибка в строке " + i + ", неправильно составлена CSV таблица, что-то не так с кавычками, пропуск строки", MessageColor.ANSI_RED);
                }
            }
            Messages.normalMessageOutput("Запись данных из файла закончилась", MessageColor.ANSI_GREEN);
        }

    }*/

    public void registerAllCommands() {
        invoker.registerNewCommand("add", new AddCommand(receiver));
        invoker.registerNewCommand("clear", new ClearCommand(receiver));
        invoker.registerNewCommand("count_less_than_number_of_rooms", new CountLessCommand(receiver));
        invoker.registerNewCommand("execute_script", new ExecuteScriptCommand(receiver));
        invoker.registerNewCommand("exit", new ExitCommand(receiver));
        invoker.registerNewCommand("head", new HeadCommand(receiver));
        invoker.registerNewCommand("help", new HelpCommand(receiver));
        invoker.registerNewCommand("info", new InfoCommand(receiver));
        invoker.registerNewCommand("min_by_coordinates", new MinByCoordinatesCommand(receiver));
        invoker.registerNewCommand("print_field_descending_number_of_rooms", new PrintFieldNumberOfRoomsCommand(receiver));
        invoker.registerNewCommand("remove_by_id", new RemoveByIdCommand(receiver));
        invoker.registerNewCommand("remove_first", new RemoveFirstCommand(receiver));
        invoker.registerNewCommand("remove_lower", new RemoveLowerCommand(receiver));
        invoker.registerNewCommand("show", new ShowCommand(receiver));
        invoker.registerNewCommand("update", new UpdateByIdCommand(receiver));
    }
}

