package au.agl.dj.pets.domain;

import au.agl.dj.pets.common.deserializer.GenderDeserializer;
import au.agl.dj.pets.common.serializer.GenderSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@JsonSerialize(using = GenderSerializer.class)
@JsonDeserialize(using = GenderDeserializer.class)
public enum Gender {
    MALE("Male"), FEMALE("Female");

    @Getter
    private String value;

    Gender(String value) {
        this.value = value;
    }

    public static Optional<Gender> parse(final String value) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.getValue().equalsIgnoreCase(value))
                .findFirst();

    }
}
