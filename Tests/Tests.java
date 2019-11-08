import calculator.Calculator;
import calculator.validator.IsValid;
import org.junit.Test;

//import static org.assertj.core.api.Assertions.catchThrowable;
//import static org.assertj.core.api.Assertions.assertThat;
import static calculator.validator.IsValid.*;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void testArabic() {
        assertEquals("2", new Calculator(" 4 - 2 ").initCalc());
        assertEquals("6", new Calculator("4 + 2").initCalc());
        assertEquals("8", new Calculator("4* 2").initCalc());
        assertEquals("2", new Calculator("   4/2").initCalc());
        assertEquals("100", new Calculator("10*10    ").initCalc());
    }

    @Test
    public void testRoman() {
        assertEquals("IV", new Calculator("iI*Ii").initCalc());
        assertEquals("LXXXI", new Calculator("Ix* I x").initCalc());
        assertEquals("V", new Calculator("    x   /  I    I    ").initCalc());
        assertEquals("IX", new Calculator("IV+V").initCalc());
        assertEquals("I", new Calculator("V-IV").initCalc());
    }

    @Test
    public void testOnlyOneType(){

        assertTrue(new IsValid().isOnlyOneType("1","2",4,1,1));
        assertTrue(new IsValid().isOnlyOneType("I","VI",4,1,1));

        System.out.println("9 & II");
        assertFalse(new IsValid().isOnlyOneType("9","II",4,1,1));
        System.out.println("I & 8");
        assertFalse(new IsValid().isOnlyOneType("I","8",4,1,1));
        System.out.println("a & b");
        assertFalse(new IsValid().isOnlyOneType("a","b",4,1,1));
        System.out.println("2 & b");
        assertFalse(new IsValid().isOnlyOneType("2","b",4,1,1));
        System.out.println("f & IX");
        assertFalse(new IsValid().isOnlyOneType("f","IX",4,1,1));
        System.out.println("@ & (");
        assertFalse(new IsValid().isOnlyOneType("@","(",4,1,1));
        System.out.println("6 & $");
        assertFalse(new IsValid().isOnlyOneType("6","$",4,1,1));
        System.out.println("IV & #");
        assertFalse(new IsValid().isOnlyOneType("IV","#",4,1,1));
    }

    @Test
    public void testIsNumber(){

        assertTrue(new IsValid().isNumber("1","1" , 2,1,1));
        assertTrue(new IsValid().isNumber("1","X" , 2,1,1));
        assertTrue(new IsValid().isNumber("XI","10" , 2,1,1));
        assertTrue(new IsValid().isNumber("X","X" , 2,1,1));
        assertTrue(new IsValid().isNumber("IX","IX" , 2,1,1));
        assertTrue(new IsValid().isNumber("X","X" , 2,1,1));

        assertFalse(new IsValid().isNumber("q1","1",2,1,1));
        assertFalse(new IsValid().isNumber("q1","IX",2,1,1));
        assertFalse(new IsValid().isNumber("q1","q1",2,1,1));
        assertFalse(new IsValid().isNumber("q1","@",2,1,1));
        assertFalse(new IsValid().isNumber("@","q1",2,1,1));
        assertFalse(new IsValid().isNumber("@","1",2,1,1));
        assertFalse(new IsValid().isNumber("@","I",2,1,1));
        assertFalse(new IsValid().isNumber("@","@",2,1,1));
        assertFalse(new IsValid().isNumber("@@","IX",2,1,1));
        assertFalse(new IsValid().isNumber("@@","1",2,1,1));
        assertFalse(new IsValid().isNumber("@@","q1",2,1,1));
        assertFalse(new IsValid().isNumber("@@","@@",2,1,1));
    }

    @Test
    public void testInRange(){
        assertTrue(new IsValid().inRange("1","9",6,1,1));
        assertTrue(new IsValid().inRange("10","10",6,1,1));
        assertTrue(new IsValid().inRange("10","2",6,1,1));
        assertTrue(new IsValid().inRange("8","2",6,1,1));
        System.out.println();

        assertFalse(new IsValid().inRange("11","2",6,1,1));
        assertFalse(new IsValid().inRange("2","11",6,1,1));
        assertFalse(new IsValid().inRange("-1","11",6,1,1));
        assertFalse(new IsValid().inRange("11","-1",6,1,1));
        System.out.println();

        assertTrue(new IsValid().inRange("IV","IX",6,1,1));
        assertTrue(new IsValid().inRange("IX","VI",6,1,1));
        assertTrue(new IsValid().inRange("X","V",6,1,1));
        assertTrue(new IsValid().inRange("I","V",6,1,1));
        System.out.println();

        assertFalse(new IsValid().inRange("M","V",6,1,1));
        assertFalse(new IsValid().inRange("V","XII",6,1,1));
        assertFalse(new IsValid().inRange("II","XI",6,1,1));
        assertFalse(new IsValid().inRange("XI","II",6,1,1));


    }


//    @Test
//    public void testInvalidInputData() throws IllegalArgumentException {
//
//        Throwable thrown = catchThrowable(() -> {
//            new Calculator("1+V").initCalc();
//        });
//        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
//        assertThat(thrown.getMessage()).isNotBlank();
//
//    }
}
