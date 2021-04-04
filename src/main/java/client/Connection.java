package client;

import commands.Command;
import commands.serializable_commands.SerializableCommandStandard;
import message.MessageColor;
import message.Messages;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static client.FileResponse.NothingAccepted;

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

    public FileResponse getFileResponseFromServer(){
        ObjectInputStream objectInputStream = null;
        FileResponse fileResponse = FileResponse.NothingAccepted;
        try {
            objectInputStream = getObjectInputStream();
            fileResponse = (FileResponse) objectInputStream.readObject();
            objectInputStream.close();
            return fileResponse;
        } catch (IOException | ClassNotFoundException e) {
            Messages.normalMessageOutput("Ошибка получения ответа от сервера о файле, пробуем еще раз", MessageColor.ANSI_RED);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {}
            return getFileResponseFromServer();
        }

    }

    public void sendFile(File file){
        try {
            ObjectOutputStream objectOutputStream = getObjectOutputStream();
            objectOutputStream.writeObject(file);
            objectOutputStream.flush();
            Messages.normalMessageOutput("Отправка данных на сервер!", MessageColor.ANSI_CYAN);
            objectOutputStream.close();
        } catch (IOException e) {
            Messages.normalMessageOutput("Ошибка отправки файла на сервер", MessageColor.ANSI_RED);
        }

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
