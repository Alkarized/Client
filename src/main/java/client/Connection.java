package client;

import main.FileResponse;
import main.Serial;
import commands.serializable_commands.SerializableCommandStandard;
import message.MessageColor;
import message.Messages;

import java.io.*;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private final ObjectOutputStream objectOutputStream;

    public Connection(Socket socket) {
        this.socket = socket;
        objectOutputStream = getObjectOutputStream();
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getObjectOutputStream() {
        try {
            return new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObjectInputStream getObjectInputStream() throws IOException {
        return new ObjectInputStream(socket.getInputStream());

    }

    public String getStringAnsFromServer() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = getObjectInputStream();;
        String ans = (String) objectInputStream.readObject();
        objectInputStream.close();
        return ans;
    }

    public FileResponse getFileResponseFromServer(){
        ObjectInputStream objectInputStream = null;
        FileResponse fileResponse = FileResponse.NothingAccepted;
        Serial serial = null;
        try {
            objectInputStream = getObjectInputStream();
            fileResponse = (FileResponse) objectInputStream.readObject();
        } catch (StreamCorruptedException e){
        } catch (IOException | ClassNotFoundException e) {
            Messages.normalMessageOutput("Ошибка получения ответа от сервера о файле", MessageColor.ANSI_RED);
        }

        return fileResponse;
    }

    public void sendFile(File file){
        try {
            objectOutputStream.writeObject(file);
            objectOutputStream.flush();
            Messages.normalMessageOutput("Отправка данных на сервер!", MessageColor.ANSI_CYAN);
        } catch (IOException e) {
            Messages.normalMessageOutput("Ошибка отправки файла на сервер", MessageColor.ANSI_RED);
        }

    }

// у классов можно написать свой метод для сериализации, если наследоваться от Externable
    public void sendSerializableCommand(SerializableCommandStandard serializableCommandStandard) throws IOException {
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
