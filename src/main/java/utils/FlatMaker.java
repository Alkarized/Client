package utils;

import fields.*;

import java.util.Arrays;
import java.util.Scanner;


public class FlatMaker {


    public Flat makeFlat(Scanner scanner) {
        Flat flat = new Flat();
        String line;
        while (true) {
            System.out.print("Введите значение для поля area: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (flat.setArea(Long.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля area, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля name: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (flat.setName(line)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля name, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля numberOfRooms: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (flat.setNumberOfRooms(Integer.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля numberOfRooms, попробуйте еще раз или напишите end");
            }
        }

        House house = new House();
        System.out.println("Теперь необходимо создать объект дома, для этого:");
        while (true) {
            System.out.print("Введите значение для поля name: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (house.setName(line)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля name, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля year: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (house.setYear(Long.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля year, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля numberOfFlatsOnFloor: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (house.setNumberOfFlatsOnFloor(Long.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля numberOfFlatsOnFloor, попробуйте еще раз или напишите end");
            }
        }

        flat.setHouse(house);

        Coordinates coordinates = new Coordinates();
        System.out.println("Теперь необходимо создать Координаты, для этого:");
        while (true) {
            System.out.print("Введите значение для поля x: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (coordinates.setX(Integer.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля x, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля y: ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (coordinates.setY(Float.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля y, попробуйте еще раз или напишите end");
            }
        }

        flat.setCoordinates(coordinates);

        while (true) {
            System.out.print("Введите значение для поля transport, есть такие значения + " + Arrays.toString(Transport.values()) + " : ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (flat.setTransport(Transport.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля transport, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля view, есть такие значения + " + Arrays.toString(View.values()) + " : ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (flat.setView(View.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля view, попробуйте еще раз или напишите end");
            }
        }

        while (true) {
            System.out.print("Введите значение для поля furnish, есть такие значения + " + Arrays.toString(Furnish.values()) + " : ");
            try {
                if (scanner.hasNextLine()) {
                    line = scanner.nextLine().trim();
                } else {
                    return null;
                }
                System.out.println();
                if (line.equals("end")) {
                    System.out.println("Ну как скажите, тогда дальше не пойдем.");
                    return null;
                }
                if (flat.setFurnish(Furnish.valueOf(line))) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода поля furnish, попробуйте еще раз или напишите end");
            }
        }

        return flat;
    }

    private int checkAmountOfCommasInLine(String line) {
        int amount = 0;
        for (char x : line.toCharArray()) {
            if (x == ',') amount++;
        }
        return amount;
    }
}
