package com.develogical;

import java.time.Instant;

public class CustomForecast {
    private final Instant timestamp;
    private final java.lang.String summary;
    private final int temperature;

    public CustomForecast(java.lang.String summary, int temperature, Instant timestamp) { /* compiled code */
        this.summary = summary;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    public java.lang.String summary() {
        return this.summary;
    }

    public int temperature() {
        return this.temperature;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }
}
