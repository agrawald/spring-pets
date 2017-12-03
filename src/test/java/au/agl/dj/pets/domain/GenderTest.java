package au.agl.dj.pets.domain;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class GenderTest {
    @Test
    public void shouldParseANullToEmpty() {
        assertEquals(Optional.empty(), Gender.parse(null));
    }

    @Test
    public void shouldParseEmtpyToOptionalEmpty() {
        assertEquals(Optional.empty(), Gender.parse(""));
    }

    @Test
    public void shouldParseInvalidStringToOptionalEmpty() {
        assertEquals(Optional.empty(), Gender.parse("test"));
    }

    @Test
    public void shouldParseAValidValueToOptionalGender() {
        assertEquals(Optional.of(Gender.MALE), Gender.parse("male"));
    }
}