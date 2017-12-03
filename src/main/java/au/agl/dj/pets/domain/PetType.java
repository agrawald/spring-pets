package au.agl.dj.pets.domain;

import au.agl.dj.pets.common.deserializer.PetTypeDeserializer;
import au.agl.dj.pets.common.serializer.PetTypeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enumeration to specify the type of pet
 * This can be extended to have more types without changing the Pet call.
 */
@JsonSerialize(using = PetTypeSerializer.class)
@JsonDeserialize(using = PetTypeDeserializer.class)
public enum PetType {
    CAT("Cat"), DOG("Dog"), FISH("Fish");

    @Getter
    private String type;

    PetType(String type) {
        this.type = type;
    }

    //TODO: Some custom specific behaviour related to the type of pets can be coded here

    /**
     * Function to parse a string into {@link PetType}
     *
     * @param type {@link String}
     * @return returns {@link Optional} of {@link PetType}
     */
    public static Optional<PetType> parse(final String type) {
        //we can pre load the stream at the class loading time to save computation everytime,
        //however in our case the data set is low hence not very expensive.
        //Can be a possible performance improvement if we know we have lot of calls to this function
        return Arrays.stream(PetType.values())
                .filter(petType -> petType.getType().equalsIgnoreCase(type))
                .findFirst();
    }
}
