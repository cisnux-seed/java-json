package dev.cisnux.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public record Person(
        String id,
        String name,
        @JsonProperty(value = "full_name")
        String fullName,
        @JsonIgnore
        String password,
        List<String> hobbies,
        Address address,
        Date createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date updatedAt
) {
    Person(String id,
           String name,
           List<String> hobbies,
           Address address) {
        this(id, name, null, null, hobbies, address, null, null);
    }

    Person(String id,
           String name,
           List<String> hobbies,
           Address address,
           Date createdAt,
           @JsonFormat(pattern = "yyyy-MM-dd")
           Date updatedAt
    ) {
        this(id, name, null, null, hobbies, address, createdAt, updatedAt);
    }
}
