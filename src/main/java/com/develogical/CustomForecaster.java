package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import java.util.*;

public class CustomForecaster {
    private Forecaster forecaster;
    private Map<String, CustomForecast> cache = new HashMap<String, CustomForecast>();

    public CustomForecaster(Forecaster forecaster) {
        this.forecaster = forecaster;
    }

    public CustomForecast forecastFor(Region region, Day day) {
        String key = region.name() + day.name();
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        Forecast original = this.forecaster.forecastFor(region, day);
        CustomForecast customForecast = new CustomForecast(original.summary(), original.temperature());
        cache.put(key, customForecast );

        return customForecast;
    }

    public void setLimit(int i) {
    }
}
