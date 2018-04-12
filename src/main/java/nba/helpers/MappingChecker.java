package nba.helpers;

public class MappingChecker {
    private MappingChecker() {
    };

    public static Boolean canBeParsedIntoLong(String string) {
        if (string == null || string.equals("") || string.replaceAll("[0-9]", "").trim().length() > 0 || string.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static Boolean canBeParsedIntoBigDecimal(String string) {
        if (string == null || string.equals("") || string.replaceAll("[0-9.]", "").trim().length() > 0 || string.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static String returnCleanValue(String badData) {
        return badData.split(" ")[0];
    }
}
