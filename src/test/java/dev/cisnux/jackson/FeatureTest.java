package dev.cisnux.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FeatureTest {
    @Test
    void mapperFeature() throws JsonProcessingException {
        final var objectMapper = JsonMapper.builder()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .build();

        final var json = """
                {"ID" : "1", "Name": "Fajra"}
                """;

        final var person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.id());
        Assertions.assertEquals("Fajra", person.name());
    }

    @Test
    void deserializationFeature() throws JsonProcessingException {
        final var objectMapper = JsonMapper.builder()
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .build();

        final var json = """
                {"id" : "1", "name": "Fajra", "age": 10, "hobbies" : "Coding"}
                """;

        final var person = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals("1", person.id());
        Assertions.assertEquals("Fajra", person.name());
        Assertions.assertEquals(List.of("Coding"), person.hobbies());
    }

    @Test
    void serializationFeature() throws JsonProcessingException {
        final var address = new Address("Jalan belum jadi", "Jakarta", "Indonesia");
        final var person = new Person("1", "Fajra", List.of("Coding", "Reading"), address);

        final var objectMapper = JsonMapper.builder()
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .build();
        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void serializationInclusion() throws JsonProcessingException {
        final var objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final var person = new Person("1", "Fajra", null, null);

        String json = objectMapper.writeValueAsString(person);
        System.out.println(json);
    }
}
