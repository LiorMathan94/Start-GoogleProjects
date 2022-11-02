package exercise1;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Map;

public class HTTPClientFacade {

    public HttpResponse sendGet(String url, Map<String, String> body) throws IOException, ParseException {
        HttpGet request = new HttpGet(url);

        for (String headerName : body.keySet()) {
            request.addHeader(headerName, body.get(headerName));
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return new HttpResponse(response);
        }
    }

    public HttpResponse sendPost(String url, Map<String, String> body) throws IOException, ParseException {
        HttpPost request = new HttpPost(url);

        for (String headerName : body.keySet()) {
            request.addHeader(headerName, body.get(headerName));
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return new HttpResponse(response);
        }
    }

    public HttpResponse sendPut(String url, Map<String, String> body) throws IOException, ParseException {
        HttpPut request = new HttpPut(url);

        for (String headerName : body.keySet()) {
            request.addHeader(headerName, body.get(headerName));
        }

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

    public HttpResponse sendPatch(String url, Map<String, String> body) throws IOException, ParseException {
        HttpPatch request = new HttpPatch(url);

        for (String headerName : body.keySet()) {
            request.addHeader(headerName, body.get(headerName));
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {
            return new HttpResponse(response);
        }
    }
}
