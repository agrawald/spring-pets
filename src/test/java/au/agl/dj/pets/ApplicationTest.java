package au.agl.dj.pets;

import au.agl.dj.pets.domain.Gender;
import au.agl.dj.pets.facade.AglFacade;
import mockit.Expectations;
import mockit.FullVerificationsInOrder;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

@RunWith(JMockit.class)
public class ApplicationTest {
    @Tested
    Application application;
    @Injectable
    AglFacade aglFacade;

    @Test
    public void shouldPrintNothingWithEmptyMap() throws Exception {
        new Expectations() {{
            aglFacade.getCatsWithOwnersGender();
            result = Collections.emptyMap();
        }};

        application.run((String) null);

        new FullVerificationsInOrder() {{
            aglFacade.getCatsWithOwnersGender();
        }};
    }

    @Test
    public void shouldPrintNothingWithNullMap() throws Exception {
        new Expectations() {{
            aglFacade.getCatsWithOwnersGender();
            result = null;
        }};

        application.run((String) null);

        new FullVerificationsInOrder() {{
            aglFacade.getCatsWithOwnersGender();
        }};
    }

    @Test
    public void shouldPrintCats() throws Exception {
        final Map<Gender, Set<String>> testData = new EnumMap<>(Gender.class);
        testData.put(Gender.FEMALE, new TreeSet<>(Collections.singletonList("TestFemale")));
        new Expectations() {{
            aglFacade.getCatsWithOwnersGender();
            result = testData;
        }};

        application.run((String) null);

        new FullVerificationsInOrder() {{
            aglFacade.getCatsWithOwnersGender();
        }};
    }
}