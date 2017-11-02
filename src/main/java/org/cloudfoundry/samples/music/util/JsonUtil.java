package org.cloudfoundry.samples.music.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
    
    private static final String CREDENTIALS_KEY = "credentials";
    private static final String USER_PROVIDED_KEY = "user-provided";
    private static final String EXTERNAL_NEWS_URL_KEY = "url";

    public static String getVCAPServicesURL(String vcapServices) {

        JsonElement jsonElemen = new JsonParser().parse(vcapServices);
        JsonObject jsonObject = jsonElemen.getAsJsonObject();
        JsonArray jsonArray = jsonObject.getAsJsonArray(USER_PROVIDED_KEY);
        jsonElemen = jsonArray.get(0);
        jsonObject = jsonElemen.getAsJsonObject();
        jsonElemen = jsonObject.get(CREDENTIALS_KEY);
        jsonObject = jsonElemen.getAsJsonObject();
        return jsonObject.get(EXTERNAL_NEWS_URL_KEY).getAsString();
    }

    public static String getJsonContentFromURL(String url) throws ClientProtocolException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        StringBuilder result = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}
