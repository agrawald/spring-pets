package au.agl.dj.pets.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pet {
    private String name;
    private PetType type;
}
