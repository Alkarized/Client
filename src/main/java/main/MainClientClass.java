package main;

import java.io.*;
import java.net.Socket;


//TODO make UniqId in Server!
public class MainClientClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost",1707);
        System.out.println("Соединение открыто - " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

        socket.close();
    }
}

class TestClass implements Serializable{
    private static final long serialVersionUID = 1012312321;
    int x = 2;
}
