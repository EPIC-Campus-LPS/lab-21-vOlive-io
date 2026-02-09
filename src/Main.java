import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Stack ops = new Stack();
       Scanner scan = new Scanner(System.in);
       scan.close();
    }
    public static int evaluatePostfix(String input) {
        String[] inputs = input.split(" ");
        Stack ops = new Stack();
        String opsList = "+-*/^";
        for(String i : inputs) {
            if (opsList.indexOf(i) == -1) {
                ops.push(i);
            } else {
                int a = Integer.parseInt(ops.pop());
                int b = Integer.parseInt(ops.pop());
                ops.push(doMath(i, a, b) + "");
            }
        }
        if(ops.size() > 1) throw new IllegalArgumentException("Invalid");
        return Integer.parseInt(ops.peek());
    }
    public static String infixToPostfix(String input) {
        String[] inputs = input.split(" ");
        Stack ops = new Stack();
        String output = "";
        String opsList = "+-*/^";
        for(String i : inputs) {
            if(opsList.indexOf(i) == -1) { //if letter
               output += i;
               output+=" ";
            } else { //if not a letter
                while(true) {
                    if(ops.isEmpty()) {
                        ops.push(i);
                        break;
                    }
                    else if(checkHigher(ops.peek(), i)) {
                        ops.push(i);
                        break;
                    }
                    else {
                        output+=ops.pop();
                        output+=" ";
                    }
                }
            }
        }
        while(!(ops.isEmpty())) {
            output += ops.pop();
            output += " ";
        }
        return output;
    }
    private static boolean checkHigher(String op, String ch) {
        String low = "+-";
        String high = "*/";
        int chLv = 0; // *    2
        int opLv = 0; // +    1
        if(low.indexOf(ch) != -1) {chLv = 1;}
        if(high.indexOf(ch) != -1) {chLv = 2;}
        if(low.indexOf(op) != -1) {opLv = 1;}
        if(high.indexOf(op) != -1) {opLv = 2;}
        if(chLv > opLv) {return true;}
        return false;
    }

    private static int doMath(String op, int a, int b) {
        switch (op) {
            case "+": return a+b;
            case "-": return a-b;
            case "*": return a*b;
            case "/":
                if(b == 0) throw new ArithmeticException("Can't divide by 0");
                return a/b;
            //case "^":
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }
}