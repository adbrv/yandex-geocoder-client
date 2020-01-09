package ru.yandex.gecoder.client;

import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Dmitry Andreev <a href="mailto:AndreevDm@gmail.com"/>
 * @date 10.04.2014
 */
public class YaGeocoderTest {
    @Test
    public void testDirectGeocode() throws Exception {
        /**
         * @TODO
         * Добавить актуальный ключ апи
         */
        YaGeocoder geocoder = new YaGeocoder(new DefaultHttpClient(), "");
        String request = "Москва, Льва Толстого 16";
        GeocoderResponse response = geocoder.directGeocode(request);

        Assert.assertEquals(request, response.getRequest());
        Assert.assertEquals(1, response.getGeoObjects().size());
        GeoObject geoObject = response.getGeoObjects().get(0);
        Assert.assertEquals("улица Льва Толстого, 16", geoObject.getName());
        Assert.assertEquals("Россия, Москва, улица Льва Толстого, 16", geoObject.getAddress());
        Assert.assertEquals("улица Льва Толстого", geoObject.getThoroughfare());
        Assert.assertEquals("16", geoObject.getPremise());
        Assert.assertEquals(new GeoPoint(37.587093, 55.733969), geoObject.getPoint());

    }
}
