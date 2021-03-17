package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import java.util.*;

public class CustomForecaster {
    private Forecaster forecaster;
    private int cacheLimit = 1;
    private ArrayList<String> cacheList = new ArrayList<String>();
    private Map<String, CustomForecast> cache = new HashMap<String, CustomForecast>();

    public CustomForecaster(Forecaster forecaster) {
        this.forecaster = forecaster;
    }

    public CustomForecast forecastFor(Region region, Day day) {
        String key = region.name() + day.name();
        if (cacheList.contains(key)) {
            return cache.get(key);
        }
        Forecast original = this.forecaster.forecastFor(region, day);
        CustomForecast customForecast = new CustomForecast(original.summary(), original.temperature());
        if (cacheList.size() >= this.cacheLimit) {
            String oldestKey = cacheList.get(0);
            cacheList.remove(0);
            cache.remove(oldestKey);
        }
        cacheList.add(key);
        cache.put(key, customForecast );

        return customForecast;
    }

    public void setLimit(int i) {
        this.cacheLimit = i;
    }
}
