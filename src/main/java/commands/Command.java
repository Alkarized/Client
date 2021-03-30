package commands;

import client.Receiver;

import java.util.Scanner;

public abstract class Command {
    public Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract void execute(String[] args);

    public abstract void execute(String[] args, Scanner scanner);
}
