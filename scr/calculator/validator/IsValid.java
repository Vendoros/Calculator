package calculator.validator;

import calculator.converter.Converter;
import calculator.parser.Parser;

public class IsValid {
    private static final String letterRegex = "^[A-zА-я&&[^IVXLCDM_`^\\[\\]]]+$";
    private static final String specSymbolRegex = "^[^A-zА-я0-9]+$";
    private static final String validFormatRegex = "^.+[/*+-].+$";
    private String leftOperand;
    private String rightOperand;
    private String expression;
    private int i, j, k;

    public IsValid() {
    }

    public IsValid(String expression) {
        this.expression = Parser.toValidFormat(expression);
        Parser.getOperand(this.expression);
        this.leftOperand = Parser.getLeftOperand();
        this.rightOperand = Parser.getRightOperand();
        this.i = 1;
        this.j = 1;
        this.k = 1;//для обозначения типа ошибки
    }

    public boolean initValidate() {
        i++;
        if (isValidFormat(expression, i, j, k)) {
            i++;
            if (isNumber(leftOperand, rightOperand, i, j, k)) {
                i++;
                if (isOnlyOneType(leftOperand, rightOperand, i, j, k)) {
                    i++;
                    return inRange(leftOperand, rightOperand, i, j, k);
                }
            }
        }
        return false;
    }


    public boolean inRange(String leftOperand, String rightOperand, int i, int j, int k) {
        int leftNumeral;
        int rightNumber;
        if (isArabic(leftOperand)) {
            leftNumeral = Integer.parseInt(leftOperand);
            if (isArabic(rightOperand)) {
                rightNumber = Integer.parseInt(rightOperand);
                if (leftNumeral <= 10 & leftNumeral > 0) {
                    if (rightNumber <= 10 & rightNumber > 0) {
                        return true;
                    }
                }
                System.err.println("Левое число [" + leftOperand + "] не принадлежит допустимому диапазону значений.");

                if (!(rightNumber <= 10 & rightNumber > 0)) {
                    System.err.println("Правое число [" + rightOperand + "] не принадлежит допустимому диапазону значений.");
                }
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//5.1.1
                return false;
            }
            k++;
            System.err.println("Аномалия.");
            System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//5.1.2
        }
        j++;
        if (isRoman(leftOperand)) {
            leftNumeral = Converter.romanToArabic(leftOperand);
            if (isRoman(rightOperand)) {
                rightNumber = Converter.romanToArabic(rightOperand);
                if (leftNumeral <= 10) {
                    if (rightNumber <= 10) {
                        return true;
                    }
                }
                System.err.println("Левое число [" + leftOperand + "] не принадлежит допустимому диапазону значений.");
                if (!(rightNumber <= 10)) {
                    System.err.println("Правое число [" + rightOperand + "] не принадлежит допустимому диапазону значений.");
                }
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//5.2.1
                return false;
            }
            k++;
            System.err.println("Аномалия.");
            System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//5.2.2
            return false;
        }
        j++;
        k = 1;
        System.err.println("Аномалия.");
        System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//5.3.1
        return false;
    }

    private boolean isArabic(String operand) {
        try {
            Integer.parseInt(operand);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isRoman(String operand) {
        return Converter.romanToArabic(operand) != -1;
    }

    public boolean isOnlyOneType(String leftOperand, String rightOperand, int i, int j, int k) {

        if (isArabic(leftOperand)) {
            if (isArabic(rightOperand)) {
                return true;
            }
            if (isRoman(rightOperand)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является арабским символом числа.");
                System.err.println("Правый операнд [" + rightOperand + "] является римским символом числа.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.1.1
                return false;
            }
            k++;
            if (rightOperand.matches(letterRegex)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является арабским символом числа.");
                System.err.println("Правый операнд [" + rightOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.1.2
                return false;
            }
            k++;
            if (rightOperand.matches(specSymbolRegex)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является арабским символом числа.");
                System.err.println("Правый операнд [" + rightOperand + "] является не буквенно-цифровым символом.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.1.3
                return false;
            } else {
                k++;
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является арабским символом числа.");
                System.err.println("Правый операнд [" + rightOperand + "] является аномалией.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.1.4
                return false;
            }
        }
        j++;
        k = 1;
        if (isRoman(leftOperand)) {
            if (isRoman(rightOperand)) {
                return true;
            }
            if (isArabic(rightOperand)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является римским символом числа.");
                System.err.println("Правый операнд [" + rightOperand + "] является арабским символом числа.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.2.1
                return false;
            }
            k++;
            if (rightOperand.matches(letterRegex)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является римским символом числа.");
                System.err.println("Правый операнд [" + rightOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.2.2
                return false;
            }
            k++;
            if (rightOperand.matches(specSymbolRegex)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является римским символом числа.");
                System.err.println("Правый операнд [" + rightOperand + "] является не буквенно-цифровым символом.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.2.3
                return false;
            } else {
                k++;
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] римским символом числа.");
                System.err.println("Правый операнд [" + rightOperand + "] является аномалией.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.2.3
                return false;
            }
        }
        j++;
        k = 1;
        if (leftOperand.matches(letterRegex)) {
            if (isArabic(rightOperand)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Правый операнд [" + rightOperand + "] является арабским символом числа");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.3.1
                return false;
            }
            k++;
            if (isRoman(rightOperand)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Правый операнд [" + rightOperand + "] является римским символом числа.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.3.2
                return false;
            }
            k++;
            if (rightOperand.matches(letterRegex)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Правый операнд [" + rightOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.3.3
                return false;
            }
            k++;
            if (rightOperand.matches(specSymbolRegex)) {
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Правый операнд [" + rightOperand + "] является не буквенно-цифровым символом.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.3.4
                return false;
            } else {
                k++;
                System.err.println("Разный тип операндов.");
                System.err.println("Левый операнд [" + leftOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Правый операнд [" + rightOperand + "] является аномалией.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.3.4
                return false;
            }
        }
        j++;
        k = 1;
        if (leftOperand.matches(specSymbolRegex)) {
            System.err.println("Разный тип операндов.");
            System.err.println("Левый операнд [" + leftOperand + "] является не буквенно-цифровым символом.");
            if (isArabic(rightOperand)) {
                System.err.println("Правый операнд [" + rightOperand + "] является арабским символом числа.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.4.1
            }
            k++;
            if (isRoman(rightOperand)) {
                System.err.println("Правый операнд [" + rightOperand + "] является римским символом числа.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.4.2

            }
            k++;
            if (rightOperand.matches(letterRegex)) {
                System.err.println("Правый операнд [" + rightOperand + "] является буквой не относящейся к римским обозначениям чисел.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.4.3
            }
            k++;
            if (rightOperand.matches(specSymbolRegex)) {
                System.err.println("Правый операнд [" + rightOperand + "] является не буквенно-цифровым символом.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.4.4
            } else {
                k++;
                System.err.println("Правый операнд [" + rightOperand + "] является аномалией.");
                System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.4.5
            }
            return false;
        }
        j++;
        k = 0;
        System.err.println("Аномалия.");
        System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//4.5.1
        return false;
    }

    private boolean isValidFormat(String expression, int i, int j, int k) {
        if (expression.matches(validFormatRegex)) {
            return true;
        }
        System.err.println("Не верный формат выражения.");
        System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//3.1.1
        return false;
    }

    public boolean isNumber(String leftOperand, String rightOperand, int i, int j, int k) {
        if (isArabic(leftOperand)) {
            if (isArabic(rightOperand)) {
                return true;
            }
            if (isRoman(rightOperand)) {
                return true;
            }
        }
        if (isRoman(leftOperand)) {
            if (isRoman(rightOperand)) {
                return true;
            }
            if (isArabic(rightOperand)) {
                return true;
            }
        }
        System.err.println("Один из двух оперантов имеет не  целочисленное значение. ");
        System.err.println("Код ошибки [" + i + "." + j + "." + k + "]");//2.1.1
        return false;
    }
}
