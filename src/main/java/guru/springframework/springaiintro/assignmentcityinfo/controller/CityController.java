package guru.springframework.springaiintro.assignmentcityinfo.controller;

import guru.springframework.springaiintro.assignmentcityinfo.model.GetCityRequest;
import guru.springframework.springaiintro.assignmentcityinfo.model.GetCityResponse;
import guru.springframework.springaiintro.assignmentcityinfo.services.CityInformation;
import guru.springframework.springaiintro.assignmentcityinfo.services.CityInformationImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private final CityInformation cityInformation;

    public CityController(CityInformation cityInformation) {
        this.cityInformation = cityInformation;
    }

    @PostMapping("/city/cityDetails")
    public String getCityDetails(@RequestBody GetCityRequest cityRequest){
      return this.cityInformation.getCityDetails(cityRequest);
    }
}
