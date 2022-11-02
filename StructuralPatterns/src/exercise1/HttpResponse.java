package exercise1;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class HttpResponse {
    private CloseableHttpResponse customResponse;
    private String entity;

    public HttpResponse(CloseableHttpResponse response) throws IOException, ParseException {
        customResponse = response;

        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            entity = EntityUtils.toString(responseEntity);
        }
    }

    public String getEntity() {
        return entity;
    }
}
