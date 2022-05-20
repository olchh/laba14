package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    static class Veragenie implements Serializable {
        double x, y;
        Veragenie() {
            x = 0;
            y = 0;
        }
        void vechitan (double x) {
            this.x = x;
            y = this.x - Math.sin(this.x); //y = x - sinx
            System.out.println(y);
        }
    }

    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);
        Veragenie y = new Veragenie();
        System.out.println("введите значение");
        while (true) {
            input = in.nextLine();
            try {
                double x = Double.parseDouble(input); //перевод в тип double
                y.vechitan(x);
            } catch (Exception IOex) {
                if (input.equalsIgnoreCase("save")) { //сравнивает команду без учета регистров
                    try (ObjectOutputStream fuf = new ObjectOutputStream(new FileOutputStream("file.txt"))) {
                        fuf.writeObject(y);
                        System.out.println("команда сработала");
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                } else if (input.equalsIgnoreCase("upload")) {
                    try (ObjectInputStream pup = new ObjectInputStream(new FileInputStream("file.txt"))) {
                        y = (Veragenie) pup.readObject();
                        System.out.println("команда сработала");
                    } catch (Exception ex) {
                        ex.getMessage();
                    }
                }
                else {
                    System.out.println("команда не сработала, повторите снова (save, upload)");
                }
            }
        }
    }

    }
