package com.develogical;

public class CustomForecast {
    private final java.lang.String summary;
    private final int temperature;

    public CustomForecast(java.lang.String summary, int temperature) { /* compiled code */
        this.summary = summary;
        this.temperature = temperature;
    }

    public java.lang.String summary() {
        return this.summary;
    }

    public int temperature() {
        return this.temperature;
    }
}
