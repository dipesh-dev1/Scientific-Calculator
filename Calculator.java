package Project;

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
            System.out.println("7. Square Root (√x)");
            System.out.println("8. Percentage (%)");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            if (choice == 5) {
                System.out.println("Exiting... Thank you!");
                break;
            }

            double num1 = 0, num2 = 0;

            // Square root only needs one number
            if (choice == 7) {
                System.out.print("Enter the number: ");
                num1 = sc.nextDouble();
            }
            // All other operations (except sqrt)
            else if (choice != 5) {
                System.out.print("Enter the first number: ");
                num1 = sc.nextDouble();

                System.out.print("Enter the second number: ");
                num2 = sc.nextDouble();
            }

            double result = 0;

            switch (choice) {

                case 1:
                    result = num1 + num2;
                    break;

                case 2:
                    result = num1 - num2;
                    break;

                case 3:
                    result = num1 * num2;
                    break;

                case 4:
                    if (num2 == 0) {
                        System.out.println("Error: Cannot divide by zero!");
                        continue;
                    }
                    result = num1 / num2;
                    break;

                case 6:
                    result = Math.pow(num1, num2);
                    break;

                case 7:
                    if (num1 < 0) {
                        System.out.println("Error: Cannot take square root of a negative number!");
                        continue;
                    }
                    result = Math.sqrt(num1);
                    break;
                    case 8:
                         System.out.print("Enter the base number: ");
                         num1 = sc.nextDouble();
                         System.out.print("Enter the percentage: ");
                         num2 = sc.nextDouble();
                         result = (num1 * num2) / 100;
                         System.out.println(num2 + "% of " + num1 + " = " + result);
                     break;
                
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }

            System.out.println("Result: " + result);
        }

        sc.close();
    }
}
