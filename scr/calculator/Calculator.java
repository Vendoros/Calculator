package calculator;

import java.util.Scanner;

import calculator.converter.*;

public class Calculator {

    private String operation;

    public void start() {
        operation = input();
        // TODO: 06.11.2019 проверка строки

        output(operation);
        // TODO: 06.11.2019 вычисление результата

    }

    //получаем строку из консоли
    private String input() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите операцию: ");
        String str = in.nextLine();
        in.close();
        return str;
    }

    //выводит строку на консоль
    private void output(String str) {
        System.out.println("\u001b[32m");
        System.out.printf("Вычисляем: %s \n", str);
        System.out.println("\u001b[0m");
    }
}
