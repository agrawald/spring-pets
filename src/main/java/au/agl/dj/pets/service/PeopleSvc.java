package au.agl.dj.pets.service;

import au.agl.dj.pets.common.AglApi;
import au.agl.dj.pets.config.properties.AglProps;
import au.agl.dj.pets.domain.People;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Service class to handle everything related to People.
 */
@Service
public class PeopleSvc {
    private final RestTemplate restTemplate;
    private final AglProps aglProps;

    public PeopleSvc(final RestTemplate restTemplate, final AglProps aglProps) {
        this.restTemplate = restTemplate;
        this.aglProps = aglProps;
    }

    /**
     * Function to find all the people by invoking AGL cloud REST API
     *
     * @return list of {@link People}
     */
    public List<People> findAll() {
        final People[] peopleArr = restTemplate.getForObject(aglProps.generateUrl(AglApi.PEOPLE), People[].class);
        return peopleArr != null ? Arrays.asList(peopleArr) : Collections.emptyList();
    }
}
