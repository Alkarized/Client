package client;

import commands.Command;
import message.MessageColor;
import message.Messages;

import java.util.HashMap;
import java.util.Scanner;

public class Invoker {

    private final HashMap<String, Command> hashMap = new HashMap<>();

    public void executeCommand(Scanner scanner, String[] args) {
        if (args.length > 0) {
            Command command = hashMap.get(args[0]);
            if (command == null) {
                Messages.normalMessageOutput("Неправильный ввод команды, попробуйте написать help и посмотреть" +
                        " доступные команды", MessageColor.ANSI_RED);
            } else {
                if (scanner.equals(new Scanner(System.in))) {
                    command.execute(args);
                } else {
                    command.execute(args, scanner);
                }
            }
        }
    }

    public void registerNewCommand(String commandName, Command command) {
        hashMap.put(commandName, command);
    }

    public HashMap<String, Command> getHashMap() {
        return hashMap;
    }

}
