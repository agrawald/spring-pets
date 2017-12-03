package au.agl.dj.pets.facade;

import au.agl.dj.pets.domain.Gender;
import au.agl.dj.pets.domain.People;
import au.agl.dj.pets.domain.Pet;
import au.agl.dj.pets.domain.PetType;
import au.agl.dj.pets.service.PeopleSvc;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class AglFacade {
    private final PeopleSvc peopleSvc;

    public AglFacade(PeopleSvc peopleSvc) {
        this.peopleSvc = peopleSvc;
    }

    /**
     * Function to get Cats with Owners Gender
     *
     * @return Map of {@link Gender} and {@link Set} of Cat names
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<Gender, Set<String>> getCatsWithOwnersGender() throws ExecutionException, InterruptedException {
        //EnumMap for Gender to seperate pet names for owners gender
        final EnumMap<Gender, Set<String>> result = new EnumMap<>(Gender.class);
        //lets get all the people
        final List<People> peopleList = peopleSvc.findAll();
        if (!CollectionUtils.isEmpty(peopleList)) {
            //lets create a supplier of people who have pets.
            final Supplier<Stream<People>> peopleWithPetsSupplier = () -> peopleList
                    .parallelStream()
                    .filter(people -> !CollectionUtils.isEmpty(people.getPets()));
            //let get the pets with male owner and female owners parallely
            final CompletableFuture<Set<String>> catsWithMaleOwners = getPetsForGender(peopleWithPetsSupplier, Gender.MALE, PetType.CAT);
            final CompletableFuture<Set<String>> catsWithFemaleOwners = getPetsForGender(peopleWithPetsSupplier, Gender.FEMALE, PetType.CAT);
            //lets wait for both the futures to complete
            CompletableFuture.allOf(catsWithMaleOwners, catsWithFemaleOwners).join();
            //populate the map
            result.put(Gender.MALE, catsWithMaleOwners.get());
            result.put(Gender.FEMALE, catsWithFemaleOwners.get());
        }
        return result;
    }

    /**
     * Asynchronous function to get all the pets of a particular {@link PetType} and owned by people with a specific {@link Gender}
     *
     * @param peopleWithPetsSupplier {@link Supplier} of {@link Stream} of {@link People}
     * @param gender                 Owners {@link Gender}
     * @param petType                Pets {@link PetType}
     * @return Future of {@link Set} of pet names
     */
    @Async
    CompletableFuture<Set<String>> getPetsForGender(final Supplier<Stream<People>> peopleWithPetsSupplier,
                                                    final Gender gender,
                                                    final PetType petType) {
        final Stream<People> sPeople = peopleWithPetsSupplier.get()
                .filter(people -> gender == people.getGender());
        return CompletableFuture.completedFuture(sPeople
                .flatMap(people -> people.getPets()
                        .parallelStream()
                        .filter(pet -> petType == pet.getType()))
                .map(Pet::getName)
                .collect(Collectors.toCollection(TreeSet::new)));
    }
}
