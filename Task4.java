import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    private String description;
    private double amount;
    private String category;
    private String date;

    public Expense(String description, double amount, String category, String date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }
}

class BudgetCategory {
    private String name;
    private double limit;
    private double spent;

    public BudgetCategory(String name, double limit) {
        this.name = name;
        this.limit = limit;
        this.spent = 0;
    }

    public String getName() {
        return name;
    }

    public double getLimit() {
        return limit;
    }

    public double getSpent() {
        return spent;
    }

    public void addExpense(double amount) {
        spent += amount;
    }

    public double getRemainingBudget() {
        return limit - spent;
    }
}

class ExpenseTracker {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<BudgetCategory> budgetCategories = new ArrayList<>();

    public void addExpense(String description, double amount, String category, String date) {
        Expense expense = new Expense(description, amount, category, date);
        expenses.add(expense);

        BudgetCategory budgetCategory = findBudgetCategory(category);
        if (budgetCategory != null) {
            budgetCategory.addExpense(amount);
        }
    }

    public void addBudgetCategory(String name, double limit) {
        BudgetCategory budgetCategory = new BudgetCategory(name, limit);
        budgetCategories.add(budgetCategory);
    }

    public void viewExpenses() {
        System.out.println("Expense List:");
        for (Expense expense : expenses) {
            System.out.println("Description: " + expense.getDescription() +
                    ", Amount: $" + expense.getAmount() +
                    ", Category: " + expense.getCategory() +
                    ", Date: " + expense.getDate());
        }
    }

    public void viewBudgetSummary() {
        System.out.println("Budget Summary:");
        for (BudgetCategory budgetCategory : budgetCategories) {
            System.out.println("Category: " + budgetCategory.getName() +
                    ", Limit: $" + budgetCategory.getLimit() +
                    ", Spent: $" + budgetCategory.getSpent() +
                    ", Remaining: $" + budgetCategory.getRemainingBudget());
        }
    }

    private BudgetCategory findBudgetCategory(String category) {
        for (BudgetCategory budgetCategory : budgetCategories) {
            if (budgetCategory.getName().equalsIgnoreCase(category)) {
                return budgetCategory;
            }
        }
        return null;
    }
}

class ExpenseTrackerApp {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nExpense Tracker Application");
            System.out.println("1. Track Your Spending");
            System.out.println("2. Stay on Budget");
            System.out.println("3. Manage Your Money Wisely");
            System.out.println("4. Protect Your Privacy");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    trackSpending(expenseTracker, scanner);
                    break;

                case 2:
                    stayOnBudget(expenseTracker, scanner);
                    break;

                case 3:
                    manageMoneyWisely(expenseTracker);
                    break;

                case 4:
                    protectPrivacy();
                    break;

                case 0:
                    System.out.println("Exiting the Expense Tracker Application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void trackSpending(ExpenseTracker expenseTracker, Scanner scanner) {
        System.out.print("Enter expense description: ");
        String description = scanner.nextLine();

        System.out.print("Enter expense amount: $");
        double amount = scanner.nextDouble();

        System.out.print("Enter expense category: ");
        scanner.nextLine();  // Consume newline
        String category = scanner.nextLine();

        System.out.print("Enter expense date (MM/DD/YYYY): ");
        String date = scanner.nextLine();

        expenseTracker.addExpense(description, amount, category, date);
        System.out.println("Expense recorded successfully.");
    }

    private static void stayOnBudget(ExpenseTracker expenseTracker, Scanner scanner) {
        System.out.println("Budget Categories:");
        expenseTracker.viewBudgetSummary();

        System.out.print("Enter category to set spending limit: ");
        scanner.nextLine();  // Consume newline
        String category = scanner.nextLine();

        BudgetCategory budgetCategory = expenseTracker.findBudgetCategory(category);
        if (budgetCategory != null) {
            System.out.print("Enter spending limit for " + budgetCategory.getName() + ": $");
            double limit = scanner.nextDouble();

            budgetCategory = new BudgetCategory(budgetCategory.getName(), limit);
            System.out.println("Spending limit set successfully.");
        } else {
            System.out.println("Category not found.");
        }
    }

    private static void manageMoneyWisely(ExpenseTracker expenseTracker) {
        expenseTracker.viewExpenses();
        expenseTracker.viewBudgetSummary();
    }

    private static void protectPrivacy() {
        // Privacy protection implementation goes here
        System.out.println("Privacy protection functionality is not implemented in this example.");
    }
}

