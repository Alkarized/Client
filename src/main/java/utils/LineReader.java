
package utils;

import client.Invoker;

import java.util.Scanner;


public class LineReader {

    public void readLine(Scanner scanner, Invoker invoker, boolean flag) {
        if (flag)
            System.out.print("Введите команду: ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] args = line.trim().split(" ");
            if(args.length == 1 && args[0].equals("")){
                if (flag)
                    System.out.print("Введите команду: ");
            } else if (args.length != 0){
                invoker.executeCommand(scanner, args);
                if (flag)
                    System.out.print("Введите команду: ");
            }
        }
    }
}