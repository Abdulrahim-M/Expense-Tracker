import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class Tracker extends Help{
    public static Double budget; // This is the budget that will be set by the user (default is 0)
    static FileHandling fh = new FileHandling(); // Pass the location of Saves file

    static void Import(String[] args) {
        try {
            if (args.length > 1) {
                switch (args[1]) {
                    case "--csv" -> {
                        fh.ImportCSV(new File(args[2]));
                        System.out.println("# Data imported from " + args[2]);
                    }
                    case "--json" -> {
                        fh.ImportJSON(new File(args[2]));
                        System.out.println("# Data imported from " + args[2]);
                    }
                    default -> Help.wrongInput();
                }
            } else Help.wrongInput();
        } catch (Exception e) {
            Help.wrongInput();
        }
    }

    static void Export(String[] Args) {
        try {
            if (Args.length > 1) {
                switch (Args[1]) {
                    case "--csv" -> {
                        fh.ExportCSV(new File(Args[2]));
                        System.out.println("# Data exported to data.csv");
                    }
                    case "--json" -> {
                        fh.ExportJSON(new File(Args[2]));
                        System.out.println("# Data exported to data.json");
                    }
                    default -> wrongInput();
                }
            } else wrongInput();
        } catch (Exception e) {
            wrongInput();
        }
    }

    static void Help(String[] Args) {
        try {
            if (Args.length == 2) {
                switch (Args[1]) {
                    case "add" -> addHelp();
                    case "set" -> setHelp();
                    case "update" -> updateHelp();
                    case "list" -> ListHelp();
                    case "summary" -> SummaryHelp();
                    case "delete" -> deleteHelp();
                    case "category" -> CategoryHelp();
                    case "export" -> ExportHelp();
                    case "import" -> ImportHelp();
                    default -> wrongInput();
                }
            } else if (Args.length == 1) {
                GeneralHelp();
            } else wrongInput();
        } catch (Exception e) {
            wrongInput();
        }
    }

    // The method that looks for what to update
    static void Update(String[] Args) {
        try {
            int idToUpdate = Integer.parseInt(Args[2]) - 1;
            if (Args[1].equals("--id")) { // checking if the first argument is the valid

                Expense[] temp = fh.ReadExpenses(); // Temp array to store the new data


                // Looping through the arguments to find the values
                // The loop starts at 3 because the first two arguments are the command and the ID
                // This loop will make the update method dynamic because it can take any number of arguments
                for (int i = 3; i < Args.length; i += 2) {
                    switch (Args[i]) {
                        case "--description" -> temp[idToUpdate] = new Expense(temp[idToUpdate].date(), temp[idToUpdate].category(), Args[i + 1], temp[idToUpdate].amount());
                        case "--amount" -> temp[idToUpdate] = new Expense(temp[idToUpdate].date(), temp[idToUpdate].category(), temp[idToUpdate].description(), Double.valueOf(Args[i + 1]));
                        case "--category" -> {
                            // updating the category after checking if the category is valid
                            for (Categories category : Categories.values()) { // looping through the categories to find the category
                                if (category.toString().equalsIgnoreCase(Args[i + 1])) {
                                    temp[idToUpdate] = new Expense(temp[idToUpdate].date(), Categories.valueOf(Args[i + 1]), temp[idToUpdate].description(), temp[idToUpdate].amount()); // updating the category
                                    break;
                                }
                            }
                        }
                        default -> wrongInput();
                    }
                }
                System.out.println("# Expense updated successfully (ID: " + Args[2] + ")");
                fh.Save(temp);
            }
        } catch (Exception e) {
            wrongInput();
        }
    }

    // Sets the budget
    static void set(String[] Args) {
        try {
            if (Args.length > 1 && Args.length < 4) {
                if (Args[1].equals("--budget")) {
                    budget = Double.valueOf(Args[2]);
                    fh.setBudget(Args[2]);
                    System.out.println("# Your monthly budget is set to $" + budget);
                } else wrongInput();
            } else wrongInput();
        } catch (Exception e) {
            wrongInput();
        }
    }

    // Lists the Expenses
    static void List(String[] Args) {
        String header = "\n# " + String.format("%-" + 4 + "s", "ID")
                + String.format("%-" + 12 + "s", "Date")
                + String.format("%-" + 15 + "s", "Category")
                + String.format("%-" + 25 + "s", "Description")
                + String.format("%-" + 8 + "s", "Amount");
        try {
            Expense[] expenses = fh.ReadExpenses();
            if (Objects.equals(Args[1], "--all")) {
                System.out.println(header);
                int currentID = 1;
                for (Expense expense : expenses) {
                    System.out.println("# " + String.format("%-" + currentID + "s", "ID") + expense);
                }
            } else {
                // Default values
                int year = LocalDate.now().getYear();
                int month = LocalDate.now().getMonthValue();
                int day = LocalDate.now().getDayOfMonth();
                Categories category = null;
                boolean isYear = false;
                boolean isMonth = false;
                boolean isDay = false;
                boolean isCategory = false;

                // Looping through the arguments to find the values
                for (int i = 1; i < Args.length; i += 2) {
                    switch (Args[i]) {
                        case "--year":
                            isYear = true;
                            year = Integer.parseInt(Args[i + 1]);
                            break;
                        case "--month":
                            isMonth = true;
                            month = Integer.parseInt(Args[i + 1]);
                            break;
                        case "--day":
                            isDay = true;
                            day = Integer.parseInt(Args[i + 1]);
                            break;
                        case "--category":
                            isCategory = true;
                            for (Categories categories : Categories.values()) {
                                if (categories.toString().equalsIgnoreCase(Args[i + 1])) {
                                    category = Categories.valueOf(Args[i + 1]);
                                    break;
                                }
                            }
                            break;
                        default:
                            wrongInput();
                            return;
                    }
                }

                double total = Summary(isYear, isMonth, isDay, isCategory, year, month, day, category);
                // Print the summary
                StringBuilder summaryMessage = new StringBuilder("# A list of expenses");
                summaryMessage.append(" for ").append(year);
                if (isMonth) summaryMessage.append(" ").append(Month.of(month).toString().toLowerCase());
                if (isDay) summaryMessage.append(" ").append(day);
                if (isCategory) {
                    assert category != null;
                    summaryMessage.append(", in category ").append(category.toString().toLowerCase());
                }
                summaryMessage.append(": total $").append(total);

                System.out.println(summaryMessage);

                // Loop through expenses and apply filters
                int currentID = 1;
                System.out.println(header);
                for (Expense expense : expenses) {
                    boolean matches = true; // this is to check if the expense matches the filters

                    if (isYear && expense.date().getYear() != year) matches = false;
                    if (isMonth && expense.date().getMonthValue() != month) matches = false;
                    if (isDay && expense.date().getDayOfMonth() != day) matches = false;
                    if (isCategory && !expense.category().equals(category)) matches = false;

                    if (matches) {
                        System.out.print("# " + String.format("%-" + 4 + "s", currentID));
                        System.out.println(expense);
                        currentID++;
                    }
                }
                System.out.println("\n");
            }
        } catch (Exception e) {
            Help.wrongInput();
        }
    }

    // Deletes Expenses
    static void Delete(String[] Args) {
        try {
            if (Args.length > 1 && Args.length < 4) {
                if (Args[1].equals("--all")) {
                    fh.Save(new Expense[0]); // creating new data array as a means to delete the past data
                    System.out.println("# The Tracker have been reset");
                } else if (Args[1].equals("--id")) {
                    Expense[] expenses = fh.ReadExpenses();
                    Expense[] temp = removeExpense(expenses, Integer.parseInt(Args[2]) - 1);
                    if (!(expenses == temp)) {
                        fh.Save(temp);
                        System.out.println("# Expense number " + Args[2] + " has been deleted!");
                    } else System.out.println("# Expense number " + Args[2] + " does not exist!");
                } else if (Args[1].equals("--category")) {
                    boolean isCategory = false;
                    for (Categories category : Categories.values()) {
                        if (category.toString().equalsIgnoreCase(Args[2])) { // this is to check if the input is a category
                            isCategory = true;
                            break;
                        }
                    }
                    if (isCategory) {
                        Expense[] expenses = fh.ReadExpenses();
                        Expense[] temp = new Expense[0];
                        for (Expense expense : expenses) { // looping through the expenses to find the category
                            if (!expense.category().toString().equalsIgnoreCase(Args[2])) {
                                Expense[] temp2 = new Expense[temp.length + 1]; // creating a new array with the new size if the category is not found
                                System.arraycopy(temp, 0, temp2, 0, temp.length);
                                temp2[temp.length] = expense;
                                temp = temp2;
                            }
                        }
                        fh.Save(temp);
                        System.out.println("# All expenses in the category " + Args[2] + " have been deleted!");
                    } else { wrongInput(); System.out.println("# This category is invalid");}
                }
            } else wrongInput();
        } catch (Exception e) {
            wrongInput();
        }
    }

    static Expense[] removeExpense(Expense[] expenses, int indexToRemove) {
        if (expenses == null || indexToRemove < 0 || indexToRemove >= expenses.length) {
            return expenses; // Invalid input, return the original array
        }

        Expense[] newArray = new Expense[expenses.length - 1]; // Create a new array one size smaller
        for (int i = 0, j = 0; i < expenses.length; i++) {
            if (i != indexToRemove) {
                newArray[j++] = expenses[i]; // Copy elements, skipping the one to remove
            }
        }
        return newArray;
    }

    // The method that prints the Summary of the month or in total
    static void Summary(String[] Args) {


        try {
            if (Objects.equals(Args[1], "--all")) {
                System.out.println("# Total expenses: $" + Total());
            } else {
                // Default values
                double total = 0;
                int year = LocalDate.now().getYear();
                int month = LocalDate.now().getMonthValue();
                int day = LocalDate.now().getDayOfMonth();
                Categories category = null;
                boolean isYear = false;
                boolean isMonth = false;
                boolean isDay = false;
                boolean isCategory = false;

                // Looping through the arguments to find the values
                for (int i = 1; i < Args.length; i += 2) {
                    switch (Args[i]) {
                        case "--year":
                            isYear = true;
                            year = Integer.parseInt(Args[i + 1]);
                            break;
                        case "--month":
                            isMonth = true;
                            month = Integer.parseInt(Args[i + 1]);
                            break;
                        case "--day":
                            isDay = true;
                            day = Integer.parseInt(Args[i + 1]);
                            break;
                        case "--category":
                            isCategory = true;
                            for (Categories categories : Categories.values()) {
                                if (categories.toString().equalsIgnoreCase(Args[i + 1])) {
                                    category = Categories.valueOf(Args[i + 1]);
                                    break;
                                }
                            }
                            break;
                        default:
                            wrongInput();
                            return;
                    }
                }

                total = Summary(isYear, isMonth, isDay, isCategory, year, month, day, category);

                // Print the summary
                StringBuilder summaryMessage = new StringBuilder("# Total expenses for");
                if (isYear) summaryMessage.append(" ").append(year);
                if (isMonth) summaryMessage.append(" ").append(Month.of(month).toString().toLowerCase());
                if (isDay) summaryMessage.append(" ").append(day);
                if (isCategory) {
                    assert category != null;
                    summaryMessage.append(" category ").append(category.toString().toLowerCase());
                }
                summaryMessage.append(": $").append(total);

                System.out.println(summaryMessage);

            }
        } catch (Exception e) {
            wrongInput();
        }
    }

    static Double Summary(boolean isYear, boolean isMonth, boolean isDay, boolean isCategory, int year, int month, int day, Categories category) {
        Double total = 0.0;
        for (Expense expense : fh.ReadExpenses()) {
            boolean matches = true;

            if (isYear && expense.date().getYear() != year) matches = false;
            if (isMonth && expense.date().getMonthValue() != month) matches = false;
            if (isDay && expense.date().getDayOfMonth() != day) matches = false;
            if (isCategory && !expense.category().equals(category)) matches = false;

            if (matches) total += expense.amount();

        }
        return total;
    }

    // The method that gives the month total expenses
    static Double MonthTotal(int month) {
        Double total = 0.0;
        for (Expense expense : fh.ReadExpenses()) {
            if (expense.date().getYear() == LocalDate.now().getYear() && expense.date().getMonth().equals(Month.of(month)))
                total += expense.amount();
        }
        return total;
    }

    // The method that gives the total expenses
    static Double Total() {
        Double total = 0.0;
        for (Expense expense : fh.ReadExpenses()) {
            total += expense.amount();
        }
        return total;
    }

    // Method the add new Expenses
    static void Add(String[] Args) {
        // default value that won't be used
        String d = null;
        double a = 0.0;
        Categories c = Categories.Food;

        // Making these so the order has no effect
        try {
            for (int i = 1; i < 7; i += 2) {
                switch (Args[i]) {
                    case "--description": d = Args[i + 1];break;
                    case "--amount": a = Double.parseDouble(Args[i + 1]);break;
                    case "--category": c = Categories.valueOf(Args[i + 1]);break;
                    default: wrongInput();
                }
            }

            if (a <= 0) {
                System.out.println("# Amount must be greater than 0");
                return;
            } else if (d == null) {
                wrongInput();
                return;
            }
            // Adding the new expense to the save file
            int currentID = fh.ReadExpenses().length;
            Expense[] temp = new Expense[currentID + 1];
            System.arraycopy(fh.ReadExpenses(), 0, temp, 0, currentID);
            temp[currentID] = new Expense(LocalDate.now(), c, d, a);
            fh.Save(temp);

            System.out.println("# Expense added successfully (ID: " + (currentID + 1) + ")");

            // Checks the budget
            Double monthExpenses = MonthTotal(LocalDate.now().getMonthValue());
            if (monthExpenses > budget && budget != 0)
                System.out.println("# But your monthly budget of $" + budget + " was exceeded by $" + (monthExpenses - budget));

        } catch (Exception e) {
            wrongInput();
        }
    }


}


