package au.agl.dj.pets.common.http;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * AGL specific response error handler which will identify of the response received is a correct response
 * and logic to handle any error
 */
@Component
public class AglResponseErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return super.hasError(response);
        //TODO: Any custom validation to identify if the response is in error
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        super.handleError(response);
        //TODO: Logic to handle the error response.
    }
}
