package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

import java.time.Instant;

public class ForecasterAdapter implements WeatherForecaster {
	private final Forecaster forecaster;

	public ForecasterAdapter(Forecaster forecaster) {
		this.forecaster = forecaster;
	}

	@Override
	public CustomForecast forecastFor(Region region, Day day) {
		Forecast forecast = forecaster.forecastFor(region, day);
		return new CustomForecast(forecast.summary(), forecast.temperature(), Instant.now());
	}
}
