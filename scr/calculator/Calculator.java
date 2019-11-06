package calculator;

import java.util.Scanner;

public class Calculator {

    private static String operation;

    public static void start() {
        operation = input();
        output(operation);

        //TODO

    }

    private static String input(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите операцию: ");
        String str = in.nextLine();
        in.close();
        return str;
    }

    private static void output(String str){
        System.out.println("\u001b[32m");
        System.out.printf("Вычисляем: %s \n", str);
        System.out.println("\u001b[0m");
    }
}
