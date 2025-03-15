public class Application extends Tracker{
    public static void main(String[] args){
        budget = fh.getBudget();
        new FileHandling();

        // ### for testing purposes
//        args = new String[]{"add", "--description", "Electricity", "--amount", "5000.5487", "--category", "Utilities"};
//        args = new String[]{"add", "--description", "askhdbqw", "--amount", "54.354", "--category", "Food"};
//        args = new String[]{"add", "--description", "voemmv", "--amount", "9765.72", "--category", "Transportation"};
//        args = new String[]{"add", "--description", "qweqwe", "--amount", "123.45", "--category", "Personal_Care"};
//        args = new String[]{"set", "--budget", "10000"};

//        args = new String[]{"help"};
//        args = new String[]{"help", "add"};
//        args = new String[]{"help", "summary"};


//        args = new String[]{"update", "--id", "4", "--amount", "40", "--category", "Food", "--description", "icitotes"};
//        args = new String[]{"update", "--id", "4", "--amount", "470"};

//        args = new String[]{"list", "--year", "2022", "--month", "12", "--category", "Utilities", "--day", "22"};
//        args = new String[]{"list", "--month", "3", "--category", "Utilities"};
//        args = new String[]{"list", "--all"};

//        args = new String[]{"summary", "--year", "2025", "--month", "4", "--category", "Utilities", "--day", "3"};
//        args = new String[]{"summary", "--year", "2025"};

//        args = new String[]{"delete", "--id", "3"};
//        args = new String[]{"delete", "--all"};
//        args = new String[]{"delete", "--category", "Utilities"};

//        args = new String[]{"category"};

//        args = new String[]{"export", "--csv"};
//        args = new String[]{"Import", "--json", "data.json"};
//        args = new String[]{"Export", "--csv", "C:\\Users\\RAD\\Desktop\\New folder\\data.csv"};

        try {
            switch (args[0].toLowerCase()) {
                case "add": Add(args);break;
                case "set": set(args);break;
                case "help": Help(args);break;
                case "update": Update(args);break;
                case "list": List(args);break;
                case "summary": Summary(args);break;
                case "delete": Delete(args);break;
                case "export": Export(args);break;
                case "import": Import(args);break;
                case "category": System.out.print("# ");Categories.getCategories();break;
                default: Help.wrongInput();
            }
        }catch (Exception e) {
            Help.noInputHelp();
        }
    }
}
