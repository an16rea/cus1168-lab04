// Source code is decompiled from a .class file using FernFlower decompiler.
package academy.javapro;

class ExpressionParser {
    private final String input;
    private int position;

    public ExpressionParser(String input) {
        this.input = input;
        this.position = 0;
    }

    public double parseExpression() {
        double result = parseTerm();
        while (position < input.length() && input.charAt(position) == '+') {
            position++;
            result += parseTerm();
        }
        return result;
    }

    private double parseTerm() {
        double result = parseFactor();
        while (position < input.length() && input.charAt(position) == '*') {
            position++;
            result *= parseFactor();
        }
        return result;
    }

    private double parseFactor() {
        if (position < input.length() && input.charAt(position) == '(') {
            position++;
            double result = parseExpression();
            position++;
            return result;
        }
        return parseNumber();
    }

    private double parseNumber() {
        StringBuilder num = new StringBuilder();
        while (position < input.length() && (Character.isDigit(input.charAt(position)) || input.charAt(position) == '.')) {
            num.append(input.charAt(position++));
        }
        return Double.parseDouble(num.toString());
    }

    public static void main(String[] args) {
        String[] testCases = {
                "2 + 3 * (4 + 5)",
                "2 + 3 * 4",
                "(2 + 3) * 4",
                "2 * (3 + 4) * (5 + 6)",
                "1.5 + 2.5 * 3"
        };

        for (String expression : testCases) {
            System.out.println("\nTest Case: " + expression);
            try {
                ExpressionParser parser = new ExpressionParser(expression.replaceAll("\\s+", ""));
                double result = parser.parseExpression();
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}