package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter {

    public static String formatPlain(List<Map<String, Object>> differences) {
        return differences.stream()
                .map(diff -> {
                    String key = (String) diff.get("key");
                    String status = (String) diff.get("status");
                    String oldValue = formatValue(diff.get("oldValue"));
                    String newValue = formatValue(diff.get("newValue"));

                    switch (status) {
                        case "removed":
                            return "Property '" + key + "' was removed";
                        case "added":
                            return "Property '" + key + "' was added with value: " + newValue;
                        case "updated":
                            return "Property '" + key + "' was updated. From " + oldValue + " to " + newValue;
                        default:
                            return "";
                    }
                })
                .collect(Collectors.joining("\n"));
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

