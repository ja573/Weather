package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class PutYourTestCodeInThisDirectoryTest {
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
}
