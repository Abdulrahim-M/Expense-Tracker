public enum Categories {
    Housing,
    Utilities,
    Food,
    Transportation,
    Personal_Care,
    Health,
    Entertainment,
    Education,
    Gifts,
    Business,
    ;


    public static void getCategories() {
        for (Categories categories :Categories.values())
            System.out.print("| " + categories + " |");
    }
}
