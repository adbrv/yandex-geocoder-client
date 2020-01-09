package ru.yandex.gecoder.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Dmitry Andreev <a href="mailto:AndreevDm@yandex-team.ru"/>
 * @date 08.04.2014
 */
public class YaGeocoder {

    private static final String GEOCODER_HOST = "https://geocode-maps.yandex.ru/1.x/";
    private static final int HTTP_OK = 200;

    private XmlResponseParser responseParser = new XmlResponseParser();

    private HttpClient httpClient;
    private String apikey;

    public YaGeocoder(HttpClient httpClient, String apikey) {

        this.httpClient = httpClient;
        this.apikey = apikey;
    }



    public GeocoderResponse directGeocode(String geocode) throws IOException {
        String url = GEOCODER_HOST + "?geocode=" + URLEncoder.encode(geocode, "UTF-8") + "&apikey=" + this.apikey;
        HttpUriRequest request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != HTTP_OK) {
            throw new IOException(response.getStatusLine().toString());
        }
        return responseParser.parse(response.getEntity().getContent());
    }
}
