package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    public static String formatJson(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("[\n");

        for (Map<String, Object> diff : differences) {
            result.append("{\n");
            appendKeyValuePair(result, "key", diff.get("key"));
            appendKeyValuePair(result, "oldValue", diff.get("oldValue"));
            appendKeyValuePair(result, "newValue", diff.get("newValue"));
            appendKeyValuePair(result, "status", diff.get("status"));
            result.append("},\n");
        }

        removeTrailingComma(result);
        result.append("]");
        return result.toString();
    }

    private static void appendKeyValuePair(StringBuilder builder, String key, Object value) {
        if (value != null) {
            builder.append("  \"").append(key).append("\" : ").append(formatValue(value)).append(",\n");
        }
    }

    private static void removeTrailingComma(StringBuilder builder) {
        int length = builder.length();
        if (length > 2 && builder.charAt(length - 2) == ',') {
            builder.deleteCharAt(length - 2);
        }
    }

    private static String formatValue(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return String.valueOf(value);
    }
}
