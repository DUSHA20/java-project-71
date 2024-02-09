package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter {

    public static String formatPlain(List<Map<String, Object>> differences) {
        return differences.stream()
                .map(diff -> formatDiff((String) diff.get("key"), (String) diff.get("status"),
                        diff.get("oldValue"), diff.get("newValue")))
                .collect(Collectors.joining("\n"));
    }

    private static String formatDiff(String key, String status, Object oldValue, Object newValue) {
        String formattedOldValue = formatValue(oldValue);
        String formattedNewValue = formatValue(newValue);

        switch (status) {
            case "removed":
                return "Property '" + key + "' was removed";
            case "added":
                return "Property '" + key + "' was added with value: " + formattedNewValue;
            case "updated":
                return "Property '" + key + "' was updated. From " + formattedOldValue + " to " + formattedNewValue;
            default:
                return "";
        }
    }

    private static String formatValue(Object value) {
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return String.valueOf(value);
    }
}

