package guru.springframework.springaiintro.springaifunctions.weatherinfo.controller;

import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.Answer;
import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.Question;
import guru.springframework.springaiintro.springaifunctions.weatherinfo.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping("/weather")
    public Answer getWeatherDetails(@RequestBody Question question){
        return weatherService.getWeatherInfo(question);
    }
}
