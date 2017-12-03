package au.agl.dj.pets.config.properties;


import au.agl.dj.pets.common.AglApi;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "agl")
public class AglProps {
    private String server;

    public String generateUrl(final AglApi aglApi) {
        return this.getServer() + aglApi.getApi();
    }
}
