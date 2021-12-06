package Gateways;

import Session.Session;
import exceptions.UnauthorizedException;
import exceptions.UnknownException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SessionGateway {
    private static final Logger logger = LogManager.getLogger();

    public static Session login(String userName, String password) throws UnauthorizedException {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        logger.info("Finally starting Session");
        try {
            httpclient = HttpClients.createDefault();
            HttpPost postRequest = new HttpPost("http://localhost:8080/login");

            // use this for submitting form data as raw json
            JSONObject formData = new JSONObject();
            logger.info(userName);
            logger.info(password);
            formData.put("username", userName);
            formData.put("password", password);
            String formDataString = formData.toString();

            StringEntity reqEntity = new StringEntity(formDataString);
            postRequest.setEntity(reqEntity);


            response = httpclient.execute(postRequest);

            switch (response.getStatusLine().getStatusCode()) {
                case 200 -> {
                    HttpEntity entity = response.getEntity();
                    // use org.apache.http.util.EntityUtils to read json as string
                    String strResponse = EntityUtils.toString(entity, StandardCharsets.UTF_8);
                    EntityUtils.consume(entity);
                    return new Session(strResponse);
                }
                case 401 -> throw new UnauthorizedException("login failed");
                default -> throw new UnknownException(response.getStatusLine().getReasonPhrase());
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
}
