package calculator.parser;

public class Parser {

    private static String leftOperand = null;
    private static char operator;
    private static String rightOperand = null;

    public static String getLeftOperand() {
        return leftOperand;
    }

    public static char getOperator() {
        return operator;
    }

    public static String getRightOperand() {
        return rightOperand;
    }

    public static void parse(String str) {
        str = toValidFormat(str);
        getOperand(str);
        getOperator(str);
    }

    public static String toValidFormat(String str) {
        str = str.replaceAll("\\s+", "").toUpperCase();
        return str;
    }


    private static void getOperator(String str) {
        if (str.contains("+")) {
            operator = '+';
        } else if (str.contains("-")) {
            operator = '-';
        } else if (str.contains("*")) {
            operator = '*';
        } else if (str.contains("/") || str.contains(":")) {
            operator = '/';
        }
    }

    public static void getOperand(String str) {
        try {
            String[] temp = str.split("[/*+\\-:]");
            leftOperand = temp[0];
            rightOperand = temp[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Индекс [i] находится вне границ значений. Вероятно вы ввели пустую строку.\nЗавершение работы");
            System.exit(1);
        }
    }


}
