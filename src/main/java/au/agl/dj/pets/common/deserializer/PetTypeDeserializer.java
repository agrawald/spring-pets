package au.agl.dj.pets.common.deserializer;

import au.agl.dj.pets.domain.PetType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Optional;

public class PetTypeDeserializer extends JsonDeserializer<PetType> {
    @Override
    public PetType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final String type = p.getValueAsString();
        final Optional<PetType> oPetType = PetType.parse(type);
        return oPetType.orElse(null);
    }
}
