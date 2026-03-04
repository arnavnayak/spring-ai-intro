package guru.springframework.springaiintro.springaifunctions.weatherinfo.functions;

import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.WeatherRequest;
import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

@Slf4j
public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse> {

    public static final String WEATHER_URL = "https://api.api-ninjas.com/v1/weather";

    private final String apiNinjaKey;

    public WeatherServiceFunction(String apiNinjaKey) {
        this.apiNinjaKey = apiNinjaKey;
    }

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        log.info("base URI for weather request: {}", WEATHER_URL);

        RestClient restClient = RestClient.builder()
                .baseUrl(WEATHER_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key",apiNinjaKey);
                    httpHeaders.set("Accept","application/json");
                    httpHeaders.set("Content-Type","application/json");
                }).build();

        return restClient.get().uri(uriBuilder -> {
            log.info("Building URI for weather request: {}", weatherRequest);

            uriBuilder.queryParam("lat",weatherRequest.latitude());
            uriBuilder.queryParam("lon",weatherRequest.longitude());

            log.info("uri built : {}", uriBuilder.build());
            return uriBuilder.build();
        }).retrieve().body(WeatherResponse.class);
    }
}
