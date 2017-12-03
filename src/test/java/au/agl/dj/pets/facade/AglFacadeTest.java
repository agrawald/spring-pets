package au.agl.dj.pets.facade;

import au.agl.dj.pets.domain.Gender;
import au.agl.dj.pets.domain.People;
import au.agl.dj.pets.domain.Pet;
import au.agl.dj.pets.domain.PetType;
import au.agl.dj.pets.service.PeopleSvc;
import mockit.Expectations;
import mockit.FullVerificationsInOrder;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class AglFacadeTest {
    @Tested
    AglFacade facade;
    @Injectable
    PeopleSvc peopleSvc;

    @Test
    public void shouldReturnEmptyMapForNoPeopleFound() throws ExecutionException, InterruptedException {
        new Expectations() {{
            peopleSvc.findAll();
            result = null;
        }};

        assertTrue(CollectionUtils.isEmpty(facade.getCatsWithOwnersGender()));

        new FullVerificationsInOrder() {{
            peopleSvc.findAll();
        }};
    }

    @Test
    public void shouldReturnEmptyMapForNoPets() throws ExecutionException, InterruptedException {
        final People people = People.builder()
                .age(12)
                .gender(Gender.FEMALE)
                .name("TestPeople")
                .build();
        new Expectations() {{
            peopleSvc.findAll();
            result = Collections.singletonList(people);
        }};

        final Map<Gender, Set<String>> result = facade.getCatsWithOwnersGender();
        assertTrue(!CollectionUtils.isEmpty(result));
        assertEquals(2, result.size());
        assertTrue(CollectionUtils.isEmpty(result.get(Gender.MALE)));
        assertTrue(CollectionUtils.isEmpty(result.get(Gender.FEMALE)));

        new FullVerificationsInOrder() {{
            peopleSvc.findAll();
        }};
    }

    @Test
    public void shouldReturnMapOfCatsWithFemaleOwners() throws ExecutionException, InterruptedException {
        final People catOwner = People.builder()
                .age(12)
                .gender(Gender.FEMALE)
                .name("TestFemale")
                .pets(Collections.singletonList(Pet.builder()
                        .name("Felix")
                        .type(PetType.CAT)
                        .build()))
                .build();
        final People dogOwner = People.builder()
                .age(22)
                .gender(Gender.MALE)
                .name("TestMale")
                .pets(Collections.singletonList(Pet.builder()
                        .name("Fred")
                        .type(PetType.DOG)
                        .build()))
                .build();
        new Expectations() {{
            peopleSvc.findAll();
            result = Arrays.asList(catOwner, dogOwner);
        }};

        final Map<Gender, Set<String>> result = facade.getCatsWithOwnersGender();
        assertTrue(!CollectionUtils.isEmpty(result));
        assertEquals(2, result.size());
        assertEquals(0, result.get(Gender.MALE).size());
        assertEquals(1, result.get(Gender.FEMALE).size());
        assertEquals("Felix", result.get(Gender.FEMALE).toArray()[0]);

        new FullVerificationsInOrder() {{
            peopleSvc.findAll();
        }};
    }
}