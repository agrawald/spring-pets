package au.agl.dj.pets.common.serializer;

import au.agl.dj.pets.domain.PetType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serializer for {@link PetType} conversion by {@link com.fasterxml.jackson.databind.ObjectMapper}
 */
public class PetTypeSerializer extends JsonSerializer<PetType> {
    @Override
    public void serialize(PetType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value != null ? value.getType() : "");
    }
}
