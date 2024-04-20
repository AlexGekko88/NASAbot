package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Utils {
    public static String getUrl(String nasaURL) {
        //HTTP клиент, который будет отправлять запросы
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //Сущность, которая будет преобразовывать ответ в наш объект NASA
        ObjectMapper mapper = new ObjectMapper();

        // Отправляем запрос и получаем ответ с нашей картинкой
        HttpGet httpGet = new HttpGet(nasaURL);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            NASAAnswer answer = mapper.readValue(response.getEntity().getContent(), NASAAnswer.class);
            return answer.getUrl();
        } catch (Exception e) {
            System.out.println("Сервер NASA не доступен");
        }
        return " ";

    }
}
