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

import static calculator.validator.IsValid.initValidate;

public class Main {


    public static void main(String... args) {
        System.out.print("\u001b[32m");
        System.out.print("Введите операцию: ");
        System.out.print("\u001b[0m");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        in.close();


        if (initValidate(str)) {
            System.out.println("Ответ:" + new Calculator(str).initCalc());
        } else {
            System.err.println("Некорректные входные данные. Заверщение работы.");
            System.exit(1);
        }
    }
}
