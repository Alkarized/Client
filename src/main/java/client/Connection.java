package client;

import commands.serializable_commands.SerializableCommandStandard;
import message.MessageColor;
import message.Messages;
import utils.SerializableAnswerToClient;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public Object getAnsFromServer() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return objectInputStream.readObject();

    }

    public SerializableAnswerToClient getStringAnsFromServer() throws IOException, ClassNotFoundException {
        return (SerializableAnswerToClient) getAnsFromServer();
    }

    public void sendSerializableCommand(SerializableCommandStandard serializableCommandStandard) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(serializableCommandStandard);
        objectOutputStream.flush();
        Messages.normalMessageOutput("Отправка данных на сервер!", MessageColor.ANSI_CYAN);

    }

    public void endConnection() throws IOException {
        socket.close();
    }

}
