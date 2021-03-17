package com.develogical;

import com.weather.Day;
import com.weather.Region;

public interface WeatherForecaster {
	CustomForecast forecastFor(Region region, Day day);
}
