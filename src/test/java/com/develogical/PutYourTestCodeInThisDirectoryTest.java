package com.develogical;

import com.weather.Day;
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
    public void placeholder() throws Exception {
        CustomForecaster underTest = new CustomForecaster(forecaster);
        underTest.forecastFor(Region.LONDON, Day.MONDAY);

        verify(forecaster).forecastFor(Region.LONDON, Day.MONDAY);
    }
}
