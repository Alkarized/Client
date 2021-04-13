package commands;

import client.Receiver;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Command implements Serializable{
    private static final long serialVersionUID = 12311;

    protected transient Receiver receiver;

    public Command() {

    }

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract void execute(String[] args);

    public abstract void execute(String[] args, Scanner scanner);
}
