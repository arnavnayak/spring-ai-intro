package guru.springframework.springaiintro.assignmentcityinfo.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCityResponse(@JsonPropertyDescription("The City information") String cityDetails) {
}
