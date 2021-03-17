package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class CustomForecaster {
    private Forecaster forecaster;

    public CustomForecaster(Forecaster forecaster) {
        this.forecaster = forecaster;
    }

    public Forecast forecastFor(Region region, Day day) {
        this.forecaster.forecastFor(region, day);
        return null;
    }
}
