package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    public static Map<String, Object> parseFile(String filePath) throws Exception {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }

        if (filePath.endsWith(".json")) {
            return jsonFileToMap(path);
        } else if (filePath.endsWith(".yaml") || filePath.endsWith(".yml")) {
            return yamlFileToMap(path);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
    }

    private static Map<String, Object> jsonFileToMap(Path path) throws Exception {
        return JSON_MAPPER.readValue(path.toFile(), new TypeReference<>() { });
    }

    private static Map<String, Object> yamlFileToMap(Path path) throws Exception {
        return YAML_MAPPER.readValue(path.toFile(), new TypeReference<>() { });
    }
}

