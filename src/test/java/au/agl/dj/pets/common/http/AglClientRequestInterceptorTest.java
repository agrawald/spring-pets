package au.agl.dj.pets.common.http;

import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class AglClientRequestInterceptorTest {
    @Tested
    AglClientRequestInterceptor interceptor;
    @Mocked
    HttpRequest request;
    @Mocked
    ClientHttpRequestExecution execution;

    @Test
    public void shouldAddContentTypeToHeaders(@Mocked HttpHeaders headers, @Mocked ClientHttpResponse response) throws IOException {
        new Expectations() {{
            request.getHeaders();
            result = headers;
            execution.execute(request, (byte[])any);
            result = response;
        }};

        byte[] body = "test".getBytes();
        assertEquals(response, interceptor.intercept(request, body, execution));

        new FullVerificationsInOrder() {{
            request.getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            execution.execute(request, body);
        }};
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOException(@Mocked HttpHeaders headers) throws IOException {
        new Expectations() {{
            request.getHeaders();
            result = headers;
            execution.execute(request, (byte[])any);
            result = new IOException();
        }};

        byte[] body = "test".getBytes();
        interceptor.intercept(request, body, execution);

        new FullVerificationsInOrder() {{
            request.getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            execution.execute(request, body);
        }};
    }
}