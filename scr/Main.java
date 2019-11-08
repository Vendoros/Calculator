
import calculator.Calculator;
import calculator.validator.IsValid;

import java.util.Scanner;


/**
 * Консольное приложение “Калькулятор”.
 * Приложение должно читать из консоли введенные пользователем арифметические операции и выводить в консоль результат их выполнения.
 * <p>
 * Является решением тестового задания для Java Mentor
 * Автор: Алексей Логачев
 * Дата: 06.11.2019 16:16
 */
public class Main {
    public static void main(String... args) {
        System.out.print("\u001b[32m");
        System.out.print("Введите операцию: ");
        System.out.print("\u001b[0m");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        in.close();

        if (str.isEmpty()) {
            System.err.println("Строка пуста.");
            System.err.println("Код ошибки 1.1.1");
            System.exit(1);
        }
        if (new IsValid(str).initValidate()) {
            System.out.println("Ответ:" + new Calculator(str).initCalc());
        } else {
            System.err.println("Некорректные входные данные.");
            System.err.println("Код ошибки 1.1.2");
        }
        System.out.print("\u001b[35m");
        System.out.println("Калькулятор отработал в штатном режиме.");
        System.out.print("\u001b[0m");
    }
}
