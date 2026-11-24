import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n------- Advanced Calculator -------");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.println("6. Power (x^y)");
            System.out.println("7. Square Root (\u221Ax)");
            System.out.println("8. Percentage (%)");

            int choice = readInt(sc, "Choose an option: ");

            if (choice == 5) {
                System.out.println("Exiting... Thank you!");
                break;
            }

            if (choice < 1 || choice > 8) {
                System.out.println("Invalid choice!");
                continue;
            }

            double result;

            switch (choice) {

                case 1: {
                    double a = readDouble(sc, "Enter the first number: ");
                    double b = readDouble(sc, "Enter the second number: ");
                    result = a + b;
                    break;
                }

                case 2: {
                    double a = readDouble(sc, "Enter the first number: ");
                    double b = readDouble(sc, "Enter the second number: ");
                    result = a - b;
                    break;
                }

                case 3: {
                    double a = readDouble(sc, "Enter the first number: ");
                    double b = readDouble(sc, "Enter the second number: ");
                    result = a * b;
                    break;
                }

                case 4: {
                    double a = readDouble(sc, "Enter the first number: ");
                    double b = readDouble(sc, "Enter the second number: ");
                    if (b == 0) {
                        System.out.println("Error: Cannot divide by zero!");
                        continue;
                    }
                    result = a / b;
                    break;
                }

                case 6: {
                    double a = readDouble(sc, "Enter the base: ");
                    double b = readDouble(sc, "Enter the power: ");
                    result = Math.pow(a, b);
                    if (Double.isNaN(result) || Double.isInfinite(result)) {
                        System.out.println("Error: Result is not a valid number!");
                        continue;
                    }
                    break;
                }

                case 7: {
                    double a = readDouble(sc, "Enter the number: ");
                    if (a < 0) {
                        System.out.println("Error: Cannot take square root of a negative number!");
                        continue;
                    }
                    result = Math.sqrt(a);
                    break;
                }

                case 8: {
                    double base = readDouble(sc, "Enter the base number: ");
                    double percent = readDouble(sc, "Enter the percentage: ");
                    result = (base * percent) / 100;
                    System.out.println(format(percent) + "% of " + format(base) + " = " + format(result));
                    continue; // result already printed
                }

                default:
                    System.out.println("Invalid choice!");
                    continue;
            }

            System.out.println("Result: " + format(result));
        }

        sc.close();
    }

    // Keeps asking until the user types a whole number
    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                return sc.nextInt();
            }
            System.out.println("Invalid input! Please enter a whole number.");
            sc.next(); // throw away the bad input
        }
    }

    // Keeps asking until the user types a number
    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                return sc.nextDouble();
            }
            System.out.println("Invalid input! Please enter a number.");
            sc.next(); // throw away the bad input
        }
    }

    // Prints numbers without a useless ".0" and without long rounding errors
    private static String format(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return String.valueOf(value);
        }
        return BigDecimal.valueOf(value)
                .round(new MathContext(12))
                .stripTrailingZeros()
                .toPlainString();
    }
}
