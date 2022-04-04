package co.edu.udea.ayds2.services.web.implementations;

import co.edu.udea.ayds2.dto.user.UserDto;
import co.edu.udea.ayds2.services.web.interfaces.WebRequest;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@Service
@Primary
public class HttpWebRequest implements WebRequest {
    private static final Logger logger = LoggerFactory.getLogger(HttpWebRequest.class);

    @Value("${microservice.admin.user.uri}")
    private String uriPrefix;

    @Override
    public UserDto requestUserInformationById(String userId) {
        HttpClient httpClient = HttpClient.newHttpClient();
        JSONObject jsonObject;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(uriPrefix + "/get/user/" + userId))
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if(httpResponse.statusCode() == 200) {
                jsonObject = new JSONObject(httpResponse.body());
                return buildUserObject(jsonObject);
            }
            throw new RuntimeException("User with id " + userId + " not found.");
        } catch(IOException | InterruptedException e) {
            logger.error("Error with user request: " + e.getMessage());
        }
        return null;
    }

    private UserDto buildUserObject(JSONObject jsonObject) {
        return UserDto.builder()
                .name(jsonObject.get("name").toString())
                .email(jsonObject.get("email").toString())
                .build();
    }
}
