import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void evaluatePostfix() {
        String easyProblem =      "1 2 +";
        String mediumProblem =    "1 2 3 * +";
        String hardProblem =      "1 2 3 * + 3 + 2 *";
        String superHardProblem = "1 2 3 * + 3 + 2 * 5 4 * +";
        assertEquals(3, Main.evaluatePostfix(easyProblem));
        assertEquals(7, Main.evaluatePostfix(mediumProblem));
        assertEquals(20, Main.evaluatePostfix(hardProblem));
        assertEquals(40, Main.evaluatePostfix(superHardProblem));
    }

    @org.junit.jupiter.api.Test
    void infixToPostfix() {
        String easyProblem =      "1 + 2";
        String mediumProblem =    "1 + 2 * 3";
        String hardProblem =      "A + B - C * D + E";
        String superHardProblem = "A * B + A / B";
        assertEquals("1 2 + ", Main.infixToPostfix(easyProblem));
        assertEquals("1 2 3 * + ", Main.infixToPostfix(mediumProblem));
        assertEquals("A B + C D * - E + ", Main.infixToPostfix(hardProblem));
        assertEquals("A B * A B / + ", Main.infixToPostfix(superHardProblem));
    }
}