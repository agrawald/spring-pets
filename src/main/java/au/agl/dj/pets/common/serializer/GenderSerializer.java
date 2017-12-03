package au.agl.dj.pets.common.serializer;

import au.agl.dj.pets.domain.Gender;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serializer for {@link Gender} used by {@link com.fasterxml.jackson.databind.ObjectMapper}
 */
public class GenderSerializer extends JsonSerializer<Gender> {
    @Override
    public void serialize(Gender gender, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(gender != null ? gender.getValue() : "");
    }
}
