package au.agl.dj.pets.config.properties;

import au.agl.dj.pets.IntegrationTest;
import au.agl.dj.pets.common.AglApi;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class AglPropsTest extends IntegrationTest {
    @Autowired
    AglProps aglProps;

    @Test
    public void shouldGenerateUrl() {
        assertEquals("http://agl-developer-test.azurewebsites.net/people.json", aglProps.generateUrl(AglApi.PEOPLE));
    }
}