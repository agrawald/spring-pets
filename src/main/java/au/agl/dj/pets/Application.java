package au.agl.dj.pets;

import au.agl.dj.pets.domain.Gender;
import au.agl.dj.pets.facade.AglFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;

@Slf4j
@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {
    private final AglFacade aglFacade;

    public Application(AglFacade aglFacade) {
        this.aglFacade = aglFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final Map<Gender, Set<String>> catsWithOwnersGender = aglFacade.getCatsWithOwnersGender();
        final StringBuffer sb = new StringBuffer();
        if (!CollectionUtils.isEmpty(catsWithOwnersGender)) {
            catsWithOwnersGender.forEach((gender, petNames) -> {
                sb.append(gender.getValue())
                        .append("\n\n");
                petNames.forEach(petName -> sb.append("\t - ")
                        .append(petName)
                        .append("\n"));
                sb.append("\n");
            });
        }
        log.info(sb.toString());
    }
}
