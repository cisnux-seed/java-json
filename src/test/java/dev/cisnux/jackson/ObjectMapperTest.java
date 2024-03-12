package dev.cisnux.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ObjectMapperTest {
    @Test
    void createObjectMapper() {
        final var objectMapper = new ObjectMapper();

        Assertions.assertNotNull(objectMapper);
    }
}
