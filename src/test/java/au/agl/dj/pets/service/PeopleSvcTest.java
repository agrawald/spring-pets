package au.agl.dj.pets.service;

import au.agl.dj.pets.IntegrationTest;
import au.agl.dj.pets.domain.People;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

public class PeopleSvcTest extends IntegrationTest {
    @Autowired
    PeopleSvc peopleSvc;

    @Test
    public void shouldFindAllPeople() {
        server.expect(requestTo("http://agl-developer-test.azurewebsites.net/people.json"))
                .andRespond(MockRestResponseCreators.withSuccess("[{\"name\":\"Bob\",\"gender\":\"Male\",\"age\":23,\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"},{\"name\":\"Fido\",\"type\":\"Dog\"}]}]", MediaType.APPLICATION_JSON_UTF8));
        final List<People> peopleList = peopleSvc.findAll();
        assertNotNull(peopleList);
        assertEquals(1, peopleList.size());
        assertEquals("Bob", peopleList.get(0).getName());
    }


    @Test
    public void shouldReturnEmptyList() {
        server.expect(requestTo("http://agl-developer-test.azurewebsites.net/people.json"))
                .andRespond(MockRestResponseCreators.withSuccess("[]", MediaType.APPLICATION_JSON_UTF8));
        final List<People> peopleList = peopleSvc.findAll();
        assertNotNull(peopleList);
        assertEquals(0, peopleList.size());
    }

    @Test(expected = HttpClientErrorException.class)
    public void shouldThrowClientErrorWhenBadRequest() {
        server.expect(requestTo("http://agl-developer-test.azurewebsites.net/people.json"))
                .andRespond(MockRestResponseCreators.withBadRequest());
        peopleSvc.findAll();
    }
}