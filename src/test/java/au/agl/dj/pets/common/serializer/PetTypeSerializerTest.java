package au.agl.dj.pets.common.serializer;

import au.agl.dj.pets.domain.PetType;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import mockit.Expectations;
import mockit.FullVerificationsInOrder;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class PetTypeSerializerTest {
    @Tested
    PetTypeSerializer petTypeSerializer;

    @Test
    public void shouldSerializeNullToBlank(@Mocked JsonGenerator gen, @Mocked SerializerProvider serializers) throws IOException {
        new Expectations() {{
            gen.writeString("");
        }};

        petTypeSerializer.serialize(null, gen, serializers);

        new FullVerificationsInOrder() {{
            gen.writeString("");
        }};
    }

    @Test
    public void shouldSerializeValidPetTypeToString(@Mocked JsonGenerator gen, @Mocked SerializerProvider serializers) throws IOException {
        new Expectations() {{
            gen.writeString("Cat");
        }};

        petTypeSerializer.serialize(PetType.CAT, gen, serializers);

        new FullVerificationsInOrder() {{
            gen.writeString("Cat");
        }};
    }
}