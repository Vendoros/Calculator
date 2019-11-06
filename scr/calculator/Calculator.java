package calculator;

import calculator.parser.Parser;
import calculator.converter.Converter;
import calculator.converter.RomanNumeral;

public class Calculator {


    public String initCalc(String str) {
        String result = "";

        // TODO: 06.11.2019 проверка строки на валидность
        Parser.parse(str);


        // TODO: 06.11.2019 вычисление результата для арабских цифр DONE
        if (isArabic(Parser.getLeftOperand(), Parser.getRightOperand())) {
            result += calculateToArabic(Parser.getLeftOperand(), Parser.getOperator(), Parser.getRightOperand());
        } else {
            // TODO: 06.11.2019 вычисление результата для римских цифр
            result +=  Converter.arabicToRoman(calculateToRoman(Parser.getLeftOperand(), Parser.getOperator(), Parser.getRightOperand()));
        }
        //calculateToRoman(Parser.getLeftOperand(), Parser.getOperator(), Parser.getRightOperand())


        return result;

    }

    private int calculateToRoman(String leftOperand, char operator, String rightOperand) {
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
        }

        return 0;
    }

    private static boolean isArabic(String leftOperand, String rightOperand) {
        try {
            Integer.parseInt(leftOperand);
            Integer.parseInt(rightOperand);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    private int calculateToArabic(String leftOperand, char operator, String rightOperand) {
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
