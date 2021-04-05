package client;

import main.Serial;
import commands.serializable_commands.SerializableCommandStandard;
import message.MessageColor;
import message.Messages;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private ObjectInputStream objectInputStream;
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

    public void getObjectInputStream(){
        try {
            objectInputStream =  new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringAnsFromServer() throws IOException, ClassNotFoundException {
        String ans = (String) objectInputStream.readObject();
        objectInputStream.close();
        return ans;
    }

    public Serial getFileResponseFromServer(){
        getObjectInputStream();
        //FileResponse fileResponse = FileResponse.NothingAccepted;
        Serial serial = null;
        try {
            //fileResponse = (FileResponse) objectInputStream.readObject();
            serial = (Serial) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Messages.normalMessageOutput("Ошибка получения ответа от сервера о файле", MessageColor.ANSI_RED);
        }

        //return fileResponse;
        return serial;
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
