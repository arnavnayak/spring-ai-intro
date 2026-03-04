package guru.springframework.springaiintro.springaifunctions.weatherinfo.services;

import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.Answer;
import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.Question;

public interface WeatherService {

    public Answer getWeatherInfo(Question question);

}
