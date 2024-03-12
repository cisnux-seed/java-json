package dev.cisnux.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JsonArrayTest {
    @Test
    void createJsonArray() throws JsonProcessingException {
        final var hobbies = List.of("Coding", "Reading", "Traveling");

        final var objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(hobbies);

        System.out.println(json);
    }

    @Test
    void readJsonArray() throws JsonProcessingException {
        final var json = """
                ["Coding","Reading","Traveling"]
                """;

        final var objectMapper = new ObjectMapper();
        final var hobbies = objectMapper.readValue(json, new TypeReference<>() {});

        Assertions.assertEquals(List.of("Coding", "Reading", "Traveling"), hobbies);
    }
}
