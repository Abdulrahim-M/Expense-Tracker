import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record Expense(
//        Integer ID,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,
        Categories category,
        String description,
        Double amount
) {
    @Override
    public String toString() {
        return    String.format("%-" + 12 + "s", date)
                + String.format("%-" + 15 + "s", category)
                + String.format("%-" + 25 + "s", description)
                + String.format("%-" + 8 + "s", "$" + amount);
    }

    public String toJSON() {
        return "{" +
                "\"date\":\"" + date + "\"," +
                "\"category\":\"" + category + "\"," +
                "\"description\":\"" + description + "\"," +
                "\"amount\":" + amount +
                "}";
    }
    public static Expense fromJSON(String s) {
        String[] parts = s.replace("{","").replace("}", "").split(",");
        return new Expense( LocalDate.parse(parts[1]), Categories.valueOf(parts[2]), parts[3], Double.valueOf(parts[4]));
    }

    public String toCSV() {
        return "," + date + "," + category + "," + description + "," + amount;
    }

    public static Expense fromCSV(String s) {
        String[] parts = s.split(",");
        return new Expense(LocalDate.parse(parts[1]), Categories.valueOf(parts[2]), parts[3], Double.valueOf(parts[4]));
    }

}