package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String formatPlain(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> diff : differences) {
            switch (diff.get("status").toString()) {
                case "removed":
                    result.append("Property '").append(diff.get("key")).append("' was removed\n");
                    break;
                case "added":
                    result.append("Property '").append(diff.get("key")).append("' was added with value: ")
                            .append(formatValue(diff.get("newValue"))).append("\n");
                    break;
                case "updated":
                    result.append("Property '").append(diff.get("key")).append("' was updated. From ")
                            .append(formatValue(diff.get("oldValue"))).append(" to ")
                            .append(formatValue(diff.get("newValue"))).append("\n");
                    break;
                default:
                    break;
            }
        }
        return result.toString();
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

