
package utils;


import client.Connection;
import client.Invoker;
import client.Receiver;
import commands.*;
import message.MessageColor;
import message.Messages;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import java.net.UnknownHostException;
import java.util.Scanner;


public class ProgramStarter {

    private final Invoker invoker;
    private final Receiver receiver;
    private final Connection connection;

    public ProgramStarter(String host, int port) throws IOException {
        invoker = new Invoker();
        Messages.normalMessageOutput("Подключаемся к серверу...", MessageColor.ANSI_CYAN);
        connection = new Connection(host, port);
        connection.setSocket(connection.startConnection(host, port));
        receiver = new Receiver(invoker, connection);
    }

    public void start() {
        registerAllCommands();
        LineReader lineReader = new LineReader();
        lineReader.readLine(new Scanner(System.in), invoker, true);
    }

    private void registerAllCommands() {
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

