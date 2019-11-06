/**
 * Консольное приложение “Калькулятор”.
 * Приложение должно читать из консоли введенные пользователем арифметические операции и выводить в консоль результат их выполнения.
 * <p>
 * Является решением тестового задания для Java Mentor
 * Автор: Алексей Логачев
 * Дата: 06.11.2019 16:16
 */

import calculator.Calculator;

import java.util.Scanner;

//System.out.println("\u001b[32m"); желтый

public class Main {
    public static void main(String... args) {

        System.out.print("Введите операцию: ");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        in.close();

        //if (!str.isEmpty()) {
            System.out.println("Ответ:" + new Calculator().initCalc(str));
        //} else {
            //System.err.println("Введена пустая строка. Заверщение работы.");
        //}
    }
}
