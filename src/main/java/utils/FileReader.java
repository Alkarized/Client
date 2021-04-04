package utils;

import message.MessageColor;
import message.Messages;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class FileReader {

    public String getFileNameFromArgs(String[] args) {
        String fileName = null;
        if (args.length == 1) {
            fileName = args[0] + setFormat(checkFormat(args[0]));
            Messages.normalMessageOutput("Введеный файл - " + fileName, MessageColor.ANSI_BLUE);
        } else {
            Messages.normalMessageOutput("Неправильный ввод, что-то не так с аргументами при запуске программы, " +
                    "попробуйте еще раз", MessageColor.ANSI_RED);
            System.exit(1);
        }
        return fileName;
    }

    private boolean checkFormat(String arg) {
        StringBuilder lastFourWords = new StringBuilder();
        if (arg.length() >= 4) {
            for (int i = 4; i > 0; i--) {
                lastFourWords.append(arg.charAt(arg.length() - i));
            }
        }

        return lastFourWords.toString().toLowerCase().equals(".csv");
    }

    private String setFormat(boolean isNotNeedToSet) {
        if (isNotNeedToSet) {
            return "";
        } else {
            return ".csv";
        }
    }


}
