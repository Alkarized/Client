package main;

import utils.ProgramStarter;

import java.io.*;
import java.net.Socket;


//TODO make UniqId in Server!
public class MainClientClass {
    public static void main(String[] args) throws IOException{

        ProgramStarter programStarter = new ProgramStarter("localhost",1707);
        programStarter.start();
    }
}
