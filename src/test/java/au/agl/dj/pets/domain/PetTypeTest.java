package au.agl.dj.pets.domain;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class PetTypeTest {
    @Test
    public void shouldParseEmptyToOptionalEmpty() {
        assertEquals(Optional.empty(), PetType.parse(""));
    }
    @Test
    public void shouldParseNullToOptionalEmpty() {
        assertEquals(Optional.empty(), PetType.parse(null));
    }
    @Test
    public void shouldParseInvalidPetTypeToOptionalEmpty() {
        assertEquals(Optional.empty(), PetType.parse("test"));
    }
    @Test
    public void shouldParseValidPetTypeToOptionalPetType() {
        assertEquals(Optional.of(PetType.CAT), PetType.parse("Cat"));
    }
}