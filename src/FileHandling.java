import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class FileHandling {
    static final File SaveFile = new File("data\\Expenses.json");
    static final File configFile = new File("data\\config.properties");
    Properties properties = new Properties();

    ObjectMapper mapper = new ObjectMapper();

    public FileHandling() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.CLOSE_CLOSEABLE);

        try {
            if (SaveFile.createNewFile()){
                mapper.writeValue(SaveFile, new Expense[0]);
            }
            try {
                properties.load(new FileInputStream(configFile));
            } catch (FileNotFoundException e) {
                properties.setProperty("budget", "0");
                properties.store(new FileOutputStream(configFile), null);
                properties.load(new FileInputStream(configFile));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getBudget(){
        return Double.valueOf(properties.getProperty("budget", "0"));
    }

    public void setBudget(String budget) {
        properties.setProperty("budget", budget);
        try {
            properties.store(new FileOutputStream(configFile), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Expense[] ReadExpenses(){
        try {
            if (!SaveFile.createNewFile()) {
                return mapper.readValue(SaveFile, Expense[].class);
            } else {
                mapper.writeValue(SaveFile, new Expense[0]);
                return mapper.readValue(SaveFile, Expense[].class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void Save(Expense[] expense) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(SaveFile, expense);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ExportCSV(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write("ID,Date,Category,Description,Amount");
            writer.newLine();
            Expense[] expenses = ReadExpenses();
            for (int i = 0; i < expenses.length; i++) {
                writer.write("" + (i + 1));
                writer.write(expenses[i].toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ImportCSV(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            List<Expense> expenses = new ArrayList<>();
            reader.readLine(); // Skip the header line
            while ((line = reader.readLine()) != null) {
                expenses.add(Expense.fromCSV(line));
            }
            Expense[] newLibrary = new Expense[expenses.size() + ReadExpenses().length];
            System.arraycopy(ReadExpenses(), 0, newLibrary, 0, ReadExpenses().length);
            System.arraycopy(expenses.toArray(new Expense[0]), 0, newLibrary, ReadExpenses().length, expenses.size());

            Save(newLibrary);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ExportJSON(File file) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, ReadExpenses());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ImportJSON(File file) {
        try {
            Expense[] newExpenses = mapper.readValue(file, Expense[].class);
            Expense[] oldExpenses = ReadExpenses();
            Expense[] newLibrary = new Expense[newExpenses.length + oldExpenses.length];

            System.arraycopy(oldExpenses, 0, newLibrary, 0, oldExpenses.length);
            System.arraycopy(newExpenses, 0, newLibrary, ReadExpenses().length, newExpenses.length);

            Save(newLibrary);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
