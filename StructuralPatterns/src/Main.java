import exercise1.HTTPClientFacade;
import exercise1.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        HTTPClientFacade httpClientFacade = new HTTPClientFacade();

        testHttpGet(httpClientFacade);
        testHttpPost(httpClientFacade);
        testHttpPut(httpClientFacade);
        testHttpDelete(httpClientFacade);
        testHttpPatch(httpClientFacade);
    }

    private static void testHttpGet(HTTPClientFacade httpClientFacade) throws IOException, ParseException {
        List<BasicNameValuePair> body = new ArrayList<>();
        HttpResponse response = httpClientFacade.sendGet("https://reqres.in/api/users?page=2", body);
        System.out.println(response.getEntity());
    }

    private static void testHttpPost(HTTPClientFacade httpClientFacade) throws IOException, ParseException {
        List<BasicNameValuePair> body = new ArrayList<>();
        body.add (new BasicNameValuePair("name", "morpheus"));
        body.add (new BasicNameValuePair("job", "leader"));
        HttpResponse response = httpClientFacade.sendPost("https://reqres.in/api/users", body);
        System.out.println(response.getEntity());
    }

    private static void testHttpPut(HTTPClientFacade httpClientFacade) throws IOException, ParseException {
        List<BasicNameValuePair> body = new ArrayList<>();
        body.add (new BasicNameValuePair("name", "morpheus"));
        body.add (new BasicNameValuePair("job", "zion resident"));

        HttpResponse response = httpClientFacade.sendPut("https://reqres.in/api/users/2", body);
        System.out.println(response.getEntity());
    }

    private static void testHttpDelete(HTTPClientFacade httpClientFacade) throws IOException, ParseException {
        HttpResponse response = httpClientFacade.sendDelete("https://reqres.in/api/users/2");
        System.out.println(response.getEntity());
    }

    private static void testHttpPatch(HTTPClientFacade httpClientFacade) throws IOException, ParseException {
        List<BasicNameValuePair> body = new ArrayList<>();
        body.add (new BasicNameValuePair("name", "morpheus"));
        body.add (new BasicNameValuePair("job", "zion resident"));

        HttpResponse response = httpClientFacade.sendPatch("https://reqres.in/api/users/2", body);
        System.out.println(response.getEntity());
    }
}