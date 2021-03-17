package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CustomForecasterTest {
    Forecaster forecaster = mock(Forecaster.class);

    @Test
    public void shouldReturnForecast() throws Exception {
        given(forecaster.forecastFor(Region.LONDON, Day.MONDAY)).willReturn(new Forecast("test", 1 ));
        CustomForecaster underTest = new CustomForecaster(forecaster);
        CustomForecast result = underTest.forecastFor(Region.LONDON, Day.MONDAY);
        assertThat(result.summary(),equalTo("test"));
        assertThat(result.temperature(), equalTo(1));
    }

    @Test
    public void shouldOnlyCallOriginalOnce() throws Exception {
        given(forecaster.forecastFor(Region.LONDON, Day.MONDAY)).willReturn(new Forecast("test", 1 ));
        CustomForecaster underTest = new CustomForecaster(forecaster);
        underTest.forecastFor(Region.LONDON, Day.MONDAY);
        underTest.forecastFor(Region.LONDON, Day.MONDAY);
        verify(forecaster, times(1)).forecastFor(Region.LONDON, Day.MONDAY);
    }

    // It should be possible to set a limit for the size of the cache, evicting
    // old entries if the maximum size is reached.

    @Test
    public void shouldBeAbleToSetLimit() throws Exception {
        given(forecaster.forecastFor(Region.LONDON, Day.MONDAY)).willReturn(new Forecast("test", 1 ));
        given(forecaster.forecastFor(Region.LONDON, Day.TUESDAY)).willReturn(new Forecast("test", 2 ));
        given(forecaster.forecastFor(Region.LONDON, Day.WEDNESDAY)).willReturn(new Forecast("test", 3 ));

        CustomForecaster underTest = new CustomForecaster(forecaster);
        underTest.setLimit(2);

        underTest.forecastFor(Region.LONDON, Day.MONDAY);
        underTest.forecastFor(Region.LONDON, Day.TUESDAY);
        underTest.forecastFor(Region.LONDON, Day.WEDNESDAY);
        underTest.forecastFor(Region.LONDON, Day.MONDAY);

        verify(forecaster, times(2)).forecastFor(Region.LONDON, Day.MONDAY);
    }

    @Test
    public void shouldRefreshCacheOlderThanOneHour() throws Exception {
        given(forecaster.forecastFor(Region.LONDON, Day.MONDAY)).willReturn(new Forecast("test", 1 ));

        //would be better to pass in a clock
        CustomForecaster underTest = new CustomForecaster(forecaster) {
            @Override
            public Instant getTimestamp() {
                return Instant.now().minus(3600, ChronoUnit.SECONDS);
            }
        };
        underTest.setLimit(2);

        underTest.forecastFor(Region.LONDON, Day.MONDAY);

        verify(forecaster, times(2)).forecastFor(Region.LONDON, Day.MONDAY);
    }
}
