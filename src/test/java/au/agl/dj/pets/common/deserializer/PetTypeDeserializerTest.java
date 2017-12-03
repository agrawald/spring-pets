package au.agl.dj.pets.common.deserializer;

import au.agl.dj.pets.domain.PetType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.FullVerificationsInOrder;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class PetTypeDeserializerTest {
    @Tested
    PetTypeDeserializer petTypeDeserializer;
    @Mocked
    JsonParser p;
    @Mocked
    DeserializationContext ctxt;

    @Test
    public void shouldDeserializeEmptyToNull() throws IOException {
        new Expectations() {{
            p.getValueAsString();
            result = "";
        }};

        assertNull(petTypeDeserializer.deserialize(p, ctxt));

        new FullVerificationsInOrder() {{
            p.getValueAsString();
            PetType.parse("");
        }};
    }

    @Test
    public void shouldDeserializeNullToNull() throws IOException {
        new Expectations() {{
            p.getValueAsString();
            result = null;
        }};

        assertNull(petTypeDeserializer.deserialize(p, ctxt));

        new FullVerificationsInOrder() {{
            p.getValueAsString();
            PetType.parse("");
        }};
    }

    @Test
    public void shouldDeserializeInvalidStringToNull() throws IOException {
        new Expectations() {{
            p.getValueAsString();
            result = "test";
        }};

        assertNull(petTypeDeserializer.deserialize(p, ctxt));

        new FullVerificationsInOrder() {{
            p.getValueAsString();
            PetType.parse("test");
        }};
    }

    @Test
    public void shouldDeserializeValidStringToPetType() throws IOException {
        new Expectations() {{
            p.getValueAsString();
            result = "cat";
        }};

        assertEquals(PetType.CAT, petTypeDeserializer.deserialize(p, ctxt));

        new FullVerificationsInOrder() {{
            p.getValueAsString();
            PetType.parse("cat");
        }};
    }
}