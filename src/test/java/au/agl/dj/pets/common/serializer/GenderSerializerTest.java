package au.agl.dj.pets.common.serializer;

import au.agl.dj.pets.domain.Gender;
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
public class GenderSerializerTest {
    @Tested
    GenderSerializer genderSerializer;

    @Test
    public void shouldSerializeNullToBlank(@Mocked JsonGenerator gen, @Mocked SerializerProvider serializer) throws IOException {
        new Expectations() {{
            gen.writeString("");
        }};

        genderSerializer.serialize(null, gen, serializer);

        new FullVerificationsInOrder() {{
            gen.writeString("");
        }};
    }

    @Test
    public void shouldSerializeValidGenderToString(@Mocked JsonGenerator gen, @Mocked SerializerProvider serializer) throws IOException {
        new Expectations() {{
            gen.writeString("Male");
        }};

        genderSerializer.serialize(Gender.MALE, gen, serializer);

        new FullVerificationsInOrder() {{
            gen.writeString("Male");
        }};
    }

}