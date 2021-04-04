package main;

import utils.ProgramStarter;

import java.io.*;
import java.net.Socket;


//TODO make UniqId in Server!
public class MainClientClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String[] test = new String[1];
        test[0] = "test.csv";

        ProgramStarter programStarter = new ProgramStarter("localhost",1707,test);
        programStarter.start();
    }
}
