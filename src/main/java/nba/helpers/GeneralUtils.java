package nba.helpers;

public class GeneralUtils {

    private GeneralUtils() {
    };

    public static String insertPeriodically(String text, String insert, int period) {
        StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length() / period) + 1);

        int index = 0;
        String prefix = "";
        while (index < text.length()) {
            // Don't put the insert in the very first iteration.
            // This is easier than appending it *after* each substring
            builder.append(prefix);
            prefix = insert;
            builder.append(text.substring(index, Math.min(index + period, text.length())));
            index += period;
        }
        return builder.toString();
    }

    public static String insertPeriodicallyReverse(String text, String insert, int period) {
        StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length() / period) + 1);
        StringBuilder reverse = new StringBuilder(text).reverse();
        int index = 0;
        String prefix = "";
        builder.reverse();
        while (index < text.length()) {
            // Don't put the insert in the very first iteration.
            // This is easier than appending it *after* each substring
            builder.append(prefix);
            prefix = insert;
            builder.append(reverse.toString().substring(index, Math.min(index + period, text.length())));
            index += period;
        }
        return builder.reverse().toString();
    }
}
