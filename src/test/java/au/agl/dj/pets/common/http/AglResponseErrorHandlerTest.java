package au.agl.dj.pets.common.http;

import mockit.Expectations;
import mockit.FullVerificationsInOrder;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class AglResponseErrorHandlerTest {
    @Tested
    AglResponseErrorHandler aglResponseErrorHandler;

    @Test
    public void shouldReturnTrueWhenHasError(@Mocked ClientHttpResponse response) throws IOException {
        new Expectations() {{
            response.getStatusCode();
            result = HttpStatus.BAD_REQUEST;
        }};
        assertTrue(aglResponseErrorHandler.hasError(response));
        new FullVerificationsInOrder() {{
            response.getStatusCode();
        }};
    }

    @Test
    public void shouldReturnFalseWhenHasError(@Mocked ClientHttpResponse response) throws IOException {
        new Expectations() {{
            response.getStatusCode();
            result = HttpStatus.OK;
        }};
        assertFalse(aglResponseErrorHandler.hasError(response));
        new FullVerificationsInOrder() {{
            response.getStatusCode();
        }};
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOException(@Mocked ClientHttpResponse response) throws IOException {
        new Expectations() {{
            response.getStatusCode();
            result = new IllegalArgumentException();
            response.getRawStatusCode();
            result = new IOException();
        }};
        aglResponseErrorHandler.hasError(response);
    }
}