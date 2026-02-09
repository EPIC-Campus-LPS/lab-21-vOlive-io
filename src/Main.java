import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       Stack ops = new Stack();
       Scanner scan = new Scanner(System.in);
       scan.close();
    }
    public static String evaluatePostfix(String input) {
        input = Arrays.toString(input.trim().split(" "));
        if(input.length() <= 2) throw new IllegalArgumentException("Invalid");
        Stack ops = new Stack();
        String opsList = "+-*/^";
        while(input.length() != 0) {
            String ch = input.substring(input.length()-1);
            if(opsList.indexOf(ch) == -1) {
                ops.push(ch);
            } else {
                int a = Integer.parseInt(ops.pop());
                int b = Integer.parseInt(ops.pop());
                ops.push(doMath(ch, a, b) + "");
            }
            input = input.substring(0, input.length()-1);
        }
        if(ops.size() > 1) throw new IllegalArgumentException("Invalid");
        return ops.peek();
    }
    public static String infixToPostfix(String input) {
        input = Arrays.toString(input.trim().split(" "));
        if(input.length() <= 2) throw new IllegalArgumentException("Invalid");
        Stack ops = new Stack();
        String output = "";
        String opsList = "+-*/^";
        while(input.length() != 0) {
            String ch = input.substring(input.length()-1);
            if(opsList.indexOf(ch) == -1) { //if letter
               output += ch;
            } else { //if not a letter
                if(ops.isEmpty()) {ops.push(ch);}
                else if(checkHigher(ops.peek(), ch)) {
                    output+=ch;
                }
                else {
                    output+=ops.pop();
                    ops.push(ch);
                }
            }
            input = input.substring(0, input.length()-1);
        }
        return output;
    }
    private static boolean checkHigher(String op, String ch) {
        String low = "+-";
        String high = "*/";
        int chLv = Math.max(low.indexOf(ch), high.indexOf(ch));
        int opLv = Math.max(low.indexOf(op), high.indexOf(op));
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