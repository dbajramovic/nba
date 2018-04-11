package nba.helpers;

public class MappingChecker {
    private MappingChecker() {
    };

    public static Boolean canBeParsedIntoLong(String string) {
        if (string == null || string.replaceAll("[0-9]", "").trim().length() > 0 || string.equals("") || string.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static Boolean canBeParsedIntoBigDecimal(String string) {
        if (string == null || string.replaceAll("[0-9.]", "").trim().length() > 0 || string.equals("") || string.trim().length() == 0) {
            return false;
        }
        return true;
    }
}
