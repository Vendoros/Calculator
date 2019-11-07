package calculator.validator;

import calculator.converter.Converter;
import calculator.parser.Parser;

public class IsValid {


    public static boolean initValidate(String str) {
        str = Parser.toValidFormat(str);
        Parser.getOperand(str);
        boolean LEFTOPERAND = inRange(Parser.getLeftOperand());
        boolean RIGHTOPERAND = inRange(Parser.getRightOperand());
        return isEmpty(str) & (LEFTOPERAND & RIGHTOPERAND) & isValidFormat(str) & isOnlyOneType(str);
    }


    private static boolean inRange(String operand) {
        boolean flag = true;




        return flag;
    }

    private static boolean isRoman(String operand) {
        if (Converter.romanToArabic(operand) == -1 ) {
            // TODO: 07.11.2019 logging & Exception
            return false;
        }
        return true;
    }

    private static boolean isArabic(String operand) {
        try {
            Integer.parseInt(operand);
            return true;
        } catch (NumberFormatException e) {
            // TODO: 07.11.2019 logging & Exception
            System.err.println("Операнд ["+operand+"] не принадлежит к арабским обозначениям цифр, либо неккоректен.");
            return false;
        }
    }

    private static boolean isOnlyOneType(String str) {

        try {
            Integer.parseInt(Parser.getLeftOperand());
            Integer.parseInt(Parser.getRightOperand());
        } catch (NumberFormatException e) {
            // TODO: 07.11.2019 logging logging & Exception
            System.err.println("Не верный тип операндов. Должны быть одного типа, только римские, либо только арабские");
            return false;
        }
        return true;
    }

    private static boolean isValidFormat(String str) {

        if (!str.matches("^[\\w]{1,4}[/*+-][\\w]{1,4}")) {
            // TODO: 07.11.2019 logging logging & Exception
            System.err.println("Не верный формат выражения.");
            return false;
        }
        return true;
    }

    private static boolean isEmpty(String str) {
        if (str.isEmpty()) {
            // TODO: 07.11.2019 logging & Exception
            System.err.println("Строка пуста.");
            return false;
        }
        return true;
    }
}
