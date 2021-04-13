package client;

import commands.serializable_commands.SerializableCommandStandard;
import message.MessageColor;
import message.Messages;
import utils.SerializableAnswerToClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class Connection {
    private Socket socket;
    private final String host;
    private final int port;

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Object getAnsFromServer() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return objectInputStream.readObject();

    }

    public SerializableAnswerToClient getStringAnsFromServer() throws IOException, ClassNotFoundException {
        return (SerializableAnswerToClient) getAnsFromServer();
    }

    public boolean sendSerializableCommand(SerializableCommandStandard serializableCommandStandard) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(serializableCommandStandard);
            objectOutputStream.flush();
            Messages.normalMessageOutput("Отправка данных на сервер!", MessageColor.ANSI_CYAN);
            return true;
        } catch (SocketException e) {
            socket.close();
            Messages.normalMessageOutput("В данный момент сервер недоступен", MessageColor.ANSI_RED);
            socket = startConnection(host, port);
            return false;
        }

    }

    public void endConnection() throws IOException {
        socket.close();
    }

    public Socket startConnection(String host, int port) {
        Socket socket;
        try {
            socket = new Socket(InetAddress.getByName(host), port);
            System.out.println("Соединение открыто - " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
            socket.setSoTimeout(1000 * 2);
            return socket;
        } catch (IOException e) {
            Messages.normalMessageOutput("Нет возможности подключиться, попробуем еще раз!", MessageColor.ANSI_RED);
            return startConnection(host, port);
        }

    }

}
