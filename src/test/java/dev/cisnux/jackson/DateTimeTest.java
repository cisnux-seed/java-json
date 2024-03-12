package dev.cisnux.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateTimeTest {
    @Test
    void dateTime() throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();

        final var address = new Address("Jalan belum jadi", "Jakarta", "Indonesia");
        final var person = new Person("1", "Fajra", List.of("Coding", "Reading"), address, new Date(), new Date());

        final var json = objectMapper.writeValueAsString(person);
        System.out.println(json);
    }

    @Test
    void dateFormat() throws JsonProcessingException {
        final var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        final var objectMapper = new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setDateFormat(dateFormat);

        final var address = new Address("Jalan belum jadi", "Jakarta", "Indonesia");
        final var person = new Person("1", "Fajra", List.of("Coding", "Reading"), address, new Date(), new Date());

        final var json = objectMapper.writeValueAsString(person);
        System.out.println(json);
    }
}
