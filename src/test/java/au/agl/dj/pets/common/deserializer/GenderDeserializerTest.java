package au.agl.dj.pets.common.deserializer;

import au.agl.dj.pets.domain.Gender;
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
public class GenderDeserializerTest {
    @Tested
    GenderDeserializer genderDeserializer;
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

        assertNull(genderDeserializer.deserialize(p, ctxt));

        new FullVerificationsInOrder() {{
            p.getValueAsString();
            Gender.parse("");
        }};
    }

    @Test
    public void shouldDeserializeNullToNull() throws IOException {
        new Expectations() {{
            p.getValueAsString();
            result = null;
        }};

        assertNull(genderDeserializer.deserialize(p, ctxt));

        new FullVerificationsInOrder() {{
            p.getValueAsString();
            Gender.parse(null);
        }};
    }

    @Test
    public void shouldDeserializeInvalidStringToNull() throws IOException {
        new Expectations() {{
            p.getValueAsString();
            result = "test";
        }};

        assertNull(genderDeserializer.deserialize(p, ctxt));

        new FullVerificationsInOrder() {{
            p.getValueAsString();
            Gender.parse("test");
        }};
    }

    @Test
    public void shouldDeserializeValidStringToGender() throws IOException {
        new Expectations() {{
            p.getValueAsString();
            result = "male";
        }};

        assertEquals(Gender.MALE, genderDeserializer.deserialize(p, ctxt));

        new FullVerificationsInOrder() {{
            p.getValueAsString();
            Gender.parse("male");
        }};
    }
}