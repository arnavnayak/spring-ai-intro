package guru.springframework.springaiintro.assignmentcityinfo.services;

import guru.springframework.springaiintro.assignmentcityinfo.model.GetCityRequest;
import guru.springframework.springaiintro.assignmentcityinfo.model.GetCityResponse;

public interface CityInformation {

    String getCityDetails(GetCityRequest cityName);
}
