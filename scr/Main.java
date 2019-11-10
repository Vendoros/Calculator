
import calculator.Calculator;
import calculator.validator.IsValid;

import java.util.Scanner;

/**
 * Консольное приложение “Калькулятор”.
 * Приложение должно читать из консоли введенные пользователем арифметические операции и выводить в консоль результат их выполнения.
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
        String str = null;
        try {
            str = in.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        try {
            assert str != null;
            if (str.isEmpty()) {
                System.err.println("Строка пуста.");
                System.err.println("Код ошибки 1.1.1");
                System.exit(-1);
            }
        }catch (NullPointerException e){
            System.err.println("Строка содержит null.");
            System.err.println("Код ошибки 1.1.2");
            System.exit(-1);
        }
        try{
        if (new IsValid(str).initValidate()) {
            System.out.println("Ответ:" + new Calculator(str).initCalc());
            System.out.print("\u001b[35m");
            System.out.println("Калькулятор отработал в штатном режиме.");
            System.out.print("\u001b[0m");
        } }catch (Exception e){
            System.err.println("Калькулятор не смог корректно завершить вычисление.");
            System.exit(-1);
        }finally {
            System.out.println("Пока!");
        }
    }
}
