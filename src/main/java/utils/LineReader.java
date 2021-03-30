
package utils;

import client.Invoker;

import java.util.Scanner;


public class LineReader {

    public void readLine(Scanner scanner, Invoker invoker) {
        System.out.print("Введите команду: ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] args = line.trim().split(" ");
            if(args.length == 1 && args[0].equals("")){
                System.out.print("Введите команду: ");
                continue;
            } else if (args.length != 0){
                invoker.executeCommand(scanner, args);
                System.out.print("Введите команду: ");
            }
        }
    }
}