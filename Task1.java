import java.util.Scanner;

class LogicalPrograms {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nSelect an option:");
            System.out.println("1. Fibonacci Series Generator");
            System.out.println("2. Factorization");
            System.out.println("3. Palindrome Checker");
            System.out.println("4. Armstrong Number Checker");
            System.out.println("5. Factorial Calculator");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the number of terms for Fibonacci series: ");
                    int n = scanner.nextInt();
                    generateFibonacci(n);
                    break;

                case 2:
                    System.out.print("Enter a number to find its prime factors: ");
                    int numForFactorization = scanner.nextInt();
                    factorization(numForFactorization);
                    break;

                case 3:
                    System.out.print("Enter a string to check if it is a palindrome: ");
                    scanner.nextLine();  // Consume newline
                    String palindromeString = scanner.nextLine();
                    checkPalindrome(palindromeString);
                    break;

                case 4:
                    System.out.print("Enter a number to check if it is an Armstrong number: ");
                    int armstrongNumber = scanner.nextInt();
                    checkArmstrongNumber(armstrongNumber);
                    break;

                case 5:
                    System.out.print("Enter a non-negative integer to calculate its factorial: ");
                    int factorialNumber = scanner.nextInt();
                    calculateFactorial(factorialNumber);
                    break;

                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void generateFibonacci(int n) {
        int[] fibSeries = new int[n];
        fibSeries[0] = 0;
        fibSeries[1] = 1;

        for (int i = 2; i < n; i++) {
            fibSeries[i] = fibSeries[i - 1] + fibSeries[i - 2];
        }

        System.out.print("Fibonacci Series: ");
        for (int num : fibSeries) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static void factorization(int num) {
        System.out.print("Prime Factors of " + num + ": ");
        for (int i = 2; i <= num; i++) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num = num / i;
            }
        }
        System.out.println();
    }

    private static void checkPalindrome(String str) {
        String reversedStr = new StringBuilder(str).reverse().toString();
        if (str.equals(reversedStr)) {
            System.out.println(str + " is a palindrome.");
        } else {
            System.out.println(str + " is not a palindrome.");
        }
    }

    private static void checkArmstrongNumber(int num) {
        int originalNumber = num;
        int result = 0;
        int n = String.valueOf(num).length();

        while (num != 0) {
            int digit = num % 10;
            result += Math.pow(digit, n);
            num /= 10;
        }

        if (result == originalNumber) {
            System.out.println(originalNumber + " is an Armstrong number.");
        } else {
            System.out.println(originalNumber + " is not an Armstrong number.");
        }
    }

    private static void calculateFactorial(int num) {
        int factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }
        System.out.println("Factorial of " + num + ": " + factorial);
    }
}

