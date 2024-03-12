package dev.cisnux.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class JsonObjectTest {
    @Test
    void createJson() throws JsonProcessingException {
        final var person = Map.of(
                "firstName", "Budi",
                "lastName", "Nugraha",
                "age", 30,
                "married", true,
                "address", Map.of(
                        "street", "Jalan belum ada",
                        "city", "Jakarta",
                        "country", "Indonesia"
                )
        );

        final var objectMapper = new ObjectMapper();
        final var json = objectMapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void createJsonOnFile() throws IOException {
        final var person = Map.of(
                "firstName", "Budi",
                "lastName", "Nugraha",
                "age", 30,
                "married", true,
                "address", Map.of(
                        "street", "Jalan belum ada",
                        "city", "Jakarta",
                        "country", "Indonesia"
                )
        );

        final var objectMapper = new ObjectMapper();
        objectMapper.writeValue(Path.of("test.json").toFile(), person);
    }

    @Test
    void readJson() throws JsonProcessingException {
        final var json = """
                {
                  "age": 30,
                  "address": {
                    "street": "Jalan belum ada",
                    "city": "Jakarta",
                    "country": "Indonesia"
                  },
                  "firstName": "Budi",
                  "lastName": "Nugraha",
                  "married": true
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        final var person = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });

        Assertions.assertEquals("Budi", person.get("firstName"));
        Assertions.assertEquals("Nugraha", person.get("lastName"));
    }
}
