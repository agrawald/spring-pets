package au.agl.dj.pets.common.deserializer;

import au.agl.dj.pets.domain.Gender;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Optional;

/**
 * Class to defin how {@link Gender} will be deserialized by the {@link com.fasterxml.jackson.databind.ObjectMapper}
 */
public class GenderDeserializer extends JsonDeserializer<Gender> {
    @Override
    public Gender deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final String gender = p.getValueAsString();
        final Optional<Gender> oGender = Gender.parse(gender);
        return oGender.orElse(null);
    }
}
