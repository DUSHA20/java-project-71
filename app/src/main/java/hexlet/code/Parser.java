package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new YAMLMapper();

    public static Map<String, Object> parseJson(String jsonContent) throws Exception {
        return JSON_MAPPER.readValue(jsonContent, new TypeReference<>() { });
    }

    public static Map<String, Object> parseYaml(String yamlContent) throws Exception {
        return YAML_MAPPER.readValue(yamlContent, new TypeReference<>() { });
    }
}

