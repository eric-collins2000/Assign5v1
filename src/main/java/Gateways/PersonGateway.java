package Gateways;


import DataModels.Person;
import exceptions.UnauthorizedException;
import exceptions.UnknownException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PersonGateway {
    private static final String URL = "http://localhost:8080";

    public PersonGateway() {
    }

    public static List<Person> fetchPersons(String token) throws UnauthorizedException, UnknownException {
        List<Person> people = new ArrayList<>();
        logger.info("Fetching\n\n");
        try {
            String response = executeGetRequest(URL + "/people", token);
            JSONArray personList = new JSONArray(response);
            for (Object person : personList) {
                people.add(Person.fromJSONObject((JSONObject) person));
            }
        } catch(RuntimeException e) {
            throw new UnknownException(e);
        }
        return people;
    }

    private static String executeGetRequest(String url, String token) throws UnauthorizedException, UnknownException {
        logger.info("Executing");
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet(url);

            if(token != null && token.length() > 0)
                getRequest.setHeader("Authorization", token);

            response = httpclient.execute(getRequest);
            logger.error(response);

            switch(response.getStatusLine().getStatusCode()) {
                case 200:
                    return getStringFromResponse(response);
                case 401:
                    throw new UnauthorizedException(response.getStatusLine().getReasonPhrase());
                default:
                    throw new UnknownException(response.getStatusLine().getReasonPhrase());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new UnauthorizedException(e);

        } finally {
            try {
                if(response != null) {
                    response.close();
                }
                if(httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new UnauthorizedException(e);
            }
        }
    }

    private static String getStringFromResponse(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        String strResponse = EntityUtils.toString(entity, StandardCharsets.UTF_8);
        EntityUtils.consume(entity);
        return strResponse;
    }
    private static final Logger logger = LogManager.getLogger();
}
