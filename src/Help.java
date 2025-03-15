public class Help {
    public static void GeneralHelp() {
        System.out.println("""
                This is a CLI application to track and manage your expenses.
                You can add, update, delete, list, and get a summary of your expenses.
                You can also set a budget and export your expenses to a file in CSV of Json format.
                You can also import expenses from files. The app supports CSV and JSON files.
                The app has dynamic summaries and lists based on the options you provide.
                
                """);
        System.out.println("List of commands:" +
                "\n  " +String.format("%-" + 15 + "s", "add")+ "To add a new expense" +
                "\n  " +String.format("%-" + 15 + "s", "set")+ "To set the budget" +
                "\n  " +String.format("%-" + 15 + "s", "update")+ "To update an expense" +
                "\n  " +String.format("%-" + 15 + "s", "list")+ "To list all expenses" +
                "\n  " +String.format("%-" + 15 + "s", "summary")+ "To get a summary of expenses" +
                "\n  " +String.format("%-" + 15 + "s", "delete")+ "To delete an expense" +
                "\n  " +String.format("%-" + 15 + "s", "category")+ "To list all categories" +
                "\n  " +String.format("%-" + 15 + "s", "export")+ "To export expenses to a file" +
                "\n  " +String.format("%-" + 15 + "s", "import")+ "To import expenses from a file");
        System.out.println("""
                
                To get more information on a specific command, use "Help" followed by the command
                For example, to get help on the add command, use "Help add".""");
        System.exit(0);
    }

    public static void ListHelp() {
        System.out.println("The list command has four options:" +
                "\n  " +String.format("%-" + 15 + "s", "--day")+ "To list expenses for a specific day" +
                "\n  " +String.format("%-" + 15 + "s", "--month")+ "To list expenses for a specific month" +
                "\n  " +String.format("%-" + 15 + "s", "--category")+ "To list expenses for a specific category" +
                "\n  " +String.format("%-" + 15 + "s", "--year")+ "To list expenses for a specific year" +
                "\n  " +String.format("%-" + 15 + "s", "--all")+ "To list all expenses");
        System.out.println("\nYou can use any combination of the options to list expenses." +
                "\n Except for --all is used alone. And you can't use --day with --year without --month." +
                "\nExample: list --year 2025 --month 4 --category Utilities --day 3" +
                "\nExample: list --all" +
                "\nExample: list --year 2025 --month 8 --category Health");
        System.exit(0);
    }

    public static void CategoryHelp() {
        System.out.println("You can choose from the following categories in your expenses:" +
                "\n  " +String.format("%-" + 15 + "s", "Housing")+
                "\n  " +String.format("%-" + 15 + "s", "Utilities")+
                "\n  " +String.format("%-" + 15 + "s", "Food")+
                "\n  " +String.format("%-" + 15 + "s", "Transportation")+
                "\n  " +String.format("%-" + 15 + "s", "Personal_Care")+
                "\n  " +String.format("%-" + 15 + "s", "Health")+
                "\n  " +String.format("%-" + 15 + "s", "Entertainment")+
                "\n  " +String.format("%-" + 15 + "s", "Education")+
                "\n  " +String.format("%-" + 15 + "s", "Gifts")+
                "\n  " +String.format("%-" + 15 + "s", "Business"));
        System.exit(0);
    }

    public static void deleteHelp() {
        System.out.println("The delete command has three options:" +
                "\n  " +String.format("%-" + 15 + "s", "--id")+ "To delete an expense by ID" +
                "\n  " +String.format("%-" + 15 + "s", "--category")+ "To delete all expenses in a category" +
                "\n  " +String.format("%-" + 15 + "s", "--all")+ "To delete all expenses");
        System.exit(0);
    }

    public static void SummaryHelp() {
        System.out.println("The summary command has four options:" +
                "\n  " +String.format("%-" + 15 + "s", "--day")+ "To get a summary of expenses for a specific day" +
                "\n  " +String.format("%-" + 15 + "s", "--month")+ "To get a summary of expenses for a specific month" +
                "\n  " +String.format("%-" + 15 + "s", "--category")+ "To get a summary of expenses for a specific category" +
                "\n  " +String.format("%-" + 15 + "s", "--year")+ "To get a summary of expenses for a specific year" +
                "\n  " +String.format("%-" + 15 + "s", "--all")+ "To get a summary of all expenses");
        System.exit(0);
    }

    public static void addHelp() {
        System.out.println("The add command has three options:" +
                "\n  " +String.format("%-" + 15 + "s", "--description")+ "To add a description to the expense" +
                "\n  " +String.format("%-" + 15 + "s", "--amount")+ "To add an amount to the expense" +
                "\n  " +String.format("%-" + 15 + "s", "--category")+ "To add a category to the expense");
        System.out.println("\nYou must use all three options to add an expense." +
                "\nThe order doesn't matter." +
                "\nExample: add --description Electricity --amount 500 --category Utilities");
        System.exit(0);
    }

    public static void setHelp() {
        System.out.println("The set command has one option:" +
                "\n  " +String.format("%-" + 15 + "s", "--budget")+ "To set the budget");
        System.out.println("\nIt's used to set the budget for a month." +
                "\nIt's not necessary to set the budget to use the application." +
                "\nExample: set --budget 10000");
        System.exit(0);
    }

    public static void updateHelp() {
        System.out.println("The update command has two options:" +
                "\n  " +String.format("%-" + 15 + "s", "--id")+ "To update an expense by ID" +
                "\n  " +String.format("%-" + 15 + "s", "--description")+ "To update the description of the expense" +
                "\n  " +String.format("%-" + 15 + "s", "--amount")+ "To update the amount of the expense" +
                "\n  " +String.format("%-" + 15 + "s", "--category")+ "To update the category of the expense");
        System.out.println("\nYou must use the ID option to update an expense." +
                "\nYou can use any combination of the other options to update the expense." +
                "\nExample: update --id 1 --description Electricity Bill");
        System.exit(0);
    }

    public static void ExportHelp() {
        System.out.println("The export command has one option:" +
                "\n  " +String.format("%-" + 15 + "s", "--csv")+ "To export expenses to a CSV file" +
                "\n  " +String.format("%-" + 15 + "s", "--json")+ "To export expenses to a JSON file");
        System.out.println("\nYou must use either the --csv or --json option to export expenses." +
                "\nAfter the option you should specify the name of the file and the path." +
                "\nExample: export --csv data.csv" +
                "\nExample: export --json C:\\Users\\{User name}\\Desktop\\New folder\\data.json");

        System.exit(0);
    }

    public static void wrongInput() {
        System.out.println("Invalid input. Please use Help to see the available commands");
        System.exit(0);
    }

    public static void noInputHelp() {
        System.out.println("No input detected. Please use Help to see the available commands");
        System.exit(0);
    }

    public static void ImportHelp() {
        System.out.println("The import command has one option:" +
                "\n  " +String.format("%-" + 15 + "s", "--csv")+ "To import expenses from a CSV file" +
                "\n  " +String.format("%-" + 15 + "s", "--json")+ "To import expenses from a JSON file");
        System.out.println("\nYou must use either the --csv or --json option to import expenses." +
                "\nAfter the option you should specify the name of the file and the path." +
                "\nExample: import --csv data.csv" +
                "\nExample: import --json C:\\Users\\{User name}\\Desktop\\New folder\\data.json");
        System.out.println("\nYou must make sure the file is in the correct format." +
                "\nFor CSV, the file should have a header and the following columns:" +
                "\nID,Date,Category,Description,Amount" +
                "\nThe header can be this line." +
                "\n\nFor JSON, the file should have the following format:" +
                "\n[{" +
                "\n  \"date\":\"yyyy-mm-dd\"," +
                "\n  \"category\":\"{Check \"Help category\" for available Categories}\"," +
                "\n  \"description\":\"{any}\"," +
                "\n  \"amount\":{any number}" +
                "\n}]");

        System.exit(0);
    }
}
