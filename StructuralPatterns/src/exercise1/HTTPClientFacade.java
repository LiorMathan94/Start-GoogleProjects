package exercise1;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HTTPClientFacade {

    public HttpResponse sendGet(String url, List<BasicNameValuePair> body) throws IOException, ParseException {
        HttpGet request = new HttpGet(url);

        request.setEntity(new UrlEncodedFormEntity(body));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return new HttpResponse(response);
        }
    }

    public HttpResponse sendPost(String url, List<BasicNameValuePair> body) throws IOException, ParseException {
        HttpPost request = new HttpPost(url);

        request.setEntity(new UrlEncodedFormEntity(body));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return new HttpResponse(response);
        }
    }

    public HttpResponse sendPut(String url, List<BasicNameValuePair> body) throws IOException, ParseException {
        HttpPut request = new HttpPut(url);

        request.setEntity(new UrlEncodedFormEntity(body));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return new HttpResponse(response);
        }
    }

    public HttpResponse sendDelete(String url) throws IOException, ParseException {
        HttpDelete request = new HttpDelete(url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return new HttpResponse(response);
        }
    }

    public HttpResponse sendPatch(String url, List<BasicNameValuePair> body) throws IOException, ParseException {
        HttpPatch request = new HttpPatch(url);

        request.setEntity(new UrlEncodedFormEntity(body));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return new HttpResponse(response);
        }
    }
}
