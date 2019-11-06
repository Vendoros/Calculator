package calculator.parser;

public class Parser {

    private static String leftOperand;
    private static char operator ;
    private static String rightOperand;

    public Parser() {
    }

    // TODO: 06.11.2019 приведение типов геторов
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
        str = str.replaceAll("\\s+", "").toUpperCase();
        getOperand(str);
        getOperator(str);

    }



    private static void getOperator(String str) {
        if(str.contains("+")){
            operator = '+';
        }else if (str.contains("-")){
            operator = '-';
        }else if (str.contains("*")){
            operator = '*';
        }else if (str.contains("/")){
            operator = '/';
        }
    }

    private static void getOperand(String str) {
        try {
            String[] temp = str.split("[/*+-]");
            leftOperand = temp[0];
            rightOperand = temp[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Индекс [i] находится вне границ значений. Вероятно вы ввели пустую строку.\nЗавершение работы");
            System.exit(1);
        }
    }
}
