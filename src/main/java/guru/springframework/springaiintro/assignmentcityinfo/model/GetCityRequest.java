package guru.springframework.springaiintro.assignmentcityinfo.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCityRequest(@JsonPropertyDescription("The City name") String cityName) {
}
