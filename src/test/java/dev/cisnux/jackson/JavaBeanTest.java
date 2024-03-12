package dev.cisnux.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JavaBeanTest {
    @Test
    void createJsonFromObject() throws JsonProcessingException {
        final var address = new Address("Jalan belum jadi", "Jakarta", "Indonesia");
        final var person = new Person("1", "Fajra", List.of("Coding", "Reading"), address);


        final var objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);

        System.out.println(json);
    }

    @Test
    void readObjectFromJson() throws JsonProcessingException {
        final var json = """
                {
                  "id": "1",
                  "name": "Eko",
                  "hobbies": [
                    "Coding",
                    "Reading"
                  ],
                  "address": {
                    "street": "Jalan belum jadi",
                    "city": "Jakarta",
                    "country": "Indonesia"
                  }
                }
                """;

        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(json, Person.class);

        Assertions.assertEquals("1", person.id());
        Assertions.assertEquals("Eko", person.name());
        Assertions.assertEquals("Jalan belum jadi", person.address().street());
        Assertions.assertEquals("Jakarta", person.address().city());
        Assertions.assertEquals("Indonesia", person.address().country());
    }
}
