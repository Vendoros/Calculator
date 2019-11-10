package calculator;

import calculator.parser.Parser;
import calculator.converter.Converter;

public class Calculator {


    private final String leftOperand;
    private final char operator;
    private final String rightOperand;


    public Calculator(String str) {
        Parser.parse(str);
        this.leftOperand = Parser.getLeftOperand();
        this.operator = Parser.getOperator();
        this.rightOperand = Parser.getRightOperand();
    }

    public String initCalc() {

        String result = "";
        if (isArabic()) {
            result += calculateToArabic();
        } else {
            result += Converter.arabicToRoman(calculateToRoman());
        }
        return result;
    }

    private int calculateToRoman() {
        output("" + leftOperand + operator + rightOperand);
        switch (operator) {
            case '+':
                return Converter.romanToArabic(leftOperand) + Converter.romanToArabic(rightOperand);
            case '-':
                return Converter.romanToArabic(leftOperand) - Converter.romanToArabic(rightOperand);
            case '*':
                return Converter.romanToArabic(leftOperand) * Converter.romanToArabic(rightOperand);
            case '/':
                return Converter.romanToArabic(leftOperand) / Converter.romanToArabic(rightOperand);
            default:
                System.err.println("Не могу распознать оператор. Завершение работы.");
                System.exit(-1);
        }
        return 0;
    }

    private boolean isArabic() {
        try {
            Integer.parseInt(leftOperand);
            Integer.parseInt(rightOperand);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private int calculateToArabic() {
        output("" + leftOperand + operator + rightOperand);
        switch (operator) {
            case '+':
                return Integer.parseInt(leftOperand) + Integer.parseInt(rightOperand);
            case '-':
                return Integer.parseInt(leftOperand) - Integer.parseInt(rightOperand);
            case '*':
                return Integer.parseInt(leftOperand) * Integer.parseInt(rightOperand);
            case '/':
                return Integer.parseInt(leftOperand) / Integer.parseInt(rightOperand);
            default:
        }
        return 0;
    }

    //выводит строку на консоль
    private void output(String str) {
        System.out.print("\u001b[32m");
        System.out.printf("Вычисляем: %s \n", str);
        System.out.print("\u001b[0m");
    }
}
