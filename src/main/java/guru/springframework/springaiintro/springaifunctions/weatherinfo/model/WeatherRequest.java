package guru.springframework.springaiintro.springaifunctions.weatherinfo.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Created by jt, Spring Framework Guru.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Weather API request")
public record WeatherRequest(@JsonProperty(required = true,
        value = "latitude") @JsonPropertyDescription("latitude of the location") String latitude,
        @JsonProperty(required = true, value = "longitude") @JsonPropertyDescription("longitude of the location") String longitude){
}
