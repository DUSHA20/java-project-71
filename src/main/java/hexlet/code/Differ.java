package hexlet.code;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Differ {

    public static Map<String, Object> readFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return parseJson(content);
    }

    public static Map<String, Object> parseJson(String jsonContent) {
        JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
        return parseJsonObject(jsonObject);
    }

    private static Map<String, Object> parseJsonObject(JsonObject jsonObject) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (value.isJsonObject()) {
                result.put(key, parseJsonObject(value.getAsJsonObject()));
            } else {
                result.put(key, parseJsonPrimitive(value.getAsJsonPrimitive()));
            }
        }
        return result;
    }

    private static Object parseJsonPrimitive(JsonPrimitive jsonPrimitive) {
        if (jsonPrimitive.isBoolean()) {
            return jsonPrimitive.getAsBoolean();
        } else if (jsonPrimitive.isString()) {
            return jsonPrimitive.getAsString();
        } else if (jsonPrimitive.isNumber()) {
            return jsonPrimitive.getAsNumber();
        }
        return null;
    }

    public static Set<String> getSortedKeys(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> keys = new TreeSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());
        return keys;
    }

    public static String generate(String filePath1, String filePath2) throws IOException {

        Map<String, Object> map1 = readFile(filePath1);
        Map<String, Object> map2 = readFile(filePath2);

        Set<String> keys = getSortedKeys(map1, map2);

        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (String key : keys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (Objects.equals(value1, value2)) {
                continue;
            }

            String prefix1 = value1 == null ? "+ " : "- ";
            String prefix2 = value2 == null ? "+ " : "- ";

            if (value1 != null) {
                result.append("  ").append(prefix1).append(key).append(": ");
                result.append(value1 instanceof Map ? formatNestedMap((Map<String, Object>) value1) : value1).append("\n");
            }

            if (value2 != null) {
                result.append("  ").append(prefix2).append(key).append(": ");
                result.append(value2 instanceof Map ? formatNestedMap((Map<String, Object>) value2) : value2).append("\n");
            }
        }

        result.append("}");

        return result.toString();
    }

    private static String formatNestedMap(Map<String, Object> nestedMap) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        for (Map.Entry<String, Object> entry : nestedMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            result.append("    ").append(key).append(": ").append(value).append("\n");
        }

        result.append("  }");
        return result.toString();
    }
}

