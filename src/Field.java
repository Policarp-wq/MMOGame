public class Field {
    public static Unit[] FirstGroup;
    public static Unit[] SecondGroup;

    public Field(Player first, Player second) {
        FirstGroup = first.getUnits();
        SecondGroup = second.getUnits();
    }
}
