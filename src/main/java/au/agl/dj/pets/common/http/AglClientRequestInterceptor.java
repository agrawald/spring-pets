package au.agl.dj.pets.common.http;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Interceptor to decorate the request with AGL custom headers can be authorization etc.
 */
@Component
public class AglClientRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        //TODO logic to decorate the request header with JWT,OAuth authorization tokens
        request.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        return execution.execute(request, body);
    }
}
