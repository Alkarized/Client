package client;

import commands.Command;
import commands.serializable_commands.SerializableCommandStandard;
import message.MessageColor;
import message.Messages;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
//comment
public class Connection {
    private final Socket socket;

    public Connection(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(socket.getOutputStream());
    }

    public ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(socket.getInputStream());
    }

    public String getStringAnsFromServer() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = getObjectInputStream();
        String ans = (String) objectInputStream.readObject();
        objectInputStream.close();
        return ans;
    }
// у классов можно написать свой метод для сериализации, если наследоваться от Externable
    public void sendSerializableCommand(SerializableCommandStandard serializableCommandStandard) throws IOException {
        ObjectOutputStream objectOutputStream = getObjectOutputStream();
        objectOutputStream.writeObject(serializableCommandStandard);
        objectOutputStream.flush();
        Messages.normalMessageOutput("Отправка данных на сервер!", MessageColor.ANSI_CYAN);
        objectOutputStream.close();
    }

    public int getPort() {
        return socket.getPort();
    }

    public String getHostAddress() {
        return socket.getInetAddress().getHostAddress();
    }

    public void endConnection() throws IOException {
        socket.close();
    }
}
