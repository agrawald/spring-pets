package au.agl.dj.pets.domain;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class People {
    private String name;
    private Gender gender;
    private int age;
    private List<Pet> pets;
}
